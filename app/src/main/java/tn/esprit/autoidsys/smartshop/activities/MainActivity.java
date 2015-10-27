package tn.esprit.autoidsys.smartshop.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.skyfishjy.library.RippleBackground;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.autoidsys.smartshop.R;
import tn.esprit.autoidsys.smartshop.models.Category;
import tn.esprit.autoidsys.smartshop.models.Product;
import tn.esprit.autoidsys.smartshop.utils.ChatHeadService;

/**
 * Created by Gordon Wong on 7/17/2015.
 *
 * All items fragment.
 */
public class MainActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //barSystem
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        //ripple BAckground
        final RippleBackground rippleBackground = (RippleBackground)findViewById(R.id.content);
        rippleBackground.startRippleAnimation();

        ImageView imageView=(ImageView)findViewById(R.id.centerImage);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1200);

                            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                            startActivityForResult(intent, 0);


                            overridePendingTransition(R.anim.slide_in_right,
                                    R.anim.slide_out_left);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        final FloatingActionButton actionA = (FloatingActionButton) findViewById(R.id.action_a);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionA.setTitle("Action A clicked");
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.siBubble:
                final Activity activity = this;
                activity.startService(new Intent(activity, ChatHeadService.class));
                break;
        }
        return true;

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) if (resultCode == RESULT_OK) {
            String contents = intent.getStringExtra("SCAN_RESULT");
            String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
            Log.e("content", contents + " " + format);

            if (format.equals("CODE_128")) {

                final Intent intent1 = new Intent(MainActivity.this, ProductActivity.class);

                try {
                    int ref = Integer.parseInt(contents);
                    //  Product product = ScanControllers.getProductByRef(ref);

                    ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("product");
                    query.whereEqualTo("reference", contents);


                    query.findInBackground(new FindCallback<ParseObject>() {

                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            if (objects.size()!= 0) {
                                Product product = new Product();
                                ParseObject object = objects.get(0);

                                product.setDescription(object.get("description").toString());
                                product.setName(object.get("name").toString());
                                product.setUrlPic(object.get("urlPic").toString());
                                product.setPrice(Double.parseDouble(object.get("price").toString()));

                                Log.e("product done  : ", product.toString());
                                intent1.putExtra("product", product);
                                startActivity(intent1);
                            }
                            else
                            {
                                // e.printStackTrace();
                                Log.e("error" ,e.getMessage()+"");                            }
                        }


                    });


                } catch (NumberFormatException e) {

                }


            } else if (format.equals("QR_CODE")) {



                final Intent intent1 = new Intent(MainActivity.this, ListCategoriesActivity.class);

                try {
                    int num = Integer.parseInt(contents);
                    //  Product product = ScanControllers.getProductByRef(ref);



                    ParseQuery<ParseObject> queryRayon = new ParseQuery<ParseObject>("rayon");
                    queryRayon.whereEqualTo("number", num);
                    queryRayon.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            if (objects.size()!= 0) {
                                ParseObject rayon =objects.get(0);
                                ParseQuery<ParseObject> queryCategory = new ParseQuery<ParseObject>("category");
                                queryCategory.whereEqualTo("rayon", rayon);

                                queryCategory.findInBackground(new FindCallback<ParseObject>() {
                                    @Override
                                    public void done(List<ParseObject> objects, ParseException e) {

                                        final ArrayList<Category> categories=new ArrayList<Category>();

                                        if( objects.size()!= 0) {

                                            for( int i=0; i < objects.size();i++)
                                            {
                                                ParseObject object = objects.get(i);
                                                final Category category=new Category();
                                                category.setName(object.get("name").toString());
                                                category.setUrlPic(object.get("urlPic").toString());

                                                ParseQuery<ParseObject> queryProducts = new ParseQuery<ParseObject>("product");
                                                queryProducts.whereEqualTo("category", object);

                                                final ArrayList<Product> products=new ArrayList<Product>();
                                                queryProducts.findInBackground(new FindCallback<ParseObject>() {
                                                    @Override
                                                    public void done(List<ParseObject> objects, ParseException e) {
                                                        for (int i = 0; i < objects.size(); i++) {
                                                            Product product = new Product();
                                                            ParseObject object = objects.get(i);
                                                            product.setDescription(object.get("description").toString());
                                                            product.setName(object.get("name").toString());
                                                            product.setUrlPic(object.get("urlPic").toString());
                                                            product.setPrice(Double.parseDouble(object.get("price").toString()));

                                                            products.add(product);

                                                        }

                                                        category.setProducts(products);
                                                        categories.add(category);
                                                        intent1.putExtra("categories",  categories);
                                                        startActivity(intent1);
                                                    }
                                                });


                                            }


                                        }
                                    }
                                });

                            }
                        }
                    });



                } catch (NumberFormatException e) {

                }

            }
        } else {

        }
        else if (resultCode == RESULT_CANCELED) {
            // Handle cancel
        }
    }



}
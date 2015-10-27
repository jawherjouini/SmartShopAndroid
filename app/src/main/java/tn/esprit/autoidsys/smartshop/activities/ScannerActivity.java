package tn.esprit.autoidsys.smartshop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import at.markushi.ui.CircleButton;
import tn.esprit.autoidsys.smartshop.R;
import tn.esprit.autoidsys.smartshop.models.Category;
import tn.esprit.autoidsys.smartshop.models.Product;


public class ScannerActivity extends ActionBarActivity {

    CircleButton btnScan;
    TextView textStatus ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        btnScan = (CircleButton) findViewById(R.id.btnScan);
          textStatus=(TextView)findViewById(R.id.textStatus);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                // intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                startActivityForResult(intent, 0);

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_qrreader, menu);
        return true;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) if (resultCode == RESULT_OK) {
            String contents = intent.getStringExtra("SCAN_RESULT");
            String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
            Log.e("content", contents + " " + format);

            if (format.equals("CODE_128")) {
                textStatus.setText(contents);
                final Intent intent1 = new Intent(ScannerActivity.this, ProductActivity.class);

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
                    textStatus.setText("status : not valid barcode");
                }


            } else if (format.equals("QR_CODE")) {


                textStatus.setText(contents);
                final Intent intent1 = new Intent(ScannerActivity.this, ListCategoriesActivity.class);

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
                    textStatus.setText("status : not valid barcode");
                }

            }
        } else {

        }
        else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
            }
        }



    }


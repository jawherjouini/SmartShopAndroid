package tn.esprit.autoidsys.smartshop.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import tn.esprit.autoidsys.smartshop.R;
import tn.esprit.autoidsys.smartshop.models.Product;


public class ProductActivity extends ActionBarActivity {

    ImageView image ;
    TextView name ;
    TextView description ;
    TextView price ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

    Product product=(Product)getIntent().getSerializableExtra("product");

        image=(ImageView)findViewById(R.id.prImg);
        name=(TextView)findViewById(R.id.prName);
        description=(TextView)findViewById(R.id.prDescrip);
        price=(TextView)findViewById(R.id.prPrice);


        name.setText(product.getName());
        description.setText(product.getDescription());
        price.setText(product.getPrice()+"");

        Picasso.with(this).load(product.getUrlPic()).into(image);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

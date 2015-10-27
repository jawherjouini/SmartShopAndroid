package tn.esprit.autoidsys.smartshop.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.autoidsys.smartshop.R;
import tn.esprit.autoidsys.smartshop.adapters.CategoryAdapter;
import tn.esprit.autoidsys.smartshop.models.Category;


public class ListCategoriesActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    List<Category> categories =new ArrayList<Category>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_categories);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
       categories= (List<Category>) getIntent().getSerializableExtra("categories");
        mAdapter = new CategoryAdapter(categories,this);
        mRecyclerView.setAdapter(mAdapter);


    }

}

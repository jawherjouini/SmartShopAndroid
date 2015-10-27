package tn.esprit.autoidsys.smartshop.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tn.esprit.autoidsys.smartshop.R;
import tn.esprit.autoidsys.smartshop.models.Category;

/**
 * Created by Fares Ben Hamouda on 22/10/2015.
 */

 public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
        private List<Category> mDataset;

        Context ctx ;
        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            // each data item is just a string in this case

            public ImageView imageCategory ;
            public TextView nameCategory;
            public TextView nbrProducts ;
            public ViewHolder(View v) {
                super(v);

                nameCategory= (TextView)  v.findViewById(R.id.nameCat);
                imageCategory =(ImageView) v.findViewById(R.id.imgCat);
                nbrProducts=(TextView)v.findViewById(R.id.nbrProducts);
            }


            @Override
            public void onClick(View v) {

            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public CategoryAdapter(List<Category> myDataset, Context context) {
            mDataset = myDataset;
    ctx=context;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.one_item_category, parent, false);
            // set the view's size, margins, paddings and layout parameters

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.nameCategory.setText(mDataset.get(position).getName());
            Picasso.with(ctx).load(mDataset.get(position).getUrlPic()).into(holder.imageCategory);
            holder.nbrProducts.setText("number of products :"+mDataset.get(position).getProducts().size());
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }



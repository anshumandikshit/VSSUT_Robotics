package com.robotix_vssut.welcomeanimationapp.items;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexvasilkov.android.commons.adapters.ItemsAdapter;
import com.alexvasilkov.android.commons.utils.Views;
import com.robotix_vssut.welcomeanimationapp.R;
import com.robotix_vssut.welcomeanimationapp.activities.FoldableListActivity;
import com.robotix_vssut.welcomeanimationapp.utils.GlideHelper;

import java.util.Arrays;

/**
 * Created by Hp on 26-12-2016.
 */

public class PaintingsAdapter extends ItemsAdapter<Painting> implements View.OnClickListener {

    public PaintingsAdapter(Context context) {
        super(context);
        setItemsList(Arrays.asList(Painting.getAllPaintings(context.getResources())));
    }

    @Override
    protected View createView(Painting item, int pos, ViewGroup parent, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.list_item3, parent, false);
        ViewHolder vh = new ViewHolder();
        vh.image = Views.find(view, R.id.list_item_image5);
        vh.image.setOnClickListener(this);
        vh.title = Views.find(view, R.id.list_item_title5);
        view.setTag(vh);

        return view;
    }

    @Override
    protected void bindView(Painting item, int pos, View convertView) {
        ViewHolder vh = (ViewHolder) convertView.getTag();

        vh.image.setTag(R.id.list_item_image5, item);
        GlideHelper.loadPaintingImage(vh.image, item);
        vh.title.setText(item.getTitle());
    }

    @Override
    public void onClick(View view) {
        Painting item = (Painting) view.getTag(R.id.list_item_image5);

        if (view.getContext() instanceof FoldableListActivity) {
            Toast.makeText(view.getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
    }

    private static class ViewHolder {
        ImageView image;
        TextView title;
    }

}

package com.robotix_vssut.welcomeanimationapp.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alexvasilkov.android.commons.adapters.ItemsAdapter;
import com.alexvasilkov.android.commons.utils.Views;
import com.alexvasilkov.foldablelayout.FoldableListLayout;
import com.robotix_vssut.welcomeanimationapp.R;
import com.robotix_vssut.welcomeanimationapp.items.PaintingsAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);


        startActivity(new Intent(MainActivity2.this,FoldableListActivity.class));
    }

   /* @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ActivityInfo info = (ActivityInfo) parent.getItemAtPosition(position);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(this, info.name));
        startActivity(intent);
    }

    private BaseAdapter getSampleAdapter() {
        List<ActivityInfo> item1 = new ArrayList<>();

        try {
            ActivityInfo[] activitiesInfo = getPackageManager()
                    .getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES).activities;

            for (ActivityInfo info : activitiesInfo) {
                if (!getClass().getName().equals(info.name)) {
                    item1.add(info);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return new SampleAdapter(this, item1);
    }

    private static class SampleAdapter extends ItemsAdapter<ActivityInfo> {

        SampleAdapter(Context context, List<ActivityInfo> list) {
            super(context);
            setItemsList(list);
        }

        @Override
        protected View createView(ActivityInfo item, int pos, ViewGroup parent,
                                  LayoutInflater inflater) {
            return inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        @Override
        protected void bindView(ActivityInfo item, int pos, View convertView) {
            TextView tv = (TextView) convertView;
            if (TextUtils.isEmpty(item.nonLocalizedLabel)) {
                //tv.setText(item.labelRes);
            } else {
                tv.setText(item.nonLocalizedLabel);
            }
        }

    }*/

}
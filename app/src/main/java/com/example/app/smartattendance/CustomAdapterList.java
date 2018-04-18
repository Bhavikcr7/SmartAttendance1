package com.example.app.smartattendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by VARSHA on 3/25/2018.
 */

public  class CustomAdapterList extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;

    Map<String, String> map = new HashMap<String, String>();

    public CustomAdapterList(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listdesign, null);
        }

        //Handle TextView and display string from your list
        TextView studentList = (TextView)view.findViewById(R.id.nameRollno);
        studentList.setText(list.get(position));

        Button markPresent = (Button)view.findViewById(R.id.markPresent);

        markPresent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                 map.put(list.get(position),"Present");
                //Toast.makeText(context,"Hello",Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}
package com.example.fitbuff;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RecordList extends ArrayAdapter<Record> {

    private Activity context;
    List<Record> records;

    public RecordList(Activity context, List<Record> records) {
        super(context, R.layout.layout_record_list, records);
        this.context = context;
        this.records = records;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_record_list, null, true);
        //initialize
        TextView textViewDate = (TextView) listViewItem.findViewById(R.id.textViewDate);
        TextView textviewweight = (TextView) listViewItem.findViewById(R.id.textviewweight);
        TextView textviewexercise = (TextView) listViewItem.findViewById(R.id.textviewexercise);

        //getting user at position
        Record Record = records.get(position);

        textViewDate.setText(Record.getRecorddate());

        textviewweight.setText(Record.getRecordweight());

        textviewexercise.setText(Record.getRecordtodayex());

        return listViewItem;
    }
}
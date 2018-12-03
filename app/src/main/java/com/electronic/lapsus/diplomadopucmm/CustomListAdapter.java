package com.electronic.lapsus.diplomadopucmm;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<Student> {

    private final Activity context;
    private final List<Student> itemName;

    public CustomListAdapter(Activity context, List<Student> itemName) {
        super(context, R.layout.mystudentlist, itemName);
        this.context = context;
        this.itemName = itemName;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mystudentlist, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.StudentName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.StudentPic);
        final CheckBox chkStudent = (CheckBox) rowView.findViewById(R.id.checkBoxStudent);

        final Student student = itemName.get(position);
        txtTitle.setText(student.name);
        imageView.setImageResource(student.imgID);

        chkStudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               student.setAssistance(isChecked);
            }
        });

        return rowView;
    }
}

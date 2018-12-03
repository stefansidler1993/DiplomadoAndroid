package com.electronic.lapsus.diplomadopucmm;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StudentActivity extends Activity {

    ListView list;
    List<Student> studentsList = new ArrayList<>();
    CheckBox chkAssistance;

    private DatabaseHelper db;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        db.deleteAllStudent();

        db = new DatabaseHelper(this);

        db.insertStudent(new Integer(R.drawable.student0),"Stephanie Mendez", "Femenino", LocalDateTime.of(1996, 11, 16, 0, 0,0), "Contabilidad", "Los prados", false);
        db.insertStudent(new Integer(R.drawable.student1),"Stefan Sidler", "Masculino", LocalDateTime.of(1993, 10, 8, 0, 0,0), "Ingenieria de sistemas", "Los prados", false);
        db.insertStudent(new Integer(R.drawable.student2),"Elcida Lachapell", "Femenino", LocalDateTime.of(1960, 5, 5, 0, 0,0), "Administración", "Bella Vista", false);
        db.insertStudent(new Integer(R.drawable.student3),"Marci Sidler", "Masculino", LocalDateTime.of(1991, 10, 8, 0, 0,0), "Administración", "Bella Vista", false);
        db.insertStudent(new Integer(R.drawable.student4),"Chavelin Agramonte", "Femenino", LocalDateTime.of(1995, 4, 8, 0, 0,0), "Ingenieria de sistemas", "Jacobo Majluta", false);
        db.insertStudent(new Integer(R.drawable.student5),"Markus Sidler", "Masculino", LocalDateTime.of(1997, 11, 8, 0, 0,0), "Ingenieria de sistemas", "Los prados", false);
        db.insertStudent(new Integer(R.drawable.student6),"Rachel Agramonte", "Femenino", LocalDateTime.of(1996, 7, 1, 0, 0,0), "Psicologia", "Higuey", false);
        db.insertStudent(new Integer(R.drawable.student7),"Marcel Sidler", "Masculino", LocalDateTime.of(1961, 1, 30, 0, 0,0), "Ingenieria Mecánica", "In Der Reben", false);

        studentsList = db.getAllStudents();

        CustomListAdapter adapter=new CustomListAdapter(this, studentsList);
        list = (ListView)findViewById(R.id.list);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = studentsList.get(position).name;
                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();

                Student student = studentsList.get(position);

                if(student.assistance){
                    student.setAssistance(true);
                }else{
                    student.setAssistance(false);
                }

                Intent intent_details = new Intent(getApplicationContext(), StudentDetailsActivity.class);
                intent_details.putExtra("student", student);
                startActivity(intent_details);

            }
        });

    }
}

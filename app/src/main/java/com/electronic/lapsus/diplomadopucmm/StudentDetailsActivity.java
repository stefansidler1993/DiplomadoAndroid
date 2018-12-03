package com.electronic.lapsus.diplomadopucmm;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class StudentDetailsActivity extends AppCompatActivity {

    ImageView imgStudent;
    EditText etName;
    EditText etGender;
    EditText etDate;
    EditText etCareer;
    EditText etAdress;
    Button btnBack;
    Button btnLocation;
    Button btnAddDateToCalendar;
    ImageView assisted;
    ImageView notAssisted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        imgStudent = findViewById(R.id.studentPicture);
        etName = findViewById(R.id.name);
        etGender = findViewById(R.id.gender);
        etDate = findViewById(R.id.dateOfBirth);
        etCareer = findViewById(R.id.career);
        etAdress = findViewById(R.id.address);
        btnBack = findViewById(R.id.back);
        btnLocation = findViewById(R.id.location);
        btnAddDateToCalendar = findViewById(R.id.addDateToCalendar);
        assisted = findViewById(R.id.assisted);
        notAssisted = findViewById(R.id.notAssisted);

        assisted.setVisibility(View.GONE);
        notAssisted.setVisibility(View.GONE);

        Student student = (Student) getIntent().getSerializableExtra("student");
        final String name = student.getName();
        final String gender = student.getGender();
        final LocalDateTime dateOfBirth = student.getBirthday();
        final String career = student.getCareer();
        final int imageID = student.getImgID();
        final String address = student.getAddress();

        if(student.assistance){
            assisted.setVisibility(View.VISIBLE);
        }else {
            notAssisted.setVisibility(View.VISIBLE);
        }

        imgStudent.setImageResource(imageID);
        etName.setText(name);
        etGender.setText(gender);
        etDate.setText(dateOfBirth.toString());
        etCareer.setText(career);
        etAdress.setText(address);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent back_intent = new Intent(getApplicationContext(), StudentActivity.class);
                startActivity(back_intent);

            }
        });

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uri = String.format(Locale.ENGLISH, "geo:0,0?q=%s", address);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);

            }
        });

        btnAddDateToCalendar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra(CalendarContract.Events.TITLE, "Cumpleaños de " + name);
                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false);
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(dateOfBirth.getYear(), dateOfBirth.getMonth().getValue(), dateOfBirth.getDayOfMonth());
                Calendar endTime = Calendar.getInstance();
                endTime.set(dateOfBirth.getYear(), dateOfBirth.getMonth().getValue(), dateOfBirth.getDayOfMonth());
                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
                intent.putExtra(CalendarContract.Events.DESCRIPTION, "Feliz cumpleaños " + name);
                startActivity(intent);
            }
        });
    }
}

package com.electronic.lapsus.diplomadopucmm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button clear;
    Button login;
    TextView user;
    TextView password;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        db.insertUser(1, "lapsus", "lapsus123");
        db.insertUser(2, "stefan", "stefan123");

        clear = findViewById(R.id.btnClear);
        login = findViewById(R.id.btnLogin);
        user = findViewById(R.id.txtuser);
        password = findViewById(R.id.txtpassword);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user.requestFocus();
                user.setText("");
                password.setText("");
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean success = validateUserAndPass(user != null ? user.getText().toString() : "",
                        password != null ? password.getText().toString() : "");

                if(success){
                    Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
                    startActivity(intent);

                }
            }
        });

    }

    public boolean validateUserAndPass(String user, String password){

        if(!user.isEmpty() && !password.isEmpty()){

            if(db.searchUser(user, password) != null){
                return true;
            }else{
                Toast.makeText(this, "Usuario o Contraseña incorrecta", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        else{

            Toast.makeText(this, "Favor Llenar todos los campos", Toast.LENGTH_LONG).show();

            return false;
        }


    }
}

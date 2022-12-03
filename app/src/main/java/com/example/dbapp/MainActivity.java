package com.example.dbapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText txtName;
    EditText txtNIC;
    EditText txtMobile;
    EditText txtEmail;

    String name;
    String nic;
    String mobile;
    String email;

    DBHelper dbHelper;

    Button btnSubmit;
    Button btnView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtName);
        txtNIC = findViewById(R.id.txtNic);
        txtMobile = findViewById(R.id.txtMobile);
        txtEmail = findViewById(R.id.txtEmail);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnView = findViewById(R.id.btnView);

        dbHelper = new DBHelper(this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                     name = txtName.getText().toString();
                     nic = txtNIC.getText().toString();
                     mobile = txtMobile.getText().toString();
                     email = txtEmail.getText().toString();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                    if (name.isEmpty() || nic.isEmpty() || mobile.isEmpty() || email.isEmpty()){

                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                        alert.setMessage("Please Enter the All Values");
                        alert.setPositiveButton("Ok",null);
                        alert.show();
                    }else{

                        dbHelper.addNewStudent(name,nic,mobile,email);
                        AlertDialog.Builder alert2 = new AlertDialog.Builder(MainActivity.this);
                        alert2.setMessage("Successfully Saved");
                        alert2.setPositiveButton("Ok",null);
                        alert2.show();

                        txtName.setText("");
                        txtNIC.setText("");
                        txtMobile.setText("");
                        txtEmail.setText("");

                    }
                }
            }
        });


        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alt = new AlertDialog.Builder(MainActivity.this);

                List<Student> students = dbHelper.getAllStudents();

                String data = "";

                for(Student std: students){
                    String details ="Name:" + std.getName() + "\n"
                            + "NIC:" + std.getNic() + "\n"
                            + "Mobile:" + std.getMobile() + "\n"
                            + "Email:" + std.getEmail()+"\n\n";

                    data +=details;


                }

                alt.setMessage(data);
                alt.setPositiveButton("Ok",null);
                alt.show();



            }
        });

    }
}
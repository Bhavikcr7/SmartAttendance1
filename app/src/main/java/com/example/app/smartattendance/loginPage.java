package com.example.app.smartattendance;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginPage extends AppCompatActivity implements View.OnClickListener{


    TextView loginPage_login;
    EditText loginPage_email,loginPage_password;
    Button loginPage_signIn,loginPage_signUp;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        loginPage_login=(TextView)findViewById(R.id.loginPage_login);
        loginPage_email=(EditText)findViewById(R.id.loginPage_email);
        loginPage_password=(EditText)findViewById(R.id.loginPage_password);
        loginPage_signIn=(Button)findViewById(R.id.loginPage_signIn);
        loginPage_signUp=(Button)findViewById(R.id.loginPage_signUp);

        firebaseAuth=FirebaseAuth.getInstance();


        if(firebaseAuth.getCurrentUser()!=null) //already logged in
        {
            //profile
            finish();
            startActivity(new Intent(getApplicationContext(),profilePage.class));


        }



        loginPage_signIn.setOnClickListener(this);
        loginPage_signUp.setOnClickListener(this);


    }

    private void userLogin(){

        String login_email=loginPage_email.getText().toString();
        String login_password=loginPage_password.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(login_email,login_password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    //profile activity
                    finish();
                    startActivity(new Intent(getApplicationContext(),profilePage.class));


                }

            }
        });

    }


    @Override
    public void onClick(View view) {
        if(view==loginPage_signIn)
        {

            userLogin();
        }

        if(view==loginPage_signUp)
        {
            finish();
            Intent fromLoginToReg =new Intent(loginPage.this,regPage.class);
            startActivity(fromLoginToReg);



        }

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close this activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

}
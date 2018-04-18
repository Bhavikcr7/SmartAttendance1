package com.example.app.smartattendance;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class regPage extends AppCompatActivity implements View.OnClickListener {

    TextView reg;
    EditText regPage_name,regPage_email,regPage_password,regPage_division1,regPage_division2,regPage_division3;
    Button submit;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_page);

        firebaseAuth=FirebaseAuth.getInstance();

        databaseReference= FirebaseDatabase.getInstance().getReference();

        regPage_name=(EditText)findViewById(R.id.regPage_name);
        reg=(TextView)findViewById(R.id.regPage_reg);
        regPage_email=(EditText)findViewById(R.id.regPage_email);
        regPage_password=(EditText)findViewById(R.id.regPage_pass);
        regPage_division1=(EditText)findViewById(R.id.regPage_division1);
        regPage_division2=(EditText)findViewById(R.id.regPage_division2);
        regPage_division3=(EditText)findViewById(R.id.regPage_division3);
        submit=(Button)findViewById(R.id.regPage_submit);

        submit.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        final String email=regPage_email.getText().toString();
        String password=regPage_password.getText().toString();
        final String division1=regPage_division1.getText().toString();
        final String division2=regPage_division2.getText().toString();
        final String division3=regPage_division3.getText().toString();
        final String name=regPage_name.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {        Toast.makeText(regPage.this,"Success",Toast.LENGTH_SHORT).show();

                         TeacherInfo t_info=new TeacherInfo(name,email,division1,division2,division3);

                    FirebaseUser user=firebaseAuth.getCurrentUser();

                    databaseReference.child(user.getUid()).setValue(t_info);


                }
                else {Toast.makeText(regPage.this,"Failure",Toast.LENGTH_LONG).show();
                }
            }
        });


    }


}


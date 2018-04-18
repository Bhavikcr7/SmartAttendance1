package com.example.app.smartattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class divList extends AppCompatActivity {

    Button divA , divB , divC;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_div_list);

        divA=(Button)findViewById(R.id.adivi);
        divB=(Button)findViewById(R.id.bdivi);
        divC=(Button)findViewById(R.id.cdivi);

        firebaseAuth=FirebaseAuth.getInstance();

        final FirebaseUser user=firebaseAuth.getCurrentUser();

        databaseReference= FirebaseDatabase.getInstance().getReference();


        divA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.child(user.getUid()).addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        ArrayList<String> temp=new ArrayList<String>();
                        TeacherInfo t=dataSnapshot.getValue(TeacherInfo.class);

                        temp.add(t.tdivision1);
                        temp.add(t.tdivision2);
                        temp.add(t.tdivision3);

                        if(temp.contains("A"))
                        {
                            Intent intent =new Intent(divList.this,A_Division.class);
                            startActivity(intent);

                        }
                        else
                        {
                            Toast.makeText(divList.this,"You have not selected this div", Toast.LENGTH_LONG).show();
                        }
                        // profilePage_name.setText(t.tname);
                       // profilePage_email.setText(t.temail);
                        //profilePage_division.setText(t.tdivision);
                        //div.append(t.tdivision);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });
            }
        });


        divB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.child(user.getUid()).addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        ArrayList<String> temp=new ArrayList<String>();
                        TeacherInfo t=dataSnapshot.getValue(TeacherInfo.class);

                        temp.add(t.tdivision1);
                        temp.add(t.tdivision2);
                        temp.add(t.tdivision3);

                        if(temp.contains("B"))
                        {
                            Intent intent =new Intent(divList.this,B_Division.class);
                            startActivity(intent);

                        }
                        else
                        {
                            Toast.makeText(divList.this,"You have not selected this div", Toast.LENGTH_LONG).show();
                        }
                        // profilePage_name.setText(t.tname);
                        // profilePage_email.setText(t.temail);
                        //profilePage_division.setText(t.tdivision);
                        //div.append(t.tdivision);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });
            }
        });

        divC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.child(user.getUid()).addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        ArrayList<String> temp=new ArrayList<String>();
                        TeacherInfo t=dataSnapshot.getValue(TeacherInfo.class);

                        temp.add(t.tdivision1);
                        temp.add(t.tdivision2);
                        temp.add(t.tdivision3);

                        if(temp.contains("C"))
                        {
                            Intent intent =new Intent(divList.this,C_Division.class);
                            startActivity(intent);

                        }
                        else
                        {
                            Toast.makeText(divList.this,"You have not selected this div", Toast.LENGTH_LONG).show();
                        }
                        // profilePage_name.setText(t.tname);
                        // profilePage_email.setText(t.temail);
                        //profilePage_division.setText(t.tdivision);
                        //div.append(t.tdivision);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });
            }
        });





    }
}

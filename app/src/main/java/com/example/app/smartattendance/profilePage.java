package com.example.app.smartattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class profilePage extends AppCompatActivity  implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
   TextView profilePage_name,profilePage_email;//profilePage_division;
   Button profilePage_takeAttendance,profilePage_logout;
   StringBuffer div=new  StringBuffer(16) ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        firebaseAuth=FirebaseAuth.getInstance();

        FirebaseUser user=firebaseAuth.getCurrentUser();

        databaseReference= FirebaseDatabase.getInstance().getReference();

        profilePage_name=(TextView)findViewById(R.id.profilePage_name);
        profilePage_email=(TextView)findViewById(R.id.profilePage_email);
        //profilePage_division=(TextView)findViewById(R.id.profilePage_division);

        profilePage_takeAttendance=(Button)findViewById(R.id.profilePage_takeAttendance);
        profilePage_logout=(Button)findViewById(R.id.profilePage_logOut);


        profilePage_takeAttendance.setOnClickListener(this);
        profilePage_logout.setOnClickListener(this);


        databaseReference.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                TeacherInfo t=dataSnapshot.getValue(TeacherInfo.class);
                profilePage_name.setText(t.tname);
                profilePage_email.setText(t.temail);
                //profilePage_division.setText(t.tdivision);
                //div.append(t.tdivision);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });



    }

    @Override
    public void onClick(View view) {


        if(view==profilePage_logout)
        {
            finish();
            firebaseAuth.signOut();

            Intent i= new Intent(profilePage.this,loginPage.class);
            startActivity(i);



        }
        if(view==profilePage_takeAttendance)
        {

            Intent i=new Intent (profilePage.this,divList.class);
            startActivity(i);


            //if("A".equals(div.toString()))
            //{Intent I= new Intent(profilePage.this , A_Division.class);
            //startActivity(I);
            //}

            //FirebaseUser user=firebaseAuth.getCurrentUser();

            //Map<String, String> userData = new HashMap<String, String>();

            //userData.put("Product_Name", "productName");
            //userData.put("Holding_Stock", "holdingStock");
            //userData.put("Primary_Stock", "primaryStock");
            //databaseReference.child(user.getUid()).child("bhavik").setValue(userData);
        }

    }
}

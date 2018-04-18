package com.example.app.smartattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class B_Division extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    Button submitB,viewB,backB;
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b__division);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        //final int total_day=0;




        submitB = (Button) findViewById(R.id.B_submitButton);
        viewB=(Button)findViewById(R.id.viewAttendanceB);
        backB=(Button)findViewById(R.id.backToProfileB);
        // myResultText=(TextView)findViewById(R.id.myResultText);


        //generate list
        final ArrayList<String> listB = new ArrayList<String>();
        listB.add("1 Balram");
        listB.add("2 Kumar");
        listB.add("3 Pratyush");
        listB.add("4 Rachit");
        listB.add("5 Piyush");

        final CustomAdapterList adapter = new CustomAdapterList(listB, this);

        ListView lViewB = (ListView) findViewById(R.id.myListViewB);
        lViewB.setAdapter(adapter);

        submitB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
                String time = " Time : " + mdformat.format(calendar.getTime());

                String date = new SimpleDateFormat("yyyy MM dd", Locale.getDefault()).format(new Date());
                FirebaseUser user = firebaseAuth.getCurrentUser();


                databaseReference.child(user.getUid()).child("Attendance").child("B Division").child(date).child(time).setValue(adapter.map);


                Toast.makeText(B_Division.this, "Successfully Added", Toast.LENGTH_LONG).show();


            }
        });


        viewB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Integer iInteger = new Integer(days);
                //StringBuffer sb =new StringBuffer(1000);
                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).child("Attendance").child("B Division").addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int count = 0;
                        StringBuffer sb =new StringBuffer(1000);
                        ArrayList<String> finalLst= new ArrayList<String>();
                        HashMap<String, Integer> dataToSend = new  HashMap<String, Integer>();
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            for (DataSnapshot child1 : child.getChildren()) {
                                for (DataSnapshot child2 : child1.getChildren())
                                {
                                    String s1=child2.getKey();

                                //Toast.makeText(A_Division.this,s1, Toast.LENGTH_LONG).show();
                                finalLst.add(s1);
                                }
                                count++;
                            }


                        }
                        //String natu=String.valueOf(finalLst.size());
                        //Toast.makeText(A_Division.this,natu, Toast.LENGTH_LONG).show();
                        int noOfTimes=0;
                        for(int j=0;j<listB.size();j++)
                        {
                            for(int k=0;k<finalLst.size();k++)
                            {
                                if(listB.get(j).equals(finalLst.get(k)))
                                {

                                    noOfTimes++;
                                    dataToSend.put(listB.get(j),new Integer(noOfTimes));

                                }

                            }
                            noOfTimes=0;
                        }

                        Intent divToFinaDisplay = new Intent(B_Division.this, finalDisplay.class);
                        divToFinaDisplay.putExtra("finalData",dataToSend);
                        divToFinaDisplay.putExtra("no of days",count);
                        divToFinaDisplay.putExtra("original List",listB);
                        divToFinaDisplay.putExtra("divName","B");
                        startActivity(divToFinaDisplay);


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {


                    }
                });


            }
        });


        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(B_Division.this,profilePage.class);
                startActivity(i);
            }
        });





    }
}

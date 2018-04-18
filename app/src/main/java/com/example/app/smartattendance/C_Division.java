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

public class C_Division extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    Button submitC,viewC,backC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c__division);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        //final int total_day=0;




        submitC = (Button) findViewById(R.id.C_submitButton);
        viewC=(Button)findViewById(R.id.viewAttendanceC);
        backC=(Button)findViewById(R.id.backToProfileC);
        // myResultText=(TextView)findViewById(R.id.myResultText);


        //generate list
        final ArrayList<String> listC = new ArrayList<String>();
        listC.add("1 Rohit");
        listC.add("2 Munaf");
        listC.add("3 Dishank");
        listC.add("4 Sharad");
        listC.add("5 Priyank");


        final CustomAdapterList adapter = new CustomAdapterList(listC, this);

        ListView lViewC = (ListView) findViewById(R.id.myListViewC);
        lViewC.setAdapter(adapter);

        submitC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = new SimpleDateFormat("yyyy MM dd", Locale.getDefault()).format(new Date());
                FirebaseUser user = firebaseAuth.getCurrentUser();
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
                String time = " Time : " + mdformat.format(calendar.getTime());


                databaseReference.child(user.getUid()).child("Attendance").child("C Division").child(date).child(time).setValue(adapter.map);


                Toast.makeText(C_Division.this, "Successfully Added", Toast.LENGTH_LONG).show();


            }
        });

        viewC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Integer iInteger = new Integer(days);
                //StringBuffer sb =new StringBuffer(1000);
                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).child("Attendance").child("C Division").addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int count = 0;
                        StringBuffer sb =new StringBuffer(1000);
                        ArrayList<String> finalLst= new ArrayList<String>();
                        HashMap<String, Integer> dataToSend = new  HashMap<String, Integer>();
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            for (DataSnapshot child1 : child.getChildren()) {

                                for(DataSnapshot child2 : child1.getChildren())
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
                        for(int j=0;j<listC.size();j++)
                        {
                            for(int k=0;k<finalLst.size();k++)
                            {
                                if(listC.get(j).equals(finalLst.get(k)))
                                {

                                    noOfTimes++;
                                    dataToSend.put(listC.get(j),new Integer(noOfTimes));

                                }

                            }
                            noOfTimes=0;
                        }

                        Intent divToFinaDisplay = new Intent(C_Division.this, finalDisplay.class);
                        divToFinaDisplay.putExtra("finalData",dataToSend);
                        divToFinaDisplay.putExtra("no of days",count);
                        divToFinaDisplay.putExtra("original List",listC);
                        divToFinaDisplay.putExtra("divName","C");
                        startActivity(divToFinaDisplay);


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {


                    }
                });


            }
        });




        backC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(C_Division.this,profilePage.class);
                startActivity(i);
            }
        });





    }
}

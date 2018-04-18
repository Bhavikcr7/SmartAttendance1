package com.example.app.smartattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class A_Division extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    Button submit,view,back;
//    TextView myResultText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a__division);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference();

         //final int total_day=0;




        submit = (Button) findViewById(R.id.A_submitButton);
        view=(Button)findViewById(R.id.viewAttendance);
        back=(Button)findViewById(R.id.backToProfile);
       // myResultText=(TextView)findViewById(R.id.myResultText);


        //generate list
        final ArrayList<String> list = new ArrayList<String>();
        list.add("1 Bhavik");
        list.add("2 Kunal");
        list.add("3 Amit");
        list.add("4 Adarsh");
        list.add("5 Natu");

        //initilize the res




        //instantiate custom adapter
        final CustomAdapterList adapter = new CustomAdapterList(list, this);

        ListView lView = (ListView) findViewById(R.id.myListView);
        lView.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String date = new SimpleDateFormat("yyyy MM dd", Locale.getDefault()).format(new Date());

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
                String time = " Time : " + mdformat.format(calendar.getTime());

                FirebaseUser user = firebaseAuth.getCurrentUser();


                databaseReference.child(user.getUid()).child("Attendance").child("A Division").child(date).child(time).setValue(adapter.map);


                Toast.makeText(A_Division.this, "Successfully Added", Toast.LENGTH_LONG).show();


            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               // Integer iInteger = new Integer(days);
                //StringBuffer sb =new StringBuffer(1000);
                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).child("Attendance").child("A Division").addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int count = 0;
                        StringBuffer sb =new StringBuffer(1000);
                        ArrayList<String> finalLst= new ArrayList<String>();
                        HashMap<String, Integer> dataToSend = new  HashMap<String, Integer>();
                        for (DataSnapshot child : dataSnapshot.getChildren())
                        {
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
                        for(int j=0;j<list.size();j++)
                        {
                            for(int k=0;k<finalLst.size();k++)
                            {
                                if(list.get(j).equals(finalLst.get(k)))
                                {

                                    noOfTimes++;
                                    dataToSend.put(list.get(j),new Integer(noOfTimes));

                                }

                            }
                            noOfTimes=0;
                        }

                        Intent divToFinaDisplay = new Intent(A_Division.this, finalDisplay.class);
                        divToFinaDisplay.putExtra("finalData",dataToSend);
                        divToFinaDisplay.putExtra("no of days",count);
                        divToFinaDisplay.putExtra("original List",list);
                        divToFinaDisplay.putExtra("divName","A");

                        startActivity(divToFinaDisplay);


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {


                    }
                });


            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(A_Division.this,profilePage.class);
                startActivity(i);
            }
        });



    }
}
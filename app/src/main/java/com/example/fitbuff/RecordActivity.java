package com.example.fitbuff;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity
{


    //initialize
    EditText editTextDate, editTextWeight, editTextExercise;

    Button buttonAddRecord;
    ListView listViewRecords;


    //a list to store all the Record from firebase database
    List<Record> records;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

         // method for find ids of views
        findViews();

        // to maintian click listner of views
        initListner();
           }


    private void findViews() {
        //getRefrance for user table
        databaseReference = FirebaseDatabase.getInstance().getReference("records");

        editTextDate = (EditText) findViewById(R.id.editTextDate);
        editTextWeight = (EditText) findViewById(R.id.editTextWeight);
        editTextExercise = (EditText) findViewById(R.id.editTextExercise);
        listViewRecords = (ListView) findViewById(R.id.listViewRecords);
        buttonAddRecord = (Button) findViewById(R.id.buttonAddRecord);
        //list for store objects of user
        records = new ArrayList<>();
    }
    private void initListner() {
        //adding an onclicklistener to button
        buttonAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRecord();
            }
        });

        // list item click listener
        listViewRecords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Record Record = records.get(i);
                CallUpdateAndDeleteDialog(Record.getRecordid(), Record.getRecorddate(), Record.getRecordweight(), Record.getRecordtodayex());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                records.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Record Record = postSnapshot.getValue(com.example.fitbuff.Record.class);
                    records.add(Record);
                }

                RecordList RecordAdapter = new RecordList(RecordActivity.this, records);
                //attaching adapter to the listview
                listViewRecords.setAdapter(RecordAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void CallUpdateAndDeleteDialog(final String recordid, String date, final String weight, String yourexercise) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);
        //Access Dialog views
        final EditText updateTextdate = (EditText) dialogView.findViewById(R.id.updateTextdate);
        final EditText updateTextweight = (EditText) dialogView.findViewById(R.id.updateTextweight);
        final EditText updateTexttodayex = (EditText) dialogView.findViewById(R.id.updateTexttodayex);
        updateTextdate.setText(date);
        updateTextweight.setText(weight);
        updateTexttodayex.setText(yourexercise);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateRecord);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteRecord);

        dialogBuilder.setTitle(date);
        final AlertDialog b = dialogBuilder.create();
        b.show();

       // Click listener for Update data
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = updateTextdate.getText().toString().trim();
                String weight = updateTextweight.getText().toString().trim();
                String todayexercise = updateTexttodayex.getText().toString().trim();
                //checking if the value is provided or not Here, you can Add More Validation as you required

                if (!TextUtils.isEmpty(date)) {
                    if (!TextUtils.isEmpty(weight)) {
                        if (!TextUtils.isEmpty(todayexercise)) {
                            //Method for update data
                            updateRecord(recordid, date, weight, todayexercise);
                            b.dismiss();
                        }
                    }
                }

            }
        });

        // Click listener for Delete data
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Method for delete data
                deleteRecord(recordid);
                b.dismiss();
            }
        });
    }

    private boolean updateRecord(String id, String date, String weight, String todayexercise) {
        //getting the specified Record reference
        DatabaseReference UpdateReference = FirebaseDatabase.getInstance().getReference("records").child(id);
        Record Record = new Record(id, date, weight, todayexercise);
        //update  Record  to firebase
        UpdateReference.setValue(Record);
        Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteRecord(String id) {
        //getting the specified Record reference
        DatabaseReference DeleteReference = FirebaseDatabase.getInstance().getReference("records").child(id);
        //removing Record
        DeleteReference.removeValue();
        Toast.makeText(getApplicationContext(), "Record Deleted", Toast.LENGTH_LONG).show();
        return true;
    }


    private void addRecord() {


        //getting the values to save
        String date = editTextDate.getText().toString().trim();
        String weight = editTextWeight.getText().toString().trim();
        String todayexercise = editTextExercise.getText().toString().trim();


        //checking if the value is provided or not Here, you can Add More Validation as you required

        if (!TextUtils.isEmpty(date)) {
            if (!TextUtils.isEmpty(weight)) {
                if (!TextUtils.isEmpty(todayexercise)) {

                    //it will create a unique id and we will use it as the Primary Key for our Record
                    String id = databaseReference.push().getKey();
                    //creating an Record Object
                    Record Record = new Record(id, date, weight, todayexercise);
                    //Saving the Record
                    databaseReference.child(id).setValue(Record);

                    editTextDate.setText("");
                    editTextExercise.setText("");
                    editTextWeight.setText("");
                    Toast.makeText(this, "Record added", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Please enter an exercise/Please enter None", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Please enter a Weight", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Please enter a Date", Toast.LENGTH_LONG).show();
        }
    }
}
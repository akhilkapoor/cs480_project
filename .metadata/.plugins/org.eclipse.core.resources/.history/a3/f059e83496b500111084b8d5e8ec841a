package com.rec;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ScheduleAppActivity extends Activity {
	
	private EditText mNameField;
	private Button mNameButton;
	private Button mScheduleButton;
	private Button mOpenButton;
	private Intent mIntent;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mNameField = (EditText) findViewById(R.id.nameField);
        mIntent = new Intent(this, weekSchedule.class);
        
        //Search for schedule of a specific Employee
        mNameButton = (Button) findViewById(R.id.submitName);
        mNameButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		startIntent(mNameField.getText().toString());
        	}
        });
        
        //Look at the schedule for the whole week
        mScheduleButton = (Button) findViewById(R.id.viewSchedule);
        mScheduleButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		startIntent("ViewWeekSchedule");
        	}
        });
        
      //Look at the open shifts
        mOpenButton = (Button) findViewById(R.id.viewOpen);
        mOpenButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		startIntent("ViewOpenShifts");
        	}
        });
    }
    
    private void startIntent(String query) {
    	mIntent.putExtra("query", query);
    	if (!query.contains("ViewWeekSchedule") && !query.contains("ViewOpenShifts")) {
    		mIntent.putExtra("EmployeeSearch", true);
    	}
    	else {
    		mIntent.putExtra("EmployeeSearch", false);
    	}
    	
    	startActivity(mIntent);
    }
}
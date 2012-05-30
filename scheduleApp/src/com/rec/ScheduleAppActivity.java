package com.rec;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ScheduleAppActivity extends Activity {
	
	private EditText nameField;
	private Button nameButton;
	private Button scheduleButton;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        nameField = (EditText) findViewById(R.id.nameField);
        
        //Search for schedule of a specific Employee
        nameButton = (Button) findViewById(R.id.submitName);
        nameButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		Intent empSched = new Intent(v.getContext(), employeeSchedule.class);
        		empSched.putExtra("employee", nameField.getText().toString());
        		startActivity(empSched);
        	}
        });
        
        //Look at the schedule for the whole week
        scheduleButton = (Button) findViewById(R.id.viewSchedule);
        scheduleButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		Intent weekSched = new Intent(v.getContext(), weekSchedule.class);
        		startActivity(weekSched);
        	}
        });
    }
}
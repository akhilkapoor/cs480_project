package com.todo.client2;

import com.todo.client2.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TodoClientActivity extends Activity {
	
	private Button viewButton;
	private Button newTodoButton;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //View To Do List
        viewButton = (Button) findViewById(R.id.viewButton);
        viewButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent ListViewActivity = new Intent(v.getContext(), ListTodoAct.class);
				startActivity(ListViewActivity);
			}
		});
        
        //Create a new To Do
        newTodoButton = (Button) findViewById(R.id.createButton);
        newTodoButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent TodoActivity = new Intent(v.getContext(), NewTodoActivity.class);
				startActivity(TodoActivity);
			}
		});
    }

	@Override
	public void onBackPressed() {
		// Turning off Back press
		Toast.makeText(this, "Press back to exit", 2).show();
		finish();
	}
}
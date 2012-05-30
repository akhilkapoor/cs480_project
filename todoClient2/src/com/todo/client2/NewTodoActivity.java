package com.todo.client2;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.client2.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class NewTodoActivity extends Activity{
	
	private Button submitButton;
	private EditText titleText;
	private EditText notesText;
	private DatePicker datePicker;
	private TimePicker timePicker;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newtodo);
        
        titleText = (EditText) findViewById(R.id.titleField);
        notesText = (EditText) findViewById(R.id.noteField);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        
        //Submit button
        submitButton = (Button) findViewById(R.id.submit);
        submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if ( !titleText.getText().toString().isEmpty() ) {
					new PostJson().execute();
				}
			}
		});
    }
    
	private class PostJson extends AsyncTask<Void, Void, String> {

		private ObjectMapper mapper;
		private ProgressDialog dialog;
		
		protected void onPreExecute() {
			dialog = ProgressDialog.show(NewTodoActivity.this, "Notice", "Creating. Please wait...", true);			
		}

		protected String doInBackground(Void... params) {

			String input = "";
			mapper = new ObjectMapper();
			Todo todo = new Todo();
			todo.setTitle(titleText.getText().toString());
			todo.setNotes(notesText.getText().toString());

			Date date = new Date();
			date.setDate(datePicker.getDayOfMonth());
			date.setMonth(datePicker.getMonth());
			date.setYear(datePicker.getYear()-1900);
			date.setHours(timePicker.getCurrentHour());
			date.setMinutes(timePicker.getCurrentMinute());
			date.setSeconds(0);
			todo.setDueDate(date.toString());
			
			try {
				
				input = mapper.writeValueAsString(todo);
				String sURL = "http://www.cs480-todo.appspot.com/rest/todos/post";
    			URL rURL = new URL(sURL);
    			HttpURLConnection conn = (HttpURLConnection)rURL.openConnection();
    			conn.setRequestMethod("POST");
    			conn.setRequestProperty("Content-Type", "application/json");
    			conn.setRequestProperty("Accept", "application/json");
    			conn.setRequestProperty("Content-Length", Integer.toString(input.length()));
    			conn.setDoInput(true);
    			conn.setDoOutput(true);
    			conn.getOutputStream().write(input.getBytes());
    			conn.getOutputStream().flush();
    			conn.connect();
    			//input = 
    					conn.getResponseMessage();
				conn.disconnect();
				//input = ""+todo.getDueDate();

			} catch (JsonGenerationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JsonMappingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
						
			return input;
		}
		
		protected void onPostExecute(String word) {
		
			titleText.setText("");
			notesText.setText("");
			titleText.requestFocus();
			
			if (dialog.isShowing()) {
				dialog.cancel();
			}
			
			Intent myIntent = new Intent(NewTodoActivity.this, TodoClientActivity.class);
			startActivity(myIntent);
			finish();
		}

	}
}

package com.rec;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;

public class employeeSchedule extends ListActivity{
	
	private String empName = "";
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	Bundle extras = getIntent().getExtras();
    	empName = extras.getString("employee");
    }
    
    private class requestSchedule extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... arg0) {
			//Enter Magic Here
			return null;
		}
    	
		protected void onPostExecute(String data) {
			//Enter Magic Here
		}
    }
}

package com.rec;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;

public class weekSchedule extends ListActivity{

	@Override
    public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
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

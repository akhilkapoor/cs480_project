package com.rec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.ExpandableListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

public class employeeSchedule extends ExpandableListActivity{
	
	private String empName = "";
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	Bundle extras = getIntent().getExtras();
    	empName = extras.getString("employee");
    }
    
private class requestSchedule extends AsyncTask<Void, Void, String> {
		
		private ProgressDialog dialog;
		private ObjectMapper mapper;
		
		protected void onPreExecute() {
			dialog = ProgressDialog.show(employeeSchedule.this, "", "Loading Schedule.\n Please wait...", true);			
		}

		@Override
		protected String doInBackground(Void... arg0) {
	    	String data = "";
			try {
				String sURL = "Replace This!";
    			URL rURL = new URL(sURL);
    			HttpURLConnection conn = (HttpURLConnection)rURL.openConnection();
    			conn.setRequestMethod("GET");
    			conn.setDoInput(true);
    			conn.connect();
    			
    			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    			String line = "";
    			while((line = br.readLine()) != null)
    			{
    				data += line + "\n";
    			}
    			
    			br.close();
    			conn.disconnect();
    			
    			return data;
    			
    		} catch (MalformedURLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	return data;
		}
		
		protected void onPostExecute(String data) {
			//Enter Magic Here
			if (dialog.isShowing()) {
				dialog.cancel();
			}
			
			mapper = new ObjectMapper();
			ArrayList <String> list = new ArrayList<String>();
			final ArrayList <ShiftStruct> shifts = new ArrayList<ShiftStruct>();
			
			try {
				JsonNode root = this.readJson(data);
				
				//Enter More Magic Here!
				//Parse JSON
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		public JsonNode readJson(String json) throws Exception {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readValue(json, JsonNode.class);
			JsonParser jp = mapper.getJsonFactory().createJsonParser(json);
			rootNode = mapper.readTree(jp);
			return rootNode;
	    }
    }
}
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
import android.widget.ExpandableListView;

public class weekSchedule extends ExpandableListActivity{
	
	private String mQuery;
	private Boolean mEmployeeSearch;
	
	//Run On start of Activity
	@Override
    public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schedulelayout);
		
		
		Bundle extras = getIntent().getExtras();
		//mName = new EditText(this);
		//mName.setText(extras.getString("query"));
		mQuery = extras.getString("query");
		mEmployeeSearch = extras.getBoolean("EmployeeSearch");
		
		new requestSchedule().execute();
	}
	
	//Perform a request of the schedule for the week
	private class requestSchedule extends AsyncTask<Void, Void, String> {
		
		private String query;
		private Boolean employeeSearch;
		private ProgressDialog dialog;
		private ObjectMapper mapper;
		
		//Show a loading screen
		protected void onPreExecute() {
			query = mQuery;
			employeeSearch = mEmployeeSearch;
			dialog = ProgressDialog.show(weekSchedule.this, "", "Loading Schedule.\n Please wait...", true);			
		}

		//Perform request and receive JSON
		@Override
		protected String doInBackground(Void... arg0) {
	    	String data = "";
			try {
				String sURL = "";
				if (employeeSearch) {
					query = query.trim();
					query = query.replace(" ", "%20");
					sURL = "http://zyntangi.appspot.com/rest/shift/name/"+query;
				}
				else if (query.contains("ViewWeekSchedule")) {
					sURL = "http://zyntangi.appspot.com/rest/shift/all";
				}
				else if (query.contains("ViewOpenShifts")) {
					sURL = "http://zyntangi.appspot.com/rest/shift/open";
				}
				
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
		
		//Parse JSON and update the UI
		protected void onPostExecute(String data) {
			if (dialog.isShowing()) {
				dialog.cancel();
			}
			
			mapper = new ObjectMapper();
			final ArrayList <Shift> shifts = new ArrayList<Shift>();
			
			try {
				JsonNode root = this.readJson(data);
				
				if (root.path("shift").isArray()) {
					for (JsonNode node: root.path("shift")) {
						Shift t = mapper.readValue(node.traverse(), Shift.class);						
						shifts.add(t);
					}	
				}
				else {
					Shift t = mapper.readValue(root.path("todo").traverse(), Shift.class);
					shifts.add(t);
				}
				
				ArrayList<String> shiftTimes = new ArrayList<String>();
				ArrayList<ArrayList<Shift>> shiftData = new ArrayList<ArrayList<Shift>>();
				
				ExpandableListView expList;
				expList = getExpandableListView();
				ExpoAdapter adapter = new ExpoAdapter(getApplicationContext(), shiftTimes, shiftData);
				
				for (Shift s : shifts) {
					String time = s.getDayOfWeek() + " " + s.getStartTime() + "-" + s.getEndTime();
					adapter.addShift(time);
					adapter.addShiftData(time, s);
				}
				expList.setAdapter(adapter);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		//Read JSON using Jackson
		public JsonNode readJson(String json) throws Exception {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readValue(json, JsonNode.class);
			JsonParser jp = mapper.getJsonFactory().createJsonParser(json);
			rootNode = mapper.readTree(jp);
			return rootNode;
	    }
    }
}
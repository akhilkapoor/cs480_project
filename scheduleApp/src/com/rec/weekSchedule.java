package com.rec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.ExpandableListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class weekSchedule extends ExpandableListActivity{
	
	@Override
    public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		new requestSchedule().execute();
	}
	
	private class requestSchedule extends AsyncTask<Void, Void, String> {
		
		private ProgressDialog dialog;
		private ObjectMapper mapper;
		
		protected void onPreExecute() {
			dialog = ProgressDialog.show(weekSchedule.this, "", "Loading Schedule.\n Please wait...", true);			
		}

		@Override
		protected String doInBackground(Void... arg0) {
	    	String data = "";
			try {
				String sURL = "http://zyntango.appspot.com/test/testapijson";
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
				
				List<String> days = new ArrayList<String>();
				//Enter More Magic here
				//Parse JSON, fill expandable list
				
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
	
	public class MyExpandableListAdapter extends BaseExpandableListAdapter {
        private String[] groups = new String[1];
        private String[][] children = new String[1][1];
        
        public MyExpandableListAdapter(String[] group, String[][] children) {
        	this.groups = group;
        	this.children = children;
        }

        public Object getChild(int groupPosition, int childPosition) {
            return children[groupPosition][childPosition];
        }

        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        public int getChildrenCount(int groupPosition) {
            return children[groupPosition].length;
        }

        public TextView getGenericView() {
            // Layout parameters for the ExpandableListView
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 64);

            TextView textView = new TextView(weekSchedule.this);
            textView.setLayoutParams(lp);
            // Center the text vertically
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            // Set the text starting position
            textView.setPadding(36, 0, 0, 0);
            return textView;
        }

        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                View convertView, ViewGroup parent) {
            TextView textView = getGenericView();
            textView.setText(getChild(groupPosition, childPosition).toString());
            return textView;
        }

        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        public int getGroupCount() {
            return groups.length;
        }

        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                ViewGroup parent) {
            TextView textView = getGenericView();
            textView.setText(getGroup(groupPosition).toString());
            return textView;
        }

        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        public boolean hasStableIds() {
            return true;
        }
    }
}
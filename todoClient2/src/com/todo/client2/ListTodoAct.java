package com.todo.client2;

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

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;

public class ListTodoAct extends ListActivity{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new DownloadJsonString().execute();	
	}
	
	private class DownloadJsonString extends AsyncTask<Void, Void, String> {
    	
		private ProgressDialog dialog;
		private ObjectMapper mapper;

		protected void onPreExecute() {
			dialog = ProgressDialog.show(ListTodoAct.this, "", "Loading. Please wait...", true);			
		}
		
		@Override
		protected String doInBackground(Void... arg0) {
	    	String data = "";
			try {
				String sURL = "http://www.cs480-todo.appspot.com/rest/todos/json";
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

			if (dialog.isShowing()) {
				dialog.cancel();
			}
			
			mapper = new ObjectMapper();
			ArrayList <String> list = new ArrayList<String>();
			final ArrayList <Todo> todos = new ArrayList<Todo>();
			
			try {
				JsonNode root = this.readJson(data);
				if (root.path("todo").isArray()) {
					for (JsonNode node: root.path("todo")) {
						Todo t = mapper.readValue(node.traverse(), Todo.class);
						todos.add(t);
						list.add(t.getTitle());
					}					
				} else {
					Todo t = mapper.readValue(root.path("todo").traverse(), Todo.class);
					todos.add(t);
					list.add(t.getTitle());
				}
				
				String [] res = {};
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListTodoAct.this,android.R.layout.simple_list_item_1, list.toArray(res));
				setListAdapter(adapter);
				
				getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
					@Override
					public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int position, long arg3) {
						final Todo todo = todos.get(position);
						AlertDialog.Builder builder = new AlertDialog.Builder(ListTodoAct.this);
						builder.setMessage(todo.toString())
						       .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
						           public void onClick(DialogInterface dialog, int id) {
						        	   Intent editTodoActivity = new Intent(ListTodoAct.this, EditTodoActivity.class);
						        	   Bundle b = new Bundle();
						        	   b.putSerializable("todo", todo);
						        	   editTodoActivity.putExtras(b);
						        	   dialog.cancel();
						        	   startActivity(editTodoActivity);
						        	   finish();
						           }
						       })
						       .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
						           public void onClick(DialogInterface dialog, int id) {
						        	   dialog.cancel();
						        	   new DeleteEvent().execute(todo.getId());
						           }
						       });
						AlertDialog alert = builder.create();
						alert.show();
						
						return false;
					}
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
	
	private class DeleteEvent extends AsyncTask<Long, Void, String> {

		private ProgressDialog dialog;

		protected void onPreExecute() {
			dialog = ProgressDialog.show(ListTodoAct.this, "", "Deleting. Please wait...", true);			
		}

		protected String doInBackground(Long ... param) {
			
			String input = "";

			for(Long id: param) {

				try {
					String sURL = "http://www.cs480-todo.appspot.com/rest/todos/delete/" + id;
	    			URL rURL = new URL(sURL);
	    			HttpURLConnection conn = (HttpURLConnection)rURL.openConnection();
	    			conn.setDoInput(true);
	    			conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded" );
	    			conn.setRequestProperty("Accept", "application/json");
	    			conn.setRequestMethod("DELETE");
	    			conn.connect();
	    			input = conn.getResponseMessage();
	    			conn.disconnect();

				} catch (MalformedURLException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
			}
			return input;
		}

		protected void onPostExecute(String result) {
			
			if (dialog.isShowing()) {
				dialog.cancel();
			}

			Intent todoClientActivity = new Intent(ListTodoAct.this, TodoClientActivity.class);
			startActivity(todoClientActivity);
			finish();
		}		
	}
}

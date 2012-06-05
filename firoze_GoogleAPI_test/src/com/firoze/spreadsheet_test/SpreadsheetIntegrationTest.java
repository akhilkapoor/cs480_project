package com.firoze.spreadsheet_test;
import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.*;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class SpreadsheetIntegrationTest {
	
	private SpreadsheetIntegrationTest(){}
	
	public static List<Shift> getData(){
		long id = 1000;
		List<Shift> shifts = new ArrayList<Shift>();
	    try {
	    	SpreadsheetService service = new SpreadsheetService("MySpreadsheetIntegration-v1");
	    	service.setUserCredentials("recdrexel@gmail.com", "cs480rec");
	    	
	    	URL FEED_URL = new URL("https://spreadsheets.google.com/feeds/cells/0AitD7pxhITV1dGVBRnFzLWNTYlkxd1h5MkhyeElab3c/od6/private/full");
			CellFeed cellFeed = service.getFeed(FEED_URL, CellFeed.class);
			List<CellEntry> cells = cellFeed.getEntries();
			HashMap<Integer, String> locations;
			int i = 0;
			CellEntry cell = cells.get(i);
			String value = cell.getCell().getValue();
			int k = cell.getCell().getCol();
			while(!(value.equals("end of file"))) {
				if (Helper.isDay(value)){
					Shift s;
					locations = new HashMap<Integer, String>();
					// day
					String day = cell.getCell().getValue();
					cell = cells.get(i++);
					k = cell.getCell().getCol();
					
					// locations
					while (k!=1){
						locations.put(k, cell.getCell().getValue());
						cell = cells.get(i++);
						k = cell.getCell().getCol();
					}
					
					while(true){
						// shift time
						String[] times = cell.getCell().getValue().split(" ");
						cell = cells.get(i++);
						k = cell.getCell().getCol();
						// names
						while(k!=1){
							String name = cell.getCell().getValue();
							s = new Shift(day, times[0], times[1], locations.get(k), name, "Regular Shift", "No Notes");
								s.setId(id++);
							shifts.add(s);
							cell = cells.get(i++);
							k = cell.getCell().getCol();
						}
						value = cell.getCell().getValue();
						if (Helper.isDay(value)){i--;break;}
						else if (value.equals("end of file")){i--;break;}
					}
				}
				cell = cells.get(i++);
				value = cell.getCell().getValue();
			}
	    }
	    catch(AuthenticationException e) {
	    	e.printStackTrace();
	    }
	    catch(MalformedURLException e) {
	    	e.printStackTrace();
	    }
	    catch(ServiceException e) {
	    	e.printStackTrace();
	    }
	    catch(IOException e) {
	    	e.printStackTrace();
	    }
	    catch(NullPointerException n) {
	    	n.printStackTrace();
	    }
	    return shifts;
	}
}

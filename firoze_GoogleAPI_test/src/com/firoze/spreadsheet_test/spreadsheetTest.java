package com.firoze.spreadsheet_test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gdata.client.authn.oauth.OAuthException;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

@Path("test")
public class spreadsheetTest {
	
	@GET
	@Path("/testapi")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shift> getSpreadsheetData() 
			throws AuthenticationException, MalformedURLException, IOException, ServiceException, OAuthException
	{
		List<Shift> shifts = SpreadsheetIntegrationTest.getData();
		return shifts;
	}
	
	@GET
	@Path("/testapijson")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shift> getShiftListJSON(){
		Shift a = new Shift("Monday","1AM","2AM","PSIB","Akhil","Taken","No Notes");
			a.setId((long) 1001);
		Shift b = new Shift("Monday","2AM","3AM","Library","Firoze","Taken","No Notes");
			b.setId((long) 1002);
		Shift c = new Shift("Monday","3AM","4AM","Bossone","Varoon","Taken","No Notes");
			c.setId((long) 1003);
		Shift d = new Shift("Monday","4AM","5AM","CLC","Donny","Taken","No Notes");
			d.setId((long) 1004);
		List<Shift> shifts = new ArrayList<Shift>();
		shifts.add(a);
		shifts.add(b);
		shifts.add(c);
		shifts.add(d);
		return shifts;
	}
	
	@GET
	@Path("/testapixml")
	@Produces(MediaType.APPLICATION_XML)
	public List<Shift> getShiftListXML(){
		Shift a = new Shift("Monday","1AM","2AM","PSIB","Akhil","Taken","No Notes");
			a.setId((long) 1001);
		Shift b = new Shift("Monday","2AM","3AM","Library","Firoze","Taken","No Notes");
			b.setId((long) 1002);
		Shift c = new Shift("Monday","3AM","4AM","Bossone","Varoon","Taken","No Notes");
			c.setId((long) 1003);
		Shift d = new Shift("Monday","4AM","5AM","CLC","Donny","Taken","No Notes");
			d.setId((long) 1004);
		List<Shift> shifts = new ArrayList<Shift>();
		shifts.add(a);
		shifts.add(b);
		shifts.add(c);
		shifts.add(d);
		return shifts;
	}
	
}

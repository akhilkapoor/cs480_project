package com.firoze.spreadsheet_test;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class Helper {
	public static boolean isDay(String day) {
		boolean isDay = false;
		if (day.equals("Monday")){isDay = true;}
		if (day.equals("Tuesday")){isDay = true;}
		if (day.equals("Wednesday")){isDay = true;}
		if (day.equals("Thursday")){isDay = true;}
		if (day.equals("Friday")){isDay = true;}
		if (day.equals("Saturday")){isDay = true;}
		if (day.equals("Sunday")){isDay = true;}
		return isDay;
	}
	
	public static void storeShift(Shift s) {
		EntityManager em = EMF.get().createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(s);
	    } finally {
	    	em.getTransaction().commit();
	        em.close();
	    }
	}
	
	public static void deleteShifts() {
		EntityManager em = EMF.get().createEntityManager();
		try {
			Query query=em.createQuery("DELETE FROM Shifts s");
			query.executeUpdate();
	    } finally {
	        em.close();
	    }
	}
	
}

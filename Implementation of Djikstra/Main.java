
/**
 * @author Group8
 * */

//class built in order to understand the stuff better
public class Main {
	
	public static void main(String[] args){
		
		//ask for the desired departure position
		System.out.println("Desired departure:");
		String departure = SimpleIO.readString();

		//ask for the desired arrival position
		System.out.println("Desired arrival point:");
		String arrival = SimpleIO.readString();
		
		//ask for the desired departure time
		System.out.println("Desired departure time(format: 00:00):");
		String time = SimpleIO.readString();
		//convert the desired departure time from String to numbers
		int no1 = Integer.parseInt(time.substring(0, 2));
		//System.out.println(no1);
		int no2 = Integer.parseInt(time.substring(3));
		//System.out.println(no2);
		
		//set the desired departure time accordingly
		int timeNow = no1*60+no2;

		//call the method that makes the route according to the specific desired inputs
		Gothenburg.findGBG(timeNow, departure, arrival);
		
//		
//	        findGBG(0,"Centralstationen","Chalmers");
//	        
//	        findGBG(0,"Lilla Bommen","Chalmers");
//	        
//	        findGBG(23*60+44,"Lilla","Vasa V");
//	        
//	        findGBG(23*60+50,"Hjal","Chal");
//	        
//	        findGBG(23*60+50,"Hjal","Kor");
//	        
//	        findGBG(23*60+50,"Kor","Chal");
//	        
//	    }

	}

}

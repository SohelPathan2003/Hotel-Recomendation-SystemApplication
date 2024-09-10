package hotel.recommendation.system.client;

import static java.lang.System.*;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;

import hotel.recommendation.system.model.classes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import hotel.recommendation.system.service.*;

public class HotelRecommendationSystemMainApplicatioin {

	public static void main(String[] args) throws SQLException, IOException {
			
			out.println("\n\n\t\tWelCome To My Hotel Recommedation Application");
	
	
	//                     ***** objects ******
	
	CityMasterService citymasterservice=new CityMasterService(); // single object created for all program.......
	Scanner sc= new Scanner(System.in);
	CityMasterModel citymastermodel=null;
	AreaMasterModel areamastermodel=null;
	ArrayList<String> listofcities=null;
	ArrayList<String> listofareas=null;
	HotelMasterModel hotelmastermodel=null;
	ArrayList ratinglist=null;
	
	HotelMasterService hotelmasterservice=new HotelMasterService();  // single object created for all program.......
	ArrayList<HotelMasterModel> listofhotels=null;
	ClientMasterModel clientdetails=null;
	HashMap listoffeedbackforhotel=null;
	ClientRegisterModel clientregistermodel=null;
	
	//-----------------------*****************************------------------------------------------------
	
	
	//                *********  Variables ********	
	
	  String cityname=null;
	  String areaname=null;
	  String hotelname=null;
	  String hoteladdress=null;
	  String answer=null;
	  String admin_username=null;
	  String admin_password=null;
	  String clientname=null;
	  String textReview;
	  String clientpassword=null;
	  
	  int price_from=0;
	  int price_to=0;
	  int areaid=0;
	  int cityid=0;
	  boolean b;
	  int result=0;
	  int count=0;
	  int choice=0;
	  long clientnumber;
	  int hotelrating=0;
	  int hotelid=0;
	  int clientid=0;
	 
	  
	  
	//-----------------------*****************************---------------------------------------------------
	 do {
	out.println("\n\n");
	out.println("1 For Admin Long In");
	out.println("2 For User");
	out.println("3 For Registration");
	
	
	choice=sc.nextInt();  // enter your choice for admin or user
	switch(choice) { 
	
	case 1:
		do {
		out.println("Admin valid username");
	sc.nextLine();
	admin_username=sc.nextLine();
	out.println("Enter Admin Valid Password");
	admin_password=sc.nextLine();
	if(admin_username.equals("admin") && admin_password.equals("admin")) {  // match user input
	
	out.println("Admin Successfully Loged In....... ");
	
	do {
		 out.println("");
	out.println("Enter 1 For Add City");
	out.println("Enter 2 For Add Area In City");
	out.println("Enter 3 For Show All Cities ");
	out.println("Enter 4 For Show Area for particular city");
	out.println("Enter 5 For Add Hotels In Area");
	out.println("Enter 6 For Show Hotels AreaWise");
	out.println("Enter 7 For Exit");
	
	 choice=sc.nextInt();
	
	
	switch(choice) {
	
	case 1:
		    // Add city in data base.............
	
	citymastermodel=new CityMasterModel(); // create Citymaster object
	out.println("Enter a City Id and City Name");
	cityid=sc.nextInt();
	sc.nextLine();
	cityname=sc.nextLine();
	citymastermodel.setCityName(cityname);
	citymastermodel.setCityId(cityid);
	b=citymasterservice.isAddCity(citymastermodel);
	if(b) 
		out.println("City Added Successfully..................");
	else 
		out.println("Some Problem Is There............");
	
		
		break;
		
	case 2:
		
		// Add Area within a city............
	
	out.println("Enter a city name for add area");
	sc.nextLine();
	cityname=sc.nextLine();
	result=cityid=citymasterservice.getCityIdByCityName(cityname);
	if(result!=-1) {
		do {
	    out.println("Enter a area name ");
	areaname=sc.nextLine();
	areamastermodel=new AreaMasterModel();
	areamastermodel.setAreaname(areaname);
	areamastermodel.setCityId(cityid);
	b=citymasterservice.isAddArea(areamastermodel);
	if(b) {
		out.println("Area Added Successfully...........");
	}
	else
		out.println("Some Problem is There................");
	
	out.println("do you want to add city");
	answer=sc.nextLine();
	}while(answer.equals("yes"));
	    
	    
	}
	else
		out.println("city is not present");
		
		break;
		
	case 3:
		
		// show all cities in database..........
	
	out.println("Total Cities Present in Your Database\n");
	listofcities=new ArrayList<String>();
	listofcities=citymasterservice.getAllCities();
	if(listofcities!=null) {
	count=0;
	for(String city:listofcities) {
		count++;
		out.println(count+") "+city);
		
	}
	}else
		out.println("There is no area present in database");
		
		break;
		
	case 4:
		
		// take city from user and print total area's 
	
	out.println("Enter a city for area names");
	sc.nextLine();
	cityname=sc.nextLine();
	listofareas=citymasterservice.getAreasCityWise(cityname);
	
	if(listofareas!=null) {
		count=0;   // this is for count number of cities present in city
	for(String area:listofareas) {
		count++;
		out.println(count+") "+area);
		}
	}else
		out.println("There is no area present in "+cityname);
		
		break;
		
	case 5:
		
		//  Add Hotel In particular city..........
	
	out.println("Enter a city name where you want to add hotel");
	sc.nextLine();
	cityname=sc.nextLine();
	cityid=citymasterservice.getCityIdByCityName(cityname);
	listofareas=citymasterservice.getAreasCityWise(cityname);
	if(listofareas!=null){
	count=1;
	for(String area:listofareas) {// here we print all area's under city
	out.println(count+") "+area);
		count++;
	}
	}else
		out.println("there is no area present");
	if(cityid!=-1) {   // city must be present for entering........
	out.println("Enter a area name where you want to add hotel");
	areaname=sc.nextLine();
	areaid=citymasterservice.getAreaIdByAreaName(areaname);
	if(areaid!=-1)
	{
		do {
		out.println("Enter Hotel Name");
	hotelname=sc.nextLine();
	out.println("Enter Hotel Address");
	hoteladdress=sc.nextLine();
	out.println("Enter Hotel Price Range From & To");
	price_from=sc.nextInt();
	price_to=sc.nextInt();
	sc.nextLine();
	hotelmastermodel=new HotelMasterModel();
	hotelmastermodel.setCityId(cityid);
	hotelmastermodel.setAreaid(areaid);
	hotelmastermodel.setHotelname(hotelname);
	hotelmastermodel.setHoteladdress(hoteladdress);
	hotelmastermodel.setPriceFrom(price_from);
	hotelmastermodel.setPriceTo(price_to);
	b=hotelmasterservice.isAddHotel(hotelmastermodel);
	if(b)
		out.println("Hotel Added Successfully.......");
	else
		out.println("Some problem is there for add hotel");
	
	out.println("do you want to add another hotel");
	answer=sc.nextLine();
	}while(answer.equals("yes"));
		
		
		
	}else {      // else city is not present 
	
	out.println("No Area Present In this city");
	    	
	  //if area not present  in our database then we will ask want to add.........
	
	out.println("do you want to add this area in "+cityname);
	answer=sc.nextLine();
	if(answer.equals("yes")) { 
		
	out.println("Enter a area name ");
	areaname=sc.nextLine();
	areamastermodel=new AreaMasterModel();
	areamastermodel.setAreaname(areaname);
	areamastermodel.setCityId(cityid);
	b=citymasterservice.isAddArea(areamastermodel);
	if(b) {
		out.println("Area Added Successfully...........");
	}
	else
		out.println("Some Problem is There................");
	   }else {
		   out.println("Thank You For Visiting.........");
	   }
	 }
	}else
		out.println("city not present");
		
		
		
		break;
		
	case 6:
		
		// show all hotels particular area wise.......
	out.println("Enter City Name");
	sc.nextLine();
	cityname=sc.nextLine();
	listofareas=citymasterservice.getAreasCityWise(cityname);
	if(listofareas!=null){
	count=1;
	for(String area:listofareas) {
		out.println(count+") "+area);
		count++;
	}
	}else
		out.println("there is no area present");
	
	
	out.println("Enter a area for hotels ");
	areaname=sc.nextLine();
	listofhotels=hotelmasterservice.getAllHotelsAreaWise(areaname,cityname);
	if(listofhotels.size()>0) {
		out.println("hotelname\thotel address\t\thotel price range");
	out.println("------------------------------------------------------------------------------------------------------------------------------------\n");
	for(HotelMasterModel hotel:listofhotels) {
		out.println("  "+hotel.getHotelname()+"\t"+hotel.getHoteladdress()+"\t\t"+hotel.getPriceFrom()+" , "+hotel.getPriceTo()+"\n");
	}
	out.println("\n----------------------------------------------------------------------------------------------------------------------------------------");
	
		
	}else
		out.println("No Hotel Present in This Area......");
			break;
		}
	
	}while(choice<=6);
	}
	else {
	// if you entered wrong password or user name name then else block will be execute
	out.println("wrong Admin Username or Password\n\n");
	}
	
	break;
	} while(choice<=3);// for execute code repeatedly........
		break;
		
		
		// end of Admin cases............
		
		
		
		
	case 2:
		// check client is valid or not 
		out.println("Enter User Name");
		sc.nextLine();
		clientname=sc.nextLine();
		out.println("Enter Your Password");
		clientpassword=sc.nextLine();
		if(hotelmasterservice.isClinetValid(clientname,clientpassword))
		{
			out.println("Successfully login...................");
			clientid=hotelmasterservice.getClientIdByUserNameAndPassword(clientname, clientpassword);
			
		
		do {
		out.println("\n\nEnter 1 For See All Hotel AreaWise");
	    out.println("Enter 2 For view hotels rating wise");
	    out.println("Enter 3 For visit to hotel");
	    out.println("Enter 4 For Exit");
	
	out.println("\n\nEnter Your Choice");
	choice=sc.nextInt();
	
	switch(choice) {
	case 1:
		// show all hotels particular area wise.......
	
	out.println("Enter City Name");
	sc.nextLine();
	cityname=sc.nextLine();
	
	listofareas=citymasterservice.getAreasCityWise(cityname);
	if(listofareas!=null){
	count=1;
	for(String area:listofareas) {
		out.println(count+") "+area);
		count++;
	}
	}else
		out.println("there is no area present");
	
	
	out.println("Enter a area for hotels ");
	areaname=sc.nextLine();
	listofhotels=new ArrayList<HotelMasterModel>();
	listofhotels=hotelmasterservice.getAllHotelRatingWise(cityname,areaname); // cityname is optional
	if(listofhotels.size()>0) {
		out.println("hotelname\thotel address\t\thotel price range\t number of client");
	out.println("------------------------------------------------------------------------------------------------------------------------------------\n");
	for(HotelMasterModel hotel:listofhotels) {
		out.println("  "+hotel.getHotelname()+"\t"+hotel.getHoteladdress()+"\t\t"+hotel.getPriceFrom()+" , "+hotel.getPriceTo()+"\t\t\t"+hotel.getNumberOfClient()+"\n");
	}
	out.println("\n----------------------------------------------------------------------------------------------------------------------------------------");
	
	}else
		out.println("No Hotel Present in This Area......");
		
		break;
	case 2:
		
		
	    
		
		listofhotels=new ArrayList<HotelMasterModel>();
		hotelmastermodel=new HotelMasterModel();
		out.println("Enter City Name");
		sc.nextLine();
		cityname=sc.nextLine();
		
		listofareas=citymasterservice.getAreasCityWise(cityname);
		if(listofareas!=null) {
			count=0;   // this is for count number of cities present in city
		for(String area:listofareas) {
			count++;
			out.println(count+") "+area);
			}
		}else
			out.println("There is no area present in "+cityname);
		
		out.println("Enter Area Name");
		areaname=sc.nextLine();
		
		listofhotels=hotelmasterservice.getAllHotelRatingWise(cityname,areaname);
		if(listofhotels.size()>0) {
			
			
		out.println("hotelname\thotel address\t\thotel price range\thotelrating\t number of client");
	out.println("------------------------------------------------------------------------------------------------------------------------------------\n");
	for(HotelMasterModel hotel:listofhotels) {
		out.println("  "+hotel.getHotelname()+"\t"+hotel.getHoteladdress()+"\t\t"+hotel.getPriceFrom()+" , "+hotel.getPriceTo()+"\t\t"+hotel.getHotelRating()+"\t\t\t"+hotel.getNumberOfClient()+"\n");
	}
	out.println("\n----------------------------------------------------------------------------------------------------------------------------------------");
		
	
	out.println("do you want to see review about particular hotel.......");
	answer=sc.nextLine();
	if(answer.equals("yes")) {
		out.println("Enter Hotel Name");
		hotelname=sc.nextLine();
		
		listoffeedbackforhotel=	hotelmasterservice.getClientFeedbackForHotel(areaname, hotelname);
		if(listoffeedbackforhotel.size()>0) {
			out.println("------------------------------------------------------------------------------");
			
		Set<Map.Entry<String,String>> data=listoffeedbackforhotel.entrySet();
	    for(Map.Entry<String,String> feedback:data) {
	    	out.println(feedback.getKey()+"\t\t"+feedback.getValue());
	    }
	    out.println("------------------------------------------------------------------------------");
		}else {
			out.println("NO Feedback For This Hotel.........");
		}
	
		}else 
			out.println("NO Best Hotels in "+areaname+"\n\n");	
			
		}else 
			out.println("There is no Hotels Present");
		
	break;
	
	
	
		case 3:
	
	out.println("Enter City Name");
	sc.nextLine();
	cityname=sc.nextLine();
	
listofareas=citymasterservice.getAreasCityWise(cityname);
	
	if(listofareas!=null) {
		count=0;   // this is for count number of cities present in city
	for(String area:listofareas) {
		count++;
		out.println(count+") "+area);
		}
	}else
		out.println("There is no area present in "+cityname);
	
	out.println("Enter Area Name");
	areaname=sc.nextLine();
	
	// get city id and area id ..........
	
	areaid=citymasterservice.getAreaIdByAreaName(areaname);
	cityid=citymasterservice.getCityIdByCityName(cityname);
	do {
	out.println("\n\n1 : Recommendation on the basis of Hotel Rating");
	out.println("2 : Recommendation on the basis of Number Of Clients");
	out.println("3 : For Exit..........\n\n");
	out.println("Enter Your Choice");
	choice=sc.nextInt();
	switch(choice) {
	case 1:
		listofhotels=new ArrayList<HotelMasterModel>();
		hotelmastermodel=new HotelMasterModel();
		
		listofhotels=hotelmasterservice.getAllHotelRatingWise(cityname,areaname);
		if(listofhotels.size()>0) {
		out.println("hotelname\thotel address\t\thotel price range\thotelrating\t\tNumber of Clients");
	out.println("------------------------------------------------------------------------------------------------------------------------------------\n");
	for(HotelMasterModel hotel:listofhotels) {
		out.println("  "+hotel.getHotelname()+"\t"+hotel.getHoteladdress()+"\t\t"+hotel.getPriceFrom()+" , "+hotel.getPriceTo()+"\t\t"+hotel.getHotelRating()+"\t\t\t"+hotel.getNumberOfClient()+"\n");
	}
	out.println("\n----------------------------------------------------------------------------------------------------------------------------------------");
	
	out.println("do you want to give rating and review to any hotel");
	sc.nextLine();
	answer=sc.nextLine();
	
	if(answer.equals("yes")) {
	out.println("Enter hotel Name");
	hotelname=sc.nextLine();
	// here i get hotel id which user want to go.........
	hotelid=hotelmasterservice.getHotelIdByHotelName(cityname,areaname,hotelname);
//	out.println(hotelid);
	out.println("Enter Hotel Rating");
	hotelrating=sc.nextInt();
	sc.nextLine();	
	
	out.println("Enter you valuable review for this hotel");
	textReview=sc.nextLine();
	clientdetails=new ClientMasterModel();
	clientdetails.setHotelId(hotelid);
	clientdetails.setClientid(clientid);
	clientdetails.setHotelRating(hotelrating);
	clientdetails.setTextReview(textReview);
	b=hotelmasterservice.isAddClientDetails(clientdetails);
	if(b) {
		out.println("Client Review Added Successfully..........");
		// rating update in hotel master table.......
		b=hotelmasterservice.updateRatingInHotelMaster(hotelid);
		if(b) {
			out.println("Rating Updated Successfully in Hotel Master");
			out.println("     Thank You for Visiting to our Application ............");
		}
		else
			out.println("Rating Not Updated.........");
		
	}
	else
		out.println("Some problem is there....................");
	
	}else 
		out.println("     Thank You for Visiting to our Application ............");
	
	}else     // if hotels not present in this area..........
		out.println("There is no Hotels Present In this Area");
			
			
			 break;
	case 2:
		// recommend hotels on the basis of number of clients
		
		
		listofhotels=new ArrayList<HotelMasterModel>();
		hotelmastermodel=new HotelMasterModel();
		
		listofhotels=hotelmasterservice.getAllHotelNumberOfClientgWise(cityname,areaname);
		if(listofhotels.size()>0) {
		out.println("hotelname\thotel address\t\thotel price range\thotelrating\t\tNumber of Clients");
	out.println("------------------------------------------------------------------------------------------------------------------------------------\n");
	for(HotelMasterModel hotel:listofhotels) {
		out.println("  "+hotel.getHotelname()+"\t"+hotel.getHoteladdress()+"\t\t"+hotel.getPriceFrom()+" , "+hotel.getPriceTo()+"\t\t"+hotel.getHotelRating()+"\t\t\t"+hotel.getNumberOfClient()+"\n");
	}
	out.println("\n----------------------------------------------------------------------------------------------------------------------------------------");
	
	out.println("do you want to give rating and review to any hotel");
	sc.nextLine();
	answer=sc.nextLine();
	
	if(answer.equals("yes")) {
	out.println("Enter hotel Name");
	hotelname=sc.nextLine();
	// here i get hotel id which user want to go.........
	hotelid=hotelmasterservice.getHotelIdByHotelName(cityname,areaname,hotelname);
	out.println(hotelid);
	out.println("Enter Hotel Rating");
	hotelrating=sc.nextInt();
	sc.nextLine();	
	
	out.println("Enter you valuable review for this hotel");
	textReview=sc.nextLine();
	clientdetails=new ClientMasterModel();
	clientdetails.setHotelId(hotelid);
	clientdetails.setClientid(clientid);
	clientdetails.setHotelRating(hotelrating);
	clientdetails.setTextReview(textReview);
	b=hotelmasterservice.isAddClientDetails(clientdetails);
	if(b) {
		out.println("Client Review Added Successfully..........");
		// rating update in hotel master table.......
		b=hotelmasterservice.updateRatingInHotelMaster(hotelid);
		if(b) {
			out.println("Rating Updated Successfully in Hotel Master");
			out.println("     Thank You for Visiting to our Application ............");
		}
		else
			out.println("Rating Not Updated.........");
		
	}
	else
		out.println("Some problem is there....................");
	
	}else 
		out.println("     Thank You for Visiting to our Application ............");
	
	}else     // if hotels not present in this area..........
		out.println("There is no Hotels Present In this Area");
		
		break;
		default:
			break;
		
		}
	}while(choice<=2);
	default :
		out.println("Invalid choice.........");
	}
	}while(choice<=2);
		break;
		}else {
			out.println("Invalid User Name or Password..............");
			break;
		}
	case 3:
		
		
		// User Registration details.........
		// Enter Client Details 
		out.println("Enter Your Name");
		sc.nextLine();
		clientname=sc.nextLine();
		out.println("Enter Your contact Number");
		clientnumber=sc.nextLong();
		out.println("Enter Your password");
		sc.nextLine();
		clientpassword=sc.nextLine();
		clientregistermodel =new ClientRegisterModel();
		clientregistermodel.setClientName(clientname);
		clientregistermodel.setClientnumber(clientnumber);
		clientregistermodel.setClientpassword(clientpassword);
		b=hotelmasterservice.isClientRegister(clientregistermodel);
		if(b)
			out.println("Client Registered Successfully.............");
		else
			out.println("some problem in registration..............");
		break;
		
		
	default:
		out.println("Invalid choice.........");
	}         // first switch case end........
	}while(choice>0); 
  }
}

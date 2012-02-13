//Provides screen to give authority to particular user.
package com.Rewards_Engine;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import com.Rewards_Engine.add_reason.CallWebServiceTask;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;



public class assign_authority extends Activity {

	
	
	private static final String NAMESPACE = "http://tempuri.org/";      
	//private static final String URL = "http://192.168.1.85/studentresponse/Service.asmx";
	private static final String URL = "http://184.168.111.52:82/Service.asmx";
	
	private static final String SOAP_ACTION1 = "http://tempuri.org/GetUserTypeInfo";      
	private static final String METHOD_NAME1 = "GetUserTypeInfo"; 
	
	
	private static final String SOAP_ACTION3 = "http://tempuri.org/GetUserTypeInfo1";      
	private static final String METHOD_NAME3 = "GetUserTypeInfo1"; 
	
	
	private static final String SOAP_ACTION10 = "http://tempuri.org/GetReward_Point";      
	private static final String METHOD_NAME10 = "GetReward_Point";  
	
	 	
	static int	curtimestamp;
	static int nexttimestamp;
	int intpoint, usedpnt, availpnt; 
	static int temp6;
	static int position;
	int position1, temp8;
	static String sdate;
	static String sdate1;
	static String sdate12;
	static String sdate13;
	String strdateassign;
	static String strutype;
	String strdateexpire;
	static String str12;
	static String str1;
	String strutypes, strname;
	static String[] fname;
	static String[] lname;
	static String[] userlogin;
	static String[] user_type1;
	static String[] user_point1;
	static ArrayAdapter<CharSequence> usertypeadapter;
	static ArrayAdapter<CharSequence> unameadapter;
	static TextView txtdate;
	static Spinner utype;
	static LinearLayout ll;
	static Spinner sprname;
	static EditText edtpoint;
	static Button button;
	static Button back;
	static Button logout;
	static AlertDialog.Builder alertbox;
	String user_login_name,sumearnedpoint,sumredimedpoint,utype12;
	
	static Session ss;
	static session_webservices sw;
	DataBaseHelper data;
	private String sumnegativepoint;
	
		/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignpointbyadmin);
        Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);               
        
        ll=(LinearLayout) findViewById(R.id.ll);
              
        txtdate=(TextView) findViewById(R.id.txtsysdate); 
    	utype = (Spinner) findViewById(R.id.spnrtype);
    	sprname = (Spinner) findViewById(R.id.spnrname);      
        edtpoint = (EditText) findViewById(R.id.editpoints);
        button = (Button) findViewById(R.id.btnadd);
        back=(Button) findViewById(R.id.btnback);
        logout=(Button) findViewById(R.id.btnlogout);
        
        
        sw=new session_webservices();
        alertbox = new AlertDialog.Builder(this);
        
        
        usertypeadapter = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item);
        utype.setOnItemSelectedListener(new MyOnutypepointSelectedListener());
        
        unameadapter = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item);
        sprname.setOnItemSelectedListener(new MyOnunamepointSelectedListener());
        
        
        
        
    	 
    	 
    	back.setOnClickListener(new Button.OnClickListener() 
   	{ 
       	
       	public void onClick (View v)
   		{ 
   		  back();
   		    			
   		}}); 
    	logout.setOnClickListener(new Button.OnClickListener()  
   	{ 
       	
       	public void onClick (View v)
   		{ 
   		    
   		    		logout();	
   	 	}});
    	
        button.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
        	         
       	  	strdateassign=""+curtimestamp;
       	  	strutypes=utype.getSelectedItem().toString();
       	  	strname=sprname.getSelectedItem().toString();
       	      	  	  	 
       	
       	  	intpoint=Integer.parseInt(str12);
       	  	strdateexpire=""+nexttimestamp;
       	 	
       	  	/*System.out.println("date is:"+strdateassign);     
       	  	System.out.println("type is:"+strutype);
       	  	System.out.println("name is:"+strname);                 	 
       	  	System.out.println("point are:"+intpoint);*/
       	 
       	  	usedpnt=0;
       	  	availpnt=intpoint-usedpnt; 
       	  	
       	  	
       	  	if(userlogin[position1].contains(";")) 
       	  	{       	  		
       	  		userlogin[position1]=userlogin[position1].replace(";", "");
       	  	
       	  	}
	       	 //System.out.println("User Login is..."+userlogin[position1]);
	       	 
	       	 
	       	 sumearnedpoint=sw.getsumearnedpoint(userlogin[position1]);
	       	 
	       	 sumredimedpoint=sw.getsumredimpoint(userlogin[position1]);
	       	 
	       	 //System.out.println("Sum of earned points..."+sumearnedpoint);
	       	 //System.out.println("Sum of redimed points..."+sumredimedpoint);
	       	 getreward_point5(userlogin[position1]); 
       	    	              	  
            }  
        });
        
        ll.setVisibility(View.INVISIBLE);
		CallWebServiceTask task = new CallWebServiceTask();
		task.applicationContext = assign_authority.this;
		task.execute();
	
    }
    
    
    
    
    /*Type :Function
    name:runback
    return type:void
    date:12-12-2011
    purpose:Performing background work*/ 
    
    public static void runback()
    {
    	sw.point();
        
        user_type1=ss.getSessionuser_type1();
        user_point1=ss.getSessionuser_point();
       
        call_calender1();
        call_calender();
        
    }
    /*Type :Function
    name:onrunback
    return type:void
    date:12-12-2011
    purpose:Updating UI when background processing completes*/
    public static void onrunback()
    {
    	txtdate.setText("Date:"+sdate);
        
        fillusertype();
        
       ll.setVisibility(View.VISIBLE);
    	
    }
    /*Type :Class
    name:CallWebServiceTask
    return type:void
    date:12-12-2011
    purpose:Creating progress dialog*/
    public static class CallWebServiceTask extends AsyncTask<String, Integer, Void> {
		private static ProgressDialog dialog;
		protected Context applicationContext;

		@Override
		protected void onPreExecute() {
			
			System.out.println("IN PreExecute");
			this.dialog = ProgressDialog.show(applicationContext, "Assign Authority", "Loading data...", true);
		}
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("IN BACKGROUND");
			assign_authority.runback();
			
			return null ;
			
			
		}
		@Override
		protected void onProgressUpdate(Integer... progress) {
	        //this runs in UI thread so its safe to modify the UI
	        //myTextField.append("finished call " + progress);
	    }
		@Override
		protected void onPostExecute(Void unused) {
			
			
			this.dialog.cancel();
			System.out.println("IN PostExecute");
			assign_authority.onrunback();
					
			 
		}

		

		
	}
    /*Type :Function
    name:call_calender1
    return type:void
    date:12-12-2011
    purpose:Doing some date/calender related work*/
	public static void call_calender1()
	{
		Calendar cal = new GregorianCalendar(); 
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);   
        int day = cal.get(Calendar.DAY_OF_MONTH);
        curtimestamp=(int) (cal.getTimeInMillis()/1000L);
        sdate=(+ day + "-" + (month + 1) + "-" + year);
        sdate12=(+ year + "-" + (month + 1) + "-" + day );
	
	
        Calendar cal1 = new GregorianCalendar();
        cal1.add(Calendar.DAY_OF_MONTH,7);
        int day12=cal1.get(Calendar.DAY_OF_MONTH);
        int month1 = cal1.get(Calendar.MONTH);
        int year1 = cal1.get(Calendar.YEAR);   
        sdate13=(+ year1 + "-" + (month1 + 1) + "-" + (day12));
	}
	/*Type :Function
    name:call_calender
    return type:void
    date:12-12-2011
    purpose:Doing some date/calender related work*/
	public static void call_calender() 
	{	
		
		  
     	Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		//System.out.println("Month is:"+month);
		int year = cal.get(Calendar.YEAR);   
		int day = cal.get(Calendar.DAY_OF_MONTH);
		curtimestamp=(int) (cal.getTimeInMillis()/1000L);
		
		//increament date by 12 months
		cal.add(Calendar.MONTH,11);
		int month1 = cal.get(Calendar.MONTH);
		//System.out.println("Month is:"+month);
		int year1 = cal.get(Calendar.YEAR);   
		int day1 = cal.get(Calendar.DAY_OF_MONTH);
		nexttimestamp=(int) (cal.getTimeInMillis()/1000L);
		sdate1=(+ day1 + "-" + (month1 + 1) + "-" + year1);
	          
	}
	
	/*Type :Function
    name:getuname
    return type:void
    date:12-12-2011
    purpose:retriving user name from user type*/
	public static void getuname(String Type)
	 { 
		
		 try {
			    SoapObject request;	  
			 	if(Type.equals("professor")||Type.equals("student")||Type.equals("administrator"))
			 	{
	    	 	 request = new SoapObject(NAMESPACE, METHOD_NAME3);
			 		 		
		 		request.addProperty("UserType",Type);
		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION3, envelope);   
		    	Object result = (Object)envelope.getResponse();
		 	    str1=result.toString(); 
			 	}
			 	else
			 	{
			 		request = new SoapObject(NAMESPACE, METHOD_NAME1);
	 		 		
			 		request.addProperty("UserType",Type);
			 		
			 			         		 
			 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

			 		envelope.setOutputSoapObject(request); 
			 		envelope.dotNet=true; 
			 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
			 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			 	
			 		androidHttpTransport.call(SOAP_ACTION1, envelope);   
			    	Object result = (Object)envelope.getResponse();
			 	    str1=result.toString(); 
			 		
			 	}
		 	      
		 	   // System.out.println("Data:"+str1);
		 	    if(str1.equals(null)||str1.equals("anyType{}"))
		        {
		 		   
		        	
		        	alertbox.setMessage("There are no users of type "+Type);
	    		   	alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	    				 
	                    
	                    public void onClick(DialogInterface arg0, int arg1) {
	                        
	                    }
	                });
	                   
	                alertbox.show();
		        } 
		 	    else
		 	    {
		 	    if(str1.contains("anyType{anyType=")||str1.contains("}")||str1.contains("anyType="))
		        {
		    	  str1=str1.replace("anyType{anyType=", "");
		    	  str1=str1.replace("anyType=", "");
		    	  str1=str1.replace("}", ""); 
		    	  str1=str1.trim();
		    	 // System.out.println("String"+str1); 
		    	  
		        }
		        String[] arrauserData;
		        arrauserData = ((String) str1).split("; ");
		         
		        int temp2=arrauserData.length;
		        temp6=((arrauserData.length)/3);
		        userlogin=new String[temp6]; 
		        fname=new String[temp6]; 
		        lname=new String[temp6]; 
		             
		       
		         
		        for(int a=0,b=0;a<temp2;a=a+3) 
		        {
		        	userlogin[b]=arrauserData[a];
		        	//System.out.println("user login is..."+userlogin[b]);
		        	b++;
	 	                	
		        }
		        for(int a=1,b=0;a<temp2;a=a+3)
		        {
		        	fname[b]=arrauserData[a];
		        	b++;
	 	                	
		        }
		        for(int a=2,b=0;a<temp2;a=a+3)
		        {
			        lname[b]=arrauserData[a];
		        	b++;
	 	                	
		        }
		        
		        
		        
	             
		        
		 	    }   
		 }
	 	    catch (Exception e)    
	        {
			  
			 System.out.println("Error msg:"+e.getMessage());
	        
	        }
	 }  
	/*Type :Function
    name:fillnames
    return type:void
    date:12-12-2011
    purpose:filling adapter with data*/
	public static void fillnames()
	{
		for(int x=0;x<temp6;x++) 
        {
               	
    	   unameadapter.add(fname[x]+" "+lname[x]);
        }
    	 sprname.setAdapter(unameadapter);
    	 
    
	}
	/*Type :Function
    name:getreward_point5
    return type:void
    date:12-12-2011
    purpose:inserting record to the remote db if it does not exits and updates if it exista*/
	public void getreward_point5(String name)
	 {   
		 
		 try { 
			 		  
			 	  
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME10);   
		 		
		 		request.addProperty("UserName",name); 
		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION10, envelope);     
		    	Object result = (Object)envelope.getResponse();
		 	    String str13=result.toString(); 
		 	      
		 	    //System.out.println("Data:"+str13);  
		 	    sumearnedpoint=sw.getsumearnedpoint(name);
		 	    sumredimedpoint=sw.getsumredimpoint(name);
		 	    sumnegativepoint=sw.getsumnegativepoint(name); 
		 	    if(str13.equals(null)||str13.equals("anyType{}"))
		        {
		 	    	 	    	
		 	    	
		 	    	sw.addrewardpoints(name, intpoint, usedpnt, availpnt, strdateassign, strdateexpire,Integer.parseInt(sumearnedpoint),Integer.parseInt(sumredimedpoint),Integer.parseInt(sumnegativepoint));
		 	    	Toast.makeText(this, "Points assigned successfully",Toast.LENGTH_SHORT).show();
		 	    	
		        } 
		 	    else 
		 	    {
		 	    		    	
		 	    	
		 	    	sw.updaterewardpointswhole(name,intpoint,usedpnt, availpnt,strdateassign,strdateexpire);	
		 	    	Toast.makeText(this, "Points Updated Successfully",Toast.LENGTH_SHORT).show();
		          
		 	    }     
		 }
	 	    catch (Exception e)      
	        {
			 
			 System.out.println("Error msg:"+e.getMessage()); 
	        
	        }
	 } 
	
	
	public class MyOnutypepointSelectedListener implements OnItemSelectedListener {

	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {

	    	
	    	strutype=parent.getItemAtPosition(pos).toString();
	    	position=pos;
	    	unameadapter.clear();
	    	CallWebServiceTask1 task = new CallWebServiceTask1();
			task.applicationContext = assign_authority.this;
			task.execute();
	    	
	            
	    }
   
	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing.
	    }
	}
	 /*Type :Class
    name:CallWebServiceTask1
    return type:void
    date:12-12-2011
    purpose:Creating progress dialog*/
	public static class CallWebServiceTask1 extends AsyncTask<String, Integer, Void> {
		private static ProgressDialog dialog;
		protected Context applicationContext;

		@Override
		protected void onPreExecute() {
			
			System.out.println("IN PreExecute");
			this.dialog = ProgressDialog.show(applicationContext, "Assign Authority", "Loading names...", true);
		}
		@Override
		protected Void doInBackground(String... params) {    
			// TODO Auto-generated method stub
			System.out.println("IN BACKGROUND");
			assign_authority.utype_selected_runback();
			//return flag1;
			return null ;
			
			
		}
		@Override
		protected void onProgressUpdate(Integer... progress) {
	        //this runs in UI thread so its safe to modify the UI
	        //myTextField.append("finished call " + progress);
	    }
		@Override
		protected void onPostExecute(Void unused) {
			
			
			this.dialog.cancel();
			System.out.println("IN PostExecute");
			assign_authority.onutyep_selected_runback();
					
			 
		}

		

		
	}
	/*Type :Function
    name:utype_selected_runback
    return type:void
    date:12-12-2011
    purpose:Performing background work*/ 
	public static void utype_selected_runback()
	{
		getuname(strutype);
    	str12=user_point1[position];
	}
	/*Type :Function
    name:onutyep_selected_runback
    return type:void
    date:12-12-2011
    purpose:Updating UI when background processing completes*/
	public static void onutyep_selected_runback()
	{
		
		fillnames();
		if(str12.contains(";"))
    	{
    		str12=str12.replace(";", "");
    	}
    			
       	edtpoint.setText(str12); 
	}
	
	public class MyOnunamepointSelectedListener implements OnItemSelectedListener {

	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {

	    	
	    	
	    	
	    	position1=pos;
	       
	    	
	            
	    }
   
	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing.
	    }
	}
	
	/*
    type:function
    name:addreason
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:adding data to the adapter
    */
	public static void fillusertype()
	{
		
		
		for(int x=0;x<user_type1.length;x++) 
        {
               	
        	usertypeadapter.add(user_type1[x]);
        }
        utype.setAdapter(usertypeadapter);
        
	}
	/*
    type:function
    name:logout
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:Moving to splash screen
    */
	private void logout()
	{
		Intent i1 = new Intent(this,Rewards_Engine.class);
	    startActivity(i1);

	}
	/*
    type:function
    name:logout
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:Moving to previous activity
    */
    private void back()
	{
		Intent i1 = new Intent(this,reward_main_page.class);
	    startActivity(i1);  

	}
  //overriding devices back button
    @Override
	public void onBackPressed()
	{
		Intent i=new Intent(this,reward_main_page.class);
		startActivity(i);
		return;
	}
	
	
	
}

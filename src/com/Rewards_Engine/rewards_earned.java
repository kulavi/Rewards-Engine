//Displays the reward point earned and redeemed
package com.Rewards_Engine;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.Rewards_Engine.rewards_given.CallWebServiceTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class rewards_earned extends Activity {
	
	
	
	private static final String NAMESPACE01 = "http://tempuri.org/";      
	private static final String URL01 = "http://192.168.1.204/iBharti/Service.asmx"; 
	
	private static final String SOAP_ACTION01 = "http://tempuri.org/insertCoupen";      
	private static final String METHOD_NAME01= "insertCoupen";  
	
	
	private static final String NAMESPACE = "http://tempuri.org/";      
	//private static final String URL = "http://192.168.1.85/studentresponse/Service.asmx";
	private static final String URL = "http://184.168.111.52:82/Service.asmx";
	
	/*private static final String SOAP_ACTION9 = "http://tempuri.org/Getreward_enginedata";      
	private static final String METHOD_NAME9 = "Getreward_enginedata"; */
	
	private static final String SOAP_ACTION10 = "http://tempuri.org/GetReward_Point";      
	private static final String METHOD_NAME10 = "GetReward_Point"; 
	
	private static final String SOAP_ACTION16 = "http://tempuri.org/UpdateTotalRedimPoint";  
	private static final String METHOD_NAME16 = "UpdateTotalRedimPoint";
	
	private static final String SOAP_ACTION17 = "http://tempuri.org/Insertredim_point_details";  
	private static final String METHOD_NAME17 = "Insertredim_point_details";
	
	
	private static final String SOAP_ACTION19 = "http://tempuri.org/Getredim_point_details";  
	private static final String METHOD_NAME19 = "Getredim_point_details";
	
	private static final String SOAP_ACTION20 = "http://tempuri.org/get_redeem_pnt_to_currency";  
	private static final String METHOD_NAME20 = "get_redeem_pnt_to_currency";
	
	
	
    String condi_ser="a:6:{s:4:\"type\";s:32:\"salesrule/rule_condition_combine\";s:9:\"attribute\";N;s:8:\"operator\";N;s:5:\"value\";s:1:\"1\";s:18:\"is_value_processed\";N;s:10:\"aggregator\";s:3:\"all\";}";
	    
	String action_ser="a:6:{s:4:\"type\";s:40:\"salesrule/rule_condition_product_combine\";s:9:\"attribute\";N;s:8:\"operator\";N;s:5:\"value\";s:1:\"1\";s:18:\"is_value_processed\";N;s:10:\"aggregator\";s:3:\"all\";}";
		
	
	session_webservices sw;
	Session ss;
	DataBaseHelper data;
	Dialog myDialog; 
	
	
	TableLayout earned_tbl,redimtbl,showredimdetailstbl;
	
	TextView total_earned,total_redimed;
	Button view_details,redim_details,redim_point,cancel;
	Button back,logout;
	
	EditText pointee;
	RelativeLayout rl;
	LinearLayout footer1;
	Button btnok;
	
	String onlyname,str1,utype,totalredimed_points,totalearned_points,coupen,sdate,sdate12,sdate13;
	String sumredimedpoint,sumearnedpoint;
	int total_point;
	double total_amount;
	int temp16,getpoint,curtimestamp;
	
	int[] reasonid,new_courseid_engine;
	
	double amount;
	
	int temp61;
    String[] redimdate,redimcoupen;
	private String sumnegativepoint;
	private String totalnegative_points; 
     
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reward_earned);
        
        
        Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
        /*inflating the views to use in this class*/
        rl=(RelativeLayout) findViewById(R.id.rl);
        footer1=(LinearLayout) findViewById(R.id.footer1);
        earned_tbl=(TableLayout) findViewById(R.id.tbl_reward_earned);
        
        redimtbl=(TableLayout)findViewById(R.id.redim);
        showredimdetailstbl=(TableLayout)findViewById(R.id.redimdetails);
        
        total_earned=(TextView) findViewById(R.id.txt_total_earned);
        total_redimed=(TextView) findViewById(R.id.txt_total_redimed);
        
        view_details=(Button) findViewById(R.id.btn_view_details);
        redim_details=(Button) findViewById(R.id.btn_redim_details);
        redim_point=(Button) findViewById(R.id.btn_redim_point);
       
        
        
        back=(Button) findViewById(R.id.btnback);
   	 	logout=(Button) findViewById(R.id.btnlogout);
   	 	btnok=(Button) findViewById(R.id.btnok);
   	 
   	 	footer1.setVisibility(View.GONE);
   	 
	   	back.setOnClickListener(new Button.OnClickListener() 
	  	{ 
	      	
	      	public void onClick (View v)
	  		{ 
	      		back();
	  		    			
	  		}});
	   	btnok.setOnClickListener(new Button.OnClickListener() 
	  	{ 
	      	
	      	public void onClick (View v)
	  		{ 
	      		footer1.setVisibility(View.GONE);
	      		back.setVisibility(View.VISIBLE);
        		earned_tbl.setVisibility(View.VISIBLE);
        		redim_point.setVisibility(View.VISIBLE);
		   		showredimdetailstbl.setVisibility(View.GONE);
	      		
	  		    			
	  		}}); 
	   	logout.setOnClickListener(new Button.OnClickListener()  
	  	{ 
	      	
	      	public void onClick (View v)
	  		{ 
	  		    
	  		    		logout();	
	  	 	}});
	   	
	   	
    
    
	    view_details.setOnClickListener(new Button.OnClickListener()  
	  	{ 
	        	
	      	public void onClick (View v)
	   		{ 
	        		
	      		view_reward_earned_details();
	      		
	      		/*data.deletereward_engine_in();
	        		//rewardearnedtbl.removeAllViews();
			   		sw.getreward_engine_to(onlyname);
			   		//back.setVisibility(View.INVISIBLE);
			  		//rewardearnedtbl.setVisibility(View.VISIBLE);
			  		footer1.setVisibility(View.VISIBLE);	
			  		redim_point.setVisibility(View.GONE);
			  		redim_details.setVisibility(View.GONE);
			   		earned_tbl.setVisibility(View.GONE); 
			  		redimtbl.setVisibility(View.GONE);*/
	    		    			
	   		}}); 
	     redim_details.setOnClickListener(new Button.OnClickListener()  
	   	 { 
	        	
	       	public void onClick (View v)
	    	{ 
	        		showredimdetailstbl.removeAllViews();
	        		back.setVisibility(View.INVISIBLE);
	        		earned_tbl.setVisibility(View.GONE);
	        		redim_point.setVisibility(View.GONE);
			   		showredimdetailstbl.setVisibility(View.VISIBLE);
			  		//rewardearnedtbl.setVisibility(View.GONE);
			  		getredim_details_lplanet();
			  		footer1.setVisibility(View.VISIBLE);
			  		showredimdetailstbl.removeAllViews();
			  		redim_detail_display();
	    		    			
	     	}});
	        
	        
	      redim_point.setOnClickListener(new Button.OnClickListener() 
	      { 
	        	
	       	public void onClick (View v)
	    	{ 
	        	pointstoredim();
	    		    			
	    	}});
	             
	     
	        sw=new session_webservices();
	        data=new DataBaseHelper(this); 
	        
	        onlyname=ss.getSessionLoginId();
	        
	        rl.setVisibility(View.INVISIBLE);
	        
	        
	        /*showing progress bar till the background work of web services completes*/
			CallWebServiceTask task = new CallWebServiceTask();
			task.applicationContext = rewards_earned.this;
			task.execute();
    }
    
    /*Type :Function
    name:view_reward_earned_details
    return type:void
    date:12-12-2011
    purpose:Calling an activity*/ 
    public void view_reward_earned_details()
    {
    	
    	
    	Intent i1 = new Intent(this,view_reward_earned_details.class);
    	Bundle bundle = new Bundle();
		bundle.putString("wel",onlyname);
		i1.putExtras(bundle);
	    startActivity(i1);
    }
    /*Type :Function
    name:runback
    return type:void
    date:12-12-2011
    purpose:Performing background work*/ 
    public void runback()
    {
    	sw.getreasonfromserver(utype);
        reasonid=ss.getSessionReasonid();
        
        
        Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		System.out.println("Month is:"+month);
		int year = cal.get(Calendar.YEAR);   
		
		
		int day = cal.get(Calendar.DAY_OF_MONTH);
		curtimestamp=(int) (cal.getTimeInMillis()/1000L);
		
		 
		
		sdate=(+ day + "-" + (month + 1) + "-" + year);
		
		sdate12=(+ year + "-" + (month + 1) + "-" + day );
	
	
		Calendar cal1 = new GregorianCalendar();
		
		cal1.add(Calendar.DAY_OF_MONTH,7);
		int day12=cal1.get(Calendar.DAY_OF_MONTH);
		
		int month1 = cal1.get(Calendar.MONTH);
		System.out.println("Month is:"+month);
		
		int year1 = cal1.get(Calendar.YEAR);   
		
		
		sdate13=(+ year1 + "-" + (month1 + 1) + "-" + (day12));  
		
	
	
	
		System.out.println("Date IN YYMMDD:"+sdate12);
		System.out.println("Date IN YYMMDD:"+sdate13);
        
        
        
        
        totalearned_points=sw.getsumearnedpoint(onlyname);
        totalnegative_points=sw.getsumnegativepoint(onlyname);
        totalredimed_points=sw.getsumredimpoint(onlyname); 
        
        
        
        
    }
    /*Type :Function
    name:onrunback
    return type:void
    date:12-12-2011
    purpose:Updating UI when background processing completes*/
    public void onrunback()
    {
    	int point_in_hand=Integer.parseInt(totalearned_points)-Integer.parseInt(totalnegative_points);
    	rl.setVisibility(View.VISIBLE);	
    	total_earned.setText("Total Points Available:"+point_in_hand);
	    total_redimed.setText("Total Points Redeemed:"+totalredimed_points);
    }
    /*Type :Class
    name:CallWebServiceTask
    return type:void
    date:12-12-2011
    purpose:Creating progress dialog*/
    public class CallWebServiceTask extends AsyncTask<String, Integer, Void> {
		private ProgressDialog dialog;
		protected Context applicationContext;

		@Override
		protected void onPreExecute() {
			
			System.out.println("IN PreExecute");
			this.dialog = ProgressDialog.show(applicationContext, "Rewards Earned", "Loading data...", true);
		}
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("IN BACKGROUND");
			runback();
			
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
			onrunback();
					
			 
		}

		

		
	}
    /*
    type:function
    name:pointstoredim
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:opening dialog asking for the points to redeem
    */ 
    public void pointstoredim()
	{
		myDialog = new Dialog(this);
     	myDialog.setContentView(R.layout.redimpoint);
     	myDialog.setTitle("REDIM POINTS");
     	myDialog.setCancelable(true);  
     	
     	final int sum=0;
		 
        
        
     	 pointee = (EditText) myDialog.findViewById(R.id.edtpoints);
     	 
     	
         Button button = (Button) myDialog.findViewById(R.id.btngeneratecoupen);
         
         cancel=(Button) myDialog.findViewById(R.id.cancel);
         cancel.setOnClickListener(new Button.OnClickListener() 
	      { 
	        	
	       	public void onClick (View v)
	    	{ 
	        	myDialog.cancel();
	    		    			
	    	}});
         
                
            
         
         button.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
        	 
        	 
             redeem_click();
            
             
        	 
             } 
         });

         myDialog.show();
		
	}
    
    /*
    type:function
    name:getredim_details_lplanet
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:Fetching points redeemed for particular user name
    */
    public void getredim_details_lplanet()
	 { 
		
		 try {
			 		   
			 	  
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME19);
		 		
		 		request.addProperty("UserName",onlyname);
		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION19, envelope);   
		    	 Object result = (Object)envelope.getResponse();
		 	      str1=result.toString(); 
		 	      
		 	     System.out.println("Data:"+str1);
		 	    if(str1.equals(null)||str1.equals("anyType{}"))
		        {
		 		    	ss.setSessionflag(0);
		        } 
		 	    else
		 	    {
		 	    if(str1.contains("anyType{anyType=")||str1.contains("}")||str1.contains("anyType="))
		        {
		    	  str1=str1.replace("anyType{anyType=", "");
		    	  str1=str1.replace("anyType=", "");
		    	  str1=str1.replace("}", ""); 
		    	  str1=str1.trim();
		    	  System.out.println("String"+str1); 
		    	  
		        }
		        String[] arrauserData;
		        arrauserData = ((String) str1).split("; ");
		         
		        int temp2=arrauserData.length;
		        temp61=((arrauserData.length)/5);
		        redimdate=new String[temp61]; 
		        redimcoupen=new String[temp61]; 
		         
		             
		       
		         
		        for(int a=3,b=0;a<temp2;a=a+5)
		        {
		        	redimdate[b]=arrauserData[a];
		        	b++;
	 	                	
		         }
		        for(int a=4,b=0;a<temp2;a=a+5)
		        {
		        	redimcoupen[b]=arrauserData[a];
		        	b++;
	 	                	
		         }
		        
		        ss.setSessionflag(1);
		            
	        	 
	             
		        
		 	    }   
		 }
	 	    catch (Exception e)    
	        {
			 
			 System.out.println("Error msg:"+e.getMessage());
	        
	        }
	 } 
    /*
    type:function
    name:get_redeem_pnt_to_currency
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:Fetching redeem ratio from remote db
    */
    public void get_redeem_pnt_to_currency()
	 { 
		
		 try {
			 		   
			 	  
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME20);
		 		
		 		        		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION20, envelope);   
		    	 Object result = (Object)envelope.getResponse();
		 	      str1=result.toString(); 
		 	      
		 	     System.out.println("Data:"+str1);
		 	    if(str1.equals(null)||str1.equals("anyType{}"))
		        {
		 		   
		        	final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		        	alertbox.setMessage("No data found to calculate amountfor redeem...");
	    		     
		        	// set a positive/yes button and create a listener
	    			alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	    				 
	                    // click listener on the alert box
	                    public void onClick(DialogInterface arg0, int arg1) {
	                        // the button was clicked
	                        //Toast.makeText(getApplicationContext(), "OK button clicked", Toast.LENGTH_LONG).show();
	                    }
	                });
	     
	                // show it
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
		    	  System.out.println("String"+str1); 
		    	  
		        }
		        String[] arrauserData;
		        arrauserData = ((String) str1).split("; ");
		         
		        if(arrauserData[1].contains(";"))
		        {
		        	arrauserData[1]=arrauserData[1].replace(";", "");
		        }
		            
		         total_point=Integer.parseInt(arrauserData[0]);
		         total_amount=Double.parseDouble(arrauserData[1]);
		       
		         
		            
	        	 
	             
		        
		 	    }   
		 }
	 	    catch (Exception e)    
	        {
			 
			 System.out.println("Error msg:"+e.getMessage());
	        
	        }
	 } 
    /*
    type:function
    name:redim_detail_display
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:Displaying redeem points details in table format
    */
    public void redim_detail_display() 
	 {
    	int i=0;
 	
 		final TableRow tr = new TableRow(this); 
 		tr.setId(100+i);
 		tr.layout(0, 0, 0, 0); 
   
 		final TableRow tr2 = new TableRow(this); 
 		tr2.setId(100+i);
 		tr2.layout(0, 0, 0, 0);
   
   
 	
	 	final TextView labelname = new TextView(this);
	   
	   
	 	labelname.setId(200+i);
	 	labelname.setText("Coupen will expire in 7 days"); 
	   
	 	labelname.setTextColor(Color.BLACK);
	   
	 	tr.addView(labelname);
   
				
		final TextView labeldatetitle = new TextView(this);
		   
		   
		labeldatetitle.setId(200+i);
		labeldatetitle.setText("Date"); 
		   
		labeldatetitle.setTextColor(Color.BLACK);
		
		labeldatetitle.setPadding(20, 0, 0, 0);
		tr2.setPadding(0, 1, 0, 1);

   
		tr2.addView(labeldatetitle);
		   
		final TextView labelcoupentitle = new TextView(this);
		   
		   
		labelcoupentitle.setId(200+i);
		labelcoupentitle.setText("Coupen Id"); 
		   
		labelcoupentitle.setTextColor(Color.BLACK);
		
		labelcoupentitle.setPadding(20, 0, 0, 0);
		tr2.setPadding(0, 1, 0, 1);
		
		tr2.addView(labelcoupentitle);
		
   
	   showredimdetailstbl.addView(tr, new TableLayout.LayoutParams(
	           LayoutParams.WRAP_CONTENT,
	          LayoutParams.WRAP_CONTENT));
	
	   showredimdetailstbl.addView(tr2, new TableLayout.LayoutParams(
	           LayoutParams.WRAP_CONTENT,
	          LayoutParams.WRAP_CONTENT));
	   
	   
	  
 	
  if(ss.getSessionflag()==0)
  {
	  Toast.makeText(this, "No points redeemed", Toast.LENGTH_LONG).show();
	  
  }
  else
  {
 	
	 	for(int i4=0;i4<redimcoupen.length;i4++) 
	 	{
	 		
					try 
					{
					
		            final TableRow tr1 = new TableRow(this); 
		            tr1.setId(100+i);
		            tr1.layout(0, 0, 0, 0); 
	     	
		            
		         // Create a TextView to house the name of the province 
		            
		            int date1=Integer.parseInt(redimdate[i4]);
		            
		            Date date = new Date( date1* 1000L); 
		            
		            SimpleDateFormat postFormater = new SimpleDateFormat("dd-MM-yyyy"); 
		            
		            String newDateStr = postFormater.format(date); 
		               
		            
		            final TextView labeldate = new TextView(this);
		             
	              
		            labeldate.setId(200+i);
		            labeldate.setText(""+newDateStr);      
		           
		            labeldate.setTextColor(Color.BLACK);
		            
		            tr1.addView(labeldate);
		            
		            final TextView labelcoupencode = new TextView(this);
		             
		               
		            labelcoupencode.setId(200+i);
		            labelcoupencode.setText(""+redimcoupen[i4]);      
		           
		            labelcoupencode.setTextColor(Color.BLACK);
		            
		            tr1.addView(labelcoupencode);
		            	            
		            
	            
		            
	         
	            
	         
		         // Add the TableRow to the TableLayout 
	               showredimdetailstbl.addView(tr1, new TableLayout.LayoutParams(
		                     LayoutParams.WRAP_CONTENT,
		                    LayoutParams.WRAP_CONTENT));
	                             
	             
	             i++;  
		            
		           
		            
		              
					}
					catch (Exception e)    
			        {
			         
			        System.out.println("Error msg:::::::::"+e.getMessage());
			        }
			}
  }
	 }
    /*
    type:function
    name:getreward_point4
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:fetching data and if record already exists then updating else inserting teh new one
    */
    public void getreward_point4()
	 {   
		 
		 try { 
			 		  
			 	  
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME10);   
		 		
		 		request.addProperty("UserName",onlyname); 
		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION10, envelope);     
		    	 Object result = (Object)envelope.getResponse();
		 	     String  str14=result.toString();   
		 	      
		 	     System.out.println("Data:"+str14);    
		 	    sumearnedpoint=sw.getsumearnedpoint(onlyname);
		 	    sumredimedpoint=sw.getsumredimpoint(onlyname);   
		 	    sumnegativepoint=sw.getsumnegativepoint(onlyname); 
		 	   
		 	    if(Integer.parseInt(sumearnedpoint)-Integer.parseInt(sumnegativepoint)-Integer.parseInt(sumredimedpoint)>=getpoint) 
		 	    {
		 	    	
		 	    	insert_coupen_ibharati(sdate12,sdate13,coupen,""+amount);
		 	    	insertredimpoint(onlyname,getpoint,""+curtimestamp,coupen);
		 	    	sumredimedpoint=sw.getsumredimpoint(onlyname);
		 	    	
		 	    
		 	    if(str14.equals(null)||str14.equals("anyType{}"))
		        {
		 	    	
		 	    	
		 	    	sw.addrewardpoints(onlyname,0,0,0,null,null,Integer.parseInt(sumearnedpoint),Integer.parseInt(sumredimedpoint),Integer.parseInt(sumnegativepoint));
		 	    	Toast.makeText(this, "Points Assigned Succussfully",Toast.LENGTH_SHORT).show();
		 	    	
		        } 
		 	    
		 	    else 
		 	    {
		 	    	 
		 	    	
		 	    	updatereward_point_redimpnt(onlyname,Integer.parseInt(sumredimedpoint)); 	
		 	    	
		         
		 	    } 
		 	    
		 	    ss.setSessionflag(0);
		 	    
		 	    
		 	    }
		 	    else  
		 	    {  
		 	    	final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		 	    	int pntinhand=Integer.parseInt(sumearnedpoint)-Integer.parseInt(sumnegativepoint)-Integer.parseInt(sumredimedpoint);
		 	    	String strpntinhand=Integer.toString(pntinhand);
		 	    	if(strpntinhand.contains("-"))
		 	    	{
		 	    		
		 	    		alertbox.setMessage("You have less than 0 point in hand");
		 	    	}
		 	    	else
		 	    	{
		        	alertbox.setMessage("You have " +pntinhand +" point in hand");
		 	    	}
		        	// set a positive/yes button and create a listener
	    			alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	    				 
	                    // click listener on the alert box
	                    public void onClick(DialogInterface arg0, int arg1) {
	                        // the button was clicked
	                        //Toast.makeText(getApplicationContext(), "OK button clicked", Toast.LENGTH_LONG).show();
	                    }
	                });
	     
	                // show it
	                alertbox.show();
	                
	                ss.setSessionflag(1);
		 	    }
		 }
	 	    catch (Exception e)      
	        {
			 
			 System.out.println("Error msg:"+e.getMessage()); 
	        
	        }
	 } 
    
    
    public void insert_coupen_ibharati(String date,String expdate,String coupen,String amounts)
	 { 
		 try {
			 		 
			 	  
	      	 	SoapObject request = new SoapObject(NAMESPACE01, METHOD_NAME01);
	    	 	
	      	 	
	    	 	request.addProperty("ruleid","null");
	    	 	request.addProperty("name","LPlanet");
	    	 	request.addProperty("desc","LearningPlanet");
	    	 	request.addProperty("fromdate",date);
	    	 	request.addProperty("todate",expdate);
	    	 	request.addProperty("coupencode",coupen);
	    	 	request.addProperty("usespercoupen","1");
	    	 	request.addProperty("usespercustomer","1");
	    	 	request.addProperty("customergroupids","0,1,2,3");
	    	 	request.addProperty("isactive","1");
	    	 	request.addProperty("conditions_serialized",condi_ser);
	    	 	request.addProperty("action_serialized",action_ser);
	    	 	request.addProperty("stoprulesprocessing","0");
	    	 	request.addProperty("isadvanced","1");
	    	 	request.addProperty("productids","");
	    	 	request.addProperty("sortorder","0");
	    	 	request.addProperty("simpleaction","cart_fixed");
	    	 	request.addProperty("discountamt",amounts);
	    	 	request.addProperty("discountqnty","");
	    	 	request.addProperty("discountstep","0");
	    	 	request.addProperty("simplefreeshipping","0");
	    	 	request.addProperty("timesused","0");
	    	 	request.addProperty("isrss","1");
	    	 	request.addProperty("websiteids","5,3,2,4,1");
		 		
		 				 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request);
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL01);
		 	
		 	    androidHttpTransport.call(SOAP_ACTION01, envelope);
		       
		 	    Toast.makeText(this, "Coupen Added At iBharati",Toast.LENGTH_SHORT).show();
		       
		       
		 	      
		 } 
	        catch (Exception e)    
	        { 
	        
	        	System.out.println("ERROR:"+e.getMessage());
	        }  
	 }
    
    /*
    type:function
    name:insertredimpoint
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:inserting points redeemed to the remote db
    */
    public void insertredimpoint(String redimname,int redimpnt,String redimdate,String coupen_id)
	{
		
		try {
	 		 
		 	  
      	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME17);
    	 	
    	 	
    	 	request.addProperty("redimUserName",redimname); 
    	 	request.addProperty("redimPoint",redimpnt);
    	 	request.addProperty("redimDate",redimdate);
    	 	request.addProperty("coupenId",coupen_id);
    	 	
    	 		 			         		 
	 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

	 		envelope.setOutputSoapObject(request);
	 		envelope.dotNet=true; 
	 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
	 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL); 
	 	
	 	   androidHttpTransport.call(SOAP_ACTION17, envelope);
	 	    
	       
	 	   Toast.makeText(this, "Coupen generated succussfully",Toast.LENGTH_SHORT).show();
	       
	       
	 	      
	 } 
        catch (Exception e)    
        { 
        
        	System.out.println("ERROR:"+e.getMessage());
        }  
	}
    
    /*
    type:function
    name:updatereward_point_redimpnt
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:Updating redeem table
    */
    public void updatereward_point_redimpnt(String name,int pnt)
	{
		
		try {
	 		 
		 	  
      	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME16);
    	 	
    	 	
    	 	request.addProperty("UserName",name);
    	 	request.addProperty("TotalredimPoint",""+pnt);
    	 	
    	 	
    	 		 			         		 
	 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

	 		envelope.setOutputSoapObject(request);
	 		envelope.dotNet=true; 
	 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
	 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
	 	
	 	   androidHttpTransport.call(SOAP_ACTION16, envelope);
	 	   
	 	   
	       
	 	   Toast.makeText(this, "Updated Succussfully",Toast.LENGTH_SHORT).show();
	       
		
	}
		 catch (Exception e)    
	        { 
	        
	        	System.out.println("ERROR:"+e.getMessage());  
	        }  
	
	}
    
    /*
    type:function
    name:redeem_click
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:Redeeming points
    */
    public void redeem_click()
    {
    	if(pointee.getText().toString().equals(null)||pointee.getText().toString().equals(""))
   	 {
   		 Toast.makeText(this, "Please Enter Points....", Toast.LENGTH_SHORT).show();
   	 }
    	
    	else
    	{
   	 
   	 
   	 getpoint=Integer.parseInt(pointee.getText().toString());
   	 
   	 
   	 System.out.println("Points to Redim:"+getpoint);
   	 
   	 get_redeem_pnt_to_currency(); 
   	 
   	 long tim = System.currentTimeMillis();
        Random random = new Random(tim);
        long RandomNumber = random.nextInt(curtimestamp);   
         coupen=String.valueOf(RandomNumber);
        if(coupen.contains("-"))
        {
       	 coupen=coupen.replace("-", "");
       	 
        }
        
              
        amount=(getpoint*total_amount/total_point);
        
        System.out.println("Amount for redeem is..."+amount);
              
        getreward_point4();
        
        
        sumredimedpoint=sw.getsumredimpoint(onlyname) ;
        
        total_redimed.setText("Total Points Redeemed:"+sumredimedpoint);
        
                
        redimtbl.removeAllViews();  
             
        
    	         	
    	 
        myDialog.dismiss(); 
    	 
    	 if(ss.getSessionflag()==0)
    	 {
    	
    		 getredim_details_lplanet();  
    		 showredimdetailstbl.removeAllViews();
    		 redim_detail_display();
    		 
    		 back.setVisibility(View.INVISIBLE);
    	   	 earned_tbl.setVisibility(View.GONE);
    		// rewardearnedtbl.setVisibility(View.GONE); 
    		 redim_point.setVisibility(View.GONE);
    	   	 redimtbl.setVisibility(View.GONE);  
    	   	 showredimdetailstbl.setVisibility(View.VISIBLE);
    	 }
        
   	 
   	 
   	 
    	}
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

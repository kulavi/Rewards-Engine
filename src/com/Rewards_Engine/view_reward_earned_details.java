//View detailed info on reward point earned
package com.Rewards_Engine;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.Rewards_Engine.assign_reward.CallWebServiceTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class view_reward_earned_details extends Activity {
	
	
	private static final String NAMESPACE = "http://tempuri.org/";      
	//private static final String URL = "http://192.168.1.85/studentresponse/Service.asmx";
	private static final String URL = "http://184.168.111.52:82/Service.asmx";
	
	private static final String SOAP_ACTION9 = "http://tempuri.org/Getreward_enginedata";      
	private static final String METHOD_NAME9 = "Getreward_enginedata";  
	
	  

	TableLayout rewardearnedtbl_positive,rewardearnedtbl_negative;                               
	Button ok;
	RelativeLayout rl;
	
	session_webservices sw;
	Session ss;
	Session_table_values stv;
	DataBaseHelper data;
	
	String onlyname;
	String[] new_userloginto,new_userloginby,new_date_engine;
	String[] new_gift_reason,new_flag;
	String[] data_positive_name,data_positive_date,data_negative_name,data_negative_date;
	int[] data_positive_point,data_positive_rid,data_negative_point,data_negative_rid;
	int[] new_gift_reasonid,new_gift_point;
	int temp16;
	String[] data_positive_flag;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_reward_earned_details);
        
        
        /*getting data from previous activity*/
        Bundle bundle = getIntent().getExtras();
   	 	onlyname=bundle.getString("wel");
   	 	
   	 /*initializing classes*/
   	 	data=new DataBaseHelper(this);
   	 	
   	 	sw=new session_webservices();
   	 	
   	 /*inflating the views to use in this class*/
   	 	rl=(RelativeLayout) findViewById(R.id.rl);
   	 	
   	 rewardearnedtbl_positive=(TableLayout)findViewById(R.id.tbl_reward_earned_details_positive);
   	 rewardearnedtbl_negative=(TableLayout)findViewById(R.id.tbl_reward_earned_details_negative);
   	 
   	 ok=(Button) findViewById(R.id.btnok);
   	 
   	
	
   	 
   	 
    rl.setVisibility(View.GONE);
    
    /*showing progress bar till the background work of web services completes*/
   	CallWebServiceTask task = new CallWebServiceTask();
	task.applicationContext = view_reward_earned_details.this;
	task.execute();
   	 
  	 	
   	 	ok.setOnClickListener(new Button.OnClickListener()  
	  	{ 
	      	
	      	public void onClick (View v)
	  		{ 
	      		
	      		reward_earned();
	  		    			
	  	 	}});
	
	
    }
    /*Type :Class
    name:CallWebServiceTask
    return type:void
    date:12-12-2011
    purpose:Creating progress dialog*/
    public  class CallWebServiceTask extends AsyncTask<String, Integer, Void> {
		private  ProgressDialog dialog;
		protected Context applicationContext;

		@Override
		protected void onPreExecute() {
			
			System.out.println("IN PreExecute");
			this.dialog = ProgressDialog.show(applicationContext, "View Details", "Loading data...", true);
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
    /*Type :Function
    name:runback
    return type:void
    date:12-12-2011
    purpose:Performing background work*/ 
    public void runback()
    {
    	 data.deletereward_engine();
      	 getreward_engine_to(onlyname);
      	 get_positive_details();
      	 //get_negative_details();
      	 
    }
    /*Type :Function
    name:onrunback
    return type:void
    date:12-12-2011
    purpose:Updating UI when background processing completes*/
    public void onrunback()
    {
    	new_getreward_engine_display_positive();
     	rl.setVisibility(View.VISIBLE);
     	
    }
    /*Type :Function
    name:get_positive_details
    return type:void
    date:12-12-2011
    purpose:Fetching details from local db for both positive and negative reward points to show in table*/
    public void get_positive_details()
	 {
		 
    	Cursor c1=data.selectall_positive_flag();
  
		 data_positive_name=new String[c1.getCount()];
		 data_positive_date=new String[c1.getCount()];
		 data_positive_point=new int[c1.getCount()];
		 data_positive_rid=new int[c1.getCount()];
		 data_positive_flag=new String[c1.getCount()];
		 int i=0;
			while(c1.moveToNext())
			{
				String name=c1.getString(1);
				String date=c1.getString(2);  
				int point=c1.getInt(3);  
				int rid=c1.getInt(4);
				String flag=c1.getString(5);
				  
				data_positive_name[i]=name;
				data_positive_date[i]=date;
				data_positive_point[i]=point;
				data_positive_rid[i]=rid;
				data_positive_flag[i]=flag;
				
				i++;
				 
			}
			c1.close();
			
	 }
    
    
    private void reward_earned()
	{
		Intent i1 = new Intent(this,rewards_earned.class);
	    startActivity(i1);

	}
    /*Type :Function
    name:getreward_engine_to
    return type:void
    date:12-12-2011
    purpose:Fetching reward points earned details for logged in user*/
    public void getreward_engine_to(String name)
	 { 
		
		 try {
			 		    
			 	  
	     	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME9);
		 		
		 		request.addProperty("ULoginRewardTo",name);   
	 	 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true;  
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION9, envelope);   
		 		Object result = (Object)envelope.getResponse();
		 	    String str4=result.toString(); 
		 	      
		 	    System.out.println("Data:"+str4);  
		 	    if(str4.equals(null)||str4.equals("anyType{}"))
		        {  
		 		   
		        	
		        } 
		 	    else
		 	    {
		 	    if(str4.contains("anyType{anyType=")||str4.contains("}")||str4.contains("anyType="))
		        {
		    	  str4=str4.replace("anyType{anyType=", "");
		    	  str4=str4.replace("anyType=", "");
		    	  str4=str4.replace("}", ""); 
		    	  str4=str4.trim();
		    	  System.out.println("String"+str4); 
		    	  
		        }
		        String[] arrauserData;
		        arrauserData = ((String) str4).split("; ");   
		        
		        
		         
		        int temp2=arrauserData.length;   
		        temp16=((arrauserData.length)/8);
		        new_userloginto=new String[temp16]; 
		        new_userloginby=new String[temp16];
		        new_date_engine=new String[temp16];    
		        new_gift_point=new int[temp16]; 
		        new_gift_reason=new String[temp16];
		        new_gift_reasonid=new int[temp16];
		        new_flag=new String[temp16];
		             
		       
		         
		        for(int a=2,b=0;a<temp2;a=a+8) 
		        {
		        	new_userloginby[b]=arrauserData[a];
		        	b++;
	 	                	
		        }
		        
		       		        
		        for(int a=4,b=0;a<temp2;a=a+8)
		        {
		        	new_date_engine[b]=arrauserData[a];
		        	b++;
	 	                	
		        }
		        for(int a=5,b=0;a<temp2;a=a+8)
		        	
		        {
		        		        	
		        	new_gift_point[b]=Integer.parseInt(arrauserData[a]);
		        	b++;
	 	                	
		        } 
		        for(int a=6,b=0;a<temp2;a=a+8)
		        {   
		        	new_gift_reason[b]=arrauserData[a];
		        	String gift=new_gift_reason[b]; 
		        	
		        	
		        	new_gift_reasonid[b]=Integer.parseInt(gift);
		           
		        	b++;  
	 	                	
		        }  
		        for(int a=7,b=0;a<temp2;a=a+8)
		        {
		        	new_flag[b]=arrauserData[a];
		        	if(new_flag[b].contains(";"))
		        	{
		        		new_flag[b]=new_flag[b].replace(";", "");
		        	}
		        	b++;
	 	                	
		        }
		        for(int x=0;x<temp16;x++)
		        {
		        	data.Insertreward_engine(new_userloginby[x], new_date_engine[x],""+new_gift_point[x], ""+new_gift_reasonid[x], new_flag[x]);
	 	                	
		        }
		         		        
		                      
		        
		 	    }   
		 }
	 	    catch (Exception e)     
	        {  
			 
			 System.out.println("Error msg:"+e.getMessage());
	        
	        }
	 }  
    /*Type :Function
    name:new_getreward_engine_display_positive
    return type:void
    date:12-12-2011
    purpose:Displaying logs in table format*/
    public void new_getreward_engine_display_positive() 
	 {
    	
    	
    	
    	final TableRow tr = new TableRow(this); 
		tr.layout(0, 0, 0, 0); 
		 
		
		final CheckBox chk = new CheckBox(this);
       
		chk.setPadding(10, 0, 0, 0);
		tr.setPadding(0, 1, 0, 1);
		
		tr.addView(chk);
	
	
		//Create a TextView to house the name of the province 
		  final TextView labeltitlename = new TextView(this);
		  	  
		  labeltitlename.setText("Reward From"); 
		  labeltitlename.setTextColor(Color.BLACK);
		  
		  labeltitlename.setPadding(20, 0, 0, 0);
		  tr.setPadding(0, 1, 0, 1);
		  
		  tr.addView(labeltitlename);
		  
		  	
		
			//Create a TextView to house the name of the province 
			  final TextView labeldatetitle = new TextView(this);
			  
			  labeldatetitle.setText("Date"); 
			  labeldatetitle.setTextColor(Color.BLACK);
			  
			  labeldatetitle.setPadding(10, 0, 0, 0);
			  tr.setPadding(0, 1, 0, 1);
			  
			  tr.addView(labeldatetitle);
			  
			//Create a TextView to house the name of the province 
			  final TextView labeltypetitle = new TextView(this);
			          
			  
			  labeltypetitle.setText("Type Of Point"); 
			  labeltypetitle.setTextColor(Color.BLACK);
			  
			  labeltypetitle.setPadding(10, 0, 0, 0);
			  tr.setPadding(0, 1, 0, 1);
			  
			  tr.addView(labeltypetitle);  
			  
			  
			//Create a TextView to house the name of the province 
			  final TextView labelpnttitle = new TextView(this);
			          
			  
			  labelpnttitle.setText("Point"); 
			  labelpnttitle.setTextColor(Color.BLACK);
			  
			  labelpnttitle.setPadding(10, 0, 0, 0);
			  tr.setPadding(0, 1, 0, 1);
			  
			  tr.addView(labelpnttitle);
			  
			//Create a TextView to house the name of the province 
			  final TextView labelreasontitle = new TextView(this);
			           
			  
			  labelreasontitle.setText("Reward Reason"); 
			  labelreasontitle.setTextColor(Color.BLACK);
			  
			  labelreasontitle.setPadding(15, 0, 0, 0);
			  tr.setPadding(0, 1, 0, 1);
			  
			  tr.addView(labelreasontitle);
			    
			  
			  rewardearnedtbl_positive.addView(tr, new TableLayout.LayoutParams(
			          LayoutParams.WRAP_CONTENT,
			         LayoutParams.WRAP_CONTENT)); 
	
	for(int i4=0;i4<data_positive_name.length;i4++) 
	{
		
				try 
				{
				
	            final TableRow tr1 = new TableRow(this); 
	              
	            tr1.layout(0, 0, 0, 0);
	            
	            
	            final CheckBox chk1 = new CheckBox(this);
	            
	    		chk1.setPadding(10, 0, 0, 0);
	    		tr1.setPadding(0, 1, 0, 1);
	    		
	            tr1.addView(chk1);
	             
	            final String test2=data_positive_name[i4];
	            
	        	
	            
	         // Create a TextView to house the name of the province 
	            final TextView labelname = new TextView(this);
	            
	                   
	           
	            labelname.setText(test2); 
	           // System.out.println("User Name is:"+new_userloginby[i4]);
	            labelname.setTextColor(Color.BLACK);
	            
	            labelname.setPadding(10, 0, 0, 0);
	    		tr1.setPadding(0, 1, 0, 1);
	            
	            tr1.addView(labelname);
	            
	            
	            int date1=Integer.parseInt(data_positive_date[i4]);
	            
	            Date date = new Date( date1* 1000L); 
	            
	            SimpleDateFormat postFormater = new SimpleDateFormat("dd-MM-yyyy"); 
	            
	            String newDateStr = postFormater.format(date); 
	            
	            final TextView labeldate = new TextView(this);
	             
	               
	            
	            labeldate.setText(newDateStr);      
	           
	            labeldate.setTextColor(Color.BLACK);
	            
	            labeldate.setPadding(10, 0, 0, 0);
	    		tr1.setPadding(0, 1, 0, 1);
	            
	            tr1.addView(labeldate);
	            
	            
	            final TextView labeltype = new TextView(this);
	             
	             
	            
	            labeltype.setText(""+data_positive_flag[i4]);      
	           
	            labeltype.setTextColor(Color.BLACK);
	            
	            labeltype.setPadding(10, 0, 0, 0);
				tr1.setPadding(0, 1, 0, 1);
	            
	            tr1.addView(labeltype);
	            
	            final TextView labelpoint = new TextView(this);
	             
             
	            
	            labelpoint.setText(""+data_positive_point[i4]);      
	           
	            labelpoint.setTextColor(Color.BLACK);
	            
	            labelpoint.setPadding(10, 0, 0, 0);
	    		tr1.setPadding(0, 1, 0, 1);
	            
	            tr1.addView(labelpoint);
	            
	            
	            String reas=sw.getreason_for_id(""+data_positive_rid[i4]);
	           
	            final TextView labelreason = new TextView(this);
	             
	               
	            
	            labelreason.setText(reas);      
	           
	            labelreason.setTextColor(Color.BLACK);      
	            
	            labelreason.setPadding(15, 0, 0, 0);
	    		tr1.setPadding(0, 1, 0, 1);
	            
	            tr1.addView(labelreason);  
	               
               
	            
         
	         // Add the TableRow to the TableLayout 
	            
	            rewardearnedtbl_positive.addView(tr1, new TableLayout.LayoutParams(
	                     LayoutParams.WRAP_CONTENT,
	                    LayoutParams.WRAP_CONTENT));    
	            
	                   
				}
				catch (Exception e)    
		        {
		         
		        System.out.println("Error msg:::::::::"+e.getMessage());  
		        }
			}
	 }
	
   
}

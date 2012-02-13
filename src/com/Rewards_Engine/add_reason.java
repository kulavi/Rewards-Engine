//Provides user interface to add reasons for selected user type
package com.Rewards_Engine;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.Rewards_Engine.assign_authority.MyOnutypepointSelectedListener;
import com.Rewards_Engine.reward_balance.CallWebServiceTask;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class add_reason extends Activity{
	
	
	private static final String NAMESPACE = "http://tempuri.org/";      
	//private static final String URL = "http://192.168.1.85/studentresponse/Service.asmx";
	private static final String URL = "http://184.168.111.52:82/Service.asmx";
	
	private static final String SOAP_ACTION = "http://tempuri.org/InsertReward_reason";      
	private static final String METHOD_NAME = "InsertReward_reason";  
	
	
	String rewardreason,utype_addreason,utype12;
	
	DataBaseHelper data;
	static session_webservices sw;
	static Session ss;
	static Spinner user_type;
	static String[] user_type1;
	static String[] user_point1; 
	static EditText edt_resaon;
	static Button btn_add;
	static Button back;
	static Button logout;
	static LinearLayout ll;
	
	
	
	int position;
	
	static ArrayAdapter<CharSequence> usertypeadapter;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addreason);
        
        /*initializing classes*/
        data=new DataBaseHelper(this);
        sw=new session_webservices();
        
        Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
        
        /*inflating the views to use in this class*/
        ll=(LinearLayout) findViewById(R.id.ll);
        
        
        user_type=(Spinner) findViewById(R.id.sprutype);
        edt_resaon=(EditText) findViewById(R.id.editreason);
        btn_add=(Button) findViewById(R.id.btnadd);
        
        
        back=(Button) findViewById(R.id.btnback);
    	logout=(Button) findViewById(R.id.btnlogout);
    	 
    	usertypeadapter=new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item);
        user_type.setOnItemSelectedListener(new MyOnItemSelectedListenerutype());
    	 
    	 
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
        
        
        
        
		
		
		btn_add.setOnClickListener(new Button.OnClickListener() {        	 
            public void onClick(View v) {
            	
            	checkvalidate();
            	
            	
            	
                }
            });
		
		ll.setVisibility(View.INVISIBLE);
		
		/*showing progress bar till the background work of web services completes*/
		CallWebServiceTask task = new CallWebServiceTask();
		task.applicationContext = add_reason.this;
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
    }
    /*Type :Function
    name:onrunback
    return type:void
    date:12-12-2011
    purpose:Updating UI when background processing completes*/
    public static void onrunback()
    {
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
			this.dialog = ProgressDialog.show(applicationContext, "Add Reasons", "Loading data...", true);
		}
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("IN BACKGROUND");
			add_reason.runback();
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
			add_reason.onrunback();
					
			 
		}

		

		
	}
    /*Type :Function
    name:checkvalidate
    return type:void
    date:12-12-2011
    purpose:Performing Validations*/
    public void checkvalidate()
    {
    	if(edt_resaon.getText().toString().equals(null)||edt_resaon.getText().toString().equals(""))
    	{
    		Toast.makeText(this, "Please Enter Reason", Toast.LENGTH_LONG).show();
    	}
    	else
    	{
    	addreason();
    	}
    }
    
    
    
    public class MyOnItemSelectedListenerutype implements OnItemSelectedListener {
    	 
	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	     
	       utype12=parent.getItemAtPosition(pos).toString();  
	     	              
	       data.deletereason();
	      
	      
	      
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
    purpose:Add reasons for a particular user types
    */
	
	public void addreason()
	 { 
		 try {
			 		 
			    rewardreason=edt_resaon.getText().toString();
			    
			    
			    
			 	  
	      	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
	    	 	
	      	 	
	    	 	request.addProperty("reason",rewardreason);
	    	 	request.addProperty("UserType",utype12);
		 		
		 				 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request);
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION, envelope);
		       
		 		Toast.makeText(this, "Reason Added Successfully",Toast.LENGTH_SHORT).show();
		       
		       
		 	      
		 } 
	        catch (Exception e)    
	        { 
	        
	        	System.out.println("ERROR:"+e.getMessage());
	        }  
	 }
	/*
    type:function
    name:fillusertype
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
		user_type.setAdapter(usertypeadapter);
		
	}
	
	public class MyOnutypepointSelectedListener implements OnItemSelectedListener {

	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {

	    	String strutype=parent.getItemAtPosition(pos).toString();
	    	    	
	    	position=pos;
	       
	    	 
	            
	    }
   
	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing.
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

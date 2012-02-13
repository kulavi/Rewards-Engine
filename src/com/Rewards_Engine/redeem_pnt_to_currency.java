//provides screen to define redeem ratio taht is redeem point to currency
package com.Rewards_Engine;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;  

public class redeem_pnt_to_currency extends Activity{
	
	private static final String NAMESPACE = "http://tempuri.org/";      
	//private static final String URL = "http://192.168.1.85/studentresponse/Service.asmx";
	private static final String URL = "http://184.168.111.52:82/Service.asmx";
	
	
	private static final String SOAP_ACTION1 = "http://tempuri.org/insert_redeem_pnt_to_currency_data";      
	private static final String METHOD_NAME1= "insert_redeem_pnt_to_currency_data"; 
	
	private static final String SOAP_ACTION2 = "http://tempuri.org/get_redeem_pnt_to_currency";      
	private static final String METHOD_NAME2= "get_redeem_pnt_to_currency"; 
	
	private static final String SOAP_ACTION3 = "http://tempuri.org/Updateredeemratio";      
	private static final String METHOD_NAME3= "Updateredeemratio"; 
	
	EditText edtpoint,edtamt;
	Button back,logout;
	Button btnok;
	
	 String strpoint;
	 String stramt;  
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redeem_pnt_to_currency);
        
        Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
        
        /*inflating the views to use in this class*/
        edtpoint=(EditText) findViewById(R.id.editpoint);        
        edtamt=(EditText) findViewById(R.id.editcurrency);
        
        btnok=(Button) findViewById(R.id.btnok);
        
        back=(Button) findViewById(R.id.btnback);
   	 	logout=(Button) findViewById(R.id.btnlogout);
   	 
   	 
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
        
        
        btnok.setOnClickListener(new Button.OnClickListener() 
	  	{ 
	      	
	      	

			public void onClick (View v)
	  		{ 
	      		 strpoint=edtpoint.getText().toString();
	      		 stramt=edtamt.getText().toString();
	      		
	      		getredeem_ratio();
	      		edtpoint.setText("");
	      		edtamt.setText("");
	      		
	  		    			
	  		}}); 
        
        
        
    }
    /*
    type:function
    name:getredeem_ratio
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:Fetching the redeem ratio if it exista then updating it otherwise inserting the new ratio
    */
    public void getredeem_ratio()
	 { 
		
		 try {
			 		  
			 	  
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME2);
		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request);   
		 		envelope.dotNet=true;  
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION2, envelope);   
		    	 Object result = (Object)envelope.getResponse();
		 	      String str1=result.toString(); 
		 	      
		 	     System.out.println("Data:"+str1);
		 	    if(str1.equals(null)||str1.equals("anyType{}"))
		        {
		 		   
		 	    	insert_redeem_pnt_to_currency(strpoint, stramt);
		        } 
		 	    else
		 	    {
		 	    
		 	    	updateratio(strpoint, stramt);
		        
		 	    }   
		 }
	 	    catch (Exception e)    
	        {  
			 
			 System.out.println("Error msg:"+e.getMessage());
	        
	        }
	 }
    /*
    type:function
    name:insert_redeem_pnt_to_currency
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:inserting the new ratio to remote db
    */
    public void insert_redeem_pnt_to_currency(String point,String amount_in_doller)
	 { 
		 try {
			 		 
			 	  
	      	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME1);
	    	 	
	    	 	
	    	 	request.addProperty("Point",point);
	    	 	request.addProperty("Amount",amount_in_doller);
	    	 	 				 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request);
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 	    androidHttpTransport.call(SOAP_ACTION1, envelope);
		       
		 	    Toast.makeText(this, "Data Added Successfully",Toast.LENGTH_SHORT).show();
		       
		 	      
		 }   
	        catch (Exception e)    
	        { 
	       
	        	System.out.println("ERROR:"+e.getMessage());
	        }  
	 }
    /*
    type:function
    name:updateratio
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:updating the ratio to remote db
    */
    public void updateratio(String point,String amount_in_doller)
	 { 
		 try {
			 		 
			 	  
	      	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME3);
	    	 	
	    	 	
	    	 	request.addProperty("strpoint",point);
	    	 	request.addProperty("strcurrency",amount_in_doller);
	    	 	 				 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request);
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 	    androidHttpTransport.call(SOAP_ACTION3, envelope);
		       
		 	    Toast.makeText(this, "Data Updated Successfully",Toast.LENGTH_SHORT).show();
		       
		 	      
		 }   
	        catch (Exception e)    
	        { 
	       
	        	System.out.println("ERROR:"+e.getMessage());
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

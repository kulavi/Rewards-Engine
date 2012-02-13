//provides screen to view the reward point balance used and available for distribution 
package com.Rewards_Engine;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class reward_balance extends Activity {
	
	private static final String NAMESPACE = "http://tempuri.org/";      
	//private static final String URL = "http://192.168.1.85/studentresponse/Service.asmx";
	private static final String URL = "http://184.168.111.52:82/Service.asmx";
	
	
	
	static session_webservices sw;
	static Session ss;
	DataBaseHelper data;
	
	static TextView wel;
	static TextView usedpnt;
	static TextView availpnt;
	Button back,logout;
	
	static LinearLayout ll;
	static int used_point1;
	static int yearly_point;
	static int avail_point1;
	static int earnedpoint1;
	static int redimedpoint1;
	static String onlyname;
	String str1;

	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rewardbalance);
        
        /*initializing classes*/
          data=new DataBaseHelper(this);
        sw=new session_webservices();
        
        Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
        /*inflating the views to use in this class*/
        ll=(LinearLayout) findViewById(R.id.ll);
        wel=(TextView) findViewById(R.id.welcomes); 
    	 usedpnt = (TextView) findViewById(R.id.txtusedpnt);
    	 availpnt = (TextView) findViewById(R.id.txtavailpnt);
    	 
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
        
        onlyname=ss.getSessionLoginId();
        ll.setVisibility(View.INVISIBLE);
        
        /*showing progress bar till the background work of web services completes*/
        CallWebServiceTask task = new CallWebServiceTask();
		task.applicationContext = reward_balance.this;
		task.execute();
		
			
        
        
        
    }
    /*Type :Function
    name:runback
    return type:void
    date:12-12-2011
    purpose:Performing background work*/ 
    public static void runback()
    {
    	sw.getreward_point(onlyname);
        
        
        used_point1=ss.getSessionused_point();;
        
        
        avail_point1=ss.getSessionavail_point();
        
        
        yearly_point=ss.getSessionyearly_point();
        
        redimedpoint1=ss.getSessionredim_point();
        earnedpoint1=ss.getSessionearned_point();
        sw.getreward_point(onlyname);
        
    }
    /*Type :Function
    name:onrunback
    return type:void
    date:12-12-2011
    purpose:Updating UI when background processing completes*/
    public static void onrunback()
    {
    	
    	wel.setText("Welcome "+onlyname);   
        usedpnt.setText("Used Point:"+used_point1);  
        availpnt.setText("Available Point For Distribution:"+avail_point1);
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
			this.dialog = ProgressDialog.show(applicationContext, "Reward Balance", "Loading data...", true);
		}
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("IN BACKGROUND");
			reward_balance.runback();
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
			reward_balance.onrunback();
					
			 
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

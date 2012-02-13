//provides screen to assign reward point
package com.Rewards_Engine;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.Rewards_Engine.assign_authority.CallWebServiceTask;
import com.Rewards_Engine.assign_authority.CallWebServiceTask1;
import com.Rewards_Engine.assign_authority.MyOnunamepointSelectedListener;
import com.Rewards_Engine.assign_authority.MyOnutypepointSelectedListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.DialogPreference;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class assign_reward extends Activity{
	
	
	private static final String NAMESPACE = "http://tempuri.org/";      
	//private static final String URL = "http://192.168.1.85/studentresponse/Service.asmx";
	private static final String URL = "http://184.168.111.52:82/Service.asmx";
	
	
	private static final String SOAP_ACTION1 = "http://tempuri.org/GetUserTypeInfo";      
	private static final String METHOD_NAME1 = "GetUserTypeInfo";   
	
	
	private static final String SOAP_ACTION3 = "http://tempuri.org/GetUserTypeInfo1";      
	private static final String METHOD_NAME3 = "GetUserTypeInfo1"; 
	
	
	private static final String SOAP_ACTION7 = "http://tempuri.org/Insertreward_engine";      
	private static final String METHOD_NAME7 = "Insertreward_engine"; 
	
	
	private static final String SOAP_ACTION10 = "http://tempuri.org/GetReward_Point";      
	private static final String METHOD_NAME10 = "GetReward_Point"; 
	
	
	private static final String SOAP_ACTION11 = "http://tempuri.org/Updatereward_Point";         
	private static final String METHOD_NAME11 = "Updatereward_Point";
	
	private static final String SOAP_ACTION12 = "http://tempuri.org/GetORG_AND_PROJECT_Name";      
	private static final String METHOD_NAME12 = "GetORG_AND_PROJECT_Name";   
	
	
	
	
	
	 session_webservices sw;   
	 Session ss;
	DataBaseHelper data;  
	
	
	ArrayAdapter<CharSequence> unameadapter,projectadapter,orgadapter;
	ArrayAdapter<CharSequence> usertypeadapter;
	ArrayAdapter<CharSequence> rewardreasonadapter;
	Spinner user_type;
	Spinner spr_org;
	Spinner spr_project;
	Spinner reason_reward;
	EditText edtpoint;
	protected TextView myTextField;
	Button assign, back,logout,user_list;
	TableLayout newrewrdtbl;
	TableLayout nametbl;
	ProgressDialog dialog;
	protected static boolean[] _selections; 
	protected static RadioGroup radiogroup; 
	RadioButton r1,r2;
	RelativeLayout rl;
	AlertDialog.Builder alertbox;
	
	String flag_check;
	String j;
	String user_login_name;
	String onlyname;
	String str11;
	String sumearnedpoint,sumredimedpoint,sumnegativepoint;
	String sdate;   
	String sdate1;  
	String sdate12;
	String sdate13;
	String utype12;
	String[] org_array,pro_array;
	String[] userlogin;   
	String[] reason;
	String[] fname;
	String[] lname;
	String[] selected_name,selected_login;
	protected static CharSequence[] complete_name;  
	int	curtimestamp;
	int nexttimestamp, used_point1, yearly_point, reasonid1, avail_point1;
	int temp6;
	int position, redim_point1, earned_point1;
	String str1;
	String str12;
	String[] arrreason;
	String[] user_point1;
	String[] user_type1;
	int[] reasonid;
	String used_point,avail_point,yearly_point1;
	int temp11,position1,position2,temp8;
	int maximum,count,increment,count1=0;
	int project_position,org_position;
	public int size;
	public String strutype;
	String flag_alert; 
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assign_reward);  
        
        /*initializing classes*/
        data=new DataBaseHelper(this);
        sw=new session_webservices();
        
        
        Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
        /*inflating the views to use in this class*/
        alertbox = new AlertDialog.Builder(this);
               
        rl=(RelativeLayout) findViewById(R.id.rl);
        
        
        back=(Button) findViewById(R.id.btnback);
        logout=(Button) findViewById(R.id.btnlogout);
     	 
     	newrewrdtbl=(TableLayout)findViewById(R.id.new_reward);
        nametbl=(TableLayout)findViewById(R.id.name_table);  
        
        
        myTextField=(TextView) findViewById(R.id.text);  
        
        reason_reward=(Spinner) findViewById(R.id.reason_reward );   
       
        
        edtpoint=(EditText) findViewById(R.id.editpoint);
        assign=(Button) findViewById(R.id.btnassign);
        user_list=(Button) findViewById(R.id.user_list);
        user_type=(Spinner) findViewById(R.id.user_type);  
        
        
        radiogroup = (RadioGroup) findViewById(R.id.Group1);

		r1=(RadioButton)findViewById(R.id.positive);
		r2=(RadioButton)findViewById(R.id.negative);

        
        
       
        
        usertypeadapter = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item);
        user_type.setOnItemSelectedListener(new MyOnutypepointSelectedListener());
        user_type.setHorizontalFadingEdgeEnabled(false);             
        unameadapter = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item);
        
       
        rewardreasonadapter = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item);
        reason_reward.setOnItemSelectedListener(new MyOnItemSelectedListener());
        reason_reward.setHorizontalFadingEdgeEnabled(false);    
        
        rl.setVisibility(View.INVISIBLE);  
        
        /*showing progress bar till the background work of web services completes*/
        CallWebServiceTask task = new CallWebServiceTask();
		task.applicationContext = assign_reward.this;
		task.execute();
        
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
    	
      	        	 
            
    	
    	assign.setOnClickListener(new Button.OnClickListener() {        	 
            public void onClick(View v) {
            	
            	checkvalidate();
            	
            	 
                } 
            });
    	
    	user_list.setOnClickListener(new Button.OnClickListener() {        	 
            public void onClick(View v) {
            	
            	
            	function();  
            	 
                } 
            });
    	
    	
    	
    	
        
         
    }
    
    /*Type :Class
    name:CallWebServiceTask2
    return type:void
    date:12-12-2011
    purpose:Creating progress dialog*/
    public class CallWebServiceTask2 extends AsyncTask<String, String, String> {
    	
    	
		private ProgressDialog dialog;
		protected Context applicationContext;
		

		@Override
		protected void onPreExecute() {
			
			System.out.println("IN PreExecute");
			this.dialog = ProgressDialog.show(applicationContext, "Assign Reward", "Assigning Rewards Please Wait...", true);
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			
				int strpoint=Integer.parseInt(edtpoint.getText().toString());
				int i2=reasonid[position1];
				String s=""+curtimestamp; 	
				
				
								 
				for(int i=0;i<selected_name.length;i++)   
				{ 
					
				if(r1.isChecked())
				{
			    String pos_or_neg=r1.getText().toString();
				System.out.println("User Login name is..."+selected_login[i]);
				addrewardengine(selected_login[i], onlyname,s, strpoint, i2,pos_or_neg); 
				
				sw.getreward_point(onlyname);
		    	
		    	
		    	used_point1=ss.getSessionused_point(); 
		        
		        
		        avail_point1=ss.getSessionavail_point();   
		        
		        
		        yearly_point=ss.getSessionyearly_point();
		        
		        redim_point1=ss.getSessionredim_point();
		        earned_point1=ss.getSessionearned_point();      
		        
		         
				
		       			
		           
				int usedpoint3=used_point1+strpoint;    
				int availpoint3=yearly_point-usedpoint3; 
				   
				updaterewardpoints(onlyname, usedpoint3, availpoint3); 
				
				getreward_point3(selected_login[i]);   
				//flag_check="1";  
				}
				if(r2.isChecked())  
				{
					String pos_or_neg=r2.getText().toString();
					System.out.println("User Login name is..."+selected_login[i]);
					
					addrewardengine(selected_login[i], onlyname,s, strpoint, i2,pos_or_neg); 
								 			
					getreward_point3(selected_login[i]);
					
				}   
				publishProgress(selected_login[i]);   
				
				}  
				
				
				    
				
				           
			   
			return "complete!!" ;   
			
			
		}
		
		@Override
		protected void onProgressUpdate(String... progress) {
	        //this runs in UI thread so its safe to modify the UI
			System.out.println("IN ProgressUpdate"+progress[0]);
	        myTextField.setText("Reward Assigned to " + progress[0]);
	    }
		@Override
		protected void onPostExecute(String unused) { 
			
			
			this.dialog.cancel();
			System.out.println("IN PostExecute");
			
			
			done();
			
			 
		}

		

		
	}
    /*Type :Function
    name:done
    return type:void
    date:12-12-2011
    purpose:When work finishes showing some notifications*/
    public void done()
    {
    	edtpoint.setText("");
    	
    	
    	Toast.makeText(this, "Points Assigned to selected users successfully",Toast.LENGTH_LONG).show();
    }
    
    
    /*Type :Function
    name:function
    return type:void
    date:12-12-2011
    purpose:Creating and showing alert dialog*/
    
    public void function()
    {
    	final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
    	alertbox.setTitle("User List");
    	alertbox.setMultiChoiceItems( complete_name, _selections, new DialogSelectionClickHandler() );
    	alertbox.setPositiveButton( "OK", new DialogButtonClickHandler() );
		alertbox.show();
    	 
    }   
    
    public class DialogSelectionClickHandler implements DialogInterface.OnMultiChoiceClickListener
	{
		public void onClick( DialogInterface dialog, int clicked, boolean selected )
		{
			Log.i( "ME", complete_name[ clicked ] + " selected: " + selected );
			
			if(selected==true)
			{
				count1++;
			}
			else
			{
				count1--; 
			}
			
			
			
			
		}

		
	}
	

	public class DialogButtonClickHandler implements DialogInterface.OnClickListener
	{
		public void onClick( DialogInterface dialog, int clicked )
		{
						
			switch( clicked )
			{
				case DialogInterface.BUTTON_POSITIVE:
					printSelectedPlanets();
					break;
			}
			
		}
	}
	 /*Type :Function
    name:printSelectedPlanets
    return type:void
    date:12-12-2011
    purpose:Listing selected user names in an array*/
	protected void printSelectedPlanets()
	{
		
			
		selected_name=new String[count1];
		selected_login=new String[count1];
			int j=0;
			int k;
			for(k=0; k<complete_name.length;k++)
			{
			if (_selections[k]==true)
			{
				
				selected_login[j]=userlogin[k];
				selected_name[j]=(String) complete_name[k];
				_selections[k]=false;
				//System.out.println("Selected Users are..."+selected_name[j]);
				j++;
			}
			else  
			{
				Log.i( "ME", complete_name[ k ] + " selected: " + _selections[k] );
				
			}
			}   
			count1=0;  
			
		
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
			this.dialog = ProgressDialog.show(applicationContext, "Assign Reward", "Loading data...", true);
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
	public  void runback()
	{
		onlyname=ss.getSessionLoginId();
              
        
               
        getreward_point2();//Checks Whether logged in User has authority to assign reward
                       
        sw.point(); //get the user types to fill the user type spinner
         
        user_type1=ss.getSessionuser_type1();
        user_point1=ss.getSessionuser_point();
           
        
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
    	System.out.println("Month is:"+month);
    	int year1 = cal1.get(Calendar.YEAR);   
    	sdate13=(+ year1 + "-" + (month1 + 1) + "-" + (day12));
    	 	
	}
	 /*Type :Function
    name:onrunback
    return type:void
    date:12-12-2011
    purpose:Updating UI when background processing completes*/
	public void onrunback()
    {
		System.out.println("FLAG IS="+flag_alert);
		
		if(flag_alert.equals("0"))
		{
			
			newrewrdtbl.setVisibility(View.GONE); 
        	
        	nametbl.setVisibility(View.GONE);
        	
			alertbox.setMessage(onlyname+" you are not authorized to assign reward");
		   	alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
				 
	            
	            public void onClick(DialogInterface arg0, int arg1) {
	            	
	            	back();
	                
	            }
	        });
	           
	        alertbox.show();
		}
		else
		{
    	 fillusertype();//fills the user types spinner
    	 rl.setVisibility(View.VISIBLE);
		}
    }

	/*Type :Function
    name:checkvalidate
    return type:void
    date:12-12-2011
    purpose:Performing Validations*/
    public void checkvalidate()
    {
    	if(edtpoint.getText().toString().equals(null)||edtpoint.getText().toString().equals("")||reason_reward.getSelectedItem().toString().equals(null)||reason_reward.getSelectedItem().toString().equals(""))
    	{
    		Toast.makeText(this, "Please Enter All Values", Toast.LENGTH_LONG).show();
    	}
    	
    	else
    	{
    		CallWebServiceTask2 task = new CallWebServiceTask2();
			task.applicationContext = assign_reward.this;
			task.execute();
    		
    	}
    	
    } 
    
       
    
    /*
    type:function
    name:addrewardengine
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:inserting record to the remote db 
    */ 
      
    public void addrewardengine(String to,String by,String ddate,int pnt,int reason1,String type)
	 { 
		 try {
			 		 
			 	  
	      	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME7);
	    	 	
	    	 	
	    	 	request.addProperty("UserLogin_to",to);
	    	 	request.addProperty("UserLogin_By",by);
	    	 	request.addProperty("Status","Rewarded");
	    	 	request.addProperty("TimeStamp",ddate);
	    	 	request.addProperty("GifiPoint",pnt);
	    	 	request.addProperty("GiftResonID",reason1);
	    	 	request.addProperty("TYPEOFPOINT",type);
	    	 	
	    	 		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request);
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD; 
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);   
		 	
		 	   androidHttpTransport.call(SOAP_ACTION7, envelope);  
		 	   
		 	   data.Insertreward_engine_out(to,ddate, pnt, reason1); 
		       
		 	  
		       
		       
		 	      
		 } 
	        catch (Exception e)    
	        { 
	       
	        	System.out.println("ERROR:"+e.getMessage());
	        }  
	 }
    /*
    type:function
    name:updaterewardpoints
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:updating record in to the remote db if it already exists
    */ 
      
    public void updaterewardpoints(String uname,int usedpoint,int availpoint)
	 { 
		 try {
			 		 
			 	  
	      	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME11);
	    	 	
	    	 	
	    	 	request.addProperty("UserName",uname);
	    	 	request.addProperty("UsedPoint",usedpoint);
	    	 	request.addProperty("AvaliablePoint",availpoint);    
	    	 	   		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request);
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 	    androidHttpTransport.call(SOAP_ACTION11, envelope);  
		       
		 	 
		       
		 	      
		 } 
	        catch (Exception e)    
	        { 
	       
	        	System.out.println("ERROR:"+e.getMessage());
	        }  
	 }
    /*
    type:function
    name:getreward_point3
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:fetching reward points
    */ 
    
    public void getreward_point3(String names)  
	 {   
		 
		 try {   
			 		  
			 	  
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME10);   
		 		
		 		request.addProperty("UserName",names); 
		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION10, envelope);     
		 		Object result = (Object)envelope.getResponse();
		 	    String   str12=result.toString(); 
		 	      
		 	     System.out.println("Data:"+str12);
		 	     
		 	   
		 	    sumearnedpoint=sw.getsumearnedpoint(names); 
		 	    sumredimedpoint=sw.getsumredimpoint(names);
		 	    sumnegativepoint=sw.getsumnegativepoint(names);    
		 	    
		 	    System.out.println("SUM OF NEGATIVE POINTS IS....."+sumnegativepoint);
		 	   
		 	    if(str12.equals(null)||str12.equals("anyType{}"))
		        {
		 	    
		 	    	sw.addrewardpoints(names,0,0,0,null,null,Integer.parseInt(sumearnedpoint),Integer.parseInt(sumredimedpoint),Integer.parseInt(sumnegativepoint));
		 	      	
		        } 
		 	    else 
		 	    {
		 	    	 if(str12.contains("anyType{anyType=")||str12.contains("}")||str12.contains("anyType="))
				        {
				    	  str12=str12.replace("anyType{anyType=", "");
				    	  str12=str12.replace("anyType=", "");
				    	  str12=str12.replace("}", ""); 
				    	  str12=str12.trim();
				    	  System.out.println("String"+str12);  
				    	  
				        }
		 	    	String[] arrauserData;
			        arrauserData = ((String) str12).split("; ");
			       	 	    	 
		 	    	if(arrauserData[1].equals("0"))
		 	    	{	
		 	    		  
		 	    		if(r1.isChecked())  
		 	    		{
		 	    		sw.updatereward_point_earnedpnt(names,Integer.parseInt(sumearnedpoint));
		 	    		}
		 	    		if(r2.isChecked())
		 	    		{
		 	    			
		 	    			sw.updatereward_point_negativepnt(names,Integer.parseInt(sumnegativepoint));
		 	    			//System.out.println("NEGATIVE BUTTON CLICKED");
		 	    		}
		 	    	}
		 	    	
		 	    	else
		 	    	{
		 	    		sw.addrewardpoints(names,0,0,0,null,null,Integer.parseInt(sumearnedpoint),Integer.parseInt(sumredimedpoint),Integer.parseInt(sumnegativepoint));
			 	    		    	  
		 	    	
		 	    	} 
		 	    	
		        
		 	    }      
		 }
	 	    catch (Exception e)      
	        {
			 
			 System.out.println("Error msg:"+e.getMessage()); 
	        
	        }
	 } 
    
    
    
    
    
    
    
    
    /*
    type:function
    name:getuname
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:fetching user name for selected user types
    */ 
    
    public void getuname(String Type)
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
		 	    str11=result.toString(); 
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
			 	    str11=result.toString(); 
			 		
			 	}
		 	      
		 	     System.out.println("Data:"+str11);
		 	    if(str11.equals(null)||str11.equals("anyType{}"))
		        {
		 		   
		        	
		        	
		        } 
		 	    else
		 	    {
		 	    if(str11.contains("anyType{anyType=")||str1.contains("}")||str1.contains("anyType="))
		        {
		    	  str11=str11.replace("anyType{anyType=", "");
		    	  str11=str11.replace("anyType=", "");
		    	  str11=str11.replace("}", ""); 
		    	  str11=str11.trim();
		    	  System.out.println("String"+str1); 
		    	  
		        }
		        String[] arrauserData;
		        arrauserData = ((String) str11).split("; ");
		         
		        int temp2=arrauserData.length;
		        temp6=((arrauserData.length)/3);
		        userlogin=new String[temp6]; 
		        fname=new String[temp6]; 
		        lname=new String[temp6]; 
		        complete_name=new String[temp6];    
		       
		         
		        for(int a=0,b=0;a<temp2;a=a+3)
		        {
		        	userlogin[b]=arrauserData[a];
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
		        
		        
		        
		        
		        
		        for(int x=0;x<temp6;x++) 
		        {
		               	
		    	   complete_name[x]=fname[x]+" "+lname[x];
		        }
		        
		    
	             
		        
		 	    }   
		 }
	 	    catch (Exception e)    
	        {
			 
			 System.out.println("Error msg:"+e.getMessage());
	        
	        }
	 }  
    
    
    
    
    public class MyOnItemSelectedListener implements OnItemSelectedListener {
		 
	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	       
	       position1=pos;
	      
	      
	    }

	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing.
	    }
	} 
    
   
    
    public class MyOnutypepointSelectedListener implements OnItemSelectedListener {

	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {

	    	strutype=parent.getItemAtPosition(pos).toString();
	    	 	
	    	position=pos; 
	    	
	    	
	    	
	    	unameadapter.clear();
	    	rewardreasonadapter.clear();
	    	CallWebServiceTask1 task = new CallWebServiceTask1();
			task.applicationContext = assign_reward.this;
			task.execute();
	          	
	            
 	    }
   
	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing.
	    }
	}
    
    public class MyOnprojectSelectedListener implements OnItemSelectedListener {

	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {

	    		    	 	
	    	project_position=pos; 
	    	
	    	         	
	            
 	    }
   
	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing.
	    }
	}
    public class MyOnorgSelectedListener implements OnItemSelectedListener {

	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {

	    		    	 	
	    	org_position=pos; 
	    	
	    	         	
	            
 	    }
   
	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing.
	    }
	}
    
    /*Type :Function
    name:utype_selected_runback
    return type:void
    date:12-12-2011
    purpose:Performing background work*/ 
    public  void utype_selected_runback()
    {
    	getuname(strutype);
    	size=complete_name.length;
    	_selections =  new boolean[size];
    	sw.getreasonfromserver(strutype);
    	
    }
    /*Type :Class
    name:CallWebServiceTask1
    return type:void
    date:12-12-2011
    purpose:Creating progress dialog*/
    public  class CallWebServiceTask1 extends AsyncTask<String, String, Void> {
		private  ProgressDialog dialog;
		protected Context applicationContext;

		@Override
		protected void onPreExecute() {
			
			System.out.println("IN PreExecute");
			this.dialog = ProgressDialog.show(applicationContext, "Assign Reward", "Loading names...", true);
		}
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("IN BACKGROUND");
			utype_selected_runback();
			//return flag1;
			return null ;
			
			
		}
		@Override
		protected void onProgressUpdate(String... progress) {
	        //this runs in UI thread so its safe to modify the UI
	        myTextField.setText("finished call " + progress);
	    }
		@Override
		protected void onPostExecute(Void unused) {
			
			
			this.dialog.cancel();
			System.out.println("IN PostExecute");
			onutype_selected_runback();
					
			 
		}

		

		
	}
    /*
    type:function
    name:fillreason
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:adding reason data to the adapter
    */
    public void fillreason()
    {
    	
    	
    	
    	if(ss.getSessionflag1()==0)
    	{
    		Toast.makeText( sw, "No reward reasons for selected user type", Toast.LENGTH_LONG).show();
    	}
    	else
    	{
    		
    		reasonid=ss.getSessionReasonid();
        	reason=ss.getSessionReason();
    	for(int x=0;x<reason.length;x++) 
        {
         		        	 
        	rewardreasonadapter.add(reason[x]);  
            
        } 
        reason_reward.setAdapter(rewardreasonadapter);  
        
    	}
    }
    /*Type :Function
    name:onutype_selected_runback
    return type:void
    date:12-12-2011
    purpose:Updating UI when background processing completes*/
    public void onutype_selected_runback() {
    	
   
		fillreason();
		
	}


    /*
    type:function
    name:fillusertype
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:adding user name data to the adapter
    */

	public  void fillusertype()
	{
    for(int x=0;x<user_type1.length;x++) 
    {
           	
    	usertypeadapter.add(user_type1[x]);
    }
    user_type.setAdapter(usertypeadapter);
     
	}
	/*
    type:function
    name:getreward_point2
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:fetching reward points for user name
    */ 
    public void getreward_point2()
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
		 	      str1=result.toString(); 
		 	      
		 	     System.out.println("Data:"+str1);
		 	    if(str1.equals(null)||str1.equals("anyType{}"))
		        {
		 	    	
		 	    	flag_alert="0";
		 	    	
		 	    	        	
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
				     int temp=((arrauserData.length)/5);
				     
				     
				     int used_point2=Integer.parseInt(arrauserData[0]);
			         int yearly_point2=Integer.parseInt(arrauserData[1]); 
			         int avail_point2=Integer.parseInt(arrauserData[2]);   
			         int earnedpoint2=Integer.parseInt(arrauserData[3]);
			         
			         if(arrauserData[4].contains(";"))
			         {
			        	 arrauserData[4]=arrauserData[4].replace(";", "");
			         }
			         int redimedpoint2=Integer.parseInt(arrauserData[4]);
			         
			         
			         if(yearly_point2==0||avail_point2==0)
			         {
			        	 
			        	 flag_alert="0";
			        	 
			         }
				        
			         else
			         {
			        	 flag_alert="1";
			        	 newrewrdtbl.setVisibility(View.VISIBLE); 
	            	
			        	 nametbl.setVisibility(View.VISIBLE);
			         } 
		 	    	
		        
		 	    }    
		 }
	 	    catch (Exception e)    
	        {
			 
			 System.out.println("Error msg:"+e.getMessage()); 
	        
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

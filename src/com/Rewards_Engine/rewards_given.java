//Dispalys reward point given by logged in user
package com.Rewards_Engine;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.Rewards_Engine.assign_authority.CallWebServiceTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class rewards_given extends Activity implements OnItemClickListener{
	
	private static final String NAMESPACE = "http://tempuri.org/";      
	//private static final String URL = "http://192.168.1.85/studentresponse/Service.asmx";
	private static final String URL = "http://184.168.111.52:82/Service.asmx";
	
	
	private static final String SOAP_ACTION8 = "http://tempuri.org/Getreward_engine";      
	private static final String METHOD_NAME8 = "Getreward_engine";
	
	
	session_webservices sw;  
	Session ss;
	DataBaseHelper data; 
	
	protected static RadioGroup radiogroup; 
	RadioButton r1,r2;
	ListView lv1;  
	private ArrayList<String> results = new ArrayList<String>();
	Dialog myDialog;
	
	TextView auname,atxtdate;
	Spinner asprreason;
	EditText aedtpoint1;
	Button back,logout,viewdetails,assignmorepnt;
	
	RelativeLayout rl;
	//LinearLayout ll;
	LinearLayout footer1;
	LinearLayout footer;
	AlertDialog.Builder alertbox,alertbox1;
	
	String onlyname,str1,sdate,sdate12,sdate13,ans,user_type,strreason,strname,strpoint;
	int strdate;
	int temp6,pointe,curtimestamp,position1,pos,user_type_id,id_for_reason;
	
	String[] userloginto,userloginby,date_engine,gift_reason,rec;
	String[] rec_name_given,date_given;
	int[] point_given,reasonid_given,reasonid;
	String[] arrreason,reason;
	int[] gift_point,gift_reasonid;
	
	
	int used_point1,avail_point1,yearly_point,redim_point1,earned_point1;
	
	String flag_alert1;
	String[] new_flag;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reward_given);
        
        Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
        
        /*inflating the views to use in this class*/ 
        alertbox = new AlertDialog.Builder(this);
        alertbox1 = new AlertDialog.Builder(this);
        
        rl=(RelativeLayout) findViewById(R.id.rl);
        footer1=(LinearLayout) findViewById(R.id.footer1);
        footer=(LinearLayout) findViewById(R.id.footer);
            
        
        back=(Button) findViewById(R.id.btnback);
    	logout=(Button) findViewById(R.id.btnlogout);
    	
    	 
    	viewdetails=(Button) findViewById(R.id.btnviewdetails);
    	assignmorepnt=(Button) findViewById(R.id.btnassignmore);
    	lv1=(ListView)findViewById(R.id.List);  
    	 
    	 
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
    	
    	
    	viewdetails.setOnClickListener(new Button.OnClickListener() 
       	{ 
           	
           	public void onClick (View v)
       		{ 
           		viewdetails();
       		    			
       		}});  
        	assignmorepnt.setOnClickListener(new Button.OnClickListener()  
        	{ 
           	
           	public void onClick (View v)      
       		{ 
           		funassignmorepoints(rec[pos]);
       		    			
       	 	}});
        
        	 /*initializing classes*/
        sw=new session_webservices();
        data=new DataBaseHelper(this);
        
        onlyname=ss.getSessionLoginId();
        
        
        data.deletereward_engines();   
        
          
    	rl.setVisibility(View.INVISIBLE);
		CallWebServiceTask task = new CallWebServiceTask();
		task.applicationContext = rewards_given.this;
		task.execute();
        
        
    }
    
    /*Type :Function
    name:runback
    return type:void
    date:12-12-2011
    purpose:Performing background work*/ 
    public void runback()
    {
    	sw.getreward_point(onlyname);
        
        used_point1=ss.getSessionused_point();;
        avail_point1=ss.getSessionavail_point();
        yearly_point=ss.getSessionyearly_point();
        redim_point1=ss.getSessionredim_point();  
        earned_point1=ss.getSessionearned_point();
        
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
        
        getreward_engine_by();
        
        getdistinctname();
        
        for(int x=0;x<rec.length;x++) 
        {
			System.out.println("Questions are..."+rec[x]);
			
			sw.getreward_point(rec[x]);
			int earnedpoint=ss.getSessionearned_point();
			int negativepoint=ss.getSessionnegative_point();
			int redimpoint=ss.getSessionredim_point();
			
			pointe=earnedpoint-redimpoint-negativepoint;
			results.add("Name:"+rec[x]+"\nTotal Points Available:"+pointe);
        }
    
    }
    /*Type :Function
    name:onrunback
    return type:void
    date:12-12-2011
    purpose:Updating UI when background processing completes*/
    public void onrunback()
    {
    	 getreward_engine_display();   
    	 rl.setVisibility(View.VISIBLE);
    	// ll.setVisibility(View.GONE);
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
			this.dialog = ProgressDialog.show(applicationContext, "Rewards Given", "Loading data...", true);
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
    /*Type :Class
    name:CallWebServiceTask1
    return type:void
    date:12-12-2011
    purpose:Creating progress dialog*/
    public class CallWebServiceTask1 extends AsyncTask<String, Integer, Void> {
		private ProgressDialog dialog;
		protected Context applicationContext;

		@Override
		protected void onPreExecute() {
			
			System.out.println("IN PreExecute");
			this.dialog = ProgressDialog.show(applicationContext, "Rewards Given", "Assigning Points Please Wait...", true);
		}
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("IN BACKGROUND");
			runback_assignmorepnts();
			
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
			onrunback_assignmorepnts();
					
			 
		}

		

		
	}
    
       
    public void getreward_engine_by()
	 { 
		
		 try {
			 		  
			 	  
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME8);
		 		
		 		request.addProperty("ULoginRewardBy",onlyname);
		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request);   
		 		envelope.dotNet=true;  
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION8, envelope);   
		    	 Object result = (Object)envelope.getResponse();
		 	      str1=result.toString(); 
		 	      
		 	     System.out.println("Data:"+str1);
		 	    if(str1.equals(null)||str1.equals("anyType{}"))
		        {
		 		   
		        	
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
		        temp6=((arrauserData.length)/8);
		        userloginto=new String[temp6]; 
		        date_engine=new String[temp6];    
		        gift_point=new int[temp6]; 
		        gift_reason=new String[temp6];
		        gift_reasonid=new int[temp6];
		        new_flag=new String[temp6];
		             
		       
		         
		        for(int a=1,b=0;a<temp2;a=a+8)
		        {
		        	userloginto[b]=arrauserData[a];
		        	b++;
	 	                	
		         }
		        
		         for(int a=4,b=0;a<temp2;a=a+8)
		        {
		        	date_engine[b]=arrauserData[a];
		        	b++;
	 	                	
		         }
		        for(int a=5,b=0;a<temp2;a=a+8)
		        {
		        	gift_point[b]=Integer.parseInt(arrauserData[a]);
		        	b++;
	 	                	
		         }
		        for(int a=6,b=0;a<temp2;a=a+8)
		        {
		        	gift_reason[b]=arrauserData[a];
		        	String gift=gift_reason[b];
		        	
		        	gift_reasonid[b]=Integer.parseInt(gift);
		           
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
		        for(int x=0;x<temp6;x++) 
		        {
		          
              	data.Insertreward_engines(userloginto[x],date_engine[x], ""+gift_point[x], ""+gift_reasonid[x],new_flag[x]);
              	
   
		        }
		              
		        
		 	    }   
		 }
	 	    catch (Exception e)    
	        {  
			 
			 System.out.println("Error msg:"+e.getMessage());
	        
	        }
	 }
    
   
    
    
    /*Type :Function
    name:getdistinctname
    return type:void
    date:12-12-2011
    purpose:Fetching distinct user names from local db*/
    
    public void getdistinctname()
	 {
		 Cursor c1=data.selectdistinctusersforreward();

		 rec=new String[c1.getCount()];
		 int i=0;
			while(c1.moveToNext())
			{
				String name=c1.getString(0);
				
				rec[i]=name; 
				i++;
				 
			}
			
			c1.close();
	 }
    
   
    public void getreward_engine_display() 
	 {
		CheckBox cb=new CheckBox(this);
		lv1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,results));
		lv1.setTextFilterEnabled(true);
		lv1.setOnItemClickListener(this);   
	 }
    
    public void onItemClick(AdapterView<?> a, View v, int position, long id) 
	{
		
		pos=position;
		ss.setSessionposi(pos);
		System.out.println("Position..."+position);
		ans= (String) a.getItemAtPosition(position);
		System.out.println("Value is "+ans);
		if(ans.contains(";"))
	 	{
	 		ans=ans.replace(";","");
	 	}
		
		
		
	}
    /*Type :Function
    name:funassignmorepoints
    return type:void
    date:12-12-2011
    purpose:Doing work to asign more points to the selected user name*/ 
    public void funassignmorepoints(String temp1)  
	{
		myDialog = new Dialog(this);
     	myDialog.setContentView(R.layout.assignmorepoints);
     	myDialog.setTitle("Assign More Points");  
     	myDialog.setCancelable(true);   
     		  
     	
     	radiogroup = (RadioGroup) myDialog.findViewById(R.id.Group1);

		r1=(RadioButton) myDialog.findViewById(R.id.positive);
		r2=(RadioButton) myDialog.findViewById(R.id.negative);
     	
         atxtdate=(TextView) myDialog.findViewById(R.id.txdate); 
     	 auname = (TextView) myDialog.findViewById(R.id.txtitle);
     	 asprreason = (Spinner) myDialog.findViewById(R.id.spnrreason);      
         aedtpoint1 = (EditText) myDialog.findViewById(R.id.edtpoint);  
     	 
          
         atxtdate.setText("Date:"+sdate);   
         
         if(temp1.contains("Name:"))
    	 {
        	 temp1=temp1.replace("Name:", "");
    	 }
         auname.setText(temp1);  
         auname.setTextColor(Color.BLACK);
         
         String names=auname.getText().toString();
         
         
         sw.get_user_type_for_login(names);
         user_type=ss.getSessionusers_type();
        
         Button button = (Button) myDialog.findViewById(R.id.btassign);
         
         
         sw.getreasonfromserver(user_type);
         arrreason=ss.getSessionReason(); 
         
             	  
         ArrayAdapter<CharSequence> arewardreasonadapter = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item);
         for(int x=0;x<arrreason.length;x++) 
	        {
	             arewardreasonadapter.add(arrreason[x]);
	        } 
         		asprreason.setAdapter(arewardreasonadapter);         
         		asprreason.setOnItemSelectedListener(new MyOnReasonSelectedListener());
              	 
         
         button.setOnClickListener(new OnClickListener() {
         public void onClick(View v) { 
        	 
        	 
        	CallWebServiceTask1 task = new CallWebServiceTask1();
     		task.applicationContext = rewards_given.this;
     		task.execute();
         	                	  
             }  
         });   

         myDialog.show();     
         
          
	}
    
    public class MyOnReasonSelectedListener implements OnItemSelectedListener {
		 
	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	       
	       position1=pos;
	      
	      
	    }

	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing.
	    }
	} 
    /*Type :Function
    name:runback_assignmorepnts
    return type:void
    date:12-12-2011
    purpose:Performing background work*/ 
    public void runback_assignmorepnts()
    {
     String strdateassign=""+curtimestamp;     
   	 String aname=auname.getText().toString();
   	 int apoint=Integer.parseInt(aedtpoint1.getText().toString());
   	 reasonid=ss.getSessionReasonid();
   	 int id=reasonid[position1];   
   	 if(r1.isChecked())
   	 {
   	 
   	 String pos_or_neg=r1.getText().toString();	 
   	 sw.addrewardengine(aname, onlyname,strdateassign, apoint, id,pos_or_neg);
   	 
   	sw.getreward_point(onlyname);
    
    used_point1=ss.getSessionused_point();;
    avail_point1=ss.getSessionavail_point();
    yearly_point=ss.getSessionyearly_point();
    redim_point1=ss.getSessionredim_point();  
    earned_point1=ss.getSessionearned_point();
   	 
   	 int used_point2=used_point1+apoint;  
  	 int availpoint2=yearly_point-used_point2;
  	 
   	 sw.updaterewardpoints(onlyname, used_point2, availpoint2);
   	 
   	 
   	 
   	 ss.setSessionradio_flag(0);  
   	 sw.getreward_point3(aname);
   	  
   	 }
   	 if(r2.isChecked())
   	 {
   		 String pos_or_neg=r2.getText().toString();	   
   		 sw.addrewardengine(aname, onlyname,strdateassign, apoint, id,pos_or_neg); 
   	   	 ss.setSessionradio_flag(1);
   	   	 sw.getreward_point3(aname);
   	 }
   	data.deletereward_engines();
	results.clear(); 
  	getreward_engine_by(); 
  	getdistinctname();
    
    for(int x=0;x<rec.length;x++) 
    {
		System.out.println("Questions are..."+rec[x]);
		
		sw.getreward_point(rec[x]);
		int earnedpoint=ss.getSessionearned_point();
		int negativepoint=ss.getSessionnegative_point();
		int redimpoint=ss.getSessionredim_point();
		
		pointe=earnedpoint-redimpoint-negativepoint;
		results.add("Name:"+rec[x]+"\nTotal Points Available:"+pointe);
    }
    }
    /*Type :Function
    name:onrunback_assignmorepnts
    return type:void
    date:12-12-2011
    purpose:Updating UI when background processing completes*/
    public void onrunback_assignmorepnts()
    {
    	
    	 
    	 myDialog.dismiss();
    	 getreward_engine_display();
    	 Toast.makeText( this, "Points Assigned successfully", Toast.LENGTH_LONG).show();
    }
      
    /*
    type:function
    name:viewdetails
    returns:void
    parameters:no parameters
    date:29-06-2011
    purpose:starting some activity
    */
    public void viewdetails()
    {
    	
    	Intent i1 = new Intent(this,view_reward_given_details.class);
    	
	    startActivity(i1);
    	
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

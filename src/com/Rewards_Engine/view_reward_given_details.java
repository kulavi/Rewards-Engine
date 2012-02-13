////View detailed info on reward point given
package com.Rewards_Engine;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class view_reward_given_details extends Activity{
	
	private static final String NAMESPACE = "http://tempuri.org/";      
	//private static final String URL = "http://192.168.1.85/studentresponse/Service.asmx";
	private static final String URL = "http://184.168.111.52:82/Service.asmx";
	
	private static final String SOAP_ACTION9 = "http://tempuri.org/revert_reward";      
	private static final String METHOD_NAME9 = "revert_reward";
	
	 String[] rec_name_given_negative;
	 String[] date_given_negative;
	 int[] point_given_negative;
	 int[] reasonid_given_negative;
	 String[] rec_name_given_positive;
	 String[] date_given_positive;
	 int[] point_given_positive;    
	 int[] reasonid_given_positive;
	 String strflag;
	 String[] rec;
	 String onlyname,text;
	 int pos,selected_index;
	 String strname;
	 int strdate;
	 String strreason;
	 String strpoint;
	 String flag_alert1;
	 int used_point1;
	 int avail_point1;
	 int yearly_point;
	 int redim_point1;
	 int earned_point1;
	 int[] reasonid;
	 String[] reason;
	 int id_for_reason;
	 int negativepoint1;
	 String user_type;
	 
	 Button ok,revert;
	 TextView txtname;
	 TableLayout givenrewarddetails_positive,givenrewarddetails_negative;
	 AlertDialog.Builder alertbox,alertbox1;
	 
	 RelativeLayout rl;
	 
	 DataBaseHelper data;
	 Session ss;
	 session_webservices sw;
	private String[] flag_positive;
	
	 
	
	  
	 
	 
	 
	 
	 /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.view_reward_given_details);
	        
	        /*initializing classes*/
	        data=new DataBaseHelper(this);
	        sw=new session_webservices();
	        
	        onlyname=ss.getSessionLoginId();   
	        
	        
	        /*inflating the views to use in this class*/
	        rl=(RelativeLayout) findViewById(R.id.rl);
	        
	        txtname=(TextView) findViewById(R.id.txtname);
	        
	        ok=(Button) findViewById(R.id.btnok);
	    	revert=(Button) findViewById(R.id.btnrevert);
	    	
	    	givenrewarddetails_positive=(TableLayout)findViewById(R.id.givenrewarddetails_positive);
	    	
	    	
	    	alertbox = new AlertDialog.Builder(this);
	        alertbox1 = new AlertDialog.Builder(this);
	        
	       
	        /*showing progress bar till the background work of web services completes*/
	        CallWebServiceTask2 task = new CallWebServiceTask2();
			task.applicationContext = view_reward_given_details.this;
			task.execute();
			
			ok.setOnClickListener(new Button.OnClickListener()  
	    	{ 
	       	
	       	public void onClick (View v)      
	   		{ 
	   		    
	   		    		okclick();	
	   	 	}});
			revert.setOnClickListener(new Button.OnClickListener()  
	    	{ 
	       	
	       	public void onClick (View v)      
	   		{ 
	   		    
	       		confirm_revert();
	   	 	}});
			
			
			
			
	        
	    }
	    public void okclick()
	    {
	     Intent i=new Intent(this,rewards_given.class);
	     startActivity(i);
	    }
	    
	    /*Type :Function
	    name:revert_point
	    return type:void
	    date:12-12-2011
	    purpose:Performing operations to revert the reward points for selected user name*/ 
	    private void revert_point(String name,String date,String point,String reason_id,String flags)
		{	
			
			
			 try {
		 		 
			 	  
		      	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME9);
		    	 	
		    	 	
		    	 	request.addProperty("strto",name); 
		      	 	request.addProperty("strfrom",onlyname);   
		    	 	request.addProperty("strdate",date); 
		    	 	request.addProperty("strpoint",point); 
		    	 	request.addProperty("strreasonid",reason_id);
		    	 	request.addProperty("flag",flags);
		    	 	
		      	 		
		      	 	
		    	 		 			         		 
			 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

			 		envelope.setOutputSoapObject(request);
			 		envelope.dotNet=true; 
			 		envelope.encodingStyle = SoapSerializationEnvelope.XSD; 
			 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			 	
			 	    androidHttpTransport.call(SOAP_ACTION9, envelope);
			 	   
			 	  
			       
			 	    Toast.makeText(this, "Question Deleted succussfully",Toast.LENGTH_SHORT).show();
			       
			       
			 	      
			 } 
		        catch (Exception e)    
		        { 
		        
		        	System.out.println("ERROR:"+e.getMessage());
		        }  

		}
	    /*Type :Class
	    name:CallWebServiceTask2
	    return type:void
	    date:12-12-2011
	    purpose:Creating progress dialog*/
	    public class CallWebServiceTask2 extends AsyncTask<String, Integer, Void> {
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
				runback_viewdetails();
				
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
				onrunback_viewdetails();
						
				 
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
	    /*Type :Function
	    name:getrewardgivendetails_positive
	    return type:void
	    date:12-12-2011
	    purpose:Fetching details from local db for both positive and negative reward points to show in table*/
	    public void getrewardgivendetails_positive(String name)
		 {
			 Cursor c1=data.selectall_fromengines(name);  
			 rec_name_given_positive=new String[c1.getCount()];
			 date_given_positive=new String[c1.getCount()];
			 point_given_positive=new int[c1.getCount()];
			 reasonid_given_positive=new int[c1.getCount()];
			 flag_positive=new String[c1.getCount()];
			 int i=0;
				while(c1.moveToNext())
				{  
					    String rec=c1.getString(1);
					    String dat=c1.getString(2);
			  		    int pointec=c1.getInt(3);
					    int rids=c1.getInt(4);
					    String flag=c1.getString(5);
					    
					    System.out.println("NAME IS....."+rec);
					    
					    rec_name_given_positive[i]=rec;
					    date_given_positive[i]=dat;
					    point_given_positive[i]=pointec;
					    reasonid_given_positive[i]=rids;
					    flag_positive[i]=flag;
					    i++;
					
					
				}
				
				c1.close();
				  
		 }
	    /*Type :Function
	    name:runback_viewdetails
	    return type:void
	    date:12-12-2011
	    purpose:Performing background work*/ 
	    public void runback_viewdetails()
	    {  	
	    	getdistinctname();
	    	pos=ss.getSessionposi();
	    	getrewardgivendetails_positive(rec[pos]);
	    	
	    	
	    	
	    	
	    }
	    /*Type :Function
	    name:onrunback_viewdetails
	    return type:void
	    date:12-12-2011
	    purpose:Updating UI when background processing completes*/
	    public void onrunback_viewdetails()
	    {
	    	    	
	        getgivenrewarddisplay_positive(rec[pos]);
	        
	       
	    }
	    /*Type :Function
	    name:getgivenrewarddisplay_positive
	    return type:void
	    date:12-12-2011
	    purpose:Displaying logs in table format*/
	    public void getgivenrewarddisplay_positive(String name1) 
		 {
	    	
	    	
	    	
		    txtname.setText("Name:" +name1);
			
			  	final TableRow tr1 = new TableRow(this); 
				tr1.layout(0, 0, 0, 0); 
				
				final CheckBox chk = new CheckBox(this);
	            
				chk.setPadding(10, 0, 0, 0);
				tr1.setPadding(0, 1, 0, 1);
				
	            tr1.addView(chk);
			
				//Create a TextView to house the name of the province 
				  final TextView labeldatetitle = new TextView(this);
				  
				  labeldatetitle.setText("Date"); 
				  labeldatetitle.setTextColor(Color.BLACK);
				  
				  labeldatetitle.setPadding(10, 0, 0, 0);
				  tr1.setPadding(0, 1, 0, 1);
				  
				  tr1.addView(labeldatetitle);
				  
				//Create a TextView to house the name of the province 
				  final TextView labeltypetitle = new TextView(this);
				          
				  
				  labeltypetitle.setText("Type Of Point"); 
				  labeltypetitle.setTextColor(Color.BLACK);
				  
				  labeltypetitle.setPadding(10, 0, 0, 0);
				  tr1.setPadding(0, 1, 0, 1);
				  
				  tr1.addView(labeltypetitle);  
				  
				//Create a TextView to house the name of the province 
				  final TextView labelpnttitle = new TextView(this);
				          
				  
				  labelpnttitle.setText("Point"); 
				  labelpnttitle.setTextColor(Color.BLACK);
				  
				  labelpnttitle.setPadding(10, 0, 0, 0);  
				  tr1.setPadding(0, 1, 0, 1);
				  
				  tr1.addView(labelpnttitle);
				  
				//Create a TextView to house the name of the province 
				  final TextView labelreasontitle = new TextView(this);
				           
				  
				  labelreasontitle.setText("Reward Reason"); 
				  labelreasontitle.setTextColor(Color.BLACK);
				  
				  labelreasontitle.setPadding(10, 0, 0, 0);
				  tr1.setPadding(0, 1, 0, 1);
				  
				  tr1.addView(labelreasontitle);
				    
				  
				  givenrewarddetails_positive.addView(tr1, new TableLayout.LayoutParams(
				          LayoutParams.WRAP_CONTENT,
				         LayoutParams.WRAP_CONTENT)); 

		
				 for(int i4=0;i4<rec_name_given_positive.length;i4++) 
				  {  
			
					try 
					{
					
		            
		            final TableRow tr2 = new TableRow(this);  
		            tr2.layout(0, 0, 0, 0);
		            
		            
		                
		            
		            final CheckBox chk1 = new CheckBox(this);
		            
		            chk1.setPadding(10, 0, 0, 0);
		            chk1.setId(i4);
					tr2.setPadding(0, 1, 0, 1);
		              
		            tr2.addView(chk1);
		            
		            
		        	final int date1=Integer.parseInt(date_given_positive[i4]);
		            
		            Date date = new Date( date1* 1000L); 
		            
		            SimpleDateFormat postFormater = new SimpleDateFormat("dd-MM-yyyy"); 
		            
		            String newDateStr = postFormater.format(date); 
		            
		            final TextView labeldate = new TextView(this);
		             
		               
		              
		            labeldate.setText(newDateStr);      
		             
		            labeldate.setTextColor(Color.BLACK);
		            
		            labeldate.setPadding(10, 0, 0, 0);
					tr2.setPadding(0, 1, 0, 1);
		            
		            tr2.addView(labeldate);
		            
		            final TextView labeltype = new TextView(this);
		             
	             
		            
		            labeltype.setText(""+flag_positive[i4]);      
		           
		            labeltype.setTextColor(Color.BLACK);
		            
		            labeltype.setPadding(10, 0, 0, 0);
					tr2.setPadding(0, 1, 0, 1);
		            
		            tr2.addView(labeltype);
		            
		            final TextView labelpoint = new TextView(this);
		             
	             
		            
		            labelpoint.setText(""+point_given_positive[i4]);      
		           
		            labelpoint.setTextColor(Color.BLACK);
		            
		            labelpoint.setPadding(10, 0, 0, 0);
					tr2.setPadding(0, 1, 0, 1);
		            
		            tr2.addView(labelpoint);
		            
		            String reas=sw.getreason_for_id(""+reasonid_given_positive[i4]);
		            
		            
		            final TextView labelresaon = new TextView(this);
		             
		               
		            
		            labelresaon.setText(reas);       
		           
		            labelresaon.setTextColor(Color.BLACK);
		            
		            labelresaon.setPadding(10, 0, 0, 0);
					tr2.setPadding(0, 1, 0, 1);
		            
		            tr2.addView(labelresaon);
		            
		            
		            
		            
		            
	               // Add the TableRow to the TableLayout 
		                          
		            givenrewarddetails_positive.addView(tr2, new TableLayout.LayoutParams(
		                     LayoutParams.WRAP_CONTENT,
		                    LayoutParams.WRAP_CONTENT));
		            
		            
		            chk1.setOnCheckedChangeListener(new OnCheckedChangeListener()
		            {
		            	

						 

						public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		                {
		                    if (isChecked )
		                    {
		                    	
		                        selected_index=chk1.getId();
		                        System.out.println("Selected Checkbox id is..."+selected_index);
		                        strname=txtname.getText().toString();
		                        if(strname.contains("Name: "))
		                        {
		                        	strname=strname.replace("Name: ", "");
		                        }
		                       
		                        strdate=date1;
		                        System.out.println("Date is:"+strdate);
		                        strflag=labeltype.getText().toString();
		                        strreason=labelresaon.getText().toString();
		                        strpoint=labelpoint.getText().toString();
		                        
		                        System.out.println("SELECTED FLAG IS..."+strflag);
		                        
		                        System.out.println("Name is:"+strname+"..date is:"+strdate+"..reason is:"+strreason+"..points are:"+strpoint);
		                        System.out.println("SELECTED INDEX IS..."+selected_index);
		                    	
		                    	
		                    }

		                }

						
		            });
	            
		            
		                     
		              
					}
					catch (Exception e)    
			        {
			         
			        System.out.println("Error msg:::::::::"+e.getMessage());
			        }
				}
		 }
	    /*Type :Class
	    name:CallWebServiceTask_revert
	    return type:void
	    date:12-12-2011
	    purpose:Creating progress dialog*/
	    public class CallWebServiceTask_revert extends AsyncTask<String, Integer, Void> {
			private ProgressDialog dialog;
			protected Context applicationContext;

			@Override
			protected void onPreExecute() {
				
				System.out.println("IN PreExecute");
				this.dialog = ProgressDialog.show(applicationContext, "Rewards Given", "Please Wait...", true);
			}
			@Override
			protected Void doInBackground(String... params) {
				// TODO Auto-generated method stub
				System.out.println("IN BACKGROUND");
				runback_revert();
				
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
				onrunback_revert();
						
				 
			}

			

			
		}
	    
	    /*Type :Function
	    name:confirm_revert
	    return type:void
	    date:12-12-2011
	    purpose:Opening alert asking for some confirmation of task to be done*/
	    public void confirm_revert()
	    {
	    	// set the message to display
	        alertbox.setMessage("Are you sure you want to revert this reward?");

	        // set a positive/yes button and create a listener
	        alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

	            // do something when the button is clicked
	            public void onClick(DialogInterface arg0, int arg1) {
	               // Toast.makeText(getApplicationContext(), "'Yes' button clicked", Toast.LENGTH_SHORT).show();
	            	
	            	//givenrewarddetails.removeAllViews();
	         	   // givenrewarddetails1.removeAllViews();
	            	
	            	CallWebServiceTask_revert task = new CallWebServiceTask_revert();
	        		task.applicationContext = view_reward_given_details.this;
	        		task.execute();
	                
	            }
	        });

	        // set a negative/no button and create a listener
	        alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {

	            // do something when the button is clicked
	            public void onClick(DialogInterface arg0, int arg1) {
	               // Toast.makeText(getApplicationContext(), "'No' button clicked", Toast.LENGTH_SHORT).show();
	            }
	        });

	        // display box
	        alertbox.show();
	    	
	    	  			
	 			
	    	
		        
	    }
	    /*Type :Function
	    name:onrunback_revert
	    return type:void
	    date:12-12-2011
	    purpose:Updating UI when background processing completes*/
	    public void onrunback_revert()  
	    {
	    	  
	    	
	    	givenrewarddetails_positive.removeViewAt(selected_index+1);	    	
	    	System.out.println("Selected index is:"+selected_index);
	    	   	
	    	Toast.makeText(this, "Reward Reverted",Toast.LENGTH_LONG).show();
			
	    }
	    /*Type :Function
	    name:runback_revert
	    return type:void
	    date:12-12-2011
	    purpose:Performing background work*/
	    public void runback_revert()  
	    {
	    	
	    	
	    	if(strname.contains("Name:"))
	    	{
	    		strname=strname.replace("Name:", "");
	    	}   
	    	int points=Integer.parseInt(strpoint); 
	    	
	    	System.out.println("NOW THE STRING IS:"+strname);  
	    	sw.getreward_point(onlyname);
	        
	        used_point1=ss.getSessionused_point();;
	        avail_point1=ss.getSessionavail_point();
	        yearly_point=ss.getSessionyearly_point();
	        redim_point1=ss.getSessionredim_point();  
	        earned_point1=ss.getSessionearned_point();
	        
	        int used_point2=used_point1-points;   
	        
	        int avail_point2=avail_point1+points;  
	    	
	    	    
	        sw.getreward_point(strname);  
	        
	        int earned_point2=ss.getSessionearned_point();
	        
	        int redim_point2=ss.getSessionredim_point();
	        
	        negativepoint1=ss.getSessionnegative_point();
	              
	        
	    	sw.get_user_type_for_login(strname);  
	    	  
	    	user_type=ss.getSessionusers_type();
	        
	        sw.getreasonfromserver(user_type);
	            
	        reasonid=ss.getSessionReasonid();
	        reason=ss.getSessionReason();
	          
	        data.deletereason();
	        for(int x=0;x<reason.length;x++)
	        {
	        data.Insertreason(reasonid[x], reason[x]);
	        }
	        
	        getid_for_reason();            
	                        
	        
	       
	             
	        if(strflag.equals("Positive"))
	        {
	        	revert_point(strname,""+strdate,strpoint,""+id_for_reason,strflag);
	        	sw.updaterewardpoints(onlyname, used_point2, avail_point2);
	        	int total_earned_now=earned_point2-points;
		        
		        sw.updatereward_point_earnedpnt(strname,total_earned_now);
		        getrewardgivendetails_positive(strname);
	        }    
	        if(strflag.equals("Negative"))
	        {
	        	revert_point(strname,""+strdate,strpoint,""+id_for_reason,strflag);
	        	int total_negative_now=negativepoint1-points;
		        
		        sw.updatereward_point_negativepnt(strname,total_negative_now);
		        getrewardgivendetails_positive(strname);
		       
	        }
	        
	          
	        
	        
	    }  
	    /*Type :Function
	    name:getid_for_reason
	    return type:void
	    date:12-12-2011
	    purpose:fetching reason id for selected reason from local db*/
	    public void getid_for_reason()
		 {
			 Cursor c1= data.selectid_for_reason(strreason);
			    
				while(c1.moveToNext())
				{
					 id_for_reason=c1.getInt(0);  
					
							 
				}
				
				c1.close();
		 }
	    
	    
}

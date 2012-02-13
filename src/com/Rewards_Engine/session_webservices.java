//This class contains all those web services methods call which are commonly required in more than one activity
package com.Rewards_Engine;

import java.util.HashMap;
import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.Rewards_Engine.assign_authority.MyOnutypepointSelectedListener;
import com.Rewards_Engine.assign_reward.MyOnItemSelectedListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

public class session_webservices extends Activity {
	
	   
	private static final String NAMESPACE = "http://tempuri.org/";      
	//private static final String URL = "http://192.168.1.85/studentresponse/Service.asmx";
	private static final String URL = "http://184.168.111.52:82/Service.asmx";
	
	
	private static final String SOAP_ACTION1 = "http://tempuri.org/get_user_type_for_login_name";      
	private static final String METHOD_NAME1 = "get_user_type_for_login_name";
	
	private static final String SOAP_ACTION2 = "http://tempuri.org/GetReward_point_to_usertype";      
	private static final String METHOD_NAME2 = "GetReward_point_to_usertype";  
	
	  
	private static final String SOAP_ACTION3 = "http://tempuri.org/InsertReward_point";      
	private static final String METHOD_NAME3 = "InsertReward_point";    
	
	  
	private static final String SOAP_ACTION5 = "http://tempuri.org/GetReason";        
	private static final String METHOD_NAME5 = "GetReason";  
	
	  
	private static final String SOAP_ACTION6 = "http://tempuri.org/GetReason_for_id";      
	private static final String METHOD_NAME6 = "GetReason_for_id";
	
	
	private static final String SOAP_ACTION7 = "http://tempuri.org/Insertreward_engine";      
	private static final String METHOD_NAME7 = "Insertreward_engine"; 
	
	
	
	
	private static final String SOAP_ACTION10 = "http://tempuri.org/GetReward_Point";      
	private static final String METHOD_NAME10 = "GetReward_Point"; 
	
	private static final String SOAP_ACTION11 = "http://tempuri.org/Updatereward_Point";      
	private static final String METHOD_NAME11 = "Updatereward_Point";
	
	
	
	private static final String SOAP_ACTION13 = "http://tempuri.org/SUMreward_enginedata";      
	private static final String METHOD_NAME13 = "SUMreward_enginedata"; 
	
	
	private static final String SOAP_ACTION14 = "http://tempuri.org/SUMredim_point_details";      
	private static final String METHOD_NAME14 = "SUMredim_point_details"; 
	
	
	private static final String SOAP_ACTION15 = "http://tempuri.org/UpdatetotalearnedPoint";      
	private static final String METHOD_NAME15 = "UpdatetotalearnedPoint"; 
	
	private static final String SOAP_ACTION16 = "http://tempuri.org/UpdatetotalnegativePoint";      
	private static final String METHOD_NAME16 = "UpdatetotalnegativePoint"; 
	
	private static final String SOAP_ACTION17 = "http://tempuri.org/SUMnegative_point_details";      
	private static final String METHOD_NAME17 = "SUMnegative_point_details"; 
	
	private static final String SOAP_ACTION18 = "http://tempuri.org/NewUpdatereward_Point";  
	private static final String METHOD_NAME18 = "NewUpdatereward_Point";
	
	String str1,sumearnedpoint,sumredimedpoint,user_login_name,sumnegativepoint;
	
	
	
	DataBaseHelper data;
	Session ss;
	Session_table_values stv;
	
	String[] arrauserData,arrreason,user_point,user_type;
	int[] reasonid;
	String reason_name;
	int temp11,position1,temp8,temp16;
	String str12;
	int used_point1,yearly_point,avail_point1,earnedpoint1,redimedpoint1;
	String[] new_userloginto,new_userloginby,new_date_engine;
	String[] new_gift_reason;
	int[] new_gift_reasonid,new_gift_point;
	int negativepoint1;
	
	
	
	public String getreason_for_id(String ids)
	{
		try { 
	 		   
		 	  
    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME6);   
	 		
	 		request.addProperty("id",ids);
	 		
	 			         		 
	 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

	 		envelope.setOutputSoapObject(request); 
	 		envelope.dotNet=true; 
	 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
	 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);  
	 	
	 		androidHttpTransport.call(SOAP_ACTION6, envelope);   
	    	 Object result = (Object)envelope.getResponse();  
	 	      str1=result.toString(); 
	 	      
	 	     System.out.println("Data:"+str1);
	 	    if(str1.equals(null)||str1.equals("anyType{}")||str1.equals("anyType{anyType=; }")) 
	        {
	 	    	
	        	
	        } 
	 	    else
	 	    {
	 	    if(str1.contains("anyType{anyType=")||str1.contains("}")||str1.contains("anyType=")||str1.contains(";"))
	        {
	    	  str1=str1.replace("anyType{anyType=", "");
	    	  str1=str1.replace("anyType=", "");
	    	  str1=str1.replace("}", ""); 
	    	  str1=str1.replace(";","");
	    	  str1=str1.trim();
	    	  System.out.println("String"+str1); 
	    	  
	        }
	        reason_name=str1;
	           
	                 
	 	    }   
	 }
 	    catch (Exception e)    
        {
		 
		 System.out.println("Error msg:"+e.getMessage());  
        
        }  
 	    return reason_name;
		
		 
	}
	
	
	public String getsumearnedpoint(String name)
	{
		try { 
	 		   
		 	  
    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME13);   
	 		
	 		request.addProperty("ULoginRewardTo",name);
	 		
	 			         		 
	 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

	 		envelope.setOutputSoapObject(request); 
	 		envelope.dotNet=true; 
	 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
	 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
	 	
	 		androidHttpTransport.call(SOAP_ACTION13, envelope);   
	    	 Object result = (Object)envelope.getResponse();  
	 	      str1=result.toString(); 
	 	      
	 	     System.out.println("Data:"+str1);
	 	    if(str1.equals(null)||str1.equals("anyType{}")||str1.equals("anyType{anyType=; }")) 
	        {
	 	    	sumearnedpoint="0";
	        	
	        } 
	 	    else
	 	    {
	 	    if(str1.contains("anyType{anyType=")||str1.contains("}")||str1.contains("anyType=")||str1.contains(";"))
	        {
	    	  str1=str1.replace("anyType{anyType=", "");
	    	  str1=str1.replace("anyType=", "");
	    	  str1=str1.replace("}", ""); 
	    	  str1=str1.replace(";","");
	    	  str1=str1.trim();
	    	  System.out.println("String"+str1); 
	    	  
	        }
	        sumearnedpoint=str1;
	           
	                 
	 	    }   
	 }
 	    catch (Exception e)    
        {
		 
		 System.out.println("Error msg:"+e.getMessage());  
        
        }  
 	    return sumearnedpoint;
		
		 
	}
	public String getsumnegativepoint(String name)
	{
		try { 
	 		   
		 	  
    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME17);   
	 		
	 		request.addProperty("ULoginRewardTo",name);
	 		
	 			         		 
	 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

	 		envelope.setOutputSoapObject(request); 
	 		envelope.dotNet=true; 
	 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
	 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);  
	 	
	 		androidHttpTransport.call(SOAP_ACTION17, envelope);   
	    	 Object result = (Object)envelope.getResponse();  
	 	      str1=result.toString(); 
	 	      
	 	     System.out.println("Data:"+str1);
	 	    if(str1.equals(null)||str1.equals("anyType{}")||str1.equals("anyType{anyType=; }")) 
	        {
	 	    	sumnegativepoint="0";
	        	
	        } 
	 	    else
	 	    {
	 	    if(str1.contains("anyType{anyType=")||str1.contains("}")||str1.contains("anyType=")||str1.contains(";"))
	        {
	    	  str1=str1.replace("anyType{anyType=", "");
	    	  str1=str1.replace("anyType=", "");
	    	  str1=str1.replace("}", ""); 
	    	  str1=str1.replace(";","");
	    	  str1=str1.trim();
	    	  System.out.println("String"+str1); 
	    	  
	        }
	 	   sumnegativepoint=str1;
	           
	                 
	 	    }   
	 }
 	    catch (Exception e)    
        {
		 
		 System.out.println("Error msg:"+e.getMessage());  
        
        }  
 	    return sumnegativepoint;
		
		 
	}
	public String getsumredimpoint(String name) 
	{
		
		try {  
	 		  
		 	  
    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME14); 
	 		
	 		request.addProperty("UserName",name);
	 		
	 			         		 
	 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

	 		envelope.setOutputSoapObject(request); 
	 		envelope.dotNet=true; 
	 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
	 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
	 	
	 		androidHttpTransport.call(SOAP_ACTION14, envelope);   
	    	 Object result = (Object)envelope.getResponse();
	 	      str1=result.toString(); 
	 	      
	 	     System.out.println("Data:"+str1); 
	 	    if(str1.equals(null)||str1.equals("anyType{}")||str1.equals("anyType{anyType=; }"))
	        {
	 	    	sumredimedpoint="0";
	        	
	        } 
	 	    else
	 	    {
	 	    if(str1.contains("anyType{anyType=")||str1.contains("}")||str1.contains("anyType=")||str1.contains(";"))
	        {
	    	  str1=str1.replace("anyType{anyType=", "");
	    	  str1=str1.replace("anyType=", "");
	    	  str1=str1.replace("}", ""); 
	    	  str1=str1.replace(";","");
	    	  str1=str1.trim();
	    	  System.out.println("String"+str1); 
	    	  
	        }
	        sumredimedpoint=str1;
	           
	                 
	 	    }   
	 }
 	    catch (Exception e)    
        {
		 
		 System.out.println("Error msg:"+e.getMessage()); 
        
        }
 	    return sumredimedpoint;
		
		
		
	}
	
	public void updaterewardpointswhole(String name,int ypnt,int upnt,int apnt,String adate,String edate)
	{
		
		try {
	 		 
		 	  
      	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME18);
    	 	
    	 	
    	 	request.addProperty("UserName",name); 
    	 	request.addProperty("yearlyPoint",ypnt);
    	 	request.addProperty("usedPoint",upnt);
    	 	request.addProperty("AvaliablePoint",apnt);
    	 	request.addProperty("dateofassign",adate);
    	 	request.addProperty("dateofexpire",edate);
    	 	
    	 		 			         		 
	 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

	 		envelope.setOutputSoapObject(request);
	 		envelope.dotNet=true; 
	 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
	 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL); 
	 	
	 	   androidHttpTransport.call(SOAP_ACTION18, envelope);
	 	   
	 	   
	       
	 	   
	       
	       
	 	      
	 } 
        catch (Exception e)    
        { 
        
        	System.out.println("ERROR:"+e.getMessage());
        }  
	}
	 
	
	
	
	
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
	
	
	public void addrewardpoints(String uname,int ypoint,int usedpoint,int availpoint,String dateassign,String dateexpire,int earn,int redim,int negative)
	 { 
		 try {
			 		 
			 	  
	      	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME3);
	    	 	
	    	 	
	    	 	request.addProperty("UserName",uname);
	    	 	request.addProperty("YearlyPoint",ypoint);
	    	 	request.addProperty("UsedPoint",usedpoint);
	    	 	request.addProperty("AvailablePoint",availpoint);
	    	 	request.addProperty("Dateassign",dateassign); 
	    	 	request.addProperty("DateExpire",dateexpire);
	    	 	request.addProperty("TotalearnedPoint",earn);
	    	 	request.addProperty("totalredimPoint",redim);
	    	 	request.addProperty("totalnegativePoint",negative);
		 		
		 				 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request);
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 	    androidHttpTransport.call(SOAP_ACTION3, envelope);
		       
		 	    
		       
		 	      
		 }   
	        catch (Exception e)    
	        { 
	       
	        	System.out.println("ERROR:"+e.getMessage());
	        }  
	 }
	
	
	public void getreasonfromserver(String utypes)
	 { 
		  
		 try { 
			 		  
			 	   
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME5);
		 		
	    	 	request.addProperty("UserType", utypes);
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request); 
		 		envelope.dotNet=true; 
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION5, envelope);   
		    	 Object result = (Object)envelope.getResponse();
		 	      str1=result.toString();   
		 	      
		 	     System.out.println("Data:"+str1);
		 	    if(str1.equals(null)||str1.equals("anyType{}")) 
		        {
		 	    	
		 	    	
		 	    	ss.setSessionflag1(0);
		 	    	
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
		        temp11=((arrauserData.length)/2);
		        reasonid=new int[temp11];
		        arrreason=new String[temp11];  
		        
		       
		        
		        for(int a=0,b=0;a<temp2;a=a+2)
		        {
		        	reasonid[b]=Integer.parseInt(arrauserData[a]);
		        	
		        	b++;
	 	                	
		         }
		        for(int a=1,b=0;a<temp2;a=a+2)
		        {
		        	arrreason[b]=arrauserData[a];
		        	b++; 
	 	                	  
		        }
		        
		        
		        
		        ss.setSessionflag1(1);
		        
		        ss.setSessionReasonid(reasonid);
		        ss.setSessionReason(arrreason);
		        		        		                  
		        
		 	    }   
		 }
	 	    catch (Exception e)    
	        {
			 
			 System.out.println("Error msg:"+e.getMessage()); 
	        
	        }
	 }
	
	
	
	public void getreward_point(String name)
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
		         
		        
		        if(arrauserData[4].contains(";"))
		        {
		        	arrauserData[4]=arrauserData[4].replace(";", "");
		        }
		        if(arrauserData[5].contains(";"))
		        {
		        	arrauserData[5]=arrauserData[5].replace(";", "");
		        }
		        
		        if(arrauserData[3].equals(null))
		        {
		        	arrauserData[3].replace(null,"0");  
		        }
		        if(arrauserData[4].equals(null)) 
		        {
		        	arrauserData[4].replace(null,"0");
		        }
		        if(arrauserData[5].equals(null)) 
		        {
		        	arrauserData[5].replace(null,"0");
		        }
		        
		         
		         used_point1=Integer.parseInt(arrauserData[0]);
		         yearly_point=Integer.parseInt(arrauserData[1]); 
		         avail_point1 =Integer.parseInt(arrauserData[2]);   
		         earnedpoint1=Integer.parseInt(arrauserData[3]);
		         redimedpoint1=Integer.parseInt(arrauserData[4]);
		         negativepoint1=Integer.parseInt(arrauserData[5]);
		         
		         		           
		         ss.setSessionused_point(used_point1);
		         ss.setSessionyearly_point(yearly_point);
		         ss.setSessionavail_point(avail_point1);
		         ss.setSessionearned_point(earnedpoint1);
		         ss.setSessionredim_point(redimedpoint1);
		         ss.setSessionnegative_point(negativepoint1);
		         
	        	
	             
		        
		 	    }   
		 }
	 	    catch (Exception e)    
	        {
			 
			 System.out.println("Error msg:"+e.getMessage()); 
	        
	        }
	 }  
	
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
		 	           
		        
		 	   	getsumearnedpoint(names); 
		 	    getsumnegativepoint(names); 
		 	    getsumredimpoint(names); 
		 	    
		 	 
		 	    
		 	    if(str12.equals(null)||str12.equals("anyType{}"))
		        {
		 	    	
		 	    	
		 	    	addrewardpoints(names,0,0,0,null,null,Integer.parseInt(sumearnedpoint),Integer.parseInt(sumredimedpoint),Integer.parseInt(sumnegativepoint));
		 	    	
		 	    	
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
		 	    	
		 	    	int flag=ss.getSessionradio_flag();
		 	    	
		 	    	if(arrauserData[1].equals("0"))
		 	    	{
		 	    		if(flag==0)  
		 	    		{
		 	    		updatereward_point_earnedpnt(names,Integer.parseInt(sumearnedpoint));
		 	    		}
		 	    		if(flag==1)
		 	    		{
		 	    			
		 	    			updatereward_point_negativepnt(names,Integer.parseInt(sumnegativepoint));
		 	    			//System.out.println("NEGATIVE BUTTON CLICKED");
		 	    		}
		 	    		  
		 	    	}
		 	    	
		 	    	else
		 	    	{
		 	    		addrewardpoints(names,0,0,0,null,null,Integer.parseInt(sumearnedpoint),Integer.parseInt(sumredimedpoint),Integer.parseInt(sumnegativepoint));
			 	    		    	  
		 	    	
		 	    	} 
		 	    	
		        
		 	    }   
		 }
	 	    catch (Exception e)      
	        {
			 
			 System.out.println("Error msg:"+e.getMessage()); 
	        
	        }
	 } 
	
	public void updatereward_point_earnedpnt(String name,int pnt)
	{
		 
		try {
	 		 
		 	  
      	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME15);
    	 	
    	 	
    	 	request.addProperty("UserName",name); 
    	 	request.addProperty("TotalearnedPoint",""+pnt);
    	 	
    	 	
    	 		 			         		 
	 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

	 		envelope.setOutputSoapObject(request);
	 		envelope.dotNet=true; 
	 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
	 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
	 	
	 	   androidHttpTransport.call(SOAP_ACTION15, envelope);
	 	       
		
	}
		 catch (Exception e)    
	        { 
	        
	        	System.out.println("ERROR:"+e.getMessage());
	        }  
	
	}
	
	public void updatereward_point_negativepnt(String name,int pnt)
	{
		 
		try {
	 		 
		 	  
      	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME16);
    	 	
    	 	
    	 	request.addProperty("UserName",name); 
    	 	request.addProperty("TotalnegativePoint",""+pnt);
    	 	
    	 	
    	 		 			         		 
	 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

	 		envelope.setOutputSoapObject(request);
	 		envelope.dotNet=true; 
	 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
	 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
	 	
	 	   androidHttpTransport.call(SOAP_ACTION16, envelope);  
	 	   
		
	}
		 catch (Exception e)    
	        { 
	        
	        	System.out.println("ERROR:"+e.getMessage());  
	        }  
	
	}
	
	public void point()
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
		 	    str12=result.toString(); 
		 	      
		 	    System.out.println("Data:"+str12);
		 	    if(str12.equals(null)||str12.equals("anyType{}"))
		        {
		 		   		        	
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
		         
		        int temp2=arrauserData.length;
		        temp8=((arrauserData.length)/2);
		 	    
		 	    
		 	   user_type=new String[temp8]; 
		        user_point=new String[temp8]; 
		        
		        
		        for(int a=0,b=0;a<temp2;a=a+2)
		        {
		        	user_type[b]=arrauserData[a];
		        	b++;
	 	                	
		        }
		        for(int a=1,b=0;a<temp2;a=a+2)
		        {
		        	user_point[b]=arrauserData[a];
		        	b++;
	 	                	
		        }
		        
		        ss.setSessionuser_type1(user_type);
		        ss.setSessionuser_point(user_point);
		        
		               
		             
		        
		 	    }   
		 }
	 	    catch (Exception e)     
	        {
			 
			 System.out.println("Error msg:"+e.getMessage());
	        
	        }
	 }
	
	public void get_user_type_for_login(String login)
	 { 
		
		 try {
			 		  
			 	  
	    	 	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME1);
		 		
		 		request.addProperty("loginid",login);
		 		
		 			         		 
		 		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 

		 		envelope.setOutputSoapObject(request);   
		 		envelope.dotNet=true;  
		 		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		 		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 	
		 		androidHttpTransport.call(SOAP_ACTION1, envelope);   
		    	 Object result = (Object)envelope.getResponse();
		 	      String str1=result.toString(); 
		 	      
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
		               
		       
		         String user_type=arrauserData[0];
		         if(arrauserData[1].contains(";"))
		         {
		        	 arrauserData[1]=arrauserData[1].replace(";", "");
		         }
		         int user_type_id=Integer.parseInt(arrauserData[1]);
		         
		         
		         ss.setSessionusers_type(user_type);
		        
		              
		        
		 	    }   
		 }
	 	    catch (Exception e)    
	        {  
			 
			 System.out.println("Error msg:"+e.getMessage());
	        
	        }
	 }
	

}

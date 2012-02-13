//provides the screen to show the options to navigate through the app
package com.Rewards_Engine;



import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class reward_main_page extends Activity {
	
	
	Session ss,ss1;
	TextView txt_welcome_user;
	ImageView img_user_pic;
	Button btn_add_reason,btn_assgn_authority,btn_assign_reward,btn_reward_earned,btn_reward_given,btn_reward_balance;
	Button logout,btn_currency_to_point;
	Button btn_assign_reward1,btn_reward_earned1,btn_reward_given1,btn_reward_balance1;
	Button logout1;
	TextView txt_welcome_user1;
	ImageView img_user_pic1;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reward_main_page);
        
        
        String type=ss.getSessionuser_type();
        String user_name=ss.getSessionUserName();
        
        Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
        /*inflating the views to use in this class*/
        txt_welcome_user=(TextView) findViewById(R.id.welcome_user);  
        img_user_pic=(ImageView) findViewById(R.id.pro_pic);
        btn_currency_to_point=(Button) findViewById(R.id.define_currency);  
        btn_add_reason=(Button) findViewById(R.id.add_reason);
        btn_assgn_authority=(Button) findViewById(R.id.assign_authority);    
        btn_assign_reward=(Button) findViewById(R.id.assign_reward);
        btn_reward_earned=(Button) findViewById(R.id.reward_earned);
        btn_reward_given=(Button) findViewById(R.id.reward_given);
        btn_reward_balance=(Button) findViewById(R.id.reward_balance);
        logout=(Button) findViewById(R.id.btnlogout);
        
        
        txt_welcome_user.setText("Welcome "+user_name);
        
        
        
        /*downloading image from ftp path*/
        Bitmap bitmap =DownloadImage("http://bpsius:Bpsi%40123@bpsi.us/propic.JPG");
    	
        
     	
        img_user_pic.setImageBitmap(bitmap); 	
        
         if(type.equals("student"))       
        {
            
        	student();
        	
        }
        
        
        logout.setOnClickListener(new Button.OnClickListener()  
       	{ 
           	
           	public void onClick (View v)
       		{ 
       		    
       		    		logout();	
       	 	}});
     	
     	
        
        btn_currency_to_point.setOnClickListener(new Button.OnClickListener()  
    	{ 
        	
        	public void onClick (View v)
    		{ 
    		    add_currency_for_point();
    		    			
    	 	}

			});
              
         
        btn_add_reason.setOnClickListener(new Button.OnClickListener()  
    	{ 
        	
        	public void onClick (View v)
    		{ 
    		    add_reason();
    		    			
    	 	}});
        
        
        btn_assgn_authority.setOnClickListener(new Button.OnClickListener() 
    	{ 
        	
        	public void onClick (View v)
    		{ 
    		  assign_authority();
        		
    		    			
    		}}); 
        btn_assign_reward.setOnClickListener(new Button.OnClickListener()  
    	{ 
        	
        	public void onClick (View v)
    		{ 
    		    		
    		    		assign_reward();	
    	 	}});
        
        
        btn_reward_earned.setOnClickListener(new Button.OnClickListener() 
    	{ 
        	
        	public void onClick (View v)
    		{ 
    		  reward_earned();
    		    			
    		}});
        btn_reward_given.setOnClickListener(new Button.OnClickListener()  
    	{ 
        	
        	public void onClick (View v)
    		{ 
    		    reward_given();
    		    			
    	 	}});
        
        
        btn_reward_balance.setOnClickListener(new Button.OnClickListener() 
    	{ 
        	
        	public void onClick (View v)
    		{ 
    		   reward_balance();
    		    			
    		}}); 
    }
    /*Type :Function
    name:student
    return type:void
    date:12-12-2011
    purpose:View when the user of user type student is logged in*/
    
    private void student() {
		// TODO Auto-generated method stub
		
                setContentView(R.layout.reward_student);
        
        
        String type1=ss1.getSessionuser_type();
        String user_name1=ss1.getSessionUserName();
        Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        txt_welcome_user1=(TextView) findViewById(R.id.welcome_user);  
        img_user_pic1=(ImageView) findViewById(R.id.pro_pic);
        btn_assign_reward1=(Button) findViewById(R.id.assign_reward);
        btn_reward_earned1=(Button) findViewById(R.id.reward_earned);
        btn_reward_given1=(Button) findViewById(R.id.reward_given);
        btn_reward_balance1=(Button) findViewById(R.id.reward_balance);
        logout1=(Button) findViewById(R.id.btnlogout);
        
        
        txt_welcome_user1.setText("Welcome "+user_name1);
        
        
        Bitmap bitmap1 =DownloadImage("http://bpsius:Bpsi%40123@bpsi.us/propic.JPG");
    	
        
     	
        img_user_pic1.setImageBitmap(bitmap1); 	
        
              
        
        logout1.setOnClickListener(new Button.OnClickListener()  
       	{ 
           	
           	public void onClick (View v)
       		{ 
       		    
       		    		logout();	
       	 	}});
     	
     	
        
      
        btn_assign_reward1.setOnClickListener(new Button.OnClickListener()  
    	{ 
        	
        	public void onClick (View v)
    		{ 
    		    		
    		    		assign_reward();	
    	 	}});
        
        
        btn_reward_earned1.setOnClickListener(new Button.OnClickListener() 
    	{ 
        	
        	public void onClick (View v)
    		{ 
    		  reward_earned();
    		    			
    		}});
        btn_reward_given1.setOnClickListener(new Button.OnClickListener()  
    	{ 
        	
        	public void onClick (View v)
    		{ 
    		    reward_given();
    		    			
    	 	}});
        
        
        btn_reward_balance1.setOnClickListener(new Button.OnClickListener() 
    	{ 
        	
        	public void onClick (View v)
    		{ 
    		   reward_balance();
    		    			
    		}}); 
	}

    /*Type :Function
    name:DownloadImage
    return type:void
    date:12-12-2011
    purpose:Downloading image from path*/
	private Bitmap DownloadImage(String URL)
    {        
        Bitmap bitmap = null;
        InputStream in = null;        
        try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();  
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return bitmap;                
    }
    
    private InputStream OpenHttpConnection(String urlString) 
    throws IOException
    {
        InputStream in = null;
        int response = -1;
               
        URL url = new URL(urlString);     
        URLConnection conn = url.openConnection();
                 
        if (!(conn instanceof HttpURLConnection))                     
            throw new IOException("Not an HTTP connection");
        
        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect(); 

            response = httpConn.getResponseCode();                 
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();                                 
            }                     
        }
        catch (Exception ex)
        {
        	img_user_pic = (ImageView) findViewById(R.id.pro_pic);
            throw new IOException("Error connecting");            
        }
        return in;     
    }
/*Defining intents to be called for button click*/
    private void add_currency_for_point() 
    {
		
    	Intent i1 = new Intent(this,redeem_pnt_to_currency.class);
	    startActivity(i1);
	}
       
    private void add_reason()
	{
		Intent i1 = new Intent(this,add_reason.class);
	    startActivity(i1);

	}
    private void assign_authority()
	{
    	
    	
		Intent i1 = new Intent(this,assign_authority.class);
		
	    startActivity(i1);
	    

	}
    private void assign_reward()
	{
    	
    	
		Intent i1 = new Intent(this,assign_reward.class);
	    startActivity(i1);

	}
    private void reward_earned()
	{
		Intent i1 = new Intent(this,rewards_earned.class);
	    startActivity(i1);

	}
    private void reward_given()
	{
		Intent i1 = new Intent(this,rewards_given.class);
	    startActivity(i1);

	}
    private void reward_balance()
	{
		Intent i1 = new Intent(this,reward_balance.class);
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
    //overriding devices back button
    @Override
	public void onBackPressed()
	{
		Intent i=new Intent(this,LoginNew.class);
		startActivity(i);
		return;
	}
    
    
}
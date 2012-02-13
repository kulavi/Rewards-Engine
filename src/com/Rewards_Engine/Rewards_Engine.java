//Starting screen of the project which displays a login button

package com.Rewards_Engine;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Rewards_Engine extends Activity  {  
	
	Button login;
	
	 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
          
        login=(Button)findViewById(R.id.login);  
        login.setOnClickListener(new Button.OnClickListener()        
    	{ 
        	
        	public void onClick (View v)
    		{ 
    		  loginnew();    
    		    			
    	 	}});
        
        
    }
    
    
    
    
    
    /*
    type:function
    name:loginnew
    parameter:no parameters
    returns:void
    date:29-06-2011
    purpose: Calls the LoginNew activity
    */
    
    private void loginnew()
	{
		Intent i = new Intent(this,LoginNew.class);
	    startActivity(i);

	}
      
    @Override
	public void onBackPressed()
	{
		Intent i=new Intent(this,Rewards_Engine.class);
		startActivity(i);
		return;
	}
    
   
    
   
}
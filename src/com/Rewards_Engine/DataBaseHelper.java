//Database file containing functions.

package com.Rewards_Engine;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper extends SQLiteOpenHelper 
{

	static final String dbName="REWARD_ENGINES";  
		
	static final String reasonTable="reasons";
	static final String rid1="reasonid1";
	static final String rid="reasonid";
	static final String reason="reason";
		
	
	static final String reward_engine_outTable="reward_engin_out";
	static final String id1="id1";
	static final String rec_name="reciever";
	static final String rdate="reward_date";
	static final String rpoint="reward_point";
	static final String rreasonid="rreasonid";
	
	
	static final String reward_engines="reward_engines";
	static final String ids="ids";
	static final String recnames="recnames";
	static final String reward_dates="reward_dates";
	static final String points="points";
	static final String reasonids="reasonids";
	static final String flags="flags";
	
	
	static final String reward_engine_inTable="reward_engin_in";
	static final String id1_in="id1_in";
	static final String rec_name_in="reciever_in";
	static final String rcourseid_in="rcourseid_in";
	static final String rdate_in="reward_date_in";
	static final String rpoint_in="reward_point_in";
	static final String rreasonid_in="rreasonid_in";
	
	
	static final String reward_engine="reward_engine";
	static final String id="id";
	static final String recname="recname";
	static final String reward_date="reward_date";
	static final String point="point";
	static final String reasonid="reasonid";
	static final String flag="flag";
	
	
	
		
		
	static final String viewEmps="ViewEmps";
	
		 
	public DataBaseHelper(Context context) 
	{
		super(context, dbName, null,33);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) { 
		// TODO Auto-generated method stub
				
		db.execSQL("CREATE TABLE "+reasonTable+" ("+rid1+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+rid+ " INTEGER, "+reason+ " TEXT)");
		db.execSQL("CREATE TABLE "+reward_engine_outTable+" ("+id1+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+rec_name+ " TEXT,"+rdate+ " TEXT, "+rpoint+ " INTEGER, "+rreasonid+ " INTEGER)");
		db.execSQL("CREATE TABLE "+reward_engine_inTable+" ("+id1_in+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+rec_name_in+ " TEXT, "+rcourseid_in+ " INTEGER, "+rdate_in+ " TEXT, "+rpoint_in+ " INTEGER, "+rreasonid_in+ " INTEGER)");
		db.execSQL("CREATE TABLE "+reward_engine+" ("+id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+recname+ " TEXT, "+reward_date+ " TEXT, "+point+ " INTEGER, "+reasonid+ " INTEGER, "+flag+ " TEXT)");
		db.execSQL("CREATE TABLE "+reward_engines+" ("+ids+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+recnames+ " TEXT, "+reward_dates+ " TEXT, "+points+ " INTEGER, "+reasonids+ " INTEGER, "+flags+ " TEXT)");
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		onCreate(db);
	}
	
	public DataBaseHelper open() throws SQLException
    {
    	SQLiteDatabase db=this.getWritableDatabase();
    	return this;
    }
	public void createreward_engine()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.execSQL("CREATE TABLE "+reward_engine+" ("+id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+recname+ " TEXT, "+reward_date+ " TEXT, "+point+ " INTEGER, "+reasonid+ " INTEGER, "+flag+ " TEXT)");
		
	}
	public void createreward_engines()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.execSQL("CREATE TABLE "+reward_engines+" ("+ids+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+recnames+ " TEXT, "+reward_dates+ " TEXT, "+points+ " INTEGER, "+reasonids+ " INTEGER, "+flags+ " TEXT)");
		
	}
	public void createreason()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.execSQL("CREATE TABLE "+reasonTable+" ("+rid1+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+rid+ " INTEGER, "+reason+ " TEXT)");
		
	}
	
	public void createreward_engine_in()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.execSQL("CREATE TABLE "+reward_engine_inTable+" ("+id1_in+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+rec_name_in+ " TEXT, "+rcourseid_in+ " INTEGER, "+rdate_in+ " TEXT, "+rpoint_in+ " INTEGER, "+rreasonid_in+ " INTEGER)");
		
	}
	
	public void createreward_engine_out()
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.execSQL("CREATE TABLE "+reward_engine_outTable+" ("+id1+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+rec_name+ " TEXT,"+rdate+ " TEXT, "+rpoint+ " INTEGER, "+rreasonid+ " INTEGER)");
		
	}
	
	void Insertreward_engine_out(String  recname,String strdate,int point,int reasonid)
	 {
		 SQLiteDatabase myDB = this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 
			cv.put(rec_name,recname);
			cv.put(rdate,strdate);
			cv.put(rpoint,point);
			cv.put(rreasonid,reasonid);
			
			
			myDB.insert(reward_engine_outTable,null,cv);
				
						
	 }
	 
	 void Insertreward_engine_in(String  recname,int cid,String strdate,int point,int reasonid)
	 {
		 SQLiteDatabase myDB = this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 
			cv.put(rec_name_in,recname);
			cv.put(rcourseid_in,cid);
			cv.put(rdate_in,strdate);
			cv.put(rpoint_in,point);
			cv.put(rreasonid_in,reasonid);
			myDB.insert(reward_engine_inTable,null,cv);
				
						
	 }
	 void Insertreward_engine(String recnames,String strdate,String points,String reasonids,String flags)
	 {
		 SQLiteDatabase myDB = this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 
			cv.put(recname,recnames);
			cv.put(reward_date,strdate);
			cv.put(point,points);
			cv.put(reasonid,reasonids);
			cv.put(flag,flags);
			myDB.insert(reward_engine,null,cv);
				
						
	 }
	 void Insertreward_engines(String recnames1,String strdate1,String points1,String reasonids1,String flags1)
	 {
		 SQLiteDatabase myDB = this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 
			cv.put(recnames,recnames1);
			cv.put(reward_dates,strdate1);
			cv.put(points,points1);
			cv.put(reasonids,reasonids1);
			cv.put(flags,flags1);
			myDB.insert(reward_engines,null,cv);
				
						
	 }
	 
	 
	 void Insertreason(int id,String reason1)
	 {
		 SQLiteDatabase myDB = this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 
			cv.put(rid,id);
			cv.put(reason,reason1);
			
			
			myDB.insert(reasonTable,null,cv);
			 
			
					
	 }
	
	 
	 void deletereason()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		
		 db.execSQL("DROP TABLE IF EXISTS "+reasonTable);
		 createreason();
		 
	 }
	 void deletereward_engine()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		
		 db.execSQL("DROP TABLE IF EXISTS "+reward_engine);
		 createreward_engine();
		 
	 }
	 void deletereward_engines()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		
		 db.execSQL("DROP TABLE IF EXISTS "+reward_engines);
		 createreward_engines();
		 
	 }
	 void deletereward_engine_out()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		
		 db.execSQL("DROP TABLE IF EXISTS "+reward_engine_outTable);
		 createreward_engine_out();
		 
	 }
	 void deletereward_engine_in()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		
		 db.execSQL("DROP TABLE IF EXISTS "+reward_engine_inTable);
		 createreward_engine_in();
		 
	 }
	 
	 public Cursor selectall_positive_flag()
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT * FROM "+reward_engine, null);
		 return c;                                                 
	 }
	 /*public Cursor selectall_negative_flag(String str)
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT * FROM "+reward_engine+ " where "+flag+" = '"+str+"'", null);
		 return c;                                                 
	 }*/
	 
	 public Cursor selectreasonid(String reason1)
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+rid+" FROM "+reasonTable+ " where "+reason+" = '"+reason1+"'", null);
		 return c;                                                 
	 }
	 public Cursor selectdistinctusersforreward()
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT distinct "+recnames+" FROM "+reward_engines, null);
		 return c;                                                 
	 }
	 public Cursor selectusersforrewardgiven(String name)
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT * FROM "+reward_engine_outTable+ " where "+rec_name+" = '"+name+"'", null);
		 return c;                                                 
	 }
	 
	 public Cursor selectall_fromengines(String name)  
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();   
		 Cursor c=db.rawQuery("SELECT * FROM "+reward_engines+ " where "+recnames+" = '"+name+"'", null);
		 return c;                                                 
	 }
	
	 public Cursor selectsumpointsforreward(String name)
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT sum( "+points+" )FROM "+reward_engines+ " where "+recnames+" = '"+name+"'", null);
		 return c;                                                 
	 } 
	 
	 public Cursor selectreason(int Rid)
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+reason+" FROM "+reasonTable+ " where "+rid+" = "+Rid, null);
		 return c;                                                 
	 }
	 
	 public Cursor selectid_for_reason(String reas)  
	 {
		
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+rid+" FROM "+reasonTable+ " where "+reason+" = '"+reas+"'", null);
		 return c;                                                 
	 }
	 
	
}



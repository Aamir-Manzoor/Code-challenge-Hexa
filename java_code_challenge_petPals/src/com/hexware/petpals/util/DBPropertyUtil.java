package com.hexware.petpals.util;
import java.io.FileInputStream;
import java.util.Properties;

import com.hexaware.petpals.exception.FileHandlingException;

public class DBPropertyUtil  {
	
	public static String getDriver(String filename) throws Exception {
    	FileInputStream fis=new FileInputStream(filename);
    	Properties props=new Properties();
    	props.load(fis);
    	
    	return props.getProperty("driver");
    }
	
	
	
        public static String getConnectionString(String filename) throws Exception {
      
        	FileInputStream fis = null;
        	Properties props;
        	try{
        		 fis=new FileInputStream(filename);
            	 props=new Properties();
        		props.load(fis);
        	}
        	catch(Exception e) {
        	
        	    throw new FileHandlingException("File not found: " + e.getMessage());
        	}
        	finally {
                if (fis != null) {
                    fis.close(); 
                }
            }
        	String connectionString=props.getProperty("protocol")+"//"+props.getProperty("host")+":"+ props.getProperty("port")+"/"+props.getProperty("database")
        	+"?user="+props.getProperty("user")+"&password="+props.getProperty("password");
        	return connectionString;
        }
        
}

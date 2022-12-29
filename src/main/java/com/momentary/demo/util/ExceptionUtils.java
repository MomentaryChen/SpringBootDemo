package com.momentary.demo.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {
	ExceptionUtils instance = null;
	
	private ExceptionUtils() {}
	
	public ExceptionUtils getInstance() {
		if(instance == null) {
			synchronized(ExceptionUtils.class){
                if(instance == null){
                    instance = new ExceptionUtils();
                }    
            }
		}
		return instance;
	}
	
	public static String getStackTrace(Throwable throwable) {
		StringWriter sWriter = new StringWriter();
		PrintWriter pWriter = new PrintWriter(sWriter, true);
		
		throwable.printStackTrace(pWriter);
		return sWriter.getBuffer().toString();
	}

}

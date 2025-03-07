package it.pi.gamma.project.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	
	static final String DASH = "-";
	
	public static final String DATE_FORMAT_YYYY_MM_DD_WITH_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	
	public static String getTimeStamp(){	
		return String.valueOf(Calendar.getInstance().getTimeInMillis());
	}

    public static String getCurrentTimeStamp() {
        return new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_WITH_SSS).format(Calendar.getInstance().getTime());
    }

    public static void generateCustomAccessToken(String accessToken, String username) {
    	accessToken =  username+UUID.randomUUID().toString();
    }
    
    public static String uuidTransaction() {
    	return UUID.randomUUID().toString();
    }
    
	public static String queueCode(String applicationCode, String code) {
		return applicationCode+DASH+code;
	}
	
    public static String parseObjectToJson(Object input) throws JsonProcessingException {
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	
    	return objectMapper.writeValueAsString(input);
    	
    }
	
    public static <T> T parseJsonToObject(String json, Class<T> input) throws IOException {
    	
    	ObjectMapper objectMapper = new ObjectMapper();
      	
    	return objectMapper.readValue(json, input);	
    }

}
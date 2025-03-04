package it.pi.gamma.project.util;

import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RestUtil {
	
	private final String CONTENT_TYPE = "Content-type";
	
	public <T> T execute(String url, String request, Class<T> returnType) throws ParseException, IOException {
		
		log.info("[INFO] Entering method: execute. Params [url: "+url+" ]");
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		HttpPost httpPost = new HttpPost(url);

		httpPost.setHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON);
		
		StringEntity entity = new StringEntity(request);
		
		httpPost.setEntity(entity);
		
		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

		HttpEntity responseEntity = httpResponse.getEntity();
		
		String response = EntityUtils.toString(responseEntity);

		httpClient.close();
		
		return Utils.parseJsonToObject(response, returnType);
	}
}

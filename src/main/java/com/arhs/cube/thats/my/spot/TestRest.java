package com.arhs.cube.thats.my.spot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class TestRest {

	public static void main(String args[]){

		RestTemplate restTemplate = new RestTemplate();
	
		String fooResourceUrl = "http://opendata.vdl.lu/odaweb/index.jsp?describe=1";

		HttpHeaders headers = new HttpHeaders();
		headers.set("User-Agent", "Mozilla/5.0");

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<BussWrapper> response = restTemplate.exchange(fooResourceUrl, HttpMethod.GET, entity, BussWrapper.class);
		
		/*ResponseEntity<String> response = 
				  restTemplate.getForEntity(fooResourceUrl, String.class);*/

		
		
		System.out.println(response.getBody());
		System.out.println("-----------");
		

	}
	
	private static void sendGet() throws Exception {

		String url = "http://opendata.vdl.lu/odaweb/index.jsp?describe=1";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}
	
}

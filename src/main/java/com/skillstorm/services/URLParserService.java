package com.skillstorm.services;

public class URLParserService {
	
	public int extractIdFromURL(String url) {
		String[] splitString = url.split("/");
		
		return Integer.parseInt(splitString[1]);
	}

}

package com.skillstorm.conf;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PopWarehouseCreds {
	
	private static PopWarehouseCreds instance;
	private String url;
	private String username;
	private String password;
	
	private PopWarehouseCreds() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (InputStream input = PopWarehouseCreds.class.getClassLoader().getResourceAsStream("application.properties")){
				Properties props = new Properties();
				props.load(input);
				
				this.url = props.getProperty("db.url");
				this.username = props.getProperty("db.username");
				this.password = props.getProperty("password");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		}
	}
	
	public static PopWarehouseCreds getInstance() {
		if (instance == null) {
			instance = new PopWarehouseCreds();
		}
		return instance;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, username, password);
	}

}

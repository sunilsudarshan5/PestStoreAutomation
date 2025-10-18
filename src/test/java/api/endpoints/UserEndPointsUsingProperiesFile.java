package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UserEndPointsUsingProperiesFile {
		
	
	//This method is created for - getting data from properties file (routes.properties)
	public static ResourceBundle getPropertiesFileUrl(){
		
		ResourceBundle resource = ResourceBundle.getBundle("routes"); //loading properties file
		return resource;
		
	}
	
	public static Response createUser(User payload) {
		
		String post_url = getPropertiesFileUrl().getString("create_url");
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(post_url);
		
		return response;
	}
	
	public static Response readUser(String userName) {
		
		String get_url = getPropertiesFileUrl().getString("get_url");
		
		Response response = given()
			.pathParams("username",userName)
		.when()
			.get(get_url);
		
		return response;
	}
	
	public static Response updateUser(String usernme, User payload) {
		
		String update_url = getPropertiesFileUrl().getString("update_url");
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", usernme)
				.body(payload)
			.when()
				.put(update_url);
		
		return response;
		
	}
	
	public static Response deleteUser(String userName) {
		
		String delete_url = getPropertiesFileUrl().getString("delete_url");
		
		Response response = given()
			.pathParam("username", userName)
		.when()
			.delete(delete_url);
		
		return response;
	}
	
}

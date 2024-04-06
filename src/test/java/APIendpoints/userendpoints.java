package APIendpoints;

import  io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured. RestAssured.given;

import java.util.ResourceBundle;

import APIpayload.user;




//create for CRUD

public class userendpoints {
	// method created for getting URL's from properties file
	static ResourceBundle getURL()
	{
	ResourceBundle routes= ResourceBundle.getBundle("routes"); // Load properties file
	return routes;
	}

	public static Response createUser (user payload) {
	String post_url=	getURL().getString("post_url");
	Response response=given()
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.body (payload)
	.when()	
	.post (Routes.post_url);
	return response;
	}
	
	public static Response readUser (String userName)
	{
		String get_url=getURL().getString("get_url");
	Response response=given()
	.pathParam("username", userName)
	.when()
	.get(Routes.get_url);
	return response;
	}
	public static Response updateUser (String userName, user payload)
	{
		String update_url=getURL().getString("update_url");
	Response response=given()
	.contentType (ContentType.JSON)
	.accept(ContentType.JSON)
	.pathParam("username", userName)
	.body (payload)
	.when()
	.put(Routes.update_url);
	
	return response;
	}
	
	
	public static Response deleteUser (String userName)
	{
		String delete_url=getURL().getString("delete_url");
	Response response=given()
	.pathParam("username", userName)
	.when()
	.delete (Routes.delete_url);
	return response;
	
}
}
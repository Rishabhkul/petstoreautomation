package APItest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import APIendpoints.userendpoints;
import APIpayload.user;
import io.restassured.response.Response;


public class usertest {
	Faker faker;
	user userPayload;
	public Logger logger;//for logs
	@BeforeClass
	public void setup()
	{	
	faker=new Faker();
	userPayload=new user();
	userPayload.setId (faker.idNumber().hashCode());
	userPayload.setUsername(faker.name().username());
	userPayload.setFirstName (faker.name().firstName());
	userPayload.setLastName (faker.name().lastName());
	userPayload.setEmail(faker.internet().safeEmailAddress());
	userPayload.setPassword(faker.internet().password (5, 10));
	userPayload.setPhone(faker.phoneNumber().cellPhone());
	//logs
	logger= LogManager.getLogger(this.getClass());
	
	
	}
	@Test(priority=1)
	
	public void testPostUser()
	{
		
	Response response=userendpoints.createUser (userPayload);
	Assert.assertEquals(response.getStatusCode(),200);

}
	@Test(priority=2)
	
	public void testGetUserByName()
	{
	Response response=userendpoints.readUser(this.userPayload.getUsername());
	response.then().log().all();
	//response.then().log().body().statusCode (200); chai assertions
	Assert.assertEquals(response.getStatusCode(),200);
	}
	
	
	@Test(priority=3)
	
	public void testUpdateUserByName()
	{
	//update data using payload
	userPayload.setFirstName (faker.name().firstName());
	userPayload.setLastName (faker.name().lastName());
	userPayload.setEmail (faker.internet().safeEmailAddress());
	Response response=userendpoints.updateUser (this.userPayload.getUsername(), userPayload);
	response.then().log().body().statusCode (200);
	Assert.assertEquals(response.getStatusCode(),200);
	
	
	//Checking data after update
	Response responseAfterupdate=userendpoints.readUser(this.userPayload.getUsername());
	Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority=4)
	
	public void testDeleteUserByName()
	{
	Response response=userendpoints.deleteUser(this.userPayload.getUsername()) 	;
	Assert.assertEquals(response.getStatusCode(),200);
	}	
}

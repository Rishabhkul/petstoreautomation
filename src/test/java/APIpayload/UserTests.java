package APIpayload;

import org.testng.Assert;

import org.testng.annotations.Test;

import API.utilities.Dataproviders;
import APIendpoints.userendpoints;
import io.restassured.response.Response;

public class UserTests {
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=Dataproviders.class)
	
	public void testPostuser(String userID, String userName, String fname, String Iname, String useremail, String pwd, String ph)
	{
	user userPayload=new user();
	userPayload.setId(Integer.parseInt(userID));
	userPayload.setUsername(userName);
	userPayload.setFirstName (fname);
	userPayload.setLastName (Iname);
	userPayload.setEmail (useremail);
	userPayload.setPassword (pwd);
	userPayload.setPhone (ph);
	Response response=userendpoints.createUser(userPayload);
	Assert.assertEquals(response.getStatusCode(),200);

}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=Dataproviders.class)
	
	public void testDeleteUserByName(String userName)
	{
		Response response=userendpoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(),200);
	}	
	
}

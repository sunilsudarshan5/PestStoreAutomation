package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {

	//dataProvider = "Data" ---> name of data provider
	//dataProviderClass = DataProviders.class  ---> location of data provider class it data povider is same class then no need to give it here
	//but in this case data provider class is in other package so we need to do this.
	
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userid, String username, String firstname, String lastname, String email, String password, String phoneno) {
	//parameter sequence should be same as excel sheet sequence
		
		User payload = new User();
		
		payload.setId(Integer.parseInt(userid));
		payload.setUsername(username);
		payload.setFirstName(firstname);
		payload.setLastName(lastname);
		payload.setEmail(email);
		payload.setPassword(password);
		payload.setPhone(phoneno);
		
		Response response = UserEndPoints.createUser(payload);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String username) {
		
		Response response = UserEndPoints.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
}

package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPointsUsingProperiesFile;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTestUsingPropertiesFileData {

	Faker faker;
	User userPayload;
	
	public Logger logger;
		
	@BeforeClass
	public void setUpData() {

		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

		logger = LogManager.getLogger(this.getClass());
	}

	@Test (priority = 1)
	public void testPostUser() {
		
		logger.info("============ user create ==================");
		
		Response response = UserEndPointsUsingProperiesFile.createUser(userPayload);
				response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("============ user created ==================");
	}
	
	@Test(priority = 2)
	public void testGetUser() {
		
		logger.info("============ getting the user details ==================");
		
		Response response = UserEndPointsUsingProperiesFile.readUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("============ got the user details ==================");
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName() {
		
		logger.info("============ updating the user details ==================");
		
		Response response = UserEndPointsUsingProperiesFile.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		//user after update
		logger.info("============ updated the user details ==================");
		
		Response responseAfterUpdate=UserEndPointsUsingProperiesFile.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}
	
	@Test(priority = 4)
	public void testDeleteUser() {
		
		logger.info("============ deleting the user details ==================");
		
		Response response = UserEndPointsUsingProperiesFile.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("============ deleted the user details ==================");
	}

}

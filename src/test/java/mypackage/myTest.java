package mypackage;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import java.util.ArrayList;
import java.util.List;

public class myTest {
	
	
	@Test(enabled = true, priority=1)
	public void simpleGetRequest()
	{
		given()
		 	.log()
		 	.all()
		.when()
			.get("http://numbersapi.com/random/year?json")
		.then()
			.statusCode(200)
			.log()
			.all();
	}
	
	
	@Test(enabled = true, priority=2)
	public void validateSchema()
	{
		String schemaPath = "expectedSchema/schema.json";
		given()
		 	.log()
		 	.all()
		.when()
			.get("http://numbersapi.com/random/year?json")
		.then()
			.assertThat()
			.statusCode(200)
			.body(matchesJsonSchemaInClasspath(schemaPath));
	}
	
	
	
	@Test(enabled = true, priority=3)
	public void createListAllElements() {
		List<Integer> numberList = new ArrayList<Integer>();
		List<Boolean> foundList = new ArrayList<Boolean>();
		List<String> textList = new ArrayList<String>();
		List<String> typeList = new ArrayList<String>();
		List<String> dateList = new ArrayList<String>();

		for (int i = 0; i < 5; i++) {
			JsonPath jsonPathEvaluator = given()
						   				.when()
						   					.get("http://numbersapi.com/random/year?json")
						   				.then()
						   					.assertThat()
						   					.statusCode(200)
						   					.extract()
						   					.jsonPath();
						   		
			
			numberList.add((Integer) jsonPathEvaluator.get("number"));
			foundList.add((Boolean) jsonPathEvaluator.get("found"));
			textList.add((String) jsonPathEvaluator.get("text"));
			typeList.add((String) jsonPathEvaluator.get("type"));
			dateList.add((String) jsonPathEvaluator.get("date"));
			
		}
		
		System.out.println("Desired result:");
		System.out.println("Text array list:"+textList);
		System.out.println("Number array list:"+foundList);
		System.out.println("Found array list:"+foundList);
		System.out.println("Type array list:"+typeList);
		System.out.println("Date array list:"+dateList);

	}


}

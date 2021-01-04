package stepDefenitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class MyStepdefs  extends Utils {

    RequestSpecification res;
    ResponseSpecification resspec;
    Response responce;
    TestDataBuild data = new TestDataBuild();
    static String place_id;

    @Given("Add Place Payload {string} {string} {string}")
    public void add_Place_Payload(String name, String language, String address) throws IOException {
        RestAssured.useRelaxedHTTPSValidation();

        res = given().spec(requestSpecification())
                .body(data.addPlacePayload(name, language, address));
    }

    @Given("DeletePlace Payload")
    public void deleteplacePayload() throws IOException {
        RestAssured.useRelaxedHTTPSValidation();

        res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }

    @When("User calls {string} with {string} http request")
    public void user_calls_something_with_post_http_request(String resource, String method) {

        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println("It is API used: " + resourceAPI.getResource());
        System.out.println("My change 1");
        System.out.println("My change 2");
        System.out.println("My change 3");
        System.out.println("My change 6");
        System.out.println("My change 3");

        System.out.println("Changes in branch");

        resspec = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        if (method.equalsIgnoreCase("POST")) {
            System.out.println("POST is used");
            responce = res.when()
                    .post(resourceAPI.getResource());
        }
        else if(method.equalsIgnoreCase("GET")){
            System.out.println("GET is used");
            responce = res.when()
                    .get(resourceAPI.getResource());
        }
        else if(method.equalsIgnoreCase("DELETE")){
            responce = res.when()
                    .delete(resourceAPI.getResource());
        }
    }

    @Then("The API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code_200(Integer int1)  {
        assertEquals(responce.getStatusCode(), 200);
        System.out.println("It is my responce: " + responce.asString());

    }

 @Then("{string} in responce body is {string}")
 public void in_Responce_Body_Is(String keyValue, String expectedValue) {
     assertEquals(getJsonPath(responce, keyValue), expectedValue);
 }


    @Then("Verify place_Id created maps to {string} used in {string}")
    public void verifyPlace_IdCreatedMapsToUsedIn(String expectedName, String resource) throws IOException {
        place_id = getJsonPath(responce, "place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", place_id);
        user_calls_something_with_post_http_request(resource, "GET");
        String actualName = getJsonPath(responce, "name");
        assertEquals(actualName, expectedName);
    }
}

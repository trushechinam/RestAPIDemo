package stepDefenitions;


import io.cucumber.java.Before;
import io.restassured.RestAssured;

import java.io.IOException;

public class Hooks {
    MyStepdefs steps = new MyStepdefs();

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        if(steps.place_id==null) {
            RestAssured.useRelaxedHTTPSValidation();
            steps.add_Place_Payload("Frontline house", "French-IN", "29, side layout, cohen 09");
            steps.user_calls_something_with_post_http_request("AddPlaceAPI", "POST");
            steps.verifyPlace_IdCreatedMapsToUsedIn("Frontline house", "GetPlaceAPI");
        }
    }
}

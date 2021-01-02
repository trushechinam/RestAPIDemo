Feature: Validating Plage I's

  @AddPlace @Regression
  Scenario Outline: Verify if Place is beeng Succesfully added using AddPlace API
    Given Add Place Payload "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "post" http request
    Then The API call is success with status code 200
    And "status" in responce body is "OK"
    And "scope" in responce body is "APP"
    And Verify place_Id created maps to "<name>" used in "GetPlaceAPI"

    Examples:
      | name            | language  | address                   |
      | Frontline house | French-IN | 29, side layout, cohen 09 |
      | AAhouse         | English   | World cross center        |


    @DeletePlace @Regression
    Scenario: Verify if Delete Place functionality is working
      Given DeletePlace Payload
      When User calls "DeletePlaceAPI" with "POST" http request
      Then The API call is success with status code 200
      And "status" in responce body is "OK"

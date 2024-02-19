@get_a_cart
Feature:Getting a cart
  Scenario: Test get a cart method functionality
    Given The user is on the correct baseUri
    When The user sends POST request to the create a cart endpoint
    And The user sends GET request to the get a cart endpoint
    Then The status code should be 200
    And The date is correctly displayed

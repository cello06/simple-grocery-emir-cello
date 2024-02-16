Feature: Getting a product
  Scenario: Test get a product method functionality
    Given The user is on the correct baseUri
    When The user send GET request with 4641 id to the get a product endpoint
    Then The status code should be 200
    And The product whose id is sent equals to the expected product
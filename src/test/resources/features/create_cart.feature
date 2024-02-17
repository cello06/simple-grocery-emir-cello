Feature:Creating a cart
  Scenario: Test creating a cart functionality
    Given The user is on the correct baseUri
    When The user sends POST request to the create a cart endpoint
    Then The status code should be 201
    And The cart id should not be null or empty
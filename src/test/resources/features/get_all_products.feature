Feature: Getting all product
  Scenario: Test get all products method functionality
    Given The user is on the correct baseUri
    When The user sends GET request to the get all products endpoint
    Then The status code should be 200
    And The product list should contain all products
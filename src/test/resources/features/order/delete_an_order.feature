@delete_an_order
Feature: Deleting an order
  Scenario Outline: Test delete an order method functionality
    Given The user is on the correct baseUri
    When  The user send POST request with "<name>" and "<email>" to the registration endpoint
    And The user sends POST request to the create a cart endpoint
    And The user sends POST request with 4646 product id to add item to the cart endpoint
    And The user sends POST request with "Özgür" to create an order end point
    And The user sends DELETE request to delete an order endpoint
    Then The status code should be 204
    And Verify that the order is successfully deleted
    Examples:
      | name  | email                        |
      | merve | merve_mersinli1234@gmail.com |
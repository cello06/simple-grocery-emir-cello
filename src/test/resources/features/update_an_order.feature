Feature: Updating an order
  Scenario Outline: Test Update order method functionality
    Given The user is on the correct baseUri
    When  The user send POST request with "<name>" and "<email>" to the registration endpoint
    And The user sends POST request to the create a cart endpoint
    And The user sends POST request with 4646 product id to add item to the cart endpoint
    And The user sends POST request with "Özgür" to create an order end point
    And The user sends PATCH request with new "Team-1" customer name to the update order end point
    Then The status code should be 204
    And Verify that whether customer name is changed or not
    Examples:
      | name | email             |
      | Musa | musa123@gmail.com |
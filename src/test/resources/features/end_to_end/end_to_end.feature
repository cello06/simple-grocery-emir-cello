@end-to-end
Feature: End to end test of simple grocery API
  Scenario Outline: Test all method of simple grocery API functionality
    Given The user is on the correct baseUri
    When  The user send POST request with "<name>" and "<email>" to the registration endpoint
    Then The status code should be 201
    And The token should not be null or empty

    When The user sends POST request to the create a cart endpoint
    Then The status code should be 201
    And The cart id should not be null or empty


    When The user sends POST request with 1225 product id to add item to the cart endpoint
    And The user sends POST request with 4641 product id to add item to the cart endpoint
    And The user sends POST request with 4646 product id to add item to the cart endpoint
    Then The status code should be 201
    And The item id is not null or empty


    When The user sends POST request with "emacöm" to create an order end point
    Then The status code should be 201
    And Created should be true
    And The order id should not be null or empty

    When The user sends GET request with order id to get a single order end point
    Then The status code should be 200
    And The order id, item id and customer name are true

    When The user sends PATCH request with new "Team-1" customer name to the update order end point
    Then The status code should be 204
    And Verify that whether customer name is changed or not

    When The user sends DELETE request to delete an order endpoint
    Then The status code should be 204
    And Verify that the order is successfully deleted
    Examples:
      | name  | email                 |
      | CELLO | cellooo0006@gmail.com |
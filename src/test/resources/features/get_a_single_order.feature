Feature: Getting a single order
  Scenario Outline: Test get a single order method functionality
    Given The user is on the correct baseUri
    When  The user send POST request with "<name>" and "<email>" to the registration endpoint
    And The user sends POST request to the create a cart endpoint
    And The user sends POST request with 4646 product id to add item to the cart endpoint
    And The user sends POST request with 4641 product id to add item to the cart endpoint
    And The user sends POST request with "emir" to create an order end point
    And The user sends GET request with order id to get a single order end point
    Then The status code should be 200
    And The order id, item id and customer name are true
    Examples:
      | name  | email                    |
      | cello | emir1212123123@gmail.com |
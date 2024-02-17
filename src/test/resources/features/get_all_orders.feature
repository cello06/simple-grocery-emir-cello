Feature: Getting all orders
  Scenario Outline: Test Get all orders method functionality
    Given The user is on the correct baseUri
    When  The user send POST request with "<name>" and "<email>" to the registration endpoint
    And The user sends POST request to the create a cart endpoint
    And The user sends POST request with 4646 product id to add item to the cart endpoint
    And The user sends POST request with 4641 product id to add item to the cart endpoint
    And The user sends POST request with "emir" to create an order end point
    And The user sends POST request to the create a cart endpoint
    And The user sends POST request with 4646 product id to add item to the cart endpoint
    And The user sends POST request with 4641 product id to add item to the cart endpoint
    And The user sends POST request with "gürol" to create an order end point
    And The user sends GET request to get all orders end point
    Then The status code should be 200
    And The order ids, item ids and customer names are true
    Examples:
      | name | email          |
      | Inar | inz2@gmail.com |

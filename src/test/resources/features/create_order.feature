Feature: Creating an order

  Scenario Outline: Test create an order method functionality
    Given The user is on the correct baseUri
    When  The user send POST request with "<name>" and "<email>" to the registration endpoint
    And The user sends POST request to the create a cart endpoint
    And The user sends POST request with 4646 product id to add item to the cart endpoint
    And The user sends POST request with "özgür" to create an order end point
    Then The status code should be 201
    And Created should be true
    And The order id should not be null or empty
    Examples:
      | name  | email              |
      | gurol | gurol318@gmail.com |

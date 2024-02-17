Feature: Adding a item to cart
  Scenario: Test add an item to cart method functionality
    Given The user is on the correct baseUri
    When The user sends POST request to the create a cart endpoint
    And The user sends POST request with 4646 product id to add item to the cart endpoint
    Then The status code should be 201
    And The item id is not null or empty
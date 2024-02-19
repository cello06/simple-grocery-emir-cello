@get_items_in_cart
Feature: Getting items in cart
  Scenario: Test get items in cart method functionality
    Given The user is on the correct baseUri
    When The user sends POST request to the create a cart endpoint
    And The user sends POST request with 4646 product id to add item to the cart endpoint
    And The user sends POST request with 4641 product id to add item to the cart endpoint
    And The user sends POST request with 1225 product id to add item to the cart endpoint
    And The user send GET request get cart items endpoint
    Then The status code should be 200
    And The added items are displayed correctly
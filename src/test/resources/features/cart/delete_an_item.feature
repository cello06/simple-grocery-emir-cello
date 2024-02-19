@delete_an_item
Feature: Delete item
  Scenario: Delete an item method functionality
    Given The user is on the correct baseUri
    When The user sends POST request to the create a cart endpoint
    And The user sends POST request with 4646 product id to add item to the cart endpoint
    And The user sends POST request with 4641 product id to add item to the cart endpoint
    And The user sends DELETE request to delete an item endpoint
    Then The status code should be 204
    And The deleted item should not be displayed in items list
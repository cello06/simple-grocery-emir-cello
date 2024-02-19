@client_registration
Feature: Client registration

  Scenario Outline: Test new token is created successfully
    Given The user is on the correct baseUri
    When  The user send POST request with "<name>" and "<email>" to the registration endpoint
    Then The status code should be 201
    And The token should not be null or empty
    Examples:
      | name | email             |
      | Emir | emo253as@gmail.com |


  Scenario: Test client registration with invalid endpoint
    Given The user is on the correct baseUri
    When User sends a request with wrong end-points.
    Then The status code should be 404


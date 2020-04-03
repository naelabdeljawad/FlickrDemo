#Author: ilanpearlman@mobileodt.com
@example
Feature: Google GET

  Scenario: Google home page get method
    Given url 'https://www.google.com'
    When method get
    Then status 200
    And print response



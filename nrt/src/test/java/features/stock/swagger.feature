Feature: swagger

  Background:
    * url 'http://localhost:8080'

  Scenario: test if swagger is up
    Given path 'v2/api-docs'
    When method GET
    Then status 200
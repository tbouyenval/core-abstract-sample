Feature: Stock service

  Background:
    * url 'http://localhost:8080'

  Scenario: Retrieve all stock
    Given path 'stock'
    When method GET
    Then status 200
    And match $[0].stockState == 'FULL'
    And match $[0].shoes contains {"name":"NewFeel","size": 45,"color":"BLACK","quantity":5}

  Scenario: change newFeel stock
    Given path 'stock'
    And request {"shoes": [{"name":"NewFeel","size":"45","color":"BLACK","quantity":"3"}]}
    When method PATCH
    Then status 204

  Scenario: Check if stock change
    Given path 'stock'
    When method GET
    Then status 200
    And match $[0].stockState == 'SOME'
    And match $[0].shoes contains {"name":"NewFeel","size": 45,"color":"BLACK","quantity":3}

  Scenario: reset stock
    Given path 'stock'
    And request {"shoes": [{"name":"NewFeel","size":"45","color":"BLACK","quantity":"5"}]}
    When method PATCH
    Then status 204
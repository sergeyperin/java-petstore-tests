Feature: Store - Access to Pet Store order

  Store - Check Inventory status

  Scenario: As a Store Owner, I can check my inventory successfully
    Given I hit the "/store/inventory"
    When Request should submit and Positive API response should received "200"
    Then Response should display

  Scenario: As a Store Owner, I can not check my inventory successfully
    Given I hit the "/store/inventry"
    When Request should submit and Positive API response should received "404"
    Then Response should display


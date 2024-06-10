Feature: Store - Access to Pet Store order

  Store - Place a bet for a pet

  Scenario: As a Store Owner, I would like to place an order for a pet
    Given I would like to place an order for a pet:
      |url        | /store/order              |
      |orderId    | 7                         |
      |petId      | 1                         |
      |quantity   | 10                        |
      |shipDate   | 2024-06-10T08:10:06.239Z  |
      |status     | placed                    |
      |complete   | true                      |
    When Request should submit and Positive API response should received "200"
    Then Response should display

  Scenario: As a Store Owner, I would like to place an order for a pet without pet order id since it is generated automatically
    Given I would like to place an order for a pet:
      |url        | /store/order              |
      |petId      | 1                         |
      |quantity   | 10                        |
      |shipDate   | 2024-06-10T08:10:06.239Z  |
      |status     | placed                    |
      |complete   | true                      |
    When Request should submit and Positive API response should received "200"
    Then Response should display


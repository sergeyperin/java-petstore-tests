Feature: Store - Access to Pet Store order

  Store - Delete order for a pet

  Scenario: As a Store Owner, I can delete order by a order id
    Given I would like to place an order for a pet:
      |url        | /store/order              |
      |orderId    | 66                        |
      |petId      | 1                         |
      |quantity   | 10                        |
      |shipDate   | 2024-06-10T08:10:06.239Z  |
      |status     | placed                    |
      |complete   | true                      |
    When I would like to delete my order by "66"
    Then Request should submit and Positive API response should received "200"
    And Response should display
    # trying to find deleted order by id, it should return 404
    When I would like to get the order information by "66"
    And Request should submit and Positive API response should received "404"

  Scenario: As a Store Owner, I can not delete order by none existing order id
    When I would like to delete my order by "69"
    Then Request should submit and Positive API response should received "404"
    And Response should display


Feature: Store - Access to Pet Store order

  Store - Find order information by order id

  Scenario: As a End User, I can fetch order information by order id successfully
    Given I would like to place an order for a pet:
      |url        | /store/order              |
      |orderId    | 77                        |
      |petId      | 1                         |
      |quantity   | 10                        |
      |shipDate   | 2024-06-10T08:10:06.239Z  |
      |status     | placed                    |
      |complete   | true                      |
    When Request should submit and Positive API response should received "200"
    And My order is placed, I would like to get the order information by "77"
    And Request should submit and Positive API response should received "200"
    Then Response should display

  Scenario: As a End User, I can not fetch order information by none existing order id
    # creating to be deleted to make sure we are searching by none existing eventually.
    Given I would like to place an order for a pet:
      |url        | /store/order              |
      |orderId    | 55                        |
      |petId      | 1                         |
      |quantity   | 10                        |
      |shipDate   | 2024-06-10T08:10:06.239Z  |
      |status     | placed                    |
      |complete   | true                      |
    When Request should submit and Positive API response should received "200"
    # deleting to make sure it is deleted
    When I would like to delete my order by "55"
    Then Request should submit and Positive API response should received "200"
    # deleting by none exiting
    And I would like to get the order information by "55"
    And Request should submit and Positive API response should received "404"


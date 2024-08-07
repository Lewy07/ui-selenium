Feature: Task Feature

  Scenario: User add product to cart
    Given User open page
    When User pick "Urządzenia" form main navigation
    And User click "Smartwatche" option in "Bez abonamentu" category
    Then User should be ona a page with list of smartwatches
    When User click first possible element
    Then User should be ona a page with details of the product
    And User see prices
    When User add product to the cart
    Then User should be ona a page with cart
    And User check price
    When User go to the main page
    Then User check count of elements in bucket is equal 1

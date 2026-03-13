Feature: Send a kudo from the web application
  As a SofkianOS user
  I want to submit a kudo from the UI
  So that the request is accepted asynchronously by the backend

  Scenario: Submit a valid kudo and validate it in history
    Given the user opens the application
    When the user sends a kudo with:
      | from                | to                    | category   | message                                      |
      | Christopher Pallo   | Santiago              | Teamwork   | Gracias por tu apoyo en la entrega del sprint |
    And the actor opens the kudos history
    And the actor views the kudos history
    Then the actor should see the sent kudo in the kudos history

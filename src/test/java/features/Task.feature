Feature: User Sign Up


  Scenario Outline: Verify that user can sign up successfully
    Given I am on the Demoblaze store sign up page
    When I click on the Sign Up button
    And I fill in the sign up form with username "<username>" and password "<password>"
    And I submit the form
    Then I should see the message "<SuccessFul_Message>"

    Examples:
      | username                 | password          | SuccessFul_Message  |
      | mohamed01610               | 12345678          | Sign up successful. |
      
      
      
      
   Scenario Outline: Verify that two products are purchased successfully
    Given User is on the login page
    When User Fill username "<username>" and password "<password>" and click at log in
    Then User "<username>" should be logged in successfully


    When User selects Laptops category and Choose First product and click addToCart 
    Then First product should be added successfully
    When User Choose Second product  and click addToCart 
    Then Second product should be added successfully


    When User Click on Cart button in header
    Then User view products  and total amount is calculated correctly

    When User Click on Place Holder  button in header
    Then User view total amount is calculated correctly

    When I fill in the purchase form :
      | Name      | Country    | City     | Credit Card         | Month | Year |
      | mohamed   | Egypt      | Cairo    | 1256225589          | 10    | 1998 |
  
    And  Click on Purchase button in place holder Form
    Then User should see the message "<Purchase_SuccessFu_Message>"

    Examples:
      | username       | password          |  Purchase_SuccessFu_Message   |
      | mohamed01610     | 12345678          |  Thank you for your purchase  |

   
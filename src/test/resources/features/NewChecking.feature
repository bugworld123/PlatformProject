Feature: Creating a new checking account

  Scenario: Create a standard individual checking account
    Given the user logged in as "aidaiabd123@gmail.com" "Aidai2608@"
    When the user creates a new checking account with following data
    |checkingAccountType|accountOwnership|accountName              |initialDepositAmount|
    |Standard Checking  |Individual      |Elon Musk Second Checking|1000.00            |
    Then the user should see green "Successfully created new Standard Checking account named Elon Musk Second Checking" message
    And the user should see newly added account cart
    |accountName              |accountType       |ownership |accountNumber|interestRate|balance |
    |Elon Musk Second Checking| Standard Checking|Individual|486133114    |0.0%        |1000.00 |
    And the user should see the following transactions
    |date             |category|description              | amount  | balance|
    |2023-11-27 16:25 |Income  |845324180 (DPT) - Deposit|1000.00 |1000.00|


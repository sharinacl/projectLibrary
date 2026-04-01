Feature: Lending books
  As a library administrator I want to lend books to users.

  Scenario: Administrator lends a book to a user
    Given an administrator is logged in
    When the administrator lends book with id 1 to user with id 1
    Then the loan should be visible in the user's loans

  Scenario: User views their borrowed books
    Given a user is logged in
    When the user views their loans
    Then they should see at least one book in their loan list
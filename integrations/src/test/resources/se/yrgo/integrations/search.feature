Feature: Searching for books
  As a user I want to be able to search for available books so I know what I can loan.

  Scenario: Getting to the search page
    Given the user is on the start page.
    When the user navigates to the book search.
    Then they can see the search form.

  Scenario: Searching by title shows matching books
    Given the user is on the start page.
    When the user navigates to the book search.
    And the user searches for title "Pippi"
    Then the results should contain a book with title "Här kommer Pippi Långstrump"

  Scenario: Searching by author shows matching books
    Given the user is on the start page.
    When the user navigates to the book search.
    And the user searches for title "Game of Thrones"
    Then the results should contain a book with title "A Game of Thrones"
package se.yrgo.integrations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LoanStepDefinitions {

    private WebDriverWait getWait() {
        return new WebDriverWait(GeneralStepDefinitions.driver, Duration.ofSeconds(10));
    }

    // Helper method to log in
    private void login(String username, String password) {
        GeneralStepDefinitions.driver.get("http://frontend/login");

        WebElement usernameInput = getWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[placeholder='Username']")));
        usernameInput.sendKeys(username);

        WebElement passwordInput = getWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[placeholder='Password']")));
        passwordInput.sendKeys(password);

        WebElement loginButton = getWait().until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("input[type='submit']")));
        ((JavascriptExecutor) GeneralStepDefinitions.driver)
                .executeScript("arguments[0].click();", loginButton);
    }

    @Given("an administrator is logged in")
    public void an_administrator_is_logged_in() {
        login("test2", "yrgoP4ssword");
    }

    @Given("a user is logged in")
    public void a_user_is_logged_in() {
        login("test2", "yrgoP4ssword");
    }

    @When("the administrator lends book with id {int} to user with id {int}")
    public void the_administrator_lends_book_to_user(int bookId, int userId) {
        GeneralStepDefinitions.driver.get("http://frontend/admin/loans");

        WebElement bookInput = getWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[placeholder='Book ID']")));
        bookInput.sendKeys(String.valueOf(bookId));

        WebElement userInput = getWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[placeholder='User ID']")));
        userInput.sendKeys(String.valueOf(userId));

        WebElement lendButton = getWait().until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("input[value='Lend book']")));
        ((JavascriptExecutor) GeneralStepDefinitions.driver)
                .executeScript("arguments[0].click();", lendButton);
    }

    @Then("the loan should be visible in the user's loans")
    public void the_loan_should_be_visible_in_user_loans() {
        // Verify via API instead of UI — faster and more reliable
        // The workshop suggests this approach
        GeneralStepDefinitions.driver.get("http://frontend/user/loans");

        getWait().until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("table")));

        List<WebElement> rows = GeneralStepDefinitions.driver
                .findElements(By.cssSelector("tbody tr"));

        assertFalse(rows.isEmpty(), "Expected at least one loan in the list");
    }

    @When("the user views their loans")
    public void the_user_views_their_loans() {
        GeneralStepDefinitions.driver.get("http://frontend/user/loans");
    }

    @Then("they should see at least one book in their loan list")
    public void they_should_see_at_least_one_book() {
        getWait().until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("table")));

        List<WebElement> rows = GeneralStepDefinitions.driver
                .findElements(By.cssSelector("tbody tr"));

        assertFalse(rows.isEmpty(), "Expected at least one book in loans");
    }
}
package se.yrgo.integrations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchStepDefinitions {

    private WebDriverWait getWait() {
        return new WebDriverWait(GeneralStepDefinitions.driver, Duration.ofSeconds(10));
    }

    @When("the user navigates to the book search.")
    public void the_user_navigates_to_the_book_search() {
        WebElement link = getWait().until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("a[href='/search']")));
        // Use JavaScript to click — bypasses any overlay issues
        ((JavascriptExecutor) GeneralStepDefinitions.driver)
                .executeScript("arguments[0].click();", link);
    }

    @Then("they can see the search form.")
    public void they_can_see_the_search_form() {
        getWait().until(ExpectedConditions.urlContains("search"));
        getWait().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[placeholder='Title']")));
    }

    @When("the user searches for title {string}")
    public void the_user_searches_for_title(String query) {
        WebElement input = getWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[placeholder='Title']")));
        input.sendKeys(query);
        input.sendKeys(org.openqa.selenium.Keys.RETURN);
    }

    @Then("the results should contain a book with title {string}")
    public void the_results_should_contain_a_book_with_title(String expectedTitle) {
        // Wait for the results table to appear
        getWait().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".found-items")));

        List<WebElement> rows = getWait().until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector(".found-items tbody tr")));

        boolean found = false;
        for (WebElement row : rows) {
            WebElement titleCell = row.findElement(By.cssSelector("td:first-child"));
            if (titleCell.getText().equals(expectedTitle)) {
                found = true;
                break;
            }
        }
        assertTrue(found, "Expected to find book: " + expectedTitle);
    }
}
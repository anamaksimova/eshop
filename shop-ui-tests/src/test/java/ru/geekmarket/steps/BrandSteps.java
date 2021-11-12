package ru.geekmarket.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.geekmarket.DriverInitializer;

import static org.assertj.core.api.Assertions.assertThat;

public class BrandSteps {

    private WebDriver webDriver = null;

    @Given("^I open web browser$")
    public void iOpenWebBrowser() throws Throwable {
        webDriver = DriverInitializer.getDriver();
    }

    @When("^I navigate to login\\.html page$")
    public void iNavigateToLoginHtmlPage() throws Throwable {
        Thread.sleep(3000);
        webDriver.get(DriverInitializer.getProperty("login.url"));
    }

    @When("^I click on login button$")
    public void iClickOnLoginButton() throws Throwable {
        Thread.sleep(3000);
        WebElement webElement = webDriver.findElement(By.id("btn-login"));
        webElement.click();
    }

    @When("^I provide username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void iProvideUsernameAsAndPasswordAs(String username, String password) throws Throwable {
        Thread.sleep(3000);
        WebElement webElement = webDriver.findElement(By.id("username"));
        webElement.sendKeys(username);
        webElement = webDriver.findElement(By.id("password"));
        webElement.sendKeys(password);
    }
//    @Then("^name should be \"([^\"]*)\"$")
//    public void nameShouldBe(String name) throws Throwable {
//        Thread.sleep(3000);
//        WebElement webElement = webDriver.findElement(By.id("dd_user"));
//        assertThat(webElement.getText()).isEqualTo(name);
//    }

    @Given("^any user logged in$")
    public void userLoggedIn() throws InterruptedException {
        Thread.sleep(3000);
        webDriver.findElement(By.id("dd_user"));
    }
    @When("^I click on brand button$")
    public void iClickOnBrandButton() throws Throwable {
        Thread.sleep(3000);
        WebElement webElement = webDriver.findElement(By.id("bran-btn"));
        webElement.click();
    }

    @When("^I click on add brand button$")
    public void iClickOnAddBrandButton() throws Throwable {
        Thread.sleep(3000);
        WebElement webElement = webDriver.findElement(By.id("brand-btn"));
        webElement.click();
    }

    @When("^I provide name as \"([^\"]*)\"$")
    public void iProvideNameAs(String name) throws Throwable {
        Thread.sleep(3000);
        WebElement webElement = webDriver.findElement(By.id("name"));
        webElement.sendKeys(name);

    }
    @When("^I click on submit button$")
    public void iClickOnSubmitButton() throws Throwable {
        Thread.sleep(3000);
        WebElement webElement = webDriver.findElement(By.id("submit-btn"));
        webElement.click();
    }
    @When("^I click on delete button to delete name as \"([^\"]*)\"$")
    public void iClickOnDeleteButton(String name) throws Throwable {
        Thread.sleep(3000);
        WebElement webElement = webDriver.findElement(By.id("delete-btn"));

        webDriver.findElement(By.tagName("td")).findElement(By.linkText("name")).findElement((By) By.id("delete-btn"));
        webElement.click();
    }

    @When("^click logout button$")
    public void clickLogoutButton() throws InterruptedException {
        Thread.sleep(3000);
        WebElement webElement = webDriver.findElement(By.id("btn-logout"));
        webElement.click();
    }

    @Then("^user logged out$")
    public void userLoggedOut() throws InterruptedException {
        Thread.sleep(3000);
        webDriver.findElement(By.id("username"));
        webDriver.findElement(By.id("password"));
    }

    @After
    public void quitBrowser() {
        webDriver.quit();
    }
}

package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.TechGlobalBasePage;
import pages.TechGlobalFrontendPage;
import pages.TechGlobalPaginationPage;
import utilities.Driver;

public class TechGlobalSteps {
    WebDriver driver;
    Actions actions;
    TechGlobalBasePage techGlobalBasePage;
    TechGlobalFrontendPage techGlobalFrontendPage;
    TechGlobalPaginationPage techGlobalPaginationPage;

    @Before
    public void setup() {
        driver = Driver.getDriver();
        actions = new Actions(driver);
        techGlobalBasePage = new TechGlobalBasePage();
        techGlobalFrontendPage = new TechGlobalFrontendPage();
        techGlobalPaginationPage = new TechGlobalPaginationPage();
    }

    @After
    public void tearDown() {
        Driver.quitDriver();
    }

    @Given("user is on {string}")
    public void userIsOn(String url) {
        driver.get(url);
    }

    @When("user moves to Practices header dropdown")
    public void userMovesToHeaderDropdown() {
        actions.moveToElement(techGlobalBasePage.dropdownHeader).perform();
    }

    @And("user clicks on {string} header dropdown option")
    public void userClicksOnHeaderDropdownOption(String option) {
        techGlobalBasePage.selectDropdownOption(option);
    }

    @Then("user should be navigated to {string}")
    public void userShouldBeNavigatedTo(String url) {
        Assert.assertEquals(url, driver.getCurrentUrl());
    }


    @And("user clicks on {string} card")
    public void userClicksOnCard(String cardOption) {
        techGlobalFrontendPage.selectCardByText(cardOption);
    }

    @And("user should see {string} heading")
    public void userShouldSeeHeading(String heading) {
        switch (heading) {
            case "Pagination":
                Assert.assertEquals(heading, techGlobalPaginationPage.mainHeading.getText());
                break;
            case "World City Populations 2022":
                Assert.assertEquals(heading, techGlobalPaginationPage.subHeading.getText());
                break;
            default:
                throw new NotFoundException("Invalid heading!");
        }
    }

    @And("user should see {string} paragraph")
    public void userShouldSeeParagraph(String paragraph) {
        Assert.assertEquals(paragraph, techGlobalPaginationPage.content.getText());
    }


    @And("user should see {string} button is disabled")
    public void userShouldSeeButtonIsDisabled(String btn) {
        switch (btn) {
            case "Previous":
                Assert.assertTrue(techGlobalPaginationPage.btnPrevious.isDisplayed());
                Assert.assertFalse(techGlobalPaginationPage.btnPrevious.isEnabled());
                break;
            case "Next":
                Assert.assertTrue(techGlobalPaginationPage.btnNext.isDisplayed());
                Assert.assertFalse(techGlobalPaginationPage.btnNext.isEnabled());
                break;
            default:
                throw new NotFoundException("Button not found!");
        }
    }

    @When("user clicks on {string} button")
    public void userClicksOnButton(String btn) {
        techGlobalPaginationPage.btnNext.click();
    }

    @And("user should see {string} button is enabled")
    public void userShouldSeeButtonIsEnabled(String btn) {
        switch (btn) {
            case "Previous":
                Assert.assertTrue(techGlobalPaginationPage.btnPrevious.isDisplayed());
                Assert.assertTrue(techGlobalPaginationPage.btnPrevious.isEnabled());
                break;
            case "Next":
                Assert.assertTrue(techGlobalPaginationPage.btnNext.isDisplayed());
                Assert.assertTrue(techGlobalPaginationPage.btnNext.isEnabled());
                break;
            default:
                throw new NotFoundException("Button not found!");
        }
    }

    @When("user clicks on {string} button till it becomes disabled")
    public void userClicksOnButtonTillItBecomesDisabled(String btn) {
        while(techGlobalPaginationPage.btnNext.isEnabled()) {
            techGlobalPaginationPage.btnNext.click();
        }
        Assert.assertFalse(techGlobalPaginationPage.btnNext.isEnabled());
    }

    @And("user should see city with info below and an image")
    public void userShouldSeeCityWithInfoBelowAndAnImage(DataTable data) {
        for(int i = 0; i < data.asList().size(); i++) {
            System.out.println(data.asList().get(i));
            System.out.println(data.asList().get(i+1));
            System.out.println(techGlobalPaginationPage.pageData.get(i).getText());
            String expected = data.asList().get(i);
            String actual = techGlobalPaginationPage.pageData.get(i).getText();

            Assert.assertEquals(expected, actual);
            Assert.assertTrue(techGlobalPaginationPage.image.isDisplayed());
        }
        techGlobalPaginationPage.btnNext.click();
    }
}

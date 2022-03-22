package com.epam.tc.hw5.steps;

import com.epam.tc.hw5.components.UsersTableComponent;
import com.epam.tc.hw5.context.TestContext;
import com.epam.tc.hw5.data.BaseData;
import com.epam.tc.hw5.utils.PropertyReader;
import com.epam.tc.hw5.voids.DifferentElementsPage;
import com.epam.tc.hw5.voids.IndexPage;
import com.epam.tc.hw5.voids.UserTablePage;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionStep {

    private final IndexPage indexPage;
    private DifferentElementsPage differentElementsPage;
    private UserTablePage userTablePage;
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ActionStep() {
        driver = TestContext.getInstance().getObject("driver", WebDriver.class);
        wait = TestContext.getInstance().getObject("wait", WebDriverWait.class);
        indexPage = new IndexPage(driver, wait);
    }

    @Given("I open JDI GitHub site")
    public void openIndexPage() {
        indexPage.open();
    }

    @And("I login as user {string}")
    public void login(String username) {
        PropertyReader propertyReader = new PropertyReader(BaseData.PROPERTIES_PATH);
        if (propertyReader.getUserName().equalsIgnoreCase(username)) {
            indexPage.header().getLoginComponent().login(
                    propertyReader.getLogin(), propertyReader.getPassword());
        }
    }

    @And("I click on {string} button in Header")
    public void clickItemInHeader(String link) {
        if (BaseData.SERVICE_MENU_ITEM.equals(link)) {
            indexPage.header().clickServiceLink();
        }
    }

    @And("I click on {string} button in Service dropdown")
    public void clickItemInServiceMenu(String link) {
        if (BaseData.DIFFERENT_ELEMENTS_ITEM.equals(link)) {
            indexPage.header().openDifferentElementsPage();
            differentElementsPage = new DifferentElementsPage(this.driver, this.wait);
            TestContext.getInstance().setObject("different_elements_page", differentElementsPage);
        } else if (BaseData.USER_TABLE_ITEM.equals(link)) {
            indexPage.header().openUserTablePage();
            userTablePage = new UserTablePage(this.driver, this.wait);
            TestContext.getInstance().setObject("user_table_page", userTablePage);
        }
    }

    @ParameterType(value = "(.*)")
    public List<String> checkboxesNames(String s) {
        return Arrays.stream(s.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @When("I select checkboxes {checkboxesNames} in the row of checkboxes on the Different Elements Page")
    public void selectCheckboxes(List<String> checkboxesNames) {
        differentElementsPage.clickCheckBoxes(checkboxesNames);
    }

    @And("I select radio button {string} in the row of radio buttons on the Different Elements Page")
    public void selectRadioButton(String radioButtonName) {
        differentElementsPage.clickRadioButton(radioButtonName);
    }

    @And("I select option {string} in the dropdown list on the Different Elements Page")
    public void selectDropdown(String dropdownName) {
        differentElementsPage.selectDropdown(dropdownName);
    }

    @When("I select {string} checkbox for {string}")
    public void selectCheckboxOnUserTablePage(String checkbox, String username) {
        UsersTableComponent component = userTablePage.usersTableComponent();
        if (checkbox.equalsIgnoreCase(component.getCheckboxLabel())) {
            component.selectCheckboxForUser(username);
        }
    }

}

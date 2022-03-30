package com.epam.tc.hw5.steps;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw5.context.TestContext;
import com.epam.tc.hw5.voids.DifferentElementsPage;
import com.epam.tc.hw5.voids.UserTablePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import java.util.List;
import org.assertj.core.api.SoftAssertions;

public class AssertionStep {

    private UserTablePage userTablePage;

    @Then("log rows for checkboxes {checkboxesNames} and checkboxes status {string} "
            + "should be displayed in the logs panel on the Different Elements Page")
    public void testCheckboxesSelected(List<String> checkboxesNames, final String status) {
        SoftAssertions softAssertions = new SoftAssertions();
        DifferentElementsPage differentElementsPage = TestContext.getInstance()
                .getObject("different_elements_page", DifferentElementsPage.class);
        checkboxesNames.forEach(box ->
            softAssertions.assertThat(
                    differentElementsPage
                            .logComponent()
                            .getLogRowsByExpectedValue(box, status)).isNotNull());
        softAssertions.assertAll();
    }

    @Then("the log row for radio button {string} should be displayed "
            + "in the logs panel on the Different Elements Page")
    public void testRadioButtonSelected(String radioButtonName) {
        DifferentElementsPage differentElementsPage = TestContext.getInstance()
                .getObject("different_elements_page", DifferentElementsPage.class);
        List<String> logRow = differentElementsPage.logComponent()
                .getLogRowsByExpectedValue(radioButtonName, "");
        assertThat(logRow).isNotNull();
    }

    @Then("the log row for the dropdown option {string} should be displayed "
            + "in the logs panel on the Different Elements Page")
    public void testDropdownSelected(String dropdownName) {
        DifferentElementsPage differentElementsPage = TestContext.getInstance()
                .getObject("different_elements_page", DifferentElementsPage.class);
        List<String> logRow = differentElementsPage.logComponent()
                .getLogRowsByExpectedValue(dropdownName, "");
        assertThat(logRow).isNotNull();
    }

    @Then("{string} page should be opened")
    public void testUserTablePageOpened(String pageTitle) {
        userTablePage = TestContext.getInstance().getObject("user_table_page", UserTablePage.class);
        assertThat(userTablePage.getTitle()).isEqualTo(pageTitle);
    }

    @And("{int} Number Type Dropdowns should be displayed on Users Table on User Table Page")
    public void testDropdownsNumberOnUserTable(int number) {
        userTablePage = TestContext.getInstance().getObject("user_table_page", UserTablePage.class);
        assertThat(userTablePage.usersTableComponent().getNumberTypeDropdowns()).hasSize(number);
    }

    @And("{int} Usernames should be displayed on Users Table on User Table Page")
    public void testUsernamesNumberOnUserTable(int number) {
        userTablePage = TestContext.getInstance().getObject("user_table_page", UserTablePage.class);
        assertThat(userTablePage.usersTableComponent().getUserNames()).hasSize(number);
    }

    @And("{int} Description texts under images should be displayed on Users Table on User Table Page")
    public void testImagesDescriptionsNumber(int number) {
        userTablePage = TestContext.getInstance().getObject("user_table_page", UserTablePage.class);
        assertThat(userTablePage.usersTableComponent().getImagesDescriptions()).hasSize(number);
    }

    @And("{int} checkboxes should be displayed on Users Table on User Table Page")
    public void testCheckboxesNumberOnUserTable(int number) {
        userTablePage = TestContext.getInstance().getObject("user_table_page", UserTablePage.class);
        assertThat(userTablePage.usersTableComponent().getCheckboxes()).hasSize(number);
    }

    @And("User table should contain following values:")
    public void testValuesOnUserTable(DataTable table) {
        SoftAssertions softAssertions = new SoftAssertions();
        List<List<String>> rows = table.asLists(String.class);
        userTablePage = TestContext.getInstance().getObject("user_table_page", UserTablePage.class);
        List<String> numbers = userTablePage.usersTableComponent().getRowsNumbers();
        List<String> usernames = userTablePage.usersTableComponent().getUserNames();
        List<String> descriptions = userTablePage.usersTableComponent().getDescriptionTexts();
        for (int i = 1; i < rows.size(); i++) {
            softAssertions.assertThat(rows.get(i).get(0))
                    .as("rows[" + i + "] number").isEqualTo(numbers.get(i - 1));
            softAssertions.assertThat(rows.get(i).get(1))
                    .as("rows[" + i + "] user").isEqualTo(usernames.get(i - 1));
            softAssertions.assertThat(rows.get(i).get(2))
                    .as("rows[" + i + "] description").isEqualTo(descriptions.get(i - 1));
        }
        softAssertions.assertAll();
    }

    @And("droplist should contain values in column Type for user {string}")
    public void testDropdownOptionsOnUserTable(String name, DataTable table) {
        SoftAssertions softAssertions = new SoftAssertions();
        List<String> rows = table.asList(String.class);
        userTablePage = TestContext.getInstance().getObject("user_table_page", UserTablePage.class);
        List<String> dropdown = userTablePage.usersTableComponent().getDropdownOptionsByUserName(name);
        for (int i = 1; i < rows.size(); i++) {
            softAssertions.assertThat(rows.get(i))
                    .as("rows[" + i + "] option").isEqualTo(dropdown.get(i - 1));
        }
        softAssertions.assertAll();
    }

    @Then("1 log row has {string} text in log section")
    public void testLogRowForCheckbox(String logRow) {
        userTablePage = TestContext.getInstance().getObject("user_table_page", UserTablePage.class);
        List<String> log = userTablePage.logComponent().getLogRowsByMatchString(logRow);
        assertThat(log).isNotNull();
    }

}

package com.epam.tc.hw5.components;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UsersTableComponent extends AbstractBaseComponent {

    @FindBy(xpath = "//tr/td/select")
    private List<WebElement> numberTypeDropdowns;

    @FindBy(xpath = "//tr/td[1]")
    private List<WebElement> rowsNumbers;

    @FindBy(xpath = "//tr/td/a")
    private List<WebElement> usernameLinks;

    @FindBy(className = "user-descr")
    private List<WebElement> imagesDescriptions;

    @FindBy(css = ".user-descr > span")
    private List<WebElement> textDescriptions;

    @FindBy(css = "input[type='checkbox']")
    private List<WebElement> checkboxes;

    @FindBy(css = ".user-descr > label")
    private WebElement checkboxLabel;

    public UsersTableComponent(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public List<WebElement> getNumberTypeDropdowns() {
        return numberTypeDropdowns;
    }

    public List<String> getUserNames() {
        return usernameLinks.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<WebElement> getImagesDescriptions() {
        return imagesDescriptions;
    }

    public List<WebElement> getCheckboxes() {
        return checkboxes;
    }

    public List<String> getRowsNumbers() {
        return rowsNumbers.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getDescriptionTexts() {
        return textDescriptions.stream().map(elem -> {
            if (elem.getText().split("\n").length > 1) {
                return elem.getText().split("\n")[0] + " " + elem.getText().split("\n")[1];
            } else {
                return elem.getText();
            }
        }).collect(Collectors.toList());
    }

    public List<String> getDropdownOptionsByUserName(final String username) {
        List<String> users = getUserNames();
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(username)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            Select select = new Select(getNumberTypeDropdowns().get(index));
            return select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    public String getCheckboxLabel() {
        return checkboxLabel.getText();
    }

    public void selectCheckboxForUser(final String username) {
        if (getUserNames().contains(username)) {
            getCheckboxes().get(getUserNames().indexOf(username)).click();
        }
    }

}

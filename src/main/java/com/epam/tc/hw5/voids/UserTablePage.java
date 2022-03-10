package com.epam.tc.hw5.voids;

import com.epam.tc.hw5.components.LogComponent;
import com.epam.tc.hw5.components.UsersTableComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserTablePage extends AbstractBasePage {

    private UsersTableComponent usersTableComponent;
    private LogComponent logComponent;

    public UserTablePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        usersTableComponent = new UsersTableComponent(this.driver, this.wait);
        logComponent = new LogComponent(this.driver, this.wait);
    }

    public UsersTableComponent usersTableComponent() {
        return usersTableComponent;
    }

    public LogComponent logComponent() {
        return logComponent;
    }

}

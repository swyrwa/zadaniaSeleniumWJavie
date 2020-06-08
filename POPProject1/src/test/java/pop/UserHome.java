package pop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHome {
    private WebDriver driver;
    private By panelBody = By.className("panel-body");
    private By dropdown = By.className("dropdown-toggle");
    private By logout = By.partialLinkText("Logout");

    public UserHome(WebDriver driver) {
        this.driver = driver;
    }

    public String getPanelBodyText() {
        return driver.findElement(panelBody).getText();
    }

    public HomePage logoutUser(){
        driver.findElement(dropdown).click();
        driver.findElement(logout).click();
        return new HomePage(driver);
    }

}


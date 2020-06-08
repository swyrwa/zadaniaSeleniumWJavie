package pop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;
    private By loginLink = By.linkText("home");
    private By headerTxt = By.tagName("h1");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage gotoLoginPage() {
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }

    public String getHeaderText() {
        return driver.findElement(headerTxt).getText();


    }
}

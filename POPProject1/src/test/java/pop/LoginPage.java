package pop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {

    private WebDriver driver;
    private By registerLink = By.linkText("Register");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPage gotoRegisterPage() {
        driver.findElement(registerLink).click();
        return new RegisterPage(driver);
    }

}

package pop;

import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private WebDriver driver;
    private By name = By.id("name");
    private By company = By.id("company");
    private By email = By.id("email");
    private By password = By.id("password");
    private By passwordConfirm = By.id("password-confirm");
    private By button = By.className("btn-primary");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public UserHome fillValidRegisterForm() {
        String random = RandomString.make(8);
        driver.findElement(name).sendKeys("name" + random);
        driver.findElement(company).sendKeys("company"+random);
        driver.findElement(email).sendKeys(random + "@email.com");
        driver.findElement(password).sendKeys(random);
        driver.findElement(passwordConfirm).sendKeys(random);
        driver.findElement(button).click();
        return new UserHome(driver);
    }


}
package pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//SIGNUP
public class SignUpPage {

    private WebDriver driver;

    @FindBy(id = "user_email")
    private WebElement email;
    @FindBy(id = "user_password")
    private WebElement password;
    @FindBy(name = "commit")
    private WebElement button;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage fillForm(String email, String passwd) {
        this.email.sendKeys(email);
        password.sendKeys(passwd);
        button.click();
        return new HomePage(driver);
    }

}



package pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

///SIGNIN
public class SignInPage {

    private WebDriver driver;
    @FindBy(linkText = "Sign up")
    private WebElement signUp;
    @FindBy(id = "session_email")
    private WebElement email;
    @FindBy(id = "session_password")
    private WebElement password;
    @FindBy(name = "commit")
    private WebElement button;

    public SignInPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SignUpPage gotoSignUpPage() {
        signUp.click();
        return new SignUpPage(driver);
    }

    public HomePage fillForm(String email, String passwd) {
        this.email.sendKeys(email);
        password.sendKeys(passwd);
        button.click();
        return new HomePage(driver);
    }
}
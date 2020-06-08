package pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//HOMEPAGE
public class HomePage {

    private WebDriver driver;

    @FindBy(id = "sign-in")
    private WebElement signIn;
    @FindBy(className = "navbar-text")
    private WebElement navBar;
    @FindBy(linkText = "Addresses")
    private WebElement addressesLink;
    @FindBy(linkText = "Sign out")
    private WebElement signOut;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SignInPage gotoSignIn() {
        signIn.click();
        return new SignInPage(driver);
    }

    public AddressesPage gotoAddresses() {
        addressesLink.click();
        return new AddressesPage(driver);
    }

    public String getNavbarText() {
        return navBar.getText();
    }

    public SignInPage logoutUser() {
        signOut.click();
        return new SignInPage(driver);
    }

}
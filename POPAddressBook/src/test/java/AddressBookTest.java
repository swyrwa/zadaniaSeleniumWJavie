import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pop.AddressesPage;
import pop.HomePage;
import pop.SignInPage;
import pop.SignUpPage;

import java.util.concurrent.TimeUnit;

public class AddressBookTest {

    public WebDriver driver;
    public HomePage homePage;
    public SignInPage signInPage;
    public SignUpPage signUpPage;
    public AddressesPage addressesPage;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "./src/test/java/data/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://a.testaddressbook.com/");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    @Test
    @Parameters({"email", "passwd"})
    public void registerUserTest(String email, String passwd) {
        homePage = new HomePage(driver);
        signInPage = homePage.gotoSignIn();
        signUpPage = signInPage.gotoSignUpPage();
        signUpPage.fillForm(email, passwd);
        try {
            String popUpTekst = driver.switchTo().alert().getText();
            popUpTekst.equals("Save password?");
            driver.switchTo().alert().dismiss();
        } catch (NoAlertPresentException exception) {
            Assert.assertTrue(homePage.getNavbarText().contains(email));
        }


    }

    @Test
    @Parameters({"email", "passwd"})
    public void loginRegisteredUser(String email, String password) {
        homePage = new HomePage(driver);
        signInPage = homePage.gotoSignIn();
        signInPage.fillForm(email, password);
        Assert.assertTrue(homePage.getNavbarText().contains(email));
    }


    @Test
    public void logoutUser() {
        homePage = new HomePage(driver);
        signInPage = homePage.logoutUser();
    }


}



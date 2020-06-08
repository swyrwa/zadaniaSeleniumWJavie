import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pop.HomePage;
import pop.LoginPage;
import pop.RegisterPage;
import pop.UserHome;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class BlazeDemoTest {

    public WebDriver driver;
    public HomePage homePage;
    public LoginPage loginPage;
    public RegisterPage registerPage;
    public UserHome userHome;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "./src/test/java/data/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void mainTest() {
        driver.get("https://blazedemo.com");
        homePage = new HomePage(driver);
        loginPage = homePage.gotoLoginPage();
        registerPage = loginPage.gotoRegisterPage();
        userHome = registerPage.fillValidRegisterForm();
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        try {
            ImageIO.write(screenshot.getImage(), "png", new File("./screenshots/blazedemo1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(userHome.getPanelBodyText().contains("logged in"));
        userHome.logoutUser();
        Reporter.log(homePage.getHeaderText(),true);
        Assert.assertTrue(homePage.getHeaderText().contains("Welcome to the Simple Travel Agency"));
    }

}



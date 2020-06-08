import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FirefoxTest {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", "./src/test/java/data/geckodriver.exe");
        driver = new FirefoxDriver();

    }

    @Test
    public void mojaMetoda() {
        driver.get("http://google.com");

    }

    @AfterClass(alwaysRun = true)


    public void afterClass() {
        driver.quit();

    }

}

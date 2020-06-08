import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ChromeTest {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "./src/test/java/data/chromedriver.exe");
        driver = new ChromeDriver();


    }

    @AfterClass(alwaysRun = true)


    public void afterClass() {
        driver.quit();

    }

    // *****************************************************************************************************************
    // ************************************ Przypadki testowe dla lokalizowania elementów ******************************
    // *****************************************************************************************************************
//ID 1.1
    @Test
    public void firstInputTest() {
        driver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("at-cv-lightbox-close")));
        if (driver.findElement(By.id("at-cv-lightbox-close")).isDisplayed()) {
            driver.findElement(By.id("at-cv-lightbox-close")).click();

        }
        WebElement pole = driver.findElement(By.id("user-message"));
        WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Show Message')]"));
        pole.sendKeys("Ola ma kota");
        button.click();
        Assert.assertEquals(driver.findElement(By.id("display")).getText(), "Ola ma kota");
    }

    //ID 1.2.1
    @Test
    public void secondInputTest() {
        driver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
        /*WebDriverWait wait = new WebDriverWait(driver, 5);
        if (driver.findElement(By.id("at-cv-lightbox-close")).isDisplayed()) {
            driver.findElement(By.id("at-cv-lightbox-close")).click();
        }*/
        WebElement pole1 = driver.findElement(By.id("sum1"));
        WebElement pole2 = driver.findElement(By.id("sum2"));
        WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Get Total')]"));
        pole1.sendKeys("2");
        pole2.sendKeys("asdf");
        button.click();
        Assert.assertEquals(driver.findElement(By.id("displayvalue")).getText(), "NaN");
    }

    //ID 1.2.2
    @Test
    public void secondInputTestIncorrect() {
        driver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
        /*WebDriverWait wait = new WebDriverWait(driver, 5);
        if (driver.findElement(By.id("at-cv-lightbox-close")).isDisplayed()) {
            driver.findElement(By.id("at-cv-lightbox-close")).click();
        }*/
        WebElement pole1 = driver.findElement(By.id("sum1"));
        WebElement pole2 = driver.findElement(By.id("sum2"));
        WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Get Total')]"));
        pole1.sendKeys("2");
        pole2.sendKeys("3");
        button.click();
        Assert.assertEquals(driver.findElement(By.id("displayvalue")).getText(), "5");
    }

    // ID 1.3
    @Test
    public void googleListTest() {
        driver.get("http://google.com");
        driver.findElement(By.name("q")).sendKeys("testowanie");
        driver.findElement(By.name("q")).submit();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rso")));
        List<WebElement> lista = driver.findElements(By.className("g"));
        Reporter.log(String.valueOf(lista.size()), true);
        boolean x = lista.size() >= 9;
        Assert.assertTrue(x);
    }

    // ID 1.4
    @Test
    public void ajaxTest() {
        driver.get("https://www.seleniumeasy.com/test/ajax-form-submit-demo.html");
        WebElement pole1 = driver.findElement(By.id("title"));
        WebElement pole2 = driver.findElement(By.id("description"));
        WebElement button = driver.findElement(By.id("btn-submit"));
        pole1.sendKeys("Jakis name");
        pole2.sendKeys("opis");
        button.click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.textToBePresentInElementLocated(By.id("submit-control"), "Form submited Successfully!"));
        Assert.assertEquals(driver.findElement(By.id("submit-control")).getText(), "Form submited Successfully!");
    }

    // *****************************************************************************************************************
    // ******** Przypadki testowe dla podstawowych operacji w interfejsie użytkownika aplikacji internetowych **********
    // *****************************************************************************************************************

    //ID 1.1
    @Test
    public void popupWindowsTest() {
        driver.navigate().to("https://www.seleniumeasy.com/test/window-popup-modal-demo.html");
        driver.manage().window().fullscreen();
        String mainWindow = driver.getWindowHandle();
        driver.findElement(By.linkText("Like us On Facebook")).click();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!mainWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Assert.assertTrue(driver.findElement(By.id("u_0_3")).isDisplayed());
        driver.switchTo().window(mainWindow);
    }


    // Okna dialogowe - 1
    @Test
    public void alertTest1() {
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am an alert box!");
        alert.accept();
    }

    // Okna dialogowe - 2.1
    @Test
    public void alertTest2Cancel() {
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "Press a button!");
        alert.dismiss();
        WebElement youPressedCancel = driver.findElement(By.id("confirm-demo"));
        Assert.assertEquals(youPressedCancel.getText(), "You pressed Cancel!");
    }

    // Okna dialogowe - 2.2
    @Test
    public void alertTest2OK() {
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "Press a button!");
        alert.accept();
        WebElement youPressedOk = driver.findElement(By.id("confirm-demo"));
        Assert.assertEquals(youPressedOk.getText(), "You pressed OK!");
    }

    // Okna dialogowe - 3
    @Test
    public void alertTest3OKInput() {
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        driver.findElement(By.xpath("//button[@onclick='myPromptFunction()']")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "Please enter your name");
        alert.sendKeys("Sylwester");
        alert.accept();
        Assert.assertEquals(driver.findElement(By.id("prompt-demo")).getText(), "You have entered 'Sylwester' !");
    }

    //ID 1.2.1
    @Test
    public void selectListTest() {
        driver.get("https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");
        Select select1 = new Select(driver.findElement(By.id("select-demo")));
        select1.selectByVisibleText("Friday");
        Assert.assertTrue(driver.findElement(By.className("selected-value")).getText().contains("Friday"));
    }
//ID 1.2.2 -> TEST FAILS DUE TO DEFECT
    @Test
    public void selectMultiList() {
        driver.get("https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");
        WebElement lista = driver.findElement(By.id("multi-select"));
        WebElement button = driver.findElement(By.id("printAll"));
        Select listaSelect = new Select(lista);
        listaSelect.selectByVisibleText("Florida");
        listaSelect.selectByVisibleText("Ohio");
        listaSelect.selectByVisibleText("Texas");
        button.click();

        WebElement wynik = driver.findElement(By.className("getall-selected"));

        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        try {
            ImageIO.write(screenshot.getImage(), "png", new File("./screenshots/image1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<WebElement> lista2 = listaSelect.getAllSelectedOptions();
        for (WebElement el : lista2) {
            Reporter.log(el.getText(), true);
        }

        Assert.assertTrue(wynik.getText().contains("Florida"));
        Assert.assertTrue(wynik.getText().contains("Ohio"));
        Assert.assertTrue(wynik.getText().contains("Texas"));
    }


}

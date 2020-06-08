package pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

///ADDRESSESPAGE
public class AddressesPage {

    private WebDriver driver;

    public AddressesPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}



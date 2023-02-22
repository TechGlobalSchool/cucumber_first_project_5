package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class TechGlobalBasePage {
    public TechGlobalBasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "dropdown-button")
    public WebElement dropdownHeader;

    @FindBy(css = "div[class*=dropdown-content]>a")
    public List<WebElement> dropdownOptions;

    public void selectDropdownOption(String option) {
        for(WebElement el : dropdownOptions) {
            if(el.getText().equals(option)) {
                el.click();
                break;
            }
        }
    }
}

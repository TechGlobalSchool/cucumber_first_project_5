package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TechGlobalPaginationPage extends TechGlobalBasePage {
    public TechGlobalPaginationPage() {
        super();
    }

    @FindBy(id = "main_heading")
    public WebElement mainHeading;

    @FindBy(id = "sub_heading")
    public WebElement subHeading;

    @FindBy(id = "content")
    public WebElement content;

    @FindBy(id = "previous")
    public WebElement btnPrevious;

    @FindBy(id = "next")
    public WebElement btnNext;

    @FindBy(css = "div[class*=pagBodyData]>p")
    public List<WebElement> pageData;

    @FindBy(className = "city_image")
    public WebElement image;
}

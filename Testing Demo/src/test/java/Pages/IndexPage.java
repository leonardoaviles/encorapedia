package Pages;

import Actions.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IndexPage extends CommonActions {

    public final By reports = By.cssSelector("a[href='reports.html']");
    public final By home = By.cssSelector("a[href='index.html']");
    public final By inventory = By.cssSelector("a[href='inventory.html']");
    public final By listDivs = By.cssSelector("div[class='row']");

    public IndexPage(WebDriver webDriver){
        super(webDriver);
    }

    public void clickReports() {

       ClickElement(reports);

    }

}

package Pages;

import Actions.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReportPage extends CommonActions {

    public By sortPrices = By.cssSelector("select[id='sort']");
    public By productName = By.cssSelector("option[value='prod']");
    public By priceAscending = By.cssSelector("option[value='asc']");
    public By priceDescending = By.cssSelector("option[value='desc']");
    public By tableRows = By.cssSelector("div[id='results']");

    public ReportPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickOnSelectSort(){
        ClickElement(sortPrices);
    }

    public void clickOnSelectDescending(){
        ClickElement(priceDescending);
    }

    public boolean containdTableSorted(){

       return obtainData(tableRows);

    }

    public void existElementTable(){
        existElement(tableRows);
    }

}

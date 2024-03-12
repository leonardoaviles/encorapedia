package Pages;

import Actions.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReportsPage extends CommonActions {

    public By fromDate = By.cssSelector("input[id='fromDate']");
    public By toDate = By.cssSelector("input[id='toDate']");
    public By selectedReport = By.cssSelector("select[id='selectedReport']");
    public By pricesReport = By.cssSelector("option[value='prices']");
    public By inventoryReport = By.cssSelector("option[value='inventory']");
    public By showFullReport = By.cssSelector("input[value='full']");
    public By showCondensedReport = By.cssSelector("input[value='condensed']");
    public By viewReportPdf = By.cssSelector("i[class='fas fa-file-pdf']");
    public By viewReportAlt = By.cssSelector("i[class='far fa-file-alt']");

    public ReportsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickOnSelectPricesReport(){
        ClickElement(selectedReport);
        ClickElement(pricesReport);
    }

    public void clickOnShowFullReport(){
        ClickElement(showFullReport);
    }

    public void clickOnViewReportAlt(){
        ClickElement(viewReportAlt);
    }

    public void putFromDate(){
        SendKeys(fromDate,date);
    }

    public void putToDate(){
        SendKeys(toDate,obtainCurrentDate());

    }

}

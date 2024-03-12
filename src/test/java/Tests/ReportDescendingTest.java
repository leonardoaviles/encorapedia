package Tests;

import Actions.CommonActions;
import Pages.IndexPage;
import Pages.ReportPage;
import Pages.ReportsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import java.net.MalformedURLException;

public class ReportDescendingTest {

    private WebDriver webDriver;

    private IndexPage indexPage;

    private ReportsPage reportsPage;

    private ReportPage reportPage;

    private boolean Grid = true;

    @Parameters({"browser","grid"})
    @BeforeTest
    public void setUp(String browser, boolean grid) throws MalformedURLException, InterruptedException {
        set(browser);
        Grid = grid;

    }

    @Test(description = "This test is to validate the sort by descending")
    public void ReportDescending() throws InterruptedException {

        if(Grid){
            indexPage.existElement(By.id("continue"));
            indexPage.ClickElement(By.id("continue"));
        }

        //first go to reports
        indexPage.waitSeconds();

        indexPage.clickReports();

        // select the report that we want
        reportsPage.existElement(reportsPage.fromDate);

        reportsPage.putFromDate();

        reportsPage.putToDate();

        reportsPage.clickOnSelectPricesReport();

        reportsPage.clickOnShowFullReport();

        reportsPage.clickOnViewReportAlt();
        reportsPage.waitSeconds();

        //wait until the report is loading
        reportPage.existElementTable();

        //select the sort
        reportPage.clickOnSelectSort();
        reportsPage.waitSeconds();

        reportPage.clickOnSelectDescending();
        reportsPage.waitSeconds();

        //wait unti the report is loading
        reportPage.existElementTable();

        //verify that the sort is correct
        Assert.assertTrue(reportPage.containdTableSorted(), "The table has not been sorted by descending");
    }

    @AfterClass
    public void tearDown(){
        reportPage.TearDown();
    }

    public void set(String browser) throws MalformedURLException {
            CommonActions commonActions = new CommonActions(webDriver);
            webDriver = commonActions.SetUp2(browser);

            indexPage = new IndexPage(webDriver);
            webDriver = indexPage.SetUp2(browser);
            
            reportsPage = new ReportsPage(webDriver);
            webDriver = reportsPage.SetUp2(browser);
            
            reportPage = new ReportPage(webDriver);
            webDriver = reportPage.SetUp2(browser);
    }
}
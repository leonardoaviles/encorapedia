package Actions;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CommonActions {

    private final LocalDate localDate = LocalDate.now();
    private final DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public String date = "02-23-2023";
    public boolean Grid = true;
    private final String URL= "https://1f4jm3zb-8080.usw3.devtunnels.ms/";
    private final String URL2= "localhost:8080";

    private WebDriver webDriver;
    private WebDriverWait wait;
    private final String PATHCHROME = "./src/main/resources/chromedriver";
    private final String PATHEDGE = "./src/main/resources/msedgedriver";
    private final String PATHFIREFOX = "./src/main/resources/geckodriver";

    private EdgeOptions edgeOptions = new EdgeOptions();
    private ChromeOptions chromeOptions = new ChromeOptions();
    private FirefoxOptions firefoxOptions = new FirefoxOptions();

    private URL url;

    public CommonActions(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public WebDriver SetUp(String browser){

            switch (browser) {
                case "chrome":
                    if(webDriver == null) {
                        WebDriverManager.chromedriver().setup();
                        System.setProperty("webdriver.chrome.driver", PATHCHROME);
                        webDriver = new ChromeDriver();
                        webDriver.get(URL2);
                        webDriver.manage().window().maximize();
                    }
                    break;
                case "edge":
                    if(webDriver == null) {
                        WebDriverManager.edgedriver().setup();
                        System.setProperty("webdriver.edge.driver", PATHEDGE);
                        webDriver = new EdgeDriver();
                        webDriver.get(URL2);
                        webDriver.manage().window().maximize();
                    }
                    break;
                case "firefox":
                    if(webDriver == null) {
                        WebDriverManager.firefoxdriver().setup();
                        System.setProperty("webdriver.gecko.driver", PATHFIREFOX);
                        webDriver = new FirefoxDriver();
                        webDriver.get(URL2);
                        webDriver.manage().window().maximize();
                    }
                    break;
            }
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        return webDriver;
    }

    public WebDriver SetUp2(String browser) throws MalformedURLException {

            switch (browser) {
                case "chrome":
                    if(webDriver == null) {
                        chromeOptions.setCapability("browserName","chrome");
                        url = new URL("http://localhost:4444/wd/hub");
                        webDriver = new RemoteWebDriver(url,chromeOptions);
                        webDriver.get(URL);
                        webDriver.manage().window().maximize();
                    }

                    break;
                case "edge":
                    if(webDriver == null) {
                        edgeOptions.setCapability("browserName", "edge");
                        url = new URL("http://localhost:4444/wd/hub");
                        webDriver = new RemoteWebDriver(url, edgeOptions);
                        webDriver.get(URL);
                        webDriver.manage().window().maximize();
                    }

                    break;
                case "firefox":
                    if(webDriver == null) {
                        firefoxOptions.setCapability("browserName","firefox");
                        url = new URL("http://localhost:4444/wd/hub");
                        webDriver = new RemoteWebDriver(url,firefoxOptions);
                        webDriver.get(URL);
                        webDriver.manage().window().maximize();
                    }
                    break;
            }
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        return webDriver;
    }

    public WebElement FindElement(By element){

        try {
                return webDriver.findElement(element);
        }catch(Exception e){
            return null;
        }
    }

    public void existElement(By element){

            if(!FindElement(element).isDisplayed())
                 wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
    }

    public void SendKeys(By element, String text){
        FindElement(element).click();
        FindElement(element).clear();
        FindElement(element).sendKeys(text);
    }

    public void ClickElement(By element){

        if(FindElement(element).isDisplayed()) {
            WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            webElement.click();
        }

    }

    public void waitSeconds() throws InterruptedException {
        Thread.sleep(10000);
    }

    public void TearDown(){
            webDriver.close();
            webDriver.quit();
    }

    public String obtainCurrentDate(){
        if(Grid){
            return  localDate.format(dateTimeFormatter2);
        }
        else {
            return  localDate.format(dateTimeFormatter1);
        }

    }

    public boolean obtainData(By elementTable){

        List<Integer> colDataCollection = new ArrayList<>();

        WebElement table = webDriver.findElement(elementTable);
        List<WebElement> columns = table.findElements(By.tagName("th"));

        Optional<WebElement> priceElement = columns.stream()
                .filter(element -> element.getText().equals("Price"))
                .findFirst();

        int priceIndex;

        priceIndex = priceElement.map(columns::indexOf).orElse(0);

        WebElement body = table.findElement(By.tagName("tbody"));
        List<WebElement> rows = body.findElements(By.tagName("tr"));

        rows.forEach(row -> {
            List<WebElement> rowData = row.findElements(By.tagName("td"));

            String priceValue = rowData.get(priceIndex).getText().replace("$","");

            colDataCollection.add(Integer.valueOf(priceValue));
        });

        return colDataCollection.stream()
                .sorted((a, b) -> b - a)
                .collect(Collectors.toList())
                .equals(colDataCollection);

    }

}

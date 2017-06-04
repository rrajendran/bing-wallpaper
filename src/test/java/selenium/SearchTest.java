package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.bing.BingDownloadPage;

import java.time.LocalDate;

public class SearchTest {


    @Test(dataProvider = "pageObjects")
    public void testSearch(final SearchPage searchPage) {
        searchPage.init(driver);
        driver.get("https://bing.com");
        searchPage.download(LocalDate.now());
    }

    @DataProvider
    private Object[][] pageObjects() {
        return new Object[][]{
                {new BingDownloadPage()}
        };
    }

    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", "/opt/drivers/chromedriver.exe");
        driver = new FirefoxDriver();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

package test.java.webdemo.seleniumDemo;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class TestClass {

    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        System.setProperty("webdriver.gecko.driver", "resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("https://duckduckgo.com/");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testLinksQty() {

        driver.get("https://duckduckgo.com/?q=Cars&t=h_&ia=web");
        List<WebElement> links = driver.findElements(By.xpath("//a"));
        assertThat(links.size(), greaterThan(1));

    }

    @Test
    public void testPicturesQty() {

        driver.get("https://duckduckgo.com/?q=Cars&t=h_&ia=web");
        List<WebElement> links = driver.findElements(By.xpath("//img"));
        assertThat(links.size(), greaterThan(3));

    }

    @Test
    public void testAllUrls() {

        driver.get("https://duckduckgo.com/?q=Cars&t=h_&ia=web");
        List<WebElement> urls = driver.findElements(By.xpath("//a"));

        for (int i = 0; i <= urls.size(); i++) {
            if(!(urls.get(i).getText().isEmpty()))
            {
                urls.get(i).click();
                driver.navigate().back();
                urls=driver.findElements(By.tagName("a"));
            }
        }
        assertThat(urls.size(), greaterThan(1));

    }

    @Test
    public void testInputFields() {

        driver.get("https://gmail.com");
        List<WebElement> textfield = driver.findElements(By.xpath("//input[@type='text' or @type='password']"));
        assertEquals(2, textfield.size());

    }

}

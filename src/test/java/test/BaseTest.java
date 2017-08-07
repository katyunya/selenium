package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pageobjects.LoginPage;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver webDriver;
    private String url = "https://mail.ru";

    @BeforeClass
    public void openBrowser() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    public LoginPage navigate() {
        webDriver.get(url);
        return new LoginPage(webDriver);
    }

    @AfterClass
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(5000);
        webDriver.close();
    }
}

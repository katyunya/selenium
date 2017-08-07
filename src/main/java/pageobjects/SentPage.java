package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SentPage {
    private WebDriver webDriver;
    private Actions actions;

    @FindBy(xpath = "//a[@data-subject='New message']")
    //@FindBy(className = "b-datalist__item__cbx")
    private WebElement sentLink;

    @FindBy(id = "PH_logoutLink")
    private WebElement logoutButton;

    public SentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.actions = new Actions(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    public boolean isSentLinkPresented() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sentLink.isDisplayed());
        return sentLink.isDisplayed();
    }

    public LoginPage exit() {
        actions.click(logoutButton);
        actions.build().perform();
        return new LoginPage(webDriver);
    }
}

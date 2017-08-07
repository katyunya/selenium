package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class DraftsPage {
    private WebDriver webDriver;
    private Actions actions;

    @FindBy(xpath = "//a[@data-subject='New message']")
    private WebElement draftLink;
    @FindBy(className = "b-datalist__empty__icon_drafts")
    private WebElement emptyDraftsIcon;

    @FindBy(xpath = "//a[@href='/messages/sent/']")
    private WebElement sentLink;

    public DraftsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.actions = new Actions(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    public EditDraftPage clickDraftLink() {
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        actions.click(draftLink);
        actions.build().perform();
        return new EditDraftPage(webDriver);
    }

    public boolean isDraftLinkPresented(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(draftLink.isDisplayed());
        return draftLink.isDisplayed();
    }

    public boolean isEmptyDraftsIconPresented() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return emptyDraftsIcon.isDisplayed();
    }

    public SentPage goToSentPage() {
        actions.click(sentLink);
        actions.build().perform();
        return new SentPage(webDriver);
    }
}


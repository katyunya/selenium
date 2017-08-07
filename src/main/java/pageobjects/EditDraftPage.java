package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class EditDraftPage {
    private WebDriver webDriver;
    private Actions actions;

    @FindBy(xpath = "//*[@data-text='test.selenium.mail@mail.ru']")
    private WebElement address;
    @FindBy(name = "Subject")
    private WebElement theme;
    @FindBy(xpath = "//iframe[@scrolling='auto']")
    private WebElement iframe;
    @FindBy(id = "tinymce")
    private WebElement message;
    @FindBy(xpath = "//*[@data-name='send']")
    private WebElement sendButton;
    @FindBy(xpath = "//a[@href='/messages/drafts/']")
    private WebElement drafts;


    public EditDraftPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.actions = new Actions(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    public String getAddressText() {
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return address.getAttribute("data-text");
    }

    public String getThemeText() {
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return theme.getAttribute("value");
    }

    public String getMessageText() {
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.switchTo().frame(iframe);
        String text = message.getText();
        webDriver.switchTo().defaultContent();
        return text;
    }

    public EditDraftPage sendMessage() {
        actions.click(sendButton);
        actions.build().perform();
        return this;
    }

    public DraftsPage goToDrafts() {
        (new WebDriverWait(webDriver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("message-sent_IsSocialConnect")));
        actions.click(drafts);
        actions.build().perform();
        return new DraftsPage(webDriver);
    }
}

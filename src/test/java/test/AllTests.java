package test;

import org.testng.annotations.Test;
import pageobjects.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AllTests extends BaseTest {

    MainPage mainPage;
    NewMessagePage newMessagePage;
    DraftsPage draftsPage;
    EditDraftPage editDraftPage;
    SentPage sentPage;
    LoginPage loginPage;

    private String login = "test.selenium.webdriver";
    private String password = "1q2w3e4r!@#$";
    private String domain = "@mail.ru";
    private String address = "test.selenium.mail@mail.ru";
    private String theme = "New message";
    private String message = "First message!";

    @Test
    public void loginTest() {
        LoginPage loginPage = navigate();
        mainPage = loginPage.doLogin(login, password, domain);
        assertTrue(mainPage.isExitButtonPresented(), "Exit button does not presented!");
    }

    @Test(dependsOnMethods = "loginTest")
    public void testNewMessageCreation() {
        newMessagePage = mainPage.clickNewMessageButton();
        assertTrue(newMessagePage.isAddressInputPresented(), "Address field does not presented!");
    }

    @Test(dependsOnMethods = "testNewMessageCreation")
    public void saveDraftTest() {
        newMessagePage.saveDraft(address, theme, message);
        assertTrue(newMessagePage.isToolbarMessagePresented(), "Toolbar message does not presented!");
    }

    @Test(dependsOnMethods = "saveDraftTest")
    public void goToDraftsTest() {
        draftsPage = newMessagePage.goToDrafts();
        assertTrue(draftsPage.isDraftLinkPresented());
    }

    @Test(dependsOnMethods = "goToDraftsTest")
    public void checkDraftTest() {
        editDraftPage = draftsPage.clickDraftLink();
        assertEquals(editDraftPage.getAddressText(), address);
        assertEquals(editDraftPage.getThemeText(), theme);
        assertTrue(editDraftPage.getMessageText().contains(message));
    }

    @Test(dependsOnMethods = "checkDraftTest")
    public void sendMessageTest() {
        editDraftPage.sendMessage();
    }

    @Test(dependsOnMethods = "sendMessageTest")
    public void checkMessageIsSent() {
        editDraftPage.goToDrafts();
        assertTrue(draftsPage.isEmptyDraftsIconPresented());
    }

    @Test(dependsOnMethods = "checkMessageIsSent")
    public void checkSentPage(){
        sentPage = draftsPage.goToSentPage();
        assertTrue(sentPage.isSentLinkPresented());
    }

    @Test(dependsOnMethods = "checkMessageIsSent")
    public void logoutTest() {
        loginPage = sentPage.exit();
    }
}

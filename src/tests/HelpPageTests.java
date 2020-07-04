package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class HelpPageTests extends TestBase {

    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    UpperMenuHelper upperMenuPage;
    HelpPageHelper helpPage;
    PageBase pageBase;



    @BeforeMethod
    public void initTests() throws InterruptedException {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        upperMenuPage = PageFactory.initElements(driver, UpperMenuHelper.class);
        helpPage = PageFactory.initElements(driver, HelpPageHelper.class);
        pageBase = PageFactory.initElements(driver, PageBase.class);


        loginPage.openLoginPage();
        loginPage.loginAsAtlassian(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        upperMenuPage.openMenuPage();
        upperMenuPage.waitUntilPageIsLoaded();
        helpPage.openHelplink();
        driver.switchTo().frame(helpPage.getHelpFrameindex());
        helpPage.waitUntilHelpMenuIsloaded();
        helpPage.openGetStartedGuide();
        Thread.sleep(2000);
        System.out.println(driver.getTitle());
        pageBase.findBrowserTabByTitileAndSwichToIt("Getting Started with Trello");



    }

    @Test
    public void positiveGetStartedGuidePageTest(){
        helpPage.isHelpPageOpened();
    }





}

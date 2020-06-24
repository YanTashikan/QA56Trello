package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BoardsPageHelper;
import pages.CurrentBoardHelper;
import pages.LoginPageHelper;
import pages.ProfilesVisibilityHelper;

import java.util.List;

public class ProfilesVisabilityTests extends TestBase {

    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardHelper currentBoard;
    ProfilesVisibilityHelper profilePage;

    @BeforeMethod
    public void initTests() throws InterruptedException {
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
        currentBoard = new CurrentBoardHelper(driver);
        profilePage = new ProfilesVisibilityHelper(driver);
        loginPage.openLoginPage();
        loginPage.loginAsAtlassian(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        currentBoard.openCurrentBoard(BOARD_TITLE);
        currentBoard.waitUntilPageBoardIsLoaded(BOARD_TITLE);

        //---- Open Up_Right Menu ----
        profilePage.openUpRightMenu();

        //---- Open ProfileVisability Menu ----
        profilePage.openProfileAndVisabilityMenu();


    }

    @Test
    public void lettersIconTest(){
        //--- Receive Upper Right Menu element---
        WebElement upRightMenu = driver.findElement(By.xpath("//button[@data-test-id = 'header-member-menu-button']"));
        WebElement upRightMenuText = upRightMenu.findElement(By.xpath(".//span"));
        //--- Receive list of necessary icons ---
        List<WebElement> iconsList = driver.findElements(By.xpath(createLocatorIconlist(USERNAME)));
        int counter = 0;
        for(WebElement element: iconsList)
            if (element.getText().equals(upRightMenuText.getText())) counter++;

        Assert.assertEquals(2,counter, "The text on the upper right icon and on the icon on profile is not the same");
    }

    @Test
    public void userNameDisplayingTest(){

        //--- Receive UserName after shtrudel without username value
        WebElement userNameAfterShtrudel = driver.findElement(By.xpath("//span[contains(text(),'@')]"));

        //--- Receive UserName from user name field
        WebElement userNameField = driver.findElement(By.xpath("//input[@name='username']"));
        System.out.println("userNameAfterShtrudel: " + userNameAfterShtrudel.getText());
        System.out.println("userNameField: " + userNameField.getAttribute("value"));

        Assert.assertTrue(userNameAfterShtrudel.getText().contains(USERNAME)&&userNameField.getAttribute("value").equals(USERNAME));
    }


    private String createLocatorIconlist(String username) {
        return "//div[@title='" + username + " (" + username + ")']//span";
    }


}

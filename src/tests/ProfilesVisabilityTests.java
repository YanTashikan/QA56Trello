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
        profilePage.openUpRightMenu();
        profilePage.openProfileAndVisabilityMenu();
    }

    @Test
    public void lettersIconTest(){

        Assert.assertEquals(2,profilePage.getIndexIcon(USERNAME), "The text on the upper right icon and on the icon on profile is not the same");
    }

    @Test
    public void userNameDisplayingTest(){
        System.out.println("userNameAfterShtrudel: " + profilePage.getUserNameAfterShtrudel());
        System.out.println("userNameField: " + profilePage.getUserNameFieldText());
        Assert.assertTrue(profilePage.getUserNameAfterShtrudel().contains(USERNAME)&&profilePage.getUserNameFieldText().equals(USERNAME));
    }





}

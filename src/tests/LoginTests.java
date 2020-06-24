package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BoardsPageHelper;
import pages.LoginPageHelper;

public class LoginTests extends TestBase{
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;

    @BeforeMethod
    public void initTests(){
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
    }

    @Test
    public void loginTestPositive()  {
        loginPage.openLoginPage();
        loginPage.enterLoginAtlassianAndClickLogin(LOGIN);
        loginPage.enterPasswordAtlassionAndClickLogin(PASSWORD);
        boardsPage.waitUntilPageIsLoaded();

        Assert.assertEquals(boardsPage.getButtonBoardsText(),"Boards","Text on the boardIcon is not 'Boards'");
    }


    @Test
    public void loginNegativeNoLoginNoPassword()  {
        loginPage.openLoginPage();
        loginPage.pressLoginButton();
        loginPage.waitErrorMessage();

        Assert.assertEquals("Missing email",loginPage.getErrorMessage(),"The text of the message is not 'Missing email'");
    }


    @Test
    public void NegativeLoginIncorrect() throws InterruptedException {
        loginPage.openLoginPage();
        loginPage.enterLogin("ttt@test.com");
        loginPage.pressLoginButton();
        loginPage.waitErrorMessage();
        System.out.println("Error message: " + loginPage.getErrorMessage());
        Assert.assertEquals(loginPage.getErrorMessage(), "There isn't an account for this email","Error message is not correct");
    }

    @Test
    public void NegativePasswordIncorrect() throws InterruptedException {
        //--- Press log In menu button
        loginPage.openLoginPage();

        //--- Enter Correct Login
        loginPage.enterLoginAtlassianAndClickLogin(LOGIN);

        //---Enter incorrect password ---
        loginPage.enterPasswordAtlassionAndClickLogin("error");

        loginPage.waitAtlassianErrorMessage();

        //---Print error message ---
        System.out.println("Error message: " + loginPage.getAtlassianErrorMessage());
        Assert.assertTrue(loginPage.getAtlassianErrorMessage().contains("Incorrect email address and / or password."),
                "There is no error message or the text of the message is not correct");
    }


}

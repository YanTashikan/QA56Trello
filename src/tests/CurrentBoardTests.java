package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BoardsPageHelper;
import pages.CurrentBoardHelper;
import pages.LoginPageHelper;

public class CurrentBoardTests extends TestBase{
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardHelper currentBoard;

    @BeforeMethod
    public void initTests() throws InterruptedException {
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
        currentBoard = new CurrentBoardHelper(driver);
        loginPage.openLoginPage();
        loginPage.loginAsAtlassian(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        currentBoard.openCurrentBoard(BOARD_TITLE);
        currentBoard.waitUntilPageBoardIsLoaded(BOARD_TITLE);
    }

    @Test
    public void createNewList()  {

        //--- Add new list---
        int beforeAdding = currentBoard.getListsQuantity();
        System.out.println("Lists before adding: " + beforeAdding);
        currentBoard.createNewList();
        currentBoard.enterTitle("Test");
        currentBoard.submitAddingList();
        currentBoard.cancelFromEditMode();

        int afterAdding = currentBoard.getListsQuantity();

        Assert.assertEquals(afterAdding,beforeAdding+1,
                "The quantity of lists before adding new list is not the same as the quantity after adding");

    }


    @Test
    public void createNewCard() throws InterruptedException {
            if (!currentBoard.iSListExist()) {
                System.out.println("Lists before adding: "+ currentBoard.getListsQuantity());
                currentBoard.addNewList("Test");
                System.out.println("Lists after adding: " + currentBoard.getListsQuantity());
            }

            //---Receive the quantity of cards ---
            int beforeAdding = currentBoard.getCardsQuantity();
            currentBoard.addNewCard("Test Card");

        //---Receive the quantity of cards ---
        int afterAdding = currentBoard.getCardsQuantity();
        Assert.assertEquals(afterAdding,beforeAdding+1,
                "The quantity of cards before adding new card is not the same as the quantity after adding");


    }





}

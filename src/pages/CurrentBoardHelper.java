package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;

import java.util.List;

//import static tests.TestBase.BOARD_TITLE;

public class CurrentBoardHelper extends PageBase{


    public CurrentBoardHelper(WebDriver driver) {
        super(driver);
    }
    public void openCurrentBoard(String boardTitle){
        WebElement ourBoard = driver
                .findElement(By.xpath(boardLocator(boardTitle)));
        ourBoard.click();
    }

    public void waitUntilPageBoardIsLoaded(String boardTitle){
        waitUntilElementIsVisible(By.xpath("//span[contains(text(),'"+boardTitle+"')]"),10);
        waitUntilElementIsClickable(By.xpath("//span[@class='placeholder']"),10);
    }

    private String boardLocator(String boardTitle) {
        return "//div[@title = '" + boardTitle + "']/../..";
    }

    public int getListsQuantity(){
        List<WebElement> listLists = driver.findElements(By.xpath("//div[@class = 'list js-list-content']"));
        return listLists.size();
    }

    public int getCardsQuantity(){
        List<WebElement> cardsLists = driver.findElements(By.cssSelector("a.list-card"));
        return cardsLists.size();
    }

    public void createNewList() {
        WebElement addListOption = driver.findElement(By.xpath("//span[@class='placeholder']"));
        addListOption.click();
        waitUntilElementIsVisible(By.xpath("//input[@placeholder='Enter list title...']"),10);
        WebElement addTitleField = driver.findElement(By.xpath("//input[@placeholder='Enter list title...']"));
    }

    public void enterTitle(String test) {
        WebElement addTitleField = driver.findElement(By.xpath("//input[@placeholder='Enter list title...']"));
        addTitleField.click();
        addTitleField.sendKeys("Test");
        waitUntilElementIsClickable(By.xpath("//input[@type='submit']"),10);
    }

    public void submitAddingList() {
        WebElement addListButton = driver.findElement(By.xpath("//input[@type='submit']"));
        addListButton.click();
    }

    public void cancelFromEditMode() {
        WebElement cancelEdit = driver
                .findElement(By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel-edit']"));
        cancelEdit.click();
    }

    public boolean iSListExist(){
        if (driver.findElement(By
                .xpath("//span[@class='placeholder']")).getText().contains("another"))
        {
            return true;
        }else{
            return false;
        }
    }



    public void addNewList(String titleList){
        //--- Add new list---
        WebElement addListOption = driver.findElement(By.xpath("//span[@class='placeholder']"));
        addListOption.click();
        WebElement addTitleField = driver.findElement(By.xpath("//input[@placeholder='Enter list title...']"));

        //----Add title of the list
        addTitleField.click();
        addTitleField.sendKeys(titleList);
        waitUntilElementIsClickable(By.xpath("//input[@type='submit']"),10);

        //----Submit of adding list ----
        WebElement addListButton = driver.findElement(By.xpath("//input[@type='submit']"));
        addListButton.click();

        //--- Cancel from edit mode ----
        WebElement cancelEdit = driver.findElement(By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel-edit']"));
        cancelEdit.click();
        waitUntilElementIsNotVisible(By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel-edit']"), 10);
    }



    public void addNewCard(String textCard){
        //--- Define two possible buttons for adding new card and click on the displayed one---
        WebElement addCardButton = driver.findElement(By.cssSelector("span.js-add-a-card"));
        WebElement addAnotherCardButton = driver.findElement(By.cssSelector("span.js-add-another-card"));
        if (addCardButton.isDisplayed()) {
            addCardButton.click();
        }else{
            addAnotherCardButton.click();
        }

        //--- Enter text, submit the card
        WebElement textCurrentCard = driver.findElement(By.cssSelector("textarea.list-card-composer-textarea"));
        textCurrentCard.click();
        textCurrentCard.sendKeys(textCard);
        WebElement submitCardButton = driver.findElement(By.xpath("//input[@type='submit'][@value = 'Add Card']"));
        submitCardButton.click();

        // ---- Cancel edit mode of the next card
        WebElement cancelEditCardButton = driver.findElement(By.cssSelector("div.card-composer a.icon-close"));
        cancelEditCardButton.click();
        waitUntilElementIsNotVisible(By.cssSelector("div.card-composer a.icon-close"),10);
    }
}

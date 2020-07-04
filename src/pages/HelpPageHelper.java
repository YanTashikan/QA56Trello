package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class HelpPageHelper extends PageBase {

    public HelpPageHelper(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[contains(text(),'Help')]") WebElement helpLink;
    @FindBy(xpath = "//ul[@id='search-results']//a[contains(text(),'Getting Started Guide')]") WebElement getStartingGuideLink;
    @FindBy(xpath = "//h1[contains(text(),'Getting Started With Trello')]") WebElement helpPagetitle;



    public  void openHelplink(){
        helpLink.click();
    }

    public  void waitUntilHelpMenuIsloaded(){
        waitUntilElementIsVisible(getStartingGuideLink, 20);
    }

    public  void openGetStartedGuide(){
        getStartingGuideLink.click();
    }

    public int  getHelpFrameindex(){
        List<WebElement> listIFrames = driver.findElements(By.tagName("iframe"));
        int i=0;
        for(WebElement frame:listIFrames){
            if(frame.getAttribute("src").contains("trello.com/contact")){
                return i;
            }
            i++;
        }
        return 0;
    }

    public void isHelpPageOpened() {
        try {
            waitUntilElementIsVisible(helpPagetitle, 20);
        }catch (Exception e){
            Assert.fail("Title of help page is not found");
        }

    }
}

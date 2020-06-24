package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProfilesVisibilityHelper extends PageBase {

    public ProfilesVisibilityHelper(WebDriver driver) {
        super(driver);
    }

    public void openUpRightMenu(){
        WebElement upRightMenu = driver.findElement(By.xpath("//button[@data-test-id = 'header-member-menu-button']"));
        upRightMenu.click();
        waitUntilElementIsVisible(By.xpath("//a[@data-test-id = 'header-member-menu-profile']"),10);
    }

    public void openProfileAndVisabilityMenu(){
        WebElement profileVisabilityMenu = driver
                .findElement(By.xpath("//a[@data-test-id = 'header-member-menu-profile']"));
        profileVisabilityMenu.click();
        waitUntilAllElementsAreVisible(By.xpath("//button[@data-test-id = 'header-member-menu-button']"),20);
        waitUntilElementIsClickable(By.xpath("//button[contains(text(),'Save')]"),10);
    }

    public String getUserNameAfterShtrudel(){
        return driver.findElement(By.xpath("//span[contains(text(),'@')]")).getText();
    }

    public String getUserNameFieldText(){
        return driver.findElement(By.xpath("//input[@name='username']")).getAttribute("value");
    }

    public String getRightMenuText(){
        WebElement upRightMenu = driver.findElement(By.xpath("//button[@data-test-id = 'header-member-menu-button']"));
        return upRightMenu.findElement(By.xpath(".//span")).getText();
    }

    public int getIndexIcon(String username){
        //--- Receive list of necessary icons ---
        List<WebElement> iconsList = driver.findElements(By.xpath(createLocatorIconlist(username)));
        int counter = 0;
        for(WebElement element: iconsList)
            if (element.getText().equals(this.getRightMenuText())) counter++;

        return counter;
    }

    private String createLocatorIconlist(String username) {
        return "//div[@title='" + username + " (" + username + ")']//span";
    }

}

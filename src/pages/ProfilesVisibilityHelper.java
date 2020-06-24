package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

}

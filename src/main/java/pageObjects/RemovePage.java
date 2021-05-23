package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RemovePage {
    final private WebDriver driver;

    final private By buttonDeleteBy = By.xpath("//input[@class='btn danger']");

    public RemovePage(WebDriver driver) {
        this.driver = driver;
    }

    public void removeComputer() {
        driver.findElement(buttonDeleteBy).click();
    }
}

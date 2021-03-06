package pageStructure;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Remover {
    private final WebDriver driver;

    private final By buttonDeleteBy = By.xpath("//input[@class='btn danger']");

    public Remover(WebDriver driver) {
        this.driver = driver;
    }

    public void removeComputer() {
        driver.findElement(buttonDeleteBy).click();
    }
}

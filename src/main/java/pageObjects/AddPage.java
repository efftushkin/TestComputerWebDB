package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AddPage {
    private final WebDriver driver;

    private final String headerText = "Add a computer";

    private final By header = By.xpath("/html/body/section/h1");
    private final By inputName = By.id("name");
    private final By inputIntroduced = By.id("introduced");
    private final By inputDiscontinued = By.id("discontinued");
    private final By selectCompany = By.id("company");
    private final By buttonCreate = By.xpath("//input[@class='btn primary']");

    public AddPage(WebDriver driver) {
        this.driver = driver;
    }

    public void createComputer(String name, String introduced, String discontinued, int company) {
        driver.findElement(inputName).sendKeys(name);
        driver.findElement(inputIntroduced).sendKeys(introduced);
        driver.findElement(inputDiscontinued).sendKeys(discontinued);
        Select select = new Select(driver.findElement(selectCompany));
        select.selectByIndex(company);
        driver.findElement(buttonCreate).click();
    }

    public boolean checkHeader() {
        return headerText.equals(driver.findElement(header).getText());
    }
}

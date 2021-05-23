package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AddPage {
    final private WebDriver driver;

    final private String headerText = "Add a computer";

    final private By header = By.xpath("/html/body/section/h1");
    final private By inputName = By.id("name");
    final private By inputIntroduced = By.id("introduced");
    final private By inputDiscontinued = By.id("discontinued");
    final private By selectCompany = By.id("company");
    final private By buttonCreate = By.xpath("//input[@class='btn primary']");

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

package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ListPage {
    final private String URL = "http://computer-database.gatling.io/computers";

    final private WebDriver driver;

    final private By headerBY = By.xpath("/html/body/section/h1");
    final private By alertBy = By.xpath("//section[@id='main']//child::div[@class='alert-message warning']");
    final private By searchBy = By.id("searchbox");
    final private By buttonSearchBy = By.id("searchsubmit");
    final private By buttonAddBy = By.id("add");
    final private By namesBy = By.xpath("//tbody//child::tr//child::td//child::a");
    final private By buttonNextBy = By.xpath("//section[@id='main']//ul//li[3]");
    final private By hrefNextBy = By.xpath("//section[@id='main']//ul//li[3]/a");

    public ListPage(WebDriver driver) {
        this.driver = driver;
        init();
    }

    public void init() {
        driver.get(URL);
    }

    public AddPage addComputer() {
        driver.findElement(buttonAddBy).click();
        return new AddPage(driver);
    }

    public boolean checkHeader() {
        return driver.findElement(headerBY).getText().contains("computers found");
    }

    public boolean checkAlert(String computerName) {
        return driver.findElement(alertBy).getText().equals("Done ! Computer " + computerName + " has been created");
    }

    private boolean isComputerPresentOnPage(String computerName) {
        for (; ; ) {
            List<WebElement> webElements = driver.findElements(namesBy);

            for (WebElement webElement : webElements) {
                if (webElement.getText().equals(computerName)) {
                    return true;
                }
            }

            WebElement buttonNext = driver.findElement(buttonNextBy);
            if (buttonNext.getAttribute("class").equals("next")) {
                driver.findElement(hrefNextBy).click();
            } else {
                return false;
            }
        }
    }

    private void useSearch(String computerName) {
        driver.findElement(searchBy).sendKeys(computerName);
        driver.findElement(buttonSearchBy).click();
    }

    public boolean isComputerPresent(String computerName, boolean useSearch) {
        init();

        if (useSearch) {
            useSearch(computerName);
        }

        return isComputerPresentOnPage(computerName);
    }

    public boolean removeComputer(String computerName) {
        init();

        useSearch(computerName);

        List<WebElement> webElements = driver.findElements(namesBy);

        if (webElements.size() == 0) {
            return true;
        } else if (webElements.size() > 1) {
            return false;
        }

        driver.get(webElements.get(0).getAttribute("href"));

        RemovePage removePage = new RemovePage(driver);

        removePage.removeComputer();

        return true;
    }
}

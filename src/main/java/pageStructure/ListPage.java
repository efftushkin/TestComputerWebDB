package pageStructure;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ListPage {
    private final String URL = "http://computer-database.gatling.io/computers";

    private final WebDriver driver;

    private final By headerBY = By.xpath("/html/body/section/h1");
    private final By alertBy = By.xpath("//section[@id='main']//child::div[@class='alert-message warning']");
    private final By searchBy = By.id("searchbox");
    private final By buttonSearchBy = By.id("searchsubmit");
    private final By buttonAddBy = By.id("add");
    private final By namesBy = By.xpath("//tbody//child::tr//child::td//child::a");
    private final By buttonNextBy = By.xpath("//section[@id='main']//ul//li[3]");
    private final By hrefNextBy = By.xpath("//section[@id='main']//ul//li[3]/a");

    public ListPage(WebDriver driver) {
        this.driver = driver;
        goHome();
    }

    public void goHome() {
        driver.get(URL);
    }

    public Adder addComputer() {
        driver.findElement(buttonAddBy).click();
        return new Adder(driver);
    }

    public boolean checkHeader() {
        return driver.findElement(headerBY).getText().contains("computers found");
    }

    public boolean checkAlert(String computerName) {
        return driver.findElement(alertBy).getText().equals("Done ! Computer " + computerName + " has been created");
    }

    private boolean isComputerPresentOnPage(String computerName) {
        while (true) {
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
        goHome();

        if (useSearch) {
            useSearch(computerName);
        }

        return isComputerPresentOnPage(computerName);
    }

    public boolean removeComputer(String computerName) {
        goHome();

        useSearch(computerName);

        List<WebElement> webElements = driver.findElements(namesBy);

        if (webElements.size() == 0) {
            return true;
        } else if (webElements.size() > 1) {
            return false;
        }

        driver.get(webElements.get(0).getAttribute("href"));

        Remover remover = new Remover(driver);

        remover.removeComputer();

        return true;
    }
}

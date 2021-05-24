import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.*;
import org.testng.Assert;
import pageStructure.Adder;
import pageStructure.ListPage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class TestComputerAdding {
    private WebDriver driver;
    private ListPage listPage;
    private Adder adder;
    private String computerName;

    @BeforeClass
    public void Init() {
        System.setProperty("webdriver.opera.driver", "src/test/resources/operadriver_win32/operadriver.exe");

        driver = new OperaDriver();

        listPage = new ListPage(driver);

        computerName = UUID.randomUUID().toString();
    }

    @Test(priority = 0, groups = "add", description = "Button Add a new computer works")
    public void testOpenAddPage() {
        adder = listPage.addComputer();
        Assert.assertTrue(adder.checkHeader());
    }

    @Test(priority = 1, groups = "add", description = "Button Create this computer works when all fields are filled")
    public void testAddComputer() {
        Calendar introducedDate = Calendar.getInstance();
        introducedDate.add(Calendar.YEAR, -10);
        Calendar discontinuedDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        adder.createComputer(computerName, formatter.format(introducedDate.getTime()), formatter.format(discontinuedDate.getTime()), 10);
        Assert.assertTrue(listPage.checkHeader());
    }

    @Test(priority = 2, groups = "add", description = "Alert 'Done ! Computer [computerName] has been created' is displayed on the page")
    public void testAlert() {
        Assert.assertTrue(listPage.checkAlert(computerName));
    }

    @Test(priority = 3, groups = "add", description = "Computer is present in table (check by seeking)")
    public void testPresence() {
        Assert.assertTrue(listPage.isComputerPresent(computerName, false));
        //Assert.assertTrue(listPage.isComputerPresent("Amiga 3000UX", true));
    }

    @Test(priority = 4, dependsOnGroups = {"add"}, description = "Computer is removed from table")
    public void testRemoving() {
        Assert.assertTrue(listPage.removeComputer(computerName));
        //Assert.assertTrue(listPage.removeComputer("Amiga 3000UX"));
    }

    @AfterClass
    public void Close() {
        driver.close();
    }

}

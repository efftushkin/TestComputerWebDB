import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

public class Driver {
    private static WebDriver webDriver;

    private Driver() {
    }

    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            System.setProperty("webdriver.opera.driver", "src/test/resources/operadriver_win64/operadriver.exe");
            webDriver = new OperaDriver();
            //OperaDriver doesn't support headless mode 0_o
        }

        return webDriver;
    }
}

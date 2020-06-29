package digital.paisley;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.console.Console;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Main {

    public static void main(String[] args) {

        //Selenium 4
        System.setProperty("webdriver.chrome.driver", "/home/meysam/app/chromedriver_linux64/chromedriver");
        var chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.get("https://paisley.digital/");
        System.out.println(chromeDriver.getTitle());


        var chromeDevTools = chromeDriver.getDevTools();
        //Session of ChromeDevTool
        chromeDevTools.createSession();

        inspectArticle(chromeDriver);
        loginToGmail(chromeDriver);

    }


    private static void consoleLogs(DevTools chromeDevTools, String message) {

        chromeDevTools.send(Console.enable());

        //add listener to verify the console message
        chromeDevTools.addListener(Console.messageAdded(), consoleMessageFromDevTools ->
                assertEquals(true, consoleMessageFromDevTools.getText().equals(message)));

    }

    private static void inspectArticle(ChromeDriver chromeDriver) {

        // New Tab
        var blogTab = chromeDriver.switchTo().newWindow(WindowType.TAB);
        blogTab.get("https://www.paisley.digital/blog/");
        List<WebElement> article = blogTab.findElements(By.tagName("article"));
        article.get(0).findElement(By.tagName("a")).click();
        System.out.println(article.size());
    }

    private static void loginToGmail(ChromeDriver chromeDriver) {

        // New Tab
        var blogTab = chromeDriver.switchTo().newWindow(WindowType.TAB);
        blogTab.get("https://www.gmail.com");

        //login
        blogTab.findElement(By.id("identifierId")).sendKeys("bot.paisley");
        blogTab.findElement(By.id("identifierNext")).click();

        blogTab.findElement(By.name("password")).sendKeys("password");
        blogTab.findElement(By.id("passwordNext")).click();

    }
}

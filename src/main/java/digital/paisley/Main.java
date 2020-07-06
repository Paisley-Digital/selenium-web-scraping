package digital.paisley;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.log.Log;

import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

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

        chromeDevTools.send(Log.enable());
        chromeDevTools.addListener(Log.entryAdded(), entry -> System.out.println(entry.getText()));

    }

    private static void inspectArticle(ChromeDriver chromeDriver) {

        // New Tab
        var blogTab = chromeDriver.switchTo().newWindow(WindowType.TAB);
        blogTab.get("https://www.paisley.digital/blog/");
        List<WebElement> article = blogTab.findElements(By.tagName("article"));
        article.get(0).findElement(By.tagName("a")).click();
        System.out.println(article.size());
    }

    private static void loginToGmail(ChromeDriver chromeDriver) throws InterruptedException {

        // New Tab
        var blogTab = chromeDriver.switchTo().newWindow(WindowType.TAB);
        blogTab.get("https://www.gmail.com");

        //login
        blogTab.findElement(By.id("identifierId")).sendKeys("bot.paisley");
        blogTab.findElement(By.id("identifierNext")).click();
        Thread.sleep(1000);
        blogTab.findElement(By.name("password")).sendKeys("****");
        blogTab.findElement(By.id("passwordNext")).click();
        Thread.sleep(1000);

    }
}

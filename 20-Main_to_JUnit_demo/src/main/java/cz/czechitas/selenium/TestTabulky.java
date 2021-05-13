package cz.czechitas.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class TestTabulky {

    public void run() throws Exception {
//      System.setProperty("webdriver.gecko.driver", System.getProperty("user.home") + "/Java-Training/Selenium/geckodriver");
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");

        WebDriver prohlizec;
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        prohlizec.navigate().to("https://automation3.shinekamil.repl.co/");
        WebElement zalozkaTabulka = prohlizec.findElement(By.id("table"));
        zalozkaTabulka.click();

        List<WebElement> seznamZvirat = prohlizec.findElements(By.xpath("//table[contains(@class, 'qa-animals')]//td[1]"));
        // TODO: Zkontroluj, ze 7 == seznamZvirat.size()

        WebElement elementPrvnihoZvirete = seznamZvirat.get(0);
        String prvniZvire = elementPrvnihoZvirete.getText();
        // TODO: Zkontroluj, ze "Kočka" == prvniZvire

        Thread.sleep(10_000);

        prohlizec.close();
    }
}

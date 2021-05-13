package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestyTabulek {

    WebDriver prohlizec;

    @BeforeEach
    public void setUp() {
//      System.setProperty("webdriver.gecko.driver", System.getProperty("user.home") + "/Java-Training/Selenium/geckodriver");
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void zviratVTabulceMusiByt7() {
        prohlizec.navigate().to("https://automation3.shinekamil.repl.co/");
        WebElement zalozkaTabulka = prohlizec.findElement(By.linkText("Table"));
        zalozkaTabulka.click();

        List<WebElement> seznamZvirat = prohlizec.findElements(By.xpath("//table/tbody/tr/td[1]"));
        Assertions.assertEquals(7, seznamZvirat.size());
    }

    @Test
    public void prvniZvireVTabulceMusiBytKocka() {
        prohlizec.navigate().to("https://automation3.shinekamil.repl.co/");
        WebElement zalozkaTabulka = prohlizec.findElement(By.linkText("Table"));
        zalozkaTabulka.click();

        List<WebElement> seznamZvirat = prohlizec.findElements(By.xpath("//table/tbody/tr/td[1]"));
        WebElement elementPrvnihoZvirete = seznamZvirat.get(0);
        String prvniZvire = elementPrvnihoZvirete.getText();
        Assertions.assertEquals("Kočka", prvniZvire);
    }

    @AfterEach
    public void tearDown() {
        prohlizec.close();
    }
}

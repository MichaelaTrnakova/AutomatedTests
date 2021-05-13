package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestyLoginu {

    WebDriver prohlizec;

    @BeforeEach
    public void setUp() {
//      System.setProperty("webdriver.gecko.driver", System.getProperty("user.home") + "/Java-Training/Selenium/geckodriver");
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void poPrihlaseniMusiBytObrazekSovy() {
        prohlizec.navigate().to("https://automation3.shinekamil.repl.co/login.html");
        WebElement polickoJmeno = prohlizec.findElement(By.id("login-username"));
        polickoJmeno.sendKeys("czechitas");
        WebElement polickoHeslo = prohlizec.findElement(By.id("login-password"));
        polickoHeslo.sendKeys("D4Ostr4v42020");
        WebElement tlacitkoOdeslat = prohlizec.findElement(By.id("login-submit"));
        tlacitkoOdeslat.click();

        WebElement obrazekSovy = prohlizec.findElement(By.className("qa-owl"));
        Assertions.assertEquals("img", obrazekSovy.getTagName());
    }

    @AfterEach
    public void tearDown() {
        prohlizec.close();
    }
}

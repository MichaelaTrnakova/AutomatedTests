package cz.czechitas.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestyPrihlasovaniNaKurzy {

    WebDriver prohlizec;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void prihlaseniDoAplikace() {
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/prihlaseni");
        String textPrihlasen = prihlasitRodice();
        Assertions.assertEquals("Přihlášen", textPrihlasen);
    }

    @Test
    public void vytvorPrihlaskuProDiteVarinata1(){
        String jmenoDite = "Vaclav Muj" + new Random().nextInt();
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/zaci/pridat");
        prihlasitRodice();
        VyplnPrihlasku(jmenoDite);
        ZkontrolujPrihlaskuDitete(jmenoDite);
    }

    @Test
    public void vytvorPrihlaskuProDiteVarinata2(){
        String jmenoDite = "Vaclav Muj" + new Random().nextInt();
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/prihlaseni");
        prihlasitRodice();
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/zaci/pridat");
        VyplnPrihlasku(jmenoDite);
        ZkontrolujPrihlaskuDitete(jmenoDite);
    }

    @Test
    public void odhlaseniZKurzu(){
        vytvorPrihlaskuProDiteVarinata1();
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/zaci");
        var odhlasitBunky = prohlizec.findElement(By.id("DataTables_Table_0")).findElements(By.className("btn-danger"));

       odhlasitBunky.get(0).click();
            OdhlasitZaka();
            Assertions.assertNotEquals(odhlasitBunky.size(), prohlizec.findElement(By.id("DataTables_Table_0")).findElements(By.className("btn-danger")).size());

    }

    private void OdhlasitZaka(){
        prohlizec.findElement(By.id("logged_out_other")).sendKeys(Keys.SPACE);
        prohlizec.findElement(By.xpath("//input[@value='Odhlásit žáka']")).click();
    }

    private void VyplnPrihlasku(String jmenoDite){
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/zaci/pridat/41-html-1");
        prohlizec.findElement(By.xpath("//button[@data-id='term_id']")).click();
        prohlizec.findElement(By.xpath("//input[@aria-label='Search']")).sendKeys("21");
        prohlizec.findElement(By.xpath("//input[@aria-label='Search']")).sendKeys(Keys.ENTER);
        prohlizec.findElement(By.id("forename")).sendKeys(jmenoDite.split(" ")[0]);
        prohlizec.findElement(By.id("surname")).sendKeys(jmenoDite.split(" ")[1]);
        prohlizec.findElement(By.id("birthday")).sendKeys("21.12.2012");
        prohlizec.findElement(By.id("payment_transfer")).sendKeys(Keys.SPACE);
        prohlizec.findElement(By.id("terms_conditions")).sendKeys(Keys.SPACE);
        prohlizec.findElement(By.xpath("//input[@value='Vytvořit přihlášku']")).click();
    }

    private void ZkontrolujPrihlaskuDitete(String name){
       prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/zaci");
        List<WebElement> bunky = prohlizec.findElement(By.id("DataTables_Table_0")).findElements(By.xpath("//tbody/tr/td"));
        boolean jmenoNalezeno = false;
        for(var bunka : bunky){
           if(bunka.getText().equals(name)){
               jmenoNalezeno = true;
           }
       }
       Assertions.assertTrue(jmenoNalezeno);
    }

    private String prihlasitRodice() {
        prohlizec.findElement(By.id("email")).sendKeys("michelleseydoux@seznam.cz");
        prohlizec.findElement(By.id("password")).sendKeys("Holcicka123");
        prohlizec.findElement(By.xpath("//button[@type='submit']")).click();
        return prohlizec.findElement(By.xpath("//div[@class='nav-item dropdown']/span")).getText();
    }
    @AfterEach
    public void tearDown() {
        prohlizec.close();
    }
}

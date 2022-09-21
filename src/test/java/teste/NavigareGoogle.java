package teste;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.testng.Assert.assertTrue;


public class NavigareGoogle {

  WebDriver webdriver;


  @BeforeClass
  public void before () {

    WebDriverManager chromeManager = WebDriverManager.chromedriver();
    chromeManager.setup();
    webdriver = chromeManager.create();
  }


  @Test
  public void navighezGit () {

    // pregatesc cat sa astept pana se intampla ceva
    WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(3));

    // navighez la pagina specificata
    webdriver.get("https://github.com/nyoany/andreeaTest");

    // click pe sign in si astept sa apara formularul de login
    webdriver.findElement(By.className("HeaderMenu-link--sign-in")).click();
    wait.until(visibilityOfElementLocated(By.cssSelector("form[action='/session']")));

    // setez user si password
    webdriver.findElement(By.cssSelector("input[name='login']")).sendKeys("user");
    webdriver.findElement(By.cssSelector("input[name='password']")).sendKeys("gresit");

//    dau click pe sign in
    webdriver.findElement(By.cssSelector("input[type='submit']")).click();

    // verific ca am eroare pe pagina pentru ca parola este gresita
    wait.until(webdriver -> webdriver.getCurrentUrl().endsWith("/session"));

    assertTrue(
        webdriver.findElement(By.cssSelector("div.flash-error")).isDisplayed(),
        "Nu a aparut eroare pe formular chiar daca userul si parola sunt gresite."
    );
  }
}

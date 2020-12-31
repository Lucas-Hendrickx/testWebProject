import domain.service.Service;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import ui.controller.Controller;


import javax.servlet.http.HttpSession;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class Story5_CoronaTestOverview {
    /***
     * zorg dat je een test gebruiker hebt waar je contacten aan kunt toevoegen
     * zorg dat je een 2de test gebruiker hebt zonder contacten
     */
    private WebDriver driver;
    private String path = "http://localhost:8081/Controller";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\School\\Extra Programma's\\Driver\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void not_logged_user_is_redirected_to_Home_page_when_link_is_used(){
        new ContactPage(driver);
        String title = driver.getTitle();
        assertEquals(title,"Home");
    }

    @Test
    public void aantal_contacten_klopt()  {
        loginTestGebruiker();
        ContactPage contactPage = new ContactPage(driver);
        int firstCount = contactPage.getAantalContacten();
        int time = (int) Math.floor(Math.random()*1200);
        contactPage.addContact("29112020", String.valueOf(time), "First Child");
        int secondCount = contactPage.getAantalContacten();


        assertEquals(firstCount + 1,secondCount);
    }

    @Test
    public void gebruiker_zonder_contacten_weergeeft_geen_contacten() {
            loginTestGebruikerGeenContacten();
            ContactPage contactPage = new ContactPage(driver);
            assertEquals(contactPage.getAantalContacten(),0);
        }

    @Test
    public void van_home_naar_contacten_werkt_bij_ingelogde_gebruiker() {
            HomePage homePage = new HomePage(driver);
            loginTestGebruiker();
            homePage.goToContact();
            assertEquals(driver.getTitle(),"Corona Test");
        }

    @Test
    public void van_contacten_naar_home_werkt_bij_ingelogde_gebruiker() {
            loginTestGebruiker();
            ContactPage page = new ContactPage(driver);
            page.goToHome();
            assertEquals(driver.getTitle(),"Home");
    }


    @Test(expected = org.openqa.selenium.NoSuchElementException.class)
    public void van_home_naar_contacten_bij_niet_ingelogde_gebruiker_exception()  {
        HomePage page = new HomePage(driver);
        page.goToContact();
    }

    @Test
    public void naar_contacten_pagina_gaan_zonder_in_te_loggen_wordt_geredirected_naar_home() {
        ContactPage page = new ContactPage(driver);
        assertEquals(driver.getTitle(),"Home");
    }


    public void loginTestGebruiker(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.login("GotContacts@gmail.com", "0400000000");
    }

    public void loginTestGebruikerGeenContacten(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.login("NoContacts@gmail.com", "0400000000");
    }


}

package com.techproed.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandlingAlerts {

    //Bir class olusturun: HandlingAlerts
    //https://the-internet.herokuapp.com/javascript_alerts adresine gidin.

    WebDriver driver;
    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }
    //Bir metod olusturun: acceptAlert
    //1. butona tıklayın, uyarıdaki OK butonuna tıklayın ve result mesajının görüntülendiğini doğrulayın.
    @Test
    public  void acceptAlert(){
        WebElement button1 = driver.findElement(By.xpath("//button[@onclick='jsAlert()']"));
        button1.click();
        System.out.println("Alert messaji : "+ driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();

        WebElement resultMessage = driver.findElement(By.xpath("//p[@id='result']"));
        Assert.assertTrue(resultMessage.isDisplayed());//hard assert

    }



    @Test
    public void dismissAlert(){
        //Bir metod olusturun: dismissAlert
        //2. butona tıklayın, uyarıdaki Cancel butonuna tıklayın ve result mesajının görüntülendiğini doğrulayın.

        WebElement button2 = driver.findElement(By.xpath("//button[@onclick='jsConfirm()']"));
        button2.click();
        //uyarıdaki Cancel butonuna tıklayın
        driver.switchTo().alert().dismiss();

        WebElement resultCancelMesaj=driver.findElement(By.xpath("//p[@id='result']"));
        String actualResult = resultCancelMesaj.getText();
        String expectedResult= "You clicked: Cancel";
        Assert.assertEquals(actualResult, expectedResult);// hard assert

    }

    @Test
    public void sendKeysAlert(){
        //Bir metod olusturun: sendKeysAlert
        //3. butona tıklayın, uyarıdaki metin kutusuna isminizi yazin, OK butonuna tıklayın ve result mesajının görüntülendiğini doğrulayın.

        WebElement button3= driver.findElement(By.xpath("//button[@onclick='jsPrompt()']"));
        button3.click();
        driver.switchTo().alert().sendKeys("Tahsin");
        driver.switchTo().alert().accept();

        WebElement resultMesaj=driver.findElement(By.xpath("//p[@id='result']"));
        Assert.assertTrue( resultMesaj.isDisplayed());

    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }



}

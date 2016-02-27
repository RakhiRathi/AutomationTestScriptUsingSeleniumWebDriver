package com.rakhi.selenium.registeraccount;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rakhirathi
 */
import static org.junit.Assert.fail;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author rakhirathi
 */
public class NpuRegisterAccountTest {

    public WebDriver driver;
    public String baseUrl1;
    public String baseUrl2;
    //private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    //@Ignore
    @Test
    public void testPreloadNPUHomepageFromGoogleSearch() throws Exception {
        driver.get(baseUrl2); // google.com
        driver.manage().window().maximize(); // max window size
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS); // wait until page load

        driver.findElement(By.id("lst-ib")).clear(); // clear
        driver.findElement(By.id("lst-ib")).sendKeys("NPU"); // type NPU
        driver.findElement(By.xpath("/html/body/div/div[3]/form/div[2]/div[2]/div[1]/div[1]/div[2]/div/div/div/button")).click(); // Go

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        this.sleep(2);

        driver.findElement(By.linkText("Northwestern Polytechnic University - Fremont")).click(); // Click Search Result to open
        // driver.findElement(By.linkText("NPU Home")).click(); // Click Search Result to open
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // Open Online Application
        driver.findElement(By.xpath("//*[@id=\"headline_container\"]/div[1]/p/a")).click(); // Online Application button
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        this.sleep(5);
        driver.findElement(By.id("regcheck")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("Register Account")).click();

        // This statement will return True, in case of first Radio button is selected
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElement(By.xpath("/html/body/div/div[2]/fieldset/form[1]/p/input[1]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElement(By.id("FirstName")).clear(); // clear
        driver.findElement(By.id("FirstName")).sendKeys("aaa"); // type NPU

        driver.findElement(By.id("LastName")).clear(); // clear
        driver.findElement(By.id("LastName")).sendKeys("bbb"); // type NPU

        new Select(driver.findElement(By.id("Gender"))).selectByVisibleText("Female");

        driver.findElement(By.id("Email")).clear(); // clear
        driver.findElement(By.id("Email")).sendKeys("bbb@gmail.com"); // 

        driver.findElement(By.id("Password")).clear(); // clear
        driver.findElement(By.id("Password")).sendKeys("abcdef"); // 

        driver.findElement(By.id("ConfirmPassword")).clear(); // clear
        driver.findElement(By.id("ConfirmPassword")).sendKeys("defght"); // 
        driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);

        driver.findElement(By.xpath("/html/body/div/div[2]/fieldset/form[2]/input")).click(); // save button
        driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

        // go back to NPU Home page
        driver.navigate().back();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // close all
        Thread.sleep(2);
        driver.quit();
    }

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl1 = "http://npu.edu/";
        baseUrl2 = "http://google.com/";
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {

        }
    }

}

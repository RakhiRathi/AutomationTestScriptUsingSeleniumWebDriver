package com.rakhi.selenium.readfromexcel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.Assert.fail;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author rakhirathi
 */
public class ReadDataFromExcelFileTest {

    public WebDriver driver;
    public String baseUrl1;
    public String baseUrl2;
    String searchText1;
    String searchText2;

    private StringBuffer verificationErrors = new StringBuffer();

    //@Ignore
    @Test
    public void testPreloadNPUHomepageFromGoogleSearch() throws Exception {
        try {
            FileInputStream file = new FileInputStream(new File("/Users/rakhipartani/Downloads/CSS522_Automation_Data.xlsx"));
            //HSSFWorkbook workbook = new HSSFWorkbook(file);
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //HSSFSheet sheet = workbook.getSheetAt(0);
            XSSFSheet sheet = workbook.getSheetAt(0);

            String heading = sheet.getRow(0).getCell(0).getStringCellValue();

            searchText1 = sheet.getRow(1).getCell(0).getStringCellValue();

            searchText2 = sheet.getRow(2).getCell(0).getStringCellValue();

            System.out.println("Heading is: " + heading);

            System.out.println("Search Text 1 is: " + searchText1);

            System.out.println("Search Text 2 is: " + searchText2);

            file.close();

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        driver.get(baseUrl1); // google.com
        driver.manage().window().maximize(); // max window size
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS); // wait until page load

        driver.findElement(By.id("UserName")).clear(); // clear
        driver.findElement(By.id("UserName")).sendKeys(searchText1);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        this.sleep(2);
        driver.findElement(By.id("Password")).clear(); // clear
        driver.findElement(By.id("Password")).sendKeys(searchText2);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        this.sleep(2);
        driver.findElement(By.xpath("/html/body/div/div[2]/fieldset/form/p[2]/input")).click(); // Go

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        this.sleep(2);

        // go back to NPU Home page
        driver.navigate().back();

        // close all
        Thread.sleep(2);
        driver.quit();

    }

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl1 = "https://osc.npu.edu/Account/Logon?enc=td2nmreLJyypG4lX9oxb4obTqnVB8pwMcILEgDP1ajfK4QYWfetEDr2JalCyz0fB";
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

package testkap.selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EmailVerification {

    // Create webdriver object
    static WebDriver driver;

    // Create object for business functions
    static EmailVerifyFunctions professor;

    // Make sure this runs before the first test method
    @BeforeClass
    public static void before()
    {
        driver = new FirefoxDriver();
        driver.get("https://login.yahoo.com/?.src=ym&.intl=us+" +
                   "&.lang=en-US&.done=https%3a//mail.yahoo.com");
        professor = new EmailVerifyFunctions(driver);
    }

    // Make sure this runs after all test methods have run
    @AfterClass
    public static void after()
    {
        driver.quit();
        driver = null;
    }

    // Begin test method
    @Test(description = "Login to Yahoo email and assert predefined values")
    public void EmailVerifyConetnts()
    {
        // Login to Yahoo mail
        professor.login("professorkaplan@yahoo.com", "abc123$");

        // Call message scan, select, subject and body read function
        professor.messageListClick();

        // Assert expected values from email subject and body
        Assert.assertEquals("Welcome to Yahoo! Kaplan", professor.messageSubject());
        Assert.assertTrue(professor.messageBody(), "thank you for choosing Yahoo");

    }
}
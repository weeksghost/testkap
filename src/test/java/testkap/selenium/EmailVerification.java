package testkap.selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EmailVerification {

    static WebDriver driver;
    static EmailVerifyFunctions professor;

    @BeforeClass
    public static void before()
    {
        driver = new FirefoxDriver();
        driver.get("https://login.yahoo.com/?.src=ym&.intl=us+" +
                   "&.lang=en-US&.done=https%3a//mail.yahoo.com");
        professor = new EmailVerifyFunctions(driver);
    }
    @AfterClass
    public static void after()
    {
        driver.quit();
        driver = null;
    }

    @Test(description = "Login to Yahoo email and assert predefined values")
    public void EmailVerifyConetnts()
    {
        professor.login("professorkaplan@yahoo.com", "abc123$");
        professor.messageListClick();
        professor.waitForElement();

        Assert.assertEquals("Welcome to Yahoo! Kaplan", professor.messageSubject());
        Assert.assertTrue(professor.messageBody(), "thank you for choosing Yahoo");

    }
}

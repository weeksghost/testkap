package testkap.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class EmailVerifyFunctions {

    // Create webdriver instance
    static WebDriver driver;

    // Constructor class
    public EmailVerifyFunctions(WebDriver driver) {

        this.driver = driver;

    }

    // Email login function
    public void login(String email, String password) {

        driver.findElement(By.id("login-username")).sendKeys(email);
        driver.findElement(By.id("login-passwd")).sendKeys(password);
        driver.findElement(By.id("login-signin")).click();

        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.titleContains("Yahoo Mail"));

    }

    // Get list of messages in inbox, loop over then click on desired message
    public void messageListClick() {

        List<WebElement> latestEmail = driver.findElements(By
                                        .className("list-view-items-page"));

        for (int i = 0; i < latestEmail.size(); i++) {
            WebElement subName = latestEmail.get(i);

            List<WebElement> subj = subName.findElements(By.className("subject"));

            for (int j=1; j < subj.size(); j++) {
                String subtext = subj.get(j).getText();
                if (subtext.contains("Welcome")) {
                    subj.get(j).click();
                }

                }
            }
        }

    // Return part of text from email Subject field
    public String messageSubject() {

        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOf(driver
                        .findElement(By.className("composeshim")))
                );

        WebElement mailSubject = driver.findElement(By.
                                    className("thread-subject")
        );
        return mailSubject.getText();

    }

    // Return part of text from email body
    public boolean messageBody() {

        WebElement emailBody = driver.findElement(By.
                                    className("email-wrapped")
        );
        String bodyText;
        bodyText = emailBody.getText();
        if (bodyText.contains("thank you for choosing Yahoo"));
            return true;

    }

    // Pause helper function
    public void waitForElement()
    {

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

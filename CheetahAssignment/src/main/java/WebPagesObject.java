import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WebPagesObject {
    private WebDriver webDriver;
    private FluentWait<WebDriver> wait;

    /**
     * Open flickr url using chrome and get verifier code text
     *
     * @param url
     * @return
     */
    public String getVerifierFromChromeBrowser(String url) {
        String flickrPassword = PropertiesReader.getInstance().getProperty("password");
        String flickrUserName = PropertiesReader.getInstance().getProperty("email");
        String verifier = null;

        webDriver = openFlickrWebSite(url);

        if (webDriver != null) {
            loginFlickr(flickrUserName, flickrPassword);
            verifier = getVerifierCodeText();
            webDriver.quit();
        }

        return verifier;
    }

    /**
     * openFlickrWebSite
     *
     * @param url
     * @return
     */
    private WebDriver openFlickrWebSite(String url) {
        WebDriver webDriver = new WebDriverCreator().createChromeDriver();

        if (webDriver != null) {
            webDriver.get(url);
            webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            wait = new FluentWait<>(webDriver).withTimeout(Duration.ofSeconds(30))
                    .ignoring(StaleElementReferenceException.class).pollingEvery(Duration.ofSeconds(5));
        }

        return webDriver;
    }

    /**
     * loginFlickr
     *
     * @param flickrUserName
     * @param flickrPassword
     */
    private void loginFlickr(String flickrUserName, String flickrPassword) {
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id("login-email")))).sendKeys(flickrUserName);
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.cssSelector("[data-testid='identity-form-submit-button']")))).click();
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id("login-password")))).sendKeys(flickrPassword);
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.cssSelector("[data-testid='identity-form-submit-button']")))).click();
    }

    /**
     * getVerifierCodeText
     *
     * @return
     */
    private String getVerifierCodeText() {
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.cssSelector("#permissions div input.Butt")))).click();
        String verifier = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.cssSelector("#Main span")))).getText();
        return verifier;
    }
}

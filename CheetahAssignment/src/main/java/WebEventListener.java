import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class WebEventListener implements WebDriverEventListener, ITestListener {

    @Override
    public void beforeAlertAccept(WebDriver driver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterAlertAccept(WebDriver driver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        Log.info(" Before navigating to: " + url);
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        Log.info(" Navigated to: " + url);
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
        Log.info(" Going to navigate back to previous page");
    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        Log.info(" Navigated back to previous page");
    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
        Log.info(" Going to navigate forward to next page");
    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
        Log.info(" Navigated forward to next page");
    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {
        Log.info(" Going to refresh page");
    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {
        Log.info(" Page refreshed");
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        Log.info(" Trying to find Element By : " + by.toString());
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        Log.info(" Found Element By : " + by.toString());
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        Log.info(" Trying to click on: " + element.toString());
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        Log.info(" Clicked on: " + element.toString());
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        Log.info(" Value of the element:" + element.toString() + " need to change to " + keysToSend[0]);
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        Log.info(" Element " + element.toString() + " value changed to: " + keysToSend[0]);
    }

    @Override
    public void beforeScript(String script, WebDriver driver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterScript(String script, WebDriver driver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {
        Log.info("Going to switch window: " + windowName);
    }

    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {
        Log.info("Switched window: " + windowName);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        Log.info("Exception occured: " + throwable);
    }

    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
        // TODO Auto-generated method stub
    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterGetText(WebElement arg0, WebDriver arg1, String arg2) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeGetText(WebElement arg0, WebDriver arg1) {
        // TODO Auto-generated method stub
    }
}

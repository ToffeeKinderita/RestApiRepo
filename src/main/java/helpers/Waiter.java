package helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Waiter {

    private static int afterWaitSleep = 1000;

    @SuppressWarnings("unchecked assignment")
    public static void waitFor(ExpectedCondition condition) {
        new WebDriverWait(getWebDriver(), 60).until(condition);
        sleep(afterWaitSleep);
    }

    public static void waitFor(ExpectedCondition condition, int timeout){
        getWaiter(timeout).until(condition);
    }

    public static void waitForPageToLoad() {
        waitFor(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return ((String) executeJavaScript("return document.readyState")).equalsIgnoreCase("complete");
            }
        });
        sleep(afterWaitSleep);
    }

    public static void  waitForPageTitle(String title){
        getWaiter().until(ExpectedConditions.titleIs(title));
    }

    public static void waitForAjax(int timeOutInSeconds) {
        getWaiter(timeOutInSeconds).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return (Boolean) executeJavaScript("return !(ExtJSUtil.hasActiveAjaxCalls(Ext.Ajax.requests && !_.isEmpty(Ext.Ajax.requests)));");
            }
        });
    }

    public static void waitForJquery(){
        getWaiter().until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                JavascriptExecutor js = (JavascriptExecutor) webDriver;
                return (Boolean) js.executeScript("return jQuery.active == 0");
            }
        });
        sleep(500);
    }

    private static WebDriverWait getWaiter(){
        return new WebDriverWait(getWebDriver(), TimeoutConstants.MEDIUM_TIMEOUT);
    }

    private static WebDriverWait getWaiter(final int timeout){
        return new WebDriverWait(getWebDriver(), timeout);
    }

}

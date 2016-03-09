package jp.co.iti.glan8.samplelogin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;

/**
 * Welcome画面のページオブジェクト実装(Appiumモード)。
 */
public class WelcomePage {

    protected AndroidDriver mDriver;
    protected WebDriverWait mWait;

    public WelcomePage(AndroidDriver driver) {
        mDriver = driver;
        mWait = new WebDriverWait(mDriver, 60L);
    }

    /**
     * このページがロードされるまで一定時間待つ
     *
     * @return ロードされた場合にはtrueを、ロードが完了する前にタイムアウトした場合にはfalseを返す
     */
    public boolean waitUntilLoad() {
        try {
            mWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("welcome")));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    /**
     * このページに表示されている文言を取得する。
     */
    public String getText() {
        return mDriver.findElement(By.id("welcome")).getText();
    }

}

package jp.co.iti.glan8.samplelogin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;

import static org.junit.Assert.*;

/**
 * Login画面のページオブジェクト実装(Appiumモード)。
 */
public class LoginPage {

    protected AndroidDriver mDriver;
    protected WebDriverWait mWait;

    public LoginPage(AndroidDriver driver) {
        mDriver = driver;
        mWait = new WebDriverWait(mDriver, 60L);
    }

    /**
     * このページがロードされるまで一定時間待つ。
     *
     * @return ロードされた場合にはtrueを、ロードが完了する前にタイムアウトした場合にはfalseを返す。
     */
    public boolean waitUntilLoad() {
        // Email入力欄が見えるようになるまで待つ。
        try {
            mWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    /**
     * 「Email」欄に入力する。
     * 引数にnullが指定された場合には何もしない。
     *
     * @param email 入力するEmail。
     * @return この画面
     */
    public LoginPage inputEmail(String email) {
        if (email != null) {
            // TODO エレメントの検索方法は最適化したい
//            WebElement emailTextField = mDriver.findElement(MobileBy.AccessibilityId("email textfield"));
            WebElement emailTextField = mDriver.findElement(By.id("email"));
            emailTextField.sendKeys(email);
        }
        return this;
    }

    /**
     * 「passeord」欄に入力する。
     * 引数にnullが指定された場合には何もしない。
     *
     * @param password 入力するパスワード
     * @return この画面
     */
    public LoginPage inputPassword(String password) {
        if (password != null) {
            WebElement passwordTextField = mDriver.findElement(By.id("password"));
            passwordTextField.sendKeys(password);
        }
        return this;
    }

    /**
     * ログイン
     *
     * @return ウェルカム画面
     */
    public WelcomePage login() {
        mDriver.findElement(By.id("email_sign_in_button")).click();
        return new WelcomePage(mDriver);
    }

}

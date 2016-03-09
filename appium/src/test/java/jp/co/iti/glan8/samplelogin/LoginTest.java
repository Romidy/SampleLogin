package jp.co.iti.glan8.samplelogin;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.OutputType;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.util.logging.SimpleFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import jp.co.iti.glan8.samplelogin.page.LoginPage;
import jp.co.iti.glan8.samplelogin.page.WelcomePage;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Appiumモードで動作するテストクラスのサンプル
 * デフォルトのLogin Activityを利用
 */

public class LoginTest {

    private SimpleDateFormat fmt;
    private AndroidDriver mDriver;

    public LoginTest() {
        fmt = new SimpleDateFormat("yyyyMMddHHmmss");
    }

    @Before
    public void setUp() throws Exception {
        AppiumLauncher.launch();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "jp.co.iti.glan8.samplelogin");
        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "jp.co.iti.glan8.samplelogin.LoginActivity");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator");
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        capabilities.setCapability("app", new File(new File(System.getProperty("user.dir")), "../app/build/outputs/apk/app-release-unsigned.apk").getAbsolutePath());
        capabilities.setCapability("autoAcceptAlerts", true);
        mDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        mDriver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws Exception {
        mDriver.quit();

        AppiumLauncher.stop();
    }

    /**
     * ログインできる事を確認
     */
    @Test
    public void ログインできること() throws Exception {
        LoginPage login = new LoginPage(mDriver);
        assertTrue(login.waitUntilLoad());

        screenShot("login");

        login.inputEmail("test@iti.co.jp");
        login.inputPassword("test01");
        screenShot("input");

        WelcomePage welcome = login.login();

        assertTrue(welcome.waitUntilLoad());
        screenShot("welcome");

        assertThat(welcome.getText(), is("Welcome!!"));
    }

    /**
     * 画面スクリーンショットを保存
     */
    private void screenShot(String ss_name) {
        try {
            File ss_file = new File(new File(System.getProperty("user.dir")), "screenshot/" + fmt.format(new Date()) + "_" + ss_name + ".png");
            FileUtils.writeByteArrayToFile(ss_file, mDriver.getScreenshotAs(OutputType.BYTES));
        }catch (IOException e) {
        }
    }
}

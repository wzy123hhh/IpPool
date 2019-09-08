import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class test {

    public static  WebDriver getDriver(){
        DesiredCapabilities dacps = new DesiredCapabilities();

        dacps.setCapability("acceptSslCerts","true");
        //ssl证书支持
        dacps.setCapability("acceptSslCerts", true);
        //截屏支持
        dacps.setCapability("takesScreenshot", false);
        //css搜索支持
        dacps.setCapability("cssSelectorsEnabled", true);
        //js支持
        dacps.setJavascriptEnabled(true);
        //驱动支持
        dacps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "D:\\crawer\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        PhantomJSDriver driver = new PhantomJSDriver(dacps);
        return  driver;
    }

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver","E:\\chromedriver.exe");
        WebDriver driver = getDriver();

        driver.get("https://3c.tmall.com/?spm=a21bo.2017.201859.5.5af911d99gKl9X");
        System.out.println(driver.getTitle());
        System.out.println("===============================");
        System.out.println(driver.getPageSource());
        System.out.println("===============================");
        System.out.println(driver.getCurrentUrl());
        System.out.println("===============================");
        System.out.println(driver.getWindowHandle());
        System.out.println("===============================");

        driver.quit();


    }



}

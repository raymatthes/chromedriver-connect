package org.dev

import org.apache.commons.lang3.SystemUtils
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import java.time.Duration

/**
 * @author Ray Matthes
 */
class Application {

    public static final String CHROMEDRIVER_PATH_LINUX64 = './bin/linux64/chromedriver'
    public static final String CHROMEDRIVER_PATH_MAC64 = './bin/mac64/chromedriver'
    public static final String CHROMEDRIVER_PATH_MAC_ARM_64 = './bin/mac64_arm/chromedriver'
    public static final String CHROMEDRIVER_PATH_WIN64 = './bin/win64/chromedriver.exe'

    static void main(String[] args) {
        println('test start')

        String path = determineExecutable()
        System.setProperty('webdriver.chrome.driver', path)
        System.setProperty("webdriver.chrome.logfile", "chromedriver.log")
        System.setProperty("webdriver.chrome.verboseLogging", "true")

        ChromeOptions options = new ChromeOptions()
        options.setAcceptInsecureCerts(true)

        WebDriver driver = new ChromeDriver(options)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10))
        driver.get("https://www.google.com")
        By locator = By.name("q")
        wait.until(ExpectedConditions.elementToBeClickable(locator))
        WebElement element = driver.findElement(locator)
        element.sendKeys("Selenium Java")
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul")))
        element.submit()
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search")))
        driver.quit()

        println('test success')

        System.exit(0)
    }

    protected static String determineExecutable() {
        String path = ''

        if (SystemUtils.IS_OS_MAC) {
            path = (SystemUtils.OS_ARCH == 'x86_64') ?
                    CHROMEDRIVER_PATH_MAC64 :
                    CHROMEDRIVER_PATH_MAC_ARM_64
        } else if (SystemUtils.IS_OS_LINUX) {
            path = CHROMEDRIVER_PATH_LINUX64
        } else if (SystemUtils.IS_OS_WINDOWS) {
            path = CHROMEDRIVER_PATH_WIN64
        } else {
            throw new Error('Unknown OS')
        }

        path
    }

}

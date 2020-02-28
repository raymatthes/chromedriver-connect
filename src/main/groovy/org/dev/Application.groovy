package org.dev

import org.apache.commons.lang3.SystemUtils
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

/**
 * @author Ray Matthes
 */
class Application {

    public static final String CHROMEDRIVER_PATH_LINUX64 = './bin/linux64/chromedriver'
    public static final String CHROMEDRIVER_PATH_MAC64 = './bin/mac64/chromedriver'
    public static final String CHROMEDRIVER_PATH_WIN32 = './bin/win32/chromedriver.exe'

    static void main(String[] args) {
        println('test start')

        String path = determineExecutable()
        System.setProperty('webdriver.chrome.driver', path)
        System.setProperty("webdriver.chrome.logfile", "chromedriver.log")
        System.setProperty("webdriver.chrome.verboseLogging", "true")

        ChromeOptions options = new ChromeOptions()
        options.setAcceptInsecureCerts(true)

        WebDriver driver = new ChromeDriver(options)
        driver.get("http://www.google.com")

        sleep(3000)

        driver.quit()

        println('test success')

        System.exit(0)
    }

    protected static String determineExecutable() {
        String path = CHROMEDRIVER_PATH_MAC64
        if (SystemUtils.IS_OS_LINUX) {
            path = CHROMEDRIVER_PATH_LINUX64
        } else if (SystemUtils.IS_OS_WINDOWS) {
            path = CHROMEDRIVER_PATH_WIN32
        }
        path
    }

}

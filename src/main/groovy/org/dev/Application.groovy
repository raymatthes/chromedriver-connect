package org.dev

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

/**
 * @author Ray Matthes
 */
class Application {

    public static final String CHROMEDRIVER_PATH = './bin/macos/chromedriver'

    static void main(String[] args) {
        println("howdy!")

        System.setProperty('webdriver.chrome.driver', CHROMEDRIVER_PATH)
        System.setProperty("webdriver.chrome.logfile", "chromedriver.log")
        System.setProperty("webdriver.chrome.verboseLogging", "true")

        ChromeOptions options = new ChromeOptions()
        options.setAcceptInsecureCerts(true)

        WebDriver driver = new ChromeDriver(options)
        driver.get("http://www.google.com")

        sleep(3000)

        driver.quit()   

        System.exit(0)
    }

}

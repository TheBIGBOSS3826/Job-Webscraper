//Importing Selenium libraries such as EdgeDriver, EdgeOptions, ExpectedConditions, WebDriverWait. Also importing Duration and WebDriveManager
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

//Creating a class Scraper that will scrape the raw HTML data off the website 
public class Scraper {

    //Creating a method named scrapeHTML that will scrape the raw HTML with the URL to the website passed as the argument and return it 
    public String scrapeHTML(String url) {

        //Declaring a WebDriver object named driver and setting it to null. This will be responsible for interacting with the browser 
        WebDriver driver = null;

        //Initializing a try-catch block that will attempt to scrape the raw HTML data
        try {

            //Setting up the WebDriver for Microsoft Edge (default browser that all computers have access to)
            WebDriverManager.edgedriver().setup();

            //Configuring the Edge browser options such that it opens at the maximum size the screen will allow for 
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--start-maximized");

            //Connecting the driver variable with the settings just made above for it 
            driver = new EdgeDriver(options);

            //Navigating to the specific url 
            driver.get(url);

            //Creating a WebDriverWait object named wait that will make the driver wait to do anything until the <body> tag is present to ensure it was loaded correctly
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

            //Creating a JavaScriptExecutor named javaScript that will specifically run a JavaScript command to mimic browser scrolling so that all the content is visible
            JavascriptExecutor javaScript = (JavascriptExecutor) driver;

            //Using a for loop to execute javaScript five times and pausing between such that all the content properly is visible
            for (int i = 0; i < 5; i++) {

                //Calling javaScript to execute a command to scroll down to the bottom of the page 
                javaScript.executeScript("window.scrollTo(0, document.body.scrollHeight);");

                //Using Thread.sleep to stop the scrolling for 3 seconds such that all the necessary content has a chance to load 
                Thread.sleep(3000); 
            }

            //Returning the raw HTML data that the driver was able to see by using .getPageSource() 
            return driver.getPageSource();

        } 
        
        //If there is any error that prevents scraping, display an error message
        catch (Exception e) {

            //Error message 
            System.err.println("An error occurred during scraping: " + e.getMessage());

            //Since no data can be returned, null is returned instead 
            return null;

        } 
        
        //Using a finally block to close out the driver 
        finally {

            //Checking to see if the driver object isn't null such that it is closed to free up space. 
            if (driver != null) {

                //Closing the driver using .quit()
                driver.quit();

            }

        }

    }

}

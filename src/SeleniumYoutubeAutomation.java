import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;
import java.util.List;
public class SeleniumYoutubeAutomation
{
	public static void main(String[] args)
	{
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\srivatsa gubbi\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Setup Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            // Open YouTube
            driver.get("https://www.youtube.com");
            Thread.sleep(5000);
            
            // Search for a video
            WebElement searchBox = driver.findElement(By.name("search_query"));
            searchBox.sendKeys("Programming");
            searchBox.submit();
            Thread.sleep(5000);

            // Click on the first video result to open the video
            WebElement firstResult = driver.findElement(By.cssSelector("ytd-video-renderer #video-title"));
            firstResult.click();
            Thread.sleep(5000);
            
            // Check if an ad is present and skip it if found
            skipAd(driver);
            Thread.sleep(5000);
            
            // Locate and print the description text
            WebElement descriptionBox = driver.findElement(By.xpath("//*[@id='description-inline-expander']"));
            String descriptionText = descriptionBox.getText();
            System.out.println("Description: " + descriptionText);
            
            // Navigate back to the YouTube homepage
            driver.navigate().back();
            // Ensure to navigate back to the homepage
            driver.navigate().back();

            // Locate and click on the "Shorts" link
            WebElement shortsLink = driver.findElement(By.xpath("//a[@title='Shorts']"));
            shortsLink.click();
            
            // Wait for the Shorts section to load
            Thread.sleep(10000);

        }
        catch (InterruptedException e)
        {
        	e.printStackTrace();
        }
        finally
        {
        	driver.quit();
        }
    }
	// Method to skip ad if it is present
    private static void skipAd(WebDriver driver) {
        List<WebElement> adSkipButton = driver.findElements(By.xpath("//*[@id=\"skip-button:3\"]"));
        if (!adSkipButton.isEmpty()) {
            adSkipButton.get(0).click();
            System.out.println("Ad skipped.");
        } else {
            System.out.println("No ad to skip.");
        }
    }
}

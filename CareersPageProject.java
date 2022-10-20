import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CareersPageProject {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Chirag\\Downloads\\chromedriver_win32\\chromedriver1.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://numadic.com/careers/");
		driver.manage().window().maximize();

		CareersLandingPage(driver);

	}

	public static void CareersLandingPage(WebDriver driver) throws InterruptedException {

		Actions act = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		/* Test case 01 -- Job type : Full time, Location : Mumbai */
		Thread.sleep(3000);

		WebElement staticDropDown = driver.findElement(By.id("job_type"));
		Select dropdown = new Select(staticDropDown);
		dropdown.selectByIndex(1);

		WebElement staticDropdown1 = driver.findElement(By.id("job_location"));
		Select dropdown1 = new Select(staticDropdown1);
		dropdown1.selectByIndex(3);

		WebElement availability = driver
				.findElement(By.xpath("//td[text()='There are no available job positions that match your filters.']"));
		if (availability.isDisplayed()) {

			/* Test Case 02: Job Type : Full time, Location : Goa */
			WebElement staticDropDown3 = driver.findElement(By.id("job_location"));
			Select dropdown2 = new Select(staticDropDown3);
			dropdown2.selectByIndex(1);

			// Scolling down till QA Engineer Job Title appears ---
			js.executeScript("window.scrollBy(0,1000)");

			List<WebElement> jobsListed = driver.findElements(By.cssSelector(".job-title"));
			for (int i = 0; i < jobsListed.size(); i++) {
				
				Thread.sleep(3000);
				String preferredJob = jobsListed.get(i).getText();
				if (preferredJob.contains("QA Engineer")) {

					// With the help of action class, element is interactable.

					WebElement element = driver.findElement(By.xpath("//a[text()='QA Engineer']"));
					act.moveToElement(element).click().build().perform();

					js.executeScript("window.scrollBy(0,1200)");
					WebElement applyNow = driver.findElement(By.xpath("//a[text()='Apply here now']"));
					if (applyNow.isEnabled()) {
						driver.close();
					}

				}
			}

		}

	}

}

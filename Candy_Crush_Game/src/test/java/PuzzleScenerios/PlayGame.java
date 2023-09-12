package PuzzleScenerios;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class PlayGame<AndroidElement> {
	public AndroidDriver driver;
	public int screenWidth;
	public int screenHeight;

	@BeforeTest
	public void setUp() throws Exception {

		UiAutomator2Options options = new UiAutomator2Options().setDeviceName("Android Emulator")
				.setApp(System.getProperty("user.dir") + "/base.apk").setPlatformName("Android");
		options.setCapability("deviceName", "Candy_Crush_Game");
		options.setCapability("platformName", "Android");
		options.setCapability("platformVersion", "12.0");
		options.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "game2048.b2048game.twozerofoureight2048.games");
		options.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MenuActivity");
		try {
			WebDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void automateGame() {

		//driver.findElement(By.id("com.king.candycrushsaga:id/play_button")).click();

		int tapX = 686; // Specify the X coordinate of the tap position
		int tapY = 1234; // Specify the Y coordinate of the tap position

		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(PointOption.point(tapX, tapY)).perform();

		swipeLeft(driver, 500);
		swipeRight(driver, 500);
		swipeUp(driver, 500);
		swipeDown(driver, 500);

		// Continue with other actions or assertions in your test

		driver.quit();
	}

	// Swipe left
	public static void swipeLeft(AndroidDriver driver, int duration) {
		WebElement gameBoard = driver.findElement(By.id("com.king.candycrushsaga:id/game_board"));
		int startX = gameBoard.getLocation().getX() + gameBoard.getSize().getWidth() - 10;
		int startY = gameBoard.getLocation().getY() + gameBoard.getSize().getHeight() / 2;
		int endX = 10;
		int endY = startY;
		performSwipe(driver, startX, startY, endX, endY, duration);
	}

	// Swipe right
	public static void swipeRight(AndroidDriver driver, int duration) {
		WebElement gameBoard = driver.findElement(By.id("com.king.candycrushsaga:id/game_board"));
		int startX = 10;
		int startY = gameBoard.getLocation().getY() + gameBoard.getSize().getHeight() / 2;
		int endX = gameBoard.getLocation().getX() + gameBoard.getSize().getWidth() - 10;
		int endY = startY;
		performSwipe(driver, startX, startY, endX, endY, duration);
	}

	// Swipe up
	public static void swipeUp(AndroidDriver driver, int duration) {
		WebElement gameBoard = driver.findElement(By.id("com.king.candycrushsaga:id/game_board"));
		int startX = gameBoard.getLocation().getX() + gameBoard.getSize().getWidth() / 2;
		int startY = gameBoard.getLocation().getY() + gameBoard.getSize().getHeight() - 10;
		int endX = startX;
		int endY = gameBoard.getLocation().getY() + 10;
		performSwipe(driver, startX, startY, endX, endY, duration);
	}

	// Swipe down
	public static void swipeDown(AndroidDriver driver, int duration) {
		WebElement gameBoard = driver.findElement(By.id("com.king.candycrushsaga:id/game_board"));
		int startX = gameBoard.getLocation().getX() + gameBoard.getSize().getWidth() / 2;
		int startY = gameBoard.getLocation().getY() + 10;
		int endX = startX;
		int endY = gameBoard.getLocation().getY() + gameBoard.getSize().getHeight() - 10;
		performSwipe(driver, startX, startY, endX, endY, duration);
	}

	// Perform swipe action
	public static void performSwipe(AndroidDriver driver, int startX, int startY, int endX, int endY, int duration) {
		WaitOptions waitOptions = new WaitOptions().withDuration(Duration.ofMillis(duration));
		driver.performTouchAction(
				new io.appium.java_client.TouchAction<>(driver).press(PointOption.point(startX, startY))
						.waitAction(waitOptions).moveTo(PointOption.point(endX, endY)).release());
	}
}
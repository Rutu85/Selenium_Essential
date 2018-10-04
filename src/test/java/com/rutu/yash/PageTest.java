package com.rutu.yash;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PageTest {
	
	FirefoxDriver driver;
	
	@BeforeMethod
	public void beforeMethod() {
		driver = new FirefoxDriver();
	}

	@Test
	public void cssSelector() {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("Rutu");
		driver.findElement(By.cssSelector("button[name='websubmit']")).click();
		driver.findElement(By.cssSelector(".inputtext")).sendKeys("ersagefd");;
		driver.findElement(By.cssSelector("#pass")).sendKeys("rdfwsa");
	}
	
	@Test
	public void dragAndDrop() {
		driver.get("http://jqueryui.com/droppable/");
		Actions builder = new Actions(driver);
		driver.switchTo().frame(0);
		WebElement drag = driver.findElement(By.id("draggable"));
		WebElement drop = driver.findElement(By.id("droppable"));
		builder.dragAndDrop(drag, drop).build().perform();
	}
	
	@Test
	public void explicitWait() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait exWait = new WebDriverWait(driver,100);
		driver.get("https://accounts.google.com/ServiceLogin/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=AddSession");
		driver.findElement(By.xpath("//*[@id='identifierId']")).sendKeys("test49@gmail.com");
		driver.findElement(By.xpath("//*[@id='identifierNext']/content/span")).click();
		
		
		driver.findElement(By.xpath("//*[@name='password']")).sendKeys("eyrgfwdjfc");
		driver.findElement(By.xpath("//*[@id='passwordNext']/content/span")).click();
		
		exWait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(".//*[@id='password']/div[2]/div[2]"), "nnnWrong Password, try again"));
		
		System.out.println("I am printed after 100 s");
	}
	
	@Test
	public void SelectClass() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
		WebElement month = driver.findElement(By.cssSelector("#month"));
		Select select = new Select(month);
		select.selectByIndex(6);
		WebElement date = driver.findElement(By.cssSelector("#day"));
		select = new Select(date);
		select.selectByIndex(8);
	}
	
	@Test
	public void frameTest() {
		driver.get("http://jqueryui.com/droppable/");
		driver.switchTo().frame(driver.findElement(By.cssSelector(".demo-frame")));
		driver.findElement(By.cssSelector("#draggable"));
		System.out.println("in frame");
		driver.switchTo().defaultContent();
		System.out.println("main page");
	}
	
	@Test
	public void javaScriptExecutor() {
		driver.get("http://www.mississauga.ca/portal/cityhall/agendas");
		JavascriptExecutor JS = (JavascriptExecutor)driver;
		//JS.executeAsyncScript("window.scrollBy(0,1000)", "");
		WebElement council = driver.findElement(By.linkText("Council"));
		JS.executeScript("arguments[0].scrollIntoView(true)",council);
	}
	
	@Test
	public void keyTest() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://accounts.google.com/ServiceLogin/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=AddSession");
		driver.findElement(By.xpath("//*[@id='identifierId']")).sendKeys("test49@gmail.com");
		driver.findElement(By.cssSelector("#identifierId")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//*[@name='password']")).sendKeys("eyrgfwdjfc");
		driver.findElement(By.xpath("//*[@name='password']")).sendKeys(Keys.ENTER);;
	}
	
	@Test
	public void windowSizeTest() {
		driver.get("https://accounts.google.com/ServiceLogin/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=AddSession");
		driver.manage().window().maximize();
	}
	
	@Test
	public void hoverAction() {
		driver.get("http://www.mississauga.ca/portal/home");
		WebElement image = driver.findElement(By.xpath(".//img[@alt='City Hall']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(image).build().perform();
	}
	
	@Test
	public void multipleWindowTest() {
		FirefoxDriver driver = new FirefoxDriver();
		driver.get("https://moodle.cestarcollege.com/moodle/");
		driver.findElement(By.linkText("Faq")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		Object[] ids = windowHandles.toArray();
		
		System.out.println(ids[0]);
		System.out.println(ids[1]);
		
		driver.switchTo().window((String)ids[0]);
		driver.switchTo().window((String)ids[1]);
		
		Iterator<String> itr = windowHandles.iterator();
		String main = itr.next();
		String slave = itr.next();
		driver.switchTo().window(main);
		driver.switchTo().window(slave);
	}
	
	@Test
	public void navigate() {
		driver.navigate().to("https://www.google.ca/");
		driver.navigate().to("https://moodle.cestarcollege.com/moodle/");
		driver.navigate().forward();
		driver.navigate().back();
		driver.navigate().refresh();
	}
	
	@Test
	public void openPageinNewWindow() {
		Actions builder = new Actions(driver);
		driver.get("https://www.google.com/");
		WebElement image = driver.findElement(By.cssSelector(".Fx4vi"));
		builder.keyDown(Keys.SHIFT).click(image).keyUp(Keys.SHIFT).build().perform();
	}
	
	@Test
	public void pageLoadTimeOut() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		Actions builder = new Actions(driver);
		driver.get("http://jqueryui.com/droppable/");
		driver.switchTo().frame(0);
		WebElement draggable = driver.findElement(By.id("draggable"));
		WebElement droppable = driver.findElement(By.id("droppable"));
		System.out.println(droppable.getText());
		
		builder.dragAndDrop(draggable, droppable).build().perform();
		
		System.out.println(droppable.getText());
	}
	
	@Test
	public void popUpTest() {
		driver.get("https://www.aliexpress.com/");
		List<WebElement> popup = driver.findElements(By.cssSelector(".close-layer"));
		
		if(popup.size() == 1) {
			popup.get(0).click();
			System.out.println("There was a popup on screen, and I have closed it");
		}else {
			System.out.println("There is No popup");
		}
	}
	
	@Test
	public void radioButton() {
		driver.get("http://www.echoecho.com/htmlforms10.htm");
		driver.findElement(By.cssSelector("input[value='Milk']")).click();
		if(driver.findElement(By.cssSelector("input[value='Milk']")).isSelected()) {
			System.out.println("selected");
		}
	}
	
	@Test
	public void screenShotTest() throws IOException {
		FirefoxDriver driver = new FirefoxDriver();
		driver.get("http://www.mississauga.ca/portal/home");
		
		File srcFile = driver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("E:\\ScreenShot\\screenshot.jpeg"));
	}
	
	@Test
	public void dropDownTest() {
		driver.get("https://www.ebay.ca/");
		List<WebElement> dropDownMenu = driver.findElements(By.xpath("//*[@aria-label='Select a category for search']/option"));
		
		System.out.println(dropDownMenu.size());
		System.out.println("**************");
		for(WebElement q : dropDownMenu) {
			System.out.println(q.getText()+"------"+q.isSelected());
		}
		
		driver.findElement(By.xpath("//*[@aria-label='Select a category for search']")).sendKeys("Books");
		System.out.println();
		System.out.println("*****************after****************");
		System.out.println();
		for(WebElement q : dropDownMenu) {
			System.out.println(q.getText()+"------"+q.isSelected());
		}
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}

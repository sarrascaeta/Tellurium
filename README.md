# Tellurium
Easy WebDriver library

Tellurium is made to easily create WebDriver automation. It abstracts much of the WebDriver code, letting you write automation much more easily and with less code.

# Examples

If you want to create a Chrome webdriver with standard Selenium, you would need code like this:
    System.setProperty("webdriver.chrome.driver", "C:\Path to chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    
With Telurium, this is all you need to type:
    WebDriver driver = DriverFactory.createChromeDriver();
    

With standard Selenium Webdriver, this is the code you need to write to click on an element:
    driver.findElement(By.cssSelector("css.selector.here")).click;
    
Here's how you would do it with Tellurium. The Tellurium click will also automatically use a global wait time for the element. Additionally, it will catch StaleReferenceExceptions and retry the element with a fresh reference, keeping errors down!
    click(css("css.selector.here"));
    
# How to use
Just import the library and extend the Tellurium class in whatever class you need its functionality. Just remember to set the webdriver with the setDriver() method.

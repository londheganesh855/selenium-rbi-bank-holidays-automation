package pages;

import org.openqa.selenium.*;
import org.testng.Assert;

public class BankHomePage {
    protected WebDriver driver;

    // Constructor
    public BankHomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    protected final By languagePopup = By.id("dialogLan");
    protected final By rememberLanguageCheckbox = By.id("CheckBox1");
    protected final By englishLanguageButton = By.xpath(".//button[text()='English']");
    public final By bankHolidaysFooterLink = By.xpath("//ul[@class='footerlist']//a[text()='Bank Holidays']");

    // Language Selection
    public void handleLanguagePopup() {
        try {
            if (driver.findElement(languagePopup).isDisplayed()) {
                driver.findElement(rememberLanguageCheckbox).click();
                driver.findElement(languagePopup).findElement(englishLanguageButton).click();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Language popup not displayed.");
        }
    }

    // Navigation
    public void clickBankHolidaysFooterLink() {
        driver.findElement(bankHolidaysFooterLink).click();
    }

    
    // Utility Methods
    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void verifyPageTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title mismatch!");
        System.out.println("Page title verified: " + actualTitle);
    }
}

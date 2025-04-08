package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HolidayPage {
    private WebDriver driver;

    public HolidayPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators 

    public final By regionalOfficeDropdown = By.id("drRegionalOffice");
    public final By monthDropdown = By.id("drMonth");
    protected final By goButton = By.id("btnGo");

    public final By holidatSubTitle = By.className("sub_title");
    protected final By holidayTableCells = By.xpath("(//table//tbody)[3]/tr//td");

    // Actions & Verifications 

    public void selectDropdownByVisibleText(By dropdownLocator, String visibleText) {
        WebElement dropdownElement = driver.findElement(dropdownLocator);
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(visibleText);
        System.out.println("Selected '" + visibleText + "' from dropdown.");
    }

    public void verifySelectedDropdownValue(By dropdownLocator, String expectedText) {
        WebElement dropdownElement = driver.findElement(dropdownLocator);
        Select select = new Select(dropdownElement);
        String selectedText = select.getFirstSelectedOption().getText();
        Assert.assertEquals(selectedText, expectedText, "Dropdown selection mismatch!");
        System.out.println("Verified selected value: " + selectedText);
    }

    public void clickGoButton() {
        driver.findElement(goButton).click();
        System.out.println("Clicked on Go button.");
    }

    public void verifyElementVisibleWithText(By locator, String expectedText) {
        WebElement element = driver.findElement(locator);
        Assert.assertTrue(element.isDisplayed(), "Element is not visible on the page!");
        String actualText = element.getText();
        Assert.assertEquals(actualText, expectedText, "Text mismatch!");
        System.out.println("Verified subtitle: " + actualText);
    }

    public void extractAndSaveHolidayData() {
        List<WebElement> allCells = driver.findElements(holidayTableCells);
        List<String> holidayDataList = new ArrayList<>();
        String currentMonth = "";

        for (int i = 0; i < allCells.size(); i++) {
            String text = allCells.get(i).getText().trim().replace("�", "");

            if (text.matches("^[A-Za-z]+$")) {
                currentMonth = text;
            } else if (text.matches("^\\d{1,2}$")) {
                String day = text;
                if (i + 1 < allCells.size()) {
                    String occasion = allCells.get(i + 1).getText().trim().replace("�", "");
                    holidayDataList.add(currentMonth + "," + day + "," + occasion);
                    i++; // Skip next cell (occasion)
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("RBI_Holidays_Cleaned.txt"))) {
            for (String entry : holidayDataList) {
                writer.write(entry);
                writer.newLine();
            }
            System.out.println("Holiday data saved to RBI_Holidays_Cleaned.txt");
        } catch (IOException e) {
            System.err.println("Failed to write holiday data: " + e.getMessage());
        }
    }
}

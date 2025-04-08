package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.BankHomePage;
import pages.HolidayPage;
import utils.Constants;

public class ReserveBankOfIndiaHolidays extends BaseTest {

    // Constants
    private final String RBI_HOME_TITLE = "Reserve Bank of India";
    private final String RBI_HOLIDAY_PAGE_TITLE = "Reserve Bank of India - Holidays";
    private final String REGION = "Mumbai";
    private final String MONTH = "All Months";
    private final String HOLIDAY_SUBTITLE = "MUMBAI REGIONAL OFFICE HOLIDAY LIST FOR THE YEAR 2025";

    @Test
    public void reserveBankOfIndiaHolidaysTest() {
        // Initialize Page Objects
        BankHomePage bankHomePage = new BankHomePage(driver);
        HolidayPage holidayPage = new HolidayPage(driver);

        // Step 1: Navigate to the RBI website
        driver.get(Constants.BASE_URL);

        // Step 2: Verify the home page title
        bankHomePage.verifyPageTitle(RBI_HOME_TITLE);

        // Step 3: Handle the language popup if it appears
        bankHomePage.handleLanguagePopup();

        // Step 4: Scroll to and click on the "Bank Holidays" footer link
        bankHomePage.scrollToElement(bankHomePage.bankHolidaysFooterLink);
        bankHomePage.clickBankHolidaysFooterLink();

        // Step 5: Select "Mumbai" from the Regional Office dropdown
        holidayPage.selectDropdownByVisibleText(holidayPage.regionalOfficeDropdown, REGION);

        // Step 6: Select "All Months" from the Month dropdown
        holidayPage.selectDropdownByVisibleText(holidayPage.monthDropdown, MONTH);

        // Step 7: Verify "Mumbai" is selected
        holidayPage.verifySelectedDropdownValue(holidayPage.regionalOfficeDropdown, REGION);

        // Step 8: Verify "All Months" is selected
        holidayPage.verifySelectedDropdownValue(holidayPage.monthDropdown, MONTH);

        // Step 9: Click the "Go" button to display the holiday list
        holidayPage.clickGoButton();

        // Step 10: Verify the holidays page title
        bankHomePage.verifyPageTitle(RBI_HOLIDAY_PAGE_TITLE);

        // Step 11: Verify the holiday section subtitle
        holidayPage.verifyElementVisibleWithText(holidayPage.holidatSubTitle, HOLIDAY_SUBTITLE);

        // Step 12: Extract the holiday table data and save it to a file
        holidayPage.extractAndSaveHolidayData();
    }
}

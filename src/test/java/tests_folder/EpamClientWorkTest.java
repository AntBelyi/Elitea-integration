package tests_folder;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import junit.framework.TestCase;

public class EpamClientWorkTest extends TestCase {

    private Playwright playwright;
    private Browser browser;
    private Page page;

    @Override
    protected void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        page = browser.newPage();
    }

    @Override
    protected void tearDown() {
        closeBrowser();
    }

    private void closeBrowser() {
        if (browser != null) {
            browser.close();
            browser = null;
        }
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }

    public void testClientWorkIsVisible() {
        try {
            page.navigate("https://www.epam.com/");
            page.locator("a.top-navigation__item-link.js-op[href=\"/services\"]").click();
            page.locator("a[href=\"https://www.epam.com/services/client-work\"]").click();

            assertTrue(page.getByRole(com.microsoft.playwright.options.AriaRole.HEADING,
                    new Page.GetByRoleOptions().setName("Client Work")).isVisible());
        } finally {
            closeBrowser();
        }
    }
}

package com.learning;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginTest {
    /*private static final Map<String, String> uiConfig = new UIConfig().getConfig();
    private static final String USER_NAME = uiConfig.get("user");
    private static final String PASSWORD = uiConfig.get("password");
    private static final ThreadLocal<LoginPage> loginPage = new ThreadLocal<>();
    private static final ThreadLocal<DashboardsPage> dashboardsPage = new ThreadLocal<>();

    private static final ThreadLocal<WebDriver> currentDriver = new ThreadLocal<>();
    private static final List<WebDriver> driversToCleanup = Collections.synchronizedList(new ArrayList<>());


    @AfterAll
    public static void tearDown() {
        Iterator<WebDriver> iterator = driversToCleanup.iterator();
        while (iterator.hasNext()) {
            WebDriver driver = iterator.next();
            driver.quit();
            iterator.remove();
        }
    }

    public static Object[][] wrongUsers() {
        return new Object[][]{
                {"1", "1"},
                {"advanced", "1"},
                {"1", "advanced"},
                {"Qwerty123456", "1"},
        };
    }

    @BeforeEach
    public void initDrivers(){
        if (currentDriver.get()==null) {
            final WebDriver driver = DriverManager.getDriver();
            driversToCleanup.add(driver);
            currentDriver.set(driver);
        }
        loginPage.set(new LoginPage(currentDriver.get()));

    }

    @Test()
    void testSuccessfulLogin() {

        dashboardsPage.set(new DashboardsPage(currentDriver.get()));
        loginPage.get().login(USER_NAME, PASSWORD);
        assertTrue(dashboardsPage.get().isPageLoaded(), "Dashboards page is not loaded");
    }

    @ParameterizedTest
    @MethodSource("wrongUsers")
    void testWrongCredsLogin(String userName, String password) {

        loginPage.get().login(userName, password);
        loginPage.get().waitForRedirection();
        assertTrue(loginPage.get().isOnPage(), "Login page is not loaded anymore!");

    }*/
}

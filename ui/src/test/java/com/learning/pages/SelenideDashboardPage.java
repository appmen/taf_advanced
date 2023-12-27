package com.learning.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class SelenideDashboardPage {
    private static final Logger LOG = LogManager.getRootLogger();
    private final SelenideElement dashboardsLink = $(By.xpath("//*[contains(text(), 'All Dashboards')]"));
    private final ElementsCollection dashboards = $$(By.xpath("//a[contains(@class, 'dashboardTable')]"));
    private final ElementsCollection dashboardWidgets =
            $$(By.xpath("//div[@class='widgets-grid']//div[contains(@class, 'widgetHeader__widget-name-block')]"));
    private final SelenideElement editDashboardButton = $(By.xpath("//*[contains(text(), 'Edit')]"));
    private final SelenideElement addButton = $(By.xpath("//button[contains(text(), 'Add')]"));
    private final SelenideElement updateDashboardButton = $(By.xpath("//*[contains(text(), 'Update')]"));
    private final SelenideElement dashboardNameDuringEdit = $(By.xpath("//input[@placeholder='Enter dashboard name']"));
    private final SelenideElement dashboardDescriptionDuringEdit = $(By.xpath("//textarea"));
    private final SelenideElement dashboardDescriptionInTable = $(By.xpath("//*[contains(@class, 'dashboardTable__description')]"));
    private final SelenideElement addNewDashboardButton = $(By.xpath("//*[contains(text(), 'Add New Dashboard')]"));
    private final SelenideElement confirmDeleteButton = $(By.xpath("//button[contains(text(), 'Delete')]"));
    private final SelenideElement addWidget = $(By.xpath("//div[contains(@class, 'empty-widget-content')]//*[contains(text(), 'Add new widget')]"));
    private final ElementsCollection widgetOptions = $$(By.xpath("//div[contains(@class, 'widgetTypeItem')]/label"));
    private final SelenideElement nextStepButton = $(By.xpath("//*[contains(text(), 'Next step')]"));
    private final ElementsCollection activeFiltersList = $$(By.xpath("//div[contains(@class, 'filtersList')]//label"));



    public void isPageLoaded() {
        LOG.info("Checking if user is on a dashboard page");
        dashboardsLink.shouldBe(visible);
    }

    public void goToAllDashboards() {
        dashboardsLink.click();
    }

    public String getDashboardDescription() {
        return dashboardDescriptionInTable.shouldBe(visible).text();
    }

    public void createDashboard(String dashboardName, String dashboardDescription) {
        LOG.info(format("Creating a dashboard with name %s and description %s", dashboardName, dashboardDescription));
        addNewDashboardButton.click();
        dashboardNameDuringEdit.val(dashboardName);
        dashboardDescriptionDuringEdit.val(dashboardDescription);
        addButton.click();
    }

    public void addWidget(String dashboardName, String widgetType, String widgetName){
        LOG.info(format("Adding a widget %s to a dashboard with name %s", widgetName, dashboardName));
        addWidget.shouldBe(visible);
        addWidget.click();
        widgetOptions.find(text(widgetType)).click();
        nextStepButton.click();
        activeFiltersList.find(text(widgetName)).click();
        nextStepButton.click();
        addButton.click();
    }

    public void deleteDashboard(String dashboardName) {
        $(By.xpath(
                format("//a[contains(text(), '%s') and contains(@class, 'dashboardTable__name')]/..//i[contains(@class, 'delete')]",
                        dashboardName)))
                .click();
        confirmDeleteButton.click();
    }

    public void openDashboardByName(String dashboardName) {
        LOG.info("Opening dashboard " + dashboardName);
        getDashboardByName(dashboardName).get(0).click();
    }

    public Point dragDashboardJava(String widgetToMove, String widgetToBeReplaced, Map<String, Point> widgetCoords) {
        Point secondElementLocation = widgetCoords.get(widgetToBeReplaced);

        SelenideElement toMove = dashboardWidgets.find(text(widgetToMove));
        try {
            LOG.info(format("Dragging %s widget to %s widget position", widgetToMove, widgetToBeReplaced));
            actions().dragAndDropBy(toMove, secondElementLocation.getX(), secondElementLocation.getY()).perform();
        } catch (MoveTargetOutOfBoundsException ignored) {
        }
        return dashboardWidgets.shouldHave(size(widgetCoords.size())).find(text(widgetToMove)).getLocation();
    }

    public void scrollToWidget(String widgetToScroll) {
        SelenideElement elementToMove = dashboardWidgets.shouldHave(itemWithText(widgetToScroll))
                .find(text(widgetToScroll));
        executeJavaScript("arguments[0].scrollIntoView();", elementToMove);
    }

    public Boolean isInViewJs(String widget) {
        String jsCode = "function isInViewport(element) {\n" +
                "    const rect = element.getBoundingClientRect();\n" +
                "    return (\n" +
                "        rect.top >= 0 &&\n" +
                "        rect.left >= 0 &&\n" +
                "        rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) &&\n" +
                "        rect.right <= (window.innerWidth || document.documentElement.clientWidth)\n" +
                "    );\n" +
                "};" +
                "return isInViewport(arguments[0]);";
        SelenideElement elementToMove = dashboardWidgets.shouldHave(itemWithText(widget))
                .find(text(widget));
        return executeJavaScript(jsCode, elementToMove);
    }

    public void editDashboardDescription(String description) {
        executeJavaScript("arguments[0].click();", editDashboardButton);
        dashboardDescriptionDuringEdit.clear();
        dashboardDescriptionDuringEdit.val(description);
        updateDashboardButton.click();
    }

    public Map<String, Point> getWidgetsCoords() {
        LOG.info("Getting coords for widgets");
        Map<String, Point> widgetCoords = new HashMap<>();

        dashboardWidgets.get(0).shouldNotBe(empty);
        dashboardWidgets.forEach(widget -> widgetCoords.put(widget.text(), widget.getLocation()));

        return widgetCoords;
    }

    public ElementsCollection getDashboardByName(String dashboardName) {
        LOG.info("Getting all dashboards with name " + dashboardName);
        return dashboards.filterBy(text(dashboardName));
    }
}

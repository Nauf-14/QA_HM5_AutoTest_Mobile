import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.time.Duration;

public class HW5 {
    // Инициализация фабрики драйвера и драйвера
    private final DriverFactory driverFactory = new DriverFactory();
    private AndroidDriver<?> driver;

    // Элементы интерфейса пользователя с помощью аннотации @AndroidFindBy
    @AndroidFindBy(accessibility = "Каталог")
    MobileElement catalogElement;

    @AndroidFindBy(id = "block_search_widget")
    MobileElement searchWidget;
    @AndroidFindBy(id = "search_src_text")
    MobileElement searchText;

    @AndroidFindBy(uiAutomator = "resourceIdMatches(\".*id/title\").textContains(\"товаров\")")
    MobileElement foundTitle;

    @AndroidFindBy(id = "filter_label")
    MobileElement filterLabel;

    @AndroidFindBy(id = "filter_switch")
    MobileElement filterSwitch;

    @AndroidFindBy(id = "apply_filters")
    MobileElement applyFiltersButton;

    @AndroidFindBy(id = "specify_category_pager")
    MobileElement specifyCategoryPager;

    // Метод для настройки драйвера перед каждым тестом
    @Before
    @Step("Driver creation")
    public void setDriver() throws MalformedURLException {
        driver = driverFactory.setUp();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // Тестовый метод для проверки основного сценария
    @Test
    @Story("test mvideo")
    public void homeTest() {
        // Проверка начального состояния элемента
        Assert.assertFalse(catalogElement.isSelected());
        // Выполнение действий в приложении
        catalogElement.click();
        swipe(Direction.UP);
        swipe(Direction.DOWN);
        searchWidget.click();
        String television = "Телевизор";
        searchText.sendKeys(television);
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        String foundTitleText = foundTitle.getText();
        filterLabel.click();
        filterSwitch.click();
        applyFiltersButton.click();
        String foundSaleTitleText = foundTitle.getText();
        Assert.assertNotEquals(foundTitleText, foundSaleTitleText);

        // Проверка состояния после действий
        driver.lockDevice(Duration.ofSeconds(3));
        Assert.assertEquals(television, searchText.getText());

        swipe(Direction.LEFT, specifyCategoryPager);
    }

    // Метод для завершения работы с драйвером после каждого теста
    @After
    @Step("Closing the driver")
    public void tearDown() {
        driver.quit();
    }

    // Перечисление направлений свайпа
    private enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }

    // Метод для выполнения свайпа по экрану
    private void swipe(Direction direction) { // swipe across the screen
        Dimension dims = driver.manage().window().getSize();
        int width = dims.width / 2;
        int height = dims.height / 2;
        swipeExecution(direction, width, height);
    }

    // Метод для выполнения свайпа по указанному элементу
    private void swipe(Direction direction, MobileElement mobileElement) { // swipe on a specific element
        int width = mobileElement.getCenter().x;
        int height = mobileElement.getCenter().y;
        swipeExecution(direction, width, height);
    }

    // Метод для выполнения свайпа
    @Step("swipe {direction}")
    private void swipeExecution(Direction direction, int width, int height) {
        int border = 5;
        int press = 300;
        Dimension dims = driver.manage().window().getSize();
        PointOption<?> pointOptionStart = PointOption.point(width, height);
        PointOption<?> pointOptionEnd;
        switch (direction) {
            case RIGHT:
                pointOptionEnd = PointOption.point(dims.width - border, height);
                break;
            case LEFT:
                pointOptionEnd = PointOption.point(border, height);
                break;
            case UP:
                pointOptionEnd = PointOption.point(width, border);
                break;
            case DOWN:
                pointOptionEnd = PointOption.point(width, dims.height - border);
                break;
            default:
                throw new IllegalArgumentException("swipe is NOT supported");
        }

        new TouchAction<>(driver)
                .press(pointOptionStart)
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(press)))
                .moveTo(pointOptionEnd)
                .release()
                .perform();
    }
}

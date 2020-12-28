package pages;

import io.qameta.allure.Step;
import managers.DriverManager;
import managers.PageManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    /**
     * Менеджер страниц
     *
     * @see PageManager
     */
    protected PageManager appManager = PageManager.getPageManager();

    /**
     * Объект для имитации реального поведения мыши или клавиатуры
     *
     * @see Actions
     */
    protected Actions action = new Actions(DriverManager.getDriver());

    /**
     * Объект для выполнения любого js кода
     *
     * @see JavascriptExecutor
     */
    protected JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

    /**
     * Объект явного ожидания
     * При применении будет ожидать задонного состояния 10 секунд с интервалом в 1 секунду
     *
     * @see WebDriverWait
     */
    protected WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 10, 1000);

    /**
     * Конструктор позволяющий инициализировать все странички и их эелементы помеченные анотацией {@link FindBy}
     * Подробнее можно просмотреть в класс {@link org.openqa.selenium.support.PageFactory}
     *
     * @see FindBy
     * @see PageFactory
     * @see PageFactory#initElements(WebDriver, Object)
     */
    public BasePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    /**
     * Функция позволяющая скролить до любого элемента с помощью js
     *
     * @param element - веб-элемент странички
     * @see JavascriptExecutor
     */
    protected void scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Явное ожидание состояния кликабельности элемента
     *
     * @param element - веб-элемент который требует проверки на кликабельность
     * @return WebElement - возвращаем тот же веб элемент что был передан в функцию
     * @see WebDriverWait
     * @see org.openqa.selenium.support.ui.FluentWait
     * @see org.openqa.selenium.support.ui.Wait
     * @see ExpectedConditions
     */
    protected WebElement elementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Явное ожидание состояния видимости элемента
     *
     * @param element - веб-элемент который требует проверки на видимость
     * @return WebElement - возвращаем тот же веб элемент что был передан в функцию
     * @see WebDriverWait
     * @see org.openqa.selenium.support.ui.FluentWait
     * @see org.openqa.selenium.support.ui.Wait
     * @see ExpectedConditions
     */
    public WebElement elementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Общий метод по заполнения полей ввода
     *
     * @param field - веб-елемент поле ввода
     * @param value - значение вводимое в поле
     */
    public void fillInputField(WebElement field, int value) {
        scrollToElementJs(field);
        while (!field.getAttribute("value").isEmpty()) {
            field.sendKeys(Keys.BACK_SPACE);
        }
        field.sendKeys("" + value);
    }



}

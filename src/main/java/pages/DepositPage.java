package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static utils.AdditionalFunctions.*;

public class DepositPage extends BasePage {

    @FindBy(xpath = "//h1")
    WebElement depositTitleElement;

    @FindBy(xpath = "//span[text()='Рубли']")
    WebElement rubElement;

    @FindBy(xpath = "//span[text()='Доллары США']")
    WebElement usdElement;

    @FindBy(xpath = "//label[text()='Сумма вклада']/..//input")
    WebElement depositSumElement;

    @FindBy(xpath = "//label[text()='Ежемесячное пополнение']/..//input")
    WebElement monthlyInstalmentElement;

    @FindBy(xpath = "//select[@name = 'period']")
    WebElement periodElement;

    @FindBy(xpath = "//label[text() = 'На срок']/..//div[@class = 'jq-selectbox__select-text']")
    WebElement checkPeriodElement;

    @FindBy(xpath = "//span[text() = 'Ежемесячная капитализация']/../..//div[@class = 'jq-checkbox calculator__check']")
    WebElement monthlyCapCheckBox;

    @FindBy(xpath = "//span[text() = 'Частичное снятие']/../..//div[@class = 'jq-checkbox calculator__check']")
    WebElement partlyWithdrawalCheckBox;

    @FindBy(xpath = "//span[text() = 'Ежемесячная капитализация']/../..//div[contains(@class, 'checked')]")
    List <WebElement> monthlyCapCheck;

    @FindBy(xpath = "//span[text() = 'Частичное снятие']/../..//div[contains(@class, 'checked')]")
    List <WebElement> partlyWithdrawalCheck;

    @FindBy(xpath = "//span[@class = 'js-calc-earned']")
    WebElement earnedElement;

    @FindBy(xpath = "//span[@class = 'js-calc-replenish']")
    WebElement replenishElement;

    @FindBy(xpath = "//span[@class = 'js-calc-result']")
    WebElement resultSumElement;


    @Step("Проверить переход на страницу Вклады")
    public DepositPage checkPresentPage() {
        elementToBeVisible(depositTitleElement);
        assertEquals("Заголовок не соответствует обозначенному", "Вклады", depositTitleElement.getText());
        return this;
    }

    @Step("Выбрать валюту '{currency}'")
    public DepositPage chooseCurrency(String currency) {
        WebElement element = null;
        switch (currency) {
            case "Рубли":
                element = rubElement;
                break;
            case "Доллары США":
                element = usdElement;
                break;
            default:
                Assert.fail("Введенная валюта " + currency + " отсутствует");
        }
        elementToBeClickable(element).click();
        return this;
    }

    @Step("заполнение полей '{fieldName}' значениями '{value}'")
    public DepositPage fillTheFields(String fieldName, Integer value) {
        if (value < 0) {
            Assert.fail("Значение меньше нуля");
        }
        WebElement element = null;
        switch (fieldName) {
            case "Сумма вклада":
                fillInputField(depositSumElement, value);
                element = depositSumElement;
                break;
            case "Ежемесячное пополнение":
                fillInputField(monthlyInstalmentElement, value);
                element = monthlyInstalmentElement;
                break;
            default:
                Assert.fail("Поле с именем " + fieldName + " отсутствует");
        }
        Assert.assertEquals("Поле " + fieldName + "неправильно заполнено", value, atoiInteger(element.getAttribute("value")));
        return this;
    }

    @Step("Выбрать срок вклада")
    public DepositPage fillPeriod(String value) {
        Select select = new Select(periodElement);
        select.selectByValue("" + atoiInteger(value));
        Assert.assertEquals("Данные " + value + "не совпадают", value,
                checkPeriodElement.getText());
        return this;
    }

    @Step("Отметить значения полей которые нам нужны")
    public DepositPage fillCheckboxes(String fieldName) {
        List<WebElement> elements = null;
        switch (fieldName) {
            case "Ежемесячная капитализация":
                monthlyCapCheckBox.click();
                elements = monthlyCapCheck;
                break;
            case "Частичное снятие":
                partlyWithdrawalCheckBox.click();
                elements = partlyWithdrawalCheck;
                break;
            default:
                Assert.fail("Поле с именем " + fieldName + " отсутствует");
        }
        Assert.assertFalse(fieldName + " не отмечено", elements.isEmpty());
        return this;
    }

    @Step("Проверить значение полей на ожидаемый результат")
    public DepositPage checkTheFieldsWithExpectedResults(String checkField, double value) {
        sleepByInterval(500);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.valueOf(monthlyCapCheckBox))));

        if (checkField.isEmpty() || value < 0) {
            Assert.fail("Выберете поле для проверки / значение должно быть больше 0");
        }
        WebElement element = null;
        switch (checkField) {
            case "Начислено %":
                element = earnedElement;
                break;
            case "Пополнение":
                element = replenishElement;
                break;
            case "К снятию":
                element = resultSumElement;
                break;
            default:
                Assert.fail(checkField + " не найдено!");
        }
        Assert.assertEquals(value, atoiDouble(element.getText()), 9e-10);
        return this;
    }

}

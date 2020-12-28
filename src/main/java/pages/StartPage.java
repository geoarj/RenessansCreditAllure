package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends BasePage {
    @FindBy(xpath = "//div/div[text()='Вклады']/../a[@href='/contributions/']")
    WebElement depositElement;

    /**
     * Нажать на подменю вклады
     *
     * @return DepositPage - т.е. переходим на страницу {@link DepositPage}
     */
    @Step("Выбрать меню вклады")
    public DepositPage choosePage(){
        elementToBeClickable(depositElement).click();
        return appManager.getDepositPage();
    }
}

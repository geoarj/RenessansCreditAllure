package tests;

import basetestclass.BaseTests;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Test;

public class AllureTest extends BaseTests {
    @Epic("Тест Ренесcанc Вклады")
    @Feature(value = "Тест вклада в рублях")
    @Test
    public void depositRenCredit1() {
        app.getStartPage()
                .choosePage()
                .checkPresentPage()
                .chooseCurrency("Рубли")
                .fillTheFields("Сумма вклада", 300_000)
                .fillPeriod("6 месяцев")
                .fillTheFields("Ежемесячное пополнение", 50_000)
                .fillCheckboxes("Ежемесячная капитализация")
//                .fillCheckboxes("Частичное снятие")
                .checkTheFieldsWithExpectedResults("Начислено %", 9132.17)
                .checkTheFieldsWithExpectedResults("Пополнение", 250_000)
                .checkTheFieldsWithExpectedResults("К снятию", 559_132.17)
        ;
    }

    @Epic("Тест Ренесcанc Вклады")
    @Feature(value = "Тест вклада в долларах")
    @Test
    public void depositRenCredit2() {
        app.getStartPage()
                .choosePage()
                .checkPresentPage()
                .chooseCurrency("Доллары США")
                .fillTheFields("Сумма вклада", 500_000)
                .fillPeriod("12 месяцев")
                .fillTheFields("Ежемесячное пополнение", 30_000)
                .fillCheckboxes("Ежемесячная капитализация")
//                .fillCheckboxes("Частичное снятие")
                .checkTheFieldsWithExpectedResults("Начислено %", 1_003.59)
                .checkTheFieldsWithExpectedResults("Пополнение", 330_000)
                .checkTheFieldsWithExpectedResults("К снятию", 831_003.59)
        ;
    }
}

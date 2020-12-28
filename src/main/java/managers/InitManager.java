package managers;

import utils.PropConst;

import java.util.concurrent.TimeUnit;

public class  InitManager {

    public static TestPropManager props = TestPropManager.getTestPropManager();

    /**
     * Инициализация фреймворка и запуск браузера со страницей приложения
     * @see DriverManager#getDriver()
     * @see TestPropManager#getProperty(String)
     * @see PropConst
     */
    public static void initFramework() {
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(PropConst.IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        DriverManager.getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty(PropConst.PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
        DriverManager.getDriver().get(props.getProperty(PropConst.APP_URL));
    }

    /**
     * Завершения работы фреймворка - гасит драйвер и закрывает сессию с браузером
     * @see DriverManager#quitDriver()
     */
    public static void quitFramework() {
        DriverManager.quitDriver();
    }
}

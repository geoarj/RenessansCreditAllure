package basetestclass;

import managers.InitManager;
import managers.PageManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import static managers.DriverManager.getDriver;
import static managers.InitManager.props;
import static utils.PropConst.APP_URL;

public class BaseTests {

    /**
     * Менеджер страниц
     * @see PageManager#getPageManager()
     */
    protected PageManager app = PageManager.getPageManager();

    @BeforeClass
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @Before
    public void beforeEach(){
        getDriver().get(props.getProperty(APP_URL));
    }

//    @AfterClass
//    public static void afterAll() {
//        InitManager.quitFramework();
//    }
}

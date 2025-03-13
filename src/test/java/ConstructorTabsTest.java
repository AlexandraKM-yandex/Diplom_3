import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import pages.ConstructorPage;

public class ConstructorTabsTest extends BaseUITest {

    @Test
    @DisplayName("Вкладка с булочками становится активной при клике")
    @Description("Проверка, что при клике на вкладку с булочками она становится активной на странице конструктора.")
    public void testBunsTabIsActiveAfterClick() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.openConstructorPage();

        constructorPage.clickBunsTab();
        Assert.assertTrue("Вкладка с булочками не стала активной", constructorPage.checkIfBunsTabIsCurrent());
    }

    @Test
    @DisplayName("Вкладка с соусами становится активной при клике")
    @Description("Проверка, что при клике на вкладку с соусами она становится активной на странице конструктора.")
    public void testSaucesTabIsActiveAfterClick() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.openConstructorPage();

        constructorPage.clickSaucesTab();
        Assert.assertTrue("Вкладка с соусами не стала активной", constructorPage.checkIfSaucesTabIsCurrent());
    }

    @Test
    @DisplayName("Вкладка с начинками становится активной при клике")
    @Description("Проверка, что при клике на вкладку с начинками она становится активной на странице конструктора.")
    public void testFillingsTabIsActiveAfterClick() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.openConstructorPage();

        constructorPage.clickFillingsTab();
        Assert.assertTrue("Вкладка с начинками не стала активной", constructorPage.checkIfFillingsTabIsCurrent());
    }
}

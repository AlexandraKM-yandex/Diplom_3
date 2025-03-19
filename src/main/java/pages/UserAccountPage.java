package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static base.Constants.BASE_URL;

public class UserAccountPage extends BasePage {

    public static final String userAccountPageUrl = BASE_URL + "account/profile";
    private final By logoutButton = By.xpath("//button[text()='Выход']");

    public UserAccountPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Выйти из аккаунта")
    public void clickLogoutButton() {
        clickElementButton(logoutButton);
    }
}

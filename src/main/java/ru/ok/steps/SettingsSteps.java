package ru.ok.steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.ok.data.Account;
import ru.ok.helpers.LangHelper;
import ru.ok.pages.SettingsPage;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Шаги для работы с настройками пользователя
 * Created by Alexey Dybov on 27.10.16.
 */
public class SettingsSteps extends Steps {

    private SettingsPage settingsPage;
    private LangHelper langHelper;

    public SettingsSteps(WebDriver driver) {
        super(driver);
        settingsPage = PageFactory.initElements(driver, SettingsPage.class);
        langHelper = new LangHelper(this.getClass());
    }

    /**
     * Открытие страницы
     */
    public void openSettingsPage() {
        openPageUnchecked();
        settingPageShouldBeOpened();
    }

    /**
     * Открытие страницы
     */
    public void openPageUnchecked(){
        settingsPage.open();
    }

    /**
     * Проверка открытия страницы
     */
    public void settingPageShouldBeOpened(){
        Assert.assertTrue("Не открылась страница настроек", settingsPage.isPageOpened());
    }

    /**
     * Клик на блок Личные данные
     */
    public void clickUserData() {
        settingsPage.clickUserDataBlock();
    }

    /**
     * Проверка данных пользователя на странице настроек
     * @param account
     */
    public void userDataShouldBeSameAs(Account account) {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("d MMMM YYYY", new Locale("ru"));
        String date = dateTimeFormat.format(account.getBirthDate().getTime());

        String expectedText = langHelper.getMessage("userData", account.getFirstName(), account.getLastName(),
            account.getGender(), date, account.getCityOfBirth().getShortName(), account.getCityOfResidence().getShortName());

        Assert.assertEquals("Не совпадают личные данные пользователя на странице настроек",
            expectedText, settingsPage.getUserData());
    }
}

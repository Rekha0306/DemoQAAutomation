package com.demoqa.pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demoqa.utils.CommonUtils;

public class PageObjectManager {

    private final WebDriver webDriver;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private BookStorePage bookStorePage;
    private BookDetailsPage bookDetailsPage;
    private WebDriverWait wait;
    

    public PageObjectManager(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait =  new WebDriverWait(webDriver, Duration.ofSeconds(CommonUtils.EXPLICIT_WAIT_BASIC_TIME));
    }


    public LoginPage getLoginPage() {
        return (loginPage == null) ? loginPage = new LoginPage(webDriver,wait) : loginPage;
    }

    public ProfilePage getProfilePage() {
        return (profilePage == null) ? profilePage = new ProfilePage(webDriver,wait) : profilePage;
    }
    
    public BookStorePage getBookStorePage() {
        return (bookStorePage == null) ? bookStorePage = new BookStorePage(webDriver,wait) : bookStorePage;
    }
    
    public BookDetailsPage getBookDetailsPage() {
        return (bookDetailsPage == null) ? bookDetailsPage = new BookDetailsPage(webDriver,wait) : bookDetailsPage;
    }
}

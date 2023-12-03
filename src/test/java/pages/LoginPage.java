package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    // Constructor to initialize the WebDriver and PageFactory
    public LoginPage(WebDriver driver){
      this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "username")
    private WebElement usernameTxtBox;


    @FindBy(id = "password")
    private WebElement passwordTxtBox;

    @FindBy(id = "remember-me")
    private WebElement remember_meCheckBox;

    @FindBy(xpath = "//button")
    private WebElement submitButton;

    @FindBy(xpath = "//a[contains(text(),'Sign Up Here')]")
    private WebElement signUpHereLink;

    // create action methods

    public void login(String username,String password){
        usernameTxtBox.sendKeys(username);
        passwordTxtBox.sendKeys(password);
        submitButton.click();
    }

}

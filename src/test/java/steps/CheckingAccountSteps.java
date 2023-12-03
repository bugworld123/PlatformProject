package steps;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import models.AccountCard;
import models.BankTransaction;
import models.NewCheckingAccountInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.LoginPage;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckingAccountSteps {
    WebDriver driver=new FirefoxDriver();
    private LoginPage loginPage=new LoginPage(driver);

    @BeforeAll
    public static void setup(){
        WebDriverManager.firefoxdriver().setup();
    }


    @Before
    public void the_user_is_on_dbank_homepage() {
        driver.get("https://dbank-qa.wedevx.co/bank/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }
    @Given("the user logged in as {string} {string}")
    public void the_user_logged_in_as(String username, String password) {
     loginPage.login(username,password);

    }


    @When("the user creates a new checking account with following data")
    public void the_user_creates_a_new_checking_account_with_following_data(List<NewCheckingAccountInfo> checkingAccountInfoList) {
        NewCheckingAccountInfo testDataForOneCheckingAccount=checkingAccountInfoList.get(0);
        // user clicks on checking button
        WebElement checkingMenu=driver.findElement(By.id("checking-menu"));
        checkingMenu.click();

        // the user clicks on the new checking button
        WebElement newCheckingButton=driver.findElement(By.id("new-checking-menu-item"));
        newCheckingButton.click();
        String expectedUrl="https://dbank-qa.wedevx.co/bank/account/checking-add";

        assertEquals(expectedUrl, driver.getCurrentUrl(),"new Checking Button didn't take the url");

        // the user selects a accountType
        WebElement accountTypeRadioButton=driver.findElement(By.id(testDataForOneCheckingAccount.getCheckingAccountType()));
        accountTypeRadioButton.click();

        // the user selects ownership
        WebElement ownerShipType=driver.findElement(By.id(testDataForOneCheckingAccount.getAccountOwnership()));
        ownerShipType.click();

        // the user names the account
        WebElement accountNameTxt=driver.findElement(By.id("name"));
        accountNameTxt.sendKeys(testDataForOneCheckingAccount.getAccountName());

         // the user makes the initial deposit
        WebElement openingBalanceTxtBox=driver.findElement(By.id("openingBalance"));
        openingBalanceTxtBox.sendKeys(String.valueOf(testDataForOneCheckingAccount.getInitialDepositAmount()));


        //the user clicks on the   submit button
        WebElement submitButton=driver.findElement(By.id("newCheckingSubmit"));
        submitButton.click();
    }

    @Then("the user should see green {string} message")
    public void the_user_should_see_green_message(String expectedConfMessage) {
      WebElement newAccountConfirmation= driver.findElement(By.id("new-account-conf-alert"));
        System.out.println(newAccountConfirmation.getText());
         String actualConfMessage=newAccountConfirmation.getText();
        System.out.println(actualConfMessage.substring(0,actualConfMessage.indexOf("\n")));

        expectedConfMessage="Confirmation "+expectedConfMessage+"\n×";
        //assertEquals(expectedConfMessage,newAccountConfirmation.getText().substring(0,newAccountConfirmation.getText().indexOf("\n")));
        assertEquals(expectedConfMessage,newAccountConfirmation.getText());



    }
    @Then("the user should see newly added account cart")
    public void the_user_should_see_newly_added_account_cart(List<AccountCard> accountCardList) {


        List<WebElement> allfirstRowDivs= driver.findElements(By.xpath("//div[@id='firstRow']/div"));
        WebElement lastAccountCard=allfirstRowDivs.get(allfirstRowDivs.size()-1);
        String actualResult=lastAccountCard.getText();
        String actualAccountName=actualResult.substring(0,actualResult.indexOf("\n")).trim();
        String actualAccountType=actualResult.substring(actualResult.indexOf("Account"),actualResult.indexOf("Ownership")).trim();
        //String actualOwnership=actualResult.substring(actualResult.indexOf("Ownership:"),actualResult.indexOf(" Account Number:")).trim();
        String actualAccountNumber=actualResult.substring(actualResult.indexOf("Account Number:"),actualResult.indexOf("Interest Rate")).trim();
        String actualInterestRate=actualResult.substring(actualResult.indexOf("Interest Rate:"),actualResult.indexOf("Balance:")).trim();
        String actualBalance=actualResult.substring(actualResult.indexOf("Balance:")).trim();
        System.out.println("//////////////////////////////////");
        System.out.println(lastAccountCard.getText());

        AccountCard expectedResult=accountCardList.get(0);//|Elon Musk Second Checking| Standard Checking|Individual|486133114    |0.0%        |1000.00 |
        assertEquals(expectedResult.getAccountName(),actualAccountName);
        assertEquals("Account: "+expectedResult.getAccountType(),actualAccountType);
        //assertEquals("Ownership: "+expectedResult.getOwnership(),actualOwnership);
        assertEquals("Interest Rate: "+expectedResult.getInterestRate(),actualInterestRate);
        String expectedBalance=String.format("%.2f",expectedResult.getBalance());
        //assertEquals("Balance: $"+expectedBalance,actualBalance);
        assertEquals("Balance: $" + expectedBalance.replace(",", "."), actualBalance);





//        Elon Musk Second Checking
//        Account: Standard Checking
//        Ownership: Individual
//        Account Number: 486133131
//        Interest Rate: 0.0%
//
//        Balance: $1000.00



    }
    @Then("the user should see the following transactions")
    public void the_user_should_see_the_following_transactions(List<BankTransaction> expectedTransactions) {
         WebElement firstRowOfTransactions=driver.findElement(By.xpath("//table[@id='transactionTable']/tbody/tr"));
         // //table[@id='transactionTable']/tbody/tr/td
        List<WebElement> firstRowColumns = firstRowOfTransactions.findElements(By.xpath("./td"));

        String actualCategory=firstRowColumns.get(1).getText();
        String actualDescription=firstRowColumns.get(2).getText();
        double actualAmount=Double.parseDouble(firstRowColumns.get(3).getText().substring(1));//not include $
        double actualBalance=Double.parseDouble(firstRowColumns.get(4).getText().substring(1));

        BankTransaction expectedTransaction=expectedTransactions.get(0);//  |2023-11-27 16:25 |Income  |845324180 (DPT) - Deposit|1000.00 |$1000.00|
        assertEquals(expectedTransaction.getCategory(),actualCategory,"transaction category mismatch");
        //assertEquals(expectedTransaction.getDescription(),actualDescription,"transaction description mismatch");
        assertEquals(expectedTransaction.getAmount(),actualAmount,"transaction amount mismatch");
        assertEquals(expectedTransaction.getBalance(),actualBalance,"transaction balance mismatch");
        System.out.println();
        System.out.println();


    }

}

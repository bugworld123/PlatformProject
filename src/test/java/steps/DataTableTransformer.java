package steps;

import io.cucumber.java.DataTableType;
import models.AccountCard;
import models.BankTransaction;
import models.NewCheckingAccountInfo;

import java.util.Map;

public class DataTableTransformer {


@DataTableType
    public AccountCard accountCardEntry(Map<String,String> entry){
     String accountName= entry.get("accountName");
     String  accountType= entry.get("accountType");
     String  ownership= entry.get("ownership");
     long accountNumber=Long.valueOf(entry.get("accountNumber"));
     String interestRate= entry.get("interestRate");
     double balance=Double.parseDouble(entry.get("balance"));
     return new AccountCard(accountName,accountType,ownership,accountNumber,interestRate,balance);


}

@DataTableType
 public BankTransaction transactionEntry(Map<String,String>entry){
 String date= entry.get("date");
 String category= entry.get("category");
 String  description= entry.get("description");
 double  amount=Double.parseDouble(entry.get("amount"));
 double  balance=Double.parseDouble(entry.get("balance"));

 return new BankTransaction(date,category,description,amount,balance);


}

@DataTableType
 public NewCheckingAccountInfo newCheckingAccountInfoEntry(Map<String,String>entry){
 String checkingAccountType = entry.get("checkingAccountType");
 String  ownership= entry.get("accountOwnership");
 String accountName=entry.get("accountName");
 double balance=Double.parseDouble(entry.get("initialDepositAmount"));

 return new NewCheckingAccountInfo(checkingAccountType,ownership,accountName,balance);
}
}

package models;

public class NewCheckingAccountInfo {
    private String checkingAccountType;
    private String accountOwnership;

    private String accountName;
    private  double initialDepositAmount;

    public NewCheckingAccountInfo(String checkingAccountType, String accountOwnership, String accountName, double initialDepositAmount) {
        this.checkingAccountType = checkingAccountType;
        this.accountOwnership = accountOwnership;
        this.accountName = accountName;
        this.initialDepositAmount = initialDepositAmount;
    }

    public String getCheckingAccountType() {
        return checkingAccountType;
    }

    public void setCheckingAccountType(String checkingAccountType) {
        this.checkingAccountType = checkingAccountType;
    }

    public String getAccountOwnership() {
        return accountOwnership;
    }

    public void setAccountOwnership(String accountOwnership) {
        this.accountOwnership = accountOwnership;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getInitialDepositAmount() {
        return initialDepositAmount;
    }

    public void setInitialDepositAmount(double initialDepositAmount) {
        this.initialDepositAmount = initialDepositAmount;
    }
}

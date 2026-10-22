package accountsServices;

import accounts.*;
import person.AccountOwner;

import java.util.ArrayList;
import java.util.List;

public class BankAccountService implements IBankAccountService {
    BusinessAccountFactory businessAccountFactory = new BusinessAccountFactory();
    CurrentAccountFactory currentAccountFactory = new CurrentAccountFactory();
    SavingAccountFactory savingAccountFactory = new SavingAccountFactory();
    StudentAccountFactory studentAccountFactory = new StudentAccountFactory();

    private List<BankAccount> bankAccounts = new ArrayList<>();

    @Override
    public BankAccount createCurrentBankAccount(AccountOwner accountOwner, double balance) {
        BankAccount currentAccount = currentAccountFactory.createCurrentAccount(accountOwner, balance);
        this.bankAccounts.add(currentAccount);

        return currentAccount;
    }

    @Override
    public BankAccount createSavingBankAccount(AccountOwner accountOwner, double balance) {
        BankAccount savingAccount = savingAccountFactory.createSavingAccount(accountOwner, balance);
        this.bankAccounts.add(savingAccount);

        return savingAccount;
    }

    @Override
    public BankAccount createStudentBankAccount(AccountOwner accountOwner, String school, double balance) {
        BankAccount studentAccount = studentAccountFactory.createStudentAccount(accountOwner, school, balance);
        this.bankAccounts.add(studentAccount);

        return studentAccount;
    }

    @Override
    public BankAccount createBusinessBankAccount(AccountOwner accountOwner, double balance) {
        BankAccount businessAccount = businessAccountFactory.createBusinessAccount(accountOwner, balance);
        this.bankAccounts.add(businessAccount);

        return businessAccount;
    }

    @Override
    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

}

package accountsServices.decorators;

import accounts.BankAccount;
import accountsServices.IBankAccountService;
import person.AccountOwner;

import java.time.LocalTime;
import java.util.List;

import java.time.LocalDateTime;

public class BankAccountWithLoggerWithTimeService implements IBankAccountService {
    IBankAccountService bankAccountService;

    public BankAccountWithLoggerWithTimeService(IBankAccountService bankAccountService)
    {
        this.bankAccountService = bankAccountService;
    }

    @Override
    public BankAccount createCurrentBankAccount(AccountOwner accountOwner, double balance){
        logStart();
        BankAccount bankAccount = this.bankAccountService.createCurrentBankAccount(accountOwner, balance);
        logEnd();

        return bankAccount;
    }

    @Override
    public BankAccount createSavingBankAccount(AccountOwner accountOwner, double balance){
        logStart();
        BankAccount bankAccount = this.bankAccountService.createSavingBankAccount(accountOwner, balance);
        logEnd();

        return bankAccount;
    }

    @Override
    public BankAccount createBusinessBankAccount(AccountOwner accountOwner, double balance){
        logStart();
        BankAccount bankAccount = this.bankAccountService.createBusinessBankAccount(accountOwner, balance);
        logEnd();

        return bankAccount;
    }



    @Override
    public BankAccount createStudentBankAccount(AccountOwner accountOwner, String school, double balance){
        logStart();
        BankAccount bankAccount = this.bankAccountService.createStudentBankAccount(accountOwner, school, balance);
        logEnd();

        return bankAccount;
    }

    @Override
    public List<BankAccount> getBankAccounts() {
        return List.of();
    }

    public void logStart(){
        IO.println("Vytvářím účet..., " + LocalTime.now());
    }
    public void logEnd(){
        IO.println("Vytvořil jsem účet..., " + LocalTime.now());
    }
}

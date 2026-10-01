package accounts;

import notifier.ConsoleNotifierService;
import notifier.NotifierService;
import person.AccountOwner;

import java.util.UUID;

public abstract class BankAccount {

    private String uuid;

    private AccountOwner accountOwner;

    private String accountNumber;

    private double balance;

    private NotifierService notifierService = new ConsoleNotifierService();

    public BankAccount(String uuid, AccountOwner accountOwner, String accountNumber) {
        this.uuid = uuid;
        this.accountOwner = accountOwner;
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    public BankAccount(String uuid, AccountOwner accountOwner, String accountNumber, double balance) {
        this(uuid, accountOwner, accountNumber);

        this.balance = balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

}

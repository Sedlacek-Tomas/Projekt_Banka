package accounts;

import person.AccountOwner;

import java.util.UUID;

public abstract class BankAccount {

    private String uuid;

    private AccountOwner accountOwner;

    private String accountNumber;

    private double balance;

    public BankAccount(AccountOwner accountOwner, String accountNumber) {
        this.uuid = UUID.randomUUID().toString();
        this.accountOwner = accountOwner;
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    public BankAccount(AccountOwner accountOwner, String accountNumber, double balance) {
        this(accountOwner, accountNumber);

        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void add(double amount){

        double newBalance = balance+amount;
        if(amount < 0){
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        this.balance += amount;
    }

    public void sub(double amount) {
        double newBalance = balance - amount;

        if(amount < 0){
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        this.balance -= amount;
    }

}

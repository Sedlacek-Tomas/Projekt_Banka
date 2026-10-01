package accounts;

import person.AccountOwner;

public class SavingAccount extends BankAccount implements InterestPoint {
    public SavingAccount(String uuid, AccountOwner accountOwner, String accountNumber) {
        super(uuid, accountOwner, accountNumber);
    }

    private static final float INTEREST_RATE = 0.05f;

    public SavingAccount(String uuid, AccountOwner accountOwner, String accountNumber, double balance) {
        super(uuid, accountOwner, accountNumber, balance);
    }



    @Override
    public void calculateInterest() {
        double interest = getBalance() * INTEREST_RATE;
        double newBalance = getBalance() + interest;

        super.setBalance(newBalance);
    }
}

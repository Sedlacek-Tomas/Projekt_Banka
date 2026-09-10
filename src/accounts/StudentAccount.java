package accounts;

import person.AccountOwner;

public class StudentAccount extends BankAccount{

    private String schoolName;
    public StudentAccount(AccountOwner accountOwner, String accountNumber, String schoolName) {
        this(accountOwner, accountNumber, 0, schoolName);
    }

    public StudentAccount(AccountOwner accountOwner, String accountNumber, double balance, String schoolName) {
        super(accountOwner, accountNumber, balance);

        this.schoolName = schoolName;

    }

    @Override
    public void add(double amount) {

        double bonusAmount = amount * 0.05;

        super.add(bonusAmount);
        super.add(amount);
    }

    @Override
    public void sub(double amount) {

        if(getBalance()-amount<-5000)
        {
            throw new IllegalArgumentException("Zůstatek nesmí být menší než 5000 Kč");
        }

        super.sub(amount);
    }

    public String getSchoolName() {
        return schoolName;
    }
}

import accounts.*;
import person.AccountOwner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


public static void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    AccountOwner accountOwner = new AccountOwner("Tomas", "Sedlacek");

    accountOwner.setLastName("Pokorny");

    BankAccount bankAccount = new CurrentAccount(accountOwner, "123", 500);
    BankAccount studentAccount = new StudentAccount(accountOwner, "124", 500, "DELTA");
    BankAccount savingAccount = new SavingAccount(accountOwner, "125", 500);
    BankAccount businessAccount = new BusinessAccount(accountOwner, "126", 500);

    List<BankAccount> bankAccounts = new ArrayList<>();
    bankAccounts.add(bankAccount);
    bankAccounts.add(studentAccount);
    bankAccounts.add(savingAccount);
    bankAccounts.add(businessAccount);

    for (BankAccount account: bankAccounts){
        if(account instanceof  StudentAccount) {
            StudentAccount stdAccount = (StudentAccount) account;
            IO.println("school: " + stdAccount.getSchoolName());
        }
    }

    //studentAccount.sub(5501);
    studentAccount.sub(5500);
    printBalance(studentAccount);

    savingAccount.add(100);
    printBalance(savingAccount);

    businessAccount.sub(100);
    printBalance(businessAccount);



}

private static void printBalance(BankAccount bankAccount){
    IO.println(bankAccount.getBalance());
}

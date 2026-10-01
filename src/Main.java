import accounts.*;
import notifier.ConsoleNotifierService;
import person.AccountOwner;
import person.AccountOwnerFactory;
import transfer.DepositTransferService;
import transfer.TransferTransferService;
import transfer.WithdrawTransferService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    AccountOwnerFactory accountOwnerFactory = new AccountOwnerFactory();

    AccountOwner accountOwner = accountOwnerFactory.createAccountOwner("Tomas", "Sedlacek");

    accountOwner.setLastName("Pokorny");

    BusinessAccountFactory businessAccountFactory = new BusinessAccountFactory();
    CurrentAccountFactory currentAccountFactory = new CurrentAccountFactory();
    SavingAccountFactory savingAccountFactory = new SavingAccountFactory();
    StudentAccountFactory studentAccountFactory = new StudentAccountFactory();

    BankAccount bankAccount = currentAccountFactory.createCurrentAccount(accountOwner, 500);
    BankAccount studentAccount = studentAccountFactory.createStudentAccount(accountOwner, "DELTA", 500);
    BankAccount savingAccount = savingAccountFactory.createSavingAccount(accountOwner, 500);
    BankAccount businessAccount = businessAccountFactory.createBusinessAccount(accountOwner, 500);

    List<BankAccount> bankAccounts = new ArrayList<>();
    bankAccounts.add(bankAccount);
    bankAccounts.add(studentAccount);
    bankAccounts.add(savingAccount);
    bankAccounts.add(businessAccount);

    for (BankAccount account: bankAccounts){
        if(account instanceof InterestPoint) {
            ((InterestPoint)account).calculateInterest();
        }
    }

    for (BankAccount account: bankAccounts){
        if(account instanceof  StudentAccount) {
            StudentAccount stdAccount = (StudentAccount) account;
            IO.println("school: " + stdAccount.getSchoolName());
        }
    }

    ConsoleNotifierService NotifierService = new ConsoleNotifierService();
    TransferTransferService TransferService = new TransferTransferService();

    NotifierService.notify("Simulace převodů.");

    NotifierService.notify("Stav účtů před převody.");
    NotifierService.notify("studentAccount.");
    printBalance(studentAccount);
    NotifierService.notify("businessAccount.");
    printBalance(businessAccount);

    TransferService.transfer(studentAccount, businessAccount, 500);

    NotifierService.notify("Stav účtů po převodech.");

    NotifierService.notify("studentAccount.");
    printBalance(studentAccount);
    NotifierService.notify("businessAccount.");
    printBalance(businessAccount);

    TransferService.transfer(businessAccount, studentAccount, 500);

    NotifierService.notify("Stav účtů po převodech.");

    NotifierService.notify("studentAccount.");
    printBalance(studentAccount);
    NotifierService.notify("businessAccount.");
    printBalance(businessAccount);

}

private static void printBalance(BankAccount bankAccount){
    IO.println(bankAccount.getBalance());
}

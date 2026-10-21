import accounts.*;
import accounts.serialization.*;
import notifier.ConsoleNotifierService;
import notifier.NotifierService;
import person.AccountOwner;
import person.AccountOwnerFactory;
import transfer.TransferLoggerService;
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
    TransferLoggerService transferLoggerService = new TransferLoggerService();

    DepositTransferService depositTransferService = new DepositTransferService(transferLoggerService);
    WithdrawTransferService withdrawTransferService = new WithdrawTransferService(transferLoggerService);
    TransferTransferService transferTransferService = new TransferTransferService(transferLoggerService);

    NotifierService.notify("Simulace převodů.");

    NotifierService.notify("Stav účtů před převody.");
    NotifierService.notify("studentAccount.");
    printBalance(studentAccount);
    NotifierService.notify("businessAccount.");
    printBalance(businessAccount);

    transferTransferService.transfer(studentAccount, businessAccount, 500);

    NotifierService.notify("Stav účtů po převodech.");

    NotifierService.notify("studentAccount.");
    printBalance(studentAccount);
    NotifierService.notify("businessAccount.");
    printBalance(businessAccount);

    transferTransferService.transfer(businessAccount, studentAccount, 500);

    NotifierService.notify("Stav účtů po převodech.");

    NotifierService.notify("studentAccount.");
    printBalance(studentAccount);
    NotifierService.notify("businessAccount.");
    printBalance(businessAccount);

    NotifierService.notify("Vložení a vybrání z účtů.");
    withdrawTransferService.withdraw(businessAccount, 100);
    depositTransferService.deposit(studentAccount, 167);

    NotifierService.notify("Výpis historie");
    transferLoggerService.printAllTransactions();

    NotifierService.notify("XmlSerializace");
    BankAccountSerializer bankAccountJacksonSerializer = new BankAccountJacksonSerializer();
    String bankAccountXml = bankAccountJacksonSerializer.serialize(bankAccount);
    String bankAccountXmlAll = bankAccountJacksonSerializer.serializeAll(bankAccounts);

    IO.println(bankAccountXml);
    IO.println(bankAccountXmlAll);

    NotifierService.notify("JSONSerializace");
    BankAccountSerializer bankAccountJSONSerializer = new BankAccountGsonSerializer();
    String bankAccountJSON = bankAccountJSONSerializer.serialize(bankAccount);
    String bankAccountJSONlAll = bankAccountJSONSerializer.serializeAll(bankAccounts);

    IO.println(bankAccountJSON);
    IO.println(bankAccountJSONlAll);

}

private static void printBalance(BankAccount bankAccount){
    IO.println(bankAccount.getBalance());
}

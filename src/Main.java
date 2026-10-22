import accounts.*;
import accounts.serialization.*;
import accountsServices.BankAccountService;
import accountsServices.IBankAccountService;
import accountsServices.decorators.BankAccountWithLoggerService;
import accountsServices.decorators.BankAccountWithLoggerWithTimeService;
import notifier.ConsoleNotifierService;
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

    IBankAccountService bankAccountService = new BankAccountService();
    IBankAccountService bankAccountWithLoggerService = new BankAccountWithLoggerService(bankAccountService);
    IBankAccountService bankAccountWithLoggerWithTimeService = new BankAccountWithLoggerWithTimeService(bankAccountService);
    IBankAccountService bankAccountWithLoggerAndTimeLoggerService = new BankAccountWithLoggerService(bankAccountWithLoggerWithTimeService);

    ConsoleNotifierService NotifierService = new ConsoleNotifierService();
    TransferLoggerService transferLoggerService = new TransferLoggerService();

    DepositTransferService depositTransferService = new DepositTransferService(transferLoggerService);
    WithdrawTransferService withdrawTransferService = new WithdrawTransferService(transferLoggerService);
    TransferTransferService transferTransferService = new TransferTransferService(transferLoggerService);

    NotifierService.notify("ORIGINAL");
    BankAccount bankAccount = bankAccountService.createCurrentBankAccount(accountOwner, 500);
    NotifierService.notify("======");
    NotifierService.notify("LOGGER");
    BankAccount studentAccount = bankAccountWithLoggerService.createStudentBankAccount(accountOwner, "DELTA", 500);
    NotifierService.notify("======");
    NotifierService.notify("TIME");
    BankAccount savingAccount = bankAccountWithLoggerWithTimeService.createSavingBankAccount(accountOwner, 500);
    NotifierService.notify("======");
    NotifierService.notify("ALL");
    BankAccount businessAccount = bankAccountWithLoggerAndTimeLoggerService.createBusinessBankAccount(accountOwner, 500);
    NotifierService.notify("======");


    for (BankAccount account: bankAccountService.getBankAccounts()){
        if(account instanceof InterestPoint) {
            ((InterestPoint)account).calculateInterest();
        }
    }

    for (BankAccount account: bankAccountService.getBankAccounts()){
        if(account instanceof  StudentAccount) {
            StudentAccount stdAccount = (StudentAccount) account;
            IO.println("school: " + stdAccount.getSchoolName());
        }
    }

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
    String bankAccountXmlAll = bankAccountJacksonSerializer.serializeAll(bankAccountService.getBankAccounts());

    IO.println(bankAccountXml);
    IO.println(bankAccountXmlAll);

    NotifierService.notify("JSONSerializace");
    BankAccountSerializer bankAccountJSONSerializer = new BankAccountGsonSerializer();
    String bankAccountJSON = bankAccountJSONSerializer.serialize(bankAccount);
    String bankAccountJSONlAll = bankAccountJSONSerializer.serializeAll(bankAccountService.getBankAccounts());

    IO.println(bankAccountJSON);
    IO.println(bankAccountJSONlAll);

}

private static void printBalance(BankAccount bankAccount){
    IO.println(bankAccount.getBalance());
}

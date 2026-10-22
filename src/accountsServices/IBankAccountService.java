package accountsServices;

import accounts.BankAccount;
import person.AccountOwner;

import java.util.List;

public interface IBankAccountService {
    BankAccount createCurrentBankAccount(AccountOwner accountOwner, double balance);

    BankAccount createSavingBankAccount(AccountOwner accountOwner, double balance);

    BankAccount createStudentBankAccount(AccountOwner accountOwner, String school, double balance);

    BankAccount createBusinessBankAccount(AccountOwner accountOwner, double balance);

    List<BankAccount> getBankAccounts();
}

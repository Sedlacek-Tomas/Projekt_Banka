package accounts;

import accountsServices.AccountNumberGeneratorService;
import person.AccountOwner;

import java.util.UUID;

public class SavingAccountFactory {

    public SavingAccount createSavingAccount (AccountOwner accountOwner, double balance)
    {
        AccountNumberGeneratorService accountNumberGeneratorService = new AccountNumberGeneratorService();

        String uuid = UUID.randomUUID().toString();
        String accountNumber = accountNumberGeneratorService.generate();

        return new SavingAccount(uuid, accountOwner, accountNumber, balance);
    }
}

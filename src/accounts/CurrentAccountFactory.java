package accounts;

import accountsServices.AccountNumberGeneratorService;
import person.AccountOwner;

import java.util.UUID;

public class CurrentAccountFactory {

    public CurrentAccount createCurrentAccount(AccountOwner accountOwner, double balance)
    {
        AccountNumberGeneratorService accountNumberGeneratorService = new AccountNumberGeneratorService();

        String uuid = UUID.randomUUID().toString();
        String accountNumber = accountNumberGeneratorService.generate();

        return new CurrentAccount(uuid, accountOwner, accountNumber, balance);
    }
}

package accounts;

import accountsServices.AccountNumberGeneratorService;
import person.AccountOwner;

import java.util.UUID;

public class BusinessAccountFactory {

    public BusinessAccount createBusinessAccount(AccountOwner accountOwner, double balance)
    {
        AccountNumberGeneratorService accountNumberGeneratorService = new AccountNumberGeneratorService();

        String uuid = UUID.randomUUID().toString();
        String accountNumber = accountNumberGeneratorService.generate();

        return new BusinessAccount(uuid, accountOwner, accountNumber, balance);
    }

}

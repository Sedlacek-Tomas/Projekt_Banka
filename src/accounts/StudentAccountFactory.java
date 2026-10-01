package accounts;

import accountsServices.AccountNumberGeneratorService;
import person.AccountOwner;

import java.util.UUID;

public class StudentAccountFactory {

    public StudentAccount createStudentAccount(AccountOwner accountOwner,String schoolName, double balance)
    {
        AccountNumberGeneratorService accountNumberGeneratorService = new AccountNumberGeneratorService();

        String uuid = UUID.randomUUID().toString();
        String accountNumber = accountNumberGeneratorService.generate();


        return new StudentAccount(uuid, accountOwner, accountNumber, balance, schoolName);
    }
}

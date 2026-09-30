package transfer;

import accounts.BankAccount;
import accounts.BusinessAccount;
import accounts.StudentAccount;

public class TransferTransferService {

    private static final double BUSINESS_ACCOUNT_SERVICE_FEE = 0.03;
    public void transfer(BankAccount sourceAccount, BankAccount targetAccount, double amount)
    {
        if(sourceAccount == targetAccount)
            {throw new IllegalArgumentException("Source account and target account cannot be same.");}
        if(sourceAccount == null)
            {throw new IllegalArgumentException("Source account cannot be null");}
        if(targetAccount == null)
            {throw new IllegalArgumentException("Target account cannot be null");}
        if(amount < 0)
            {throw new IllegalArgumentException("Ammount cannot be lower or equal to 0.");}

            double newBalanceSourceAccount = sourceAccount.getBalance() - amount;
            double newBalanceTargetAccount = targetAccount.getBalance() + amount;

            if(sourceAccount instanceof BusinessAccount)
            {
                double transferFee = newBalanceSourceAccount*BUSINESS_ACCOUNT_SERVICE_FEE;
                newBalanceTargetAccount-=transferFee;
            }

        if(newBalanceSourceAccount < GetWithDrawLimit(sourceAccount))
        {
            throw new IllegalArgumentException("Cannot transfer negative amount");
        }

        sourceAccount.setBalance(newBalanceSourceAccount);
        targetAccount.setBalance(newBalanceTargetAccount);

    }
    private int GetWithDrawLimit(BankAccount account)
    {
        if (account instanceof StudentAccount) {
            return -5000;
        }
        return 0;
    }
}

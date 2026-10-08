package transfer;

import accounts.BankAccount;
import accounts.BusinessAccount;
import accounts.StudentAccount;
import transaction.Transaction;
import transaction.TransactionFactory;
import transaction.TransactionStatus;
import transaction.TransactionType;

public class TransferTransferService {

    private static final double BUSINESS_ACCOUNT_SERVICE_FEE = 0.03;
    private final TransferLoggerService transferLoggerService;
    private final TransactionFactory transactionFactory = new TransactionFactory();

    public TransferTransferService(TransferLoggerService transferLoggerService) {
        this.transferLoggerService = transferLoggerService;
    }
    public void transfer(BankAccount sourceAccount, BankAccount targetAccount, double amount)
    {
        if(sourceAccount == targetAccount) {
                logTransaction(sourceAccount, targetAccount, amount, TransactionStatus.FAILED);
                throw new IllegalArgumentException("Source account and target account cannot be same.");}
        if(sourceAccount == null) {
            logTransaction(sourceAccount, targetAccount, amount, TransactionStatus.FAILED);
            throw new IllegalArgumentException("Source account cannot be null");}
        if(targetAccount == null) {
            logTransaction(sourceAccount, targetAccount, amount, TransactionStatus.FAILED);
            throw new IllegalArgumentException("Target account cannot be null");}
        if(amount < 0) {
            logTransaction(sourceAccount, targetAccount, amount, TransactionStatus.FAILED);
            throw new IllegalArgumentException("Ammount cannot be lower or equal to 0.");}

            double newBalanceSourceAccount = sourceAccount.getBalance() - amount;
            double newBalanceTargetAccount = targetAccount.getBalance() + amount;

            if(sourceAccount instanceof BusinessAccount)
            {
                double transferFee = newBalanceSourceAccount*BUSINESS_ACCOUNT_SERVICE_FEE;
                newBalanceTargetAccount-=transferFee;
            }
        GetWithdrawLimitService getWithdrawLimitService = new GetWithdrawLimitService();
        if(newBalanceSourceAccount < getWithdrawLimitService.getLimit(sourceAccount))
        {
            logTransaction(sourceAccount, targetAccount, amount, TransactionStatus.FAILED);
            throw new IllegalArgumentException("Source account exceeds it's limit.");
        }

        sourceAccount.setBalance(newBalanceSourceAccount);
        targetAccount.setBalance(newBalanceTargetAccount);

        logTransaction(sourceAccount, targetAccount, amount, TransactionStatus.SUCCESS);

    }

    private void logTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount, TransactionStatus status){
        String sourceAccountNumber = sourceAccount != null ? sourceAccount.getAccountNumber() : null;
        String targetAccountNumber = targetAccount != null ? targetAccount.getAccountNumber() : null;

        transferLoggerService.log(transactionFactory.createTransaction(sourceAccountNumber, targetAccountNumber, amount, TransactionType.TRANSFER, status));
    }

}

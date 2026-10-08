package transfer;

import accounts.BankAccount;
import accounts.BusinessAccount;
import accounts.StudentAccount;
import transaction.Transaction;
import transaction.TransactionFactory;
import transaction.TransactionStatus;
import transaction.TransactionType;

public class WithdrawTransferService {

    private static final double BUSINESS_ACCOUNT_SERVICE_FEE = 0.01;
    private final TransferLoggerService transferLoggerService;
    private final TransactionFactory transactionFactory = new TransactionFactory();

    public WithdrawTransferService(TransferLoggerService transferLoggerService) {
        this.transferLoggerService = transferLoggerService;
    }


    public void withdraw(BankAccount account, double amount) {
        double newBalance = account.getBalance() - amount;

        if(account instanceof BusinessAccount) {
            double serviceFee = amount * BUSINESS_ACCOUNT_SERVICE_FEE;

            newBalance -= serviceFee;
        }

        GetWithdrawLimitService getWithdrawLimitService = new GetWithdrawLimitService();
        if(newBalance < getWithdrawLimitService.getLimit(account))
        {
            transferLoggerService.log(transactionFactory.createTransaction(account.getAccountNumber(), null, amount, TransactionType.WITHDRAW, TransactionStatus.FAILED));
            throw new IllegalArgumentException("Cannot subtract negative amount");
        }

        account.setBalance(newBalance);

        transferLoggerService.log(transactionFactory.createTransaction(account.getAccountNumber(), null, amount, TransactionType.WITHDRAW, TransactionStatus.SUCCESS));
    }

}

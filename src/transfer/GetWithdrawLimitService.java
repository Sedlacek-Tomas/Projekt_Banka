package transfer;

import accounts.BankAccount;
import accounts.StudentAccount;

public class GetWithdrawLimitService {
    private static final int STUDENT_ACCOUNT_LIMIT = 5000;
    public int getLimit (BankAccount account)
    {
        if (account instanceof StudentAccount) {
            return -STUDENT_ACCOUNT_LIMIT;
        }
        return 0;
    }
}

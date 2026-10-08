package transfer;

import transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransferLoggerService {

    private final List<Transaction> transactions = new ArrayList<>();

    public void log(Transaction transaction) {
        if (transaction != null) {
            transactions.add(transaction);
        }
    }


    //Všechny transakce
    public void printAllTransactions() {
        System.out.println("=== Všechny transakce ===");
        if (transactions.isEmpty()) {
            System.out.println("Zatím neproběhla žádná transakce.");
            return;
        }

        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    //Transakce podle čísla účtu
    public void printTransactionsByAccount(String accountNumber) {
        System.out.println("=== Transakce pro účet: " + accountNumber + " ===");
        boolean found = false;

        for (Transaction t : transactions) {
            boolean isSource = accountNumber != null && accountNumber.equals(t.getSourceAccountNumber());
            boolean isTarget = accountNumber != null && accountNumber.equals(t.getTargetAccountNumber());

            if (isSource || isTarget) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Pro účet " + accountNumber + " nebyly nalezeny žádné transakce.");
        }
    }
}
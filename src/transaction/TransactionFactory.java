package transaction;

import java.util.UUID;
import java.time.LocalDateTime;

public class TransactionFactory {
    public Transaction createTransaction(String sourceAccountNumber, String targetAccountNumber, Double amount, TransactionType type, TransactionStatus status){
        String uuid = UUID.randomUUID().toString();
        LocalDateTime timestamp = LocalDateTime.now();
        return new Transaction(uuid, timestamp, sourceAccountNumber, targetAccountNumber, amount, type, status);
    }
}

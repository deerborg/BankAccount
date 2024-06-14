package art.deerborg.bank.bank.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FinanceHelper {
    public static String generateIban(String bankCode){
        List accounts = new ArrayList();
        StringBuilder iban = new StringBuilder();
        iban.append("TR").append(bankCode);
        while (true){
            Long accountNumber = ThreadLocalRandom.current().nextLong(10000000000000000L,99999999999999999L);
            if(accounts.contains(accountNumber)){
                continue;
            }
            iban.append(accountNumber);
            accounts.add(accountNumber);
            break;
        }
        return iban.toString();
    }
}

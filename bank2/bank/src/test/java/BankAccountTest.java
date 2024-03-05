package bank;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BankAccountTest {
    @Test
    @DisplayName("El metodo withdraw debe retornar false si el monto a retirar es mayor al saldo")
    public void withdraw_amountGreaterThanBalance_true(){
        BankAccount account = new BankAccount(100);
        assertTrue(account.withdraw(50));
    }

}
package bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BankAccountTest {
    @Test
    @DisplayName("El metodo withdraw debe retornar false si el monto a retirar es mayor al saldo")
    public void withdraw_amountGreaterThanBalance_returnFalse(){
        BankAccount account = new BankAccount(100);
        assertFalse(account.withdraw(120));
    }

    @Test
    @DisplayName("El metodo withdraw debe retornar true si el monto a retirar es menor al saldo")
    public void withdraw_amountLowerThanBalance_returnTrue(){
        BankAccount account = new BankAccount(100);
        assertTrue(account.withdraw(50));
    }

    @Test
    @DisplayName("El metodo deposit debe tener un amount positivo")
    public void deposit_amountNegative_throwException() {
        BankAccount account = new BankAccount(100);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-1));
    }

    @Test
    @DisplayName("El metodo deposit debe de retornar el saldo actualizado")
    public void deposit_amountPositive_returnTrue() {
        BankAccount account = new BankAccount(100);
        int balanceOld = account.getBalance();
        account.deposit(10);
        assertEquals(account.getBalance(), balanceOld+10);
    }

    @Test
    @DisplayName("El metodo payment debe calcular correctamente el pago")
    public void payment_amountInterestNpaymentsPositive_returnTrue(){
        BankAccount account = new BankAccount(100);
        int amount = 15;
        int npayments = 10;
        double interest = 0.07;
        assertEquals(account.payment(amount, interest, npayments), amount*(interest*Math.pow((1+interest), npayments)/(Math.pow((1+interest), npayments)-1)));
    
    }

    @Test
    @DisplayName("El metodo pending si es el primer mes debe de ser el total")
    public void pending_monthEqualsZeroSameAmount_returnTrue() {
        BankAccount account = new BankAccount(100);
        int amount = 10;
        int npayments = 5;
        double interest = 0.07;
        int month = 0;
        assertEquals(account.pending(amount, interest, npayments, month), amount);
    }

    @Test
    @DisplayName("El metodo pending si es mayor que el primer mes debe de ser el total menos el pago")
    public void pending_monthGreaterThanZeroLowerPending_returnTrue() {
        BankAccount account = new BankAccount(100);
        int amount = 50;
        int npayments = 5;
        double interest = 0.07;
        int month = 1;
        double expected = amount - (amount*(interest*Math.pow((1+interest), npayments)/(Math.pow((1+interest), npayments)-1)) - interest*amount);
        assertEquals(expected, account.pending(amount, interest, npayments, month));
    }
}
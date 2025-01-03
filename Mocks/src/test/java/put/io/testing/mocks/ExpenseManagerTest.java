package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.service.FancyService;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

public class ExpenseManagerTest {
    @Test
    void testCalculateTotal() {
        ExpenseRepository mock = mock(ExpenseRepository.class);
        FancyService fancyService = mock(FancyService.class);
        ArrayList<Expense> expenses = new ArrayList<Expense>();
        for (int i = 0; i < 3; i++){
            Expense exp = new Expense();
            exp.setAmount(50);
            expenses.add(exp);
        }
        when(mock.getExpenses()).thenReturn(expenses);
        ExpenseManager expenseManager = new ExpenseManager(mock,fancyService);
        assertEquals(150, expenseManager.calculateTotal());
    }

    @Test
    public void calculateTotalForCategory() {
        ExpenseRepository mock = mock(ExpenseRepository.class);
        FancyService fancyService = mock(FancyService.class);
        ArrayList<Expense> car = new ArrayList<Expense>();
        ArrayList<Expense> home = new ArrayList<Expense>();
        for(int i = 0; i < 3; i++){
            Expense expense1 = new Expense();
            expense1.setCategory("Home");
            expense1.setAmount(10000);
            Expense expense2 = new Expense();
            expense2.setCategory("Car");
            expense2.setAmount(5000);
            home.add(expense1);
            car.add(expense2);
        }
        when(mock.getExpensesByCategory(anyString())).thenReturn(Collections.emptyList());
        when(mock.getExpensesByCategory(eq("Car"))).thenReturn(car);
        when(mock.getExpensesByCategory(eq("Home"))).thenReturn(home);
        ExpenseManager expenseManager = new ExpenseManager(mock, fancyService);
        assertEquals(15000, expenseManager.calculateTotalForCategory("Car"));
        assertEquals(30000, expenseManager.calculateTotalForCategory("Home"));
        assertEquals(0, expenseManager.calculateTotalForCategory("Sport"));
    }

    @Test
    public void testCalculateTotalInDollars() throws ConnectException {
        ExpenseRepository mockRepo = mock(ExpenseRepository.class);
        FancyService mockS = mock(FancyService.class);
        ArrayList<Expense> expenses= new ArrayList<Expense>();

        when(mockRepo.getExpenses()).thenReturn(expenses);
        //when(mockS.convert(anyDouble(),eq("PLN"),eq("USD"))).thenThrow(new ConnectException());
        when(mockS.convert(anyDouble(), eq("PLN"), eq("USD"))).thenAnswer(invocation -> {
            double amountInPLN = (double) invocation.getArguments()[0];
            return amountInPLN * 0.25;
        });

        for (int i = 0; i < 5; i++) {
            Expense expense = new Expense();
            expense.setAmount(20);
            expenses.add(expense);
        }
        ExpenseManager expenseManager = new ExpenseManager(mockRepo, mockS);
        expenseManager.calculateTotalInDollars();
        assertEquals(25, expenseManager.calculateTotalInDollars(), 0.001);
    }
}
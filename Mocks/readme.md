# Paweł Kolec 155873

## 2.1
Aby sprawdzić poprawność interakcji metody loadExpenses z obiektem zastępczym bazy danych, można użyć biblioteki Mockito. Najpierw należy utworzyć zastępczy obiekt bazy danych i skonfigurować jego działanie, definiując dopuszczalne wywołania metod i wartości, które mają zostać zwrócone. Po uruchomieniu metody loadExpenses można wykorzystać narzędzie InOrder z Mockito, aby zweryfikować, czy metody connect, queryAll i close zostały wywołane w prawidłowej kolejności.

## 5.1
W przypadku Mockito kolejność oczekiwań dla tej samej metody z różnymi argumentami ma znaczenie. Mockito weryfikuje wywołania metod dokładnie w takiej kolejności, w jakiej zostały określone oczekiwania. Dlatego kluczowe jest zachowanie prawidłowej kolejności deklaracji, aby uniknąć potencjalnych błędów i zapewnić poprawne działanie testów. Zmiana kolejności oczekiwań może prowadzić do nieoczekiwanych wyników.

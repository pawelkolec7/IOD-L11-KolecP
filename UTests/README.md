Paweł Kolec 155873
3.1
Tak, testy przestałyby działać. Zastąpienie adnotacji @BeforeEach adnotacją @BeforeAll sprawiłoby, 
że metoda setUp uruchamiałaby się tylko raz, przed rozpoczęciem wszystkich testów, zamiast przed każdym z osobna. 
To oznacza, że obiekt calculator byłby inicjalizowany jednorazowo, co mogłoby wpłynąć na poprawność testów.
4.1
Metoda test1 zakończy się wynikiem typu failure, natomiast metoda test2 zakończy się wynikiem typu error.
4.2
JUnit oczekuje, że rzucanym wyjątkiem w przypadku niepowodzenia asercji będzie obiekt klasy AssertionFailedError.
5.1
Ten test należy do kategorii testów whitebox.
5.2
W metodzie calculate można wyróżnić 4 różne ścieżki wykonania kodu.
customer.isSubscriber() == true 
customer.getLoyaltyLevel() == SILVER 
customer.getLoyaltyLevel() == GOLD 
customer.getLoyaltyLevel() == STANDARD

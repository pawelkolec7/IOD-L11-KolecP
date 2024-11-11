# System aukcyjny

## Wprowadzenie

Specyfikacja wymagań funkcjonalnych w ramach informatyzacji procesu sprzedaży produktów w oparciu o mechanizm aukcyjny. 

## Procesy biznesowe

---
<a id="bc1"></a>
### BC1: Sprzedaż aukcyjna 

**Aktorzy:** [Sprzedający](#ac1), [Kupujący](#ac2)

**Opis:** Proces biznesowy opisujący sprzedaż za pomocą mechanizmu aukcyjnego.

**Scenariusz główny:**
1. [Sprzedający](#ac1) wystawia produkt na aukcję. ([UC1](#uc1))
2. [Kupujący](#ac2) oferuje kwotę za produkt wyższą od aktualnie najwyższej oferty. ([UC2](#uc2), [BR1](#br1))
3. [Kupujący](#ac2) wygrywa aukcję ([BR2](#br2))
4. [Kupujący](#ac2) przekazuje należność Sprzedającemu. ([UC3](#uc3))
5. [Sprzedający](#ac1) przekazuje produkt Kupującemu. ([UC4](#uc4))

**Scenariusze alternatywne:** 
- 2.A. Oferta Kupującego została przebita, a [Kupujący](#ac2) pragnie przebić aktualnie najwyższą ofertę.
  * 2.A.1. Przejdź do kroku 2.
- 3.A. Czas aukcji upłynął i [Kupujący](#ac2) przegrał aukcję. ([BR2](#br2))
  * 3.A.1. Koniec przypadku użycia.

---

## Aktorzy

<a id="ac1"></a>
### AC1: Sprzedający

Osoba oferująca towar na aukcji.

<a id="ac2"></a>
### AC2: Kupujący

Osoba chcąca zakupić produkt na aukcji.

## Przypadki użycia poziomu użytkownika

### Aktorzy i ich cele

[Sprzedający](#ac1):
* [UC1](#uc1): Wystawienie produktu na aukcję
* [UC4](#uc4): Przekazanie produktu Kupującemu

[Kupujący](#ac2):
* [UC2](#uc2): Złożenie oferty
* [UC3](#uc3): Przekazanie należności Sprzedającemu

---
<a id="uc1"></a>
### UC1: Wystawienie produktu na aukcję

**Aktorzy:** [Sprzedający](#ac1)

**Scenariusz główny:**
1. [Sprzedający](#ac1) zgłasza do systemu chęć wystawienia produktu na aukcję.
2. System prosi o podanie danych produktu i ceny wywoławczej.
3. [Sprzedający](#ac1) podaje dane produktu oraz cenę wywoławczą.
4. System weryfikuje poprawność danych.
5. System informuje o pomyślnym wystawieniu produktu na aukcję.

**Scenariusze alternatywne:** 
- 4.A. Podano niepoprawne lub niekompletne dane produktu.
  * 4.A.1. System informuje o błędnie podanych danych.
  * 4.A.2. Przejdź do kroku 2.

---

<a id="uc2"></a>
### UC2: Złożenie oferty

**Aktorzy:** [Kupujący](#ac2)

**Scenariusz główny:**
1. [Kupujący](#ac2) zgłasza do systemu chęć złożenia oferty na aukcji.
2. System wyświetla aktualnie najwyższą ofertę.
3. [Kupujący](#ac2) wprowadza kwotę oferty wyższą niż aktualnie najwyższa oferta.
4. System weryfikuje kwotę oferty zgodnie z [BR1](#br1).
5. System akceptuje ofertę i aktualizuje najwyższą ofertę.

**Scenariusze alternatywne:**
- 4.A. Oferta nie spełnia wymogu minimalnej wartości (BR1).
  * 4.A.1. System informuje o błędnej kwocie oferty.
  * 4.A.2. Przejdź do kroku 3.

---

<a id="uc3"></a>
### UC3: Przekazanie należności

**Aktorzy:** [Kupujący](#ac2)

**Scenariusz główny:**
1. [Kupujący](#ac2) wybiera opcję zapłaty po wygranej aukcji.
2. System generuje dane do płatności.
3. [Kupujący](#ac2) dokonuje płatności.
4. System weryfikuje płatność.
5. System informuje Sprzedającego o otrzymaniu należności.

**Scenariusze alternatywne:**
- 4.A. Płatność nie została zweryfikowana.
  * 4.A.1. System informuje o błędzie płatności.
  * 4.A.2. Przejdź do kroku 2.

---

<a id="uc4"></a>
### UC4: Przekazanie produktu

**Aktorzy:** [Sprzedający](#ac1)

**Scenariusz główny:**
1. [Sprzedający](#ac1) otrzymuje powiadomienie o zakończonej płatności.
2. [Sprzedający](#ac1) przygotowuje produkt do wysyłki.
3. [Sprzedający](#ac1) przekazuje produkt Kupującemu.
4. System kończy proces przekazania produktu.

---

## Obiekty biznesowe

### BO1: Aukcja

Aukcja jest formą zawierania transakcji kupna-sprzedaży, w której Sprzedający określa cenę wywoławczą produktu, natomiast Kupujący mogą oferować własną ofertę zakupu każdorazowo proponując kwotę wyższą od aktualnie oferowanej kwoty. Aukcja kończy się po upływie określonego czasu. Jeśli złożona została co najmniej jedna oferta zakupu, produkt nabywa ten Kupujący, który zaproponował najwyższą kwotę. 

### BO2: Produkt

Fizyczny lub cyfrowy obiekt, który ma zostać sprzedany w ramach aukcji.

## Reguły biznesowe

<a id="br1"></a>
### BR1: Złożenie oferty

Złożenie oferty wymaga zaproponowania kwoty wyższej niż aktualnie oferowana o minimum 1,00 PLN.

<a id="br2"></a>
### BR2: Rozstrzygnięcie aukcji

Aukcję wygrywa ten z [Kupujących](#ac2), który w momencie jej zakończenia (upłynięcia czasu) złożył najwyższą ofertę.

## Macierz CRUDL

| Przypadek użycia                                  | Aukcja | Produkt | Kupujący | Sprzedający |
| ------------------------------------------------- | ------ | ------- | -------- | ----------- |
| UC1: Wystawienie produktu na aukcję               |    C   |    C    |    -     |      R      |
| UC2: Złożenie oferty                              |    U   |    R    |    C     |      -      |
| UC3: Przekazanie należności                       |    R   |    R    |    U     |      R      |
| UC4: Przekazanie produktu                         |    R   |    U    |    R     |      U      |

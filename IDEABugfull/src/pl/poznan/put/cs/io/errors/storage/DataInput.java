package pl.poznan.put.cs.io.errors.storage;

import java.io.*;

/**
 * Klasa DataInput przechowuje dane wejściowe w postaci macierzy (matrix) o zadanym rozmiarze,
 * gdzie pierwszy wiersz wejścia określa wielkość tej macierzy.
 */
public class DataInput {

    private String inputName;

    private int size;

    private int[][] matrix;

    public DataInput(String inputName) {
        this.inputName = inputName;
    }

    private void initializeMatrix() {
        matrix = new int[size][size];
    }

    /**
     * Loads data from the given input. The first line is the size of the matrix.
     * Paweł Kolec
     * Zmieniłem pętlę do wypełniania macierzy tak, że teraz iteruje po wierszach i kolumnach co zapewnia prawidłowe wypełnienie macierzy
     * Uzupełniam brakujące elementy zerami co zapobiega błędom przy niekompletnych danych.
     *
     * unkcja load() czyta dane wiersz po wierszu i wypełnia macierz wartościami.
     * Jeśli dane są niekompletne (np. brak wartości w wierszu), brakujące elementy macierzy są wypełniane zerami.
     * @throws Exception
     */
    public void load() throws Exception {
        BufferedReader input = getBufferedReader();
        try {
            // Read the size of the matrix
            String line = input.readLine();
            if (line != null) {
                size = Integer.parseInt(line.trim());
                initializeMatrix();
            }

            // Read the matrix data
            for (int x = 0; x < size; x++) {
                line = input.readLine();
                if (line == null || line.length() == 0) {
                    throw new IOException("Insufficient data for matrix rows.");
                }
                for (int y = 0; y < size; y++) {
                    if (y < line.length()) {
                        matrix[x][y] = Character.getNumericValue(line.charAt(y));
                    } else {
                        matrix[x][y] = 0;
                    }
                }
            }
        } catch (NumberFormatException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    private BufferedReader getBufferedReader() throws FileNotFoundException {
        if (inputName.equalsIgnoreCase("console")) {
            return new BufferedReader(new InputStreamReader(System.in));
        }
        return new BufferedReader(new FileReader(new File(inputName)));
    }

    /**
     * kod zwraca macierz za pomocą metody getMatrix()
     * @return
     */
    public int[][] getMatrix() {
        return matrix;
    }
}

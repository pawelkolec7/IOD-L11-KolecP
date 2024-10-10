package pl.poznan.put.cs.io.errors;

import java.util.List;

import pl.poznan.put.cs.io.errors.processors.BFSProcessor;
import pl.poznan.put.cs.io.errors.storage.DataInput;
import pl.poznan.put.cs.io.errors.storage.DataOutput;

/**
 *  Paweł Kolec
 * W klasie Controller wczytywane są dane wejściowe (z pliku lub konsoli), przetwarzane przy użyciu algorytmu BFS, a wynik jest następnie zapisywany w wybranym wyjściu.
 *  Jeśli wynik przetwarzania jest pusty lub null, wyświetlany jest komunikat o błędzie.
 * W nowym kodzie dodano sprawdzenie, czy wynik przetwarzania (result) nie jest pusty ani null.
 * Jeśli wynik jest pusty lub null, wyświetlany jest komunikat.
 */

public class Controller {

    private DataInput input;
    private DataOutput output;
    BFSProcessor bFSProcessor = new BFSProcessor();

    public Controller(String inputName, String outputName) {
        input = new DataInput(inputName);
        output = new DataOutput(outputName);
    }

    public static void main(String[] args) {
        String inputName = null;
        String outputName = "console";
        if (args.length == 0) {
            System.err
                    .println("Run with parameters: input <output>"
                            + " where input/output are names of files or console for standard in/out"
                            + "\nOperation - first line of the input (+,-,*,/,sort,mean,primals)");
            System.exit(-1);
        } else {
            inputName = args[0];
            // second parameter is optional
            if (args.length > 1) {
                outputName = args[1];
            }
        }
        final Controller controler = new Controller(inputName, outputName);
        controler.run();
    }

    public void run() {
        try {
            input.load();
        } catch (Exception e) {
            System.err.println("Wrong input (see trace)!");
            e.printStackTrace();
            return;
        }

        List<Integer> result = bFSProcessor.process(input.getMatrix());

        System.out.println("Result: " + result);

        if (result != null && !result.isEmpty()) {
            try {
                output.save(result);
            } catch (Exception e) {
                System.err.println("Wrong output (see trace)!");
                e.printStackTrace();
            }
        } else {
            System.err.println("Wynik przetwarzania jest pusty.");
        }
    }
}

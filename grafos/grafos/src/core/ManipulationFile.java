/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 *
 * @author 20191BSI0077
 */
public class ManipulationFile {

    private final java.io.File file = new java.io.File("data.txt");

    public void generateFork(int order) throws IOException {
        Random random = new Random();
        String line = "";
        int value = 0, qtd_vert = 0;
        
        boolean statusFile;

        file.delete();

        statusFile = file.createNewFile();

        if (statusFile) {
            FileWriter fileWriter = new FileWriter(file, false);

            try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
                for (int i = 1; i <= order; i++) {
                    line += "V" + Integer.toString(i) + " ";
                }

                printWriter.print(line + "\n");

                for (int i = 0; i < order; i++) {
                    line = "";
                    for (int j = 0; j < order; j++) {
                        value = random.nextInt(10);
                        if (random.nextInt(100) > 80 && i != j && value > 0) {
                            line += Integer.toString(value) + " ";
                            qtd_vert++;
                        } else {
                            line += "0 ";
                        }
                    }
                    printWriter.print(line + "\n");
                }

                printWriter.flush();
                System.out.printf("Grafo gerado com %d vertices.\n", order);
                System.out.printf("Grafo gerado com %d arestas.\n", qtd_vert);
            }

        } else {
            System.out.println("Não foi possível criar o arquivo!");
        }
    }
}

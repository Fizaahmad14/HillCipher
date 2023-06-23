import java.util.*;
import java.io.*;

public class HillCipher {
    int[][] keyMatrix;
    char[] plainText;

    public static void main(String[] args) {
        String keyFile = args[0];
        String plaintextFile = args[1];
        HillCipher hc = new HillCipher();
        hc.readKeyFile(keyFile);
        hc.readPlaintextFile(plaintextFile);
        char[] cipherText = hc.hillCipherEncryption();
        hc.printResults(cipherText);
    }

    public void readKeyFile(String keyFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(keyFile));
            int n = Integer.parseInt(reader.readLine().trim()); // First line gives the size of the matrix
            keyMatrix = new int[n][n]; // initialize keyMatrix with size n x n

            // Read subsequent lines containing the matrix elements
            for(int i = 0; i < n; i++){
                String[] line = reader.readLine().trim().split("\\s+");
                for(int j = 0; j < n; j++){
                    keyMatrix[i][j] = Integer.parseInt(line[j]);
                }
            }
            reader.close();
        } catch(IOException e) {
            System.err.println("Error while reading key file: " + e.getMessage());
        } catch(NumberFormatException e) {
            System.err.println("Invalid number format in key file: " + e.getMessage());
        }
    }


    public void readPlaintextFile(String plaintextFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(plaintextFile));
            ArrayList<Character> charList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    if (Character.isLetter(c)) {
                        charList.add(Character.toLowerCase(c));
                    }
                }
            }
            reader.close();

            // Pad with 'x' if the number of characters is not a multiple of the matrix size
            while (charList.size() % keyMatrix.length != 0) {
                charList.add('x');
            }

            // Convert ArrayList to array
            plainText = new char[charList.size()];
            for (int i = 0; i < charList.size(); i++) {
                plainText[i] = charList.get(i);
            }
        } catch(IOException e) {
            System.err.println("Error while reading plaintext file: " + e.getMessage());
        }
    }


    public char[] hillCipherEncryption() {
        int n = keyMatrix.length;
        int blockSize = plainText.length / n;
        int[][] blocks = new int[blockSize][n];
        char[] cipherText = new char[plainText.length];

        // Convert plaintext characters to integers and organize into blocks
        for (int i = 0; i < plainText.length; i++) {
            blocks[i / n][i % n] = plainText[i] - 'a'; // subtract 'a' to get numbers in range 0-25
        }

        // Perform Hill cipher encryption
        for (int i = 0; i < blockSize; i++) {
            int[] encryptedBlock = modMatMul(keyMatrix, blocks[i]);
            for (int j = 0; j < n; j++) {
                cipherText[i * n + j] = (char) ('a' + encryptedBlock[j]); // add 'a' to get back to range 'a'-'z'
            }
        }

        return cipherText;
    }

    private int[] modMatMul(int[][] matrix, int[] vector) {
        int size = matrix.length;
        int[] result = new int[size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
            result[i] %= 26; // Perform modulo 26
            if (result[i] < 0) { // Ensure result is non-negative
                result[i] += 26;
            }
        }

        return result;
    }


    public void printResults(char[] cipherText) {
        // Print the key matrix
        System.out.println("Key matrix:");
        for (int[] row : keyMatrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        
        // Print the plaintext (including any padding)
        System.out.println("Plaintext:");
        System.out.println(new String(plainText));

        // Print the ciphertext
        System.out.println("Ciphertext:");
        System.out.println(new String(cipherText));
    }
}

# Hill Cipher Implementation in Java

This repository contains a Java program `hillCipher` which implements the Hill Cipher encryption algorithm.

## Description

The Hill Cipher is a polygraphic substitution cipher based on linear algebra. Each letter is represented by a number modulo 26. A block of letters is then converted into a vector, and multiplied by an invertible matrix, against modulo 26. This Java program reads in a plaintext file and a key matrix from specified files, and outputs the encrypted text.

## How to Use

The program requires two command line arguments, representing file paths to the key matrix and the plaintext file respectively.

Example:
```bash
keyFile.txt plaintextFile.txt
```

### Key file

The key file should begin with a single integer `n` on its own line, representing the size of the key matrix. This is followed by `n` lines, each containing `n` integers separated by spaces, which constitute the key matrix.

### Plaintext file

The plaintext file should contain the text to be encrypted. Non-alphabet characters are ignored and all letters are treated as lowercase.

### Output

The program prints the key matrix, plaintext, and ciphertext to standard output. Each output block of text is separated by headers ("Key matrix:", "Plaintext:", and "Ciphertext:") and wrapped to 80 characters per line.

## Methodology

The `hillCipher` class contains the following methods:

- `main(String[] args)`: Entry point of the program. Reads in the key file and plaintext file, performs the Hill Cipher encryption, and prints the results.
- `readKeyFile(String keyFile)`: Reads in the key matrix from the specified file.
- `readPlaintextFile(String plaintextFile)`: Reads in the plaintext from the specified file. Non-alphabet characters are ignored and all letters are treated as lowercase. The plaintext is padded with 'x' if its length is not a multiple of the key matrix size.
- `hillCipherEncryption()`: Performs the Hill Cipher encryption and returns the ciphertext.
- `modMatMul(int[][] matrix, int[] vector)`: Performs a modular matrix multiplication and returns the result.
- `printResults(char[] cipherText)`: Prints the key matrix, plaintext, and ciphertext to standard output.

## Requirements

- Java SE 11 or higher

## Notes

This program does not check whether the key matrix is invertible modulo 26, which is a requirement for the Hill Cipher. Make sure to use a suitable key matrix. If the key matrix is not invertible, the encrypted text may not be decrypted correctly.



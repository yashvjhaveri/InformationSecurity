import java.io.*;

public class MonoalphabeticCipher {
    private static final String PLAINTEXT_FILE = "plaintext.txt";
    private static final String ENCRYPT_FILE = "encrypt.txt";
    private static final String DECRYPT_FILE = "decrypt.txt";
    private static final int ALPHABET_LENGTH = 26;

    public static void main(String[] args) {
        String key = generateRandomKey();
        System.out.println("Randomly generated key: " + key);

        encrypt(key);
        System.out.println("Encryption completed.");

        decrypt(key);
        System.out.println("Decryption completed.");
    }

    private static String generateRandomKey() {
        // Generate a random permutation of lowercase alphabets
        StringBuilder keyBuilder = new StringBuilder();
        for (char c = 'a'; c <= 'z'; c++) {
            keyBuilder.append(c);
        }
        for (int i = 0; i < ALPHABET_LENGTH; i++) {
            int index1 = (int) (Math.random() * ALPHABET_LENGTH);
            int index2 = (int) (Math.random() * ALPHABET_LENGTH);
            char temp = keyBuilder.charAt(index1);
            keyBuilder.setCharAt(index1, keyBuilder.charAt(index2));
            keyBuilder.setCharAt(index2, temp);
        }
        return keyBuilder.toString();
    }

    private static void encrypt(String key) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PLAINTEXT_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(ENCRYPT_FILE))) {
            int ch;
            while ((ch = reader.read()) != -1) {
                if (Character.isLowerCase(ch)) {
                    int index = ch - 'a';
                    writer.write(key.charAt(index));
                } else {
                    writer.write(ch);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void decrypt(String key) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ENCRYPT_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(DECRYPT_FILE))) {
            int ch;
            while ((ch = reader.read()) != -1) {
                if (Character.isLowerCase(ch)) {
                    int index = key.indexOf(ch);
                    writer.write('a' + index);
                } else {
                    writer.write(ch);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
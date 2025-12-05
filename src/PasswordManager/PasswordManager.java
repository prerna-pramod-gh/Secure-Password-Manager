package PasswordManager;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.security.SecureRandom;


public class PasswordManager {
    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = "TheBestSecretKey".getBytes(); // 16 bytes for AES-128 (good enough for demo)
    private Map<String, String> passwordStore = new HashMap<>();
    private static final SecureRandom secureRandom = new SecureRandom();

    public static void main(String[] args) {
        PasswordManager manager = new PasswordManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Secure Password Manager - Amity University MCA Project");
        System.out.println("======================================================");

        while (true) {
            System.out.println("\n1. Add Password");
            System.out.println("2. Retrieve Password");
            System.out.println("3. List All Sites");
            System.out.println("4. Generate Strong Password");
            System.out.println("5. Exit");
            System.out.print("\nChoose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter site (e.g., gmail.com): ");
                    String site = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    manager.addPassword(site, password);
                    break;

                case 2:
                    System.out.print("Enter site: ");
                    site = scanner.nextLine();
                    String retrieved = manager.getPassword(site);
                    System.out.println("Password: " + retrieved);
                    break;

                case 3:
                    manager.listAllSites();
                    break;

                case 4:
                    System.out.print("Enter desired password length (8-20): ");
                    int len = scanner.nextInt();
                    if (len < 8 || len > 20) {
                        System.out.println("Please choose between 8 and 20!");
                        break;
                    }
                    String generated = manager.generateStrongPassword(len);
                    System.out.println("\nGenerated Password: " + generated);
                    manager.checkPasswordStrength(generated);
                    System.out.println("\nTip: Use option 1 to save it securely!");
                    break;

                case 5:
                    System.out.println("Thank you! Stay safe online.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Try 1–5.");
            }
        }
    }

    public void addPassword(String site, String password) {
        try {
            String encrypted = encrypt(password);
            passwordStore.put(site.toLowerCase(), encrypted);
            System.out.println("Password saved securely for " + site);
        } catch (Exception e) {
            System.out.println("Error saving password.");
        }
    }

    public String getPassword(String site) {
        try {
            String encrypted = passwordStore.get(site.toLowerCase());
            if (encrypted == null) return "No password found for " + site;
            return decrypt(encrypted);
        } catch (Exception e) {
            return "Error retrieving password.";
        }
    }

    public void listAllSites() {
        if (passwordStore.isEmpty()) {
            System.out.println("No passwords saved yet.");
        } else {
            System.out.println("Saved sites:");
            passwordStore.keySet().forEach(System.out::println);
        }
    }

    public String generateStrongPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*_-";
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < length; i++) {
            pass.append(chars.charAt(secureRandom.nextInt(chars.length())));
        }
        return pass.toString();
    }

    public void checkPasswordStrength(String password) {
        int strength = 0;
        if (password.length() >= 12) strength += 25;
        if (password.matches(".*[A-Z].*")) strength += 20;
        if (password.matches(".*[a-z].*")) strength += 20;
        if (password.matches(".*\\d.*")) strength += 20;
        if (password.matches(".*[!@#$%^&*_-].*")) strength += 15;

        String level = strength >= 80 ? "STRONG" : strength >= 60 ? "MEDIUM" : "WEAK";
        System.out.println("Password Strength: " + strength + "/100 → " + level);
    }

    // Encryption & Decryption methods (unchanged)
    private String encrypt(String data) throws Exception {
        SecretKeySpec key = new SecretKeySpec(keyValue, ALGORITHM);
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encVal);
    }

    private String decrypt(String encryptedData) throws Exception {
        SecretKeySpec key = new SecretKeySpec(keyValue, ALGORITHM);
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decodedValue);
        return new String(decValue);
    }
}


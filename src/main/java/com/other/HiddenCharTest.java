package com.other;

public class HiddenCharTest {



// ====== OPTION 1: RS (0x1E) — count trailing chars ======



    static final char SALT_CHAR = '\u001E'; // ASCII Record Separator

    // --- WRITE: embed salt into comment ---
    public static String embedSalt(String comment, int salt) {
        if (salt <= 0) return comment;
        StringBuilder sb = new StringBuilder(comment);
        for (int i = 0; i < salt; i++) {
            sb.append(SALT_CHAR);
        }
        return sb.toString();
    }

    // --- READ: extract salt from comment ---
    public static int extractSalt(String storedComment) {
        if (storedComment == null || storedComment.isEmpty()) return 0;
        int salt = 0;
        int i = storedComment.length() - 1;
        while (i >= 0 && storedComment.charAt(i) == SALT_CHAR) {
            salt++;
            i--;
        }
        return salt;
    }

    // --- DISPLAY: strip salt chars before showing to user ---
    public static String stripSalt(String storedComment) {
        if (storedComment == null) return null;
        int end = storedComment.length();
        while (end > 0 && storedComment.charAt(end - 1) == SALT_CHAR) {
            end--;
        }
        return storedComment.substring(0, end);
    }

    // --- TEST ---
    public static void main(String[] args) {
        String original = "Balance adjustment for TOR override";

        String salt0 = embedSalt(original, 0); // no duplicate
        String salt1 = embedSalt(original, 1); // first duplicate
        String salt2 = embedSalt(original, 2); // second duplicate

        System.out.println(salt2);

        System.out.println("Salt 0 length: " + salt0.length() + " → extract: " + extractSalt(salt0));
        System.out.println("Salt 1 length: " + salt1.length() + " → extract: " + extractSalt(salt1));
        System.out.println("Salt 2 length: " + salt2.length() + " → extract: " + extractSalt(salt2));

        System.out.println("Display (salt1): [" + stripSalt(salt1) + "]");
        System.out.println("Display (salt2): [" + stripSalt(salt2) + "]");

        // Write salt1 to DB — paste this string into your SQL INSERT test:
        System.out.println("\nBytes in salt1:");
        for (byte b : salt1.getBytes(java.nio.charset.Charset.forName("windows-1252"))) {
            System.out.printf("0x%02X ", b);
        }
    }
}

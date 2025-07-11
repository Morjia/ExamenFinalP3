package pe.com.transitsoft.config.db.util;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Cifrado {
    private static final String key = "neoFarma_grupo4";
    // Usamos AES porque es más seguro que DESede, DESede quedo obsoleto
    // Tambien SHA-256 en vez deMD5, porque MD5 es susceptible a colisiones, 
    
    public static String cifrarAES(String texto) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte[] keyBytes = Arrays.copyOf(sha.digest(key.getBytes("UTF-8")), 16); // AES-128 usa 16 bytes
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(texto.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String descifrarAES(String textoEncriptado) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte[] keyBytes = Arrays.copyOf(sha.digest(key.getBytes("UTF-8")), 16); // AES-128 usa 16 bytes
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.getDecoder().decode(textoEncriptado);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, "UTF-8");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

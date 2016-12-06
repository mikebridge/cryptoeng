import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Chapter3J {

    public static String decryptQuestion38(String hexCipherText, String hexSecretKey)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        byte[] secretKey = DatatypeConverter.parseHexBinary (hexSecretKey);
        byte[] cipherText = DatatypeConverter.parseHexBinary (hexCipherText);

        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey,"AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] plainText = cipher.doFinal(cipherText);
        return DatatypeConverter.printHexBinary(plainText);

    }
}

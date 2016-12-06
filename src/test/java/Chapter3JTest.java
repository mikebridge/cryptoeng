import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by bridge on 06/12/16.
 */
class Chapter3JTest {

    @org.junit.jupiter.api.Test
    void decryptQuestion38Java() throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        assertEquals(Chapter3J.decryptQuestion38(
                "539b333b39706d149028cfe1d9d4a407",
                "80000000000000000000000000000000" +
                        "00000000000000000000000000000001"),"80706050403020100807060504030201");
    }

}
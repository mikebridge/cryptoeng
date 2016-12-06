import com.bridgecanada.utils.HexConversions._
import javax.crypto.{Cipher, SecretKey}
import javax.crypto.spec.{IvParameterSpec, SecretKeySpec}

object Chapter3 {

  // Question 3.8
  def decrypt38(hexCipherText: String, hexKey: String): String = {
    val cipher = Cipher.getInstance("AES/ECB/NoPadding")
    val secretKey = new SecretKeySpec(hexKey.asBytes, "AES");
    cipher.init(Cipher.DECRYPT_MODE, secretKey)
    cipher.doFinal(hexCipherText.asBytes).asHexString
  }

  // Question 3.9
  def encrypt39(hexPlainText: String, hexKey: String): String = {
    val cipher = Cipher.getInstance("AES/ECB/NoPadding")
    val secretKey = new SecretKeySpec(hexKey.asBytes, "AES");
    cipher.init(Cipher.ENCRYPT_MODE, secretKey)
    cipher.doFinal(hexPlainText.asBytes).asHexString
  }

  // Question 3.10
  def encrypt310(hexPlainText: String, secretKey: SecretKey): String = {
    val cipher = Cipher.getInstance("DES/ECB/NoPadding")
    cipher.init(Cipher.ENCRYPT_MODE, secretKey)
    cipher.doFinal(hexPlainText.asBytes).asHexString
  }




  def secretKeySpec(key: String): SecretKeySpec =
    new SecretKeySpec(key.asBytes, "AES")
}


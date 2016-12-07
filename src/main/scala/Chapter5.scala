import javax.crypto.Cipher
import javax.crypto.spec.{IvParameterSpec, SecretKeySpec}

import com.bridgecanada.utils.HexConversions._

object Chapter5 {

  //
  def aesAsHash(hexCipherText: String, hexKey: String): String = {

    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    val secretKey = new SecretKeySpec(hexKey.asBytes, "AES")
    cipher.init(Cipher.ENCRYPT_MODE, secretKey)
    cipher.doFinal(hexCipherText.asBytes).asHexString

  }

  def MD5(s: String): String = {
    // Besides "MD5", "SHA-256", and other hashes are available
    val m = java.security.MessageDigest.getInstance("MD5").digest(s.getBytes("UTF-8"))
    m.map("%02x".format(_)).mkString
  }


}


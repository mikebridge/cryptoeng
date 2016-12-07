import javax.crypto.spec.{IvParameterSpec, SecretKeySpec}
import javax.crypto.{Cipher, SecretKey}

import com.bridgecanada.utils.HexConversions._

object Chapter4 {

  // Question 4.4
  def decrypt44(hexCipherText: String, hexKey: String): String = {
    val ivlength = hexKey.asBytes.length / 2
    val cipher = Cipher.getInstance("AES/CBC/NoPadding")
    val secretKey = new SecretKeySpec(hexKey.asBytes, "AES")
    val iv = getIV(hexCipherText,ivlength)
    val ivspec = new IvParameterSpec(iv)

    cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec)

    cipher.doFinal(hexCipherText.asBytes).drop(ivlength).asHexString.readable
  }

  // Question 4.5
  def encrypt45(hexPlainText: String, hexKey: String): String = {
    val cipher = Cipher.getInstance("AES/ECB/NoPadding")
    val secretKey = new SecretKeySpec(hexKey.asBytes, "AES")
    cipher.init(Cipher.ENCRYPT_MODE, secretKey)
    cipher.doFinal(hexPlainText.asBytes).asHexString
  }

  def getIV(cipherText:String, length: Int): Array[Byte] =
    cipherText.asBytes.take(length)

  def secretKeySpec(key: String): SecretKeySpec =
    new SecretKeySpec(key.asBytes, "AES")
}


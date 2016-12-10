import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.{IvParameterSpec, SecretKeySpec}

//import org.scalameter.api._

import com.bridgecanada.utils.HexConversions._

import scala.annotation.tailrec
import scala.collection.immutable.{HashMap, HashSet}

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
    m.asHexString
  }

  def SHA512(s:String) :String = MessageDigest.getInstance("SHA-512")
      .digest(s.getBytes("UTF-8"))
      .asHexString

  def SHA512n(s:String, bytes: Int) :String = MessageDigest.getInstance("SHA-512")
    .digest(s.getBytes("UTF-8"))
    .take(bytes)
    .asHexString

  def SHA512nBytes(s:String, bytes: Int): Array[Byte] = MessageDigest.getInstance("SHA-512")
    .digest(s.getBytes("UTF-8"))
    .take(bytes)

  def birthday(hashFunction: String => String, messageGenerator: Iterator[String]): BirthdayResult = {
    @tailrec def birthday(cache: HashMap[String,String], iterations:Int): BirthdayResult = {
      val key = messageGenerator.next
      val hash = hashFunction(key)
      if (cache.contains(hash)) {
        BirthdayResult(key, cache(hash), iterations)
      } else {
        birthday(cache + (hash -> key), iterations + 1)
      }
    }

    birthday(new HashMap[String, String], 0)

  }

  case class BirthdayResult(m1:String, m2:String, iterations: Int)



}


import java.security.MessageDigest
import javax.crypto.{Cipher, Mac}
import javax.crypto.spec.{GCMParameterSpec, IvParameterSpec, SecretKeySpec}

//import org.scalameter.api._

import com.bridgecanada.utils.HexConversions._

import scala.annotation.tailrec
import scala.collection.immutable.HashMap

object Chapter6 {

  def CbcAes256Mac(hexPlainText: String, hexKey:String): String = {
    val blockIterator: Iterator[String] = hexPlainText.grouped(256/8)
    val ivlength = hexKey.asBytes.length / 2
    val h0 = getIV(ivlength)
    blockIterator.foldLeft(h0)((hi, block) => hi ^ Aes256SingleBlock(block, hexKey))
  }

  private def Aes256SingleBlock(hexPlainText: String, hexKey:String):String = {
    val cipher = Cipher.getInstance("AES/ECB/NoPadding")
    val secretKey = new SecretKeySpec(hexKey.asBytes, "AES")
    cipher.init(Cipher.ENCRYPT_MODE, secretKey)
    cipher.doFinal(hexPlainText.asBytes).asHexString
  }

  def getIV(length: Int): String =
    Array.fill[Byte](length)(0).asHexString


  def HmacSha256(hexPlainText: String, hexKey: String):String =  {
    println(hexPlainText.readable)
    println(hexKey.readable)
    val hmac = Mac.getInstance("HmacSHA256")
    val secretKey = new SecretKeySpec(hexKey.asBytes, "HmacSHA256")
    hmac.init(secretKey)
    hmac.doFinal(hexPlainText.asBytes).asHexString
  }

  // SEE: http://crypto.stackexchange.com/questions/18164/using-gmac-for-authentication-without-encrypting-the-message,
  // "GMAC is quite simply GCM mode where all data is supplied as AAD
  // (or additional authenticated data)"
  def GmacAes256(hexPlainText: String, hexKey: String, nonce: String):String =  {

    val blockSize:Int = nonce.asBytes.length * 8
    //println(blockSize)
    val cipher = Cipher.getInstance ("AES/GCM/NoPadding")

    val secretKey = new SecretKeySpec(hexKey.asBytes, "AES")

    val gcmParamSpec= new GCMParameterSpec(blockSize, nonce.asBytes);
    cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParamSpec)
    cipher.updateAAD(hexPlainText.asBytes);
    //val result= cipher.doFinal(hexPlainText.asBytes).asHexString
    val result = cipher.doFinal().asHexString
    println(result)
    result
  }

  //
//  def aesAsHash(hexCipherText: String, hexKey: String): String = {
//    val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
//    //val secretKey = new SecretKeySpec(hexKey.asBytes, "AES")
//    IvParameterSpec params = new IvParameterSpec(DEFAULT_ICV);
//    cipher.init(Cipher.ENCRYPT_MODE, secretKey)
//    cipher.doFinal(hexCipherText.asBytes).asHexString
//
//  }
//
//  def MD5(s: String): String = {
//    // Besides "MD5", "SHA-256", and other hashes are available
//    val m = java.security.MessageDigest.getInstance("MD5").digest(s.getBytes("UTF-8"))
//    m.asHexString
//  }

//  def CBCMACAES256(s:String) :String = MessageDigest.getInstance("")
//      .digest(s.getBytes("UTF-8"))
//      .asHexString




}


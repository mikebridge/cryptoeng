import javax.crypto.spec.SecretKeySpec
import javax.crypto.{KeyGenerator, SecretKey}

import com.bridgecanada.utils.HexConversions._
import org.scalatest.{FlatSpec, Matchers}


class Chapter4Test extends FlatSpec with Matchers {

  "4.4" should "decrypt an AES cipherText with an IV" in {
    Chapter4.decrypt44(
      "87f348ff79b811af3857d6718e5f0f91" +
      "7c3d26f77377635a5e43e9b5cc5d0592" +
      "6E26ffc5220dc7d405f1708670e6e017",
      "80000000000000000000000000000000" +
      "00000000000000000000000000000001") shouldEqual "Another secret!  And another.   "
  }

  "4.5" should "encrypt an AES plainText in ECB mode" in {
    // p = "block ciphers   hash functions xblock ciphers"
    Chapter4.encrypt45(
      "626c6f636b2063697068657273202020" +
      "686173682066756e6374696f6e732078" +
      "626c6f636b2063697068657273202020",
      "80000000000000000000000000000000" +
      "00000000000000000000000000000001") shouldEqual
      "9b75b376fdc783bd0dff4ac44078ea8e6655f4222c0d413364f748e08f1805139b75b376fdc783bd0dff4ac44078ea8e" // ??
  }

}

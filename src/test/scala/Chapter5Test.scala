import javax.crypto.Cipher
import javax.crypto.spec.{IvParameterSpec, SecretKeySpec}

import org.scalatest.{FlatSpec, Matchers}


class Chapter5Test extends FlatSpec with Matchers {

  "Using AES as a hash function" should "..." in {
    val m = "87f348ff79b811af3857d6718e5f0f91" +
      "7c3d26f77377635a5e43e9b5cc5d0592" +
      "6E26ffc5220dc7d405f1708670e6e017"
    Chapter5.aesAsHash(m,
      "00000000000000000000000000000000" +
        "00000000000000000000000000000000") shouldEqual
      "7696ae242305f2584d5d8a05e22f09e6" +
      "2b2249f1df4a5508e447adb85ba8393d" +
      "ebe6683b023669d64370d2cbdd38acdd"


  }

  "5.2" should "calculate a SHA-512 hash" in {
    Chapter5.SHA512("48656c6c6f2c20776f726c642e202020") shouldEqual
      "7c532e636b924f5c37999adce795740d2d8018be6aa99e24bca1cf77c8c3c4d2c0eb22d8f4f08306f5052a8ef498fa3f4cb30735a6795132b390ec96e554abee"
  }

  //"5.3" should ""

}

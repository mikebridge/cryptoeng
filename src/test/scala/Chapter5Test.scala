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
        "00000000000000000000000000000000") shouldEqual "..."
  }

  "5.2" should "calculate a SHA-512 hash" in {
    val m = 

  }



}

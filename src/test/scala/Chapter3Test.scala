import javax.crypto.spec.SecretKeySpec
import javax.crypto.{KeyGenerator, SecretKey}

import com.bridgecanada.utils.HexConversions._
import org.scalatest.{FlatSpec, Matchers}


/**
  * Created by bridge on 06/12/16.
  */
class Chapter3Test extends FlatSpec with Matchers {

  "3.8" should "decrypt an AES cipherText" in {
    Chapter3.decrypt38("539b333b39706d149028cfe1d9d4a407",
      "80000000000000000000000000000000" +
      "00000000000000000000000000000001") should equal("80706050403020100807060504030201")
  }

  "3.9" should "encrypt an AES plainText" in {
    Chapter3.encrypt39("296c93fdf499aaeb4194babc2e63561d",
      "80000000000000000000000000000000" +
      "00000000000000000000000000000001") should equal("80000000000000000000000000000001")
  }

  "3.10" should "demonstrate complementation property in DES" in {
    val plainTextHex = "539b333b39706d149028cfe1d9d4a407"
    val desKey: SecretKey = createDesKey()

    val cipherText = Chapter3.encrypt310(plainTextHex, desKey)
    val cipherTextComplement = Chapter3.encrypt310(~plainTextHex, ComplementKey(desKey))

    cipherText shouldEqual ~cipherTextComplement

  }

  private def ComplementKey(desKey: SecretKey) =
    new SecretKeySpec((~desKey.getEncoded.asHexString).asBytes, "DES")

  private def createDesKey() = KeyGenerator.getInstance("DES").generateKey()

}

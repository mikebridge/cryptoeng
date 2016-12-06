package com.bridgecanada.utils

import HexConversions._
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by bridge on 05/12/16.
  */
class HexConversionsSpec extends FlatSpec with Matchers {

  behavior of "HexConversions"

  private val helloWorldAscii = "Hello, world"
  private val helloWorldBytes = Array[Byte](72, 101, 108, 108, 111, 44, 32, 119, 111, 114, 108, 100)
  private val helloWorldHex = "48656c6c6f2c20776f726c64"

  it should "convert and ASCII string to an array of bytes" in {
    helloWorldAscii.fromAsciiToBytes should contain theSameElementsAs helloWorldBytes
  }

  it should "convert and ASCII string to a HEX representation" in {
    helloWorldAscii.fromAsciiToHexString shouldEqual helloWorldHex
  }

  it should "convert a HEX string to an array of bytes" in {
    helloWorldHex.asBytes shouldEqual helloWorldBytes
  }

  it should "XOR a hex string with another HEX string" in {
    "deadbeef" ^ "10101010" shouldEqual "cebdaeff"
  }

  it should "convert an array of bytes to a HEX string" in {
    helloWorldBytes.asHexString shouldEqual helloWorldHex
  }

  it should "convert an array of bytes to an ascii string" in {
    helloWorldBytes.readable shouldEqual helloWorldAscii
  }

  it should "return the complement of a hex string" in {
    HexConversions.~("000000") shouldEqual "ffffff"
    HexConversions.~("ffffff") shouldEqual "000000"
    HexConversions.~(HexConversions.~("296c93fdf499aaeb4194babc2e63561d")) shouldEqual "296c93fdf499aaeb4194babc2e63561d"

  }

}

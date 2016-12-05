package com.bridgecanada.utils

import javax.xml.bind.DatatypeConverter

/**
  * Implicit conversions and helpers for the Coursera crypto course,
  * mainly to extend String types so they can be used as hex strings
  *
  * ==Usage==
  *
  * {{{
  * import HexConversions._
  * val result = "abcd" ^ "1234"
  * }}}
  *
  * ==Examples==
  *
  * XOR a hex string with another hex string
  * {{{
  * scala> "deadbeef" ^ "10101010"
  * res1: String = cebdaeff
  * }}}
  *
  * Convert an ascii string to a hex string
  * {{{
  * scala> "Hello, world".fromAsciiToHexString
  * res2: String = 48656c6c6f2c20776f726c64
  * }}}
  *
  * * Convert an ascii string to an array of bytes
  * {{{
  * scala> "Hello, world".fromAsciiToBytes
  * res3: Array[Byte] = Array(72, 101, 108, 108, 111, 44, 32, 119, 111, 114, 108, 100)
  * }}}
  *
  * Convert a hex string to an ascii string
  * {{{
  * scala> "48656c6c6f2c20776f726c64".readable
  * res4: String = Hello, world
  * }}}
  *
  * Convert a hex string to an array of bytes
  * {{{
  * scala> "48656c6c6f2c20776f726c64".asBytes
  * res5: Array[Byte] = Array(72, 101, 108, 108, 111, 44, 32, 119, 111, 114, 108, 100)
  * }}}
  *
  * Convert an array of bytes to a hex string
  * {{{
  * scala> Array[Byte](72, 101, 108, 108, 111, 44, 32, 119, 111, 114, 108, 100).asHexString
  * res6: String = 48656c6c6f2c20776f726c64
  * }}}
  *
  * Convert an array of bytes to an ascii string
  * {{{
  * scala> Array[Byte](72, 101, 108, 108, 111, 44, 32, 119, 111, 114, 108, 100).readable
  * res7: String = Hello, world
  * }}}
  */


object HexConversions {

  def toHexString (byteArray: Array[Byte]): String = DatatypeConverter.printHexBinary (byteArray).toLowerCase

  def toByteArray (s: String): Array[Byte] = DatatypeConverter.parseHexBinary (s)

  def isPrintable(ch: Int): Boolean = {
    ch >= 'a' && ch <= 'z' ||
      ch >= 'A' && ch <= 'Z' ||
      ch == '.' ||
      ch == ',' ||
      ch == '?' ||
      ch == '!' ||
      ch >= '0' && ch <= '9' ||
      ch == ' '
  }

  implicit class HexString(private val str: String) {

    def asBytes: Array[Byte] = toByteArray(str)

    def ^ (str2: String): String = (str.asBytes ^ str2.asBytes).asHexString

    def fromHexStringToInts: Iterator[Int] = str.sliding(2,2).map(Integer.parseInt(_, 16))

  }

  implicit class AsciiString(private val str: String) {

    def fromAsciiToHexString: String = str.map(_.toByte).toArray.asHexString

    def fromAsciiToBytes: Array[Byte] = fromAsciiToHexString.asBytes

    def readable:String = str.sliding(2,2)
      .map(b => java.lang.Integer.parseInt(b, 16).toChar)
      .map(x => if (isPrintable(x)) x else '_')
      .mkString

    def findMatchesWith(str2: String): Iterator[Int] = {
      str.fromHexStringToInts.zip(str2.fromHexStringToInts).zipWithIndex.flatMap {
        case ((a: Int, b: Int), index: Int) if a == b => Some(index)
        case (_) => None
      }
    }

  }

  implicit class HexBytes(private val byteArray: Array[Byte]) {

    def asHexString: String = toHexString(byteArray)

    def readable: String = asHexString.readable

    // SEE: http://stackoverflow.com/questions/31221462/scala-defining-own-infix-operators
    def ^ (byteArray2: Array[Byte]): Array[Byte] = byteArray.zip(byteArray2).map {
      case (a:Byte, b:Byte) => (a ^ b).byteValue
    }

  }

}


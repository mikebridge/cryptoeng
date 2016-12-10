import javax.crypto.Cipher
import javax.crypto.spec.{IvParameterSpec, SecretKeySpec}
import com.bridgecanada.utils.HexConversions._

import Chapter5.BirthdayResult
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.Inspectors._

import scala.collection.immutable.Seq
import scala.util.Random


class Chapter5Test extends FlatSpec with Matchers {

//  "Using AES as a hash function" should "..." in {
//    val m = "87f348ff79b811af3857d6718e5f0f91" +
//      "7c3d26f77377635a5e43e9b5cc5d0592" +
//      "6E26ffc5220dc7d405f1708670e6e017"
//    Chapter5.aesAsHash(m,
//      "00000000000000000000000000000000" +
//        "00000000000000000000000000000000") shouldEqual
//      "7696ae242305f2584d5d8a05e22f09e6" +
//        "2b2249f1df4a5508e447adb85ba8393d" +
//        "ebe6683b023669d64370d2cbdd38acdd"
//  }

  def counter(from: Int): Stream[Int] = from #:: counter(from + 1)

  "5.2" should "calculate a SHA-512 hash" in {
    Chapter5.SHA512("48656c6c6f2c20776f726c642e202020") shouldEqual
      "7c532e636b924f5c37999adce795740d2d8018be6aa99e24bca1cf77c8c3c4d2c0eb22d8f4f08306f5052a8ef498fa3f4cb30735a6795132b390ec96e554abee"
  }

  "5.3" should "return an n-byte value" in {
    Chapter5.SHA512n("123", 1).asBytes should have length 1
    Chapter5.SHA512n("123", 2).asBytes should have length 2

  }



  "5.3" should "perform a birthday attack on SHA-512-n" in {
    //val totalIterations = 7 // one for each byte width
    val totalIterations = 5 // one for each byte width
    val byteVsIterations: Seq[(Int, Int)] = Range(1, totalIterations).map(

      bytes => (bytes, runBirthdayNtimes(5, bytes)))
      .map(result => (result._1  * 8, result._2)
    )
    // should probably test for ranges of expected values instead
    byteVsIterations shouldEqual Vector((8,12), (16,347), (24,3434), (32,49479), (40,1528468))

  }

  "5.4" should "find a particular 1 byte collision for a in SHA-512-n" in {
    val seededStringGenerator = stringGenerator(1)
    findMatchingHash(seededStringGenerator, "a9", 1) shouldEqual((241, "jozNkogG3315waO"))
    findMatchingHash(seededStringGenerator, "3d4b", 2) shouldEqual((6061, "KnHyGELdlUxFQyI"))
    findMatchingHash(seededStringGenerator, "3a7f27", 3) shouldEqual((424106, "9nFBZ62OZzpRgf4"))
    findMatchingHash(seededStringGenerator, "c3c0357c", 4) shouldEqual((65153227, "0VoV679AAmaZQIg"))
  }

  "5.4" should "average those and see how long they take" in {
    val totalIterations = 4 // one for each byte width
    //val byteVsIterations: Seq[(Int, Int)] = Vector("a9", "3d4b", "3a7f27", "c3c0357c").map((goal: String) => {
    val byteVsIterations: Seq[(Int, Int)] = Vector("a9", "3d4b", "3a7f27").map((goal: String) => {
      val bytes = goal.length() / 2
      (bytes * 8 , runFindHashNtimes(5, goal, bytes))
    })
    // should probably test for ranges of expected values instead
    byteVsIterations shouldEqual Vector((8,151), (16,75910), (24,9309706) /*, (32, ) */)

    //runFindHashNtimes(, "a9", 1) shouldEqual((241, "jozNkogG3315waO"))
  }

//  private def findMatchingHash(seededStringGenerator: Iterator[String], goal: String): Int = {
//    findMatchingHash(seededStringGenerator, goal, 1) match {
//      case (iters, str) => iters
//    }
//  }

  // return the iterations + the collision
  private def findMatchingHash(stringGenerator: Iterator[String], goal: String, bytes: Int): (Int, String)= {
    stringGenerator
      .zipWithIndex
      .map(str => (str._2, str._1, Chapter5.SHA512n(str._1, bytes))) // (index, str, hash)
      .dropWhile(x => x._3 != goal)
      .next() match {
         case (index, str, hash) => (index, str)
      }
  }

  private def runFindHashNtimes(repetitions: Int, goal: String, bytes: Int): Int = {
    val seededStringGenerator = stringGenerator(repetitions)
    runAndAverageResults(repetitions, () => findMatchingHash(seededStringGenerator, goal, bytes)._1)
  }


  private def runBirthdayNtimes(repetitions: Int, bytes: Int): Int = {
    val seededStringGenerator = stringGenerator(repetitions)
    runAndAverageResults(repetitions, () => runBirthdayWithBytePrefixLength(bytes, seededStringGenerator).iterations)
  }

  private def runAndAverageResults(repetitions: Int, fn: () => Int ): Int =
    (Range(0, repetitions).map(_ => fn()).sum * 1.0 / repetitions).toInt


  private def runBirthdayWithBytePrefixLength(bytes: Int, stringGenerator: Iterator[String]): BirthdayResult =
    Chapter5.birthday(x => Chapter5.SHA512n(x, bytes), stringGenerator)



//  object RangeBenchmark extends Bench.LocalTime {
//    performance of "Range" in {
//      measure method "map" in {
//        var width = Gen.single("bytes")(1)
//        using(width) in {
//          r => runBirthdayWithBytePrefixLength(r)
//        }
//      }
//    }
//    // multiple tests can be specified here
//
//  }


  def stringGenerator(seed: Int): Iterator[String] = new Random(seed).alphanumeric.sliding(15).map(_.mkString)



}

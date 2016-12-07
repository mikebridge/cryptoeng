4.1) When a block has no padding (i.e. n==0) there is no way to tell if characters that 
look like padding are part of the message or padding.

4.2) 
- *fixed IV* : has the same properties as ECB, therefore it's insecure
- *counter IV* : because it is not random, it may create identical ciphertext blocks when 
      XORing similar messages
- *random IV* : can generate unique IVs without the overhead of managing them, but since the
      recipient needs the IV, it adds to the message size.  
- *nonce IV* : message size can be smaller than for random IV but it introduces overhead for the
      developer.
      
4.3) Because C and C' were generated with the same key and nonce:
- C<sub>1</sub> = P<sub>1</sub> &#8853; F(K, nonce) and C<sub>2</sub> = P<sub>2</sub> &#8853; F(K, nonce)
- so, C<sub>1</sub> &#8853; P<sub>1</sub> = C<sub>2</sub> &#8853; P<sub>2</sub>
- => P<sub>2</sub> = C<sub>1</sub> &#8853; C<sub>2</sub> &#8853; P<sub>1</sub>

```scala
import com.bridgecanada.utils.HexConversions._
val c1 = "4664dc0697bbfe69330715079ba6c23d2b84de4f908d7d34aace968b64f3df75"
val c2 = "517ecc05c3bdea3b33570e1bd897d5307bd0916b8d826b35b78bbb8d74e2c73b"
val p1 = "43727970746f6772617068792043727970746f6772617068792043727970746f"
p1.readable
=> "Cryptography Cryptography Crypto"

(c1 ^ c2 ^ p1).readable
=>"This is a secret   Confidential!"
```

4.4) [code](https://github.com/mikebridge/cryptoeng/blob/master/src/main/scala/Chapter4.scala)
      - [tests](https://github.com/mikebridge/cryptoeng/blob/master/src/test/scala/Chapter4Test.scala)
     
4.5) [code](https://github.com/mikebridge/cryptoeng/blob/master/src/main/scala/Chapter4.scala)
     - [tests](https://github.com/mikebridge/cryptoeng/blob/master/src/test/scala/Chapter4Test.scala)
     
4.6)
Given:
- C<sub>2</sub> = E<sub>K</sub>(P<sub>2</sub> &#8853; C<sub>1</sub>)
- C<sub>1</sub>' = E<sub>K</sub>(P<sub>1</sub>' &#8853; C<sub>0</sub>')
- C<sub>1</sub>' = C<sub>2</sub>

Then:
- E<sub>K</sub>(P<sub>2</sub> &#8853; C<sub>1</sub>) = E<sub>K</sub>(P<sub>1</sub>' &#8853; C<sub>0</sub>')
- P<sub>2</sub> &#8853; C<sub>1</sub> = P<sub>1</sub>' &#8853; C<sub>0</sub>'
- P<sub>1</sub>' = P<sub>2</sub> &#8853; C<sub>1</sub> &#8853; C<sub>0</sub>'

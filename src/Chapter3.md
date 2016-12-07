3.1) 2<sup>64</sup> * 2<sup>80+80</sup> = 2 <sup>204</sup>

3.2) 
- DES has 16 rounds 
- A DES key is 56 bits
- A DES block is 64 bits
- 3DES encrypts or decrypts with DES three times

3.3)
- AES has 128, 192 and 256 bit keys
- 10 rounds for 128, 12 for 192 and 14 for 256
- AES block size is 128 bits

3.4)
- You might choose 3DES when there are limitations on the cipher you can use.
- You might choose AES when you need speed and feel more comfortable with a newer standard 

3.5)
- 2<sup>-26</sup>s * 2<sup>56</sup> = 2<sup>30</sup> / 3600 &#8773; 298262 hours
- 2<sup>30</sup> / 2<sup>14</sup> / 3600 = 18 hours

3.6) 
- take one of the plaintexts and split it into two 32-bit pairs, L0 and R0
- brute-force all the 48-bit values of K<sub>0</sub> and K<sub>1</sub> where: 
    - L<sub>1</sub> = R<sub>0<sub>,     
    - R<sub>1</sub> = L<sub>0</sub> &#8853; F(R<sub>0</sub>,K<sub>0</sub>),
    - L<sub>2</sub> = R<sub>1</sub>,
    - R<sub>2</sub> = L<sub>1</sub> &#8853; F(R<sub>1</sub>,K<sub>1</sub>),
    - C = L<sub>2</sub> || R<sub>2</sub>
    - C = (L<sub>0</sub> &#8853; F(R<sub>0</sub>,K<sub>0</sub>)) ||  R<sub>0</sub> &#8853; F(L<sub>0</sub> &#8853; F(R<sub>0</sub>,K<sub>0</sub>), K<sub>1</sub>)
    
So you could exclusively search the left half of this equation once, and the right have once, which would be
2<sup>48</sup> + 2<sup>48</sup>, which is less than 2<sup>56</sup>.

- With the found k1 and k2 values, you can create a determiner by running DES2 on the other plaintext/ciphertext pairs.
  
3.7) A system might encrypt some text via DES and a single key.  You can perform a Chosen Plaintext Attack 
by encoding a particular m and ~m with that key, then you only need to exclusively search half the 
keyspace to match m with c and ~m with ~c Because E<sub>~k</sub>(~m) will equal ~E<sub>k</sub>(m), 
you effectively get two c values calling E once, you reduce the steps to 2<sup>55</sup>.


3.8-10) [code](https://github.com/mikebridge/cryptoeng/blob/master/src/main/scala/Chapter3.scala)
     - [tests](https://github.com/mikebridge/cryptoeng/blob/master/src/test/scala/Chapter3Test.scala)
     - [java code](https://github.com/mikebridge/cryptoeng/blob/master/src/main/java/Chapter3J.java)

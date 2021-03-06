5.1)  [code](https://github.com/mikebridge/cryptoeng/blob/master/src/main/scala/Chapter5.scala)
      - [tests](https://github.com/mikebridge/cryptoeng/blob/master/src/test/scala/Chapter5Test.scala)
  
5.3) [code](https://github.com/mikebridge/cryptoeng/blob/master/src/main/scala/Chapter5.scala)
        - [tests](https://github.com/mikebridge/cryptoeng/blob/master/src/test/scala/Chapter5Test.scala)

Using random data, we get the following table, using the following formula to calculate the expected 
values:

given:
- k = randomly generated guesses
- N = possible hash values (2<sup># bits</sup>)
- p = percent probablility of a collision

then:
- p = k<sup>2</sup> / 2 N
- k = sqrt(p * 2N)
- k = sqrt(0.5 * 2N) = sqrt(2<sup># bits</sup>)


| bits | iterations | expected  
| ----:| ----------:| ----------:
| 8    | 12         | 16
| 16   | 347        | 256
| 24   | 3434       | 4096
| 32   | 49479      | 65536
| 40   | 1528468    | 1048576
| 48   | ???        | 16777216

So in theory we would expect the following:

| bits  | expected  
| ----: | ----------:
| 256   | sqrt(2<sup>256</sup>) = 3.4 x 10<sup>38</sup>
| 512   | sqrt(2<sup>384</sup>) = 6.7 x 10<sup>57</sup>
| 512   | sqrt(2<sup>512</sup>) = 1.2 x 10<sup>77</sup>


5.4) 

| bits | iterations | expected  
| ----:| ----------:| ----------:
| 8    | 151        | 256
| 16   | 75910      | 65536
| 24   | 9309706    | 16777216
| 32   | | 2 ^ 32
| 40   | | 2 ^ 40
| 48   | | 2 ^ 48

5.5)
Given:
- K = {0,1}<sup>256</sup>
- H<sub>0</sub> = {0}<sup>128</sup>
- m = m<sub>1</sub>, ..., m<sub>k</sub>
- H<sub>i</sub> = AES<sub>K</sub>(H<sub>i-1</sub> &#8853; m<sub>i</sub>)

And
1. m' = m<sub>1</sub>', m<sub>2</sub>' 
2. m<sub>1</sub>' = m<sub>2</sub> &#8853; H<sub>1</sub>
3. m<sub>2</sub>' = H<sub>2</sub> &#8853; m<sub>2</sub> &#8853; H<sub>1</sub>

Then:
4. H<sub>1</sub> = AES<sub>K</sub>({0}<sup>128</sup> &#8853; m<sub>1</sub>) = AES<sub>K</sub>(m<sub>1</sub>) 
5. H<sub>2</sub> = AES<sub>K</sub>(H<sub>1</sub> &#8853; m<sub>2</sub>)
6. H<sub>1</sub>' = AES<sub>K</sub>({0}<sup>128</sup> &#8853; m<sub>1</sub>') = AES<sub>K</sub>(m<sub>1</sub>')
7. H<sub>2</sub>' = AES<sub>K</sub>(H<sub>1</sub>' &#8853; m<sub>2</sub>')
 
Show that H<sub>2</sub> = H<sub>2</sub>'

- H<sub>2</sub> = H<sub>2</sub>'
- AES<sub>K</sub>(H<sub>1</sub> &#8853; m<sub>2</sub>) *[from 5]* = AES<sub>K</sub>(H<sub>1</sub>' &#8853; m<sub>2</sub>') *[from 7]*
- AES<sub>K</sub>(m<sub>1</sub>') *[from 2]* =  AES<sub>K</sub>(H<sub>1</sub>' &#8853; m<sub>2</sub>')
- AES<sub>K</sub>(m<sub>1</sub>') =  AES<sub>K</sub>(H<sub>1</sub>' &#8853; (H<sub>2</sub> &#8853; m<sub>2</sub> &#8853; H<sub>1</sub>))  *[from 3]* 
- AES<sub>K</sub>(m<sub>1</sub>') = AES<sub>K</sub>(H<sub>1</sub>' &#8853; H<sub>2</sub> &#8853; m<sub>1</sub>')
- AES<sub>K</sub>(m<sub>1</sub>') = AES<sub>K</sub>(AES(m<sub>1</sub>') &#8853; H<sub>2</sub> &#8853; m<sub>1</sub>') *[from 4]*
- AES<sub>K</sub>(m<sub>1</sub>') = AES<sub>K</sub>(AES(m<sub>1</sub>') &#8853; AES<sub>K</sub>(H<sub>1</sub> &#8853; m<sub>2</sub>) &#8853; m<sub>1</sub>') *[from 5]*
- AES<sub>K</sub>(m<sub>1</sub>') = AES<sub>K</sub>(AES(m<sub>1</sub>') &#8853; AES(m<sub>1</sub>') &#8853; m<sub>1</sub>') *[from 2]*
- AES<sub>K</sub>(m<sub>1</sub>') = AES<sub>K</sub>(m<sub>1</sub>') Q.E.D.


5.6) See: http://ehash.iaik.tugraz.at/wiki/The_SHA-3_Zoo
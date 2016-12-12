6.1) You could design a financial payment system that encodes one transaction per block.
Each block P might consist of a transaction number and the transaction details.
It would be possible to take the output of the valid CBC-MAC (MAC<sub>K</sub>) and forge a new 
message P<sub>K+1</sub> which includes transaction number K+1 and a fake transaction, 
because you could create a new, valid MAC<sub>K+1</sub> using the following:

    MAC<sub>K+1</sub> = E<sub>K</sub>(P<sub>K+1</sub> &#8853; MAC<sub>K</sub>)

SEE: https://blog.cryptographyengineering.com/2013/02/15/why-i-hate-cbc-mac/
    
6.2) If M(a || c) and M(b || c) are equal, and a and b end on a block boundary, then 
M(a) and M(b) must be equal, because M(a) and M(b) each feed into the step which adds "c" to
the MAC.  And if they are equal, they will both also feed the same input into the step
that adds "d" to the MAC.

6.3) If an attacker has:

- M(a)
- M(b)
- M(a || b)

Show:

M(b || M(b) &#8853; M(a) &#8853; b) = M(a || b)

LHS:

- P<sub>0</sub> = b
- P<sub>1</sub> = M(b) &#8853; M(a) &#8853; b


- H<sub>0</sub> = IV = {0}<sup>n</sup>
- H<sub>1</sub> = M(P<sub>0</sub>) = M(b)  
- H<sub>2</sub> = M(P<sub>1</sub> &#8853; H<sub>1</sub>)
- H<sub>2</sub> = M(P<sub>1</sub> &#8853; M(b))
- H<sub>2</sub> = M( M(b) &#8853; M(a) &#8853; b &#8853; M(b))
- H<sub>2</sub> = M(M(a) &#8853; b)

RHS: 

- P<sub>0</sub>' = a
- P<sub>1</suB>' = b

- H<sub>0</sub>' = IV = {0}<sup>n</sup>
- H<sub>1</sub>' = M(P<sub>0</sub>') = M(a)  
- H<sub>2</sub>' = M(P<sub>1</sub>' &#8853; H<sub>1</sub>')
- H<sub>2</sub>' = M(b &#8853; M(a)))

Therefore H<sub>2</sub> = H<sub>2</sub>'

6.4) This question doesn't make sense---doesn't "of your choice" mean you choosing the blocks arbitrarily?  
Why would you have to justify an arbitrary choice mathematically?

6.5) 
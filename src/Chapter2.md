Chapter 2

2.1) Kerchhoff's principle The security of an encryption scheme must depend only on 
     the secrecy of the key K<sub>e</sub>, and not on the secrecy of the algorithm
     
     Pros:
     - algorithms are hard to change & therefore hard to update
     - it's hard to keep algorithms secret
     
     Cons:
     - If the algorithm is weak it might be better secret
     
     ...
     
2.2) People who can attack it:
    - people in range of the network
    - people who operate the network locally
    - people at the ISP
    - people with access to the routed traffic on the interent
    People who can attack the sytem:
    - any of those groups could read data, and anyone able to send packets
     could also modify them.
    To make it more secure they could use an established means of 
     encryption/validation... 

2.3) k * (k - 1)

2.4) No, only that Alice's private key was used.

2.5) It is likely that Alice would want to establish human contact with a trusted 
  source to convince herself that the key is valid.  The assumption would be that
  this vocal contact would be difficult or impossible to forge or tamper with.

2.6) No, because it may still reveal partial information.

2.7) n = 256 (using the the 2<sup>n/2</sup> bound)
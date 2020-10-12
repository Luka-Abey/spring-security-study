## CSRF
### Cross Sight Request Forgery:

1) Action of creating an imitation of a document, signature, bank note etc.

2) Attacker forges a request for fund transfer to a web site.

3) Perpetrator embeds the request into a hyperlink.

4) Visitor to the site clicks on the link, sending the request to the forged site.

5) With CSRF disabled, the site validates the request and sends the visitor to the forged site.


CSRF means the server generates a CSRF token in a cookie and sends to the client
Only forms submitted (put post, delete) with the given token are accepted.  
 


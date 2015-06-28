# FibonacciCalc
<h4>About</h4>
<a href="https://en.wikipedia.org/wiki/Fibonacci_number">Wikipedia:Fibonacci number </a>

There are two types of calculating:
<h5>Default</h5>
Direct calculate number throw loop via direct formula:
X(n+2)=X(n)+X(n+1);

<h5>Binet's Fibonacci number formula</h5>

<a href="https://en.wikipedia.org/wiki/Jacques_Philippe_Marie_Binet">Wikipedia:Jacques Philippe Marie Binet </a>
Using Heron method to calculate sqrt.
<a href="https://en.wikipedia.org/wiki/Methods_of_computing_square_roots">Wikipedia:Babylonian method (Heron method) </a>

<h4>Usage</h4>
<pre>
Usage: java -jar FibonacciCalc.jar [OPTION]...
Program will calculate Fibonacci number based on reference number.

-n			Referece Number, Default 100
-t			Type: Default or ByBinet
-p			Precision, required by Binet Method, Default 100

</pre>
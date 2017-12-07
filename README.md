# cs6367 : Mutation Testing 

## Introduction
This was a group project. This project will add several mutations to the suite of available PIT code. Mutation testing is a method of software quality control that tests the unit test suite defined for programs by artificially injecting errors, or mutations, into software. Tests are then executed to evaluate the quality of the test suite.  

## Idea
Contributed	to the PIT mutation testing tool to implement more mutation operators. The	description	of the original	set	of mutation	operators	implemented	in PIT	is available	at:	http://pitest.org/quickstart/mutators/.	All	existing PIT mutation	operators	are	implemented	under	the	following source code	dir: “pitest/src/main/java/org/pitest/mutationtest/engine/gregor/mutators”). 
We	evaluated	following	augmented	PIT mutators on	5 real-world projects called dnsjava, coreNLP, jsoup, twilio, webmagic.  
1. ABS: Replaces a variable	by its negation,	e.g.,	a -> a	
2. OBBN: Replaces	the	operators	&	by	|	and	vice	versa,	e.g., a&b	-> a|b	
3. AOD: Replaces an	arithmetic expression	by one of	the	operand,	e.g., a	+	b	-> a	
4. ROR: Replaces the relational operators	with another one. It	applies	every	replacement,	e.g., <	to ≥, or > to ≤	
5. AOR: Replaces an	arithmetic expression	by another one.	a	+	b	-> a ∗ b	
6. UOI: Replaces a variable	with a unary operator or removes an instance of	an unary operator.	a	-> a++	
7. CRCR: Replaces	a	constant a with	its	negation,	or with	1, 0, a +	1, a – 1,	e.g.,	a -> −a,	and	a -> a − 1.

## Overview
#### 

![Home page](/HomePage.png)

#### Back-end


![Database](/schema/db1.png)

## Team Members
* [Matthew Bachelder](https://www.linkedin.com/in/matthew-bachelder/)
* [Vaishnavi Bhosale](https://www.linkedin.com/in/vaishnavi-bhosale/)
* [Richard Fisher]

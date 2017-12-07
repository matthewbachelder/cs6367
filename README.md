# cs6367 : Mutation Testing 


# TA DIRECTIONS TO RUN
1. Clone the repository.
2. Run 'mvn clean install -DskipTests' to install the pit maven project into your local repository (located at pitest/pitest/*). Please      note you must skip the default tests PIT has included. 
3. Include the 'CS6367' group name in the PIT configuration (located in the pom.xml of your test project). Alternativley you may run each    of the mutators individually by including the name of the mutator in the configuration instead.

    Example PIT pom.xml configuration for group run
    
            <plugin>
                <groupId>org.pitest</groupId>
                <artifactId>pitest-maven</artifactId>
                <version>1.2.4</version>
                <configuration>
                    <includedGroups>
                        CS6367
                    </includedGroups>
                </configuration>
            </plugin>

   Example PIT pom.xml configuration for individual run
   
             <plugin>
                <groupId>org.pitest</groupId>
                <artifactId>pitest-maven</artifactId>
                <version>1.2.4</version>
                <configuration>
                    <mutators>
                        <mutator>ABS</mutator>
                    </mutators>
                </configuration>
            </plugin>


4. Run the PIT plugin on your test project.
5. View the results.


Mutator Names (if running individually)
ABS
OBBN
AOD
ROR
AOR
UOI_PLUS
UOI_MINUS
CRCRADDONE
CRCRSUBTRACTONE
CRCRNEGATE
CRCRAWITHONE
CRCRWITHZERO






## Introduction
This was a group project. This project will add several mutations to the suite of available PIT code. Mutation testing is a method of software quality control that tests the unit test suite defined for programs by artificially injecting errors, or mutations, into software. Tests are then executed to evaluate the quality of the test suite.  

## Idea
Contributed	to the PIT mutation testing tool to implement more mutation operators. The description of the original set of mutation	operators	implemented	in PIT	is available	at:	http://pitest.org/quickstart/mutators/.	All	existing PIT mutation	operators	are	implemented	under	the	following source code	dir: “pitest/src/main/java/org/pitest/mutationtest/engine/gregor/mutators”). 
We	evaluated	following	augmented	PIT mutators on	5 real-world projects called dnsjava, coreNLP, jsoup, twilio, webmagic.  
1. ABS: Replaces a variable	by its negation,	e.g.,	a -> a	
2. OBBN: Replaces	the	operators	&	by	|	and	vice	versa,	e.g., a&b	-> a|b	
3. AOD: Replaces an	arithmetic expression	by one of	the	operand,	e.g., a	+	b	-> a	
4. ROR: Replaces the relational operators	with another one. It	applies	every	replacement,	e.g., <	to ≥, or > to ≤	
5. AOR: Replaces an	arithmetic expression	by another one.	a	+	b	-> a ∗ b	
6. UOI: Replaces a variable	with a unary operator or removes an instance of	an unary operator.	a	-> a++	
7. CRCR: Replaces	a	constant a with	its	negation,	or with	1, 0, a +	1, a – 1,	e.g.,	a -> −a,	and	a -> a − 1.

## Overview
### Pre-augementation Webmagic project report

![Pre-augementation Webmagic project report](/webmagic1.png)

### Post-augementation Webmagic project report

![Post-augementation Webmagic project report](/webmagic2.png)

## Team Members
* [Matthew Bachelder](https://www.linkedin.com/in/matthew-bachelder/)
* [Vaishnavi Bhosale](https://www.linkedin.com/in/vaishnavi-bhosale/)
* [Richard Fisher]

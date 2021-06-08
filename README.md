# Author: Okan Menevşeoğlu

You can find my CV online <a href="https://okanmenevseoglu.github.io">here</a>.

I also write blog posts on Medium from time to time. If you are interested, you can have a
look <a href="https://medium.com/@okanmenevseoglu">here</a>.

# Reverse Polish Notation (RPN) Calculator

## Tools & Libraries

* Java 15
* JUnit 5.7.1
* Mockito 3.9.0
* AssertJ 3.19.0
* Maven 3.8.1
* Maven Compiler Plugin

## Tests

The project has 44 unit tests and 100% test coverage with JUnit 5, Mockito & AssertJ.

## How to run?

The project is a Java 15/Maven project. To be able to run it you should install at least Java 15 and Maven 3.8.1. After
the installation run the command:

* ```mvn clean install exec:java -Dexec.mainClass=io.github.okanmenevseoglu.rpncalculator.Main```
  under ```{PROJECT_DIR}/rpn-calculator```

## How to use the application?

The project is a console application. After running the application, the calculator will print the instructions on the
console. It follows the following order:

1. The calculator asks for the user to enter an RPN in the new line.
2. After user enters a space separated string, the calculator displays the current stack after operations.
3. All operations can be undone including clear.
4. To exit, case-insensitive "exit" needs to be typed.
5. After typing "exit", the program exits with a "thank you" message.

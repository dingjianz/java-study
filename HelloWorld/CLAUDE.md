# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Java learning project containing demonstration code for various Java concepts, organized by topic. Each package under `com.itheima` represents a specific learning module with standalone demo classes.

## Build and Run

### Maven Commands
```bash
# Compile the project
mvn compile

# Clean and rebuild
mvn clean compile

# Package (creates JAR in target/)
mvn package
```

### Running Demo Classes
Each demo class contains a `main()` method and can be run independently:

```bash
# From IntelliJ IDEA: Right-click on the class → Run
# From command line (after compilation):
java -cp target/classes com.itheima.ioDemo.CommonsIoDemo
java -cp target/classes com.itheima.collectionDemo.TreeSetDemo
```

For classes using external JARs in `lib/` directory:
```bash
java -cp "target/classes:lib/*" com.itheima.hutool.HutoolDemo
```

## Project Architecture

```
HelloWorld/
├── src/com/itheima/          # All source code organized by topic
│   ├── literal/              # Basic literals
│   ├── variable/             # Variables, BigDecimal, arrays
│   ├── operator/             # Operators (arithmetic, logical, etc.)
│   ├── method/               # Methods, parameters, overloading
│   ├── oop*/                 # OOP fundamentals (5 packages)
│   ├── oopextends*/          # Inheritance (7 packages)
│   ├── ooppolymorphic*/      # Polymorphism (3 packages)
│   ├── oopinterface*/        # Interfaces (2 packages)
│   ├── oopabstract/          # Abstract classes
│   ├── oopinnerclass/        # Inner classes
│   ├── oopjavabean/          # JavaBean patterns
│   ├── arraydemo/            # Array operations
│   ├── stringdemo/           # String manipulation
│   ├── collectionDemo/       # Collections framework
│   ├── mapDemo/              # Map implementations
│   ├── streamDemo/           # Stream API
│   ├── lambdademo/           # Lambda expressions
│   ├── methodReference/      # Method references
│   ├── ioDemo/               # I/O streams, serialization, compression
│   ├── fileDemo/             # File operations
│   ├── datedemo/             # Date/time handling
│   ├── exceptionDemo/        # Exception handling
│   ├── enumtest/             # Enumerations
│   ├── immutableDemo/        # Immutable collections
│   ├── hutool/               # Hutool library demos
│   ├── game/                 # Game application (UI + bean packages)
│   └── runtime/              # Runtime operations
├── lib/                      # External JARs (commons-io, gson, jol-core)
├── pom.xml                   # Maven configuration
└── SnakeGame.java            # Standalone game implementation
```

## Key Dependencies

- **Java 25**: Project requires Java 25 features (e.g., `addFirst()` on List)
- **Hutool 5.8.36** (`cn.hutool:hutool-all`): Comprehensive utility library for dates, strings, collections, JSON
- **Commons IO 2.15.1** (`commons-io:commons-io`): File and stream utilities
- **Gson 2.10.1** (`com.google.code.gson:gson`): JSON serialization/deserialization
- **JOL Core 0.17** (`org.openjdk.jol:jol-core`): Java Object Layout inspection

## Package Organization by Topic

### Core Language (11 packages)
- `literal`, `variable`, `operator`, `method`: Language fundamentals
- `oop` through `oop5`: Basic OOP concepts
- `finaltest`: Final keyword usage

### OOP Advanced (18 packages)
- `oopextends` through `oopextends7`: Inheritance hierarchies
- `ooppolymorphic1-3`: Polymorphism with Student, Father-Son, Tool examples
- `oopinterface`, `oopinterface2`: Interface implementations
- `oopabstract`: Abstract classes (Animal hierarchy)
- `oopinnerclass`: Member, local, anonymous inner classes
- `oopjavabean`: JavaBean patterns with Person, Player, Coach

### Data Structures (6 packages)
- `arraydemo`: Array operations, binary search, sorting
- `collectionDemo`: List, Set, TreeSet, LinkedList, iterators (23 classes)
- `collectionsDemo`: Collections utility methods
- `mapDemo`: HashMap, TreeMap implementations
- `streamDemo`: Stream API filtering and processing
- `immutableDemo`: Immutable collections

### I/O Operations (2 packages)
- `ioDemo`: 36 classes covering byte/buffered/conversion streams, zip, serialization, print streams, Commons IO
- `fileDemo`: File creation, deletion, manipulation

### Utilities (8 packages)
- `stringdemo`: String operations
- `datedemo`: Calendar, SimpleDateFormat, Instant, ZoneId, LocalDateTime
- `lambdademo`: Lambda expressions and functional interfaces
- `methodReference`: Method reference syntax (::)
- `enumtest`: Enum with OrderState examples
- `Objectdemo`: Object class methods, cloning
- `toolclasstest`: Custom utility classes
- `hutool`: Hutool library examples

### Advanced Topics (3 packages)
- `exceptionDemo`: Custom exceptions (AgeOutBoundsException, NameFormateException)
- `runtime`: Runtime class operations
- `game`: Game application with login system (bean + ui subpackages)

## Important Notes

### Java Version Requirements
- **Requires Java 21+** for `List.addFirst()`, `List.addLast()` methods
- **Current target: Java 25** (configured in pom.xml)
- If encountering compilation errors on older Java versions, downgrade these method calls to `add(0, element)`

### Common Issues

**Hutool DateUnit Import**
```java
// Correct import for Hutool's DateUnit
import cn.hutool.core.date.DateUnit;
// NOT java.time.temporal.ChronoUnit
```

**Three-Letter Time Zone Warnings**
When running `HutoolDemo`, you may see warnings like:
```
WARNING: Use of the three-letter time zone ID "ACT" is deprecated
```
These are Java runtime warnings (not errors) from the time zone system. The program runs correctly; these warnings can be safely ignored or suppressed with JVM logging configuration.

**Commons IO Usage**
- JAR is in `lib/commons-io-2.15.1.jar`
- Also declared in pom.xml as Maven dependency
- Demo class: `com.itheima.ioDemo.CommonsIoDemo`

### Running Individual Demos
Most classes are self-contained with `main()` methods and can be run independently. They typically demonstrate a single concept with console output examples.


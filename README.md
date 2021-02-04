# Javar.Project

### WARNING: This version was deprecated and will not be updated any more.

**Javar** is a lightweight and powerful coder for C/C++, Java, Python and HTML. 

## Build

Javar has been built and tested with Java SE 11. Please download and install a suitable version of Java on your system before building Javar.

### Windows

```
// TO BE COMPLETED
```

### macOS

```
$ cd Javar.Project
$ cp ./configs/properties/Init.properties ./configs/properties/Javar.properties
$ mkdir build && cd build && mkdir c_lib
$ javac -d . ../src/*.java
$ gcc -c ../src/c_src/parser.c && gcc -dynamiclib -shared -o ./c_lib/libparser.dylib parser.o && rm parser.o
$ java com.yiyaowen.javar.Javar
```

## Compile/Run/Render Requirement

To compile, run or render these source files using Javar, you should make sure that C/C++, Java and Python can be compiled/run with your command prompt or terminal, and that HTML can be rendered under general HTML standard. That is, the following commands must work well on your system.

### C/C++

For Windows and macOS, they are the same most of time.

```
# Windows
gcc -std=c11 -o example example.c
g++ -std=c++11 -o example example.cpp

# macOS
gcc -std=c11 -o example example.c
g++ -std=c++11 -o example example.cpp
```

### Java

[cmd] means that it is optional.

```
# Windows
javac -encoding utf-8 -d . example.java

# macOS
javac [-encoding utf-8] -d . example.java
```

### Python

Only support Python3.x.

```
python3 example.py
```

Sometimes the system only has Python3.x installed. In this case Javar still run well, since it will try to run the following command instead:

```
python example.py
```

### HTML

A example.html should be rendered well with your browser.

## Other Information About Javar

### Supported Languages

* English

* 简体中文


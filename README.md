# Javar.CodEditor

**Javar** is a lightweight and powerful coder for C/C++, Java, Python and HTML.

## Build & Run

Javar has been built and tested with Java SE 11. Please download and install a suitable version of Java on your system before building Javar.

### Windows

```
> build.bat && run.bat
```

### macOS

```
$ cd Javar.CodEditor
$ cp ./configs/properties/Init.properties ./configs/properties/Javar.properties
$ mkdir -p build && cd build && -p mkdir c_lib && cd c_lib
$ gcc -c ../../src/c_src/parser.c
$ gcc -dynamiclib -shared -o ./libparser.dylib parser.o
$ cd .. && javac -d . ../src/*.java
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

```
py example.py
```

### HTML

An example.html should be rendered well with your browser.

## Other Information About Javar

### Supported Languages

* English

* 简体中文


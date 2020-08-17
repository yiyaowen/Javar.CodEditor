# Javar.Project

[TOC]

![generalIcon](https://tva1.sinaimg.cn/large/007S8ZIlgy1ghu4wcmd2lj30go05kgmq.jpg)

**Javar** is a lightweight and powerful coder for C/C++, Java, Python and HTML. 

## Build

Javar has been built and tested with Java SE 11. Please download and install a suitable version of Java on your system before building Javar.

### Windows

```
> cd Javar.Project
> copy Init.properties Javar.properties
> mkdir javar
> javac -encoding utf-8 -d . *.java
> java javar.Javar
```

### MacOS

```
$ cd Javar.Project
$ cp Init.properties Javar.properties
$ mkdir javar
$ javac -d . *.java
$ java javar.Javar
```

## Compile/Run/Render Requirement

To compile, run or render these source files using Javar, you should make sure that C/C++, Java and Python can be compiled/run with your command prompt or terminal, and that HTML can be rendered under general HTML standard. That is, the following commands must work well on your system.

### C/C++

For Windows and MacOS, they are the same most of time.

```
# Windows
gcc -std=c11 -o example example.c
g++ -std=c++11 -o example example.cpp

# MacOS
gcc -std=c11 -o example example.c
g++ -std=c++11 -o example example.cpp
```

### Java

[cmd] means that it is optional.

```
# Windows
javac -encoding utf-8 -d . example.java

# MacOS
javac [-encoding utf-8] -d . example.java
```

### Python

Only support Python3.x.

```
python3 example.py
```

### HTML

A example.html should be rendered well with your browser.

## Other Information About Javar

### App Icon

![app](https://tva1.sinaimg.cn/large/007S8ZIlgy1ghu5i2a0buj30e80e8q6l.jpg)

### Supported Languages

* English

* 简体中文


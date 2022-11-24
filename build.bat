@echo off
cd ./configs/properties
copy Init.properties Javar.properties
cd ../..
mkdir build 2>NUL
cd build
mkdir c_lib 2>NUL
cd c_lib
cl /LD /I %JAVA_INCLUDE% /I %JAVA_INCLUDE%\win32 ../../src/c_src/parser.c
cd ..
javac -d . ../src/*.java -encoding utf-8
cd ..

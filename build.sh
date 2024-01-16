#!/bin/sh

cd plugin
javac -Xlint:all -encoding UTF-8 @org/vcssl/connect/sourcelist.txt
javac -Xlint:all -encoding UTF-8 @org/vcssl/nano/plugin/sourcelist.txt
cd ..


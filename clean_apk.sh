#!/bin/bash

find . -name "*-unaligned.apk" | xargs rm -rf
find . -name "*-debug.apk" | xargs rm -rf
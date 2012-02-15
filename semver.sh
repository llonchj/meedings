#!/bin/bash

# This script shows a semver git repo version 
# Courtesy: http://semver.org and "breakdown-git-version-number"

#      o----- major release: 1.7
#     / o---- feature release: 1.7.3
#    / / o--- maintenance release: 1.7.3.2
#   / / /
#  1.7.3.2

#         o------------- most recent reachable tag: 1.7.3.2
#        /          o--- 'g' + abbreviated object name of built commit: 6f10c
#       /          /
# 1.7.3.2.164.g6f10c
#           \
#            o---------- number of commits in built commit "on top" of tag: 164

# git tag -am "Neo causes their bullets to freeze in mid-air" 0.0.3

version=`git describe --always`                                # last tag version 
build=`git log $version -n 1 --oneline | awk '{ print $1 }'`   # built commit
commits=`git log --pretty=oneline | wc -l `                    # number of commits

echo "$version.$commits.$build"


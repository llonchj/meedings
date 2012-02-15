#!/bin/bash

# This script makes your remote git repo storage encrypted  
# by openssl enc -base64 -aes-256-ecb 
# $> sh encryption.sh 
# Courtesy: http://syncom.appspot.com/papers/git_encryption.txt

# make some keys $> openssl enc -aes-256-cbc -k frankvilhelmsen -P -md sha1
 salt=ED8F1737EF4A2E8C
 key=4D3D4B7A723034258E6D20F110475746D2F77634A76402941B5DEA2E6084F966
 # iv=EDF7AB93C5DF4812A8242253D21EE426
 
# file .gitencrypt/clean_filter_openssl
mkdir -p .gitencrypt ; touch .gitencrypt/clean_filter_openssl
echo "#!/bin/bash 
  openssl enc -base64 -aes-256-ecb -S $salt -k $key 
  " > .gitencrypt/clean_filter_openssl

# file .gitencrypt/diff_filter_openssl
touch .gitencrypt/diff_filter_openssl

echo "#!/bin/bash
  openssl enc -d -base64 -aes-256-ecb -k $key -in \"\$1\" 2> /dev/null || cat \"\$1\" 
  " > .gitencrypt/diff_filter_openssl

# file .gitencrypt/smudge_filter_openssl
touch .gitencrypt/smudge_filter_openssl
echo "#!/bin/bash
  openssl enc -d -base64 -aes-256-ecb -k $key 2> /dev/null || cat 
  " > .gitencrypt/smudge_filter_openssl

# file .gitattributes
touch .gitattributes
echo " *    filter=openssl    diff=openssl
[merge]
     renormalize = true " > .gitattributes

# file .git/config
mkdir -p .git ; touch .git/config
echo "
[filter \"openssl\"]
    smudge = .gitencrypt/smudge_filter_openssl
    clean  = .gitencrypt/clean_filter_openssl
[diff \"openssl\"]
    textconv = .gitencrypt/diff_filter_openssl
" >> .git/config

echo "Add and commit a diff and push to remote as encrypted" 
echo ".. share key/salt between your valid co-workers.. " 

#!/bin/bash

RET=1
while [[ RET -ne 0 ]]; do
    echo "=> Waiting for confirmation of MongoDB service startup"
    sleep 5
    mongo admin --eval "help" >/dev/null 2>&1
    RET=$?
done

mongo admin << EOF
use authorization
db.createUser({user: 'user', pwd: 'pass', roles:[{role:'readWrite',db:'authorization'}]})
EOF

mongo admin << EOF
use product
db.createUser({user: 'user', pwd: 'pass', roles:[{role:'readWrite',db:'product'}]})
EOF


echo "=> Done!"
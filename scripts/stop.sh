#!/bin/sh
[[ -e /etc/init/dw-helloworld.conf ]] \
&& status dw-helloworld | \
grep -q '^dw-helloworld start/running, process' \
&& [[ $? -eq 0 ]] \
&& stop dw-helloworld || echo "Application not started"


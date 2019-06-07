#!/bin/bash

sed -i~ "/<servers>/ a\
<server>\
  <id>sytm-nexus</id>\
  <username>${SYTM_NEXUS_USERNAME}</username>\
  <password>${SYTM_NEXUS_PASSWORD}</password>\
</server>" /usr/share/maven/conf/settings.xml
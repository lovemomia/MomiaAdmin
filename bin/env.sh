#!/bin/bash

JAVA_OPTS="-Xms512M -Xmx512M -Xmn64M \
	-XX:PermSize=64M -XX:MaxPermSize=64M \
	-XX:+UseConcMarkSweepGC -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=75 \
	-XX:+PrintGCDetails -XX:+PrintGCDateStamps -verbose:gc -XX:+DisableExplicitGC \
	-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9999"

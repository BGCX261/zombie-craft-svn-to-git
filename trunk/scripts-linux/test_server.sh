#!/bin/bash

source setup.sh

javac -help >/dev/null 2>/dev/null
if [ $? -ne 0 ]
then
  echo "Unable to locate java."
  exit 1
fi

echo "=== Minecraft Coder Pack $MCPVERSION ==="

cd $MCPBINDIR
java -Xmx1024M -Xms1024M -cp $MCSTESTCP net.minecraft.server.MinecraftServer

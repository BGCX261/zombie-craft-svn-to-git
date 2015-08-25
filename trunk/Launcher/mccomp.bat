@echo off
set p_7z="N:\programs\7za.exe"
set mcjar="C:\Users\Corosus\AppData\Roaming\.minecraft\ZombieCraftLaunch.jar"
set classes="%mcjar%;N:\dev\Game Dev\minecraft\comp\lwjgl-2.6\jar\lwjgl.jar;"

@echo on
javac -classpath %classes% -target 1.5 %1.java
@echo off

%p_7z% a %mcjar% %1.class
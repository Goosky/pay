pay
===

pay use union and alipay

++++++++++++++++++
Project info:

the base project:
SimulateGameServer:
send the buy message[union pay or ali pay] to pay server 

SimulateUnionServer:
simulate the union server

DatabaseServer project:
modify the data by the modify message from payServer[pay project]


pay project:

check the pay type and deal it. if success send modify the data message to DatabaseServer by SyncMessage way


+++++++++++++++++++++++++++++
create Java+MySQL+Tomcat+Struts2.x
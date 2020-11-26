# HotelReservation

Versions des technologies (les mêmes que dans les TP):
**Java**: oracle 8 
**axis**: axis2-1.5.1
**tomcat**: apache v.7
**Javafx**: jxrft.jar inclus dans la version du java 8
**restlet**: restlet-jee-2.0.6

Inclure les projets sur Eclipse JEE
Les librairies sont inclus dans le dossier /lib, faire attention aux chemins des librairies dans le build path

Lancez **WS_HotelReservation** (WS SOAP) sur le serveur tomcat inclus dans le dossier Servers (port 8081).

Lancez **WS_HotelFiltering** (WS REST) en java application (port 8182). Le main se trouve dans src/restServer/RESTDistributor.java

Lancez **WS_HotelAuthentification** (WS REST) en java application (port 8183). Le main se trouve dans src/serverconfig/RESTDistributor.java

Lancez **CLIENT_HotelReservation** en java application. Le main se trouve dans src/main/LaunchApplication.java

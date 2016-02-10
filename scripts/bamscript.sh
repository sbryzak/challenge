#!/bin/sh

# DONT RUN THIS, JUST COPY/PASTE pieces you want

wget http://download.jboss.org/wildfly/10.0.0.Final/wildfly-10.0.0.Final.zip
unzip wildfly-10.0.0.Final.zip
wget http://downloads.jboss.org/dashbuilder/release/6.3.0.Final/dashbuilder-6.3.0.Final-wildfly8.war
wget http://central.maven.org/maven2/org/mariadb/jdbc/mariadb-java-client/1.1.7/mariadb-java-client-1.1.7.jar
mkdir wildfly-10.0.0.Final/modules/com/
mkdir wildfly-10.0.0.Final/modules/com/mariadb/
mkdir wildfly-10.0.0.Final/modules/com/mariadb/main
mv mariadb-java-client-1.1.7.jar wildfly-10.0.0.Final/modules/com/mariadb/main
cp github/ds_module.xml wildfly-10.0.0.Final/modules/com/mariadb/main/module.xml
cp github/standalone.xml wildfly-10.0.0.Final/standalone/configuration/standalone.xml 
cp dashbuilder-6.3.0.Final-wildfly8.war wildfly-10.0.0.Final/standalone/deployments



#------------- FOR use with BPMS
unzip jboss-bpmsuite-6.2.0.GA-deployable-eap6.x.zip
wget http://central.maven.org/maven2/org/mariadb/jdbc/mariadb-java-client/1.1.7/mariadb-java-client-1.1.7.jar
mkdir jboss-eap-6.4/modules/com/
mkdir jboss-eap-6.4/modules/com/mariadb/
mkdir jboss-eap-6.4/modules/com/mariadb/main
mv mariadb-java-client-1.1.7.jar jboss-eap-6.4/modules/com/mariadb/main
cp github/ds_module.xml jboss-eap-6.4/modules/com/mariadb/main/module.xml
cp github/bpms_standalone.xml jboss-eap-6.4/standalone/configuration/standalone.xml 


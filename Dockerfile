FROM quay.io/wildfly/wildfly:26.1.2.Final
COPY target/*.war /opt/jboss/wildfly/standalone/deployments/
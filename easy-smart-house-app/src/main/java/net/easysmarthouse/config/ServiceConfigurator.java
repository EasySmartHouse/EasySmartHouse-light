package net.easysmarthouse.config;

public class ServiceConfigurator {

    private final String serviceHost;
    private final long servicePort;

    public ServiceConfigurator(String serviceHost, long servicePort) {
        this.serviceHost = serviceHost;
        this.servicePort = servicePort;
    }

    public String getServiceUrl(String serviceName) {
        return String.format("rmi://%s:%d/%s", serviceHost, servicePort, serviceName);
    }
}

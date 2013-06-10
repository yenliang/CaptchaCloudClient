/**
 * HiCaptchaLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tw.com.chttl.hicaptcha;

public class HiCaptchaLocator extends org.apache.axis.client.Service implements tw.com.chttl.hicaptcha.HiCaptcha {

    public HiCaptchaLocator() {
    }


    public HiCaptchaLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public HiCaptchaLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for HiCaptchaHttpSoap11Endpoint
    private java.lang.String HiCaptchaHttpSoap11Endpoint_address = "http://localhost:8080/HiCaptcha/services/HiCaptcha.HiCaptchaHttpSoap11Endpoint/";

    public java.lang.String getHiCaptchaHttpSoap11EndpointAddress() {
        return HiCaptchaHttpSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String HiCaptchaHttpSoap11EndpointWSDDServiceName = "HiCaptchaHttpSoap11Endpoint";

    public java.lang.String getHiCaptchaHttpSoap11EndpointWSDDServiceName() {
        return HiCaptchaHttpSoap11EndpointWSDDServiceName;
    }

    public void setHiCaptchaHttpSoap11EndpointWSDDServiceName(java.lang.String name) {
        HiCaptchaHttpSoap11EndpointWSDDServiceName = name;
    }

    public tw.com.chttl.hicaptcha.HiCaptchaPortType getHiCaptchaHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(HiCaptchaHttpSoap11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getHiCaptchaHttpSoap11Endpoint(endpoint);
    }

    public tw.com.chttl.hicaptcha.HiCaptchaPortType getHiCaptchaHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            tw.com.chttl.hicaptcha.HiCaptchaSoap11BindingStub _stub = new tw.com.chttl.hicaptcha.HiCaptchaSoap11BindingStub(portAddress, this);
            _stub.setPortName(getHiCaptchaHttpSoap11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setHiCaptchaHttpSoap11EndpointEndpointAddress(java.lang.String address) {
        HiCaptchaHttpSoap11Endpoint_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (tw.com.chttl.hicaptcha.HiCaptchaPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                tw.com.chttl.hicaptcha.HiCaptchaSoap11BindingStub _stub = new tw.com.chttl.hicaptcha.HiCaptchaSoap11BindingStub(new java.net.URL(HiCaptchaHttpSoap11Endpoint_address), this);
                _stub.setPortName(getHiCaptchaHttpSoap11EndpointWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("HiCaptchaHttpSoap11Endpoint".equals(inputPortName)) {
            return getHiCaptchaHttpSoap11Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://hicaptcha.chttl.com.tw", "HiCaptcha");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://hicaptcha.chttl.com.tw", "HiCaptchaHttpSoap11Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("HiCaptchaHttpSoap11Endpoint".equals(portName)) {
            setHiCaptchaHttpSoap11EndpointEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}

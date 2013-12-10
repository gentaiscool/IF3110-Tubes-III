/**
 * AddBarangServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package helloJsp.AddBarang;

public class AddBarangServiceLocator extends org.apache.axis.client.Service implements helloJsp.AddBarang.AddBarangService {

    public AddBarangServiceLocator() {
    }


    public AddBarangServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AddBarangServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AddBarang
    private java.lang.String AddBarang_address = "http://localhost:8080/Chintalian/services/AddBarang";

    public java.lang.String getAddBarangAddress() {
        return AddBarang_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AddBarangWSDDServiceName = "AddBarang";

    public java.lang.String getAddBarangWSDDServiceName() {
        return AddBarangWSDDServiceName;
    }

    public void setAddBarangWSDDServiceName(java.lang.String name) {
        AddBarangWSDDServiceName = name;
    }

    public helloJsp.AddBarang.AddBarang getAddBarang() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AddBarang_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAddBarang(endpoint);
    }

    public helloJsp.AddBarang.AddBarang getAddBarang(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            helloJsp.AddBarang.AddBarangSoapBindingStub _stub = new helloJsp.AddBarang.AddBarangSoapBindingStub(portAddress, this);
            _stub.setPortName(getAddBarangWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAddBarangEndpointAddress(java.lang.String address) {
        AddBarang_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (helloJsp.AddBarang.AddBarang.class.isAssignableFrom(serviceEndpointInterface)) {
                helloJsp.AddBarang.AddBarangSoapBindingStub _stub = new helloJsp.AddBarang.AddBarangSoapBindingStub(new java.net.URL(AddBarang_address), this);
                _stub.setPortName(getAddBarangWSDDServiceName());
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
        if ("AddBarang".equals(inputPortName)) {
            return getAddBarang();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://SOAP.helloJsp", "AddBarangService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://SOAP.helloJsp", "AddBarang"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AddBarang".equals(portName)) {
            setAddBarangEndpointAddress(address);
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

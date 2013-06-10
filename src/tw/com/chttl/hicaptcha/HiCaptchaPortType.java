/**
 * HiCaptchaPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tw.com.chttl.hicaptcha;

public interface HiCaptchaPortType extends java.rmi.Remote {
    public java.lang.String getCaptcha(java.lang.String clientToken, java.lang.String clientSign, java.lang.Integer length, java.lang.Integer OCRlevel) throws java.rmi.RemoteException;
    public java.lang.String getNumericCaptcha(java.lang.String clientToken, java.lang.String clientSign, java.lang.Integer length, java.lang.Integer OCRlevel) throws java.rmi.RemoteException;
}

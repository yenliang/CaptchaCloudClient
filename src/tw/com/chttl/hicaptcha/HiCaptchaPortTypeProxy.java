package tw.com.chttl.hicaptcha;

import java.rmi.RemoteException;

public class HiCaptchaPortTypeProxy implements tw.com.chttl.hicaptcha.HiCaptchaPortType {
  private String _endpoint = null;
  private tw.com.chttl.hicaptcha.HiCaptchaPortType hiCaptchaPortType = null;
  
  public HiCaptchaPortTypeProxy() {
    _initHiCaptchaPortTypeProxy();
  }
  
  public HiCaptchaPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initHiCaptchaPortTypeProxy();
  }
  
  private void _initHiCaptchaPortTypeProxy() {
    try {
      hiCaptchaPortType = (new tw.com.chttl.hicaptcha.HiCaptchaLocator()).getHiCaptchaHttpSoap11Endpoint();
      if (hiCaptchaPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)hiCaptchaPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)hiCaptchaPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (hiCaptchaPortType != null)
      ((javax.xml.rpc.Stub)hiCaptchaPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public tw.com.chttl.hicaptcha.HiCaptchaPortType getHiCaptchaPortType() {
    if (hiCaptchaPortType == null)
      _initHiCaptchaPortTypeProxy();
    return hiCaptchaPortType;
  }

	@Override
	public String getCaptcha(String clientToken, String clientSign, Integer length,
			Integer OCRlevel) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getNumericCaptcha(String clientToken, String clientSign,
			Integer length, Integer OCRlevel) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
  
  
}
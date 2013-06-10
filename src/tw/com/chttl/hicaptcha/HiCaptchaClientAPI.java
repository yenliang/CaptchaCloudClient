package tw.com.chttl.hicaptcha;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;

import com.sun.org.apache.xml.internal.security.exceptions.*;
import com.sun.org.apache.xml.internal.security.utils.*;
import cht.ccsdk.bean.AuthTokenBean;
import cht.ccsdk.proxy.ServiceManager;
	//import tw.com.chttl.hikeygen.*;

	public class HiCaptchaClientAPI{
		private String account = null;
		private String serviceid = null;
		private String isvkey = null;
		//private String token = null;
		//private String sign = null;
		
		private String ServiceApi_address = "http://cht-captcha-api.hicloud.net.tw:8080/CaptchaCloud/services/HiCaptcha.HiCaptchaHttpSoap11Endpoint/";

		private String result = null;
		
		/**
		 * Constructor with parameter
		 * @param clientAccount
		 * @param clientServiceid
		 * @param clientIsvkey
		 */
		public HiCaptchaClientAPI(String clientAccount, String clientServiceid, String clientIsvkey){
			this.account = clientAccount;
			this.serviceid = clientServiceid;
			this.isvkey = clientIsvkey;
		}
		/**
		 * Constructor without parameter
		 */
		public HiCaptchaClientAPI(){}
		
		/**
		 * Set VA address such as ""http://localhost:8080/CaptchaCloud/services/HiCaptcha.HiCaptchaHttpSoap11Endpoint/"
		 * @param val
		 */
		public void setVaAddress(String val){
			this.ServiceApi_address=val;
		}
		/**
		 * Set application account
		 * @param val
		 */
		public void setAccount(String val){
			this.account=val;
		}
		/**
		 * Set application service id
		 * @param val
		 */
		public void setServiceid(String val){
			this.serviceid=val;
		}
		/**
		 * Set application password
		 * @param val
		 */
		public void setIsvKey(String val){
			this.isvkey=val;
		}
		
		////////////////////////////////////////////////////
		////		HiCaptcha Implementation				////
		////////////////////////////////////////////////////

		////////////////////////////////////////////////////
		///////////		generate HiCaptcha	////////////////
		////////////////////////////////////////////////////
		/**
		 * Generate HiCaptcha
		 * @param length Length of Captcha
		 * @param OCRlevel Captcha Difficulty
		 * @return Captcha String
		 * @throws ServiceException 
		 * @throws RemoteException 
		 */
		
		public String getHiCaptcha(int length, int OCRlevel) throws ServiceException, RemoteException{//to captcha
				
				AuthTokenBean authToken =  getAuthToken();
				HiCaptchaLocator locator = new HiCaptchaLocator();
				HiCaptchaPortType hcpt = null;
				locator.setHiCaptchaHttpSoap11EndpointEndpointAddress(ServiceApi_address);
				hcpt = locator.getHiCaptchaHttpSoap11Endpoint();
				//this.result = hcpt.getCaptcha(authToken.getToken(), authToken.getSign(), length, OCRlevel);
				result = hcpt.getCaptcha(authToken.getToken(), authToken.getSign(), length, OCRlevel);
				
				return result;
		}
		
		////////////////////////////////////////////////////
		////		HiNumericCaptcha Implementation				////
		////////////////////////////////////////////////////

		////////////////////////////////////////////////////
		///////////		generate HiNumericCaptcha	////////////////
		////////////////////////////////////////////////////
		/**
		 * Generate HiNumericCaptcha
		 * @param length Length of NumericCaptcha
		 * @param OCRlevel NumericCaptcha Difficulty
		 * @return NumericCaptcha String
		 * @throws ServiceException 
		 * @throws RemoteException 
		 */
		
		public String getHiNumericCaptcha(int length, int OCRlevel) throws ServiceException, RemoteException{//to captcha
				
				AuthTokenBean authToken =  getAuthToken();
				HiCaptchaLocator locator = new HiCaptchaLocator();
				HiCaptchaPortType hcpt = null;
				locator.setHiCaptchaHttpSoap11EndpointEndpointAddress(ServiceApi_address);
				hcpt = locator.getHiCaptchaHttpSoap11Endpoint();								
				result = hcpt.getNumericCaptcha(authToken.getToken(), authToken.getSign(), length, OCRlevel);
				return result;
		}
		
		// Authentication Utility
		/**
		 * get AuthToken
		 * @param 
		 * @return AuthTokenBean
		 */
		private AuthTokenBean getAuthToken(){
			String timestamp = Long.toString(ServiceManager.genTimestamp());
			String nonce = ServiceManager.genNonce();
			//Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.160.3.88", 8080));
		//ServiceManager srvMgr = new ServiceManager("http","hiapi.ext.hipaas.hinet.net",80,null);
			ServiceManager srvMgr = new ServiceManager("http","api.hicloud.hinet.net",80,null);   //####
			
			//ServiceManager srvMgr = new ServiceManager("http","lab.api.hicloud.hinet.net",80,null);			
			String signCl = ServiceManager.genSign(isvkey + nonce + timestamp,
					"SHA");
			AuthTokenBean authToken = srvMgr.requestToken(account, serviceid,
					nonce, timestamp, signCl);// tokenBean���^�Ǥ�token�Psign
		
			if(srvMgr.getResultCode()!=0){
				this.result="<Returns><ErrorCode>"+srvMgr.getResultCode()+"</ErrorCode><ErrorMsg>"+srvMgr.getErrMsg()+"</ErrorMsg></Returns>";
				return null;
			}
			// test IF_B
			// assuming sending token and sign to service provider
			//this.sign = authToken.getSign();
			//this.token = authToken.getToken();
			//return "sign="+sign+";token="+token;
			return authToken;
		}
		
		//Tools
		/**
		 * Turn byte data as hex string
		 * @param buf buffer of byte data
		 * @return HEX string
		 */
		public String asHex(byte buf[]) {
			StringBuffer strbuf = new StringBuffer(buf.length * 2);
			int i;

			for (i = 0; i < buf.length; i++) {
				if (((int) buf[i] & 0xff) < 0x10)
					strbuf.append("0");
				strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
			}
			return strbuf.toString().toUpperCase();
		}
		
		//XML parser
		/**
	     * Get result of KeyValue from XML format
	     * @return byte of KeyValue
		 * @throws Base64DecodingException 
	     */
		public byte[] getKeyValue() throws Base64DecodingException {
			return Base64.decode(getResult("KeyValue"));
		}
		/**
	     * Get result of KeyFile(.der) from XML format
	     * @return byte of KeyFile
		 * @throws Base64DecodingException 
	     */
		public byte[] getKeyFile() throws Base64DecodingException {
	    	return  Base64.decode(getResult("KeyFile"));//this.result.substring(start,end) ;
		}
		
		public byte[] getImgFile()  {
	    	try {
				return  Base64.decode(getResult("CaptchaImage"));
			} catch (Base64DecodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//this.result.substring(start,end) ;
			return null;
		}
		
		public String getCaptchaAnswer(){
	    	if(this.result==null) return null;
	    	String code=getResult("CaptchaAnswer");
	    	if(code!=null) return code;
	    	return null;
	    }
	    /**
	     * Get the return attribute of the name
	     * @param name the name of attribute
	     * @return value of attribute
	     */
		public String getResult(String name){
	    	if(this.result==null) return null;
	    	int start=this.result.indexOf("<"+name+">");
	    	if(start==-1) return null;    	int end=this.result.indexOf("</"+name+">");
	    	if(end==-1) return null;
	    	start=start+2+name.length();
	    	return this.result.substring(start,end);
	    }
	    /**
	     * Get the whole string result in XML format
	     * @return whole string get from service
	     */
	    public String getResult(){
	    	return this.result;
	    }
	    /**
	     * Get the error code
	     * @return ErrorCode
	     */
	    public int getErrorCode(){
	    	if(this.result==null) return 0;
	    	String code=getResult("ErrorCode");
	    	if(code!=null) return Integer.parseInt(code);
	    	return 0;
	    }
	    /**
	     * Get the error message
	     * @return ErrorMsg
	     */
	    public String getErrorMsg(){
	    	if(this.result==null) return null;
	    	String code=getResult("ErrorMsg");
	    	if(code!=null) return code;
	    	return null;
	    }
	}



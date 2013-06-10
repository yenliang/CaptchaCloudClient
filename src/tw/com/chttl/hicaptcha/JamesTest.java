package tw.com.chttl.hicaptcha;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

public class JamesTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int Length = 4; // 4~10
		int OCRLevel = 1; // 0~3
		
		HiCaptchaClientAPI hct = new HiCaptchaClientAPI("ISVTEST", "1", "sliTgaYmYX1qU1CLY7AvUQ==");
		hct.setVaAddress("http://pki.hinet.net/CaptchaCloud/services/HiCaptcha.HiCaptchaHttpSoap11Endpoint/");
		//http://pki.hinet.net/CaptchaCloud/services/HiCaptcha.HiCaptchaHttpSoap11Endpoint/
	//	"http://pki.hinet.net/HiPKIService/services/ServiceApi"
		try {
			//hct.getHiCaptcha (Length, OCRLevel); 
			
			hct.getHiNumericCaptcha (Length, OCRLevel); 
			
			
		} catch (ServiceException e1) {
			e1.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

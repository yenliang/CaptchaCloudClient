/**
 * HiCaptcha.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tw.com.chttl.hicaptcha;

//import java.awt.*;
//import java.awt.image.*;
import java.io.*;

//import javax.imageio.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.xml.rpc.ServiceException;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
//import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;

@WebServlet(name="HiCaptchaWebServlet", urlPatterns={"/HiCaptchaWebServlet"},loadOnStartup=1)
public class HiCaptchaWebServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("image/png");
        int Length = 6; // 4~10
		int OCRLevel = 2; // 0~3
				
		HiCaptchaClientAPI hct = new HiCaptchaClientAPI("367f7deaa1ce47b185a0c91cb6d8f714", "57", "n+ABj+1w6e1Ht2A2ziBh0Q==");
		//ISVTEST 開發商需自行修改為自己的帳號
		//sliTgaYmYX1qU1CLY7AvUQ== 開發商需自行修改為自己的SDK secretKey
		
		hct.setVaAddress("http://pki.hinet.net/CaptchaCloud/services/HiCaptcha.HiCaptchaHttpSoap11Endpoint/");

		try {
			hct.getHiCaptcha (Length, OCRLevel); // 產生英文數字夾雜的結果
			
		//	hct.getHiNumericCaptcha (Length, OCRLevel);// 產生純數字的結果 
			
			
		} catch (ServiceException e1) {
			e1.printStackTrace();
			e1.printStackTrace(new PrintStream(response.getOutputStream()));
			response.getOutputStream().write("error".getBytes());
			response.getOutputStream().flush();
			
			return;
		}
		
		if( hct.getErrorCode() == 0){
		//	System.out.println("CaptchaAnswer: " + hct.getCaptchaAnswer());			
			
			HttpSession session = request.getSession(true);
		    session.setAttribute("ans" , hct.getCaptchaAnswer()); // set "ans"  variable for captchasubmit.jsp used
			
			//write to file: 
			byte[] captchaImgBytes = null;
			
				captchaImgBytes = hct.getImgFile();

			BufferedImage imag=ImageIO.read(new ByteArrayInputStream(captchaImgBytes));
			
			try {
						  
					  OutputStream out = response.getOutputStream();
				        ImageIO.write(imag, "PNG", out);
				        out.close();
				        
				} catch (IOException e) {
					e.printStackTrace();
				}			 
		}
		else{
			System.out.println("Error Code: " + hct.getErrorCode());
			System.out.println("Error Message: " + hct.getErrorMsg());
		}    
    }
}

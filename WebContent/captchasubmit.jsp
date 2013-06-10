<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cht.paas.util.CloudLogger"%>
<%@ page import="cht.paas.util.LogLevel"%>
<%@ page import="cht.paas.hiair.sms.sdk.SmsSDK"%>
<%@ page import="cht.paas.hiair.sms.sdk.utility.IReturnCode"%>
<%@ page import="cht.paas.hiair.sms.bean.*"%>
<%
	request.setCharacterEncoding("UTF-8");

	//check 驗證碼
	String answer = request.getParameter("answer");
	String sessAns = (String) session.getAttribute("ans");
	try {
		if (answer.length() > 1 && answer.compareToIgnoreCase(sessAns)==0) {
			//傳送簡訊
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			
			String isv = "367f7deaa1ce47b185a0c91cb6d8f714";
			String isvkey="n+ABj+1w6e1Ht2A2ziBh0Q==";
			String phone=request.getParameter("tel") ;
			String txt1=request.getParameter("msgtxt") ;
			String strMsgid="";
			SmsSDK sdk = new SmsSDK();
			sdk.initIsvAccount(isv, isvkey);
			strMsgid = sdk.getSendService(phone, txt1,"",15);
%>
{"status":"200"}
<%
	} else {
%>
{"status":"400"}
<%
	}
	} catch (Exception e1) {
		e1.printStackTrace();
%>
{"status":"600"}
<%
	}
%>



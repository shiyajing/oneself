package com.oneself.cloud.provider.qrcode.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oneself.cloud.provider.qrcode.util.QRCodeUtil;

/**
 * @author shiyajing
 * @E-mail shiyj@agree.com.cn
 * @version 2017年3月10日下午2:45:28
 */
@RestController
public class QrcodeGeneralController {

	@RequestMapping("/qtest")
	public @ResponseBody void show(HttpServletResponse rsp){
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			QRCodeUtil.encode("shiyajing",output);
			rsp.getOutputStream().write(output.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

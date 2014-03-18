package com.cardinfolink.vtsh.validationcode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.cardinfolink.vtsh.action.BaseAction;
import com.cardinfolink.vtsh.util.ImageUtil;


public class ImageAction extends BaseAction{
	//输出属性，是一个图片的输入流。
	private InputStream imageStream;
	
	//生成验证码图片，赋值给输出属性。
	public String execute(){
		//1.生成验证码图片
		Map<String,BufferedImage> imageMap = ImageUtil.createImage();
		//2.从图片对象中找出验证码，放入session
		String imageCode =imageMap.keySet().iterator().next();
		Map<String,Object> session = getSession();
		session.put("imageCode", imageCode);
		//3.将图片转换成流，赋值给输出属性
		BufferedImage image = imageMap.get(imageCode);
		try {
			imageStream = ImageUtil.getInputStream(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//返回字符串，指定Result	
		return "success";
	}

	public InputStream getImageStream(){
		return imageStream;
	}

	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}

}

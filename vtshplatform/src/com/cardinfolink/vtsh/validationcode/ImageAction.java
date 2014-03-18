package com.cardinfolink.vtsh.validationcode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.cardinfolink.vtsh.action.BaseAction;
import com.cardinfolink.vtsh.util.ImageUtil;


public class ImageAction extends BaseAction{
	//������ԣ���һ��ͼƬ����������
	private InputStream imageStream;
	
	//������֤��ͼƬ����ֵ��������ԡ�
	public String execute(){
		//1.������֤��ͼƬ
		Map<String,BufferedImage> imageMap = ImageUtil.createImage();
		//2.��ͼƬ�������ҳ���֤�룬����session
		String imageCode =imageMap.keySet().iterator().next();
		Map<String,Object> session = getSession();
		session.put("imageCode", imageCode);
		//3.��ͼƬת����������ֵ���������
		BufferedImage image = imageMap.get(imageCode);
		try {
			imageStream = ImageUtil.getInputStream(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//�����ַ�����ָ��Result	
		return "success";
	}

	public InputStream getImageStream(){
		return imageStream;
	}

	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}

}

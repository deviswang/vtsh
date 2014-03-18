package com.cardinfolink.vtsh.validationcode;

import com.cardinfolink.vtsh.action.BaseAction;

public class ValidateCheckCodeAction extends BaseAction{
	//��ȡ�û��������֤����Ϣ
	private String userCode;
	//����������ԣ��Ƿ���֤�ɹ�
	private Boolean checkPassed;
	
	public String execute(){
		System.out.println(userCode);
		String checkCode=(String)getSession().get("imageCode");
		if(userCode==null || !checkCode.equalsIgnoreCase(userCode)){
			checkPassed=false;
		}else{
			checkPassed=true;
		}
		return "success";
	}
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Boolean getCheckPassed() {
		return checkPassed;
	}
	public void setCheckPassed(Boolean checkPassed) {
		this.checkPassed = checkPassed;
	}
	
}

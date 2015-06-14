package kr.ac.mju.exception;

import kr.ac.mju.constants.CConstants.EErrorCodes;

public class NotCorrectUserPasswordException extends Exception {
	private static final long serialVersionUID = 1L;
	private int errorCode = EErrorCodes.NoExistUserPassword.getErrorCode();
	private String errorName = EErrorCodes.NoExistUserPassword.toString();
	public int getErrorCode() {
		return this.errorCode;
	}
	public String getErrorName() {
		return this.errorName;
	}
	
}

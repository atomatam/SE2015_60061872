package kr.ac.mju.exception;

import kr.ac.mju.constants.CConstants.EErrorCodes;

public class NotCorrectUserIDException extends Exception {
	private static final long serialVersionUID = 1L;
	private int errorCode = EErrorCodes.NoExistUserID.getErrorCode();
	private String errorName = EErrorCodes.NoExistUserID.toString();
	public int getErrorCode() {
		return this.errorCode;
	}
	public String getErrorName() {
		return this.errorName;
	}
}

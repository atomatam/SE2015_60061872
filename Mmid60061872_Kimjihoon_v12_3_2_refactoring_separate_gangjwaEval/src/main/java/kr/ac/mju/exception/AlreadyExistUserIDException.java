package kr.ac.mju.exception;

import kr.ac.mju.constants.CConstants.EErrorCodes;

public class AlreadyExistUserIDException extends Exception{
	private static final long serialVersionUID = 1L;
	private int errorCode = EErrorCodes.AlreadyExistUserID.getErrorCode();
	private String errorName = EErrorCodes.AlreadyExistUserID.toString();
	public int getErrorCode() {
		return this.errorCode;
	}
	public String getErrorName() {
		return this.errorName;
	}
}

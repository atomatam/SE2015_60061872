package kr.ac.mju.exception;

import kr.ac.mju.constants.CConstants.EErrorCodes;

public class NotInputDataException extends Exception{
	private static final long serialVersionUID = 1L;
	private int errorCode = EErrorCodes.NotInputData.getErrorCode();
	private String errorName = EErrorCodes.NotInputData.toString();
	public int getErrorCode() {
		return this.errorCode;
	}
	public String getErrorName() {
		return this.errorName;
	}
}

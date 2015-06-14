package kr.ac.mju.exception;

import kr.ac.mju.constants.CConstants.EErrorCodes;

public class OverSizeInputException extends Exception {
	private static final long serialVersionUID = 1L;
	private int errorCode = EErrorCodes.OverSize.getErrorCode();
	private String errorName = EErrorCodes.OverSize.toString();
	public int getErrorCode() {
		return this.errorCode;
	}
	public String getErrorName() {
		return this.errorName;
	}
}

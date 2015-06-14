package kr.ac.mju.exception;

import kr.ac.mju.constants.CConstants.EErrorCodes;

public class NotInputNumberException extends Exception {
	private static final long serialVersionUID = 1L;
	private int errorCode = EErrorCodes.NotCorrectInput.getErrorCode();
	private String errorName = EErrorCodes.NotCorrectInput.toString();
	public int getErrorCode() {
		return this.errorCode;
	}
	public String getErrorName() {
		return this.errorName;
	}
}

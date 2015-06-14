package kr.ac.mju.exception;

import kr.ac.mju.constants.CConstants.EErrorCodes;

public class NoExistGwamokException extends Exception {
	private static final long serialVersionUID = 1L;
	private int errorCode = EErrorCodes.NoExistGwamok.getErrorCode();
	private String errorName = EErrorCodes.NoExistGwamok.toString();
	public int getErrorCode() {
		return this.errorCode;
	}
	public String getErrorName() {
		return this.errorName;
	}
}

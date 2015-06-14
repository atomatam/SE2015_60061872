package kr.ac.mju.exception;

import kr.ac.mju.constants.CConstants.EErrorCodes;

public class AlreadyExistGwamokException extends Exception {
	private static final long serialVersionUID = 1L;
	private int errorCode = EErrorCodes.AlreadyExistGwamok.getErrorCode();
	private String errorName = EErrorCodes.AlreadyExistGwamok.toString();
	public int getErrorCode() {
		return this.errorCode;
	}
	public String getErrorName() {
		return this.errorName;
	}
}

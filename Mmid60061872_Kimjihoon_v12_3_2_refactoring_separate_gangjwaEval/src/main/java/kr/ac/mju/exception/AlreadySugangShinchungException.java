package kr.ac.mju.exception;

import kr.ac.mju.constants.CConstants.EErrorCodes;

public class AlreadySugangShinchungException extends Exception {
	private static final long serialVersionUID = 1L;
	private int errorCode = EErrorCodes.AlreadySugangShinchung.getErrorCode();
	private String errorName = EErrorCodes.AlreadySugangShinchung.toString();
	public int getErrorCode() {
		return this.errorCode;
	}
	public String getErrorName() {
		return this.errorName;
	}
}

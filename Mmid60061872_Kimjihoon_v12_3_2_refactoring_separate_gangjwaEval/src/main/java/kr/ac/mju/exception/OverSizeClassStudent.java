package kr.ac.mju.exception;

import kr.ac.mju.constants.CConstants.EErrorCodes;

public class OverSizeClassStudent extends Exception {
	private static final long serialVersionUID = 1L;
	private int errorCode = EErrorCodes.OverSizeClassStudent.getErrorCode();
	private String errorName = EErrorCodes.OverSizeClassStudent.toString();
	public int getErrorCode() {
		return this.errorCode;
	}
	public String getErrorName() {
		return this.errorName;
	}
}

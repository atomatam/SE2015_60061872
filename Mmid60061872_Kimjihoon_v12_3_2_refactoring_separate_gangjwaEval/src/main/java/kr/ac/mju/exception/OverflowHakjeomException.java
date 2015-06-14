package kr.ac.mju.exception;

import kr.ac.mju.constants.CConstants.EErrorCodes;

public class OverflowHakjeomException extends Exception {
	private static final long serialVersionUID = 1L;
	private int errorCode = EErrorCodes.OverflowHakjeom.getErrorCode();
	private String errorName = EErrorCodes.OverflowHakjeom.toString();
	public int getErrorCode() {
		return this.errorCode;
	}
	public String getErrorName() {
		return this.errorName;
	}
}

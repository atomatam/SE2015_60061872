package kr.ac.mju.exception;

import kr.ac.mju.constants.CConstants.EErrorCodes;

public class DuplicateGangjwaIDException extends Exception{
	private static final long serialVersionUID = 1L;
	private int errorCode = EErrorCodes.DuplicateGangjwaID.getErrorCode();
	private String errorName = EErrorCodes.DuplicateGangjwaID.toString();
	public int getErrorCode() {
		return this.errorCode;
	}
	public String getErrorName() {
		return this.errorName;
	}
}

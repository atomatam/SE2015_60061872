package kr.ac.mju.exception;

import kr.ac.mju.constants.CConstants.EErrorCodes;

public class NoEixstGangjwaException extends Exception{
	private static final long serialVersionUID = 1L;
	private int errorCode = EErrorCodes.NoExistGangjwa.getErrorCode();
	private String errorName = EErrorCodes.NoExistGangjwa.toString();
	public int getErrorCode() {
		return this.errorCode;
	}
	public String getErrorName() {
		return this.errorName;
	}
}

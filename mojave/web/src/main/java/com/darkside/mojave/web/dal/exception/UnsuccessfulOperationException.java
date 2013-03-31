package com.darkside.mojave.web.dal.exception;

public class UnsuccessfulOperationException extends RuntimeException {
	private static final long serialVersionUID = -6827244474237145574L;

	public UnsuccessfulOperationException(Exception cause) {
		initCause(cause);
	}
}

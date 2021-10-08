package br.com.stecnology.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileStorageExecption extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public FileStorageExecption(String execption) {
		super(execption);
	}
	
	public FileStorageExecption(String execption, Throwable cause) {
		super(execption, cause);
	}

}

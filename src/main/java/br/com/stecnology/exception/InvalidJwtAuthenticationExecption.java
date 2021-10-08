package br.com.stecnology.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidJwtAuthenticationExecption extends AuthenticationException {

	private static final long serialVersionUID = 1L;
	
	public InvalidJwtAuthenticationExecption(String execption) {
		super(execption);
	}

}

package com.ssafy.banana.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.ssafy.banana.dto.response.ExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionAdvice {
	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<ExceptionResponse> customExceptionHandler(CustomException e) {
		return getResponseEntity(e.getException());
	}

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<ExceptionResponse> runtimeExceptionHandler(RuntimeException e) {
		System.out.println(e.getMessage());
		return getResponseEntity(CustomExceptionType.RUNTIME_EXCEPTION);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ExceptionResponse> exceptionHandler(Exception e) {
		System.out.println(e.getMessage());
		return getResponseEntity(CustomExceptionType.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = MaxUploadSizeExceededException.class)
	public ResponseEntity<ExceptionResponse> maxUploadSizeExceededExceptionHandler(Exception e) {
		System.out.println(e.getMessage());
		return getResponseEntity(CustomExceptionType.MAX_UPLOAD_SIZE_EXCEEDED);
	}

	private ResponseEntity getResponseEntity(CustomExceptionType exceptionType) {
		return ResponseEntity
			.status(exceptionType.getHttpStatus())
			.body(ExceptionResponse.builder()
				.code(exceptionType.getCode())
				.message(exceptionType.getMessage())
				.build());
	}
}

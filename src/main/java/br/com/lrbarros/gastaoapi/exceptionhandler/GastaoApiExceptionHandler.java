package br.com.lrbarros.gastaoapi.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.lrbarros.gastaoapi.exceptionhandler.util.DescricaoErro.ErroDesenvolvedor;

@ControllerAdvice
public class GastaoApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = Optional.ofNullable(ex.getCause()).orElse(ex).toString();
		List<ErroDesenvolvedor> errosLista = Arrays
				.asList(new ErroDesenvolvedor(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, errosLista, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<ErroDesenvolvedor> errosLista = criarListaDeErros(ex.getBindingResult());

		return handleExceptionInternal(ex, errosLista, headers, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,WebRequest request) {
		String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null,LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		System.out.println(mensagemDesenvolvedor);
		List<ErroDesenvolvedor> errosLista = Arrays.asList(new ErroDesenvolvedor(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, errosLista, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({DataIntegrityViolationException.class})
	public ResponseEntity<Object> handleDataIntegrityViolationExceptioEntity(DataIntegrityViolationException ex, WebRequest request){
		String mensagemUsuario = messageSource.getMessage("recurso.nao-permitido", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
		List<ErroDesenvolvedor> errosList = Arrays.asList(new ErroDesenvolvedor(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, errosList, new HttpHeaders(), HttpStatus.BAD_GATEWAY, request);
	}
	
	private List<ErroDesenvolvedor> criarListaDeErros(BindingResult bindingResult) {
		List<ErroDesenvolvedor> erros = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor = fieldError.toString();
			erros.add(new ErroDesenvolvedor(mensagemUsuario, mensagemDesenvolvedor));
		}

		return erros;
	}
}

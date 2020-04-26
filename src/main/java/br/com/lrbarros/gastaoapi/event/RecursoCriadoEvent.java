package br.com.lrbarros.gastaoapi.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;
/*
 * Classe usada para adicionar o header location resolvendo 
 * o problema de duplicação de codigo em adição de headerlocation
 */
public class RecursoCriadoEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1L;

	private HttpServletResponse response;
	private Long codigo;
	
	public RecursoCriadoEvent(Object source,HttpServletResponse response,Long codigo) {
		super(source);
		this.response = response;
		this.codigo = codigo;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Long getCodigo() {
		return codigo;
	}

}

package br.com.lrbarros.gastaoapi.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.lrbarros.gastaoapi.model.Lancamento;
import br.com.lrbarros.gastaoapi.model.Pessoa;
import br.com.lrbarros.gastaoapi.repository.LancamentoRepository;
import br.com.lrbarros.gastaoapi.repository.PessoaRepository;
import br.com.lrbarros.gastaoapi.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo()).orElse(null);
		if(pessoa == null || pessoa.isInativa()) {
			throw new PessoaInexistenteOuInativaException();
		}
		return lancamentoRepository.save(lancamento);
	}
	
	public Lancamento atualizar(Long codigo, @Valid Lancamento lancamento) {
		//validacoes dentro dos métodos buscarLancamento e salvar
		Lancamento lancamentoSalvo = buscarLancamento(codigo);
		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");
		return salvar(lancamentoSalvo);
	}
	
	public Lancamento buscarLancamento(Long codigo) {
		Lancamento lancamentoEncontrado = lancamentoRepository.findById(codigo).orElse(null);
		if(lancamentoEncontrado==null) {
			throw new EmptyResultDataAccessException(1);
		}
		return lancamentoEncontrado;
	}
	
	
}

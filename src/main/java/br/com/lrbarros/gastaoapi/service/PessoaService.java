package br.com.lrbarros.gastaoapi.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.lrbarros.gastaoapi.model.Pessoa;
import br.com.lrbarros.gastaoapi.repository.PessoaRepository;

@Service
public class PessoaService {
	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa atualizarPessoa(Long codigo, @Valid Pessoa pessoa) {
		Pessoa pessoaEncontrada = buscarPessoaCodigo(codigo);
		BeanUtils.copyProperties(pessoa,pessoaEncontrada, "codigo");
		return pessoaRepository.save(pessoaEncontrada);
	}

	public void atualizarPropriedadeAtivo(@Valid Long codigo, Boolean ativo) {
		Pessoa pessoaEncontrada = buscarPessoaCodigo(codigo);
		pessoaEncontrada.setAtivo(ativo);
		pessoaRepository.save(pessoaEncontrada);
	}

	public Pessoa buscarPessoaCodigo(Long codigo) {
		Pessoa pessoaEncontrada = pessoaRepository.findById(codigo).orElse(null);
		if(pessoaEncontrada==null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaEncontrada;
	}

}

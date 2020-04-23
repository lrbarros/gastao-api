package br.com.lrbarros.gastaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lrbarros.gastaoapi.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}

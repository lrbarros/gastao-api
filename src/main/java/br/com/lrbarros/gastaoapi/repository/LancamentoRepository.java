package br.com.lrbarros.gastaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lrbarros.gastaoapi.model.Lancamento;
import br.com.lrbarros.gastaoapi.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long >, LancamentoRepositoryQuery{

}

package br.com.lrbarros.gastaoapi.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.lrbarros.gastaoapi.model.Lancamento;
import br.com.lrbarros.gastaoapi.repository.filter.LancamentoFilter;
import br.com.lrbarros.gastaoapi.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {

	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
}

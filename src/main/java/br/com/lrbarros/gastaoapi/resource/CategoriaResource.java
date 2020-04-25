package br.com.lrbarros.gastaoapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lrbarros.gastaoapi.event.RecursoCriadoEvent;
import br.com.lrbarros.gastaoapi.model.Categoria;
import br.com.lrbarros.gastaoapi.repository.CategoriaRepository;

//Esta classe é um controlador
@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	//@CrossOrigin(maxAge = 10 , origins = {"http://localhost:8000"}) @TODO: resolvido no filtro pacote cors
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
	public List<Categoria> listar(){
		return categoriaRepository.findAll();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('write')")
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		//Cria o location de retorno
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA')")
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
		Categoria categoriaEncontrada = categoriaRepository.findById(codigo).orElse(null);
		return categoriaEncontrada != null? ResponseEntity.ok(categoriaEncontrada):ResponseEntity.notFound().build();
	}
}

// Para varias permissões em um método
//antMatchers(HttpMethod.POST, "/dicas/**").hasAnyAuthority("ROLE_ADMIN", ROLE_CONTA_ATIVA, ROLE_OUTRO)
//ou
//PreAuthorize(value="hasRole('ROLE_ADMIN') and (hasRole('ROLE_CONTA_ATIVA') or hasRole('ROLE_OUTRO'))")


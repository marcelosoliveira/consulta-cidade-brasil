package consulta.cidade.brasil.staties.resources;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import consulta.cidade.brasil.staties.entities.State;
import consulta.cidade.brasil.staties.repositories.StateRepository;

@RestController
@RequestMapping("/api/v1/staties")
public class StateResource {

  private final StateRepository repository;

  public StateResource(final StateRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public List<State> staties() {
    return repository.findAll();
  }
  
  @GetMapping("/{id}")
	public ResponseEntity<?> findByIdCities(@PathVariable Long id) {
		try {
			Optional<State> state = this.repository.findById(id);
			if (!state.isPresent())
				throw new Exception("Estado não encontrado!");
			return ResponseEntity.ok(state.get());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			Map<String, String> error = new LinkedHashMap<>();
			error.put("title", e.getMessage());
			error.put("statusCode", HttpStatus.NOT_FOUND.toString());
			error.put("dateTime", LocalDateTime.now().toString());
			error.put("message", e.getMessage());
			return ResponseEntity.ok(error);
		}
	}
  
}

package consulta.cidade.brasil.countries.resources;

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

import consulta.cidade.brasil.countries.entities.Country;
import consulta.cidade.brasil.countries.repositories.CountryRepository;

@RestController
@RequestMapping("/api/v1")
public class CountryResource {

	private final CountryRepository repository;

	public CountryResource(final CountryRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/countries")
	public List<Country> cities() {

		return repository.findAll();
	}

	@GetMapping("/countries/{id}")
	public ResponseEntity<?> findByIdCities(@PathVariable Long id) {
		try {
			Optional<Country> country = this.repository.findById(id);
			if (!country.isPresent())
				throw new Exception("País não encontrado!");
			return ResponseEntity.ok(country.get());
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

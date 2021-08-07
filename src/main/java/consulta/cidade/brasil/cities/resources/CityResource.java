package consulta.cidade.brasil.cities.resources;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import consulta.cidade.brasil.cities.entities.City;
import consulta.cidade.brasil.cities.repositories.CityRepository;

@RestController
@RequestMapping("/cities")
public class CityResource {

//  private final CidadeMapper cidadeMapper = CidadeMapper.INSTANCE;

	@Autowired
	private CityRepository repository;

	/*
	 * 1st
	 * 
	 * @GetMapping public List<City> cities() { return repository.findAll(); }
	 */

	// 2nd - Pageable
	@GetMapping
	public Page<City> cities(Pageable page) {
		return repository.findAll(page);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findByIdCities(@PathVariable Long id) {
		try {
			Optional<City> city = this.repository.findById(id);
			if (!city.isPresent())
				throw new Exception("Cidade não encontrado!");
			return ResponseEntity.ok(city.get());
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

	@GetMapping("/distanceByPoints/{cityId1}/{cityId2}")
	public Object distanceByPoints(@PathVariable Long cityId1, @PathVariable Long cityId2) {
		try {
			Optional<City> city1 = this.repository.findById(cityId1);
			Optional<City> city2 = this.repository.findById(cityId2);

			if (!city1.isPresent() || !city2.isPresent())
				throw new Exception("Uma ou duas das cidades pesquisadas não existem");

			Double point = this.repository.distanceByPoints(cityId1, cityId2);
			Map<String, Double> distancePoint = new LinkedHashMap<>();
			distancePoint.put("distanceByPoints", point);
			return ResponseEntity.ok(distancePoint);
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

	@GetMapping("/distanceByCube/{cityId1}/{cityId2}")
	public Object distanceByCube(@PathVariable Long cityId1, @PathVariable Long cityId2) {
		try {
			Optional<City> city1 = this.repository.findById(cityId1);
			Optional<City> city2 = this.repository.findById(cityId2);

			if (!city1.isPresent() || !city2.isPresent())
				throw new Exception("Uma ou duas das cidades pesquisadas não existem");

			Double cube = this.repository.distanceByCube(city1.get().getLocation().getX(),
					city1.get().getLocation().getY(), city2.get().getLocation().getX(),
					city2.get().getLocation().getY());

			Map<String, Double> distanceCube = new LinkedHashMap<>();
			distanceCube.put("distanceByCube", cube);
			return ResponseEntity.ok(distanceCube);
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

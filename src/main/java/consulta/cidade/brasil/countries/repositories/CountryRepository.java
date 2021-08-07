package consulta.cidade.brasil.countries.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import consulta.cidade.brasil.countries.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
}

package consulta.cidade.brasil.staties.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import consulta.cidade.brasil.staties.entities.State;

public interface StateRepository extends JpaRepository<State, Long> {
}

package felipe.bonadio.aulaSpringboot2.repository;

import felipe.bonadio.aulaSpringboot2.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

}

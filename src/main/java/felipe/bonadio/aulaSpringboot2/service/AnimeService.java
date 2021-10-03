package felipe.bonadio.aulaSpringboot2.service;

import felipe.bonadio.aulaSpringboot2.domain.Anime;
import felipe.bonadio.aulaSpringboot2.repository.AnimeRepository;
import felipe.bonadio.aulaSpringboot2.request.AnimePostRequestBody;
import felipe.bonadio.aulaSpringboot2.request.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

   private final AnimeRepository animeRepository;

    public List<Anime> listAll(){
        return animeRepository.findAll();
    }

    public Anime findByIdOrThrowBadRequestException(@PathVariable long id) {
        return animeRepository.findById(id)
              .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime não encontrado"));
    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save(Anime.builder().name(animePostRequestBody.getName()).build());
    }

    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = Anime.builder()
                .id(savedAnime.getId())
                .name(animePutRequestBody.getName())
                .build();
        animeRepository.save(anime);

    }
}

package io.app.fiztec.springazure;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActorService {

    private ActorRepository repo;

    public ActorService(ActorRepository repo) {
        this.repo = repo;
    }

    public Iterable<Actor> getAllActor(){
        return repo.findAll();
    }
    public Iterable<Actor> getMultipleActors(int amount) {
        if (amount > 0) {
            return repo.findAll(PageRequest.of(0, amount)).getContent();
        } else {
            throw new ClientException("Actor Not Found");
        }
    }

    public Actor getActor() {
        return null;
    }

    public Iterable<Actor> createAll(Iterable<Actor> Actors){
        return repo.saveAll(Actors);
    }
    public Actor createActor(Actor Actor) {
        if (Actor.getFirstName().isEmpty()) {
            throw new ClientException("Input not valid (no title)");
        }
        return repo.save(Actor);
    }

    public Actor getActor(String id) {
        Optional<Actor> optionalActor = repo.findById(Integer.valueOf(id));
        if (!optionalActor.isPresent()) {
            throw new NotFoundException("Actor Not Found");
        } else {
            return optionalActor.get();
        }
    }

}
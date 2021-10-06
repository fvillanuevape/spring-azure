package io.app.fiztec.springazure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actor/v1")
public class ActorController {

    private ActorService service;

    public ActorController(ActorService service) {
        this.service = service;
    }
    @GetMapping(value = "/actors", produces = "application/json")
    public ResponseEntity<Iterable<Actor>> getAllActors(){

        return ResponseEntity.ok(service.getAllActor());
    }
    @GetMapping(value = "/actors", params = "amount", produces = "application/json")
    public ResponseEntity<Iterable<Actor>> getMultipleActors(
            @RequestParam(name = "amount", required = true) int amount) {
        return ResponseEntity.ok(service.getMultipleActors(amount));
    }


    @GetMapping(value = "/actor", params = "id", produces = "application/json")
    public ResponseEntity<Actor> getSingleActor(@RequestParam(name = "id", required = true) String id) {
        return ResponseEntity.ok(service.getActor(id));
    }


    @PostMapping(value = "/actor", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Actor> createNewActor(@RequestBody Actor Actor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createActor(Actor));
    }
    @PostMapping(value = "actors",consumes = "application/json", produces = "application/json")
    public ResponseEntity<Iterable<Actor>> createNewActorAll(@RequestBody Iterable<Actor> Actors){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAll(Actors));
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<String> handleClientExceptions(ClientException e) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(ClientException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
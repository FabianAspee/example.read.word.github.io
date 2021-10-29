package com.example.app.contract;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * #project examples-with-java8
 *
 * @author aspeeencinaf and 25/10/21
 **/
public interface ArtistsOperation {
    @PostMapping(value = "/read/artistbyname")
    CompletableFuture<Optional<List<String>>> readArtistByName(@RequestBody final String name);
    @PostMapping("/read/allartists")
    CompletableFuture<Optional<List<String>>> readAllArtist();
    @PostMapping("/update/artistbyname")
    CompletableFuture<Optional<Integer>> updateArtistByName(@RequestBody final String name);

    @PostMapping("/read/artistbynameexec")
    Optional<List<String>> readArtistByNameExec(@RequestBody final String name);
    @PostMapping("/read/allartistsexec")
    Optional<List<String>> readAllArtistExec();
    @PostMapping("/update/artistbynameexec")
    Optional<Integer> updateArtistByNameExec(@RequestBody final String name);

    @PostMapping("/read/artistbynameforkjoin")
    Optional<List<String>> readArtistByNameForkJoin(@RequestBody final String name);
    @PostMapping("/read/allartistsforkjoin")
    Optional<List<String>> readAllArtistForkJoin();
    @PostMapping("/update/artistbynameforkjoin")
    Optional<Integer> updateArtistByNameForkJoin(@RequestBody final String name);
}

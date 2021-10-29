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
public interface PlaylistOperation {
    @PostMapping("/read/playlistbyname")
    CompletableFuture<Optional<List<String>>> readPlaylistByName(@RequestBody final String name);
    @PostMapping("/read/allplaylist")
    CompletableFuture<Optional<List<String>>> readAllPlaylist();
    @PostMapping("/update/playlistbyname")
    CompletableFuture<Optional<Integer>> updatePlaylistByName(@RequestBody final String name);

    @PostMapping("/read/playlistbynameexec")
    Optional<List<String>> readPlaylistByNameExec(@RequestBody final String name);
    @PostMapping("/read/allplaylistexec")
    Optional<List<String>> readAllPlaylistExec();
    @PostMapping("/update/playlistbynameexec")
    Optional<Integer> updatePlaylistByNameExec(@RequestBody final String name);

    @PostMapping("/read/playlistbynameforkjoin")
    Optional<List<String>> readPlaylistByNameForkJoin(@RequestBody final String name);
    @PostMapping("/read/allplaylistforkjoin")
    Optional<List<String>> readAllPlaylistForkJoin();
    @PostMapping("/update/playlistbynameforkjoin")
    Optional<Integer> updateAPlaylistByNameForkJoin(@RequestBody final String name);
}

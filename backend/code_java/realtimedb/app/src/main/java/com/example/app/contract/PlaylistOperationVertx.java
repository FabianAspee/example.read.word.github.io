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
public interface PlaylistOperationVertx {
    @PostMapping("/read/playlistbynamevertx")
    void readPlaylistByName(@RequestBody final String name);
    @PostMapping("/read/allplaylistvertx")
    void readAllPlaylist();
}

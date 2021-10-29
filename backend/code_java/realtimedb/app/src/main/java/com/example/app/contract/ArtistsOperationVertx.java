package com.example.app.contract;

import org.springframework.web.bind.annotation.PostMapping;

/**
 * #project realtimedatabase
 *
 * @author aspeeencinaf and 28/10/21
 **/
public interface ArtistsOperationVertx {
    @PostMapping("/read/artisbynamevertx")
    void readArtistByName(String name);
    @PostMapping("/read/allartistsvertx")
    void readAllArtists();
}

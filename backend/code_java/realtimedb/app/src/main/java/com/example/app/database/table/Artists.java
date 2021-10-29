package com.example.app.database.table;

import javax.persistence.*;

@Entity
@Table(name="artists")
public class Artists {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ArtistId")
    private long artistsId;

    @Column(name = "Name")
    private String name;

    public long getArtistsId() {
        return artistsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

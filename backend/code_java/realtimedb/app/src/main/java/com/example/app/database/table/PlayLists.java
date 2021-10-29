package com.example.app.database.table;

import javax.persistence.*;

@Entity
@Table(name = "playlists")
public class PlayLists {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long playListId;

    private String name;

    public long getPlayListId() {
        return playListId;
    }

    public String getName() {
        return name;
    }
    @Column(name = "Name")
    public void setName(String name) {
        this.name = name;
    }




}

package com.example.springboot;

import java.util.List;

public class Team {
    String name;
    List<Player> teamList;
    Team()
    {
        this.name="Random";
    }
    Team(String name)
    {
        this.name=name;
    }
}

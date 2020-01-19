package com.example.springboot;

import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    @RequestMapping("/")
    public String index() {
        return "<button onclick=\"window.location.href='result'\">Start Match</button>";
    }
    @RequestMapping("/result")
    public String post()
    {
        Match m=new Match(new Team("India"),new Team("Australia"),50);
        return m.startMatch();
    }
}
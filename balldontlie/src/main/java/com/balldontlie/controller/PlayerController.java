package com.balldontlie.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.balldontlie.service.PlayerService;

@RestController
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@GetMapping("/")
    public String index() {
        return "press /player to continue";
    }
	
	@RequestMapping("/player")
	public void updateCsvWithPlayers(HttpServletResponse response) throws IOException {
		
		playerService.updateCsvWithPlayers(response);
		
	}
	
	@RequestMapping("/fetch")
	public ResponseEntity<List<Object>> fetchAllPlayers()  {
		List<Object> players;
		players =  playerService.fetchAllPlayers();
		 return ResponseEntity.ok(players);
	}
	
	
	
}

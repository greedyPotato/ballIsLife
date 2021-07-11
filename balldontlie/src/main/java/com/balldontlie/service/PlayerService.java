package com.balldontlie.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.balldontlie.repo.PlayerDao;

@Service
public class PlayerService implements PlayerServiceInterface{
	
	@Autowired
	private PlayerDao playerDao;
	
	public void updateCsvWithPlayers(HttpServletResponse response) throws IOException {
		
		playerDao.updateCsvWithPlayers(response);
	}

	@Override
	public List<Object> fetchAllPlayers() {
		// TODO Auto-generated method stub
		return playerDao.fetchAllPlayers();
	}
}

package com.balldontlie.service;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public interface PlayerServiceInterface {

	void updateCsvWithPlayers(HttpServletResponse response) throws IOException;
	
	List<Object> fetchAllPlayers();
	
	
}

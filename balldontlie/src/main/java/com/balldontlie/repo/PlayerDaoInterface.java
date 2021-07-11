package com.balldontlie.repo;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public interface PlayerDaoInterface {

	void updateCsvWithPlayers(HttpServletResponse response) throws IOException;
	
	List<Object> fetchAllPlayers();
}

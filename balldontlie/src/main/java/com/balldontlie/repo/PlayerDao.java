package com.balldontlie.repo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import com.balldontlie.model.Player;

@Repository
public class PlayerDao implements PlayerDaoInterface {
	
	@Autowired
	private RedisTemplate<String, Player> redisTemplate;
	
	@Override
	public void updateCsvWithPlayers(HttpServletResponse response) throws IOException {
		
		ArrayList<Player> playersInCSV = new ArrayList<Player>();
		parseCsvFile(playersInCSV);
		
		
		//get more details from api
		RestTemplate restTemplate = new RestTemplate();
		playersInCSV.remove(0);
		for (Player player : playersInCSV) {
			
			Player p = (restTemplate.getForObject("https://www.balldontlie.io/api/v1/players/" + player.getId(), Player.class));
			player.setFirst_name(p.getFirst_name());
			player.setLast_name(p.getLast_name());
			player.setHeight_feet(p.getHeight_feet());
			player.setHeight_inches(p.getHeight_inches());
			player.setPosition(p.getPosition());
			player.setWeight_pounds(p.getWeight_pounds());
			player.setTeam(p.getTeam());
			player.setAbbreviation(p.getTeam().getAbbreviation());
			player.setCity(p.getTeam().getCity());
			player.setDivision(p.getTeam().getDivision());
			player.setName(p.getTeam().getName());
			player.setFull_name(p.getTeam().getFull_name());
			redisTemplate.opsForHash().put("PLAYER", player.getId(), player);
		}
		
				//fill csv with new info
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=players.csv"  );
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] headings = {"id","nickname","first_name","last_name","position","height_feet","height_inches","weight_pounds","city","division","full_name","name"};
		String[] pojoNames = {"id","nickname","first_name","last_name","position","height_feet","height_inches","weight_pounds","city","division","full_name","name"};
		
		csvWriter.writeHeader(headings);
		for (Player player : playersInCSV) {
			csvWriter.write(player, pojoNames);
		}
		csvWriter.close();
		
	}
	
	@Scheduled(fixedDelay = 900000L)
	public void updateEvery15() {
		ArrayList<Player> playersInCSV = new ArrayList<Player>();
		//parse csv
		parseCsvFile(playersInCSV);
		RestTemplate restTemplate = new RestTemplate();
		playersInCSV.remove(0);
		for (Player player : playersInCSV) {
			
			Player p = (restTemplate.getForObject("https://www.balldontlie.io/api/v1/players/" + player.getId(), Player.class));
			player.setFirst_name(p.getFirst_name());
			player.setLast_name(p.getLast_name());
			player.setHeight_feet(p.getHeight_feet());
			player.setHeight_inches(p.getHeight_inches());
			player.setPosition(p.getPosition());
			player.setWeight_pounds(p.getWeight_pounds());
			player.setTeam(p.getTeam());
			player.setAbbreviation(p.getTeam().getAbbreviation());
			player.setCity(p.getTeam().getCity());
			player.setDivision(p.getTeam().getDivision());
			player.setName(p.getTeam().getName());
			player.setFull_name(p.getTeam().getFull_name());
			redisTemplate.opsForHash().put("PLAYER", player.getId(), player);
		}
	}
	
	public void parseCsvFile(ArrayList<Player> playersInCSV) {
		String path = "src/main/resources/players.csv";
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while((line = br.readLine())!= null) {
				String[] data = line.split(",");
				Player playerParse = new Player();
				playerParse.setId(data[0]);
				playerParse.setNickname(data[1]);
				playersInCSV.add(playerParse);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Object> fetchAllPlayers() {
		List<Object> players;
		players = redisTemplate.opsForHash().values("PLAYER");
		return players;
	}
}



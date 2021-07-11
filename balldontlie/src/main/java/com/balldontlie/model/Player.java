package com.balldontlie.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player implements Serializable {
	
	private static final long serialVersionUID = 7761692834548531526L;

	private String id;
	private String nickname;
	private String first_name;
	private String last_name;
	private String position;
	private String height_feet;
	private String height_inches;
	private String weight_pounds;
	private String abbreviation;
	private String conference;
	private String city;
	private String division;
	private String full_name;
	private String name;
	
	@Autowired
	Team team;

	
	
	public String getConference() {
		return conference;
	}

	public void setConference(String conference) {
		this.conference = conference;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getHeight_feet() {
		return height_feet;
	}

	public void setHeight_feet(String height_feet) {
		this.height_feet = height_feet;
	}

	public String getHeight_inches() {
		return height_inches;
	}

	public void setHeight_inches(String height_inches) {
		this.height_inches = height_inches;
	}

	public String getWeight_pounds() {
		return weight_pounds;
	}

	public void setWeight_pounds(String weight_pounds) {
		this.weight_pounds = weight_pounds;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	
	
}

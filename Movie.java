package Main;

import java.util.ArrayList;

public class Movie {
	private String title;
	private int year;
	private String director;
	private String genre;
	private String description;
	private int id;
	private String imagePath;

	public Movie() {
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Movie(String titel, int year, String director, String genre, String description, int id, String imagePath) {
		super();
		this.title = titel;
		this.year = year;
		this.director = director;
		this.genre = genre;
		this.description = description;
		this.id = id;
		this.imagePath = imagePath;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}





}

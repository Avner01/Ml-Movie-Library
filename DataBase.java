package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeSet;

public class DataBase {


	private static final String DBLoacation = "DataBase";
	private static final String ConnString = "jdbc:derby:" + DBLoacation + "; create=true";
	
	private static final String MOVIE_ID = "Id";
	private static final String MOVIE_TABLE = "Movie";
	private static final String MOVIE_TITLE = "Title";
	private static final String MOVIE_DIRECTOR = "Director";
	private static final String MOVIE_GENRE = "Genre";
	private static final String MOVIE_YEAR = "MovieYear";
	private static final String MOVIE_DESCRIPTION = "Description";
	private static final String MOVIE_IMAGEPATH = "IMAGEPATH";

	private static final String MOVIELIST_TABLE = "MovieList";
	private static final String MOVIELIST_ID = "MovieListId";
	private static final String MOVIELIST_MOVIEID = "ListMovieId";
	private static final String MOVIELIST_USERID = "ListUserId";
	
	private static final String PLIST_TABLE = "PersonalList";
	private static final String PLIST_NAME = "PName";
	private static final String PLIST_ = "PName";

	
	private static final String GENRE_TABLE = "Genre";
	private static final String GENRE_NAME = "GenreName";
	private static final String GENRE_ID = "GenreId";


	private static final String USER_TABLE = "USERTABLE";
	private static final String USER_ID = "UserId";
	private static final String USER_NAME = "UserName";
	private static final String USER_PASSWORD = "UserPassword";


	public static void createUserTable()throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(ConnString);
			stmt = conn.createStatement();
			rs = conn.getMetaData().getTables(null, null, USER_TABLE.toUpperCase(), new String[] {"TABLE"});
			if(rs.next()) {
				return;
			}
			String ct = "CREATE TABLE " + USER_TABLE + "(" +
					USER_ID + " INTEGER GENERATED ALWAYS AS IDENTITY," +
					USER_NAME + " VARCHAR(200)," +
					USER_PASSWORD + " VARCHAR(200)," +
					"PRIMARY KEY(" + USER_ID + "))";
	
			System.out.println(ct);
			stmt.execute(ct);
			insertUser("admin", "admin1");
		}
		catch(SQLException e) {
			throw e;
		}
		finally {
			try {
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			}
			catch(SQLException e) {
				throw e;
			}
		}
	}

	public static void createMovieTable()throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(ConnString);
			stmt = conn.createStatement();
			rs = conn.getMetaData().getTables(null, null, MOVIE_TABLE.toUpperCase(), new String[] {"TABLE"});
			if(rs.next()) {
				return;
			}
			String ct = "CREATE TABLE " + MOVIE_TABLE + " (" +
					
					MOVIE_ID + " INTEGER GENERATED ALWAYS AS IDENTITY," +
					MOVIE_TITLE + " VARCHAR(255)," +
					MOVIE_DIRECTOR + " VARCHAR(100)," +
					MOVIE_YEAR + " BIGINT," +
					MOVIE_GENRE + " VARCHAR(64)," +
					MOVIE_DESCRIPTION + " VARCHAR(255)," +
					MOVIE_IMAGEPATH + " VARCHAR(255)," + 
					"PRIMARY KEY(" + MOVIE_ID + "))"; 
			
			stmt.executeUpdate(ct);
			insertMovie(new Movie("Jumanji: The Next Level",2019 ,"Jake Kasdan ","Adventure","",0,"Pictures/Jumanji The Next Level.jpg"));
			insertMovie(new Movie("Yes Day",2021 ,"Miguel Arteta ","Comedy","",0,"Pictures/Yes Day.jpg"));
			insertMovie(new Movie("Space Jam:New Legacy",2021 ,"Steven Malcolm D. Lee ","Comedy","",0,"Pictures/Space Jam.jpg"));

		}
		catch(SQLException e) {
			throw e;
		}
		finally {
			try {
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			}
			catch(SQLException e) {
				throw e;
			}
		}
	}
	public static void createMovieListTable()throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(ConnString);
			stmt = conn.createStatement();
			rs = conn.getMetaData().getTables(null, null, MOVIELIST_TABLE.toUpperCase(), new String[] {"TABLE"});
			if(rs.next()) {
				return;
			}
			
							String ct = "CREATE TABLE " + MOVIELIST_TABLE + " (" +
							
							MOVIELIST_ID + " INTEGER GENERATED ALWAYS AS IDENTITY," +
							MOVIELIST_MOVIEID + " INTEGER," +
							MOVIELIST_USERID + " INTEGER," +
							"FOREIGN KEY(" + MOVIELIST_MOVIEID + ") REFERENCES " + MOVIE_TABLE + "(" + MOVIE_ID + ")," + 
							"FOREIGN KEY(" + MOVIELIST_USERID + ") REFERENCES " + USER_TABLE + "(" + USER_ID + ")," + 

							"PRIMARY KEY(" + MOVIELIST_ID + "))"; 
					System.out.println(ct);
					stmt.executeUpdate(ct);
			
				}
				catch(SQLException e) {
					throw e;
				}
				finally {
					try {
						if(stmt != null)
							stmt.close();
						if(conn != null)
							conn.close();
					}
					catch(SQLException e) {
						throw e;
					}
				}
			}
			

	
	public static void createGenreTable()throws SQLException {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(ConnString);
			stmt = conn.createStatement();
			rs = conn.getMetaData().getTables(null, null, GENRE_TABLE.toUpperCase(), new String[] {"TABLE"});
			if(rs.next()) {
				return;
			}
			String ct = "CREATE TABLE " + GENRE_TABLE + " (" +
					GENRE_ID + " INTEGER GENERATED ALWAYS AS IDENTITY," +
					GENRE_NAME + " VARCHAR(200)," +

					"PRIMARY KEY(" + GENRE_ID + "))";
			stmt.executeUpdate(ct);
			insertGenre("Crime");
			insertGenre("Si-Fi");
			insertGenre("Adventure");
			insertGenre("Comedy");
			insertGenre("Action");
		

		}		

		catch(SQLException e) {
			throw e;
		}
		finally {
			try {
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			}
			catch(SQLException e) {
				throw e;
			}
		}
	}
	public static void insertGenre(String name) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String insert = "INSERT INTO " + GENRE_TABLE + " (" + GENRE_NAME + ") VALUES(" +
				
				"?)";  //NAME			
		try {
			conn = DriverManager.getConnection(ConnString);
			stmt = conn.prepareStatement(insert);
			stmt.setString(1, name);
			
			stmt.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static  ArrayList<String> loadAllGenre( ) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String select = "SELECT * FROM " + GENRE_TABLE;
		ArrayList<String> lb = new ArrayList<>();
		TreeSet<String> themen = new TreeSet<>();
		try {
			conn = DriverManager.getConnection(ConnString);
			stmt = conn.prepareStatement(select);
			rs = stmt.executeQuery();
			while(rs.next())
				themen.add(rs.getString(GENRE_NAME));	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		for(String t : themen)
			lb.add(t);
		return lb;
	}

	public static void insertUser(String name, String password) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String insert = "INSERT INTO " + USER_TABLE + " (" + USER_NAME + ", " + USER_PASSWORD + ") VALUES(" +
				"?," +
				"?)";  			
		try {
			conn = DriverManager.getConnection(ConnString);
			stmt = conn.prepareStatement(insert);
			stmt.setString(1, name);
			stmt.setString(2, password);

			stmt.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static int userExists(String username, String password) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int ret = -1;
		String select = "SELECT " + USER_ID + " FROM " + USER_TABLE + " WHERE " + USER_NAME + " = ? AND " + USER_PASSWORD + " = ?";
		try {
			conn = DriverManager.getConnection(ConnString);
			stmt = conn.prepareStatement(select);
			stmt.setString(1,  username);
			stmt.setString(2,  password);

			rs = stmt.executeQuery();
			if(rs.next()) {
				ret = rs.getInt(USER_ID);
			}
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
			return ret;
		}
	
	public static boolean userNameExists(String username) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean ret = false;
		String select = "SELECT " + USER_NAME + " FROM " + USER_TABLE + " WHERE " + USER_NAME + " = ?";
		try {
			conn = DriverManager.getConnection(ConnString);
			stmt = conn.prepareStatement(select);
			stmt.setString(1,  username);
			rs = stmt.executeQuery();
			if(rs.next()) {
				ret = true;
			}
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
			return ret;
		}

	
		
	public static void insertMovie(Movie movie)  {
		Connection conn = null;
		PreparedStatement stmt = null;
				String insert = "INSERT INTO " + MOVIE_TABLE + " (" + MOVIE_TITLE + ", "+ MOVIE_DIRECTOR + ", " + 
						MOVIE_YEAR + ", "+ MOVIE_GENRE + ", "+ MOVIE_DESCRIPTION + ", "+ MOVIE_IMAGEPATH + ") VALUES(" +
				"?," +  // TITLE
				"?," +  // DIRECTOR
				"?," +  // YEAR
				"?," +  // GENRE
				"?," +  // DESCRIPTION
				"?)";  //  IMAGEPATH
			System.out.println(insert);

		try {
			conn = DriverManager.getConnection(ConnString);
			stmt = conn.prepareStatement(insert);
			stmt.setString(1, movie.getTitle());
			stmt.setString(2, movie.getDirector());
			stmt.setLong(3, movie.getYear());
			stmt.setString(4, movie.getGenre());
			stmt.setString(5, movie.getDescription());
			stmt.setString(6, movie.getImagePath());
			stmt.executeUpdate();


			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static  ArrayList<Movie> loadAllMovies( ) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String select = "SELECT * FROM " + MOVIE_TABLE;
		ArrayList<Movie> returnList = new ArrayList<>();
		System.out.println(select);
		
		try {
			conn = DriverManager.getConnection(ConnString);
			stmt = conn.prepareStatement(select);
			rs = stmt.executeQuery();
			returnList = getMoviesFromResultSet(rs);

			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return returnList;
	}
	public static  ArrayList<Movie> loadAllMoviesFilterd( String filter) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String select = "SELECT * FROM " + MOVIE_TABLE + " WHERE UPPER(" + MOVIE_TITLE +") LIKE UPPER(?)";
		ArrayList<Movie> returnList = new ArrayList<>();
		System.out.println(select);
		
		try {
			conn = DriverManager.getConnection(ConnString);
			stmt = conn.prepareStatement(select);
			stmt.setString(1, filter + "%");
			rs = stmt.executeQuery();
			returnList = getMoviesFromResultSet(rs);

			
		}

		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return returnList;
	}
	
	

//	to create personal list in main and in the data base with UI that appears when the user clicks on a movie from the main list
//  on the deteilview with a button of 'add' to Plist that will open the Personal List.

		public static void addToPList(Movie movie, int userID) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String insert = "INSERT INTO " + MOVIELIST_TABLE + "(" + MOVIELIST_MOVIEID +"," + MOVIELIST_USERID  + ") VALUES(" +
				"?," +  // movie ID
				"?)";  // userid			
		try {
			conn = DriverManager.getConnection(ConnString);
			stmt = conn.prepareStatement(insert);
			stmt.setInt(1, movie.getId());
			stmt.setInt(2, userID);

			stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();	
			}
		finally {
			try {
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();	
			}
		}
	
		}
		
		public static  ArrayList<Movie> loadMovieList(int userID ) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String select = "SELECT * FROM " + MOVIE_TABLE + " INNER JOIN " + MOVIELIST_TABLE +
					" ON " + MOVIE_TABLE + "." + MOVIE_ID + "=" + MOVIELIST_TABLE + "." + MOVIELIST_MOVIEID +
					" WHERE " + MOVIELIST_TABLE + "." + MOVIELIST_USERID + " = ?" ;
			ArrayList<Movie> returnList = new ArrayList<>();
			System.out.println(select);
			
			try {
				conn = DriverManager.getConnection(ConnString);
				stmt = conn.prepareStatement(select);
				stmt.setInt(1, userID);
				rs = stmt.executeQuery();
				returnList = getMoviesFromResultSet(rs);
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					if(stmt != null)
						stmt.close();
					if(conn != null)
						conn.close();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
			return returnList;
		}

		private static ArrayList<Movie> getMoviesFromResultSet (ResultSet rs) {
		ArrayList<Movie> returnList = new ArrayList<Movie>();
			try {
				while(rs.next()) {
				Movie movie = new Movie();
				movie.setTitle(rs.getString(MOVIE_TITLE));
				movie.setDirector(rs.getString(MOVIE_DIRECTOR));
				movie.setYear(rs.getInt(MOVIE_YEAR));
				movie.setDescription(rs.getString(MOVIE_DESCRIPTION));
				movie.setGenre(rs.getString(MOVIE_GENRE));
				movie.setImagePath(rs.getString(MOVIE_IMAGEPATH));
				movie.setId(rs.getInt(MOVIE_ID));
				returnList.add(movie);
}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return returnList;
}
}

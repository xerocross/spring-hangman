package com.adamcross.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class HangmanGameData {
	Connection conn;

	public void connect() {
		Connection conn = null;
		try {
			String path = new ClassPathResource("chinook.db").getPath();
			String url = "jdbc:sqlite:" + path;
			conn = DriverManager.getConnection(url);

			System.out.println("Got it!");

		} catch (SQLException e) {
			throw new Error("Problem", e);
		}
	}

	public void getGames() {
		Statement stmt = null;
		try {
			String query = "select * from hangman_game";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String name = rs.getString("hangman_game_id");
			}
		}
		catch(Exception e) {
			// do nothing
		}
		finally {
			try {
				if (stmt != null) {
					conn.close();
				}
			} catch(Exception e) {
				// do nothing
			}
		}
	}

	public void newGame(String sessionId) {
		Statement stmt = null;
		try {
			String query = "insert into hangman_game ('session_id') values (" + sessionId +")";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String name = rs.getString("hangman_game_id");
			}
		}
		catch(Exception e) {
			// do nothing
		}
		finally {
			try {
				if (stmt != null) {
					conn.close();
				}
			} catch(Exception e) {
				// do nothing
			}
		}
	}

	public void close() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			// do nothing
		}
	}

}

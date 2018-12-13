package com.needsoft.exside.db;

import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.needsoft.exside.db.models.Dialogue;
import com.needsoft.exside.db.models.DialogueOption;

public class DBHelper {

	private static final String DDBB_URL = "jdbc:sqlite:db/exside.db";
	
	public void runTest() {
		ConnectionSource connection = null;
		try {
			connection = new JdbcConnectionSource(DDBB_URL);
			
			Dao<Dialogue, Integer> dialogueDao = DaoManager.createDao(connection, Dialogue.class);
			Dao<DialogueOption, Integer> dialogueOptionDao = DaoManager.createDao(connection, DialogueOption.class);
			
			TableUtils.createTable(connection, Dialogue.class);
			TableUtils.createTable(connection, DialogueOption.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (null != connection) {
				try {
					connection.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

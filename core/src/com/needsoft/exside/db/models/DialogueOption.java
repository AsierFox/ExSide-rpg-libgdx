package com.needsoft.exside.db.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "dialogue_options")
public class DialogueOption {

	@DatabaseField(id = true, generatedId = true)
	private int id;
	
	@DatabaseField(canBeNull = false)
	private String option;
	
	@DatabaseField(columnName = "id_dialogue")
	private Dialogue dialogue;

}

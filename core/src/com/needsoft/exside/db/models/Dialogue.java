package com.needsoft.exside.db.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "dialogues")
public class Dialogue {

	@DatabaseField(id = true, generatedId = true)
	private int id;
	
	@DatabaseField(canBeNull = false)
	private String talker;
	
	@DatabaseField(canBeNull = false)
	private String text;
	
	@DatabaseField(columnName = "id_next_dialogue")
	private Dialogue nextDialogue;
	
	@ForeignCollectionField(columnName = "dialogue_options", foreignFieldName = "id")
	private ForeignCollection<DialogueOption> options;

}

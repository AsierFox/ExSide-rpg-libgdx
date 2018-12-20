package com.needsoft.exside.systems.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.needsoft.exside.systems.hud.dialogue.DialogueBox;

public class HUD {

    private Stage stage;
    
    private DialogueBox dialogueBox;

    private VisWindow bagWindow;


    public HUD() {
        VisUI.load();
        
        stage = new Stage();

        stage.setDebugAll(true);

        bagWindow = new VisWindow("Bag", true);
        VisTable bagTable = new VisTable(true);
        final int bagCellSize = 30;
        bagTable.add().size(bagCellSize, bagCellSize);
        bagTable.add().size(bagCellSize, bagCellSize);
        bagTable.add().size(bagCellSize, bagCellSize);
        bagTable.add().size(bagCellSize, bagCellSize);
        bagTable.row();
        bagTable.add().size(bagCellSize, bagCellSize);
        bagTable.add().size(bagCellSize, bagCellSize);
        bagTable.add().size(bagCellSize, bagCellSize);
        bagTable.add().size(bagCellSize, bagCellSize);
        bagTable.row();
        bagTable.add().size(bagCellSize, bagCellSize);
        bagTable.add().size(bagCellSize, bagCellSize);
        bagTable.add().size(bagCellSize, bagCellSize);
        bagTable.add().size(bagCellSize, bagCellSize);
        bagWindow.setModal(true);
        bagWindow.setMovable(true);
        bagWindow.add(bagTable);
    }

    public void update(float delta) {
    	if (Gdx.input.isKeyJustPressed(Keys.E)) {
    		if (null == dialogueBox) {
                dialogueBox = new DialogueBox("WOOO Asier Qwe ASdjaskdlasjdas Asier.");
                stage.addActor(dialogueBox);
            }
            else if (dialogueBox.isFinished()) {
    		    // TODO Check for the next dialogue
                dialogueBox.remove();
                dialogueBox = null;
            }
    	}
    	
    	if (Gdx.input.isKeyJustPressed(Keys.Q)) {
    	    if (null == bagWindow.getStage()) {
                stage.addActor(bagWindow);
            }
            else {
                bagWindow.remove();
            }
    	}

        stage.act(delta);
    }

    public void render(Batch batch) {
        stage.draw();
    }

    public void dispose() {
        VisUI.dispose();
        stage.dispose();
    }

}

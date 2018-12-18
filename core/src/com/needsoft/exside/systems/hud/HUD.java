package com.needsoft.exside.systems.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kotcrab.vis.ui.VisUI;
import com.needsoft.exside.systems.dialogue.DialogueBox;

public class HUD {

    private Stage stage;
    
    private DialogueBox dialogueBox;
    
    
    public HUD() {
        stage = new Stage();
        
        VisUI.load();
    }

    public void update(float delta) {
    	if (Gdx.input.isKeyJustPressed(Keys.G)) {
    		dialogueBox = new DialogueBox("WOOO Asier Qwe ASdjaskdlasjdas Asier.");
			stage.addActor(dialogueBox);
    	}
    	
    	if (Gdx.input.isKeyJustPressed(Keys.Q)) {
    		
    	}
    	
    	if (dialogueBox != null && dialogueBox.isFinished()) {
			dialogueBox.remove();
			dialogueBox = null;
    	}
    	
        stage.act(delta);
    }

    public void render(Batch batch) {
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
        VisUI.dispose();
    }

}

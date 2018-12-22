package com.needsoft.exside.systems.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Queue;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisProgressBar;
import com.kotcrab.vis.ui.widget.VisSplitPane;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.needsoft.exside.Game;
import com.needsoft.exside.screens.PlayingScreen;
import com.needsoft.exside.systems.hud.dialogue.DialogueBox;
import com.needsoft.exside.systems.hud.eventbroadcast.EventQueueBroadcaster;

public class HUD {

    private PlayingScreen belongsToScreen;

    private Stage stage;
    
    private DialogueBox dialogueBox;

    private VisWindow bagWindow;

    private VisProgressBar healthBar;

    private EventQueueBroadcaster eventQueueBroadcaster;


    public HUD(final PlayingScreen belongsToScreen) {
        this.belongsToScreen = belongsToScreen;

        VisUI.load();

        stage = new Stage();

        //stage.setDebugAll(true);

        // Setup bag ui
        bagWindow = new VisWindow("Bag", true);
        VisTable bagTable = new VisTable(true);
        final int bagCellSize = 30;
        bagTable.debugCell();
        VisImage item = new VisImage(new TextureRegion(
                new Texture(Gdx.files.internal("items/items.png")), 16, 16));
        bagTable.add().size(bagCellSize, bagCellSize).setActor(item);
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

        // Setup health & mana bars
        healthBar = new VisProgressBar(0, 100, 1, false);
        healthBar.setValue(60);
        healthBar.setColor(new Color(1, 1, 0, 1));
        healthBar.setAnimateInterpolation(Interpolation.smooth);
        healthBar.setAnimateDuration(.8f);

        VisProgressBar manaBar = new VisProgressBar(0, 100, 1, false);
        manaBar.setValue(30);
        manaBar.setColor(new Color(1, 0, 1, 1));
        VisSplitPane barsPane = new VisSplitPane(healthBar, manaBar, true);
        healthBar.setAnimateInterpolation(Interpolation.smooth);
        healthBar.setAnimateDuration(.8f);

        final int barsTopPadding = 15;
        barsPane.setPosition(10, (Game.WINDOW_HEIGHT - barsPane.getHeight()) - barsTopPadding);

        stage.addActor(barsPane);
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
            healthBar.setValue(healthBar.getValue() - 10);

            if (null == bagWindow.getStage()) {
                stage.addActor(bagWindow);

                Gdx.input.setInputProcessor(stage);
            }
            else {
                bagWindow.remove();

                Gdx.input.setInputProcessor(belongsToScreen.getPlayer());
            }
    	}

    	if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
    		//
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

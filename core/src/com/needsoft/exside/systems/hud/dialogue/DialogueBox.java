package com.needsoft.exside.systems.hud.dialogue;

import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.needsoft.exside.Game;

public class DialogueBox extends VisTable {

    private final float TIME_PER_CHARACTER = .05f;

    private State state;
    private String targetText;

    private float animationTimer;
    private float animationTotalTime;

    private VisLabel textLabel;

    private enum State {
        IDLE,
        ANIMATING
    }
    
    public DialogueBox(final String text) {
        targetText = text;
        state = State.ANIMATING;
        animationTimer = .0f;
        animationTotalTime = text.length() * TIME_PER_CHARACTER;

        setFillParent(false);
        setWidth(Game.WINDOW_WIDTH);
        setHeight(100);

        textLabel = new VisLabel();
        textLabel.setAlignment(Align.center);
        textLabel.setWrap(true);
        final int lateralPadding = 150;
        // TODO Add padding
        add(textLabel)
                .width(getWidth());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        
        if (state == State.ANIMATING) {
            animationTimer += delta;

            if (animationTimer > animationTotalTime) {
                state = State.IDLE;
                animationTimer = animationTotalTime;
            }

            StringBuilder displayedText = new StringBuilder();

            int charactersToDisplay = (int) ((animationTimer / animationTotalTime) * targetText.length());
            for (int i = 0; i < charactersToDisplay; i++) {
                displayedText.append(targetText.charAt(i));
            }

            if (!displayedText.equals(textLabel.getText().toString())) {
                textLabel.setText(displayedText.toString());
            }
        }
    }

    public boolean isFinished() {
        return State.IDLE == state && animationTimer == animationTotalTime;
    }

}

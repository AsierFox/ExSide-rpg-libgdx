package com.needsoft.exside.systems.dialogue;

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
        textLabel = new VisLabel();

        setFillParent(false);

        debug();

        add(textLabel);
        
        setWidth(Game.WINDOW_WIDTH);
        setHeight(100);
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
                setText(displayedText.toString());
            }
        }
    }

    private void setText(String text) {
        if (!text.contains("\n")) {
            text += "\n";
        }
        textLabel.setText(text);
    }

    public boolean isFinished() {
        return State.IDLE == state && animationTimer == animationTotalTime;
    }

}

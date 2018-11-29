package com.needsoft.exside.systems.hud;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Queue;

public class EventQueueBroadcaster {

	private final int BOX_HEIGHT = 30;
	
	private Skin skin;
	private BitmapFont font;
	private NinePatch patch;
	
	private GlyphLayout layout;
	
	private Queue<Object> eventQueue;
	
	
	public EventQueueBroadcaster(Skin skin, Queue<Object> queue) {
		this.skin = skin;
		this.eventQueue = queue;
		patch = skin.getPatch("optionbox");
		font = skin.getFont("font");
		layout = new GlyphLayout();
	}
	
	public void render(SpriteBatch batch, Object event) {
		if (null == event) {
			return;
		}
		
		renderEvent(batch, event.getClass().getSimpleName(), 0);
		
		float y = BOX_HEIGHT + 10f;
		for (Object e : eventQueue) {
			renderEvent(batch, e.getClass().getSimpleName(), y);
			y += BOX_HEIGHT;
		}
	}
	
	private void renderEvent(SpriteBatch batch, String text, float y) {
		layout.setText(font, text);
		float textWidth = layout.width;
		float textHeight = layout.height;
		
		patch.draw(batch, 0, y, textWidth+10, BOX_HEIGHT);
		font.draw(batch, text, (textWidth + 10) / 2 - textWidth / 2, BOX_HEIGHT / 2 + textHeight / 2 + y);
	}

}

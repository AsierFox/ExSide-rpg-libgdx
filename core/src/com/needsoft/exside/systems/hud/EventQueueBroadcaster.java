package com.needsoft.exside.systems.hud;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Queue;

public class EventQueueBroadcaster {

	private final int BOX_HEIGHT = 30;
	
	public GlyphLayout layout;
	
	private Queue<Object> eventQueue;
	
	
	public EventQueueBroadcaster(Queue<Object> queue) {
		this.eventQueue = queue;
		layout = new GlyphLayout();
	}
	
	public void render(Batch batch, Object event) {
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
	
	private void renderEvent(Batch batch, String text, float y) {
		float textWidth = layout.width;
		float textHeight = layout.height;
	}

}

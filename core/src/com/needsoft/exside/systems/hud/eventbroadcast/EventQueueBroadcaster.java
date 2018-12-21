package com.needsoft.exside.systems.hud.eventbroadcast;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Queue;
import com.kotcrab.vis.ui.widget.VisScrollPane;

public class EventQueueBroadcaster extends VisScrollPane {

	private Queue<String> eventQueue;
	
	
	public EventQueueBroadcaster(Actor actor, ScrollPaneStyle style) {
		super(actor, style);
		eventQueue = new Queue<String>();
	}
	
	public void render(Batch batch, Object event) {
	}

}

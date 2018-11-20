package com.needsoft.exside.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.needsoft.exside.Game;

public class SplashScreen  extends ScreenAdapter {
	
	private static final float SPLASH_SECONDS_TOTAL_DURATION = 4.2f;
	
	private Game belongsToGame;
	
	private OrthographicCamera camera;
	
	private Stage stage;

	Texture splashTexture;
	private Image splashImage;
	private final int splashImageYMove = 20;

	private float elapsed;
	
	
	public SplashScreen(final Game belongsToGame) {
		this.belongsToGame = belongsToGame;
		
		camera = new OrthographicCamera();
		
		stage = new Stage(new FitViewport(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT, camera));
		Gdx.input.setInputProcessor(stage);
		
		splashTexture = new Texture(Gdx.files.internal("icon/logo-needsoft.png"));
		splashImage = new Image(splashTexture);
		splashImage.setOrigin(splashImage.getWidth() / 2,splashImage.getWidth() / 2);
		splashImage.setScale(.25f);
		
		stage.addActor(splashImage);

		elapsed = 0f;
	}
	
	@Override
	public void show() {
		splashImage.setPosition(0 * .35f,  0 * .35f);
		
		splashImage.addAction(Actions.sequence(Actions.alpha(0f),
				Actions.parallel(
						Actions.moveBy(0, -splashImageYMove, 2f, Interpolation.pow2Out),
						Actions.fadeIn(2f),
						Actions.scaleBy(.10f, .10f, 3f)),
				Actions.delay(1.2f),
				Actions.fadeOut(1f)));
	}
	
	@Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
		update(delta);
		
		stage.draw();
    }
	
	private void update(final float delta) {
		stage.act(delta);
		
		elapsed += delta;
		
		if (elapsed > SPLASH_SECONDS_TOTAL_DURATION) {
			belongsToGame.setScreen(new LoadingScreen(belongsToGame));
		}
	}
	
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, false);
	}
	
	@Override
	public void dispose() {
		splashTexture.dispose();
		stage.dispose();
	}
	
}

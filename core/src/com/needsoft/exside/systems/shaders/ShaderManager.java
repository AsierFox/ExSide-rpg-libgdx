package com.needsoft.exside.systems.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class ShaderManager {
	
	private ShaderProgram shader;
	

	public ShaderManager() {
		// We don't need to have all attributes due to shaders are simple
		ShaderProgram.pedantic = false;
		shader = new ShaderProgram(Gdx.files.internal("shaders/camera-shake.vsh"), Gdx.files.internal("shaders/camera-shake.fsh"));
		System.out.println("Shader compiled: " + shader.isCompiled() + " Errors => [" + shader.getLog() + "]");
	}
	
	public void update() {

		// For camera shake shader
//		shader.begin();
//		shader.setUniformf("u_distort", MathUtils.random(4), MathUtils.random(4), 0);
//		shader.end();
	}
	
	public ShaderProgram getShader() {
		return shader;
	}
	
	public void dispose() {
		shader.dispose();
	}
	
}

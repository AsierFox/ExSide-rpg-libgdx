uniform sampler2D u_texture;

varying vec4 v_color;
varying vec2 v_texCoord0;

void main() {
	gl_FragColor = texture2D(u_texture, v_texCoord0) * vec4(1., 1., 0, 1.);
}

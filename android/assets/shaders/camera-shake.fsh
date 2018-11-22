uniform sampler2D u_sampler2D;

varying vec4 v_color;
varying vec2 v_textCoord0;

void main() {
	vec4 originalColor = texture2D(u_sampler2D, v_textCoord0) * v_color;
	gl_FragColor = originalColor;
}

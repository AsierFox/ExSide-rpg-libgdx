uniform mat4 u_projTrans;
uniform vec3 u_distort;

attribute vec4 a_color;
attribute vec3 a_position;
attribute vec2 a_textCoord0;

varying vec4 v_color;
varying vec2 v_textCoord0;

void main() {
	v_color = a_color;
	v_textCoord0 = a_textCoord0;
	
	gl_Position = u_projTrans * vec4(a_position + u_distort, 1.); 
}

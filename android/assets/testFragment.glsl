#ifdef GL_ES
#define LOWP lowp
#define MED mediump
#define HIGH highp
precision mediump float;
#else
#define MED
#define LOWP
#define HIGH
#endif

varying LOWP vec4 v_color;
varying vec2 v_texCoords;
varying vec2 v_resolution;


uniform sampler2D u_texture;
uniform float u_globalTime;

#define FLIP_IMAGE

float rand(vec2 uv) {

    float a = dot(uv, vec2(92., 80.));
    float b = dot(uv, vec2(41., 62.));

    float x = sin(a) + cos(b) * 51. ;
    return fract(x);

}


void main() {
     vec2 uv = v_texCoords;
	vec2 rnd = vec2(rand(uv), rand(uv));

    uv += rnd * .02 *u_globalTime;
    gl_FragColor = texture2D(u_texture, uv);









}



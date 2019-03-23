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


vec2 barrelDistortion(vec2 coord, float amt) {
       	vec2 cc = coord - 0.5;
       	float dist = dot(cc, cc);
       	//return coord + cc * (dist*dist)  * amt;
       	return coord + cc * dist * amt*u_globalTime;

     }

/*vec2 Circle(float Start, float Points, float Point)
{
	float Rad = (3.141592 * 2.0 * (1.0 / Points)) * (Point + Start);
	return vec2(sin(Rad), cos(Rad))*2.0 * sin(u_globalTime);
}*/
    

void main(){

        vec2 uv = v_texCoords;

    	vec4 a1=texture2D(u_texture, barrelDistortion(uv,0.0));
    	vec4 a2=texture2D(u_texture, barrelDistortion(uv,0.2));


    	vec4 a3=texture2D(u_texture, barrelDistortion(uv,0.4));
    	vec4 a4=texture2D(u_texture, barrelDistortion(uv,0.6));


    	vec4 a5=texture2D(u_texture, barrelDistortion(uv,0.8));
    	vec4 a6=texture2D(u_texture, barrelDistortion(uv,1.));


    	vec4 tx=(a1+a2+a3+a4+a5+a6)/6.;


	gl_FragColor = vec4(tx.rgb,1.);





	//other shader;

	  /*  vec2 uv = v_texCoords;
        vec2 PixelOffset = 1.0 / vec2(480.0,800.0);

        float Start = 2.0 / 14.0;
    	vec2 Scale = 0.66 * 4.0 * 5.0 * PixelOffset.xy;

        vec3 N0 = texture2D(u_texture, uv + Circle(Start, 14.0, 0.0) * Scale).rgb;
        vec3 N1 = texture2D(u_texture, uv + Circle(Start, 14.0, 4.0) * Scale).rgb;
        vec3 N2 = texture2D(u_texture, uv + Circle(Start, 14.0, 8.0) * Scale).rgb;
        vec3 N3 = texture2D(u_texture, uv + Circle(Start, 14.0, 12.0) * Scale).rgb;
        vec3 N4 = texture2D(u_texture, uv + Circle(Start, 14.0, 8.0) * Scale).rgb;
        vec3 N5 = texture2D(u_texture, uv + Circle(Start, 14.0, 9.0) * Scale).rgb;
        vec3 N6 = texture2D(u_texture, uv).rgb;

        float W = 1.0 / 7.0;

        vec3 color = vec3(0,0,0);

    	color.rgb =
    		(N0 * W) +
    		(N1 * W) +
    		(N2 * W) +
    		(N3 * W) +
    		(N4 * W) +
    		(N5 * W) +
    		(N6 * W) ;

        gl_FragColor = vec4(color.rgb,1.0);*/
	
	

}



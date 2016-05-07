package util;

import android.graphics.Rect;

public class MyRect {
	int Fieldram;
	int gamefieldwidth;
	int gamefieldheight;
	int width;
	int height;
	public MyRect(int Fieldram, int gamefieldwidth, int gamefieldheight, int width, int height) {
		this.Fieldram = Fieldram;
		this.gamefieldheight = gamefieldheight;
		this.gamefieldwidth = gamefieldwidth;
		this.height = height;
		this.width = width;
	}
	public Rect getRect(String rectname, Rect rect){
		switch(rectname){
		case "gamefield": rect = new Rect(Fieldram, Fieldram, gamefieldwidth+Fieldram, gamefieldheight); break;
		case "nextfield": rect = new Rect(gamefieldwidth+2*Fieldram, Fieldram,width-Fieldram, (width-gamefieldwidth - Fieldram)); break;
		case "scorefield": rect = new Rect(gamefieldwidth+2*Fieldram,(width-gamefieldwidth), width-Fieldram, (width-gamefieldwidth + Fieldram + this.getRect("nextfield", rect).height()) ); break;
		case "button": rect = new Rect(3*Fieldram,gamefieldheight+Fieldram,width-3*Fieldram,height-Fieldram); break;
		case "field": rect = new Rect(0,0,width,height); break;
		default: rect = null;
		}
		return rect;
	}
}

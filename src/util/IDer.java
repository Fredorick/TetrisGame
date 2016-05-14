package util;

import android.content.res.Resources;

public class IDer {
	Resources res;
	int id;
	String numb;
	Drawer drawer;
	public IDer(Resources res) {
		this.res = res;
		drawer = new Drawer();
	}
	public int GetId(String s){
		s+=drawer.GetTheme();
		id = res.getIdentifier(s, "drawable", "com.example.game");
		return id;
	}

}

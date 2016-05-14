package util;

import java.util.ArrayList;

public class Drawer {
	ArrayList<String> theme = new ArrayList<String>();
	public static int i = 0;
public Drawer() {
	i = 0;
	theme.add(0, "_classic");
	theme.add(1, "_toxic");
}
public String GetTheme(){
	CheckTheme();
	return theme.get(i);
}
public void CheckTheme(){
}
}

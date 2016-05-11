package util;

import java.util.ArrayList;

public class Drawer {
	ArrayList<String> theme = new ArrayList<String>();
	int i;
public Drawer() {
	i = 0;
	theme.add(0, "_toxic");
	theme.add(1, "_classic");
}
public String GetTheme(){
	CheckTheme();
	return theme.get(i);
}
public void CheckTheme(){
	i = 0;
}
}

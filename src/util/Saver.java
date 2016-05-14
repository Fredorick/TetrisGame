package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.widget.Toast;

public class Saver {
	Context context;
	
	public Saver(Context context) {
		this.context =  context;
	}
	public void SaveBlocks(int numb, int x, int y, int form){
		String[] blocks = new String[numb];
		for(int i = 0; i < numb; i++){
			
		}
	}
	  public void saveFile(String[] s, String filename) {
	        try {
	            OutputStream outputStream = context.openFileOutput(filename, 0);
	            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
	            for(int i = 0; i< s.length; i++){
	            osw.write(s[i]);
	            }
	            osw.close();
	        } catch (Throwable t) {
	            Toast.makeText(context,
	                    "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
	        }
	    }
	  public String[] openFile(String fileName) {
	        try {
	            InputStream inputStream = context.openFileInput(fileName);

	            if (inputStream != null) {
	                InputStreamReader isr = new InputStreamReader(inputStream);
	                BufferedReader reader = new BufferedReader(isr);
	                String line;
	                StringBuilder builder = new StringBuilder();

	                while ((line = reader.readLine()) != null) {
	                    builder.append(line + "\n");
	                }

	                inputStream.close();
	                //mEditText.setText(builder.toString());
	            }
	        } catch (Throwable t) {
	            Toast.makeText(context,
	                    "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
	        }
			return null;
	    }

}

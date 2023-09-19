package kr.co.enders.engine.util;

import java.util.ResourceBundle;

public class ResourceManager {
	
	private ResourceManager() {
		
	}
	
	public static String GetResource(String key) {
		ResourceBundle rb = ResourceBundle.getBundle("kr.co.enders.engine.resources.properties.AxonEngine");
		return rb.getString(key);
	}

}

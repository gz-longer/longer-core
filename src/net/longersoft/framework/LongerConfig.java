package net.longersoft.framework;

public enum LongerConfig {
	Home("longer.home"), 
	Lib("longer.lib"),
	Log("longer.log");
	
	private String key;
	LongerConfig(final String key){
		this.key = key;
	}
	
	@Override
	public String toString() {
		return System.getProperty(this.key);
	}
	
	public boolean check(){
		return System.getProperty(this.key) != null;
	}
}

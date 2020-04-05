package com.xlljoy.o2o.util;

import java.io.InputStream;

public class ImageUnit {
	public InputStream image;
	public String name;
	
	public ImageUnit() {}
	public ImageUnit(InputStream image, String name) {
		this.image = image;
		this.name = name;
	}
	public InputStream getImage() {
		return image;
	}
	public void setImage(InputStream image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}

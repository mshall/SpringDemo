package com.demo.utils;

import java.io.File;
import java.io.IOException;

public class FileUtils {
	/*
	 * @Method name: clearFile
	 * 
	 * @Return: boolean
	 * 
	 * @Description: This method clears file given specific path
	 * 
	 * @param: No param
	 */
	public boolean clearFile(String filePath) {
		File file = new File(filePath);
		if (file.delete()) {
			try {
				file.createNewFile();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;

	}
}

package org.xi.quick.filemanager.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.Part;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	private final static int BUFFER_SIZE = 1024;

	public static void saveMultipartFile(MultipartFile multipartFile, String directory, String name) {
		
		File tempDirectory = new File(directory);
		if (!tempDirectory.exists()) {
			tempDirectory.mkdirs();
		}
		
		try (InputStream in = multipartFile.getInputStream();
				OutputStream out = new FileOutputStream(directory + name);) {

			byte buffer[] = new byte[BUFFER_SIZE];
			int len;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void savePart(Part part, String directory, String name) {

		File tempDirectory = new File(directory);
		if (!tempDirectory.exists()) {
			tempDirectory.mkdirs();
		}
		
		try (InputStream in = part.getInputStream();
				FileOutputStream out = new FileOutputStream(directory + name);) {

			byte buffer[] = new byte[BUFFER_SIZE];
			int len;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

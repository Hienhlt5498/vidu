package com.thucpham.shopweb.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class WriteFile {
	
	public static final String root = System.getProperty("user.dir")+"/src/main/resources/static";
	public static final String client = "C:" + File.separator + "Users" + File.separator + "DOANMANH" + File.separator + "eclipse-workspace" + File.separator + "thucpham-client-master" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static";

	public void writeBase64ToFile(byte[] bytes, String path) {
		System.out.println(root);
		System.out.println(client);
		File file = new File(StringUtils.substringBeforeLast(root + path, "/"));
		File file2 = new File(StringUtils.substringBeforeLast(client + path, "/"));
		if (!file.exists()) {
			file.mkdir();
		}
		
		if (!file2.exists()) {
			file2.mkdir();
		}
		try (FileOutputStream fileOutputStream = new FileOutputStream(new File(root + path))) {
			fileOutputStream.write(bytes);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try (FileOutputStream fileOutputStream = new FileOutputStream(new File(client + path))) {
			fileOutputStream.write(bytes);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void writeBase64ToFileAvatar(byte[] bytes, String path) {
		System.out.println(root);
		File file = new File(StringUtils.substringBeforeLast(root + path, "/"));
		if (!file.exists()) {
			file.mkdir();
		}
		
		
		try (FileOutputStream fileOutputStream = new FileOutputStream(new File(root + path))) {
			fileOutputStream.write(bytes);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
}

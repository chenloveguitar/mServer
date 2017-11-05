package com.magicmoble.luzhouapp.server.server_function;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Snippet {

	public static List<String> getFolder() {
		List<String> list = new ArrayList<String>();
		String path = "D:/"; // 路径
		File f = new File(path);
		if (!f.exists())

		{
			System.out.println(path + " not exists");

		}

		File fa[] = f.listFiles();
		for (int i = 0; i < fa.length; i++)

		{
			File fs = fa[i];
			if (fs.isDirectory()) {
				list.add(fs.getName());
			} else {
				System.out.println(fs.getName());
			}
		}
		return list;
	}

}

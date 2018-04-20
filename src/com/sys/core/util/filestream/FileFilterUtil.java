package com.sys.core.util.filestream;
import java.io.File;
import java.io.FileFilter;

public class FileFilterUtil {
	
	public static FileFilter getFileFilterForEndsWith(String endsWith){
		FileFilterEndsWith fileFilterEndsWith=new FileFilterEndsWith();
		fileFilterEndsWith.setEndsWith(endsWith);
		return fileFilterEndsWith;
	}

	static class FileFilterEndsWith implements FileFilter {
		
		public String endsWith;

		public void setEndsWith(String endsWith) {
			this.endsWith = endsWith;
		}

		@Override
		public boolean accept(File file) {

			if(file.isDirectory())
				return true;
			else
			{
				String name = file.getName();
				if(name.endsWith(endsWith))
					return true;
				else
					return false;
			}
			
		}

	}

}

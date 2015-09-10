package com.example.imagebutton;

import java.io.File;
import java.util.Calendar;

import android.net.Uri;
import android.util.Log;

public class ImageSelectGallary {

	String image;

	Uri URI;
	String NAME_OF_FILE = "";
	

	public ImageSelectGallary() {
		super();
		getFileUri(image);
	}


	public Uri getFileUri(String image) {

		File rootDir = new File("/mnt/sdcard/AndroidUploadData");
		File imageFile = null;
		if (!rootDir.exists()) {
			if (!rootDir.mkdirs())
				Log.v("error", "Sorry directory cannot be created");
			else {
				String fileName = "IMG_" + "UploadTest"
						+ Calendar.getInstance().getTimeInMillis() + ".jpg";
				NAME_OF_FILE = fileName;
				imageFile = new File(rootDir.getPath() + File.separator
						+ fileName);

			}
			return Uri.fromFile(imageFile);
		} else {
			String fileName = "IMG_" + "UploadTest"
					+ Calendar.getInstance().getTimeInMillis() + ".jpg";
			NAME_OF_FILE = fileName;
			imageFile = new File(rootDir.getPath() + File.separator + fileName);
			System.out.println();
			return Uri.fromFile(imageFile);

		}

	}

}

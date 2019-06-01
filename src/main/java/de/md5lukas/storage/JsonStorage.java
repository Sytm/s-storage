package de.md5lukas.storage;

import de.md5lukas.storage.util.DataIOUtil;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class JsonStorage extends MapBasedStorageContainer {


	@Override
	public void load(File file, boolean compressed) throws IOException {
		root.clear();
		root = new JSONObject(DataIOUtil.readFile(file)).toMap();
	}

	@Override
	public void save(File file, boolean compressed) throws IOException {
		DataIOUtil.writeFile(file, new JSONObject(root).toString());
	}
}

package de.md5lukas.storage;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This is a implementation of {@link StorageContainer} using a map as a backing data structure and saving and loading
 * it to and from json files using <a href="https://search.maven.org/artifact/org.json/json/20180813/bundle">JSON-java</a>
 *
 * @author Md5Lukas
 */
public class JsonStorage extends AbstractMapBasedStorageContainer {

	/**
	 * Loads the data from the specified file as a raw text file
	 *
	 * @param file       The file where the data should be loaded from
	 * @param compressed This parameter is ignored, as text files cannot be compressed
	 * @throws IOException If an I/O exception occurred
	 */
	@Override
	public void load(File file, boolean compressed) throws IOException {
		map.clear();
		map = new JSONObject(new JSONTokener(new FileReader(file))).toMap();
	}

	/**
	 * This saves the data to the specified as a raw text file
	 *
	 * @param file       The file where the data should be written to
	 * @param compressed This parameter is ignored, as text files cannot be compressed
	 * @throws IOException If an I/O exception occurred
	 */
	@Override
	public void save(File file, boolean compressed) throws IOException {
		try (FileWriter writer = new FileWriter(file)) {
			new JSONObject(map).write(writer);
		}
	}
}

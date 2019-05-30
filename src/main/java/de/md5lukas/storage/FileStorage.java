package de.md5lukas.storage;

import java.io.File;
import java.io.IOException;

public interface FileStorage {


	/**
	 * Loads the data from the specified file and treats it as uncompressed
	 *
	 * @param file The file where the data should be loaded from
	 * @throws IOException If an I/O exception occurred
	 */
	void load(File file) throws IOException;

	/**
	 * Loads the data from the specified file and treats it either as uncompressed
	 * or compressed
	 *
	 * @param file       The file where the data should be loaded from
	 * @param compressed Whether the file is compressed or not
	 * @throws IOException If an I/O exception occurred
	 */
	void load(File file, boolean compressed) throws IOException;

	/**
	 * This saves the data to the specified file uncompressed
	 *
	 * @param file The file where the data should be written to
	 * @throws IOException If an I/O exception occurred
	 * @see #save(File, boolean) This is called with <code>false</code> as its second argument
	 */
	void save(File file) throws IOException;

	/**
	 * This saves the data to the specified file either compressed or not
	 *
	 * @param file       The file where the data should be written to
	 * @param compressed Whether the data should be compressed or not
	 * @throws IOException If an I/O exception occurred
	 */
	void save(File file, boolean compressed) throws IOException;
}

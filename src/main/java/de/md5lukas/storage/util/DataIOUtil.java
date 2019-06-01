package de.md5lukas.storage.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class DataIOUtil {

	public static void writeString(DataOutput dataOutput, String string) throws IOException {
		byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
		dataOutput.writeInt(bytes.length);
		dataOutput.write(bytes);
	}

	public static String readString(DataInput dataInput) throws IOException {
		byte[] bytes = new byte[dataInput.readInt()];
		dataInput.readFully(bytes);
		return new String(bytes, StandardCharsets.UTF_8);
	}

	public static String readFile(File file) throws IOException {
		return new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
	}

	public static void writeFile(File file, String string) throws IOException {
		Files.write(file.toPath(), string.getBytes(StandardCharsets.UTF_8));
	}
}

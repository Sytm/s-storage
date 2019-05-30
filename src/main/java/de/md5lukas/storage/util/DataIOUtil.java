package de.md5lukas.storage.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
}

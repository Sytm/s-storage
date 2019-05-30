import de.md5lukas.storage.util.DataIOUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataIOUtilTest {

	private void stringTest(String s) throws IOException {
		byte[] bytes;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); DataOutputStream dos = new DataOutputStream(baos)) {
			DataIOUtil.writeString(dos, s);
			bytes = baos.toByteArray();
		}
		try (DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes))) {
			assertEquals(s, DataIOUtil.readString(dis));
		}
	}

	@Test
	@ExtendWith(IgnoreIOExceptionExtension.class)
	void emptyStringTest() throws IOException {
		stringTest("");
	}

	@Test
	@ExtendWith(IgnoreIOExceptionExtension.class)
	void smallStringTest() throws IOException {
		stringTest("SmallString");
	}

	@Test
	@ExtendWith(IgnoreIOExceptionExtension.class)
	void mediumStringTest() throws IOException {
		stringTest(repeatString("Z", 1000));
	}

	@Test
	@ExtendWith(IgnoreIOExceptionExtension.class)
	void longStringTest() throws IOException {
		stringTest(repeatString("Z", 100000));
	}

	@Test
	@ExtendWith(IgnoreIOExceptionExtension.class)
	void allUnicodeCharactersTest() throws IOException {
		stringTest(allUnicodeChars());
	}

	private String repeatString(String s, int amount) {
		StringBuilder builder = new StringBuilder();
		while (--amount >= 0)
			builder.append(s);
		return builder.toString();
	}

	private String allUnicodeChars() {
		StringBuilder builder = new StringBuilder();
		for (int i = 1; i < 0xd088; ++i) {
			builder.append((char) i);
		}
		return builder.toString();
	}
}

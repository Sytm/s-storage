import de.md5lukas.storage.BinaryStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryStorageTest {

	private BinaryStorage bs;
	private List<File> createdFiles;

	@BeforeEach
	void init() {
		createdFiles = new ArrayList<>();
		bs = new BinaryStorage("root");
		bs.set("booleanT", true);
		bs.set("booleanF", false);
		bs.set("byte", (byte) 9);
		bs.set("short", (short) 23523);
		bs.set("int", 124975);
		bs.set("long", 2435120217L);
		bs.set("float", 242.24521f);
		bs.set("double", 3512.462354);
		bs.set("string", "AString");
		bs.set("list.string", Arrays.asList("String 0", "String 1"));
		bs.set("list.byte", Arrays.asList(Byte.MIN_VALUE, Byte.MAX_VALUE));
		bs.set("list.short", Arrays.asList(Short.MIN_VALUE, Short.MAX_VALUE));
		bs.set("list.int", Arrays.asList(Integer.MIN_VALUE, Integer.MAX_VALUE));
		bs.set("list.long", Arrays.asList(Long.MIN_VALUE, Long.MAX_VALUE));
		bs.set("list.float", Arrays.asList(Float.MIN_VALUE, Float.MAX_VALUE));
		bs.set("list.double", Arrays.asList(Double.MIN_VALUE, Double.MAX_VALUE));
	}

	@AfterEach
	void fileCleanUp() {
		for (File file : createdFiles)
			if (file.isFile())
				file.delete();
	}

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	@Test
	void testValues() {
		assertDoesNotThrow(() -> {
			assertTrue(bs.getBoolean("booleanT").get());
			assertFalse(bs.getBoolean("booleanF").get());

			assertEquals((byte) 9, bs.getByte("byte").get());
			assertEquals((short) 23523, bs.getShort("short").get());
			assertEquals(124975, bs.getInt("int").get());
			assertEquals(2435120217L, bs.getLong("long").get());
			assertEquals(242.24521f, bs.getFloat("float").get());
			assertEquals(3512.462354, bs.getDouble("double").get());
			assertEquals("AString", bs.getString("string").get());

			compareLists(Arrays.asList("String 0", "String 1"), bs.getStringList("list.string").get());
			compareLists(Arrays.asList(Byte.MIN_VALUE, Byte.MAX_VALUE), bs.getByteList("list.byte").get());
			compareLists(Arrays.asList(Short.MIN_VALUE, Short.MAX_VALUE), bs.getShortList("list.short").get());
			compareLists(Arrays.asList(Integer.MIN_VALUE, Integer.MAX_VALUE), bs.getIntList("list.int").get());
			compareLists(Arrays.asList(Long.MIN_VALUE, Long.MAX_VALUE), bs.getLongList("list.long").get());
			compareLists(Arrays.asList(Float.MIN_VALUE, Float.MAX_VALUE), bs.getFloatList("list.float").get());
			compareLists(Arrays.asList(Double.MIN_VALUE, Double.MAX_VALUE), bs.getDoubleList("list.double").get());
		});
	}

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	@Test
	void testPathPrefix() {
		assertDoesNotThrow(() -> {
			bs.setPathPrefix("list.");
			compareLists(Arrays.asList("String 0", "String 1"), bs.getStringList("string").get());
			compareLists(Arrays.asList(Byte.MIN_VALUE, Byte.MAX_VALUE), bs.getByteList("byte").get());
			compareLists(Arrays.asList(Short.MIN_VALUE, Short.MAX_VALUE), bs.getShortList("short").get());
			compareLists(Arrays.asList(Integer.MIN_VALUE, Integer.MAX_VALUE), bs.getIntList("int").get());
			compareLists(Arrays.asList(Long.MIN_VALUE, Long.MAX_VALUE), bs.getLongList("long").get());
			compareLists(Arrays.asList(Float.MIN_VALUE, Float.MAX_VALUE), bs.getFloatList("float").get());
			compareLists(Arrays.asList(Double.MIN_VALUE, Double.MAX_VALUE), bs.getDoubleList("double").get());
			bs.resetPathPrefix();

			compareLists(Arrays.asList("String 0", "String 1"), bs.getStringList("list.string").get());
			compareLists(Arrays.asList(Byte.MIN_VALUE, Byte.MAX_VALUE), bs.getByteList("list.byte").get());
			compareLists(Arrays.asList(Short.MIN_VALUE, Short.MAX_VALUE), bs.getShortList("list.short").get());
			compareLists(Arrays.asList(Integer.MIN_VALUE, Integer.MAX_VALUE), bs.getIntList("list.int").get());
			compareLists(Arrays.asList(Long.MIN_VALUE, Long.MAX_VALUE), bs.getLongList("list.long").get());
			compareLists(Arrays.asList(Float.MIN_VALUE, Float.MAX_VALUE), bs.getFloatList("list.float").get());
			compareLists(Arrays.asList(Double.MIN_VALUE, Double.MAX_VALUE), bs.getDoubleList("list.double").get());
		});
	}

	@Test
	void saveAndLoadTest() throws IOException {
		File file = new File("src/test/resources/test.nbt");
		createdFiles.add(file);
		String value = bs.toString();
		bs.save(file);
		bs.load(file);
		assertEquals(value, bs.toString());
	}

	@Test
	void saveAndLoadCompressedTest() throws IOException {
		File file = new File("src/test/resources/test.nbt");
		createdFiles.add(file);
		String value = bs.toString();
		bs.save(file, true);
		bs.load(file, true);
		assertEquals(value, bs.toString());
	}

	private <T> void compareLists(List<T> expected, List<T> actual) {
		assertNotNull(actual);
		assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); ++i) {
			assertEquals(expected.get(i), actual.get(i));
		}
	}
}

import de.md5lukas.storage.YamlStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class YamlStorageTest {

	private YamlStorage storage;
	private List<File> createdFiles;

	@BeforeEach
	void init() {
		createdFiles = new ArrayList<>();
		storage = new YamlStorage();
		storage.set("booleanT", true);
		storage.set("booleanF", false);
		storage.set("byte", (byte) 9);
		storage.set("short", (short) 23523);
		storage.set("int", 124975);
		storage.set("long", 2435120217L);
		storage.set("float", 242.24521f);
		storage.set("double", 3512.462354);
		storage.set("string", "AString");
		storage.set("list.string", Arrays.asList("String 0", "String 1"));
		storage.set("list.byte", Arrays.asList(Byte.MIN_VALUE, Byte.MAX_VALUE));
		storage.set("list.short", Arrays.asList(Short.MIN_VALUE, Short.MAX_VALUE));
		storage.set("list.int", Arrays.asList(Integer.MIN_VALUE, Integer.MAX_VALUE));
		storage.set("list.long", Arrays.asList(Long.MIN_VALUE, Long.MAX_VALUE));
		storage.set("list.float", Arrays.asList(Float.MIN_VALUE, Float.MAX_VALUE));
		storage.set("list.double", Arrays.asList(Double.MIN_VALUE, Double.MAX_VALUE));
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
			assertTrue(storage.getBoolean("booleanT").get());
			assertFalse(storage.getBoolean("booleanF").get());

			assertEquals((byte) 9, storage.getByte("byte").get());
			assertEquals((short) 23523, storage.getShort("short").get());
			assertEquals(124975, storage.getInt("int").get());
			assertEquals(2435120217L, storage.getLong("long").get());
			assertEquals(242.24521f, storage.getFloat("float").get());
			assertEquals(3512.462354, storage.getDouble("double").get());
			assertEquals("AString", storage.getString("string").get());

			compareLists(Arrays.asList("String 0", "String 1"), storage.getStringList("list.string").get());
			compareLists(Arrays.asList(Byte.MIN_VALUE, Byte.MAX_VALUE), storage.getByteList("list.byte").get());
			compareLists(Arrays.asList(Short.MIN_VALUE, Short.MAX_VALUE), storage.getShortList("list.short").get());
			compareLists(Arrays.asList(Integer.MIN_VALUE, Integer.MAX_VALUE), storage.getIntList("list.int").get());
			compareLists(Arrays.asList(Long.MIN_VALUE, Long.MAX_VALUE), storage.getLongList("list.long").get());
			compareLists(Arrays.asList(Float.MIN_VALUE, Float.MAX_VALUE), storage.getFloatList("list.float").get());
			compareLists(Arrays.asList(Double.MIN_VALUE, Double.MAX_VALUE), storage.getDoubleList("list.double").get());
		});
	}

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	@Test
	void testPathPrefix() {
		assertDoesNotThrow(() -> {
			storage.setPathPrefix("list.");
			compareLists(Arrays.asList("String 0", "String 1"), storage.getStringList("string").get());
			compareLists(Arrays.asList(Byte.MIN_VALUE, Byte.MAX_VALUE), storage.getByteList("byte").get());
			compareLists(Arrays.asList(Short.MIN_VALUE, Short.MAX_VALUE), storage.getShortList("short").get());
			compareLists(Arrays.asList(Integer.MIN_VALUE, Integer.MAX_VALUE), storage.getIntList("int").get());
			compareLists(Arrays.asList(Long.MIN_VALUE, Long.MAX_VALUE), storage.getLongList("long").get());
			compareLists(Arrays.asList(Float.MIN_VALUE, Float.MAX_VALUE), storage.getFloatList("float").get());
			compareLists(Arrays.asList(Double.MIN_VALUE, Double.MAX_VALUE), storage.getDoubleList("double").get());
			storage.resetPathPrefix();

			compareLists(Arrays.asList("String 0", "String 1"), storage.getStringList("list.string").get());
			compareLists(Arrays.asList(Byte.MIN_VALUE, Byte.MAX_VALUE), storage.getByteList("list.byte").get());
			compareLists(Arrays.asList(Short.MIN_VALUE, Short.MAX_VALUE), storage.getShortList("list.short").get());
			compareLists(Arrays.asList(Integer.MIN_VALUE, Integer.MAX_VALUE), storage.getIntList("list.int").get());
			compareLists(Arrays.asList(Long.MIN_VALUE, Long.MAX_VALUE), storage.getLongList("list.long").get());
			compareLists(Arrays.asList(Float.MIN_VALUE, Float.MAX_VALUE), storage.getFloatList("list.float").get());
			compareLists(Arrays.asList(Double.MIN_VALUE, Double.MAX_VALUE), storage.getDoubleList("list.double").get());
		});
	}

	private <T> void compareLists(List<T> expected, List<T> actual) {
		assertNotNull(actual);
		assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); ++i) {
			assertEquals(expected.get(i), actual.get(i));
		}
	}

	@Test
	void saveAndLoadTest() throws IOException {
		File file = new File("src/test/resources/test.yaml");
		createdFiles.add(file);
		String value = storage.toString();
		storage.save(file);
		storage.load(file);
		assertEquals(value, storage.toString());
	}
}

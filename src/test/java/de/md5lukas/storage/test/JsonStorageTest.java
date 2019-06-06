package de.md5lukas.storage.test;

import de.md5lukas.storage.JsonStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonStorageTest {

	private JsonStorage storage;

	@BeforeEach
	void init() {
		storage = new JsonStorage();
		StorageContainerCreator.fillStorageContainer(storage);
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
		File file = new File("test.json");
		try {
			String value = storage.toString();
			storage.save(file);
			storage.load(file);
			assertEquals(value, storage.toString());
		} finally {
			file.delete();
		}
	}
}

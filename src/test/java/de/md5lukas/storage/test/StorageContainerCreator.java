package de.md5lukas.storage.test;

import de.md5lukas.storage.StorageContainer;

import java.util.Arrays;

public class StorageContainerCreator {

	public static <T extends StorageContainer> void fillStorageContainer(T storage) {
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
}

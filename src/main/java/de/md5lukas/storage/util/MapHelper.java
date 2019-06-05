package de.md5lukas.storage.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a helper class to convert from a map based tree structure to a path structure and vice versa
 *
 * @author Md5Lukas
 */
public class MapHelper {

	/**
	 * Retrieves a value from the map and its sub-maps using a dot separated path
	 *
	 * @param root The root map where the values are stored
	 * @param path The path to the value
	 * @return The found object or <code>null</code> if it not present
	 */
	public static Object get(Map<?, ?> root, String path) {
		String[] parts = path.split("\\.");
		Map<?, ?> parent = root;
		for (int i = 0; i < parts.length; ++i) {
			String name = parts[i];
			if (!parent.containsKey(name))
				return null;
			if (i == parts.length - 1) {
				return parent.get(name);
			} else {
				Object child = parent.get(name);
				if (child instanceof Map<?, ?>) {
					parent = (Map<?, ?>) child;
				} else {
					return null;
				}
			}
		}
		return null;
	}

	/**
	 * Sets a value in a map, and creates if necessary additional sub maps to create a tree-like structure based on a
	 * dot separated path
	 *
	 * @param root     The root map where every value and sub maps with its values will be put in
	 * @param path     The dot separated path where the value should be stored
	 * @param value    The value that should be stored
	 * @param override If the present value is a sub-map, whether it should be overridden or not
	 * @return <code>true</code> if the value has been set successfully
	 */
	@SuppressWarnings("unchecked")
	public static boolean set(Map<String, Object> root, String path, Object value, boolean override) {
		String[] parts = path.split("\\.");
		Map<String, Object> parent = root;
		for (int i = 0; i < parts.length; ++i) {
			String name = parts[i];
			if (i == parts.length - 1) {
				Object o = parent.get(name);
				if (o != null && value == null && !override)
					return false;
				if (o == null || value == null || value.getClass().equals(o.getClass()) || override) {
					parent.put(name, value);
					return true;
				} else {
					return false;
				}
			} else {
				if (parent.containsKey(name)) {
					Object child = parent.get(name);
					if (child instanceof Map<?, ?>) {
						parent = (Map<String, Object>) child;
					} else if (override) {
						Map<String, Object> map = new HashMap<>();
						parent.put(name, map);
						parent = map;
					} else {
						return false;
					}
				} else {
					Map<String, Object> map = new HashMap<>();
					parent.put(name, map);
					parent = map;
				}
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> cloneMap(Map<K, V> map) {
		Map<Object, Object> newMap = new HashMap<>(map.size());
		map.forEach((k, v) -> {
			if (v instanceof Map<?, ?>)
				newMap.put(k, cloneMap((Map<Object, Object>) v));
			else if (v instanceof List<?>)
				newMap.put(k, cloneList((List<Object>) v));
			else
				newMap.put(k, v);
		});
		return (Map<K, V>) newMap;
	}

	@SuppressWarnings("unchecked")
	private static List<Object> cloneList(List<Object> list) {
		List<Object> newList = new ArrayList<>(list.size());
		list.forEach(o -> {
			if (o instanceof Map<?, ?>)
				newList.add(cloneMap((Map<Object, Object>) o));
			else if (o instanceof List<?>)
				newList.add(cloneList((List<Object>) o));
			else
				newList.add(o);
		});
		return newList;
	}

	public static Map<String, Object> treeMapToFlatMap(Map<String, Object> map) {
		Map<String, Object> flatMap = new HashMap<>();
		treeMapToFlatMap("", flatMap, map);
		return flatMap;
	}

	@SuppressWarnings("unchecked")
	private static void treeMapToFlatMap(String currentPath, Map<String, Object> output, Map<String, Object> currentMap) {
		currentMap.forEach((s, o) -> {
			String path = (currentPath.isEmpty() ? "" : currentPath + ".") + s;
			if (o instanceof Map) {
				treeMapToFlatMap(path, output, (Map<String, Object>) o);
			} else {
				output.put(path, o);
			}
		});
	}
}

package de.md5lukas.storage.util;

import java.util.HashMap;
import java.util.Map;

public class MapHelper {

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
				if (o == null || value == null || override) {
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
}

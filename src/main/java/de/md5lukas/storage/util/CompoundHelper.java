package de.md5lukas.storage.util;

import de.md5lukas.nbt.Tag;
import de.md5lukas.nbt.tags.CompoundTag;

public class CompoundHelper {

	public static Tag getTag(CompoundTag root, String path) {
		String[] parts = path.split("\\.");
		CompoundTag parent = root;
		for (int i = 0; i < parts.length; ++i) {
			String name = parts[i];
			if (!parent.contains(name))
				return null;
			if (i == parts.length - 1) {
				return parent.get(name);
			} else {
				Tag child = parent.get(name);
				if (child instanceof CompoundTag)
					parent = (CompoundTag) child;
				else
					return null;
			}
		}
		return null;
	}

	/**
	 * @param root     The root tag
	 * @param tag      The tag to set
	 * @param override Override tag if its of different type
	 */
	public static boolean setTag(CompoundTag root, Tag tag, String path, boolean override) {
		String[] parts = path.split("\\.");
		CompoundTag parent = root;
		for (int i = 0; i < parts.length; ++i) {
			String name = parts[i];
			if (i == parts.length - 1) {
				Tag t = parent.get(name);
				if (t != null && tag == null && !override)
					return false;
				if (t == null || tag == null || t.getId() == tag.getId() || override) {
					parent.put(name, tag);
					return true;
				}
				return false;
			} else {
				if (parent.contains(name)) {
					Tag child = parent.get(name);
					if (child instanceof CompoundTag) {
						parent = (CompoundTag) child;
					} else if (override) {
						parent = parent.putCompound(name);
					} else {
						return false;
					}
				} else {
					parent = parent.putCompound(name);
				}
			}
		}
		return false;
	}
}

package de.md5lukas.storage.util;

import de.md5lukas.nbt.Tag;
import de.md5lukas.nbt.tags.CompoundTag;

/**
 * This class is a helper class to make it easier to convert between the tree structure from {@link CompoundTag}
 * to paths and vice versa
 *
 * @author Md5Lukas
 */
public class CompoundHelper {

	/**
	 * Retrieves a value from the {@link CompoundTag} and its sub-tags using a dot separated path
	 *
	 * @param root The root compound tag where the all the tags are stored
	 * @param path The path to the tag
	 * @return The found tag or <code>null</code> if it not present
	 */
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
	 * Sets a value in a {@link CompoundTag}, and creates, if necessary, additional compound tags to create a tree-like structure based on a
	 * dot separated path
	 *
	 * @param root     The root map where every value and sub maps with its values will be put in
	 * @param path     The dot separated path where the tag should be stored
	 * @param tag      The tag that should be stored
	 * @param override If the present value is of a different type, whether it should be overridden or not
	 * @return <code>true</code> if the tag has been set successfully
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
				} else {
					return false;
				}
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

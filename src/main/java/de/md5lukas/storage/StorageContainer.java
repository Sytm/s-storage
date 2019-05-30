package de.md5lukas.storage;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public abstract class StorageContainer {

	protected String pathPrefix = "";

	/**
	 * Sets a new value at the specified path. This will not override the existing value if it is of a different type
	 *
	 * @param path  The path where the value should be stored
	 * @param value The new value
	 * @return <code>true</code>if the value has been set successfully
	 */
	public boolean set(String path, boolean value) {
		return set(path, value, false);
	}

	/**
	 * Sets a new value at the specified path. This will not override the existing value if it is of a different type
	 *
	 * @param path  The path where the value should be stored
	 * @param value The new value
	 * @return <code>true</code>if the value has been set successfully
	 */
	public boolean set(String path, byte value) {
		return set(path, value, false);
	}

	/**
	 * Sets a new value at the specified path. This will not override the existing value if it is of a different type
	 *
	 * @param path  The path where the value should be stored
	 * @param value The new value
	 * @return <code>true</code>if the value has been set successfully
	 */
	public boolean set(String path, short value) {
		return set(path, value, false);
	}

	/**
	 * Sets a new value at the specified path. This will not override the existing value if it is of a different type
	 *
	 * @param path  The path where the value should be stored
	 * @param value The new value
	 * @return <code>true</code>if the value has been set successfully
	 */
	public boolean set(String path, int value) {
		return set(path, value, false);
	}

	/**
	 * Sets a new value at the specified path. This will not override the existing value if it is of a different type
	 *
	 * @param path  The path where the value should be stored
	 * @param value The new value
	 * @return <code>true</code>if the value has been set successfully
	 */
	public boolean set(String path, long value) {
		return set(path, value, false);
	}

	/**
	 * Sets a new value at the specified path. This will not override the existing value if it is of a different type
	 *
	 * @param path  The path where the value should be stored
	 * @param value The new value
	 * @return <code>true</code>if the value has been set successfully
	 */
	public boolean set(String path, float value) {
		return set(path, value, false);
	}

	/**
	 * Sets a new value at the specified path. This will not override the existing value if it is of a different type
	 *
	 * @param path  The path where the value should be stored
	 * @param value The new value
	 * @return <code>true</code>if the value has been set successfully
	 */
	public boolean set(String path, double value) {
		return set(path, value, false);
	}

	/**
	 * Sets a new value at the specified path. This will not override the existing value if it is of a different type
	 *
	 * @param path  The path where the value should be stored
	 * @param value The new value
	 * @return <code>true</code>if the value has been set successfully
	 */
	public boolean set(String path, Object value) {
		return set(path, value, false);
	}

	/**
	 * Sets a new value at the specified path
	 *
	 * @param path     The path where the value should be stored
	 * @param value    The new value
	 * @param override If <code>true</code>, the previous value, if present, will be overridden, in disregard of the type
	 * @return <code>true</code>if the value has been set successfully
	 */
	public abstract boolean set(String path, boolean value, boolean override);

	/**
	 * Sets a new value at the specified path
	 *
	 * @param path     The path where the value should be stored
	 * @param value    The new value
	 * @param override If <code>true</code>, the previous value, if present, will be overridden, in disregard of the type
	 * @return <code>true</code>if the value has been set successfully
	 */
	public abstract boolean set(String path, byte value, boolean override);

	/**
	 * Sets a new value at the specified path
	 *
	 * @param path     The path where the value should be stored
	 * @param value    The new value
	 * @param override If <code>true</code>, the previous value, if present, will be overridden, in disregard of the type
	 * @return <code>true</code>if the value has been set successfully
	 */
	public abstract boolean set(String path, short value, boolean override);

	/**
	 * Sets a new value at the specified path
	 *
	 * @param path     The path where the value should be stored
	 * @param value    The new value
	 * @param override If <code>true</code>, the previous value, if present, will be overridden, in disregard of the type
	 * @return <code>true</code>if the value has been set successfully
	 */
	public abstract boolean set(String path, int value, boolean override);

	/**
	 * Sets a new value at the specified path
	 *
	 * @param path     The path where the value should be stored
	 * @param value    The new value
	 * @param override If <code>true</code>, the previous value, if present, will be overridden, in disregard of the type
	 * @return <code>true</code>if the value has been set successfully
	 */
	public abstract boolean set(String path, long value, boolean override);

	/**
	 * Sets a new value at the specified path
	 *
	 * @param path     The path where the value should be stored
	 * @param value    The new value
	 * @param override If <code>true</code>, the previous value, if present, will be overridden, in disregard of the type
	 * @return <code>true</code>if the value has been set successfully
	 */
	public abstract boolean set(String path, float value, boolean override);

	/**
	 * Sets a new value at the specified path
	 *
	 * @param path     The path where the value should be stored
	 * @param value    The new value
	 * @param override If <code>true</code>, the previous value, if present, will be overridden, in disregard of the type
	 * @return <code>true</code>if the value has been set successfully
	 */
	public abstract boolean set(String path, double value, boolean override);

	/**
	 * Sets a new value at the specified path.
	 * The following types can be set:
	 * <ul>
	 * <li>{@link List Lists}<br>
	 * The following types can be used in lists:
	 * <ul>
	 * <li>{@link Byte}</li>
	 * <li>{@link Short}</li>
	 * <li>{@link Integer}</li>
	 * <li>{@link Long}</li>
	 * <li>{@link Float}</li>
	 * <li>{@link Double}</li>
	 * <li>{@link String}</li>
	 * <li>Other objects will be converted to a string using {@link String#valueOf(Object)}</li>
	 * </ul>
	 * </li>
	 * <li>{@link String}</li>
	 * <li>Other objects will be converted to a string using {@link String#valueOf(Object)}</li>
	 * </ul>
	 *
	 * @param path     The path where the value should be stored
	 * @param value    The new value
	 * @param override If <code>true</code>, the previous value, if present, will be overridden, in disregard of the type
	 * @return <code>true</code>if the value has been set successfully
	 */
	public abstract boolean set(String path, Object value, boolean override);

	public abstract Optional<String> getString(String path);

	public abstract String getString(String path, String def);

	public abstract Optional<Boolean> getBoolean(String path);

	public abstract boolean getBoolean(String path, boolean def);

	public abstract Optional<Byte> getByte(String path);

	public abstract byte getByte(String path, byte def);

	public abstract Optional<Short> getShort(String path);

	public abstract short getShort(String path, short def);

	public abstract Optional<Integer> getInt(String path);

	public abstract int getInt(String path, int def);

	public abstract Optional<Long> getLong(String path);

	public abstract long getLong(String path, long def);

	public abstract Optional<Float> getFloat(String path);

	public abstract float getFloat(String path, float def);

	public abstract Optional<Double> getDouble(String path);

	public abstract double getDouble(String path, double def);

	public abstract Optional<List<Byte>> getByteList(String path);

	public abstract List<Byte> getByteList(String path, List<Byte> def);

	public abstract Optional<List<Short>> getShortList(String path);

	public abstract List<Short> getShortList(String path, List<Short> def);

	public abstract Optional<List<Integer>> getIntList(String path);

	public abstract List<Integer> getIntList(String path, List<Integer> def);

	public abstract Optional<List<Long>> getLongList(String path);

	public abstract List<Long> getLongList(String path, List<Long> def);

	public abstract Optional<List<Float>> getFloatList(String path);

	public abstract List<Float> getFloatList(String path, List<Float> def);

	public abstract Optional<List<Double>> getDoubleList(String path);

	public abstract List<Double> getDoubleList(String path, List<Double> def);

	public abstract Optional<List<String>> getStringList(String path);

	public abstract List<String> getStringList(String path, List<String> def);

	public abstract Set<String> getKeys(String path);

	/**
	 * Checks if a value at the given path is set
	 *
	 * @param path The path to the value
	 * @return <code>true</code> if a value is existing at the given path, <code>false</code> otherwise
	 */
	public abstract boolean contains(String path);

	/**
	 * Sets a new prefix that will be used by every set and get call in this instance
	 *
	 * @param prefix The new prefix
	 */
	public void setPathPrefix(String prefix) {
		pathPrefix = prefix;
	}

	/**
	 * Resets the prefix<br>
	 * Does the same as {@link #setPathPrefix(String) setPathPrefix("")}
	 */
	public void resetPathPrefix() {
		pathPrefix = "";
	}

	/**
	 * Retrieves the current path-prefix used by this instance
	 *
	 * @return The current prefix
	 */
	public String getPathPrefix() {
		return pathPrefix;
	}
}

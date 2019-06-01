package de.md5lukas.storage;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StorageContainer extends FileStorage {

	/**
	 * Sets a new value at the specified path. This will not override the existing value if it is of a different type
	 * The following types can be set:
	 * <ul>
	 * <li>{@link String}</li>
	 * <li>{@link Byte}</li>
	 * <li>{@link Short}</li>
	 * <li>{@link Integer}</li>
	 * <li>{@link Long}</li>
	 * <li>{@link Float}</li>
	 * <li>{@link Double}</li>
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
	 * <li>Other objects will be converted to a string using {@link String#valueOf(Object)}</li>
	 * </ul>
	 *
	 * @param path  The path where the value should be stored
	 * @param value The new value
	 * @return <code>true</code>if the value has been set successfully
	 */
	boolean set(String path, Object value);

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
	boolean set(String path, Object value, boolean override);


	Optional<String> getString(String path);


	String getString(String path, String def);


	Optional<Boolean> getBoolean(String path);


	boolean getBoolean(String path, boolean def);


	Optional<Byte> getByte(String path);


	byte getByte(String path, byte def);


	Optional<Short> getShort(String path);


	short getShort(String path, short def);


	Optional<Integer> getInt(String path);


	int getInt(String path, int def);


	Optional<Long> getLong(String path);


	long getLong(String path, long def);


	Optional<Float> getFloat(String path);


	float getFloat(String path, float def);


	Optional<Double> getDouble(String path);


	double getDouble(String path, double def);


	Optional<List<Byte>> getByteList(String path);


	List<Byte> getByteList(String path, List<Byte> def);


	Optional<List<Short>> getShortList(String path);


	List<Short> getShortList(String path, List<Short> def);


	Optional<List<Integer>> getIntList(String path);


	List<Integer> getIntList(String path, List<Integer> def);


	Optional<List<Long>> getLongList(String path);


	List<Long> getLongList(String path, List<Long> def);


	Optional<List<Float>> getFloatList(String path);


	List<Float> getFloatList(String path, List<Float> def);


	Optional<List<Double>> getDoubleList(String path);


	List<Double> getDoubleList(String path, List<Double> def);


	Optional<List<String>> getStringList(String path);


	List<String> getStringList(String path, List<String> def);


	Set<String> getKeys(String path);

	/**
	 * Checks if a value at the given path is set
	 *
	 * @param path The path to the value
	 * @return <code>true</code> if a value is existing at the given path, <code>false</code> otherwise
	 */
	boolean contains(String path);

	/**
	 * Sets a new prefix that will be used by every set and get call in this instance
	 *
	 * @param prefix The new prefix
	 */
	void setPathPrefix(String prefix);

	/**
	 * Resets the prefix<br>
	 * Does the same as {@link #setPathPrefix(String) setPathPrefix("")}
	 */
	void resetPathPrefix();

	/**
	 * Retrieves the current path-prefix used by this instance
	 *
	 * @return The current prefix
	 */
	String getPathPrefix();
}

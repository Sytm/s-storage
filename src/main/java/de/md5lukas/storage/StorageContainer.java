package de.md5lukas.storage;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StorageContainer {

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
	boolean set(@NotNull String path, @Nullable Object value);

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
	boolean set(@NotNull String path, @Nullable Object value, boolean override);

	@NotNull Optional<String> getString(@NotNull String path);

	String getString(@NotNull String path, String def);

	@NotNull Optional<Boolean> getBoolean(@NotNull String path);

	boolean getBoolean(@NotNull String path, boolean def);

	@NotNull Optional<Byte> getByte(@NotNull String path);

	byte getByte(@NotNull String path, byte def);

	@NotNull Optional<Short> getShort(@NotNull String path);

	short getShort(@NotNull String path, short def);

	@NotNull Optional<Integer> getInt(@NotNull String path);

	int getInt(@NotNull String path, int def);

	@NotNull Optional<Long> getLong(@NotNull String path);

	long getLong(@NotNull String path, long def);

	@NotNull Optional<Float> getFloat(@NotNull String path);

	float getFloat(@NotNull String path, float def);

	@NotNull Optional<Double> getDouble(@NotNull String path);

	double getDouble(@NotNull String path, double def);

	@NotNull Optional<List<Byte>> getByteList(@NotNull String path);

	List<Byte> getByteList(@NotNull String path, List<Byte> def);

	@NotNull Optional<List<Short>> getShortList(@NotNull String path);

	List<Short> getShortList(@NotNull String path, List<Short> def);

	@NotNull Optional<List<Integer>> getIntList(@NotNull String path);

	List<Integer> getIntList(@NotNull String path, List<Integer> def);

	@NotNull Optional<List<Long>> getLongList(@NotNull String path);

	List<Long> getLongList(@NotNull String path, List<Long> def);

	@NotNull Optional<List<Float>> getFloatList(@NotNull String path);

	List<Float> getFloatList(@NotNull String path, List<Float> def);

	@NotNull Optional<List<Double>> getDoubleList(@NotNull String path);

	List<Double> getDoubleList(@NotNull String path, List<Double> def);

	@NotNull Optional<List<String>> getStringList(@NotNull String path);

	List<String> getStringList(@NotNull String path, List<String> def);

	@Nullable Set<String> getKeys(@NotNull String path);

	/**
	 * Checks if a value at the given path is set
	 *
	 * @param path The path to the value
	 * @return <code>true</code> if a value is existing at the given path, <code>false</code> otherwise
	 */
	boolean contains(@NotNull String path);

	/**
	 * Sets a new prefix that will be used by every set and get call in this instance
	 *
	 * @param prefix The new prefix
	 */
	void setPathPrefix(@NotNull String prefix);

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

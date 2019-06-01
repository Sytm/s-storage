package de.md5lukas.storage;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public abstract class AbstractStorageContainer {

	protected String pathPrefix = "";

	/**
	 * Sets a new value at the specified path. This will not override the existing value if it is of a different type
	 *
	 * @param path  The path where the value should be stored
	 * @param value The new value
	 * @return <code>true</code>if the value has been set successfully
	 */
	public boolean set(@NotNull String path, boolean value) {
		return set(path, value, false);
	}

	/**
	 * Sets a new value at the specified path. This will not override the existing value if it is of a different type
	 *
	 * @param path  The path where the value should be stored
	 * @param value The new value
	 * @return <code>true</code>if the value has been set successfully
	 */
	public boolean set(@NotNull String path, byte value) {
		return set(path, value, false);
	}

	/**
	 * Sets a new value at the specified path. This will not override the existing value if it is of a different type
	 *
	 * @param path  The path where the value should be stored
	 * @param value The new value
	 * @return <code>true</code>if the value has been set successfully
	 */
	public boolean set(@NotNull String path, short value) {
		return set(path, value, false);
	}

	/**
	 * Sets a new value at the specified path. This will not override the existing value if it is of a different type
	 *
	 * @param path  The path where the value should be stored
	 * @param value The new value
	 * @return <code>true</code>if the value has been set successfully
	 */
	public boolean set(@NotNull String path, int value) {
		return set(path, value, false);
	}

	/**
	 * Sets a new value at the specified path. This will not override the existing value if it is of a different type
	 *
	 * @param path  The path where the value should be stored
	 * @param value The new value
	 * @return <code>true</code>if the value has been set successfully
	 */
	public boolean set(@NotNull String path, long value) {
		return set(path, value, false);
	}

	/**
	 * Sets a new value at the specified path. This will not override the existing value if it is of a different type
	 *
	 * @param path  The path where the value should be stored
	 * @param value The new value
	 * @return <code>true</code>if the value has been set successfully
	 */
	public boolean set(@NotNull String path, float value) {
		return set(path, value, false);
	}

	/**
	 * Sets a new value at the specified path. This will not override the existing value if it is of a different type
	 *
	 * @param path  The path where the value should be stored
	 * @param value The new value
	 * @return <code>true</code>if the value has been set successfully
	 */
	public boolean set(@NotNull String path, double value) {
		return set(path, value, false);
	}

	/**
	 * Sets a new value at the specified path. This will not override the existing value if it is of a different type
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
	 * @param path  The path where the value should be stored
	 * @param value The new value
	 * @return <code>true</code>if the value has been set successfully
	 */
	public boolean set(@NotNull String path, @Nullable Object value) {
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
	public abstract boolean set(@NotNull String path, boolean value, boolean override);

	/**
	 * Sets a new value at the specified path
	 *
	 * @param path     The path where the value should be stored
	 * @param value    The new value
	 * @param override If <code>true</code>, the previous value, if present, will be overridden, in disregard of the type
	 * @return <code>true</code>if the value has been set successfully
	 */
	public abstract boolean set(@NotNull String path, byte value, boolean override);

	/**
	 * Sets a new value at the specified path
	 *
	 * @param path     The path where the value should be stored
	 * @param value    The new value
	 * @param override If <code>true</code>, the previous value, if present, will be overridden, in disregard of the type
	 * @return <code>true</code>if the value has been set successfully
	 */
	public abstract boolean set(@NotNull String path, short value, boolean override);

	/**
	 * Sets a new value at the specified path
	 *
	 * @param path     The path where the value should be stored
	 * @param value    The new value
	 * @param override If <code>true</code>, the previous value, if present, will be overridden, in disregard of the type
	 * @return <code>true</code>if the value has been set successfully
	 */
	public abstract boolean set(@NotNull String path, int value, boolean override);

	/**
	 * Sets a new value at the specified path
	 *
	 * @param path     The path where the value should be stored
	 * @param value    The new value
	 * @param override If <code>true</code>, the previous value, if present, will be overridden, in disregard of the type
	 * @return <code>true</code>if the value has been set successfully
	 */
	public abstract boolean set(@NotNull String path, long value, boolean override);

	/**
	 * Sets a new value at the specified path
	 *
	 * @param path     The path where the value should be stored
	 * @param value    The new value
	 * @param override If <code>true</code>, the previous value, if present, will be overridden, in disregard of the type
	 * @return <code>true</code>if the value has been set successfully
	 */
	public abstract boolean set(@NotNull String path, float value, boolean override);

	/**
	 * Sets a new value at the specified path
	 *
	 * @param path     The path where the value should be stored
	 * @param value    The new value
	 * @param override If <code>true</code>, the previous value, if present, will be overridden, in disregard of the type
	 * @return <code>true</code>if the value has been set successfully
	 */
	public abstract boolean set(@NotNull String path, double value, boolean override);

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
	public abstract boolean set(@NotNull String path, @Nullable Object value, boolean override);

	@NotNull
	public abstract Optional<String> getString(@NotNull String path);

	public abstract String getString(@NotNull String path, String def);

	@NotNull
	public abstract Optional<Boolean> getBoolean(@NotNull String path);

	public abstract boolean getBoolean(@NotNull String path, boolean def);

	@NotNull
	public abstract Optional<Byte> getByte(@NotNull String path);

	public abstract byte getByte(@NotNull String path, byte def);

	@NotNull
	public abstract Optional<Short> getShort(@NotNull String path);

	public abstract short getShort(@NotNull String path, short def);

	@NotNull
	public abstract Optional<Integer> getInt(@NotNull String path);

	public abstract int getInt(@NotNull String path, int def);

	@NotNull
	public abstract Optional<Long> getLong(@NotNull String path);

	public abstract long getLong(@NotNull String path, long def);

	@NotNull
	public abstract Optional<Float> getFloat(@NotNull String path);

	public abstract float getFloat(@NotNull String path, float def);

	@NotNull
	public abstract Optional<Double> getDouble(@NotNull String path);

	public abstract double getDouble(@NotNull String path, double def);

	@NotNull
	public abstract Optional<List<Byte>> getByteList(@NotNull String path);

	public abstract List<Byte> getByteList(@NotNull String path, List<Byte> def);

	@NotNull
	public abstract Optional<List<Short>> getShortList(@NotNull String path);

	public abstract List<Short> getShortList(@NotNull String path, List<Short> def);

	@NotNull
	public abstract Optional<List<Integer>> getIntList(@NotNull String path);

	public abstract List<Integer> getIntList(@NotNull String path, List<Integer> def);

	@NotNull
	public abstract Optional<List<Long>> getLongList(@NotNull String path);

	public abstract List<Long> getLongList(@NotNull String path, List<Long> def);

	@NotNull
	public abstract Optional<List<Float>> getFloatList(@NotNull String path);

	public abstract List<Float> getFloatList(@NotNull String path, List<Float> def);

	@NotNull
	public abstract Optional<List<Double>> getDoubleList(@NotNull String path);

	public abstract List<Double> getDoubleList(@NotNull String path, List<Double> def);

	@NotNull
	public abstract Optional<List<String>> getStringList(@NotNull String path);

	public abstract List<String> getStringList(@NotNull String path, List<String> def);

	@Nullable
	public abstract Set<String> getKeys(@NotNull String path);

	/**
	 * Checks if a value at the given path is set
	 *
	 * @param path The path to the value
	 * @return <code>true</code> if a value is existing at the given path, <code>false</code> otherwise
	 */
	public abstract boolean contains(@NotNull String path);

	/**
	 * Sets a new prefix that will be used by every set and get call in this instance
	 *
	 * @param prefix The new prefix
	 */
	public void setPathPrefix(@NotNull String prefix) {
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

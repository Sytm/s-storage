package de.md5lukas.storage;

import org.jetbrains.annotations.Contract;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

public abstract class AbstractStorageContainer implements StorageContainer {

	private String pathPrefix = "";

	public boolean set(String path, Object value) {
		return set(path, value, false);
	}

	public boolean set(String path, Object value, boolean override) {
		path = checkPathAndPrependPrefix(path);
		if (value == null) {
			return setNull(path, override);
		} else if (value instanceof List<?>) {
			return setList(path, (List<?>) value, override);
		} else if (value instanceof Boolean) {
			return setBoolean(path, (boolean) value, override);
		} else if (value instanceof Byte) {
			return setByte(path, (byte) value, override);
		} else if (value instanceof Short) {
			return setShort(path, (short) value, override);
		} else if (value instanceof Integer) {
			return setInteger(path, (int) value, override);
		} else if (value instanceof Long) {
			return setLong(path, (long) value, override);
		} else if (value instanceof Float) {
			return setFloat(path, (float) value, override);
		} else if (value instanceof Double) {
			return setDouble(path, (double) value, override);
		}
		return setString(path, Objects.toString(value), override);
	}

	protected boolean setString(String path, String value, boolean override) {
		return false;
	}

	protected boolean setBoolean(String path, boolean value, boolean override) {
		return false;
	}

	protected boolean setByte(String path, byte value, boolean override) {
		return false;
	}

	protected boolean setShort(String path, short value, boolean override) {
		return false;
	}

	protected boolean setInteger(String path, int value, boolean override) {
		return false;
	}

	protected boolean setLong(String path, long value, boolean override) {
		return false;
	}

	protected boolean setFloat(String path, float value, boolean override) {
		return false;
	}

	protected boolean setDouble(String path, double value, boolean override) {
		return false;
	}

	protected boolean setList(String path, List<?> value, boolean override) {
		return false;
	}

	protected boolean setNull(String path, boolean override) {
		return false;
	}

	@Contract(pure = true)
	protected final <T> List<T> convertList(List<?> input, Function<Object, T> converter) {
		List<T> output = new ArrayList<>(input.size());
		for (Object o : input)
			output.add(converter.apply(o));
		return output;
	}

	@Contract(pure = true)
	protected final <R, T> List<T> convertListTyped(List<R> input, Function<R, T> converter) {
		List<T> output = new ArrayList<>(input.size());
		for (R o : input)
			output.add(converter.apply(o));
		return output;
	}


	public abstract Optional<String> getString(String path);

	public String getString(String path, String def) {
		Optional<String> optional = getString(path);
		return optional.orElse(def);
	}


	public abstract Optional<Boolean> getBoolean(String path);

	public boolean getBoolean(String path, boolean def) {
		Optional<Boolean> optional = getBoolean(path);
		return optional.orElse(def);
	}


	public abstract Optional<Byte> getByte(String path);

	public byte getByte(String path, byte def) {
		Optional<Byte> optional = getByte(path);
		return optional.orElse(def);
	}


	public abstract Optional<Short> getShort(String path);

	public short getShort(String path, short def) {
		Optional<Short> optional = getShort(path);
		return optional.orElse(def);
	}


	public abstract Optional<Integer> getInt(String path);

	public int getInt(String path, int def) {
		Optional<Integer> optional = getInt(path);
		return optional.orElse(def);
	}


	public abstract Optional<Long> getLong(String path);

	public long getLong(String path, long def) {
		Optional<Long> optional = getLong(path);
		return optional.orElse(def);
	}


	public abstract Optional<Float> getFloat(String path);

	public float getFloat(String path, float def) {
		Optional<Float> optional = getFloat(path);
		return optional.orElse(def);
	}


	public abstract Optional<Double> getDouble(String path);

	public double getDouble(String path, double def) {
		Optional<Double> optional = getDouble(path);
		return optional.orElse(def);
	}


	public abstract Optional<List<Byte>> getByteList(String path);

	public List<Byte> getByteList(String path, List<Byte> def) {
		Optional<List<Byte>> optional = getByteList(path);
		return optional.orElse(def);
	}


	public abstract Optional<List<Short>> getShortList(String path);

	public List<Short> getShortList(String path, List<Short> def) {
		Optional<List<Short>> optional = getShortList(path);
		return optional.orElse(def);
	}


	public abstract Optional<List<Integer>> getIntList(String path);

	public List<Integer> getIntList(String path, List<Integer> def) {
		Optional<List<Integer>> optional = getIntList(path);
		return optional.orElse(def);
	}


	public abstract Optional<List<Long>> getLongList(String path);

	public List<Long> getLongList(String path, List<Long> def) {
		Optional<List<Long>> optional = getLongList(path);
		return optional.orElse(def);
	}


	public abstract Optional<List<Float>> getFloatList(String path);

	public List<Float> getFloatList(String path, List<Float> def) {
		Optional<List<Float>> optional = getFloatList(path);
		return optional.orElse(def);
	}


	public abstract Optional<List<Double>> getDoubleList(String path);

	public List<Double> getDoubleList(String path, List<Double> def) {
		Optional<List<Double>> optional = getDoubleList(path);
		return optional.orElse(def);
	}


	public abstract Optional<List<String>> getStringList(String path);

	public List<String> getStringList(String path, List<String> def) {
		Optional<List<String>> optional = getStringList(path);
		return optional.orElse(def);
	}


	public abstract Set<String> getKeys(String path);

	public abstract boolean contains(String path);

	public void setPathPrefix(String prefix) {
		if (prefix == null)
			throw new IllegalArgumentException("The new prefix cannot be null!");
		pathPrefix = prefix;
	}

	public void resetPathPrefix() {
		pathPrefix = "";
	}

	public String getPathPrefix() {
		return pathPrefix;
	}

	// Prepends the path prefix to the given path and checks for null path
	@Contract("null -> fail")
	protected String checkPathAndPrependPrefix(String path) {
		if (path == null)
			throw new IllegalArgumentException("The path cannot be null!");
		return pathPrefix + path;
	}

	@Override
	public void save(File file) throws IOException {
		save(file, false);
	}

	@Override
	public void load(File file) throws IOException {
		load(file, false);
	}
}

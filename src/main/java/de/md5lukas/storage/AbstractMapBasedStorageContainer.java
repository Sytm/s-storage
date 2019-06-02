package de.md5lukas.storage;

import de.md5lukas.storage.util.MapHelper;

import java.io.File;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This is a almost complete implementation of {@link StorageContainer} for file formats which allow you to save and load
 * Maps with strings as keys. The only missing methods are {@link StorageContainer#load(File, boolean)} and
 * {@link StorageContainer#save(File, boolean)}
 *
 * @author Md5Lukas
 */
public abstract class AbstractMapBasedStorageContainer extends AbstractStorageContainer {

	protected Map<String, Object> map;

	public AbstractMapBasedStorageContainer() {
		map = new HashMap<>();
	}

	@Override
	public boolean set(String path, Object value, boolean override) {
		if (value == null)
			override = true;
		return MapHelper.set(map, path, value, override);
	}

	@Override
	public Optional<String> getString(String path) {
		return get(path, o -> o instanceof String, String::valueOf);
	}

	@Override
	public Optional<Boolean> getBoolean(String path) {
		return get(path, o -> o instanceof Boolean, o -> (Boolean) o);
	}

	@Override
	public Optional<Byte> getByte(String path) {
		return get(path, o -> o instanceof Byte, o -> (Byte) o);
	}

	@Override
	public Optional<Short> getShort(String path) {
		return get(path, o -> o instanceof Short, o -> (Short) o);
	}

	@Override
	public Optional<Integer> getInt(String path) {
		return get(path, o -> o instanceof Integer, o -> (Integer) o);
	}

	@Override
	public Optional<Long> getLong(String path) {
		return get(path, o -> o instanceof Long, o -> (Long) o);
	}

	@Override
	public Optional<Float> getFloat(String path) {
		return get(path, o -> o instanceof Float, o -> (Float) o);
	}

	@Override
	public Optional<Double> getDouble(String path) {
		return get(path, o -> o instanceof Double, o -> (Double) o);
	}

	protected <T> Optional<T> get(String path, Function<Object, Boolean> checkType, Function<Object, T> caster) {
		path = checkPathAndPrependPrefix(path);
		Object value = MapHelper.get(map, path);
		if (checkType.apply(value))
			return Optional.of(caster.apply(value));
		return Optional.empty();
	}

	@Override
	public Optional<List<Byte>> getByteList(String path) {
		return getList(path, o -> o instanceof Byte, o -> (Byte) o);
	}

	@Override
	public Optional<List<Short>> getShortList(String path) {
		return getList(path, o -> o instanceof Short, o -> (Short) o);
	}

	@Override
	public Optional<List<Integer>> getIntList(String path) {
		return getList(path, o -> o instanceof Integer, o -> (Integer) o);
	}

	@Override
	public Optional<List<Long>> getLongList(String path) {
		return getList(path, o -> o instanceof Long, o -> (Long) o);
	}

	@Override
	public Optional<List<Float>> getFloatList(String path) {
		return getList(path, o -> o instanceof Float, o -> (Float) o);
	}

	@Override
	public Optional<List<Double>> getDoubleList(String path) {
		return getList(path, o -> o instanceof Double, o -> (Double) o);
	}

	@Override
	public Optional<List<String>> getStringList(String path) {
		return getList(path, o -> o instanceof String, Objects::toString);
	}

	/**
	 * Helper method to drastically reduce the amount of code in the getList methods
	 *
	 * @param path      The path where the list can be found
	 * @param checkType A function to check if the type in the list is the correct one
	 * @param caster    A function that casts a object to the correct type
	 * @param <T>       The type of the list
	 * @return A {@link Optional} containing a list of the specified type or if not present or the type doesn't match a empty Optional
	 */
	protected <T> Optional<List<T>> getList(String path, Function<Object, Boolean> checkType, Function<Object, T> caster) {
		path = checkPathAndPrependPrefix(path);
		Object value = MapHelper.get(map, path);
		if (value instanceof List<?>) {
			List<?> list = (List<?>) value;
			if (list.isEmpty()) {
				return Optional.of(new ArrayList<>());
			} else {
				Object first = list.get(0);
				if (checkType.apply(first)) {
					return Optional.of(convertList(list, caster));
				}
			}
		}
		return Optional.empty();
	}

	@Override
	public Set<String> getKeys(String path) {
		path = checkPathAndPrependPrefix(path);
		Object value = MapHelper.get(map, path);
		if (value instanceof Map<?, ?>) {
			return ((Map<?, ?>) value).keySet().stream().map(Objects::toString).collect(Collectors.toSet());
		}
		return null;
	}

	@Override
	public boolean contains(String path) {
		path = checkPathAndPrependPrefix(path);
		return MapHelper.get(map, path) != null;
	}

	/**
	 * The returned map is mutable and every change to it will reflect in this instance
	 *
	 * @return The backing map of this storage container
	 */
	public Map<String, Object> getMap() {
		return map;
	}
}

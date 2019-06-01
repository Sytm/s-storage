package de.md5lukas.storage;

import de.md5lukas.storage.util.MapHelper;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class MapBasedStorageContainer extends AbstractStorageContainer {

	protected Map<String, Object> root;

	public MapBasedStorageContainer() {
		root = new HashMap<>();
	}

	@Override
	public boolean set(String path, Object value, boolean override) {
		return MapHelper.set(root, path, value, override);
	}

	@Override
	public Optional<String> getString(String path) {
		path = checkPathAndPrependPrefix(path);
		Object value = MapHelper.get(root, path);
		if (value instanceof String)
			return Optional.of((String) value);
		return Optional.empty();
	}

	@Override
	public Optional<Boolean> getBoolean(String path) {
		path = checkPathAndPrependPrefix(path);
		Object value = MapHelper.get(root, path);
		if (value instanceof Boolean)
			return Optional.of((Boolean) value);
		return Optional.empty();
	}

	@Override
	public Optional<Byte> getByte(String path) {
		path = checkPathAndPrependPrefix(path);
		Object value = MapHelper.get(root, path);
		if (value instanceof Byte)
			return Optional.of((Byte) value);
		return Optional.empty();
	}

	@Override
	public Optional<Short> getShort(String path) {
		path = checkPathAndPrependPrefix(path);
		Object value = MapHelper.get(root, path);
		if (value instanceof Short)
			return Optional.of((Short) value);
		return Optional.empty();
	}

	@Override
	public Optional<Integer> getInt(String path) {
		path = checkPathAndPrependPrefix(path);
		Object value = MapHelper.get(root, path);
		if (value instanceof Integer)
			return Optional.of((Integer) value);
		return Optional.empty();
	}

	@Override
	public Optional<Long> getLong(String path) {
		path = checkPathAndPrependPrefix(path);
		Object value = MapHelper.get(root, path);
		if (value instanceof Long)
			return Optional.of((Long) value);
		return Optional.empty();
	}

	@Override
	public Optional<Float> getFloat(String path) {
		path = checkPathAndPrependPrefix(path);
		Object value = MapHelper.get(root, path);
		if (value instanceof Float)
			return Optional.of((Float) value);
		return Optional.empty();
	}

	@Override
	public Optional<Double> getDouble(String path) {
		path = checkPathAndPrependPrefix(path);
		Object value = MapHelper.get(root, path);
		if (value instanceof Double)
			return Optional.of((Double) value);
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

	protected <T> Optional<List<T>> getList(String path, Function<Object, Boolean> checkType, Function<Object, T> caster) {
		path = checkPathAndPrependPrefix(path);
		Object value = MapHelper.get(root, path);
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
		Object value = MapHelper.get(root, path);
		if (value instanceof Map<?, ?>) {
			return ((Map<?, ?>) value).keySet().stream().map(Objects::toString).collect(Collectors.toSet());
		}
		return null;
	}

	@Override
	public boolean contains(String path) {
		path = checkPathAndPrependPrefix(path);
		return MapHelper.get(root, path) != null;
	}

	public Map<String, Object> getRoot() {
		return root;
	}
}

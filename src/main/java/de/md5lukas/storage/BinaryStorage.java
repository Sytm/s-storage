package de.md5lukas.storage;

import de.md5lukas.nbt.NbtIo;
import de.md5lukas.nbt.Tag;
import de.md5lukas.nbt.tags.*;
import de.md5lukas.storage.util.CompoundHelper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BinaryStorage extends AbstractStorageContainer {

	private String name;
	private CompoundTag root;

	public BinaryStorage(String name) {
		this.name = name;
		root = new CompoundTag(name);
	}

	public CompoundTag getRoot() {
		return root;
	}

	@Override
	protected boolean setBoolean(String path, boolean value, boolean override) {
		return CompoundHelper.setTag(root, new ByteTag(null, (byte) (value ? 1 : 0)), path, override);
	}

	@Override
	protected boolean setByte(String path, byte value, boolean override) {
		return CompoundHelper.setTag(root, new ByteTag(null, value), path, override);
	}

	@Override
	protected boolean setShort(String path, short value, boolean override) {
		return CompoundHelper.setTag(root, new ShortTag(null, value), path, override);
	}

	@Override
	protected boolean setInteger(String path, int value, boolean override) {
		return CompoundHelper.setTag(root, new IntTag(null, value), path, override);
	}

	@Override
	protected boolean setLong(String path, long value, boolean override) {
		return CompoundHelper.setTag(root, new LongTag(null, value), path, override);
	}

	@Override
	protected boolean setFloat(String path, float value, boolean override) {
		return CompoundHelper.setTag(root, new FloatTag(null, value), path, override);
	}

	@Override
	protected boolean setDouble(String path, double value, boolean override) {
		return CompoundHelper.setTag(root, new DoubleTag(null, value), path, override);
	}

	@Override
	protected boolean setString(String path, String value, boolean override) {
		return CompoundHelper.setTag(root, new StringTag(null, value), path, override);
	}

	@Override
	protected boolean setList(String path, List<?> value, boolean override) {
		if (value.isEmpty()) {
			return CompoundHelper.setTag(root, new ListTag(null), path, override);
		} else {
			ListTag tag = new ListTag(null);
			Object first = value.get(0);
			if (first instanceof Byte) {
				tag.setList(convertList(value, o -> new ByteTag(null, (byte) o)));
			} else if (first instanceof Short) {
				tag.setList(convertList(value, o -> new ShortTag(null, (short) o)));
			} else if (first instanceof Integer) {
				tag.setList(convertList(value, o -> new IntTag(null, (int) o)));
			} else if (first instanceof Long) {
				tag.setList(convertList(value, o -> new LongTag(null, (long) o)));
			} else if (first instanceof Float) {
				tag.setList(convertList(value, o -> new FloatTag(null, (float) o)));
			} else if (first instanceof Double) {
				tag.setList(convertList(value, o -> new DoubleTag(null, (double) o)));
			} else {
				tag.setList(convertList(value, o -> new StringTag(null, Objects.toString(o))));
			}
			CompoundHelper.setTag(root, tag, path, false);
		}
		return false;
	}

	@Override
	protected boolean setNull(String path, boolean override) {
		return CompoundHelper.setTag(root, null, path, true);
	}

	public Optional<String> getString(String path) {
		path = checkPathAndPrependPrefix(path);
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof StringTag) {
			return Optional.of(((StringTag) tag).data);
		}
		return Optional.empty();
	}


	public Optional<Boolean> getBoolean(String path) {
		path = checkPathAndPrependPrefix(path);
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ByteTag) {
			return Optional.of(((ByteTag) tag).data != 0);
		}
		return Optional.empty();
	}


	public Optional<Byte> getByte(String path) {
		path = checkPathAndPrependPrefix(path);
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ByteTag) {
			return Optional.of(((ByteTag) tag).data);
		}
		return Optional.empty();
	}


	public Optional<Short> getShort(String path) {
		path = checkPathAndPrependPrefix(path);
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ShortTag) {
			return Optional.of(((ShortTag) tag).data);
		}
		return Optional.empty();
	}


	public Optional<Integer> getInt(String path) {
		path = checkPathAndPrependPrefix(path);
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof IntTag) {
			return Optional.of(((IntTag) tag).data);
		}
		return Optional.empty();
	}


	public Optional<Long> getLong(String path) {
		path = checkPathAndPrependPrefix(path);
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof LongTag) {
			return Optional.of(((LongTag) tag).data);
		}
		return Optional.empty();
	}


	public Optional<Float> getFloat(String path) {
		path = checkPathAndPrependPrefix(path);
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof FloatTag) {
			return Optional.of(((FloatTag) tag).data);
		}
		return Optional.empty();
	}


	public Optional<Double> getDouble(String path) {
		path = checkPathAndPrependPrefix(path);
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof DoubleTag) {
			return Optional.of(((DoubleTag) tag).data);
		}
		return Optional.empty();
	}


	public Optional<List<Byte>> getByteList(String path) {
		path = checkPathAndPrependPrefix(path);
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ListTag) {
			ListTag lTag = (ListTag) tag;
			List<Byte> result = new ArrayList<>(lTag.size());
			for (Tag t : lTag.values()) {
				if (!(t instanceof ByteTag))
					return Optional.empty();
				result.add(((ByteTag) t).data);
			}
			return Optional.of(result);
		}
		return Optional.empty();
	}


	public Optional<List<Short>> getShortList(String path) {
		path = checkPathAndPrependPrefix(path);
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ListTag) {
			ListTag lTag = (ListTag) tag;
			List<Short> result = new ArrayList<>(lTag.size());
			for (Tag t : lTag.values()) {
				if (!(t instanceof ShortTag))
					return Optional.empty();
				result.add(((ShortTag) t).data);
			}
			return Optional.of(result);
		}
		return Optional.empty();
	}


	public Optional<List<Integer>> getIntList(String path) {
		path = checkPathAndPrependPrefix(path);
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ListTag) {
			ListTag lTag = (ListTag) tag;
			List<Integer> result = new ArrayList<>(lTag.size());
			for (Tag t : lTag.values()) {
				if (!(t instanceof IntTag))
					return Optional.empty();
				result.add(((IntTag) t).data);
			}
			return Optional.of(result);
		}
		return Optional.empty();
	}


	public Optional<List<Long>> getLongList(String path) {
		path = checkPathAndPrependPrefix(path);
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ListTag) {
			ListTag lTag = (ListTag) tag;
			List<Long> result = new ArrayList<>(lTag.size());
			for (Tag t : lTag.values()) {
				if (!(t instanceof LongTag))
					return Optional.empty();
				result.add(((LongTag) t).data);
			}
			return Optional.of(result);
		}
		return Optional.empty();
	}


	public Optional<List<Float>> getFloatList(String path) {
		path = checkPathAndPrependPrefix(path);
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ListTag) {
			ListTag lTag = (ListTag) tag;
			List<Float> result = new ArrayList<>(lTag.size());
			for (Tag t : lTag.values()) {
				if (!(t instanceof FloatTag))
					return Optional.empty();
				result.add(((FloatTag) t).data);
			}
			return Optional.of(result);
		}
		return Optional.empty();
	}


	public Optional<List<Double>> getDoubleList(String path) {
		path = checkPathAndPrependPrefix(path);
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ListTag) {
			ListTag lTag = (ListTag) tag;
			List<Double> result = new ArrayList<>(lTag.size());
			for (Tag t : lTag.values()) {
				if (!(t instanceof DoubleTag))
					return Optional.empty();
				result.add(((DoubleTag) t).data);
			}
			return Optional.of(result);
		}
		return Optional.empty();
	}


	public Optional<List<String>> getStringList(String path) {
		path = checkPathAndPrependPrefix(path);
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ListTag) {
			ListTag lTag = (ListTag) tag;
			List<String> result = new ArrayList<>(lTag.size());
			for (Tag t : lTag.values()) {
				if (!(t instanceof StringTag))
					return Optional.empty();
				result.add(((StringTag) t).data);
			}
			return Optional.of(result);
		}
		return Optional.empty();
	}


	public Set<String> getKeys(String path) {
		path = checkPathAndPrependPrefix(path);
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof CompoundTag) {
			return ((CompoundTag) tag).getAllTags().stream().map(Tag::getName).collect(Collectors.toSet());
		}
		return null;
	}


	public boolean contains(String path) {
		path = checkPathAndPrependPrefix(path);
		return CompoundHelper.getTag(root, path) != null;
	}

	public void load(File file, boolean compressed) throws IOException {
		if (file == null)
			throw new IllegalArgumentException("The file where the data should be loaded from cannot null!");
		if (compressed)
			root = NbtIo.readCompressed(file);
		else
			root = NbtIo.read(file);
		name = root.getName();
	}

	public void save(File file, boolean compressed) throws IOException {
		if (file == null)
			throw new IllegalArgumentException("The file where the data should be stored in cannot null!");
		if (compressed)
			NbtIo.writeCompressed(root, file);
		else
			NbtIo.write(root, file);
	}
}

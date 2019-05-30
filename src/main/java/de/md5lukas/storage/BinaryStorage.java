package de.md5lukas.storage;

import de.md5lukas.nbt.NbtIo;
import de.md5lukas.nbt.Tag;
import de.md5lukas.nbt.tags.*;
import de.md5lukas.storage.util.CompoundHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class BinaryStorage extends StorageContainer implements FileStorage {

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
	public boolean set(String path, boolean value, boolean override) {
		path = pathPrefix + path;
		return CompoundHelper.setTag(root, new ByteTag(null, (byte) (value ? 1 : 0)), path, override);
	}

	@Override
	public boolean set(String path, byte value, boolean override) {
		path = pathPrefix + path;
		return CompoundHelper.setTag(root, new ByteTag(null, value), path, override);
	}

	@Override
	public boolean set(String path, short value, boolean override) {
		path = pathPrefix + path;
		return CompoundHelper.setTag(root, new ShortTag(null, value), path, override);
	}

	@Override
	public boolean set(String path, int value, boolean override) {
		path = pathPrefix + path;
		return CompoundHelper.setTag(root, new IntTag(null, value), path, override);
	}

	@Override
	public boolean set(String path, long value, boolean override) {
		path = pathPrefix + path;
		return CompoundHelper.setTag(root, new LongTag(null, value), path, override);
	}

	@Override
	public boolean set(String path, float value, boolean override) {
		path = pathPrefix + path;
		return CompoundHelper.setTag(root, new FloatTag(null, value), path, override);
	}

	@Override
	public boolean set(String path, double value, boolean override) {
		path = pathPrefix + path;
		return CompoundHelper.setTag(root, new DoubleTag(null, value), path, override);
	}

	@Override
	public boolean set(String path, Object value, boolean override) {
		if (value == null) {
			return CompoundHelper.setTag(root, null, path, true);
		} else if (value instanceof List<?>) {
			List<?> list = (List<?>) value;
			if (list.isEmpty()) {
				return CompoundHelper.setTag(root, new ListTag(null), path, override);
			} else {
				ListTag tag = new ListTag(null);
				Object first = list.get(0);
				if (first instanceof Byte) {
					for (Object o : list)
						tag.add(new ByteTag(null, (Byte) o));
				} else if (first instanceof Short) {
					for (Object o : list)
						tag.add(new ShortTag(null, (Short) o));
				} else if (first instanceof Integer) {
					for (Object o : list)
						tag.add(new IntTag(null, (Integer) o));
				} else if (first instanceof Long) {
					for (Object o : list)
						tag.add(new LongTag(null, (Long) o));
				} else if (first instanceof Float) {
					for (Object o : list)
						tag.add(new FloatTag(null, (Float) o));
				} else if (first instanceof Double) {
					for (Object o : list)
						tag.add(new DoubleTag(null, (Double) o));
				} else {
					for (Object o : list)
						tag.add(new StringTag(null, String.valueOf(o)));
				}
				return CompoundHelper.setTag(root, tag, path, override);
			}
		}
		return CompoundHelper.setTag(root, new StringTag(null, String.valueOf(value)), path, override);
	}

	@Override
	public Optional<String> getString(String path) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof StringTag) {
			return Optional.of(((StringTag) tag).data);
		}
		return Optional.empty();
	}

	@Override
	public String getString(String path, String def) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof StringTag) {
			return ((StringTag) tag).data;
		}
		return def;
	}

	@Override
	public Optional<Boolean> getBoolean(String path) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ByteTag) {
			return Optional.of(((ByteTag) tag).data != 0);
		}
		return Optional.empty();
	}

	@Override
	public boolean getBoolean(String path, boolean def) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ByteTag) {
			return ((ByteTag) tag).data != 0;
		}
		return def;
	}

	@Override
	public Optional<Byte> getByte(String path) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ByteTag) {
			return Optional.of(((ByteTag) tag).data);
		}
		return Optional.empty();
	}

	@Override
	public byte getByte(String path, byte def) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ByteTag) {
			return ((ByteTag) tag).data;
		}
		return def;
	}

	@Override
	public Optional<Short> getShort(String path) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ShortTag) {
			return Optional.of(((ShortTag) tag).data);
		}
		return Optional.empty();
	}

	@Override
	public short getShort(String path, short def) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ShortTag) {
			return ((ShortTag) tag).data;
		}
		return def;
	}

	@Override
	public Optional<Integer> getInt(String path) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof IntTag) {
			return Optional.of(((IntTag) tag).data);
		}
		return Optional.empty();
	}

	@Override
	public int getInt(String path, int def) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof IntTag) {
			return ((IntTag) tag).data;
		}
		return def;
	}

	@Override
	public Optional<Long> getLong(String path) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof LongTag) {
			return Optional.of(((LongTag) tag).data);
		}
		return Optional.empty();
	}

	@Override
	public long getLong(String path, long def) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof LongTag) {
			return ((LongTag) tag).data;
		}
		return def;
	}

	@Override
	public Optional<Float> getFloat(String path) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof FloatTag) {
			return Optional.of(((FloatTag) tag).data);
		}
		return Optional.empty();
	}

	@Override
	public float getFloat(String path, float def) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof FloatTag) {
			return ((FloatTag) tag).data;
		}
		return def;
	}

	@Override
	public Optional<Double> getDouble(String path) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof DoubleTag) {
			return Optional.of(((DoubleTag) tag).data);
		}
		return Optional.empty();
	}

	@Override
	public double getDouble(String path, double def) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof DoubleTag) {
			return ((DoubleTag) tag).data;
		}
		return 0;
	}

	@Override
	public Optional<List<Byte>> getByteList(String path) {
		path = pathPrefix + path;
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

	@Override
	public List<Byte> getByteList(String path, List<Byte> def) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ListTag) {
			ListTag lTag = (ListTag) tag;
			List<Byte> result = new ArrayList<>(lTag.size());
			for (Tag t : lTag.values()) {
				if (!(t instanceof ByteTag))
					return def;
				result.add(((ByteTag) t).data);
			}
			return result;
		}
		return def;
	}

	@Override
	public Optional<List<Short>> getShortList(String path) {
		path = pathPrefix + path;
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

	@Override
	public List<Short> getShortList(String path, List<Short> def) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ListTag) {
			ListTag lTag = (ListTag) tag;
			List<Short> result = new ArrayList<>(lTag.size());
			for (Tag t : lTag.values()) {
				if (!(t instanceof ShortTag))
					return def;
				result.add(((ShortTag) t).data);
			}
			return result;
		}
		return def;
	}

	@Override
	public Optional<List<Integer>> getIntList(String path) {
		path = pathPrefix + path;
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

	@Override
	public List<Integer> getIntList(String path, List<Integer> def) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ListTag) {
			ListTag lTag = (ListTag) tag;
			List<Integer> result = new ArrayList<>(lTag.size());
			for (Tag t : lTag.values()) {
				if (!(t instanceof IntTag))
					return def;
				result.add(((IntTag) t).data);
			}
			return result;
		}
		return def;
	}

	@Override
	public Optional<List<Long>> getLongList(String path) {
		path = pathPrefix + path;
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

	@Override
	public List<Long> getLongList(String path, List<Long> def) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ListTag) {
			ListTag lTag = (ListTag) tag;
			List<Long> result = new ArrayList<>(lTag.size());
			for (Tag t : lTag.values()) {
				if (!(t instanceof LongTag))
					return def;
				result.add(((LongTag) t).data);
			}
			return result;
		}
		return def;
	}

	@Override
	public Optional<List<Float>> getFloatList(String path) {
		path = pathPrefix + path;
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

	@Override
	public List<Float> getFloatList(String path, List<Float> def) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ListTag) {
			ListTag lTag = (ListTag) tag;
			List<Float> result = new ArrayList<>(lTag.size());
			for (Tag t : lTag.values()) {
				if (!(t instanceof FloatTag))
					return def;
				result.add(((FloatTag) t).data);
			}
			return result;
		}
		return def;
	}

	@Override
	public Optional<List<Double>> getDoubleList(String path) {
		path = pathPrefix + path;
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

	@Override
	public List<Double> getDoubleList(String path, List<Double> def) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ListTag) {
			ListTag lTag = (ListTag) tag;
			List<Double> result = new ArrayList<>(lTag.size());
			for (Tag t : lTag.values()) {
				if (!(t instanceof DoubleTag))
					return def;
				result.add(((DoubleTag) t).data);
			}
			return result;
		}
		return def;
	}


	@Override
	public Optional<List<String>> getStringList(String path) {
		path = pathPrefix + path;
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

	@Override
	public List<String> getStringList(String path, List<String> def) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ListTag) {
			ListTag lTag = (ListTag) tag;
			List<String> result = new ArrayList<>(lTag.size());
			for (Tag t : lTag.values()) {
				if (!(t instanceof StringTag))
					return def;
				result.add(((StringTag) t).data);
			}
			return result;
		}
		return def;
	}

	@Override
	public Set<String> getKeys(String path) {
		path = pathPrefix + path;
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof CompoundTag) {
			return ((CompoundTag) tag).getAllTags().stream().map(Tag::getName).collect(Collectors.toSet());
		}
		return null;
	}

	@Override
	public boolean contains(String path) {
		path = pathPrefix + path;
		return CompoundHelper.getTag(root, path) != null;
	}

	@Override
	public void load(File file) throws IOException {
		load(file, false);
	}

	@Override
	public void load(File file, boolean compressed) throws IOException {
		if (compressed)
			root = NbtIo.readCompressed(file);
		else
			root = NbtIo.read(file);
		name = root.getName();
	}

	@Override
	public void save(File file) throws IOException {
		save(file, false);
	}

	@Override
	public void save(File file, boolean compressed) throws IOException {
		if (compressed)
			NbtIo.writeCompressed(root, file);
		else
			NbtIo.write(root, file);
	}
}

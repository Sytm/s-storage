package de.md5lukas.storage;

import de.md5lukas.nbt.NbtIo;
import de.md5lukas.nbt.Tag;
import de.md5lukas.nbt.tags.*;
import de.md5lukas.storage.util.CompoundHelper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This class is a implementation of {@link StorageContainer} backed by a nbt structure
 *
 * @author Md5Lukas
 */
public class BinaryStorage extends AbstractStorageContainer {

	private CompoundTag root;

	public BinaryStorage() {
		this(null);
	}

	public BinaryStorage(String name) {
		root = new CompoundTag(name);
	}

	//<editor-fold desc="Setting values">
	@Override
	public boolean set(String path, Object value, boolean override) {
		if (value instanceof Tag) {
			return CompoundHelper.setTag(root, (Tag) value, checkPathAndPrependPrefix(path), override);
		}
		return super.set(path, value, override);
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
			return CompoundHelper.setTag(root, new ListTag((String) null), path, override);
		} else {
			ListTag tag = new ListTag((String) null);
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
	//</editor-fold>

	//<editor-fold desc="Getting values">


	@Override
	public Optional<String> getString(String path) {
		return get(path, t -> t instanceof StringTag, t -> ((StringTag) t).value());
	}

	@Override
	public Optional<Boolean> getBoolean(String path) {
		return get(path, t -> t instanceof ByteTag, t -> ((ByteTag) t).value() != 0);
	}

	@Override
	public Optional<Byte> getByte(String path) {
		return get(path, t -> t instanceof ByteTag, t -> ((ByteTag) t).value());
	}

	@Override
	public Optional<Short> getShort(String path) {
		return get(path, t -> t instanceof ShortTag, t -> ((ShortTag) t).value());
	}

	@Override
	public Optional<Integer> getInt(String path) {
		return get(path, t -> t instanceof IntTag, t -> ((IntTag) t).value());
	}

	@Override
	public Optional<Long> getLong(String path) {
		return get(path, t -> t instanceof LongTag, t -> ((LongTag) t).value());
	}

	@Override
	public Optional<Float> getFloat(String path) {
		return get(path, t -> t instanceof FloatTag, t -> ((FloatTag) t).value());
	}

	@Override
	public Optional<Double> getDouble(String path) {
		return get(path, t -> t instanceof DoubleTag, t -> ((DoubleTag) t).value());
	}

	private <T> Optional<T> get(String path, Function<Tag, Boolean> checkType, Function<Tag, T> caster) {
		path = checkPathAndPrependPrefix(path);
		Tag value = CompoundHelper.getTag(root, path);
		if (checkType.apply(value))
			return Optional.of(caster.apply(value));
		return Optional.empty();
	}
	//</editor-fold>

	//<editor-fold desc="Getting lists">
	@Override
	public Optional<List<Byte>> getByteList(String path) {
		return getList(path, t -> t instanceof ByteTag, t -> ((ByteTag) t).value());
	}

	@Override
	public Optional<List<Short>> getShortList(String path) {
		return getList(path, t -> t instanceof ShortTag, t -> ((ShortTag) t).value());
	}

	@Override
	public Optional<List<Integer>> getIntList(String path) {
		return getList(path, t -> t instanceof IntTag, t -> ((IntTag) t).value());
	}

	@Override
	public Optional<List<Long>> getLongList(String path) {
		return getList(path, t -> t instanceof LongTag, t -> ((LongTag) t).value());
	}

	@Override
	public Optional<List<Float>> getFloatList(String path) {
		return getList(path, t -> t instanceof FloatTag, t -> ((FloatTag) t).value());
	}

	@Override
	public Optional<List<Double>> getDoubleList(String path) {
		return getList(path, t -> t instanceof DoubleTag, t -> ((DoubleTag) t).value());
	}

	@Override
	public Optional<List<String>> getStringList(String path) {
		return getList(path, t -> t instanceof StringTag, t -> ((StringTag) t).value());
	}

	private <T> Optional<List<T>> getList(String path, Function<Tag, Boolean> checkType, Function<Tag, T> caster) {
		path = checkPathAndPrependPrefix(path);
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof ListTag) {
			ListTag lTag = (ListTag) tag;
			if (lTag.size() == 0)
				return Optional.of(new ArrayList<>());
			if (!checkType.apply(lTag.get(0)))
				return Optional.empty();
			List<T> result = new ArrayList<>();
			for (Tag t : lTag.values())
				result.add(caster.apply(t));
			return Optional.of(result);
		}
		return Optional.empty();
	}
	//</editor-fold>

	@Override
	public Set<String> getKeys(String path) {
		path = checkPathAndPrependPrefix(path);
		Tag tag = CompoundHelper.getTag(root, path);
		if (tag instanceof CompoundTag) {
			return ((CompoundTag) tag).getAllTags().stream().map(Tag::getName).collect(Collectors.toSet());
		}
		return null;
	}

	@Override
	public boolean contains(String path) {
		path = checkPathAndPrependPrefix(path);
		return CompoundHelper.getTag(root, path) != null;
	}

	@Override
	public void load(File file, boolean compressed) throws IOException {
		if (file == null)
			throw new IllegalArgumentException("The file where the data should be loaded from cannot be null!");
		if (compressed)
			root = NbtIo.readCompressed(file);
		else
			root = NbtIo.read(file);
	}

	@Override
	public void save(File file, boolean compressed) throws IOException {
		if (file == null)
			throw new IllegalArgumentException("The file where the data should be stored in cannot be null!");
		if (compressed)
			NbtIo.writeCompressed(root, file);
		else
			NbtIo.write(root, file);
	}

	/**
	 * The {@link CompoundTag} returned by this method is the backing tag of this instance and every change to it will be
	 * reflected in this instance
	 *
	 * @return The top level compound tag
	 */
	public CompoundTag getRoot() {
		return root;
	}

	/**
	 * Retrieves the name for the top level {@link CompoundTag}
	 *
	 * @return The name of the top level tag
	 */
	public String getName() {
		return root.getName();
	}

	/**
	 * Sets the new name for the top level {@link CompoundTag}
	 *
	 * @param name The new name
	 */
	public void setName(String name) {
		root.setName(name);
	}
}

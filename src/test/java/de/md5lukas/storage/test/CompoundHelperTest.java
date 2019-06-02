package de.md5lukas.storage.test;

import de.md5lukas.nbt.exceptions.NBTTagTypeMismatchException;
import de.md5lukas.nbt.tags.*;
import de.md5lukas.storage.util.CompoundHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class CompoundHelperTest {

	private CompoundTag tag;

	@BeforeEach
	void initTag() {
		tag = new CompoundTag("layer1");
		CompoundTag layer2 = tag.putCompound("layer2");
		CompoundTag layer3 = layer2.putCompound("layer3");
		CompoundTag layer4 = layer3.putCompound("layer4");
		CompoundTag layer5 = layer4.putCompound("layer5");
		CompoundTag layer6 = layer5.putCompound("layer6");
		CompoundTag layer7 = layer6.putCompound("layer7");
		CompoundTag layer8 = layer7.putCompound("layer8");
		tag.putFloat("float", 1353.234f);
		layer2.putByte("byte", (byte) 99);
		layer3.putLong("long", 3573175231L);
		ListTag list = layer4.putListTag("list");
		list.add(new StringTag(null, "Value 0"));
		list.add(new StringTag(null, "Value 1"));
		list.add(new StringTag(null, "Value 2"));
		list.add(new StringTag(null, "Value 3"));
		layer5.putShort("short", (short) 12752);
		layer6.putInt("int", 32153212);
		layer7.putDouble("double", 129385.237851);
		layer8.putIntArray("intArray", new int[]{351, 573453, 934346});
	}

	@AfterEach
	void clean() {
		tag = null;
	}


	@SuppressWarnings("ConstantConditions")
	@Test
	void testGet() {
		assertEquals(1353.234f, ((FloatTag) CompoundHelper.getTag(tag, "float")).data);
		assertEquals((byte) 99, ((ByteTag) CompoundHelper.getTag(tag, "layer2.byte")).data);
		assertEquals(3573175231L, ((LongTag) CompoundHelper.getTag(tag, "layer2.layer3.long")).data);

		ListTag listTag = (ListTag) CompoundHelper.getTag(tag, "layer2.layer3.layer4.list");
		for (int i = 0; i < listTag.size(); ++i) {
			StringTag stringTag = (StringTag) listTag.get(i);
			assertEquals("Value " + i, stringTag.data);
		}

		assertEquals((short) 12752, ((ShortTag) CompoundHelper.getTag(tag, "layer2.layer3.layer4.layer5.short")).data);
		assertEquals(32153212, ((IntTag) CompoundHelper.getTag(tag, "layer2.layer3.layer4.layer5.layer6.int")).data);
		assertEquals(129385.237851, ((DoubleTag) CompoundHelper.getTag(tag, "layer2.layer3.layer4.layer5.layer6.layer7.double")).data);
		assertArrayEquals(new int[]{351, 573453, 934346}, ((IntArrayTag) CompoundHelper.getTag(tag, "layer2.layer3.layer4.layer5.layer6.layer7.layer8.intArray")).data);

		assertNull(CompoundHelper.getTag(tag, "layer2.layer3.layer4.layer5.layer6.layer7.layer8.layer9"));
		assertNull(CompoundHelper.getTag(tag, "layer2.layer3.layer4.layer5.layer6.layer7.layer8.layer9.layer.layer.layer.layer"));
		assertNull(CompoundHelper.getTag(tag, "layer2.layer8"));
	}

	@Test
	void testSetNull() {
		CompoundHelper.setTag(tag, null, "layer2", false);
		assertTrue(tag.contains("layer2"));
	}

	@Test
	void testSetNullOverride() {
		CompoundHelper.setTag(tag, null, "layer2", true);
		assertFalse(tag.contains("layer2"));
	}

	@Test
	void testSetFirstLayer() {
		CompoundHelper.setTag(tag, new IntTag(null, 1337), "int1", false);
		assertTrue(tag.contains("int1"));
		CompoundHelper.setTag(tag, new LongTag(null, 1337), "int1", false);
		assertTrue(tag.contains("int1"));
		assertThrows(NBTTagTypeMismatchException.class, () -> tag.getLong("int1"));
		CompoundHelper.setTag(tag, new LongTag(null, 1337), "int1", true);
		assertDoesNotThrow(() -> tag.getLong("int1"));
	}

	@Test
	void testSetSecondLayer() {
		CompoundHelper.setTag(tag, new IntTag(null, 1337), "layer2.int1", false);
		assertTrue(tag.getCompound("layer2").contains("int1"));
		CompoundHelper.setTag(tag, new LongTag(null, 1337), "layer2.int1", false);
		assertTrue(tag.getCompound("layer2").contains("int1"));
		assertThrows(NBTTagTypeMismatchException.class, () -> tag.getCompound("layer2").getLong("int1"));
		CompoundHelper.setTag(tag, new LongTag(null, 1337), "layer2.int1", true);
		assertDoesNotThrow(() -> tag.getCompound("layer2").getLong("int1"));
	}

	@Test
	void testSetCreateNewLayer() {
		assertFalse(tag.contains("newLayer"));
		CompoundHelper.setTag(tag, new IntTag(null, 1337), "newLayer.int1", false);
		assertTrue(tag.contains("newLayer"));
		assertTrue(tag.getCompound("newLayer").contains("int1"));
		CompoundHelper.setTag(tag, new LongTag(null, 1337), "newLayer.int1", false);
		assertTrue(tag.getCompound("newLayer").contains("int1"));
		assertThrows(NBTTagTypeMismatchException.class, () -> tag.getCompound("newLayer").getLong("int1"));
		CompoundHelper.setTag(tag, new LongTag(null, 1337), "newLayer.int1", true);
		assertDoesNotThrow(() -> tag.getCompound("newLayer").getLong("int1"));
	}
}

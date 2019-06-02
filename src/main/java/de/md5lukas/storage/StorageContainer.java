package de.md5lukas.storage;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * This interface defines all the basic getters and setters for a data storage container
 */
public interface StorageContainer extends FileStorage {

	//<editor-fold desc="Set">

	/**
	 * Sets a new value at the specified path. This will not override the existing value if it is of a different type<br><br>
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
	 * Sets a new value at the specified path.<br><br>
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

	/**
	 * This method checks if a value is present under the specified path. If it found nothing it will set the given
	 * value into the given path.
	 *
	 * @param path  The path where the value should be stored
	 * @param value The value
	 * @see #contains(String) For checking if a value is present
	 * @see #set(String, Object) For setting a value and possible types for the object
	 */
	void setDefault(String path, Object value);
	//</editor-fold>

	//<editor-fold desc="Get values">

	/**
	 * This method will look for a string in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return {@link Optional#empty()}
	 *
	 * @param path The path to the string
	 * @return A optional containing the found string or a {@link Optional#empty()}
	 */
	Optional<String> getString(String path);

	/**
	 * This method will look for a String in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return the specified default value
	 *
	 * @param path The path to the String
	 * @param def  The value to return, of the above mentioned case is present
	 * @return The found String or a the default value
	 */
	String getString(String path, String def);


	/**
	 * This method will look for a boolean in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return {@link Optional#empty()}
	 *
	 * @param path The path to the boolean
	 * @return A optional containing the found boolean or a {@link Optional#empty()}
	 */
	Optional<Boolean> getBoolean(String path);

	/**
	 * This method will look for a boolean in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return the specified default value
	 *
	 * @param path The path to the boolean
	 * @param def  The value to return, of the above mentioned case is present
	 * @return The found boolean or a the default value
	 */
	boolean getBoolean(String path, boolean def);


	/**
	 * This method will look for a byte in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return {@link Optional#empty()}
	 *
	 * @param path The path to the byte
	 * @return A optional containing the found byte or a {@link Optional#empty()}
	 */
	Optional<Byte> getByte(String path);

	/**
	 * This method will look for a byte in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return the specified default value
	 *
	 * @param path The path to the byte
	 * @param def  The value to return, of the above mentioned case is present
	 * @return The found byte or a the default value
	 */
	byte getByte(String path, byte def);


	/**
	 * This method will look for a short in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return {@link Optional#empty()}
	 *
	 * @param path The path to the short
	 * @return A optional containing the found short or a {@link Optional#empty()}
	 */
	Optional<Short> getShort(String path);

	/**
	 * This method will look for a short in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return the specified default value
	 *
	 * @param path The path to the short
	 * @param def  The value to return, of the above mentioned case is present
	 * @return The found short or a the default value
	 */
	short getShort(String path, short def);


	/**
	 * This method will look for a integer in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return {@link Optional#empty()}
	 *
	 * @param path The path to the integer
	 * @return A optional containing the found integer or a {@link Optional#empty()}
	 */
	Optional<Integer> getInt(String path);

	/**
	 * This method will look for a integer in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return the specified default value
	 *
	 * @param path The path to the integer
	 * @param def  The value to return, of the above mentioned case is present
	 * @return The found integer or a the default value
	 */
	int getInt(String path, int def);

	/**
	 * This method will look for a long in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return {@link Optional#empty()}
	 *
	 * @param path The path to the long
	 * @return A optional containing the found long or a {@link Optional#empty()}
	 */
	Optional<Long> getLong(String path);

	/**
	 * This method will look for a long in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return the specified default value
	 *
	 * @param path The path to the long
	 * @param def  The value to return, of the above mentioned case is present
	 * @return The found long or a the default value
	 */
	long getLong(String path, long def);

	/**
	 * This method will look for a float in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return {@link Optional#empty()}
	 *
	 * @param path The path to the float
	 * @return A optional containing the found float or a {@link Optional#empty()}
	 */
	Optional<Float> getFloat(String path);

	/**
	 * This method will look for a float in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return the specified default value
	 *
	 * @param path The path to the float
	 * @param def  The value to return, of the above mentioned case is present
	 * @return The found float or a the default value
	 */
	float getFloat(String path, float def);

	/**
	 * This method will look for a double in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return {@link Optional#empty()}
	 *
	 * @param path The path to the double
	 * @return A optional containing the found double or a {@link Optional#empty()}
	 */
	Optional<Double> getDouble(String path);

	/**
	 * This method will look for a double in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return the specified default value
	 *
	 * @param path The path to the double
	 * @param def  The value to return, of the above mentioned case is present
	 * @return The found String or a the double value
	 */
	double getDouble(String path, double def);
	//</editor-fold>

	//<editor-fold desc="Get lists">

	/**
	 * This method will look for a List&#60;Byte&#62; in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return {@link Optional#empty()}
	 *
	 * @param path The path to the List&#60;Byte&#62;
	 * @return A optional containing the found List&#60;Byte&#62; or a {@link Optional#empty()}
	 */
	Optional<List<Byte>> getByteList(String path);

	/**
	 * This method will look for a List&#60;Byte&#62; in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return the specified default value
	 *
	 * @param path The path to the List&#60;Byte&#62;
	 * @param def  The value to return, of the above mentioned case is present
	 * @return The found List&#60;Byte&#62; or a the default value
	 */
	List<Byte> getByteList(String path, List<Byte> def);

	/**
	 * This method will look for a List&#60;Short&#62; in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return {@link Optional#empty()}
	 *
	 * @param path The path to the List&#60;Short&#62;
	 * @return A optional containing the found List&#60;Short&#62; or a {@link Optional#empty()}
	 */
	Optional<List<Short>> getShortList(String path);

	/**
	 * This method will look for a List&#60;Short&#62; in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return the specified default value
	 *
	 * @param path The path to the List&#60;Short&#62;
	 * @param def  The value to return, of the above mentioned case is present
	 * @return The found List&#60;Short&#62; or a the default value
	 */
	List<Short> getShortList(String path, List<Short> def);

	/**
	 * This method will look for a List&#60;Integer&#62; in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return {@link Optional#empty()}
	 *
	 * @param path The path to the List&#60;Integer&#62;
	 * @return A optional containing the found List&#60;Integer&#62; or a {@link Optional#empty()}
	 */
	Optional<List<Integer>> getIntList(String path);

	/**
	 * This method will look for a List&#60;Integer&#62; in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return the specified default value
	 *
	 * @param path The path to the List&#60;Integer&#62;
	 * @param def  The value to return, of the above mentioned case is present
	 * @return The found List&#60;Integer&#62; or a the default value
	 */
	List<Integer> getIntList(String path, List<Integer> def);

	/**
	 * This method will look for a List&#60;Long&#62; in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return {@link Optional#empty()}
	 *
	 * @param path The path to the List&#60;Long&#62;
	 * @return A optional containing the found List&#60;Long&#62; or a {@link Optional#empty()}
	 */
	Optional<List<Long>> getLongList(String path);

	/**
	 * This method will look for a List&#60;Long&#62; in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return the specified default value
	 *
	 * @param path The path to the List&#60;Long&#62;
	 * @param def  The value to return, of the above mentioned case is present
	 * @return The found List&#60;Long&#62; or a the default value
	 */
	List<Long> getLongList(String path, List<Long> def);

	/**
	 * This method will look for a List&#60;Float&#62; in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return {@link Optional#empty()}
	 *
	 * @param path The path to the List&#60;Float&#62;
	 * @return A optional containing the found List&#60;Float&#62; or a {@link Optional#empty()}
	 */
	Optional<List<Float>> getFloatList(String path);

	/**
	 * This method will look for a List&#60;Float&#62; in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return the specified default value
	 *
	 * @param path The path to the List&#60;Float&#62;
	 * @param def  The value to return, of the above mentioned case is present
	 * @return The found List&#60;Float&#62; or a the default value
	 */
	List<Float> getFloatList(String path, List<Float> def);

	/**
	 * This method will look for a List&#60;Double&#62; in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return {@link Optional#empty()}
	 *
	 * @param path The path to the List&#60;Double&#62;
	 * @return A optional containing the found List&#60;Double&#62; or a {@link Optional#empty()}
	 */
	Optional<List<Double>> getDoubleList(String path);

	/**
	 * This method will look for a List&#60;Double&#62; in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return the specified default value
	 *
	 * @param path The path to the List&#60;Double&#62;
	 * @param def  The value to return, of the above mentioned case is present
	 * @return The found List&#60;Double&#62; or a the default value
	 */
	List<Double> getDoubleList(String path, List<Double> def);

	/**
	 * This method will look for a List&#60;String&#62; in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return {@link Optional#empty()}
	 *
	 * @param path The path to the List&#60;String&#62;
	 * @return A optional containing the found List&#60;String&#62; or a {@link Optional#empty()}
	 */
	Optional<List<String>> getStringList(String path);

	/**
	 * This method will look for a List&#60;String&#62; in the specified path with the prepended path prefix.
	 * If the found value is of a different type or not present this method will return the specified default value
	 *
	 * @param path The path to the List&#60;String&#62;
	 * @param def  The value to return, of the above mentioned case is present
	 * @return The found List&#60;String&#62; or a the default value
	 */
	List<String> getStringList(String path, List<String> def);
	//</editor-fold>

	/**
	 * This method
	 *
	 * @param path The paths where the keys should be retrieved from
	 * @return All the keys for the direct descendants of the given path
	 */
	Set<String> getKeys(String path);

	/**
	 * Checks if a value at the given path is set
	 *
	 * @param path The path to the value
	 * @return <code>true</code> if a value is existing at the given path, <code>false</code> otherwise
	 */
	boolean contains(String path);

	/**
	 * Sets the default value whether values should be overridden or not
	 *
	 * @param overrideDefault The new default if override should be enabled
	 * @see #set(String, Object) The default values is used for this method
	 */
	void setOverrideDefault(boolean overrideDefault);

	/**
	 * Retrieves the set default value
	 *
	 * @return The default value
	 */
	boolean getOverrideDefault();

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

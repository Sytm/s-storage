package de.md5lukas.storage;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This is a implementation of {@link StorageContainer} using a map as a backing data structure and saving and loading
 * it to and from yaml files using <a href="https://bitbucket.org/asomov/snakeyaml/">SnakeYaml</a>
 *
 * @author Md5Lukas
 */
public class YamlStorage extends AbstractMapBasedStorageContainer {

	private Representer representer = new Representer();
	private final DumperOptions dumperOptions = new DumperOptions();
	private final Yaml yaml = new Yaml(new SafeConstructor(), representer, dumperOptions);
	private final YamlOptions yamlOptions = new YamlOptions();

	public YamlStorage() {
		super();
		restoreDefaultOptions();
	}

	public YamlOptions options() {
		return yamlOptions;
	}

	public void restoreDefaultOptions() {
		dumperOptions.setSplitLines(false);
		dumperOptions.setPrettyFlow(true);
		yamlOptions.restoreDefaults();
		applyOptions();
	}

	private void applyOptions() {
		dumperOptions.setIndent(yamlOptions.indent());
		dumperOptions.setDefaultFlowStyle(yamlOptions.flowStyle().getSnakeYamlFlowStyle());
		representer.setDefaultFlowStyle(yamlOptions.flowStyle().getSnakeYamlFlowStyle());
	}

	/**
	 * Loads the data from the specified file as a raw text file
	 *
	 * @param file       The file where the data should be loaded from
	 * @param compressed This parameter is ignored, as text files cannot be compressed
	 * @throws IOException If an I/O exception occurred
	 */
	@Override
	public void load(File file, boolean compressed) throws IOException {
		if (file == null)
			throw new IllegalArgumentException("The file where the data should be loaded from cannot null!");
		map.clear();
		map = yaml.load(new FileReader(file));
	}

	/**
	 * This saves the data to the specified as a raw text file
	 *
	 * @param file       The file where the data should be written to
	 * @param compressed This parameter is ignored, as text files cannot be compressed
	 * @throws IOException If an I/O exception occurred
	 */
	@Override
	public void save(File file, boolean compressed) throws IOException {
		if (file == null)
			throw new IllegalArgumentException("The file where the data should be stored in cannot null!");
		applyOptions();
		yaml.dump(map, new FileWriter(file));
	}

	/**
	 * Saves the data stored in this instance as a string
	 *
	 * @return The yaml document as a string
	 */
	public String saveAsString() {
		applyOptions();
		return yaml.dump(map);
	}

	/**
	 * Overrides all present data in this instance by loading a yaml document from the given string
	 *
	 * @param string The yaml document as a string
	 */
	public void loadFromString(String string) {
		if (string == null)
			throw new IllegalArgumentException("The string to load cannot be null!");
		map.clear();
		map = yaml.load(string);
	}
}

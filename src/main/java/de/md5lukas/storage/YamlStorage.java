package de.md5lukas.storage;

import org.jetbrains.annotations.Contract;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class YamlStorage extends MapBasedStorageContainer {

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

	@Override
	public void load(File file, boolean compressed) throws IOException {
		if (file == null)
			throw new IllegalArgumentException("The file where the data should be loaded from cannot null!");
		root.clear();
		root = yaml.load(new FileReader(file));
	}

	@Override
	public void save(File file, boolean compressed) throws IOException {
		if (file == null)
			throw new IllegalArgumentException("The file where the data should be stored in cannot null!");
		applyOptions();
		yaml.dump(root, new FileWriter(file));
	}

	@Contract(pure = true)
	public String saveAsString() {
		applyOptions();
		return yaml.dump(root);
	}

	@Contract("null -> fail")
	public void loadFromString(String string) {
		if (string == null)
			throw new IllegalArgumentException("The string to load cannot be null!");
		root.clear();
		root = yaml.load(string);
	}

	public Map<String, Object> getRoot() {
		return root;
	}
}

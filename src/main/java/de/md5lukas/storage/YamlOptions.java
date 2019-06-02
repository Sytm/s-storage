package de.md5lukas.storage;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.emitter.Emitter;

/**
 * This class is a basic abstraction of {@link DumperOptions} to reduce the possible settings to actually useful amount
 *
 * @author Md5Lukas
 */
public class YamlOptions {

	public enum FlowStyle {
		BLOCK(DumperOptions.FlowStyle.BLOCK), FLOW(DumperOptions.FlowStyle.FLOW), AUTO(DumperOptions.FlowStyle.AUTO);

		private DumperOptions.FlowStyle flowStyle;

		FlowStyle(DumperOptions.FlowStyle flowStyle) {
			this.flowStyle = flowStyle;
		}

		public DumperOptions.FlowStyle getSnakeYamlFlowStyle() {
			return flowStyle;
		}
	}

	private FlowStyle flowStyle;
	private int indent;

	public YamlOptions() {
		restoreDefaults();
	}

	/**
	 * Retrieves the currently set indentation amount
	 *
	 * @return The currently set indentation
	 */
	public int indent() {
		return indent;
	}

	/**
	 * Sets the new indentation amount
	 *
	 * @param indent The new indentation
	 * @throws IllegalArgumentException When the value <code>indent</code> is lower as <code>1</code> or higher than <code>10</code>
	 */
	public void indent(int indent) {
		if (indent < Emitter.MIN_INDENT)
			throw new IllegalArgumentException("The indent must be at least " + Emitter.MIN_INDENT);
		if (indent > Emitter.MAX_INDENT)
			throw new IllegalArgumentException("The indent must be at most " + Emitter.MAX_INDENT);
		this.indent = indent;
	}

	/**
	 * Retrieves the currently set flow style
	 *
	 * @return The currently set flow style
	 */
	public FlowStyle flowStyle() {
		return flowStyle;
	}

	/**
	 * Sets the flow style for the yaml document
	 *
	 * @param flowStyle The flow style
	 */
	public void flowStyle(FlowStyle flowStyle) {
		this.flowStyle = flowStyle;
	}

	/**
	 * Restores the default settings in this instance
	 */
	public void restoreDefaults() {
		indent = 2;
		flowStyle = FlowStyle.BLOCK;
	}
}

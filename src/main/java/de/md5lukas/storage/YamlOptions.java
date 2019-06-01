package de.md5lukas.storage;

import org.yaml.snakeyaml.DumperOptions;

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

	public int indent() {
		return indent;
	}

	public void indent(int indent) {
		this.indent = indent;
	}

	public FlowStyle flowStyle() {
		return flowStyle;
	}

	public void flowStyle(FlowStyle flowStyle) {
		this.flowStyle = flowStyle;
	}

	public void restoreDefaults() {
		indent = 2;
		flowStyle = FlowStyle.BLOCK;
	}
}

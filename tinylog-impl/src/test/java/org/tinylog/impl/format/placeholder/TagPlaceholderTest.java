package org.tinylog.impl.format.placeholder;

import java.sql.Types;

import org.junit.jupiter.api.Test;
import org.tinylog.impl.LogEntry;
import org.tinylog.impl.LogEntryValue;
import org.tinylog.impl.format.SqlRecord;
import org.tinylog.impl.test.LogEntryBuilder;
import org.tinylog.impl.test.PlaceholderRenderer;

import static org.assertj.core.api.Assertions.assertThat;

class TagPlaceholderTest {

	/**
	 * Verifies that the log entry value {@link LogEntryValue#TAG} is defined as required by the tag code placeholder.
	 */
	@Test
	void requiredLogEntryValues() {
		TagPlaceholder placeholder = new TagPlaceholder(null, null);
		assertThat(placeholder.getRequiredLogEntryValues()).containsExactly(LogEntryValue.TAG);
	}

	/**
	 * Verifies that the assigned tag of tagged log entries is output.
	 */
	@Test
	void renderWithTag() {
		TagPlaceholder placeholder = new TagPlaceholder("-", null);
		PlaceholderRenderer renderer = new PlaceholderRenderer(placeholder);
		LogEntry logEntry = new LogEntryBuilder().tag("foo").create();
		assertThat(renderer.render(logEntry)).isEqualTo("foo");
	}

	/**
	 * Verifies that the default value is output for untagged log entries.
	 */
	@Test
	void renderWithoutTag() {
		TagPlaceholder placeholder = new TagPlaceholder("-", null);
		PlaceholderRenderer renderer = new PlaceholderRenderer(placeholder);
		LogEntry logEntry = new LogEntryBuilder().create();
		assertThat(renderer.render(logEntry)).isEqualTo("-");
	}

	/**
	 * Verifies that the assigned tag of tagged log entries is resolved.
	 */
	@Test
	void resolveWithTag() {
		TagPlaceholder placeholder = new TagPlaceholder(null, "-");
		LogEntry logEntry = new LogEntryBuilder().tag("foo").create();
		assertThat(placeholder.resolve(logEntry))
			.usingRecursiveComparison()
			.isEqualTo(new SqlRecord<>(Types.VARCHAR, "foo"));
	}

	/**
	 * Verifies that the default value is resolved for untagged log entries.
	 */
	@Test
	void resolveWithoutTag() {
		TagPlaceholder placeholder = new TagPlaceholder(null, "-");
		LogEntry logEntry = new LogEntryBuilder().create();
		assertThat(placeholder.resolve(logEntry))
			.usingRecursiveComparison()
			.isEqualTo(new SqlRecord<>(Types.VARCHAR, "-"));
	}

}
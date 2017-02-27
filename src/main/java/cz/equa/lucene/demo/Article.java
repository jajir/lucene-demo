package cz.equa.lucene.demo;

public class Article {

	private final String name;

	private final String perex;

	private final String text;

	private Article(final String name, final String perex, final String text) {
		this.name = name;
		this.perex = perex;
		this.text = text;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private String name;

		private String perex;

		private String text;

		private Builder() {
		}

		public Article build() {
			return new Article(name, perex, text);
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setPerex(String perex) {
			this.perex = perex;
			return this;
		}

		public Builder setText(String text) {
			this.text = text;
			return this;
		}

	}

	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}

	public String getPerex() {
		return perex;
	}

}

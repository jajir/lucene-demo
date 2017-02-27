package cz.equa.lucene.demo;

public class Article {

	private final String name;

	private final String url;

	private final String tags;

	private final String perex;

	private final String text;

	private Article(final String name, final String url, final String tags, final String perex, final String text) {
		this.name = name;
		this.url = url;
		this.tags = tags;
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

		private String url;

		private String tags;

		private Builder() {
		}

		public Article build() {
			return new Article(name, url, tags, perex, text);
		}

		public Builder setName(final String name) {
			this.name = name;
			return this;
		}

		public Builder setUrl(final String url) {
			this.url = url;
			return this;
		}

		public Builder setTags(final String tags) {
			this.tags = tags;
			return this;
		}

		public Builder setPerex(final String perex) {
			this.perex = perex;
			return this;
		}

		public Builder setText(final String text) {
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

	public String getUrl() {
		return url;
	}

	public String getTags() {
		return tags;
	}

}

package cz.equa.lucene.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

public class FirstTest {

	private final static Path INDEX = FileSystems.getDefault().getPath("target/index");

	private Directory openDirectory() throws IOException {
		return FSDirectory.open(INDEX);
	}

	private void cleanIndex() {
		File index = INDEX.toFile();
		if (!index.exists()) {
			return;
		}
		if (index.isFile()) {
			throw new IllegalArgumentException(index.getAbsolutePath() + " is file");
		}
		Arrays.stream(index.listFiles()).forEach(file -> {
			System.out.println("deleting: " + file.getName());
			file.delete();
		});
	}

	private void build_index() throws ParseException, IOException {
		Analyzer analyzer = new StandardAnalyzer();

		// Store the index in memory:
		// To store an index on disk, use this instead:
		Directory directory = openDirectory();
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		IndexWriter iwriter = new IndexWriter(directory, config);

		PrepareData.init().forEach(article -> {
			Document doc = new Document();
			doc.add(new Field("name", article.getName(), TextField.TYPE_STORED));
			doc.add(new Field("perex", article.getPerex(), TextField.TYPE_STORED));
			doc.add(new Field("text", article.getText(), TextField.TYPE_STORED));
			addDoc(iwriter, doc);
		});
		iwriter.close();
		directory.close();
	}

	@Test
	public void test_create_index() throws ParseException, IOException {
		build_index();
	}

	private final void addDoc(IndexWriter iwriter, Document doc) {
		try {
			iwriter.addDocument(doc);
		} catch (IOException e) {
			throw new DemoException(e.getMessage(), e);
		}
	}

	private void rebuildIndex() throws ParseException, IOException {
		cleanIndex();
		build_index();
	}
	
	@Test
	public void test_simple_boolean_search() throws Exception {
		rebuildIndex();
		Analyzer analyzer = new StandardAnalyzer();

		// Now search the index:
		Directory directory = openDirectory();
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);
		// Parse a simple query that searches for "text":
		QueryParser parser = new QueryParser("text", analyzer);
		Query query = parser.parse("dir?");
		ScoreDoc[] hits = isearcher.search(query, 1000).scoreDocs;
		// Iterate through the results:
		System.out.println("'" + hits.length + "' documents was found.");
		for (int i = 0; i < hits.length; i++) {
			Document hitDoc = isearcher.doc(hits[i].doc);
			System.out.println("doc: " + hitDoc.get("text"));
		}
		ireader.close();
	}

	@Test
	public void test_clean() throws Exception {
		cleanIndex();
	}
}

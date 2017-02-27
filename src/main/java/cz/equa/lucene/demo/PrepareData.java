package cz.equa.lucene.demo;

import java.util.ArrayList;
import java.util.List;

public class PrepareData {

	public static List<Article> init() {
		List<Article> list = new ArrayList<>();
		list.add(Article.builder().setName("Ahoj").setPerex("Lidi").setText("Jak to jde?").build());
		list.add(Article.builder().setName("lego").setPerex("super").setText("pokus je dira").build());
		list.add(Article.builder().setName("blekota").setPerex("jede").setText("ve vode").build());
		list.add(Article.builder().setName("krecek").setPerex("bydli").setText("v dire").build());
		return list;
	}

}

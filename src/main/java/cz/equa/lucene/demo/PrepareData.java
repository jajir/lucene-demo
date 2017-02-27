package cz.equa.lucene.demo;

import java.util.ArrayList;
import java.util.List;

public class PrepareData {

	public static List<Article> init() {
		List<Article> list = new ArrayList<>();
		list.add(Article.builder().setName("clanek 1").setUrl("http://www.idnes.cz/clanek/jak-koupit-psa")
				.setTags("koupe, aukro, ras, psa, navod").setPerex("Podrobny navod na nakup psa")
				.setText("Jakou koupit psa? Je to docela jednoduche, psa koupite na aukro.cz. "
						+ "Proste do  hledani napiste 'koupit psa' a staci si vybrat. "
						+ "Pokud netrvate na tom, ze bude zivy, zeptejte se na psa u rasa. ")
				.build());
		list.add(Article.builder().setName("clanek 2").setUrl("http://www.idnes.cz/clanek/letadlo-spadlo").setTags("")
				.setPerex("tragedie").setText("Spadlo az na zem, na karlovarsku").build());
		list.add(Article.builder().setName("clanek 3").setUrl("http://www.idnes.cz/clanek/babis-koupil-chatu")
				.setTags("babis, chata").setPerex("jede").setText("ve vode").build());
		list.add(Article.builder().setName("clanek 4")
				.setUrl("http://www.idnes.cz/clanek/vinetou-byl-viden-na-karlovarsku")
				.setTags("vinetou, karlovarsko, psa").setPerex("bydli")
				.setText("Vinetou a jeho bratr byl viden na karlovarsku. Vinetou byl se svym vernym psem Jahodou. "
						+ "Vinetou byl viden, jak nakopl sveho psa Jahodu.")
				.build());
		return list;
	}

}

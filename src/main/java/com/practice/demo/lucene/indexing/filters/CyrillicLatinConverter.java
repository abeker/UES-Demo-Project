package com.practice.demo.lucene.indexing.filters;

public class CyrillicLatinConverter {

	public static String cir2lat(String text) {

		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			char c=text.charAt(i);
			switch(c){
				case '\u0430': ret.append("a"); break;
				case '\u0431': ret.append("b"); break;
				case '\u0446': ret.append("c"); break;
				case '\u0434': ret.append("d"); break;
				case '\u0435': ret.append("e"); break;
				case '\u0444': ret.append("f"); break;
				case '\u0433': ret.append("g"); break;
				case '\u0445': ret.append("h"); break;
				case '\u0438': ret.append("i"); break;
				case '\u0458': ret.append("j"); break;
				case '\u043A': ret.append("k"); break;
				case '\u043B': ret.append("l"); break;
				case '\u043C': ret.append("m"); break;
				case '\u043D': ret.append("n"); break;
				case '\u043E': ret.append("o"); break;
				case '\u043F': ret.append("p"); break;
				case '\u0440': ret.append("r"); break;
				case '\u0441': ret.append("s"); break;
				case '\u0442': ret.append("t"); break;
				case '\u0443': ret.append("u"); break;
				case '\u0432': ret.append("v"); break;
				case '\u0437': ret.append("z"); break;
				case '\u0410': ret.append("A"); break;
				case '\u0411': ret.append("B"); break;
				case '\u0426': ret.append("C"); break;
				case '\u0414': ret.append("D"); break;
				case '\u0415': ret.append("E"); break;
				case '\u0424': ret.append("F"); break;
				case '\u0413': ret.append("G"); break;
				case '\u0425': ret.append("H"); break;
				case '\u0418': ret.append("I"); break;
				case '\u0408': ret.append("J"); break;
				case '\u041A': ret.append("K"); break;
				case '\u041B': ret.append("L"); break;
				case '\u041C': ret.append("M"); break;
				case '\u041D': ret.append("N"); break;
				case '\u041E': ret.append("O"); break;
				case '\u041F': ret.append("P"); break;
				case '\u0420': ret.append("R"); break;
				case '\u0421': ret.append("S"); break;
				case '\u0422': ret.append("T"); break;
				case '\u0423': ret.append("U"); break;
				case '\u0412': ret.append("V"); break;
				case '\u0417': ret.append("Z"); break;
				case '\u045B': ret.append("\u0107"); break;
				case '\u0447': ret.append("\u010D"); break;
				case '\u0452': ret.append("\u0111"); break;
				case '\u0448': ret.append("\u0161"); break;
				case '\u0436': ret.append("\u017E"); break;
				case '\u040B': ret.append("\u0106"); break;
				case '\u0427': ret.append("\u010C"); break;
				case '\u0402': ret.append("\u0110"); break;
				case '\u0428': ret.append("\u0160"); break;
				case '\u0416': ret.append("\u017D"); break;
				case '\u045F': ret.append("d\u017E");break;
				case '\u0459': ret.append("lj");break;
				case '\u045A': ret.append("nj");break;
				case '\u040F': ret.append("D\u017E");break;
				case '\u0409': ret.append("Lj");break;
				case '\u040A': ret.append("Nj");break;
				default : ret.append(c);
			}
		}
		return ret.toString();
	}
}

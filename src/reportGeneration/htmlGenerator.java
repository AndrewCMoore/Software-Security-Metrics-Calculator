package reportGeneration;

public class htmlGenerator {

	public htmlGenerator() {
		// TODO access list of metrics
	}

	public String makeNavBar() {
		String navBar = "<nav class=\"navbar\">"
					+	"<button class=\"navButton\" onclick=\"expand()\">Open Menu</button>"
					+	"<ul id=\"navBar\" class=\"line collapse\">"
					+		"<li class=\"lineItem\"><a>Flexability</a></li>"
					+		"<li class=\"lineItem\"><a>Readability</a></li>"
					+		"<li class=\"lineItem\"><a>Reusability</a></li>"
					+		"<li class=\"lineItem\"><a>Effectiveness</a></li>"
					+		"<li class=\"lineItem\"><a>Extendability</a></li>"
					+		"<li class=\"lineItem\"><a>Functionality<a /></li>"
					+		"<li class=\"lineItem\"><a>Overall Score</a></li>"
					+	"</ul>"
					+"</nav>";
		return navBar;
	}

	public String makeCircle(String name, String number, String cssClass) {
		String c = "";
		c += "<div class=\"" + cssClass + "\">";
		c += "<span><span>" + name + "</span> <br>";
		c += "<span class=\"number\">" + number + "</span></span><div class=\"slice\">";
		c += "<div class=\"bar\"></div><div class=\"fill\"></div></div></div>";
		return c;
	}

	public String makeTable(String[][] table) {
		String t = "";
		t += "<table>\n";
		for (int i = 0; i < table.length; i++) {
			if (i == 0) {
				t += "<tr class = \"tableHead\">\n";
			} else {
				t += "<tr>\n";
			}
			for (int j = 0; j < table[i].length; j++) {
				if (i == 0 || j == 0) {
					t += "<th>" + table[i][j] + "</th>\n";
				} else {
					t += "<td>" + table[i][j] + "</td>\n";
				}
			}
			t += "<\tr>\n";
		}
		t += "</table>\n";
		return t;
	}

}

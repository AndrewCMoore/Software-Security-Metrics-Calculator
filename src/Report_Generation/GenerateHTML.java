package Report_Generation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Calculator.Calculator;

public class GenerateHTML {

	private final String FILENAME = "../../index";
	private final String type = ".html";
	private File htmlFile;
	private String fileName;
	private Calculator calc;
	private String flex;
	private String read;
	private String reuse;
	private String effect;
	private String extend;
	private String function;
	private String overall;

	// MAIN METHOD

	public GenerateHTML(Calculator c) {
		// TODO access list of metrics
		System.out.println(System.getProperty("user.dir"));
		System.out.println("============================================");
		System.out.println("============================================");
		this.calc = c;
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		flex = String.valueOf(Math.round(generateAverage(classNames, calc.getQualityAttributes().getFlexibility())));
		read = String
				.valueOf(Math.round(generateAverage(classNames, calc.getQualityAttributes().getUnderstandability())));
		reuse = String.valueOf(Math.round(generateAverage(classNames, calc.getQualityAttributes().getReusability())));
		effect = String
				.valueOf(Math.round(generateAverage(classNames, calc.getQualityAttributes().getEffectiveness())));
		extend = String
				.valueOf(Math.round(generateAverage(classNames, calc.getQualityAttributes().getExtendability())));
		function = String
				.valueOf(Math.round(generateAverage(classNames, calc.getQualityAttributes().getFunctionality())));
		overall = String.valueOf((Math.round(generateAverage(classNames, calc.getQualityAttributes().getFlexibility())
				+ generateAverage(classNames, calc.getQualityAttributes().getUnderstandability())
				+ generateAverage(classNames, calc.getQualityAttributes().getReusability())
				+ generateAverage(classNames, calc.getQualityAttributes().getEffectiveness())
				+ generateAverage(classNames, calc.getQualityAttributes().getExtendability())
				+ generateAverage(classNames, calc.getQualityAttributes().getFunctionality())) / 6));
		try {

			htmlFile = new File(FILENAME + type);
			if (htmlFile.createNewFile()) {
				fileName = FILENAME + type;
			} else {
				int extra = 1;
				htmlFile = new File(FILENAME + extra + type);
				while (!htmlFile.createNewFile()) {
					extra++;
					htmlFile = new File(FILENAME + extra + type);
				}
				fileName = FILENAME + extra + type;
			}
			FileWriter writer = new FileWriter(fileName);
			writer.append(makeHead());
			writer.append(makeNavBar());
			writer.append(makeSplashPage());
			writer.append(makeTableSection());
			writer.append(makeDesignQualitiesBreakdown());
			writer.append(makeAllMetricsSection());
			writer.append(makeJavaScriptSection());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// QUALITY ATTRIBUTES

	public String generateOverAllScore() {
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] overallTable = new String[7][6];
		String overTip = "Overall Score";
		String section = makeCircle("Overall Score", overall, "c100 big orange overall besidesOverall p" + overall,
				"overall1", overTip);
		overallTable[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Higeset Value", "Lowest Value",
				"Count" };
		overallTable[1] = generateRow(classNames, calc.getQualityAttributes().getEffectiveness(), "Effectiveness");
		overallTable[2] = generateRow(classNames, calc.getQualityAttributes().getFlexibility(), "Flexability");
		overallTable[3] = generateRow(classNames, calc.getQualityAttributes().getFunctionality(), "Functionality");
		overallTable[4] = generateRow(classNames, calc.getQualityAttributes().getExtendability(), "Extendability");
		overallTable[5] = generateRow(classNames, calc.getQualityAttributes().getReusability(), "Reusability");
		overallTable[6] = generateRow(classNames, calc.getQualityAttributes().getUnderstandability(), "Readability");
		section += makeTable(overallTable);
		section += "</div>\r\n" + "</div>\r\n" + "</div>";
		return section;
	}

	public String generateEffectiveness() {
		String effectTip = "Effectiveness is about achieving the desired functionality. This includes performance and resource usage and leads to higher productivity, and makes the understanding of the software a challenge.";
		String section = makeCircle("Effectiveness", effect, "c100 big maroon effectiveness besides p" + effect,
				"effectiveness1", effectTip);
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] effectiveness = new String[6][6];
		effectiveness[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Higeset Value", "Lowest Value",
				"Count" };
		effectiveness[1] = generateRow(classNames, calc.getDesignPrincipals().getAbstraction(), "Abstraction");
		effectiveness[2] = generateRow(classNames, calc.getDesignPrincipals().getEncapsulation(), "Encapsulation");
		effectiveness[3] = generateRow(classNames, calc.getDesignPrincipals().getComposition(), "Composition");
		effectiveness[4] = generateRow(classNames, calc.getDesignPrincipals().getInheritance(), "Inheritance");
		effectiveness[5] = generateRow(classNames, calc.getDesignPrincipals().getPolymorphism(), "Polymorphism");
		section += makeTable(effectiveness);
		return section;
	}

	public String generateReusability() {
		String reuseTip = "Reusability is how often the code can be reapplied to a new problem. The adaptability or the conformance the code gives the program the ability to be transferred into another environment.";
		String section = makeCircle("Reusability", reuse, "c100 big green reusability besides p" + reuse,
				"reusability1", reuseTip);
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] reusability = new String[5][6];
		reusability[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Higeset Value", "Lowest Value",
				"Count" };
		reusability[1] = generateRow(classNames, calc.getDesignPrincipals().getCoupling(), "Coupling");
		reusability[2] = generateRow(classNames, calc.getDesignPrincipals().getCohesion(), "Cohesion");
		reusability[3] = generateRow(classNames, calc.getDesignPrincipals().getMessaging(), "Messaging");
		reusability[4] = generateRow(classNames, calc.getDesignPrincipals().getDesignSize(), "Design Size");
		section += makeTable(reusability);
		return section;
	}

	public String generateUnderstandability() {
		String readTip = "Readability is the ease of which the software is read and understood. Simplicity in the code structure leads to a higher score.";
		String section = makeCircle("Readability", read, "c100 big pink readability besides p" + read, "readability1",
				readTip);
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] reusability = new String[8][6];
		reusability[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Higeset Value", "Lowest Value",
				"Count" };
		reusability[1] = generateRow(classNames, calc.getDesignPrincipals().getAbstraction(), "Abstraction");
		reusability[2] = generateRow(classNames, calc.getDesignPrincipals().getEncapsulation(), "Encapsulation");
		reusability[3] = generateRow(classNames, calc.getDesignPrincipals().getCoupling(), "Coupling");
		reusability[4] = generateRow(classNames, calc.getDesignPrincipals().getCohesion(), "Cohesion");
		reusability[5] = generateRow(classNames, calc.getDesignPrincipals().getPolymorphism(), "Polymorphism");
		reusability[6] = generateRow(classNames, calc.getDesignPrincipals().getComplexity(), "Complexity");
		reusability[7] = generateRow(classNames, calc.getDesignPrincipals().getDesignSize(), "Design Size");
		section += makeTable(reusability);
		return section;
	}

	public String generateFlexability() {
		String flexTip = "Flexibility is the adaptability of the program. Flexible program allows"
				+ " the architecture to evolve organically, shows good software development, and results"
				+ " in a complex architecture.";
		String section = makeCircle("Flexability", flex, "c100 big flexability besides p" + flex, "readability1",
				flexTip);
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] reusability = new String[5][6];
		reusability[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Higeset Value", "Lowest Value",
				"Count" };
		reusability[1] = generateRow(classNames, calc.getDesignPrincipals().getEncapsulation(), "Encapsulation");
		reusability[2] = generateRow(classNames, calc.getDesignPrincipals().getCoupling(), "Coupling");
		reusability[3] = generateRow(classNames, calc.getDesignPrincipals().getComposition(), "Composition");
		reusability[4] = generateRow(classNames, calc.getDesignPrincipals().getPolymorphism(), "Polymorphism");
		section += makeTable(reusability);
		return section;
	}

	public String generateFunctionality() {
		String functTip = "Functionality is about the behaviour between the inputs and outputs in the program. It also considers the performance during program execution in relation to the fault tolerance and recoverability.";
		String section = makeCircle("Functionality", function, "c100 big skyblue functionality besides p" + function,
				"functionality1", functTip);
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] reusability = new String[6][6];
		reusability[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Higeset Value", "Lowest Value",
				"Count" };
		reusability[1] = generateRow(classNames, calc.getDesignPrincipals().getCohesion(), "Cohesion");
		reusability[2] = generateRow(classNames, calc.getDesignPrincipals().getPolymorphism(), "Polymorphism");
		reusability[3] = generateRow(classNames, calc.getDesignPrincipals().getMessaging(), "Messaging");
		reusability[4] = generateRow(classNames, calc.getDesignPrincipals().getDesignSize(), "Design Size");
		reusability[5] = generateRow(classNames, calc.getDesignPrincipals().getHierarchies(), "Hierarchies");
		section += makeTable(reusability);
		return section;
	}

	public String generateExtendability() {
		String extendTip = "Extendability is the effort needed to make modifications to the project. This includes its stability, testability, and analyzability.";

		String section = makeCircle("Extendability", extend, "c100 big blue extendability besides p" + extend,
				"extendability1", extendTip);
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] reusability = new String[6][6];
		reusability[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Higeset Value", "Lowest Value",
				"Count" };
		reusability[1] = generateRow(classNames, calc.getDesignPrincipals().getAbstraction(), "Abstraction");
		reusability[2] = generateRow(classNames, calc.getDesignPrincipals().getCoupling(), "Coupling");
		reusability[3] = generateRow(classNames, calc.getDesignPrincipals().getMessaging(), "Messaging");
		reusability[4] = generateRow(classNames, calc.getDesignPrincipals().getDesignSize(), "Design Size");
		reusability[5] = generateRow(classNames, calc.getDesignPrincipals().getHierarchies(), "Hierarchies");
		section += makeTable(reusability);
		return section;
	}
	// HTML SECTIONS

	public String makeHead() {
		String head = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"utf-8\">\r\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "<title>Prototype</title>\r\n" + "<link rel=\"stylesheet\" href=\"circle.css\">\r\n"
				+ "<link rel=\"stylesheet\" href=\"style.css\">\r\n" + "</head>";
		return head;
	}

	public String makeNavBar() {
		String navBar = "<nav class=\"menu\">" + "<button  id=\"menuButton\" class=\"navButton\" onclick=\"expandButton()\">Open Menu</button>"
				+ "<ul id=\"navBar\" class=\"lineBar\">" 
				+ "<li class=\"\"><a href =\"#flexabilityData\" class =\"link\">Flexability</a></li>"
				+ "<li class=\"\"><a href =\"#readabilityData\" class =\"link\">Readability</a></li>" 
				+ "<li class=\"\"><a href =\"#reusabilityData\" class =\"link\">Reusability</a></li>"
				+ "<li class=\"\"><a href =\"#effectivenessData\" class =\"link\">Effectiveness</a></li>"
				+ "<li class=\"\"><a href =\"#extendabilityData\" class =\"link\">Extendability</a></li>"
				+ "<li class=\"\"><a href =\"#functionalityData\" class =\"link\">Functionality<a /></li>"
				+ "<li class=\"\"><a href =\"#overallscoreData\" class =\"link\">Overall Score</a></li>" + "</ul>" + "</nav>";
		return navBar;
	}

	public String makeSplashPage() {

		String splash = "<div class=\"container\">\r\n" + "			<div class=\"row\">";
		String flexTip = "Flexibility is the adaptability of the program. Flexible program allows"
				+ " the architecture to evolve organically, shows good software development, and results"
				+ " in a complex architecture.";
		String readTip = "Readability is the ease of which the software is read and understood. Simplicity in the code structure leads to a higher score.";
		String reuseTip = "Reusability is how often the code can be reapplied to a new problem. The adaptability or the conformance the code gives the program the ability to be transferred into another environment.";
		String effectTip = "Effectiveness is about achieving the desired functionality. This includes performance and resource usage and leads to higher productivity, and makes the understanding of the software a challenge.";
		String extendTip = "Extendability is the effort needed to make modifications to the project. This includes its stability, testability, and analyzability.";
		String functTip = "Functionality is about the behaviour between the inputs and outputs in the program. It also considers the performance during program execution in relation to the fault tolerance and recoverability.";
		String overTip = "Overall Score";

		splash += makeCircle("Flexibility", flex, "c100 big flexability p" + flex, "flexability", flexTip);
		splash += makeCircle("Reusability", reuse, "c100 big pink reusability p" + reuse, "reusability", reuseTip);
		splash += makeCircle("Readability", read, "c100 big pink readability p" + read, "readability", readTip);
		splash += makeCircle("Effectiveness", effect, "c100 big maroon effectiveness p" + effect, "effectiveness",
				effectTip);
		splash += makeCircle("Extendability", extend, "c100 big maroon extendability p" + extend, "extendability",
				extendTip);
		splash += makeCircle("Functionality", function, "c100 big maroon functionality p" + function, "functionality",
				functTip);
		splash += makeCircle("Overall Score", overall, "c100 bigger maroon overall p" + overall, "overall", overTip);
		splash += "</div>\r\n" + "		</div>";
		return splash;
	}

	public String makeTableSection() {
		String section = "<div class=\"tables\">\r\n" + "<h2>Metrics BreakDown</h2>" + "<div id=\"overallscoreData\">";
		section += makeCollapseSection("Overall Score", generateOverAllScore());
		section += "</div>\r\n" + "			\r\n" + "			<div id=\"flexabilityData\">";
		section += makeCollapseSection("Flexability", generateFlexability());
		section += "</div>\r\n" + "			\r\n" + "			<div id=\"readabilityData\">";
		section += makeCollapseSection("Reabability", generateUnderstandability());
		section += "</div>\r\n" + "			\r\n" + "			<div id=\"functionalityData\">";
		section += makeCollapseSection("Functionality", generateFunctionality());
		section += "</div>\r\n" + "			\r\n" + "			<div id=\"extendabilityData\">";
		section += makeCollapseSection("Extendability", generateExtendability());
		section += "</div>\r\n" + "			\r\n" + "			<div id=\"effectivenessData\">";
		section += makeCollapseSection("Effectiveness", generateEffectiveness());
		section += "</div>\r\n" + "			\r\n" + "			<div id=\"reusabilityData\">";
		section += makeCollapseSection("Reusability", generateReusability());
		section += "</div>";
		return section;
	}

	public String makeDesignQualitiesBreakdown() {
		String section = "<div class=\"tables\">\r\n" + "<h2>Design Qualities BreakDown</h2>"
				+ "<div id=\"apstractionData\">";
		section += makeCollapseSection("Abstraction", generateAbstraction());
		section += "</div>\r\n" + "			\r\n" + "			<div id=\"cohesionData\">";
		section += makeCollapseSection("Cohesion", generateCohesion());
		section += "</div>\r\n" + "			\r\n" + "			<div id=\"complexityData\">";
		section += makeCollapseSection("Complexity", generateComplexity());
		section += "</div>\r\n" + "			\r\n" + "			<div id=\"compositionData\">";
		section += makeCollapseSection("Composition", generateComposition());
		section += "</div>\r\n" + "			\r\n" + "			<div id=\"couplingData\">";
		section += makeCollapseSection("Coupling", generateCoupling());
		section += "</div>\r\n" + "			\r\n" + "			<div id=\"designSizeData\">";
		section += makeCollapseSection("Design Size", generateDesignSize());
		section += "</div>\r\n" + "			\r\n" + "			<div id=\"encapsulationData\">";
		section += makeCollapseSection("Encapsulation", generateEncapsulation());
		section += "</div>\r\n" + "			\r\n" + "			<div id=\"inheritanceData\">";
		section += makeCollapseSection("Inheritance", generateInheritance());
		section += "</div>\r\n" + "			\r\n" + "			<div id=\"messagingData\">";
		section += makeCollapseSection("Messaging", generateMessaging());
		section += "</div>\r\n" + "			\r\n" + "			<div id=\"polymorphismData\">";
		section += makeCollapseSection("Polymorphism", generatePolymorphism());
		section += "</div>\r\n";
		section += "</div>\r\n";
		return section;
	}

	public String makeAllMetricsSection() {
		String section = "<div class=\"tables\">\r\n" + "<h2>Metrics Breakdown</h2>" + "<div id=\"allMetricsSection\">";
		section += makeCollapseSection("All Metrics", generateAllMetricsBreakdown());
		section += "</div>\r\n";
		section += "</div>\r\n";
		return section;
	}

	public String makeCircle(String name, String number, String cssClass, String id, String toolTip) {
		String c = "";
		c += "<div class=\"" + cssClass + "\"id=\"" + id + "\">";
		c += "<div class=\"tooltiptext tooltiptext-category\">" + toolTip + "</div>";
		c += "<span class=\"title flex\"><span>" + name + "</span> <br>";
		c += "<span class=\"number\">" + number + "</span></span><div class=\"slice\">";
		c += "<div class=\"bar\"></div><div class=\"fill\"></div></div></div>";
		return c;
	}

	public String makeCollapseSection(String name, String content) {
		String section = "<button class=\"accordion\">" + name + "</button>" + "<div class=\"panel\">"
				+ "<div style=\"padding: 20px;\">";
		section += content;
		section += "	</div>\r\n" + "	</div>\r\n" + "	</div>";

		return section;
	}

	public String makeTable(String[][] table) {
		String t = "";
		t += "<table class=\"table\" cellpadding=\"10\" cellspacing=\"10\">\n";
		for (int i = 0; i < table.length; i++) {
			if (i == 0) {
				t += "<thead>\n<tr>\n";
			} else {
				t += "<tr>\n<thead>\n";
			}
			for (int j = 0; j < table[i].length; j++) {
				if (i == 0 || j == 0) {
					t += "<th>" + table[i][j] + "</th>\n";
				} else {
					t += "<td>" + table[i][j] + "</td>\n";
				}
			}
			t += "</tr>\n";
		}
		t += "</table>\n";
		return t;
	}

	public String makeJavaScriptSection() {
		String script = "<script>\r\n"
				+ "						var acc = document.getElementsByClassName(\"accordion\");\r\n"
				+ "			var i;\r\n" + "\r\n" + "			for (i = 0; i < acc.length; i++) {\r\n"
				+ "			  acc[i].addEventListener(\"click\", function() {\r\n"
				+ "				this.classList.toggle(\"active\");\r\n"
				+ "				var panel = this.nextElementSibling;\r\n"
				+ "				if (panel.style.maxHeight) {\r\n" + "				  panel.style.maxHeight = null;\r\n"
				+ "				} else {\r\n"
				+ "				  panel.style.maxHeight = panel.scrollHeight + \"px\";\r\n" + "				} \r\n"
				+ "			  });\r\n" + "			}\r\n" + "		</script>";
				script+="<script>function expandButton(){\r\n"
						+ "	if(document.getElementById(\"navBar\").getAttribute(\"class\")==\"lineBar\"){\r\n"
						+ "		document.getElementById(\"navBar\").setAttribute(\"class\",\"lineBarOpen\");\r\n"
						+ "	}else if(document.getElementById(\"navBar\").getAttribute(\"class\")==\"lineBarOpen\"){\r\n"
						+ "		document.getElementById(\"navBar\").setAttribute(\"class\",\"lineBar\");\r\n"
						+ "	}		\r\n"
						+ "}</script>";
		return script;
	}

	public String[] generateRowString(Set<String> classNames, HashMap<String, Double> results, String metric) {
		String[] row = new String[6];
		row[0] = metric;
		double average = generateAverage(classNames, results);
		double standardDeviation = generateStanderdDeviation(classNames, results, average);
		double highest = getHigestValue(classNames, results);
		double lowest = getLowestValue(classNames, results);
		double count = getCount(classNames, results);
		row[1] = "%.2f" + Double.toString(average);
		row[2] = "%.2f" + Double.toString(standardDeviation);
		row[3] = "%.2f" + Double.toString(highest);
		row[4] = "%.2f" + Double.toString(lowest);
		row[5] = "%.2f" + Double.toString(count);
		return row;
	}

	public String[] generateRow(Set<String> classNames, HashMap<String, Double> results, String metric) {
		String[] row = new String[6];
		row[0] = metric;
		double average = generateAverage(classNames, results);
		double standardDeviation = generateStanderdDeviation(classNames, results, average);
		double highest = getHigestValue(classNames, results);
		double lowest = getLowestValue(classNames, results);
		double count = getCount(classNames, results);
		row[1] = String.format("%.2f", average);
		row[2] = String.format("%.2f", standardDeviation);
		row[3] = String.format("%.2f", highest);
		row[4] = String.format("%.2f", lowest);
		row[5] = String.format("%.2f", count);
		return row;
	}

	public double getHigestValue(Set<String> classNames, HashMap<String, Double> results) {
		double highest = Integer.MIN_VALUE;
		for (String key : classNames) {
			try {
				String value = String.valueOf(results.get(key));
				Double d = Double.parseDouble(value);
				if (d != null) {
					if (highest < d) {
						highest = d;
					}
				}
			} catch (Exception e) {
			}
		}

		return highest;
	}

	public double getLowestValue(Set<String> classNames, HashMap<String, Double> results) {
		double lowest = Integer.MAX_VALUE;
		for (String key : classNames) {
			try {
				String value = String.valueOf(results.get(key));
				Double d = Double.parseDouble(value);
				if (d != null) {
					if (lowest > d) {
						lowest = d;
					}
				}
			} catch (Exception e) {
			}
		}

		return lowest;
	}

	public int getCount(Set<String> classNames, HashMap<String, Double> results) {
		int size = 0;
		for (String key : classNames) {
			try {
				String value = String.valueOf(results.get(key));
				Double d = Double.parseDouble(value);
				if (d != null) {
					size++;
				}
			} catch (Exception e) {
			}
		}

		return size;
	}

	public double generateAverage(Set<String> classNames, HashMap<String, Double> results) {
		double average = 0;
		int size = 0;
		for (String key : classNames) {
			try {

				String value = String.valueOf(results.get(key));
				if (value == null)
					value = "0.0";
				System.out.println("the value is " + Double.parseDouble(value));
				Double d = Double.parseDouble(value);
				if (d != null) {
					System.out.println("We got here");

					size++;
					if (results.get(key).isNaN())
						d = 0.0;
					average += d;
				}
			} catch (Exception e) {
			}
		}
		if (size != 0) {
			average /= size;
			return average;
		}
		return 0;

	}

	public double generateStanderdDeviation(Set<String> classNames, HashMap<String, Double> results, double average) {
		double standardDeviation = 0;
		int size = 0;
		for (String key : classNames) {
			try {
				String value = String.valueOf(results.get(key));
				Double d = Double.parseDouble(value);
				if (d != null) {
					size++;
					if (results.get(key).isNaN())
						d = 0.0;
					standardDeviation += Math.pow((d - average), 2);
				}
			} catch (Exception e) {
			}
		}
		if (size != 0) {
			standardDeviation /= size;
			return standardDeviation;
		}
		return 0;
	}
	// DESIGN QUALITY METRICS

	public String generateAbstraction() {
		String section = "";
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] abstraction = new String[4][6];
		abstraction[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Higeset Value", "Lowest Value",
				"Count" };
		abstraction[1] = generateRow(classNames, calc.getPrimaryMetrics().getAverageNumberOfAncestors(),
				"Average Number of Ancestors");
		abstraction[2] = generateRow(classNames, calc.getPrimaryMetrics().getFailSafeDefaults(), "Fail-Safe Defaults");
		abstraction[3] = generateRow(classNames, calc.getPrimaryMetrics().getReduceAttackSurface(),
				"Reduce Attack Surface");
		section += makeTable(abstraction);
		return section;
	}

	public String generateCohesion() {
		String section = "";
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] cohesion = new String[3][6];
		cohesion[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Higeset Value", "Lowest Value",
				"Count" };
		cohesion[1] = generateRow(classNames, calc.getPrimaryMetrics().getCohesionAmongMethodsInClass(),
				"Cohesion Among Methods in a Class");
		cohesion[2] = generateRow(classNames, calc.getPrimaryMetrics().getLackOfCohesionOfMethods(),
				"Lack of Cohesion of Methods");
		section += makeTable(cohesion);
		return section;
	}

	public String generateCoupling() {
		String section = "";
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		
		Set<String> classes = new HashSet<String>();
		classes.add("project");
		
		String[][] coupling = new String[10][6];
		coupling[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Higeset Value", "Lowest Value",
				"Count" };
		HashMap<String, Double> baseClasses = new HashMap<String, Double>();
		baseClasses.put("project", calc.getPrimaryMetrics().getCountOfBaseClasses());
		coupling[1] = generateRow(classes, baseClasses, "Count of Base Classes");
		coupling[2] = generateRow(classNames, calc.getPrimaryMetrics().getCouplingBetweenObjects(),
				"Coupling Between Objects");
		coupling[3] = generateRow(classNames, calc.getPrimaryMetrics().getCouplingCorruptionPropagation(),
				"Coupling Corruption Propagation");
		coupling[4] = generateRow(classNames, calc.getPrimaryMetrics().getDepthOfInheritanceTree(),
				"Depth of Inheritance Tree");
		coupling[5] = generateRow(classNames, calc.getPrimaryMetrics().getDirectClassCoupling(),
				"Direct Class Coupling");
		coupling[6] = generateRow(classNames, calc.getPrimaryMetrics().getFanIn(), "Fan In");
		coupling[7] = generateRow(classNames, calc.getPrimaryMetrics().getFanOut(), "Fan Out");
		coupling[8] = generateRow(classNames, calc.getPrimaryMetrics().getHenryKafura(), "Henry Kafura");
		coupling[9] = generateRow(classNames, calc.getPrimaryMetrics().getNumberOfChildren(), "Number of Children");
		coupling[9] = generateRow(classNames, calc.getPrimaryMetrics().getNumberOfChildren(),
				"Responses set for a Class");
		section += makeTable(coupling);
		return section;
	}

	public String generateDesignSize() {
		Set<String> classes = new HashSet<String>();
		classes.add("project");
		String section = "";
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] coupling = new String[3][6];
		coupling[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Higeset Value", "Lowest Value",
				"Count" };
		HashMap<String, Double> stallRatio = new HashMap<String, Double>();
		stallRatio.put("project", calc.getPrimaryMetrics().getStallRatio());

		HashMap<String, Double> designSize = new HashMap<String, Double>();
		designSize.put("project", calc.getPrimaryMetrics().getDesignSizeInClasses());

		coupling[1] = generateRow(classes, stallRatio, "Stall Ration");
		coupling[2] = generateRow(classes, designSize, "Design Size in Classes");

		section += makeTable(coupling);
		return section;
	}

	public String generateEncapsulation() {
		String section = "";
		Set<String> classes = new HashSet<String>();
		classes.add("project");

		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] coupling = new String[7][6];
		coupling[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Higeset Value", "Lowest Value",
				"Count" };

		HashMap<String, Double> heirarchies = new HashMap<String, Double>();
		heirarchies.put("project", calc.getPrimaryMetrics().getNumberOfHierarchies());

		HashMap<String, Double> isolation = new HashMap<String, Double>();
		isolation.put("project", calc.getPrimaryMetrics().getIsolation());

		HashMap<String, Double> mechanism = new HashMap<String, Double>();
		mechanism.put("project", calc.getPrimaryMetrics().getLeastCommonMechanism());

		coupling[1] = generateRow(classNames, calc.getPrimaryMetrics().getCriticalElementRatio(),
				"Critical Element Ratio");
		coupling[2] = generateRow(classNames, calc.getPrimaryMetrics().getDataAccessMetric(), "Data Access Metric");
		coupling[3] = generateRow(classNames, calc.getPrimaryMetrics().getGrantLeastPrivelage(),
				"Grant Least Privilege");
		coupling[4] = generateRow(classes, isolation, "Isolation");
		coupling[5] = generateRow(classes, mechanism, "Least Common Mechanism");
		coupling[6] = generateRow(classes, heirarchies, "Number of Hierarchies");

		section += makeTable(coupling);
		return section;
	}

	public String generateInheritance() {
		String section = "";
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] inheritance = new String[2][6];
		inheritance[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Higeset Value", "Lowest Value",
				"Count" };
		inheritance[1] = generateRow(classNames, calc.getPrimaryMetrics().getMeasureOfFunctionalAbtraction(),
				"Measure of Functional Abstraction");
		section += makeTable(inheritance);

		return section;
	}

	public String generateMessaging() {
		String section = "";
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] inheritance = new String[2][6];
		inheritance[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Higeset Value", "Lowest Value",
				"Count" };
		inheritance[1] = generateRow(classNames, calc.getPrimaryMetrics().getClassInterfaceSize(),
				"Measure of Functional Abstraction");
		section += makeTable(inheritance);

		return section;
	}

	public String generatePolymorphism() {
		String section = "";
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] inheritance = new String[2][6];
		inheritance[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Higeset Value", "Lowest Value",
				"Count" };
		inheritance[1] = generateRow(classNames, calc.getPrimaryMetrics().getClassInterfaceSize(),
				"Measure of Functional Abstraction");
		section += makeTable(inheritance);

		return section;
	}

	public String generateComplexity() {
		String section = "";
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] inheritance = new String[15][6];
		inheritance[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Higeset Value", "Lowest Value",
				"Count" };

		Set<String> classes = new HashSet<String>();
		classes.add("project");
		HashMap<String, Double> countofBase = new HashMap<String, Double>();
		countofBase.put("project", calc.getPrimaryMetrics().getCountOfBaseClasses());
		HashMap<String, Double> economyOfMechanism = new HashMap<String, Double>();
		economyOfMechanism.put("project", calc.getPrimaryMetrics().getEconomyOfMechanism());

		HashMap<String, Double> weakestLink = new HashMap<String, Double>();
		weakestLink.put("project", calc.getPrimaryMetrics().getSecureWeakestLink());

		HashMap<String, Double> linesOfCode = new HashMap<String, Double>();
		linesOfCode.put("project", calc.getPrimaryMetrics().getSourceLinesOfCode());

		inheritance[1] = generateRow(classNames, calc.getPrimaryMetrics().getCommentRatio(), "Comment Ratio");
		inheritance[2] = generateRow(classes, countofBase, "Count of Base Classes");
		inheritance[3] = generateRow(classNames, calc.getPrimaryMetrics().getCyclomaticComplexity(),
				"Cyclomatic Complexity");
		inheritance[4] = generateRow(classNames, calc.getPrimaryMetrics().getDepthOfInheritanceTree(),
				"Depth of Inheritance");
		inheritance[5] = generateRow(classNames, calc.getPrimaryMetrics().getCountPath(), "Count Path");
		inheritance[6] = generateRow(classes, economyOfMechanism, "Economy of Mechanism");
		inheritance[7] = generateRow(classNames, calc.getPrimaryMetrics().getMcCabesCyclomaticComplexity(),
				"Mcabes Cyclomatic Complexity");
		inheritance[8] = generateRow(classNames, calc.getPrimaryMetrics().getModifiedCyclomaticComplexity(),
				"Modified Cyclomatic Complexity");
		// inheritance[9] =
		// generateRow(classNames,calc.getPrimaryMetrics().getNestingComplexity(),"Nesting
		// Complexity");
		inheritance[9] = generateRow(classNames, calc.getPrimaryMetrics().getNumberOfChildren(), "Number of Children");
		inheritance[10] = generateRow(classNames, calc.getPrimaryMetrics().getNumberOfMethods(), "Number of Methods");
		inheritance[11] = generateRow(classNames, calc.getPrimaryMetrics().getStrictCyclomaticComplexity(),
				"Strict Cyclomatic Complexity");
		inheritance[12] = generateRow(classes, weakestLink, "Secure Weakest Link");
		inheritance[13] = generateRow(classes, linesOfCode, "Lines of Code");
		inheritance[14] = generateRow(classNames, calc.getPrimaryMetrics().getWeightedMethodsPerClass(),
				"Weighted Methods per Class");

		section += makeTable(inheritance);

		return section;
	}

	public String generateComposition() {
		String section = "";
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] inheritance = new String[2][6];
		inheritance[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Higeset Value", "Lowest Value",
				"Count" };
		inheritance[1] = generateRow(classNames, calc.getPrimaryMetrics().getMeasureOfAggregation(),
				"Measure of Functional Abstraction");
		section += makeTable(inheritance);

		return section;
	}

	public String generateAllMetricsBreakdown() {
		String section = "";
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] metrics = new String[72][6];
		metrics[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Higeset Value", "Lowest Value",
				"Count" };

		Set<String> classes = new HashSet<String>();
		classes.add("project");
		HashMap<String, Double> countofBaseClasses = new HashMap<String, Double>();
		countofBaseClasses.put("project", calc.getPrimaryMetrics().getCountOfBaseClasses());

		HashMap<String, Double> economyofMechanism = new HashMap<String, Double>();
		economyofMechanism.put("project", calc.getPrimaryMetrics().getEconomyOfMechanism());

		HashMap<String, Double> secureWeakestLink = new HashMap<String, Double>();
		secureWeakestLink.put("project", calc.getPrimaryMetrics().getSecureWeakestLink());

		HashMap<String, Double> linesOfCode = new HashMap<String, Double>();
		linesOfCode.put("project", calc.getPrimaryMetrics().getSourceLinesOfCode());

		HashMap<String, Double> baseClass = new HashMap<String, Double>();
		baseClass.put("project", calc.getPrimaryMetrics().getCountOfBaseClasses());

		HashMap<String, Double> stallRatio = new HashMap<String, Double>();
		stallRatio.put("project", calc.getPrimaryMetrics().getStallRatio());

		HashMap<String, Double> heirarchies = new HashMap<String, Double>();
		heirarchies.put("project", calc.getPrimaryMetrics().getNumberOfHierarchies());

		HashMap<String, Double> sercurityAbsoulte = new HashMap<String, Double>();
		sercurityAbsoulte.put("project", calc.getSecondaryMetrics().getSecurityAbsoluteMeasurements());

		double totalAttributes = calc.getTertiaryMetrics().getClassifiedAttributesTotal();
		double totalMethods = calc.getTertiaryMetrics().getClassifiedMethodsTotal();
		double totalClass = calc.getTertiaryMetrics().getCriticalClassesTotal();

		HashMap<String, Double> classifiedAttricbute = new HashMap<String, Double>();
		classifiedAttricbute.put("project", totalMethods);

		HashMap<String, Double> classifiedMethods = new HashMap<String, Double>();
		classifiedMethods.put("project", totalAttributes);

		HashMap<String, Double> classifiedClasses = new HashMap<String, Double>();
		classifiedClasses.put("project", totalClass);

		HashMap<String, Double> criticalClassCoupling = new HashMap<String, Double>();
		criticalClassCoupling.put("project", (double) calc.getTertiaryMetrics().getCriticalClassesCoupling());

		HashMap<String, Double> seialized = new HashMap<String, Double>();
		seialized.put("project", (double) calc.getTertiaryMetrics().getCriticalSerializedClassesProportion());

		HashMap<String, Double> superClass = new HashMap<String, Double>();
		superClass.put("project", (double) calc.getTertiaryMetrics().getCriticalSuperclassesInheritance());

		HashMap<String, Double> accesserClass = new HashMap<String, Double>();
		accesserClass.put("project", (double) calc.getTertiaryMetrics().getUnusedCriticalAccessorClass());

		HashMap<String, Double> reflection = new HashMap<String, Double>();
		reflection.put("project", (double) calc.getTertiaryMetrics().getReflectionPackageBoolean());

		HashMap<String, Double> partCriticalClasses = new HashMap<String, Double>();
		partCriticalClasses.put("project", (double) calc.getTertiaryMetrics().getCompositePartCriticalClasses());

		HashMap<String, Double> criticalClassExtensibility = new HashMap<String, Double>();
		criticalClassExtensibility.put("project", (double) calc.getTertiaryMetrics().getCriticalClassesExtensibility());

		HashMap<String, Double> criticalDesignProportion = new HashMap<String, Double>();
		criticalDesignProportion.put("project", (double) calc.getTertiaryMetrics().getCriticalDesignProportion());

		HashMap<String, Double> criticalSuperClassProportion = new HashMap<String, Double>();
		criticalSuperClassProportion.put("project",
				(double) calc.getTertiaryMetrics().getCriticalSuperclassesProportion());

		HashMap<String, Double> isolation = new HashMap<String, Double>();
		isolation.put("project", (double) calc.getPrimaryMetrics().getIsolation());

		HashMap<String, Double> designSize = new HashMap<String, Double>();
		designSize.put("project", (double) calc.getPrimaryMetrics().getDesignSizeInClasses());

		HashMap<String, Double> mechanism = new HashMap<String, Double>();
		mechanism.put("project", (double) calc.getPrimaryMetrics().getLeastCommonMechanism());

		// Primary Metrics
		metrics[1] = generateRow(classNames, calc.getPrimaryMetrics().getAverageNumberOfAncestors(),
				"Average Number of Ancestors");
		metrics[2] = generateRow(classNames, calc.getPrimaryMetrics().getFailSafeDefaults(), "Fail Safe Defaults");
		metrics[3] = generateRow(classNames, calc.getPrimaryMetrics().getReduceAttackSurface(),
				"Reduce Attack Surface");
		metrics[4] = generateRow(classNames, calc.getPrimaryMetrics().getLackOfCohesionOfMethods(),
				"Lack of Cohesion Methods");
		metrics[5] = generateRow(classNames, calc.getPrimaryMetrics().getCohesionAmongMethodsInClass(),
				"Cohesion Among Methods in Class");
		metrics[6] = generateRow(classNames, calc.getPrimaryMetrics().getCommentRatio(), "Comment Ratio");
		metrics[7] = generateRow(classes, countofBaseClasses, "Count of Base Classes");
		metrics[8] = generateRow(classNames, calc.getPrimaryMetrics().getCyclomaticComplexity(),
				"Cyclomatic Complexity");
		metrics[9] = generateRow(classNames, calc.getPrimaryMetrics().getDepthOfInheritanceTree(),
				"Depth of Inheritance");
		metrics[10] = generateRow(classNames, calc.getPrimaryMetrics().getCountPath(), "Count Path");
		metrics[11] = generateRow(classes, economyofMechanism, "Economy of Mechanism");
		metrics[12] = generateRow(classNames, calc.getPrimaryMetrics().getMcCabesCyclomaticComplexity(),
				"McCabes Cyclomatic Complexity");
		metrics[13] = generateRow(classNames, calc.getPrimaryMetrics().getModifiedCyclomaticComplexity(),
				"Modified Cyclomatic Complexity");
		// metrics[14]=
		// generateRow(classNames,calc.getPrimaryMetrics().getNestingComplexity(),"Nesting
		// Complexity");
		metrics[14] = generateRow(classNames, calc.getPrimaryMetrics().getNumberOfChildren(), "Number of Children");
		metrics[15] = generateRow(classNames, calc.getPrimaryMetrics().getNumberOfMethods(), "number of Methods");
		metrics[16] = generateRow(classNames, calc.getPrimaryMetrics().getStrictCyclomaticComplexity(),
				"Strict Cyclomatic Complexity");
		metrics[17] = generateRow(classes, secureWeakestLink, "Secure Weakest Link");
		metrics[18] = generateRow(classes, linesOfCode, "Lines of Code");
		metrics[19] = generateRow(classNames, calc.getPrimaryMetrics().getWeightedMethodsPerClass(),
				"Weighted Methods per Class");
		metrics[20] = generateRow(classNames, calc.getPrimaryMetrics().getMeasureOfAggregation(),
				"Measure of Aggregation");
		//metrics[21] = generateRow(classNames, baseClass, "Count of Base Classes");
		metrics[21] = generateRow(classNames, calc.getPrimaryMetrics().getCouplingBetweenObjects(),
				"Coupling Between Objects");
		metrics[22] = generateRow(classNames, calc.getPrimaryMetrics().getCouplingCorruptionPropagation(),
				"Coupling Corruption Propagation");
		metrics[23] = generateRow(classNames, calc.getPrimaryMetrics().getDepthOfInheritanceTree(),
				"Depth of Inheritance Tree");
		metrics[24] = generateRow(classNames, calc.getPrimaryMetrics().getDirectClassCoupling(),
				"Direct Class Coupling");
		metrics[25] = generateRow(classNames, calc.getPrimaryMetrics().getFanIn(), "Fan in");
		metrics[26] = generateRow(classNames, calc.getPrimaryMetrics().getFanOut(), "Fan out");
		metrics[27] = generateRow(classNames, calc.getPrimaryMetrics().getHenryKafura(), "Henry Kafura");
		metrics[28] = generateRow(classNames, calc.getPrimaryMetrics().getNumberOfChildren(), "Number of Children");
		metrics[29] = generateRow(classNames, calc.getPrimaryMetrics().getResponseSetForAClass(),
				"Response Set for a Class");
		metrics[30] = generateRow(classes, designSize, "Design Size in Classes");
		metrics[31] = generateRow(classes, stallRatio, "Stall Ratio");
		metrics[32] = generateRow(classNames, calc.getPrimaryMetrics().getCriticalElementRatio(),
				"Critical Element Ratio");
		metrics[33] = generateRow(classNames, calc.getPrimaryMetrics().getDataAccessMetric(), "Data Access Metric");
		metrics[34] = generateRow(classNames, calc.getPrimaryMetrics().getGrantLeastPrivelage(),
				"Grant Least Privilage");
		metrics[35] = generateRow(classes, isolation, "Isolation");
		metrics[36] = generateRow(classes, mechanism, "least Common Mechanism");
		metrics[37] = generateRow(classes, heirarchies, "Number of Hierarchies");
		metrics[38] = generateRow(classNames, calc.getPrimaryMetrics().getMeasureOfFunctionalAbtraction(),
				"Measure of Functional Abstraction");
		metrics[39] = generateRow(classNames, calc.getPrimaryMetrics().getClassInterfaceSize(), "Class Interface Size");
		metrics[40] = generateRow(classNames, calc.getPrimaryMetrics().getNumberOfPolymorphicMethods(),
				"number of PolyMorphic Methods");
		metrics[41] = generateRow(classNames, calc.getSecondaryMetrics().getReadabilityOfClassifiedAttributes(),
				"readability of Classified attributes");
		metrics[42] = generateRow(classNames, calc.getSecondaryMetrics().getReadabilityOfClassifiedMethods(),
				"Readability of Classified Methods");
		metrics[43] = generateRow(classes, sercurityAbsoulte, "Security Absolute Measurements");
		metrics[44] = generateRow(classNames, calc.getSecondaryMetrics().getWritabilityOfClassifiedAttributes(),
				"writability of Classified Attributes");
		metrics[45] = generateRow(classNames, calc.getSecondaryMetrics().getWritabilityOfClassifiedMethods(),
				"Writability of Classified Methods");
		metrics[46] = generateRow(classNames, calc.getSecondaryMetrics().getWritabilityOfClassifiedClasses(),
				"Writablility of Classified Classes");
		metrics[47] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedInstanceDataAccessibility(),
				"Classified Instance Data Accessibility");
		metrics[48] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedClassDataAccessibility(),
				"Classified Class Data Accessibility");
		metrics[49] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedAttributesInheritance(),
				"Classified Attributes Inheritance");
		metrics[50] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedOperationAccessibility(),
				"Classified Operation Accessibility");
		metrics[51] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedMethodsExtensibility(),
				"Classified Methods Extensibility");
		metrics[52] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedMethodsInheritance(),
				"Classified Methods Inheritance");
		metrics[53] = generateRow(classes, classifiedAttricbute, "Classified Attributes Total");
		metrics[54] = generateRow(classes, classifiedMethods, "Classified Methods Total");
		metrics[55] = generateRow(classes, classifiedClasses, "Critical Classes Total");
		metrics[56] = generateRow(classNames, calc.getTertiaryMetrics().getUnaccessedAssignedClassifiedAttribute(),
				"Unaccessed Assigned Classified Attribute");
		metrics[57] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedAccessorAttributeInteractions(),
				"Classified Accessor Attribute Interactions");
		metrics[58] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedMutatorAttributeInteractions(),
				"Classified Mutator Attribute Interactions");
		metrics[59] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedAttributesInteractionWeight(),
				"Classified Attributes Interaction Weight");
		metrics[60] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedMethodsWeight(),
				"Classified Methods Weight");
		metrics[61] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedWritingMethodsProportion(),
				"Classified Writing Methods Proportion");
		metrics[62] = generateRow(classNames, calc.getTertiaryMetrics().getUncalledClassifiedAccessorMethod(),
				"Uncalled Classified Accessor Methods");
		metrics[63] = generateRow(classes, criticalClassCoupling, "Critical Class Coupling");
		metrics[64] = generateRow(classes, seialized, "Critical Class Serialization");
		metrics[65] = generateRow(classes, superClass, "Critical Superclasses Inheritance");
		metrics[66] = generateRow(classes, accesserClass, "Unused Critical AccessorClass");
		metrics[67] = generateRow(classes, reflection, "Reflection Package Boolean");
		metrics[68] = generateRow(classes, partCriticalClasses, "Composite Part Critical Classes");
		metrics[69] = generateRow(classes, criticalClassExtensibility, "Critical Classes Extensibility");
		metrics[70] = generateRow(classes, criticalDesignProportion, "Critical Design Proportion");
		metrics[71] = generateRow(classes, criticalSuperClassProportion, "Critical Superclasses Proportion");

		section += makeTable(metrics);

		return section;
	}

}
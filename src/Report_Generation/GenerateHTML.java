
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
	private final int CircleMax = 5000;
	private final int CircleMin = -5000;
	// MAIN METHOD

	public GenerateHTML(Calculator c) {
		// TODO access list of metrics
		System.out.println(System.getProperty("user.dir"));
		System.out.println("============================================");
		System.out.println("============================================");
		this.calc = c;
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		flex = String.valueOf(normalizeCircle(
				(int) Math.round(generateAverage(classNames, calc.getQualityAttributes().getFlexibility()))));
		read = String.valueOf(normalizeCircle(
				(int) Math.round(generateAverage(classNames, calc.getQualityAttributes().getUnderstandability()))));
		reuse = String.valueOf(normalizeCircle(
				(int) Math.round(generateAverage(classNames, calc.getQualityAttributes().getReusability()))));
		effect = String.valueOf(normalizeCircle(
				(int) Math.round(generateAverage(classNames, calc.getQualityAttributes().getEffectiveness()))));
		extend = String.valueOf(normalizeCircle(
				(int) Math.round(generateAverage(classNames, calc.getQualityAttributes().getExtendability()))));
		function = String.valueOf(normalizeCircle(
				(int) Math.round(generateAverage(classNames, calc.getQualityAttributes().getFunctionality()))));
		overall = String
				.valueOf(
						normalizeCircle((int) (Math
								.round(generateAverage(classNames, calc.getQualityAttributes().getFlexibility())
										+ generateAverage(classNames,
												calc.getQualityAttributes().getUnderstandability())
										+ generateAverage(classNames, calc.getQualityAttributes().getReusability())
										+ generateAverage(classNames, calc.getQualityAttributes().getEffectiveness())
										+ generateAverage(classNames, calc.getQualityAttributes().getExtendability())
										+ generateAverage(classNames, calc.getQualityAttributes().getFunctionality()))
								/ 6)));
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
		String[][] overallTable = new String[7][7];
		String overTip = "Overall Score";
		String section = makeCircle("Overall Score", overall, "c100 big orange overall besidesOverall p" + overall,
				"overall1", overTip);
		overallTable[0] = new String[] { "Metric", "Average", "Standard Deviation", "Highest Value", "Lowest Value",
				"Count", "Info" };
		String effectString = "Effectiveness is about achieving the desired functionality. This includes performance and resource usage and leads to higher productivity, and makes understanding of the software a challenge";
		overallTable[1] = generateRow(classNames, calc.getQualityAttributes().getEffectiveness(), "Effectiveness",effectString);
		String flexString = "Flexibility is the adaptability of the program. Flexible program allow architecture to evolve organically, shows good software development, and results in a complex architecture. \r\n";
		overallTable[2] = generateRow(classNames, calc.getQualityAttributes().getFlexibility(), "Flexability",flexString);
		String funcString = "\r\n"
				+ "Functionality is about the behaviour between the inputs and outputs in the program. It also considers the performance during program execution in relation to the fault tolerance and recoverability. \r\n";
		overallTable[3] = generateRow(classNames, calc.getQualityAttributes().getFunctionality(), "Functionality",funcString);
		String extendString = "Extendability is the effort needed to make modifications to the project. This includes its stability, testability, and analyzability.";
		overallTable[4] = generateRow(classNames, calc.getQualityAttributes().getExtendability(), "Extendability",extendString);
		String reuseString = "Reusability is how often the code can be reapplied to a new problem. The adaptability or the conformance the code gives the program the ability to be transferred into another environment.";
		overallTable[5] = generateRow(classNames, calc.getQualityAttributes().getReusability(), "Reusability",reuseString);
		String readString = "Understandability is the ability to understand the complexity of the code and be able to trace/reproduce it.";
		overallTable[6] = generateRow(classNames, calc.getQualityAttributes().getUnderstandability(), "Readability",readString);
		section += makeTable(overallTable);
		section += "</div>\r\n" + "</div>\r\n" + "</div>";
		return section;
	}

	public String generateEffectiveness() {
		String effectTip = "Effectiveness is about achieving the desired functionality. This includes performance and resource usage and leads to higher productivity, and makes the understanding of the software a challenge.";
		String section = makeCircle("Effectiveness", effect, "c100 big maroon effectiveness besides p" + effect,
				"effectiveness1", effectTip);
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] effectiveness = new String[6][7];
		effectiveness[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Highest Value", "Lowest Value",
				"Count", "Info" };
		String astractString = "Measures the generalization and specialization within a design. Classes that have one or more descendants utilize abstraction";
		effectiveness[1] = generateRow(classNames, calc.getDesignPrincipals().getAbstraction(), "Abstraction",astractString);
		String encapString = "The enclosing of data and behaviour within a single construct. In object-oriented designs the property specifically refers to designing classes that prevent access to attribute declarations by defining them to be private, protecting the internal representation of the objects.";
		effectiveness[2] = generateRow(classNames, calc.getDesignPrincipals().getEncapsulation(), "Encapsulation",encapString);
		String compString = "Measures the “part-of”, “has”, “consists-of”, or “part-whole” relationships, which are aggregation relationships in an object-oriented design";
		effectiveness[3] = generateRow(classNames, calc.getDesignPrincipals().getComposition(), "Composition",compString);
		String inherString = "Measures the “is-a” relationship between classes. This relationship is related to the level of nesting of classes in an inheritance hierarch";
		effectiveness[4] = generateRow(classNames, calc.getDesignPrincipals().getInheritance(), "Inheritance",inherString);
		String polyString = "The ability to substitute objects whose interfaces match for one another at run-time It measures the services that are dynamically determined at run-time in an object.";
		effectiveness[5] = generateRow(classNames, calc.getDesignPrincipals().getPolymorphism(), "Polymorphism",polyString);
		section += makeTable(effectiveness);
		return section;
	}

	public String generateReusability() {
		String reuseTip = "Reusability is how often the code can be reapplied to a new problem. The adaptability or the conformance the code gives the program the ability to be transferred into another environment.";
		String section = makeCircle("Reusability", reuse, "c100 big green reusability besides p" + reuse,
				"reusability1", reuseTip);
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] reusability = new String[5][7];
		reusability[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Highest Value", "Lowest Value",
				"Count", "Info" };
		String coupleString = "Defines the interdependency of an object on other objects in a design. It is a measure of the number of other objects that would have to be accessed by an object in order for that object to function correctly";
		reusability[1] = generateRow(classNames, calc.getDesignPrincipals().getCoupling(), "Coupling",coupleString);
		String cohesionString = "Measures the “part-of”, “has”, “consists-of”, or “part-whole” relationships, which are aggregation relationships in an object-oriented design";
		reusability[2] = generateRow(classNames, calc.getDesignPrincipals().getCohesion(), "Cohesion",cohesionString);
		String messageString = "A count of the number of public methods that are available as services to other classes. This is a measure of the services that a class provides.";
		reusability[3] = generateRow(classNames, calc.getDesignPrincipals().getMessaging(), "Messaging",messageString);
		String designString = "Measures the number of classes used in a design";
		reusability[4] = generateRow(classNames, calc.getDesignPrincipals().getDesignSize(), "Design Size",designString);
		section += makeTable(reusability);
		return section;
	}

	public String generateUnderstandability() {
		String readTip = "Readability is the ease of which the software is read and understood. Simplicity in the code structure leads to a higher score.";
		String section = makeCircle("Readability", read, "c100 big pink readability besides p" + read, "readability1",
				readTip);
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] reusability = new String[8][7];
		reusability[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Highest Value", "Lowest Value",
				"Count", "Info" };
		String absractString = "Measures the generalization and specialization within a design. Classes that have one or more descendants utilize abstraction";
		reusability[1] = generateRow(classNames, calc.getDesignPrincipals().getAbstraction(), "Abstraction",absractString);
		String encapString = "The enclosing of data and behaviour within a single construct. In object-oriented designs the property specifically refers to designing classes that prevent access to attribute declarations by defining them to be private, protecting the internal representation of the objects";
		reusability[2] = generateRow(classNames, calc.getDesignPrincipals().getEncapsulation(), "Encapsulation",encapString);
		String coupString = "Defines the interdependency of an object on other objects in a design. It is a measure of the number of other objects that would have to be accessed by an object in order for that object to function correctly";
		reusability[3] = generateRow(classNames, calc.getDesignPrincipals().getCoupling(), "Coupling",coupString);
		String cohesionString = "Measures the relatedness of methods and attributes within a class. Large amounts of overlap in method parameters and attribute types is an indication of cohesion";
		reusability[4] = generateRow(classNames, calc.getDesignPrincipals().getCohesion(), "Cohesion",cohesionString);
		String polyString ="The ability to substitute objects whose interfaces match for one another at run-time It measures the services that are dynamically determined at run-time in an object.";
		reusability[5] = generateRow(classNames, calc.getDesignPrincipals().getPolymorphism(), "Polymorphism",polyString);
		String complexString = "Measures the degree of difficulty in understanding and comprehending the internal and external structure of classes and their relationships";
		reusability[6] = generateRow(classNames, calc.getDesignPrincipals().getComplexity(), "Complexity",complexString);
		String designString = "Measures the number of classes used in a design";
		reusability[7] = generateRow(classNames, calc.getDesignPrincipals().getDesignSize(), "Design Size",designString);
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
		String[][] reusability = new String[5][7];
		reusability[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Highest Value", "Lowest Value",
				"Count", "Info" };
		String encapString = "The enclosing of data and behaviour within a single construct. In object-oriented designs the property specifically refers to designing classes that prevent access to attribute declarations by defining them to be private, protecting the internal representation of the objects";
		reusability[1] = generateRow(classNames, calc.getDesignPrincipals().getEncapsulation(), "Encapsulation",encapString);
		String couplingString ="Defines the interdependency of an object on other objects in a design. It is a measure of the number of other objects that would have to be accessed by an object in order for that object to function correctly";
		reusability[2] = generateRow(classNames, calc.getDesignPrincipals().getCoupling(), "Coupling",couplingString);
		String composString = "Measures the “part-of”, “has”, “consists-of”, or “part-whole” relationships, which are aggregation relationships in an object-oriented design";
		reusability[3] = generateRow(classNames, calc.getDesignPrincipals().getComposition(), "Composition",composString);
		String polyString = "The ability to substitute objects whose interfaces match for one another at run-time It measures the services that are dynamically determined at run-time in an object.";
		reusability[4] = generateRow(classNames, calc.getDesignPrincipals().getPolymorphism(), "Polymorphism",polyString);
		section += makeTable(reusability);
		return section;
	}

	public String generateFunctionality() {
		String functTip = "Functionality is about the behaviour between the inputs and outputs in the program. It also considers the performance during program execution in relation to the fault tolerance and recoverability.";
		String section = makeCircle("Functionality", function, "c100 big skyblue functionality besides p" + function,
				"functionality1", functTip);
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] reusability = new String[6][7];
		reusability[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Highest Value", "Lowest Value",
				"Count", "Info" };
		String cohesionString = "Measures the relatedness of methods and attributes within a class. Large amounts of overlap in method parameters and attribute types is an indication of cohesion.";
		reusability[1] = generateRow(classNames, calc.getDesignPrincipals().getCohesion(), "Cohesion",cohesionString);
		String polyString = "The ability to substitute objects whose interfaces match for one another at run-time It measures the services that are dynamically determined at run-time in an object.";
		reusability[2] = generateRow(classNames, calc.getDesignPrincipals().getPolymorphism(), "Polymorphism",polyString);
		String messageString = "A count of the number of public methods that are available as services to other classes. This is a measure of the services that a class provides.";
		reusability[3] = generateRow(classNames, calc.getDesignPrincipals().getMessaging(), "Messaging",messageString);
		String designString = "Measures the number of classes used in a design";
		reusability[4] = generateRow(classNames, calc.getDesignPrincipals().getDesignSize(), "Design Size",designString);
		String hierString = "Hierarchies are used to represent different generalization-specialization concepts in a design. It is a count of the number of non-inherited classes that have children in a design";
		reusability[5] = generateRow(classNames, calc.getDesignPrincipals().getHierarchies(), "Hierarchies",hierString);
		section += makeTable(reusability);
		return section;
	}

	public String generateExtendability() {
		String extendTip = "Extendability is the effort needed to make modifications to the project. This includes its stability, testability, and analyzability.";

		String section = makeCircle("Extendability", extend, "c100 big blue extendability besides p" + extend,
				"extendability1", extendTip);
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] reusability = new String[6][7];
		reusability[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Highest Value", "Lowest Value",
				"Count", "Info" };
		String abstractString = "Measures the generalization and specialization within a design. Classes that have one or more descendants utilize abstraction";
		reusability[1] = generateRow(classNames, calc.getDesignPrincipals().getAbstraction(), "Abstraction",abstractString);
		String couplingString = "Defines the interdependency of an object on other objects in a design. It is a measure of the number of other objects that would have to be accessed by an object in order for that object to function correctly";
		reusability[2] = generateRow(classNames, calc.getDesignPrincipals().getCoupling(), "Coupling",couplingString);
		String messageString = "A count of the number of public methods that are available as services to other classes. This is a measure of the services that a class provides.";
		reusability[3] = generateRow(classNames, calc.getDesignPrincipals().getMessaging(), "Messaging",messageString);
		String designString = "Measures the number of classes used in a design";
		reusability[4] = generateRow(classNames, calc.getDesignPrincipals().getDesignSize(), "Design Size",designString);
		String hierString = "Hierarchies are used to represent different generalization-specialization concepts in a design. It is a count of the number of non-inherited classes that have children in a design";
		reusability[5] = generateRow(classNames, calc.getDesignPrincipals().getHierarchies(), "Hierarchies",hierString);
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
		String navBar = "<nav class=\"menu\">"
				+ "<button  id=\"menuButton\" class=\"navButton\" onclick=\"expandButton()\">Open Menu</button>"
				+ "<ul id=\"navBar\" class=\"lineBar\">"
				+ "<li class=\"\"><a href =\"#flexabilityData\" class =\"link\">Flexability</a></li>"
				+ "<li class=\"\"><a href =\"#readabilityData\" class =\"link\">Readability</a></li>"
				+ "<li class=\"\"><a href =\"#reusabilityData\" class =\"link\">Reusability</a></li>"
				+ "<li class=\"\"><a href =\"#effectivenessData\" class =\"link\">Effectiveness</a></li>"
				+ "<li class=\"\"><a href =\"#extendabilityData\" class =\"link\">Extendability</a></li>"
				+ "<li class=\"\"><a href =\"#functionalityData\" class =\"link\">Functionality<a /></li>"
				+ "<li class=\"\"><a href =\"#overallscoreData\" class =\"link\">Overall Score</a></li>" + "</ul>"
				+ "</nav>";
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
		script += "<script>function expandButton(){\r\n"
				+ "	if(document.getElementById(\"navBar\").getAttribute(\"class\")==\"lineBar\"){\r\n"
				+ "		document.getElementById(\"navBar\").setAttribute(\"class\",\"lineBarOpen\");\r\n"
				+ "	}else if(document.getElementById(\"navBar\").getAttribute(\"class\")==\"lineBarOpen\"){\r\n"
				+ "		document.getElementById(\"navBar\").setAttribute(\"class\",\"lineBar\");\r\n"
				+ "	}		\r\n" + "}</script>";
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

	public String[] generateRow(Set<String> classNames, HashMap<String, Double> results, String metric,
			String tooltip) {
		String[] row = new String[7];
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
		row[6] = "<div class=\"tooltip\"><img src=\"https://it-solutions4you.com/wp-content/uploads/2020/07/Tooltip_Manager_PNG_270x170icon.png\" width=\"50\" height=\"50\">\r\n"
				+ "  <span class=\"tooltiptext\">" + tooltip + "</span>\r\n" + "</div>";
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
			return Math.sqrt(standardDeviation);
		}
		return 0;
	}
	// DESIGN QUALITY METRICS

	public String generateAbstraction() {
		String section = "";
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] abstraction = new String[4][7];
		abstraction[0] = new String[] { "Metric", "Average", "Standard Deviation", "Highest Value", "Lowest Value",
				"Count", "Info" };
		String ancestors ="Determines the average number of ancestors for all classes. This signifies the average number of classes which the class inherits information from.";
		abstraction[1] = generateRow(classNames, calc.getPrimaryMetrics().getAverageNumberOfAncestors(),
				"Average Number of Ancestors",ancestors);
		String failSafe = "Signifies the capability of single classes with regards to readability of critical data.";
		abstraction[2] = generateRow(classNames, calc.getPrimaryMetrics().getFailSafeDefaults(), "Fail-Safe Defaults",failSafe);
		String attackSurface = "Signifies a program that has minimal readable classified attributes, classified methods, and critical classes";
		abstraction[3] = generateRow(classNames, calc.getPrimaryMetrics().getReduceAttackSurface(),
				"Reduce Attack Surface",attackSurface);
		section += makeTable(abstraction);
		return section;
	}

	public String generateCohesion() {
		String section = "";
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] cohesion = new String[3][7];
		cohesion[0] = new String[] { "Metric", "Average", "Standard Deviation", "Highest Value", "Lowest Value",
				"Count", "Info" };
		String cohesionIC = "Signifies the relatedness among methods of a class based upon the parameter list of the methods";
		cohesion[1] = generateRow(classNames, calc.getPrimaryMetrics().getCohesionAmongMethodsInClass(),
				"Cohesion Among Methods in a Class",cohesionIC);
		String loc = "Measures the lack of cohesion in methods. Since cohesion is negatively correlated to vulnerabilities, LCOM measures the weakness of said vulnerabilities.";
		cohesion[2] = generateRow(classNames, calc.getPrimaryMetrics().getLackOfCohesionOfMethods(),
				"Lack of Cohesion of Methods",loc);
		section += makeTable(cohesion);
		return section;
	}

	public String generateCoupling() {
		String section = "";
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();

		Set<String> classes = new HashSet<String>();
		classes.add("project");

		String[][] coupling = new String[11][7];
		coupling[0] = new String[] { "Metric", "Average", "Standard Deviation", "Highest Value", "Lowest Value",
				"Count", "Info" };
		HashMap<String, Double> baseClasses = new HashMap<String, Double>();
		baseClasses.put("project", calc.getPrimaryMetrics().getCountOfBaseClasses());
		String CBCString = "Counts the number of base classes in a project. This measures inheritance complexity as well as coupling.";
		coupling[1] = generateRow(classes, baseClasses, "Count of Base Classes",CBCString);
		String couplingObjects = "Counts the number of other classes coupled to the class for this metric";
		coupling[2] = generateRow(classNames, calc.getPrimaryMetrics().getCouplingBetweenObjects(),
				"Coupling Between Objects",couplingObjects);
		String ccpString = "Measures how two methods or more methods are reliant on each other.";
		coupling[3] = generateRow(classNames, calc.getPrimaryMetrics().getCouplingCorruptionPropagation(),
				"Coupling Corruption Propagation",ccpString);
		String doiString = "Measures the maximum depth of a class in the inheritance tree. The deeper the class is in the inheritance hierarchy, the greater the number of methods it is likely to inherit, the greater inter-class coupling due to inheritance.";
		coupling[4] = generateRow(classNames, calc.getPrimaryMetrics().getDepthOfInheritanceTree(),
				"Depth of Inheritance Tree",doiString);
		String ddcString = "Counts the number of classes that a class is directly related to. This can include attribute declarations and message passing (parameters) in methods.";
		coupling[5] = generateRow(classNames, calc.getPrimaryMetrics().getDirectClassCoupling(),
				"Direct Class Coupling",ddcString);
		String fi = "Counts the number of inputs a function uses, including parameters and global variables that are used in the function. Used in the Henry Kafura metric to calculate information flow complexity.";
		coupling[6] = generateRow(classNames, calc.getPrimaryMetrics().getFanIn(), "Fan In",fi);
		String fo = "Counts the number of outputs that are set by a function. Used in the Henry Kafura metric to calculate information flow complexity.";
		coupling[7] = generateRow(classNames, calc.getPrimaryMetrics().getFanOut(), "Fan Out",fo);
		String hkString = "Signifies the information flow complexity of the program. It is used to identify critical programs for information flow.";
		coupling[8] = generateRow(classNames, calc.getPrimaryMetrics().getHenryKafura(), "Henry Kafura",hkString);
		String nocString = "Counts the number of immediate subclasses  of a class. NOC measures inheritance coupling.";
		coupling[9] = generateRow(classNames, calc.getPrimaryMetrics().getNumberOfChildren(), "Number of Children",nocString);
		String responseSet = "Counts the number of methods in a set, including inherited methods. This counts the number of classes that an object can communicate with. The higher value of this metric, the greater the inter-class coupling.";
		coupling[10] = generateRow(classNames, calc.getPrimaryMetrics().getResponseSetForAClass(),"Responses set for a Class",responseSet);
		section += makeTable(coupling);
		return section;
	}

	public String generateDesignSize() {
		Set<String> classes = new HashSet<String>();
		classes.add("project");
		String section = "";
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] coupling = new String[2][7];
		coupling[0] = new String[] { "Metric", "Average", "Standard Deviation", "Highest Value", "Lowest Value",
				"Count", "Info" };
		HashMap<String, Double> stallRatio = new HashMap<String, Double>();
		stallRatio.put("project", calc.getPrimaryMetrics().getStallRatio());

		HashMap<String, Double> designSize = new HashMap<String, Double>();
		designSize.put("project", calc.getPrimaryMetrics().getDesignSizeInClasses());
		String dsic = "This metric is a count of the total number of classes in the design. This is the base metric for Design Size. ";
		coupling[1] = generateRow(classes, designSize, "Design Size in Classes",dsic);

		section += makeTable(coupling);
		return section;
	}

	public String generateEncapsulation() {
		String section = "";
		Set<String> classes = new HashSet<String>();
		classes.add("project");

		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] coupling = new String[7][7];
		coupling[0] = new String[] { "Metric", "Average", "Standard Deviation", "Highest Value", "Lowest Value",
				"Count", "Info" };

		HashMap<String, Double> heirarchies = new HashMap<String, Double>();
		heirarchies.put("project", calc.getPrimaryMetrics().getNumberOfHierarchies());

		HashMap<String, Double> isolation = new HashMap<String, Double>();
		isolation.put("project", calc.getPrimaryMetrics().getIsolation());

		HashMap<String, Double> mechanism = new HashMap<String, Double>();
		mechanism.put("project", calc.getPrimaryMetrics().getLeastCommonMechanism());
		String cer = "Measures the ratio of critical data elements in an object to the total number of elements in that object. The more critical element in the class, the higher the ratio and the higher the security risk. If essential data objects are changed that may destabilize the process as a whole.";
		coupling[1] = generateRow(classNames, calc.getPrimaryMetrics().getCriticalElementRatio(),
				"Critical Element Ratio",cer);
		String dam = "Measures the ratio of the number of private or protected attributes to the total number of attributes declared in the class. A high value for DAM is desired. (Range 0 to 1)";
		coupling[2] = generateRow(classNames, calc.getPrimaryMetrics().getDataAccessMetric(), "Data Access Metric",dam);
		String glp = "Measures the number of critical data that is writable from attributes and writable by the number of classes and methods. This calculates interactions among privileged users, with minimal scores making the program more secure.";
		coupling[3] = generateRow(classNames, calc.getPrimaryMetrics().getGrantLeastPrivelage(),
				"Grant Least Privilege",glp);
		String iso = "Measures the writability of critical classes. This minimizes the amount of potential damage to a system via enforcement of security privileges.";
		coupling[4] = generateRow(classes, isolation, "Isolation",iso);
		String lcm = "Measures the object-oriented program for the readability and writability of critical classes. This indicates whether critical data can be transmitted to unauthorized parties through shared resources";
		coupling[5] = generateRow(classes, mechanism, "Least Common Mechanism",lcm);
		String noh = "Counts the number of class hierarchies in the design";
		coupling[6] = generateRow(classes, heirarchies, "Number of Hierarchies",noh);

		section += makeTable(coupling);
		return section;
	}

	public String generateInheritance() {
		String section = "";
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] inheritance = new String[2][7];
		inheritance[0] = new String[] { "Metric", "Average", "Standard Deviation", "Highest Value", "Lowest Value",
				"Count", "Info" };
		String mofa = "Measures the ratio of the number of methods inherited by a class to the total number of methods accessible by member methods of the class";
		inheritance[1] = generateRow(classNames, calc.getPrimaryMetrics().getMeasureOfFunctionalAbtraction(),
				"Measure of Functional Abstraction",mofa);
		section += makeTable(inheritance);

		return section;
	}

	public String generateMessaging() {
		String section = "";
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] inheritance = new String[2][7];
		inheritance[0] = new String[] { "Metric", "Average", "Standard Deviation", "Highest Value", "Lowest Value",
				"Count", "Info" };
		String mofa = "Measures the ratio of the number of methods inherited by a class to the total number of methods accessible by member methods of the class";
		inheritance[1] = generateRow(classNames, calc.getPrimaryMetrics().getClassInterfaceSize(),
				"Class Interface Size",mofa);
		section += makeTable(inheritance);

		return section;
	}

	public String generatePolymorphism() {
		String section = "";
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] inheritance = new String[2][7];
		inheritance[0] = new String[] { "Metric", "Average", "Standard Deviation", "Highest Value", "Lowest Value",
				"Count", "Info" };
		String poly = "Counts the number of methods in a class.";
		inheritance[1] = generateRow(classNames, calc.getPrimaryMetrics().getNumberOfPolymorphicMethods(),"Number of Polymorphic Methods",poly);
		section += makeTable(inheritance);

		return section;
	}

	public String generateComplexity() {
		String section = "";
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] inheritance = new String[15][7];
		inheritance[0] = new String[] { "Metric", "Average", "Standard Deviation", "Highest Value", "Lowest Value",
				"Count", "Info" };

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
		String comment = "The ratio between the number of lines of comments to the number of lines of code in a project.";
		inheritance[1] = generateRow(classNames, calc.getPrimaryMetrics().getCommentRatio(), "Comment Ratio",comment);
		String cbc = "Counts the number of base classes in a project. This measures inheritance complexity as well as coupling.";
		inheritance[2] = generateRow(classes, countofBase, "Count of Base Classes",cbc);
		String Cyclomatic = "Measure of the number of linearly independent paths through a program’s source code.";
		inheritance[3] = generateRow(classNames, calc.getPrimaryMetrics().getCyclomaticComplexity(),
				"Cyclomatic Complexity",Cyclomatic);
		String doih = "Measures the maximum depth of a class in the inheritance tree. The deeper the class is in the inheritance hierarchy, the greater the number of methods it is likely to inherit, making it more complex to predict its behavior.";
		inheritance[4] = generateRow(classNames, calc.getPrimaryMetrics().getDepthOfInheritanceTree(),
				"Depth of Inheritance",doih);
		String path = "Counts the number of unique decision paths throughout a body of code. The more decision paths there are, the higher value this metric produces correlating to a more complex code structure.";
		inheritance[5] = generateRow(classNames, calc.getPrimaryMetrics().getCountPath(), "Count Path",path);
		String mechanism = "Signifies a minimal amount of classified attributed, classified methods, and critical classes in a project. This correlates to a less complex project, resulting in a reduced risk of design and implementation errors which make the system or parts of it more vulnerable.";
		inheritance[6] = generateRow(classes, economyOfMechanism, "Economy of Mechanism",mechanism);	
		inheritance[7] = generateRow(classNames, calc.getPrimaryMetrics().getMcCabesCyclomaticComplexity(),
				"Mcabes Cyclomatic Complexity",Cyclomatic);
		String modified = "Uses Cyclomatic Complexity but each use case is not counted, and switch cases are counted as 1. The higher this metric value, the more likely an entity is to be difficult to test and maintain without error.";
		inheritance[8] = generateRow(classNames, calc.getPrimaryMetrics().getModifiedCyclomaticComplexity(),
				"Modified Cyclomatic Complexity",modified);
		// inheritance[9] =
		// generateRow(classNames,calc.getPrimaryMetrics().getNestingComplexity(),"Nesting
		// Complexity");
		String children = "Counts the number of immediate subclasses  of a class. NOC measures inheritance complexity";
		inheritance[9] = generateRow(classNames, calc.getPrimaryMetrics().getNumberOfChildren(), "Number of Children",children);
		String noMethods = "Counts the number of methods defined in a class. Is used as a measure of class complexity in the Weighted Methods Per Class (WMC) metric."; 
		inheritance[10] = generateRow(classNames, calc.getPrimaryMetrics().getNumberOfMethods(), "Number of Methods",noMethods);
		String strictCyclo = "Uses Cyclomatic Complexity but logical AND and OR operators are also counted for";
		inheritance[11] = generateRow(classNames, calc.getPrimaryMetrics().getStrictCyclomaticComplexity(),
				"Strict Cyclomatic Complexity",strictCyclo);
		String weakLink = "Measures the program for the number of writable classified attributes and methods. With lesser functionality, a program has less security exposure. ";
		inheritance[12] = generateRow(classes, weakestLink, "Secure Weakest Link",weakLink);
		String loc = "Counts the number of executable lines of source code. Although SLOC measures size, research shows that it has a high correlation to code complexity. ";
		inheritance[13] = generateRow(classes, linesOfCode, "Lines of Code",loc);
		String wmpc = "Counts the number of local methods defined in the class. WMC is related to size complexity. Chidamber et al. empirically validated that the number of methods and complexity of the methods involved is an indicator of development and maintainability complexity";
		inheritance[14] = generateRow(classNames, calc.getPrimaryMetrics().getWeightedMethodsPerClass(),
				"Weighted Methods per Class",wmpc);

		section += makeTable(inheritance);

		return section;
	}

	public String generateComposition() {
		String section = "";
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] inheritance = new String[2][7];
		inheritance[0] = new String[] { "Metric", "Average", "Standard Deviation", "Highest Value", "Lowest Value",
				"Count", "Info" };
		String aggergate = "Count of the number of data declarations that are user defined classes. This measures the extent of the class in a part-whole relationship in respect to their attributes.";
		inheritance[1] = generateRow(classNames, calc.getPrimaryMetrics().getMeasureOfAggregation(),
				"Measure of Functional Abstraction",aggergate);
		section += makeTable(inheritance);

		return section;
	}

	public String generateAllMetricsBreakdown() {
		String section = "";
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] metrics = new String[72][7];
		metrics[0] = new String[] { "Metric", "Average", "Standard Deviation", "Highest Value", "Lowest Value", "Count",
				"Info" };

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
		String ana = "Determines the average number of ancestors for all classes. This signifies the average number of classes which the class inherits information from.";
		metrics[1] = generateRow(classNames, calc.getPrimaryMetrics().getAverageNumberOfAncestors(),
				"Average Number of Ancestors",ana);
		String fsd = "Signifies the capability of single classes with regards to readability of critical data.";
		metrics[2] = generateRow(classNames, calc.getPrimaryMetrics().getFailSafeDefaults(), "Fail Safe Defaults",fsd);
		String surface = "Signifies a program that has minimal readable classified attributes, classified methods, and critical classes";
		metrics[3] = generateRow(classNames, calc.getPrimaryMetrics().getReduceAttackSurface(),
				"Reduce Attack Surface",surface);
		String locm = "Measures the lack of cohesion in methods. Since cohesion is negatively correlated to vulnerabilities, LCOM measures the weakness of said vulnerabilities.";
		metrics[4] = generateRow(classNames, calc.getPrimaryMetrics().getLackOfCohesionOfMethods(),
				"Lack of Cohesion Methods",locm);
		String camc ="Signifies the relatedness among methods of a class based upon the parameter list of the methods";
		metrics[5] = generateRow(classNames, calc.getPrimaryMetrics().getCohesionAmongMethodsInClass(),
				"Cohesion Among Methods in Class",camc);
		String comRation = "The ratio between the number of lines of comments to the number of lines of code in a project.";
		metrics[6] = generateRow(classNames, calc.getPrimaryMetrics().getCommentRatio(), "Comment Ratio",comRation);
		String cobc = "Counts the number of base classes in a project. This measures inheritance complexity as well as coupling";
		metrics[7] = generateRow(classes, countofBaseClasses, "Count of Base Classes",cobc);
		String cycloComplex = "Measure of the number of linearly independent paths through a program’s source code.";
		metrics[8] = generateRow(classNames, calc.getPrimaryMetrics().getCyclomaticComplexity(),
				"Cyclomatic Complexity",cycloComplex);
		String doInTree = "Measures the maximum depth of a class in the inheritance tree. The deeper the class is in the inheritance hierarchy, the greater the number of methods it is likely to inherit, the greater inter-class coupling due to inheritance";
		metrics[9] = generateRow(classNames, calc.getPrimaryMetrics().getDepthOfInheritanceTree(),
				"Depth of Inheritance",doInTree);
		String cp = "Counts the number of unique decision paths throughout a body of code. The more decision paths there are, the higher value this metric produces correlating to a more complex code structure.";
		metrics[10] = generateRow(classNames, calc.getPrimaryMetrics().getCountPath(), "Count Path",cp);
		String ecom = "Signifies a minimal amount of classified attributed, classified methods, and critical classes in a project. This correlates to a less complex project, resulting in a reduced risk of design and implementation errors which make the system or parts of it more vulnerable.";
		metrics[11] = generateRow(classes, economyofMechanism, "Economy of Mechanism",ecom);
		String mcCabes = "Measure of the number of linearly independent paths through a program’s source code.";
		metrics[12] = generateRow(classNames, calc.getPrimaryMetrics().getMcCabesCyclomaticComplexity(),
				"McCabes Cyclomatic Complexity",mcCabes);
		String modified = "Uses Cyclomatic Complexity but each use case is not counted, and switch cases are counted as 1. The higher this metric value, the more likely an entity is to be difficult to test and maintain without error.";
		metrics[13] = generateRow(classNames, calc.getPrimaryMetrics().getModifiedCyclomaticComplexity(),
				"Modified Cyclomatic Complexity",modified);
		// metrics[14]=
		// generateRow(classNames,calc.getPrimaryMetrics().getNestingComplexity(),"Nesting
		// Complexity");
		String numberChild = "Counts the number of immediate subclasses  of a class. NOC measures inheritance complexity.";
		metrics[14] = generateRow(classNames, calc.getPrimaryMetrics().getNumberOfChildren(), "Number of Children",numberChild);
		String numberMethods = "Counts the number of methods defined in a class. Is used as a measure of class complexity in the Weighted Methods Per Class (WMC) metric.";
		metrics[15] = generateRow(classNames, calc.getPrimaryMetrics().getNumberOfMethods(), "number of Methods",numberMethods);
		String cyclomatic = "Measure of the number of linearly independent paths through a program’s source code.";
		metrics[16] = generateRow(classNames, calc.getPrimaryMetrics().getStrictCyclomaticComplexity(),
				"Strict Cyclomatic Complexity",cyclomatic);
		String secureLink="Measures the program for the number of writable classified attributes and methods. With lesser functionality, a program has less security exposure";
		metrics[17] = generateRow(classes, secureWeakestLink, "Secure Weakest Link",secureLink);
		String linesCode = "Counts the number of executable lines of source code. Although SLOC measures size, research shows that it has a high correlation to code complexity.";
		metrics[18] = generateRow(classes, linesOfCode, "Lines of Code",linesCode);
		String weightedMethods = "Counts the number of local methods defined in the class. WMC is related to size complexity. Chidamber et al. empirically validated that the number of methods and complexity of the methods involved is an indicator of development and maintainability complexity";
		metrics[19] = generateRow(classNames, calc.getPrimaryMetrics().getWeightedMethodsPerClass(),
				"Weighted Methods per Class",weightedMethods);
		String aggregation = "Count of the number of data declarations that are user defined classes. This measures the extent of the class in a part-whole relationship in respect to their attributes.";
		metrics[20] = generateRow(classNames, calc.getPrimaryMetrics().getMeasureOfAggregation(),
				"Measure of Aggregation",aggregation);
		// metrics[21] = generateRow(classNames, baseClass, "Count of Base Classes");
		String couplingObjects = "Counts the number of other classes coupled to the class for this metric.";
		metrics[21] = generateRow(classNames, calc.getPrimaryMetrics().getCouplingBetweenObjects(),
				"Coupling Between Objects",couplingObjects);
		String couplingProp = "Measures how two methods or more methods are reliant on each other.";
		metrics[22] = generateRow(classNames, calc.getPrimaryMetrics().getCouplingCorruptionPropagation(),
				"Coupling Corruption Propagation",couplingProp);
		String depthInheritance = "Measures the maximum depth of a class in the inheritance tree. The deeper the class is in the inheritance hierarchy, the greater the number of methods it is likely to inherit, the greater inter-class coupling due to inheritance.";
		metrics[23] = generateRow(classNames, calc.getPrimaryMetrics().getDepthOfInheritanceTree(),
				"Depth of Inheritance Tree",depthInheritance);
		String dcc = "Counts the number of classes that a class is directly related to. This can include attribute declarations and message passing (parameters) in methods.";
		metrics[24] = generateRow(classNames, calc.getPrimaryMetrics().getDirectClassCoupling(),
				"Direct Class Coupling",dcc);
		String fi = "Counts the number of inputs a function uses, including parameters and global variables that are used in the function. Used in the Henry Kafura metric to calculate information flow complexity.";
		metrics[25] = generateRow(classNames, calc.getPrimaryMetrics().getFanIn(), "Fan in",fi);
		String fo = "Counts the number of outputs that are set by a function. Used in the Henry Kafura metric to calculate information flow complexity.";
		metrics[26] = generateRow(classNames, calc.getPrimaryMetrics().getFanOut(), "Fan out",fo);
		String henry = "Signifies the information flow complexity of the program. It is used to identify critical programs for information flow.";
		metrics[27] = generateRow(classNames, calc.getPrimaryMetrics().getHenryKafura(), "Henry Kafura",henry);
		String childrenNumber = "Counts the number of immediate subclasses  of a class. NOC measures inheritance coupling";
		metrics[28] = generateRow(classNames, calc.getPrimaryMetrics().getNumberOfChildren(), "Number of Children",childrenNumber);
		String responseSet = "Counts the number of methods in a set, including inherited methods. This counts the number of classes that an object can communicate with. The higher value of this metric, the greater the inter-class coupling.";
		metrics[29] = generateRow(classNames, calc.getPrimaryMetrics().getResponseSetForAClass(),
				"Response Set for a Class",responseSet);
		String desSize = "This metric is a count of the total number of classes in the design. This is the base metric for Design Size.";
		metrics[30] = generateRow(classes, designSize, "Design Size in Classes",desSize);
		String cer = "Measures the ratio of critical data elements in an object to the total number of elements in that object. The more critical element in the class, the higher the ratio and the higher the security risk. If essential data objects are changed that may destabilize the process as a whole.";
		metrics[32] = generateRow(classNames, calc.getPrimaryMetrics().getCriticalElementRatio(),
				"Critical Element Ratio",cer);
		String dam = "Measures the ratio of the number of private or protected attributes to the total number of attributes declared in the class. A high value for DAM is desired. (Range 0 to 1)";
		metrics[33] = generateRow(classNames, calc.getPrimaryMetrics().getDataAccessMetric(), "Data Access Metric",dam);
		String glp = "Measures the number of critical data that is writable from attributes and writable by the number of classes and methods. This calculates interactions among privileged users, with minimal scores making the program more secure.";
		metrics[34] = generateRow(classNames, calc.getPrimaryMetrics().getGrantLeastPrivelage(),
				"Grant Least Privilage",glp);
		String iso = "Measures the writability of critical classes. This minimizes the amount of potential damage to a system via enforcement of security privileges.";
		metrics[35] = generateRow(classes, isolation, "Isolation",iso);
		String mech = "Measures the object-oriented program for the readability and writability of critical classes. This indicates whether critical data can be transmitted to unauthorized parties through shared resources.";
		metrics[36] = generateRow(classes, mechanism, "least Common Mechanism",mech);
		String hier = "Counts the number of class hierarchies in the design";
		metrics[37] = generateRow(classes, heirarchies, "Number of Hierarchies",hier);
		String functAbstract = "Measures the ratio of the number of methods inherited by a class to the total number of methods accessible by member methods of the class ";
		metrics[38] = generateRow(classNames, calc.getPrimaryMetrics().getMeasureOfFunctionalAbtraction(),
				"Measure of Functional Abstraction",functAbstract);
		String classInterface = "Counts the number of public methods in a class.";
		metrics[39] = generateRow(classNames, calc.getPrimaryMetrics().getClassInterfaceSize(), "Class Interface Size",classInterface);
		String polyMeth = "Counts the number of methods in a class.";
		metrics[40] = generateRow(classNames, calc.getPrimaryMetrics().getNumberOfPolymorphicMethods(),
				"number of PolyMorphic Methods",polyMeth);
		String roca = "This metric defines how easy it is to to read classified attributes. A high score means that classified data is easily accessed";
		metrics[41] = generateRow(classNames, calc.getSecondaryMetrics().getReadabilityOfClassifiedAttributes(),
				"readability of Classified attributes",roca);
		String readclassMeth = "How easily classified attributes are accessed by a classes accessor methods";
		metrics[42] = generateRow(classNames, calc.getSecondaryMetrics().getReadabilityOfClassifiedMethods(),
				"Readability of Classified Methods",readclassMeth);
		String secureAbsolute = "total amount of classified attributes, methods and classes";
		metrics[43] = generateRow(classes, sercurityAbsoulte, "Security Absolute Measurements",secureAbsolute);
		String writabilityClassAttr = "How easily a classified attribute is modified";
		metrics[44] = generateRow(classNames, calc.getSecondaryMetrics().getWritabilityOfClassifiedAttributes(),
				"writability of Classified Attributes",writabilityClassAttr);
		String writeClassMeth = "How easily an attributes is modified via attribute mutators";
		metrics[45] = generateRow(classNames, calc.getSecondaryMetrics().getWritabilityOfClassifiedMethods(),
				"Writability of Classified Methods",writeClassMeth);
		String classClass = "How easily attributes in classified classes are changes";
		metrics[46] = generateRow(classNames, calc.getSecondaryMetrics().getWritabilityOfClassifiedClasses(),
				"Writablility of Classified Classes",classClass);
		String classDataAcces ="The ratio of the number of non-private classified instance attributes to the number of classified\r\n"
				+ "attributes in the program";
		metrics[47] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedInstanceDataAccessibility(),
				"Classified Instance Data Accessibility",classDataAcces);
		String ccda = "The ratio of the number of non-private classified class attributes to the number of classified attributes\r\n"
				+ "in the program";
		metrics[48] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedClassDataAccessibility(),
				"Classified Class Data Accessibility",ccda);
		String cai = "The ratio of the number of classified attributes which can be inherited in a hierarchy to the total\r\n"
				+ "number of classified attributes in the program’s inheritance hierarchy.";
		metrics[49] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedAttributesInheritance(),
				"Classified Attributes Inheritance",cai);
		String coa = "The ratio of the number of non-private classified methods to the number of classified methods in the\r\n"
				+ "program";
		metrics[50] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedOperationAccessibility(),
				"Classified Operation Accessibility",coa);
		String cme = "The ratio of the number of the non-finalised classified methods in program to the total number of\r\n"
				+ "classified methods in the program.";
		metrics[51] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedMethodsExtensibility(),
				"Classified Methods Extensibility",cme);
		String cmi = "The ratio of the number of classified methods which can be inherited in a hierarchy to the total\r\n"
				+ "number of classified methods in the program’s inheritance hierarchy.";
		metrics[52] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedMethodsInheritance(),
				"Classified Methods Inheritance",cmi);
		String cat = "The total number of classified attributes in the program.";
		metrics[53] = generateRow(classes, classifiedAttricbute, "Classified Attributes Total",cat);
		String cmt = "The total number of classified methods in the program.";
		metrics[54] = generateRow(classes, classifiedMethods, "Classified Methods Total",cmt);
		String cct = "The total number of critical classes in the program";
		metrics[55] = generateRow(classes, classifiedClasses, "Critical Classes Total",cct);
		String uaca = "The ratio of the number of classified attributes that are assigned but never used to the total number of\r\n"
				+ "classified attributes in the program.";
		metrics[56] = generateRow(classNames, calc.getTertiaryMetrics().getUnaccessedAssignedClassifiedAttribute(),
				"Unaccessed Assigned Classified Attribute",uaca);
		String caai = "The ratio of the sum of all interactions between accessors and classified attributes to the possible\r\n"
				+ "maximum number of interactions between accessors and classified attributes in the program.";
		metrics[57] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedAccessorAttributeInteractions(),
				"Classified Accessor Attribute Interactions",caai);
		String cmai = "The ratio of the sum of all interactions between mutators and classified attributes to the possible\r\n"
				+ "maximum number of interactions between mutators and classified attributes in the program.";
		metrics[58] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedMutatorAttributeInteractions(),
				"Classified Mutator Attribute Interactions",cmai);
		String caiw = "The ratio of the number of all interactions with classified attributes to the total number of all\r\n"
				+ "interactions with all attributes in the program.";
		metrics[59] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedAttributesInteractionWeight(),
				"Classified Attributes Interaction Weight",caiw);
		String cmw = "The ratio of the number of classified methods to the total number of methods in the program";
		metrics[60] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedMethodsWeight(),
				"Classified Methods Weight",cmw);
		String cwmp = "The ratio of the number of methods which write classified attributes to the total number of classified\r\n"
				+ "methods in the program.";
		metrics[61] = generateRow(classNames, calc.getTertiaryMetrics().getClassifiedWritingMethodsProportion(),
				"Classified Writing Methods Proportion",cwmp);
		String ucam = "The ratio of the number of classes which contain classified methods that access classified attributes\r\n"
				+ "but are never used by other classes to the total number of critical classes in the program.";
		metrics[62] = generateRow(classNames, calc.getTertiaryMetrics().getUncalledClassifiedAccessorMethod(),
				"Uncalled Classified Accessor Methods",ucam);
		String ccc = "The ratio of the number of all classes’ links with classified attributes to the total number of possible\r\n"
				+ "links with classified attributes in the program.";
		metrics[63] = generateRow(classes, criticalClassCoupling, "Critical Class Coupling",ccc);
		String ccs = "The ratio of the number of critical serialized classes to the total number of critical classes in the\r\n"
				+ "program.";
		metrics[64] = generateRow(classes, seialized, "Critical Class Serialization",ccs);
		String csi = "The ratio of the sum of classes which may inherit from each critical superclass to the number of\r\n"
				+ "possible inheritances from all critical classes in the program’s inheritance hierarchy.";
		metrics[65] = generateRow(classes, superClass, "Critical Superclasses Inheritance",csi);
		String ucac = "The ratio of the number of classes which contain classified methods that access classified attributes\r\n"
				+ "but are never used by other classes to the total number of critical classes in the program.";
		metrics[66] = generateRow(classes, accesserClass, "Unused Critical Accessor Class",ucac);
		String rpf = "A boolean value representing whether the Java program imports the reflection package (1) or not (0).";
		metrics[67] = generateRow(classes, reflection, "Reflection Package Boolean",rpf);
		String cpcc = "The ratio of the number of critical composed-part classes to the total number of critical classes in the\r\n"
				+ "program.";
		metrics[68] = generateRow(classes, partCriticalClasses, "Composite Part Critical Classes",cpcc);
		String cce = "The ratio of the number of the non-finalised critical classes in program to the total number of critical\r\n"
				+ "classes in the program.\r\n";
		metrics[69] = generateRow(classes, criticalClassExtensibility, "Critical Classes Extensibility",cce);
		String cdp = "The ratio of the number of critical classes to the total number of classes in the program.";
		metrics[70] = generateRow(classes, criticalDesignProportion, "Critical Design Proportion",cdp);
		String csp = "The ratio of the number of critical superclasses to the total number of critical classes in the\r\n"
				+ "program’s inheritance hierarchy.";
		metrics[71] = generateRow(classes, criticalSuperClassProportion, "Critical Superclasses Proportion",csp);

		section += makeTable(metrics);

		return section;
	}

	private int normalizeCircle(int value) {
		if (value > CircleMax) {
			return 100;
		} else if (value < CircleMin) {
			return 0;
		} else {
			double v1 = (value - CircleMin);
			double v2 = (v1) / (CircleMax - CircleMin);
			double v3 = v2 * 100;
			return (int) v3;
		}
	}
}
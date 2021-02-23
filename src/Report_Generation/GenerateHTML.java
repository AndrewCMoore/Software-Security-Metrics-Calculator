package Report_Generation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import Calculator.Calculator;

public class GenerateHTML {

	private final String FILENAME = "../../index";
	private final String type = ".html";
	private File htmlFile;
	private String fileName;
	private Calculator calc;
	private String flex;
	String read;
	String reuse;
	String effect;
	String extend;
	String function;
	String overall;

	public GenerateHTML(Calculator c) {
		// TODO access list of metrics
		this.calc = c;
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		 flex = String.valueOf(generateAverage(classNames, calc.getQualityAttributes().getFlexibility()));
		 read = String.valueOf(generateAverage(classNames, calc.getQualityAttributes().getUnderstandability()));
		 reuse = String.valueOf(generateAverage(classNames, calc.getQualityAttributes().getReusability()));
		 effect = String.valueOf(generateAverage(classNames, calc.getQualityAttributes().getEffectiveness()));
		 extend = String.valueOf(generateAverage(classNames, calc.getQualityAttributes().getExtendability()));
		 function = String.valueOf(generateAverage(classNames, calc.getQualityAttributes().getFunctionality()));
		 overall = String.valueOf((generateAverage(classNames, calc.getQualityAttributes().getFlexibility())
				+ generateAverage(classNames, calc.getQualityAttributes().getUnderstandability())
				+ generateAverage(classNames, calc.getQualityAttributes().getReusability())
				+ generateAverage(classNames, calc.getQualityAttributes().getEffectiveness())
				+ generateAverage(classNames, calc.getQualityAttributes().getExtendability())
				+ generateAverage(classNames, calc.getQualityAttributes().getFunctionality())) / 6);
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
			writer.append(makeJavaScriptSection());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String generateOverAllScore() {
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] overallTable = new String[7][6];
		String section = makeCircle("Overall Score", overall, "c100 bigger orange overall besidesOverall p"+overall, "overall1");
		overallTable[0] = new String[] { "Metric", "Average", "Standerd Deviation", "Higeset Value", "Lowest Value",
				"Count" };
		overallTable[1] = generateRow(classNames, calc.getQualityAttributes().getEffectiveness(), "Effectiveness");
		overallTable[2] = generateRow(classNames, calc.getQualityAttributes().getFlexibility(), "Flexability");
		overallTable[3] = generateRow(classNames, calc.getQualityAttributes().getFunctionality(), "Functionality");
		overallTable[4] = generateRow(classNames, calc.getQualityAttributes().getExtendability(), "Extendability");
		overallTable[6] = generateRow(classNames, calc.getQualityAttributes().getReusability(), "Reusability");
		overallTable[6] = generateRow(classNames, calc.getQualityAttributes().getUnderstandability(), "Readability");
		section += makeTable(overallTable);
		section += "</div>\r\n" + "</div>\r\n" + "</div>";
		return section;
	}

	public String generateEffectiveness() {
		String section = makeCircle("Effectiveness", effect, "c100 big maroon effectiveness besides p"+effect, "effectiveness1");
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
		String section = makeCircle("Reusability", reuse, "c100 big green reusability besides p"+reuse, "reusability1");
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
		String section = makeCircle("Readability", read, "c100 big pink readability besides p"+read, "readability1");
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
		reusability[8] = generateRow(classNames, calc.getDesignPrincipals().getDesignSize(), "Design Size");
		section += makeTable(reusability);
		return section;
	}

	public String generateFlexability() {
		String section = makeCircle("Flexability", flex, "c100 big flexability besides p"+flex, "readability1");
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
		String section = makeCircle("Functionality", function, "c100 big skyblue functionality besides p"+function, "functionality1");
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] reusability = new String[5][6];
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
		String section = makeCircle("Extendability", extend, "c100 big blue extendability besides p"+extend, "extendability1");
		Set<String> classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		String[][] reusability = new String[5][6];
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

	public String makeHead() {
		String head = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"utf-8\">\r\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "<title>Prototype</title>\r\n" + "<link rel=\"stylesheet\" href=\"circle.css\">\r\n"
				+ "<link rel=\"stylesheet\" href=\"style.css\">\r\n" + "</head>";
		return head;
	}

	public String makeNavBar() {
		String navBar = "<nav class=\"navbar\">" + "<button class=\"navButton\" onclick=\"expand()\">Open Menu</button>"
				+ "<ul id=\"navBar\" class=\"line collapse\">" + "<li class=\"lineItem\"><a>Flexability</a></li>"
				+ "<li class=\"lineItem\"><a>Readability</a></li>" + "<li class=\"lineItem\"><a>Reusability</a></li>"
				+ "<li class=\"lineItem\"><a>Effectiveness</a></li>"
				+ "<li class=\"lineItem\"><a>Extendability</a></li>"
				+ "<li class=\"lineItem\"><a>Functionality<a /></li>"
				+ "<li class=\"lineItem\"><a>Overall Score</a></li>" + "</ul>" + "</nav>";
		return navBar;
	}

	public String makeSplashPage() {

		String splash = "<div class=\"container\">\r\n" + "			<div class=\"row\">";



		splash += makeCircle("Flexability", flex, "c100 big flexability p" + flex, "flexability");
		splash += makeCircle("Reusability", read, "c100 big pink readability p" + read, "readability");
		splash += makeCircle("Readability", reuse, "c100 big pink readability p" + reuse, "reusability");
		splash += makeCircle("Effectiveness", effect, "c100 big maroon effectiveness p" + effect, "effectiveness");
		splash += makeCircle("Extendability", extend, "c100 big maroon effectiveness p" + extend, "extendability");
		splash += makeCircle("Functionality", function, "c100 big maroon effectiveness p" + function, "functionality");
		splash += makeCircle("Overall Score", overall, "c100 big maroon effectiveness p" + overall, "overall");
		splash +="</div>\r\n"
				+ "		</div>";
		return splash;
	}
	public String makeTableSection() {
		String section ="<div class=\"tables\">\r\n"
						+ "<div id=\"overallscoreData\">";
		section += makeCollapseSection("Overall Score",generateOverAllScore());
			section += "</div>\r\n"
					+ "			\r\n"
					+ "			<div id=\"flexabilityData\">";
		section += makeCollapseSection("Flexability" ,generateFlexability());
			section +="</div>\r\n"
					+ "			\r\n"
					+ "			<div id=\"readabilityData\">";
		section += makeCollapseSection("Functionality" ,generateFunctionality());
			section +="</div>\r\n"
					+ "			\r\n"
					+ "			<div id=\"functionalityData\">";
		section += makeCollapseSection("Extendability" ,generateExtendability());
			section +="</div>\r\n"
					+ "			\r\n"
					+ "			<div id=\"extendabilityData\">";
		section += makeCollapseSection("Effectiveness" ,generateEffectiveness());
			section +="</div>\r\n"
					+ "			\r\n"
					+ "			<div id=\"reusabilityData\">";
		section += makeCollapseSection("Reusability" ,generateReusability());
			section +="</div>";
		return section;
	}

	public String makeCircle(String name, String number, String cssClass, String id) {
		String c = "";
		c += "<div class=\"" + cssClass + "\"id=\"" + id + "\">";
		c += "<span><span>" + name + "</span> <br>";
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
			t += "<\tr>\n";
		}
		t += "</table>\n";
		return t;
	}

	public String makeJavaScriptSection() {
		String script = "<script>\r\n" + "			var flexEncap = [\"10\",\"10\",\"10\"];\r\n"
				+ "			var flexCouple = [\"20\",\"20\",\"20\"];\r\n"
				+ "			var flexCompo = [\"60\",\"60\",\"60\"];\r\n"
				+ "			var flexPoly = [\"50\",\"50\",\"50\"];\r\n"
				+ "			for(var fe = 0; fe < flexEncap.length; fe++)\r\n"
				+ "				document.getElementById(\"flexEncap\"+fe).innerHTML = flexEncap[fe];\r\n"
				+ "			for(var fcu = 0; fcu < flexCouple.length; fcu++)\r\n"
				+ "				document.getElementById(\"flexCouple\"+fcu).innerHTML = flexCouple[fcu];\r\n"
				+ "			for(var fc = 0; fc < flexCompo.length; fc++)\r\n"
				+ "				document.getElementById(\"flexCompo\"+fc).innerHTML = flexCompo[fc];\r\n"
				+ "			for(var fp = 0; fp < flexPoly.length; fp++)\r\n"
				+ "				document.getElementById(\"flexPoly\"+fp).innerHTML = flexPoly[fp];\r\n"
				+ "				\r\n" + "			var readAbstr = [\"10\",\"10\",\"10\"];\r\n"
				+ "			var readEncap = [\"10\",\"10\",\"10\"];\r\n"
				+ "			var readCouple = [\"20\",\"20\",\"20\"];\r\n"
				+ "			var readCohen = [\"60\",\"60\",\"60\"];\r\n"
				+ "			var readPoly = [\"50\",\"50\",\"50\"];\r\n"
				+ "			var readComp = [\"50\",\"50\",\"50\"];\r\n"
				+ "			var readDesign = [\"50\",\"50\",\"50\"];\r\n"
				+ "			for(var ra = 0; ra < readAbstr.length; ra++)\r\n"
				+ "				document.getElementById(\"readAbstr\"+ra).innerHTML = readAbstr[ra];\r\n"
				+ "			for(var re = 0; re < readEncap.length; re++)\r\n"
				+ "				document.getElementById(\"readEncap\"+re).innerHTML = readEncap[re];\r\n"
				+ "			for(var rc = 0; rc < readCouple.length; rc++)\r\n"
				+ "				document.getElementById(\"readCouple\"+rc).innerHTML = readCouple[rc];\r\n"
				+ "			for(var rch = 0; rch < readCohen.length; rch++)\r\n"
				+ "				document.getElementById(\"readCohen\"+rch).innerHTML = readCohen[rch];\r\n"
				+ "			for(var rp = 0; rp < readPoly.length; rp++)\r\n"
				+ "				document.getElementById(\"readPoly\"+rp).innerHTML = readPoly[rp];\r\n"
				+ "			for(var rcmp = 0; rcmp < readComp.length; rcmp++)\r\n"
				+ "				document.getElementById(\"readComp\"+rcmp).innerHTML = readComp[rcmp];\r\n"
				+ "			for(var rd = 0; rd < readDesign.length; rd++)\r\n"
				+ "				document.getElementById(\"readDesign\"+rd).innerHTML = readDesign[rd];\r\n"
				+ "			\r\n" + "			var funcCohen = [\"20\",\"20\",\"20\"];\r\n"
				+ "			var funcPoly = [\"50\",\"50\",\"50\"];\r\n"
				+ "			var funcMsg = [\"50\",\"50\",\"50\"];\r\n"
				+ "			var funcDesign = [\"50\",\"50\",\"50\"];\r\n"
				+ "			var funcHier = [\"50\",\"50\",\"50\"];			\r\n"
				+ "			for(var fc = 0; fc < funcCohen.length; fc++)\r\n"
				+ "				document.getElementById(\"funcCohen\"+fc).innerHTML = funcCohen[fc];\r\n"
				+ "			for(var fp = 0; fp < funcPoly.length; fp++)\r\n"
				+ "				document.getElementById(\"funcPoly\"+fp).innerHTML = funcPoly[fp];\r\n"
				+ "			for(var fm = 0; fm < funcMsg.length; fm++)\r\n"
				+ "				document.getElementById(\"funcMsg\"+fm).innerHTML = funcMsg[fm];\r\n"
				+ "			for(var fd = 0; fd < funcDesign.length; fd++)\r\n"
				+ "				document.getElementById(\"funcDesign\"+fd).innerHTML = funcDesign[fd];\r\n"
				+ "			for(var fh = 0; fh < funcHier.length; fh++)\r\n"
				+ "				document.getElementById(\"funcHier\"+fh).innerHTML = funcHier[fh];\r\n"
				+ "			\r\n" + "			var extendCohen = [\"20\",\"20\",\"20\"];\r\n"
				+ "			var extendPoly = [\"50\",\"50\",\"50\"];\r\n"
				+ "			var extendMsg = [\"50\",\"50\",\"50\"];\r\n"
				+ "			var extendDesign = [\"50\",\"50\",\"50\"];\r\n"
				+ "			var extendHier = [\"50\",\"50\",\"50\"];			\r\n"
				+ "			for(var ec = 0; ec < extendCohen.length; ec++)\r\n"
				+ "				document.getElementById(\"extendCohen\"+ec).innerHTML = extendCohen[ec];\r\n"
				+ "			for(var ep = 0; ep < extendPoly.length; ep++)\r\n"
				+ "				document.getElementById(\"extendPoly\"+ep).innerHTML = extendPoly[ep];\r\n"
				+ "			for(var em = 0; em < extendMsg.length; em++)\r\n"
				+ "				document.getElementById(\"extendMsg\"+em).innerHTML = extendMsg[em];\r\n"
				+ "			for(var ed = 0; ed < extendDesign.length; ed++)\r\n"
				+ "				document.getElementById(\"extendDesign\"+ed).innerHTML = extendDesign[ed];\r\n"
				+ "			for(var eh = 0; eh < extendHier.length; eh++)\r\n"
				+ "				document.getElementById(\"extendHier\"+eh).innerHTML = extendHier[eh];\r\n"
				+ "			\r\n" + "			var effectAbstr = [\"20\",\"20\",\"20\"];\r\n"
				+ "			var effectEncap = [\"50\",\"50\",\"50\"];\r\n"
				+ "			var effectCompo = [\"50\",\"50\",\"50\"];\r\n"
				+ "			var effectInherit = [\"50\",\"50\",\"50\"];\r\n"
				+ "			var effectPoly = [\"50\",\"50\",\"50\"];			\r\n"
				+ "			for(var ea = 0; ea < effectAbstr.length; ea++)\r\n"
				+ "				document.getElementById(\"effectAbstr\"+ea).innerHTML = effectAbstr[ea];\r\n"
				+ "			for(var ee = 0; ee < effectEncap.length; ee++)\r\n"
				+ "				document.getElementById(\"effectEncap\"+ee).innerHTML = effectEncap[ee];\r\n"
				+ "			for(var ec = 0; ec < effectCompo.length; ec++)\r\n"
				+ "				document.getElementById(\"effectCompo\"+ec).innerHTML = effectCompo[ec];\r\n"
				+ "			for(var ei = 0; ei < effectInherit.length; ei++)\r\n"
				+ "				document.getElementById(\"effectInherit\"+ei).innerHTML = effectInherit[ei];\r\n"
				+ "			for(var ep = 0; ep < effectPoly.length; ep++)\r\n"
				+ "				document.getElementById(\"effectPoly\"+ep).innerHTML = effectPoly[ep];\r\n"
				+ "			\r\n" + "			var reuseCouple = [\"20\",\"20\",\"20\"];\r\n"
				+ "			var reuseCohen = [\"50\",\"50\",\"50\"];\r\n"
				+ "			var reuseMsg = [\"50\",\"50\",\"50\"];\r\n"
				+ "			var reuseDesign = [\"50\",\"50\",\"50\"];\r\n"
				+ "			for(var rcp = 0; rcp < reuseCouple.length; rcp++)\r\n"
				+ "				document.getElementById(\"reuseCouple\"+rcp).innerHTML = reuseCouple[rcp];\r\n"
				+ "			for(var rc = 0; rc < reuseCohen.length; rc++)\r\n"
				+ "				document.getElementById(\"reuseCohen\"+rc).innerHTML = reuseCohen[rc];\r\n"
				+ "			for(var rm = 0; rm < reuseMsg.length; rm++)\r\n"
				+ "				document.getElementById(\"reuseMsg\"+rm).innerHTML = reuseMsg[rm];\r\n"
				+ "			for(var rd = 0; rd < reuseDesign.length; rd++)\r\n"
				+ "				document.getElementById(\"reuseDesign\"+rd).innerHTML = reuseDesign[rd];\r\n"
				+ "			\r\n" + "			var overallFlex = [\"20\",\"20\",\"20\"];\r\n"
				+ "			var overallRead = [\"50\",\"50\",\"50\"];\r\n"
				+ "			var overallFunc = [\"50\",\"50\",\"50\"];\r\n"
				+ "			var overallExtend = [\"50\",\"50\",\"50\"];\r\n"
				+ "			var overallEffect = [\"50\",\"50\",\"50\"];\r\n"
				+ "			var overallReuse = [\"50\",\"50\",\"50\"];\r\n"
				+ "			for(var ofx = 0; ofx < overallFlex.length; ofx++)\r\n"
				+ "				document.getElementById(\"overallFlex\"+ofx).innerHTML = overallFlex[ofx];\r\n"
				+ "			for(var or = 0; or < overallRead.length; or++)\r\n"
				+ "				document.getElementById(\"overallRead\"+or).innerHTML = overallRead[or];\r\n"
				+ "			for(var of = 0; of < overallFunc.length; of++)\r\n"
				+ "				document.getElementById(\"overallFunc\"+of).innerHTML = overallFunc[of];\r\n"
				+ "			for(var oex = 0; oex < overallExtend.length; oex++)\r\n"
				+ "				document.getElementById(\"overallExtend\"+oex).innerHTML = overallExtend[oex];\r\n"
				+ "			for(var oe = 0; oe < overallEffect.length; oe++)\r\n"
				+ "				document.getElementById(\"overallEffect\"+oe).innerHTML = overallEffect[oe];\r\n"
				+ "			for(var oru = 0; oru < overallReuse.length; oru++)\r\n"
				+ "				document.getElementById(\"overallReuse\"+oru).innerHTML = overallReuse[oru];\r\n"
				+ "				\r\n"
				+ "			var flex = parseInt(document.getElementById(\"overallFlex0\").innerHTML);\r\n"
				+ "			var read = parseInt(document.getElementById(\"overallRead0\").innerHTML);\r\n"
				+ "			var funct = parseInt(document.getElementById(\"overallFunc0\").innerHTML);\r\n"
				+ "			var extend = parseInt(document.getElementById(\"overallExtend0\").innerHTML);\r\n"
				+ "			var effect = parseInt(document.getElementById(\"overallEffect0\").innerHTML);\r\n"
				+ "			var reuse = parseInt(document.getElementById(\"overallReuse0\").innerHTML);\r\n"
				+ "			var overall = (flex+read+funct+extend+effect+reuse)/6;\r\n"
				+ "			document.getElementById(\"flexability\").classList.add(\"p\"+flex);\r\n"
				+ "			document.getElementById(\"flexability1\").classList.add(\"p\"+flex);\r\n"
				+ "			document.getElementById(\"readability\").classList.add(\"p\"+read);\r\n"
				+ "			document.getElementById(\"readability1\").classList.add(\"p\"+read);\r\n"
				+ "			document.getElementById(\"functionality\").classList.add(\"p\"+funct);\r\n"
				+ "			document.getElementById(\"functionality1\").classList.add(\"p\"+funct);\r\n"
				+ "			document.getElementById(\"extendability\").classList.add(\"p\"+extend);\r\n"
				+ "			document.getElementById(\"extendability1\").classList.add(\"p\"+extend);\r\n"
				+ "			document.getElementById(\"effectiveness\").classList.add(\"p\"+effect);\r\n"
				+ "			document.getElementById(\"effectiveness1\").classList.add(\"p\"+effect);\r\n"
				+ "			document.getElementById(\"reusability\").classList.add(\"p\"+reuse);\r\n"
				+ "			document.getElementById(\"reusability1\").classList.add(\"p\"+reuse);\r\n"
				+ "			document.getElementById(\"overall\").classList.add(\"p\"+Math.ceil(overall));\r\n"
				+ "			document.getElementById(\"overall1\").classList.add(\"p\"+Math.ceil(overall));\r\n"
				+ "			\r\n" + "			var acc = document.getElementsByClassName(\"accordion\");\r\n"
				+ "			var i;\r\n" + "\r\n" + "			for (i = 0; i < acc.length; i++) {\r\n"
				+ "			  acc[i].addEventListener(\"click\", function() {\r\n"
				+ "				this.classList.toggle(\"active\");\r\n"
				+ "				var panel = this.nextElementSibling;\r\n"
				+ "				if (panel.style.maxHeight) {\r\n" + "				  panel.style.maxHeight = null;\r\n"
				+ "				} else {\r\n"
				+ "				  panel.style.maxHeight = panel.scrollHeight + \"px\";\r\n" + "				} \r\n"
				+ "			  });\r\n" + "			}\r\n" + "		</script>";

		return script;
	}

	public String[] generateRowString(Set<String> classNames, HashMap<String, Double> results, String metric) {
		String[] row = new String[6];
		row[0] = metric;
		int average = generateAverage(classNames, results);
		int standardDeviation = generateStanderdDeviation(classNames, results, average);
		int highest = getHigestValue(classNames, results);
		int lowest = getLowestValue(classNames, results);
		int count = getCount(classNames, results);
		row[1] = Integer.toString(average);
		row[2] = Integer.toString(standardDeviation);
		row[3] = Integer.toString(highest);
		row[4] = Integer.toString(lowest);
		row[5] = Integer.toString(count);
		return row;
	}

	public String[] generateRow(Set<String> classNames, HashMap<String, Double> results, String metric) {
		String[] row = new String[6];
		row[0] = metric;
		int average = generateAverage(classNames, results);
		int standardDeviation = generateStanderdDeviation(classNames, results, average);
		int highest = getHigestValue(classNames, results);
		int lowest = getLowestValue(classNames, results);
		int count = getCount(classNames, results);
		row[1] = Integer.toString(average);
		row[2] = Integer.toString(standardDeviation);
		row[3] = Integer.toString(highest);
		row[4] = Integer.toString(lowest);
		row[5] = Integer.toString(count);
		return row;
	}

	public int getHigestValue(Set<String> classNames, HashMap<String, Double> results) {
		int highest = Integer.MIN_VALUE;
		for (String key : classNames) {
			String value = String.valueOf(results.get(key));
			if (Integer.getInteger(value) != null) {
				if (highest < Integer.getInteger(value)) {
					highest = Integer.getInteger(value);
				}
			}
		}

		return highest;
	}

	public int getLowestValue(Set<String> classNames, HashMap<String, Double> results) {
		int lowest = Integer.MAX_VALUE;
		for (String key : classNames) {
			String value = String.valueOf(results.get(key));
			if (Integer.getInteger(value) != null) {
				if (lowest > Integer.getInteger(value)) {
					lowest = Integer.getInteger(value);
				}
			}
		}

		return lowest;
	}

	public int getCount(Set<String> classNames, HashMap<String, Double> results) {
		int size = 0;
		for (String key : classNames) {
			String value = String.valueOf(results.get(key));
			if (Integer.getInteger(value) != null) {
				size++;
			}
		}

		return size;
	}

	public int generateAverage(Set<String> classNames, HashMap<String, Double> results) {
		int average = 0;
		int size = 0;
		for (String key : classNames) {
			String value = String.valueOf(results.get(key));
			if (Integer.getInteger(value) != null) {
				size++;
				average += Integer.getInteger(value);
			}
		}
		average /= size;
		return average;
	}

	public int generateStanderdDeviation(Set<String> classNames, HashMap<String, Double> results, int average) {
		int standardDeviation = 0;
		int size = 0;
		for (String key : classNames) {
			String value = String.valueOf(results.get(key));
			if (Integer.getInteger(value) != null) {
				size++;
				standardDeviation += Math.pow((Integer.getInteger(value) - average), 2);
			}
		}
		standardDeviation /= size;
		return standardDeviation;
	}

}
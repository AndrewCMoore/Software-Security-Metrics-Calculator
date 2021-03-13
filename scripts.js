var flexEncap = ["10","10","10"];
var flexCouple = ["20","20","20"];
var flexCompo = ["60","60","60"];
var flexPoly = ["50","50","50"];
for(var fe = 0; fe < flexEncap.length; fe++)
	document.getElementById("flexEncap"+fe).innerHTML = flexEncap[fe];
for(var fcu = 0; fcu < flexCouple.length; fcu++)
	document.getElementById("flexCouple"+fcu).innerHTML = flexCouple[fcu];
for(var fc = 0; fc < flexCompo.length; fc++)
	document.getElementById("flexCompo"+fc).innerHTML = flexCompo[fc];
for(var fp = 0; fp < flexPoly.length; fp++)
	document.getElementById("flexPoly"+fp).innerHTML = flexPoly[fp];
	
var readAbstr = ["10","10","10"];
var readEncap = ["10","10","10"];
var readCouple = ["20","20","20"];
var readCohen = ["60","60","60"];
var readPoly = ["50","50","50"];
var readComp = ["50","50","50"];
var readDesign = ["50","50","50"];
for(var ra = 0; ra < readAbstr.length; ra++)
	document.getElementById("readAbstr"+ra).innerHTML = readAbstr[ra];
for(var re = 0; re < readEncap.length; re++)
	document.getElementById("readEncap"+re).innerHTML = readEncap[re];
for(var rc = 0; rc < readCouple.length; rc++)
	document.getElementById("readCouple"+rc).innerHTML = readCouple[rc];
for(var rch = 0; rch < readCohen.length; rch++)
	document.getElementById("readCohen"+rch).innerHTML = readCohen[rch];
for(var rp = 0; rp < readPoly.length; rp++)
	document.getElementById("readPoly"+rp).innerHTML = readPoly[rp];
for(var rcmp = 0; rcmp < readComp.length; rcmp++)
	document.getElementById("readComp"+rcmp).innerHTML = readComp[rcmp];
for(var rd = 0; rd < readDesign.length; rd++)
	document.getElementById("readDesign"+rd).innerHTML = readDesign[rd];

var funcCohen = ["20","20","20"];
var funcPoly = ["50","50","50"];
var funcMsg = ["50","50","50"];
var funcDesign = ["50","50","50"];
var funcHier = ["50","50","50"];			
for(var fc = 0; fc < funcCohen.length; fc++)
	document.getElementById("funcCohen"+fc).innerHTML = funcCohen[fc];
for(var fp = 0; fp < funcPoly.length; fp++)
	document.getElementById("funcPoly"+fp).innerHTML = funcPoly[fp];
for(var fm = 0; fm < funcMsg.length; fm++)
	document.getElementById("funcMsg"+fm).innerHTML = funcMsg[fm];
for(var fd = 0; fd < funcDesign.length; fd++)
	document.getElementById("funcDesign"+fd).innerHTML = funcDesign[fd];
for(var fh = 0; fh < funcHier.length; fh++)
	document.getElementById("funcHier"+fh).innerHTML = funcHier[fh];

var extendCohen = ["20","20","20"];
var extendPoly = ["50","50","50"];
var extendMsg = ["50","50","50"];
var extendDesign = ["50","50","50"];
var extendHier = ["50","50","50"];			
for(var ec = 0; ec < extendCohen.length; ec++)
	document.getElementById("extendCohen"+ec).innerHTML = extendCohen[ec];
for(var ep = 0; ep < extendPoly.length; ep++)
	document.getElementById("extendPoly"+ep).innerHTML = extendPoly[ep];
for(var em = 0; em < extendMsg.length; em++)
	document.getElementById("extendMsg"+em).innerHTML = extendMsg[em];
for(var ed = 0; ed < extendDesign.length; ed++)
	document.getElementById("extendDesign"+ed).innerHTML = extendDesign[ed];
for(var eh = 0; eh < extendHier.length; eh++)
	document.getElementById("extendHier"+eh).innerHTML = extendHier[eh];

var effectAbstr = ["20","20","20"];
var effectEncap = ["50","50","50"];
var effectCompo = ["50","50","50"];
var effectInherit = ["50","50","50"];
var effectPoly = ["50","50","50"];			
for(var ea = 0; ea < effectAbstr.length; ea++)
	document.getElementById("effectAbstr"+ea).innerHTML = effectAbstr[ea];
for(var ee = 0; ee < effectEncap.length; ee++)
	document.getElementById("effectEncap"+ee).innerHTML = effectEncap[ee];
for(var ec = 0; ec < effectCompo.length; ec++)
	document.getElementById("effectCompo"+ec).innerHTML = effectCompo[ec];
for(var ei = 0; ei < effectInherit.length; ei++)
	document.getElementById("effectInherit"+ei).innerHTML = effectInherit[ei];
for(var ep = 0; ep < effectPoly.length; ep++)
	document.getElementById("effectPoly"+ep).innerHTML = effectPoly[ep];

var reuseCouple = ["20","20","20"];
var reuseCohen = ["50","50","50"];
var reuseMsg = ["50","50","50"];
var reuseDesign = ["50","50","50"];
for(var rcp = 0; rcp < reuseCouple.length; rcp++)
	document.getElementById("reuseCouple"+rcp).innerHTML = reuseCouple[rcp];
for(var rc = 0; rc < reuseCohen.length; rc++)
	document.getElementById("reuseCohen"+rc).innerHTML = reuseCohen[rc];
for(var rm = 0; rm < reuseMsg.length; rm++)
	document.getElementById("reuseMsg"+rm).innerHTML = reuseMsg[rm];
for(var rd = 0; rd < reuseDesign.length; rd++)
	document.getElementById("reuseDesign"+rd).innerHTML = reuseDesign[rd];

var overallFlex = ["20","20","20"];
var overallRead = ["50","50","50"];
var overallFunc = ["50","50","50"];
var overallExtend = ["50","50","50"];
var overallEffect = ["50","50","50"];
var overallReuse = ["50","50","50"];
for(var ofx = 0; ofx < overallFlex.length; ofx++)
	document.getElementById("overallFlex"+ofx).innerHTML = overallFlex[ofx];
for(var or = 0; or < overallRead.length; or++)
	document.getElementById("overallRead"+or).innerHTML = overallRead[or];
for(var of = 0; of < overallFunc.length; of++)
	document.getElementById("overallFunc"+of).innerHTML = overallFunc[of];
for(var oex = 0; oex < overallExtend.length; oex++)
	document.getElementById("overallExtend"+oex).innerHTML = overallExtend[oex];
for(var oe = 0; oe < overallEffect.length; oe++)
	document.getElementById("overallEffect"+oe).innerHTML = overallEffect[oe];
for(var oru = 0; oru < overallReuse.length; oru++)
	document.getElementById("overallReuse"+oru).innerHTML = overallReuse[oru];
	
var flex = parseInt(document.getElementById("overallFlex0").innerHTML);
var read = parseInt(document.getElementById("overallRead0").innerHTML);
var funct = parseInt(document.getElementById("overallFunc0").innerHTML);
var extend = parseInt(document.getElementById("overallExtend0").innerHTML);
var effect = parseInt(document.getElementById("overallEffect0").innerHTML);
var reuse = parseInt(document.getElementById("overallReuse0").innerHTML);
var overall = (flex+read+funct+extend+effect+reuse)/6;
document.getElementById("flexability").classList.add("p"+flex);
document.getElementById("flexability1").classList.add("p"+flex);
document.getElementsByClassName("flex")[0].innerHTML = "<span>Flexability</span><br/><span class='number'>"+flex+"%</span>";
document.getElementById("readability").classList.add("p"+read);
document.getElementById("readability1").classList.add("p"+read);
document.getElementsByClassName("read")[0].innerHTML = "<span>Readability</span><br/><span class='number'>"+read+"%</span>";
document.getElementById("functionality").classList.add("p"+funct);
document.getElementById("functionality1").classList.add("p"+funct);
document.getElementsByClassName("funct")[0].innerHTML = "<span>Functionality</span><br/><span class='number'>"+funct+"%</span>";
document.getElementById("extendability").classList.add("p"+extend);
document.getElementById("extendability1").classList.add("p"+extend);
document.getElementsByClassName("extend")[0].innerHTML = "<span>Extendability</span><br/><span class='number'>"+extend+"%</span>";
document.getElementById("effectiveness").classList.add("p"+effect);
document.getElementById("effectiveness1").classList.add("p"+effect);
document.getElementsByClassName("effect")[0].innerHTML = "<span>Effectiveness</span><br/><span class='number'>"+effect+"%</span>";
document.getElementById("reusability").classList.add("p"+reuse);
document.getElementById("reusability1").classList.add("p"+reuse);
document.getElementsByClassName("reuse")[0].innerHTML = "<span>Reusability</span><br/><span class='number'>"+reuse+"%</span>";
document.getElementById("overall").classList.add("p"+Math.ceil(overall));
document.getElementById("overall1").classList.add("p"+Math.ceil(overall));
document.getElementsByClassName("overallscore")[0].innerHTML = "<span>Overall Score</span><br/><span class='number'>"+overall+"%</span>";

var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
  acc[i].addEventListener("click", function() {
	this.classList.toggle("active");
	var panel = this.nextElementSibling;
	if (panel.style.maxHeight) {
	  panel.style.maxHeight = null;
	} else {
	  panel.style.maxHeight = panel.scrollHeight + "px";
	} 
  });
}
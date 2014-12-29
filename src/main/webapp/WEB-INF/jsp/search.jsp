<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Movie Search : Predictive Key Search</title>
<link rel="stylesheet" type="text/css" href="/static/css/style.css" />
<script type="text/javascript" src="/static/js/clockp.js"></script>
<script type="text/javascript" src="/static/js/clockh.js"></script> 
<script type="text/javascript" src="/static/js/jquery.min.js"></script>
<script type="text/javascript" src="/static/js/ddaccordion.js"></script>
<script type="text/javascript">
ddaccordion.init({
	headerclass: "submenuheader", //Shared CSS class name of headers group
	contentclass: "submenu", //Shared CSS class name of contents group
	revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
	mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
	collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [], //index of content(s) open by default [index1, index2, etc] [] denotes no content
	onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
	animatedefault: false, //Should contents open by default be animated into view?
	persiststate: true, //persist state of opened contents within browser session?
	toggleclass: ["", ""], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["suffix", "<img src='images/plus.gif' class='statusicon' />", "<img src='images/minus.gif' class='statusicon' />"], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
	oninit:function(headers, expandedindices){ //custom code to run when headers have initalized
		//do nothing
	},
	onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
		//do nothing
	}
})
</script>

<script type="text/javascript" src="/static/js/jconfirmaction.jquery.js"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		$('.ask').jConfirmAction();
		
		$("#searchButton").live("click", function(e){
			
		});
		
	});
	
</script>

<script language="javascript" type="text/javascript" src="/static/js/niceforms.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="/static/css/niceforms-default.css" />

</head>
<body>
<div id="main_container">

	<div class="header">
    <div class="logo"><a href="#"><img src="" alt="" title="" border="0" /></a></div>
    
    <div class="right_header">Welcome To Predictive Key Search</div>
    <div id="clock_a"></div>
    </div>
    
    <div class="main_content">    	  
    		<div class="right_content" style="width: 600px; margin-left: 200px;">            
    			<div style="width: 600px">
    				<div style="width: 400px; display: inline-block;">
    					<input type="text" name="searchKey" id="seachbox" style="width: 380px; height: 20px; font-weight: bold;"/>
    				</div>
    				<div style="width: 100px; display: inline-block;">
    					<input type="button" value="Search" id="searchButton"/>
    				</div>
    			</div>    		
     		</div><!-- end of right content-->
     		   
    
    	<div class="clear"></div>
    	<div style="border: 1px solid; width: 900px;" id="serachResultSection">
    		<div style="margin-top: 50px; margin-left: 20px; font-weight: bold;">
    		Search result :
    		</div>
    		<div style="width: 900px; min-height:300px;" id="resultContent">
    			<div style="width: 500px; border: 1px solid; min-height:100px; margin: 0 auto; margin-top: 20px;">
    				<div style="height: 35px; width: 500px; background: #0092FA !important;">
    					<img src="/static/images/valid.png" style="width:20px; height: 20px; float: left; margin-left: 10px; margin-top: 10px;" />
    					<div style="display: inline-block; margin-left: 15px; margin-top: 10px; font-weight: bold;">
    						Garam Masala
    					</div>
    				</div>
    				<div style="width: 500px; margin-top: 20px;">
    					An engaged man (Akshay Kumar) runs into trouble when he tries to woo three beautiful air hostesses.
    				</div>
    			</div>
    			<div style="width: 500px; border: 1px solid; min-height:100px; margin: 0 auto; margin-top: 20px;">
    				<div style="height: 35px; width: 500px; background: #0092FA !important;">
    					<img src="/static/images/valid.png" style="width:20px; height: 20px; float: left; margin-left: 10px; margin-top: 10px;" />
    					<div style="display: inline-block; margin-left: 15px; margin-top: 10px; font-weight: bold;">
    						Garam Masala
    					</div>
    				</div>
    				<div style="width: 500px; margin-top: 20px;">
    					An engaged man (Akshay Kumar) runs into trouble when he tries to woo three beautiful air hostesses.
    				</div>
    			</div>
    		</div>
    	</div>
    </div> <!--end of main content-->
    
</div>		

<script type="text/javascript">


</script>
</body>
</html>
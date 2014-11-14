function legend(parent, data) {
	parent.className = 'legend';
	var datas = data.hasOwnProperty('datasets') ? data.datasets : data;

	// remove possible children of the parent
	while (parent.hasChildNodes()) {
		parent.removeChild(parent.lastChild);
	}
	
	datas.forEach(function(d) {
		var title = document.createElement('span');
		title.className = 'title';
		title.style.borderColor = d.hasOwnProperty('strokeColor') ? d.strokeColor : d.color;
		title.style.borderStyle = 'solid';
		parent.appendChild(title);
		var text = document.createTextNode(d.title);
		var input = document.createElement('input');
		input.id = d.title;
		input.setAttribute('type','checkbox');
		input.className = 'itemChart';
		//input.attributes['type'] = 'checkbox';
		title.appendChild(input);
		title.appendChild(text);
		
		
	});
}


$(document).ready(function() {
	$("span.title").mouseover(function() {
		
		var items = $(':checkbox.itemChart');
			var boolExistCheckedItem = false;
			for (var i = 0; i < items.length; i++) {

				if (items.get(i).checked) {
					boolExistCheckedItem = true;
					break;
				}
			};

			
			if (!boolExistCheckedItem) {
				var title_ = $(this).text();
				for (var i = 0; i < lineChartData.datasets.length; i++) {
					if (lineChartData.datasets[i].title == title_) {
						objectElmentChart = lineChartData.datasets[i];
		
					}
				}
				//set data of item selected
				newDataSets = [objectElmentChart];
		
				LineCartDataCostume = {
					labels : lineChartData.labels,
					datasets : newDataSets
				};
		
				
				initailisationCart(LineCartDataCostume);
			}
		
		
		
	});
		$("#chartLegend").mouseleave(function() {
			//initailisationCart(lineChartData);
			var items = $(':checkbox.itemChart');
			var boolExistCheckedItem = false;
			for (var i = 0; i < items.length; i++) {

				if (items.get(i).checked) {
					boolExistCheckedItem = true;
					break;
				}
			};
			console.log("We are in mouseleave");
			
			if (!boolExistCheckedItem) {
				console.log("We are in if");
				initailisationCart(lineChartData);
			};
		});
		
		$("span.title").click(function (){
			//$('input').checked = true;
			var items = $(':checkbox.itemChart');
			for (var i = 0; i < items.length; i++) {

				if (items.get(i).getAttribute("id") == $(this).text()) {
					if(items.get(i).checked)
					{
						items.get(i).checked = false;
					}else {
						items.get(i).checked = true;
					}
					break;
				}
			};
			
			
			
			initailisationCart(lineChartData);
			var items = $(':checkbox.itemChart');
			var boolExistCheckedItem = false;
			for (var i = 0; i < items.length; i++) {

				if (items.get(i).checked) {
					boolExistCheckedItem = true;
					break;
				}
			};

			
			if (!boolExistCheckedItem) {
				//console.log("We are in if");
				initailisationCart(lineChartData);
			} else {
				newDataSets = new Array();
				for (var i = 0; i < items.length; i++) {
					if (items.get(i).checked) {
						for (var j = 0; j < lineChartData.datasets.length; j++) {
							if (lineChartData.datasets[j].title == items.get(i).getAttribute("id")) {
								objectElmentChart = lineChartData.datasets[j];
								newDataSets[newDataSets.length] = objectElmentChart;
								break;
							}
						};
					}

				}

				LineCartDataCostume = {
					labels : lineChartData.labels,
					datasets : newDataSets
				};
				initailisationCart(LineCartDataCostume);
				//console.log("We are in else");
			};
	});
	
$("#resetChart").click(function (){
	initailisationCart(lineChartData);
	});
	
	});



//the hover chart function
function initailisationCart(chartData) {
	document.getElementById("chartDiv").innerHTML = "<canvas id='canvas' width='810' height='380'></canvas>";
	ctx = document.getElementById("canvas").getContext("2d");
	//ctx.restore();
	
	if(boolBreak)
		{
			if(varTypeGraphe)
			{
				
				if(varTypeChart)
					{
						new Chart(ctx).Line(chartData, {
							animationSteps:10,
							responsive: true,
							bezierCurve : false,
							pointHitDetectionRadius:1,
							scaleFontSize: 16
						});
					}else
						{
							new Chart(ctx).Line(chartData, {
								animationSteps:10,
								responsive: true,
								bezierCurve : true,
								pointHitDetectionRadius:1,
								scaleFontSize: 16
							});
						}
			}else
				{
					new Chart(ctx).Bar(chartData, {
						animationSteps:10,
						responsive : true,
						barShowStroke : false,
						pointHitDetectionRadius:1,
						scaleFontSize: 16
					});
				}
		}else 
			{
				if(varTypeGraphe)
				{
					
					if(varTypeChart)
						{
							new Chart(ctx).Line(chartData, {
								animationSteps:10,
								responsive: true,
								bezierCurve : false
							});
						}else
							{
								new Chart(ctx).Line(chartData, {
									animationSteps:10,
									responsive: true,
									bezierCurve : true
								});
							}
				}else
					{
						new Chart(ctx).Bar(chartData, {
							animationSteps:10,
							responsive : true,
							barShowStroke : false
						});
					}
			}
	
		
	
	
	
}


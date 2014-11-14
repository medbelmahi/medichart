

function findTheElementinterface(valInterface)
{
	for(var c = 0; c < iterfaceOption.length; c++)
	{
		if(iterfaceOption[c].aliasInterface == valInterface)
		{
			return iterfaceOption[c];
		}
	}
}

function existeElement(vall)
{
	var selectedInterfaceList = document.getElementById("ListInterface");
	
	for (var i = 0; i < selectedInterfaceList.length; i++ )
	{
		if(selectedInterfaceList[i].value == vall)
			{
			return true;
			}
	}
	
	return false;
}

function addInterfac()
{
	var selectedInterface = document.getElementById("selectionOptionInterface");
	var valeur = "";
	for (var i = 0; i < selectedInterface.length; i++ )
			{
				if(selectedInterface[i].selected)
					{
					valeur = selectedInterface[i].value;
					}
			}
			
	if(!existeElement(findTheElementinterface(valeur).idInterface))
		{
			var input_option = document.createElement("option");
			input_option.setAttribute('value',findTheElementinterface(valeur).idInterface);
			input_option.innerHTML = "Node : "+findTheElementinterface(valeur).node + " -- Interface : "+valeur ;
			document.getElementById("ListInterface").add(input_option);
		}else
			{
				alert("Alert: cette interface existe déjà");
			}
	
}

function removeSelected()
{
	var theSelectedInterfaces = document.getElementById("ListInterface");
	
	for (var i = 0; i < theSelectedInterfaces.length; i++ )
			{
				if(theSelectedInterfaces[i].selected)
					{
					//valeur = selectedInterface[i].value;
						theSelectedInterfaces[i].outerHTML = "";
					}
			}
			
			
}

function removeAll(){
	document.getElementById("ListInterface").innerHTML = "";
}
function myOnchangeSelectFunction(){
	
		var selectedVar = document.getElementById("selectionOptionNode");
		var selectedInterface = document.getElementById("selectionOptionInterface");
		var valeur;
		
		
		for (var i = 0; i < selectedVar.length; i++ )
			{
				if(selectedVar[i].selected)
					{
					valeur = selectedVar[i].value;
					}
			}
		
		
		
			
			selectedInterface.innerHTML = "";
			
			for(var c = 0; c < iterfaceOption.length; c++)
			{
				if(iterfaceOption[c].node == valeur)
				{
					var valeur_ = iterfaceOption[c].aliasInterface;
					var input_option = document.createElement("option");
					input_option.setAttribute('value',valeur_);
					input_option.innerHTML = valeur_ ;
					selectedInterface.add(input_option);	
				}
			}
}

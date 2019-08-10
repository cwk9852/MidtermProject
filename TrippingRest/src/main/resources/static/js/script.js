window.addEventListener('load', function() {

	console.log("loaded");
	init();

});

const getDestinations = async (url) => {
	let indexUrl = 'api/destinations';
    const response = await fetch(indexUrl);
    const data = await response.json();
    displayDestinations(data);
}

function init() {
	getDestinations();
	document.newDestination.submit.addEventListener('click', createDestination);
	document.getDestinationById.submit.addEventListener('click', function(e) {
		e.preventDefault();
		getOneDestination(document.getDestinationById.oid.value);
	});
	document.getElementById('allDestinations').addEventListener('click', function() {
		getDestinations();
	});
	document.getElementById('addDestination').addEventListener('click', function() {
		let addFormDiv = document.getElementById('addForm');
		addFormDiv.style.display='block';
	});
	
	
}
	
function createDestination(e) {
	e.preventDefault();
	var url = `api/destinations`
	var xhr = new XMLHttpRequest();
	xhr.open('POST', url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200 || xhr.status === 201) {
				destination = JSON.parse(xhr.responseText);
				console.log(destination.id);
				getDestinations();
			} else {
				document.getElementById('tablebody').textContent = '';
			}
		}
	}

	let destinationObject = {
		date : document.newDestination.date.value,
		summary : document.newDestination.summary.value,
		disease : document.newDestination.disease.value,
		location : document.newDestination.location.value,
		species : document.newDestination.species.value,
		cases : document.newDestination.cases.value,
		deaths : document.newDestination.deaths.value,
		significance : document.newDestination.significance.value,
	}
	
	xhr.send(JSON.stringify(destinationObject));
	document.newDestination.reset();
}
function updateDestination(e) {
	e.preventDefault();
	let oid = document.updateDestination.id.value;
	var url = 'api/destinations/' + oid;
	console.log(url);
		var xhr = new XMLHttpRequest();
	xhr.open('PUT', url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				destination = JSON.parse(xhr.responseText);
				console.log(destination.id);
				getDestinations();
			} else {
				document.getElementById('tablebody').textContent = '';
			}
		}
	}
	
	let destinationObject = {
			date : document.updateDestination.date.value,
			summary : document.updateDestination.summary.value,
			disease : document.updateDestination.disease.value,
			location : document.updateDestination.location.value,
			species : document.updateDestination.species.value,
			cases : document.updateDestination.cases.value,
			deaths : document.updateDestination.deaths.value,
			significance : document.updateDestination.significance.value,
	}
	
	xhr.send(JSON.stringify(destinationObject));
	document.newDestination.reset();
}

function displayDestinations(destinations) {
    console.log(destinations);

	document.getElementById('deleteButtonDiv').innerHTML = '';
	let addDestinationDiv = document.getElementById('addDestinationDiv');
	addDestinationDiv.style.display='none';
	let updateDiv = document.getElementById('update');
	updateDiv.style.display='none';
	let formsDiv = document.getElementById('forms');
	formsDiv.style.display='block';
//	let thead = document.getElementById('tableheaders').textContent = '';
//	let columns = destinations.keys();
//	columns.forEach(function( val, index, array) {
//		let colTitle = document.createElement('th');
//		colTitle.textContent= val;
//		thead.appendChild(colTitle);
//	});
	destinations.forEach(function(val, index, array) {
		let destinationTr = document.createElement('tr');
		let idTd = document.createElement('td');
		idTd.textContent = val.id;
		destinationTr.appendChild(idTd);
		let nameTd = document.createElement('td');
		nameTd.textContent = val.name;
		destinationTr.appendChild(nameTd);
		let shortDescriptionTd = document.createElement('td');
		shortDescriptionTd.textContent = val.shortDescription;
		destinationTr.appendChild(shortDescriptionTd);
//		let descriptionTd = document.createElement('td');
//		descriptionTd.textContent = val.description;
//		destinationTr.appendChild(descriptionTd);
//		let imageTd = document.createElement('td');
//		imageTd.textContent = val.image;
//		destinationTr.appendChild(imageTd);
		destinationTr.addEventListener('click', function(evt) {
			console.log(val.id);
			getOneDestination(val.id);
		});
		document.getElementById('tablebody').appendChild(destinationTr);
	});

}

function getOneDestination(oid) {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/destinations/' + oid, true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status < 400) {
				let destination = JSON.parse(xhr.responseText);
				displayDestination(destination);
			} else {
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}
	}
	xhr.send(null);

}

function deleteDestination(oid) {
	var xhr = new XMLHttpRequest();
	xhr.open('DELETE', 'api/destinations/' + oid, true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status < 400) {
				let destination = JSON.parse(xhr.responseText);
				getDestinations();
			} else {
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}
	}
	xhr.send(null);

}

function displayDestination(destination) {
	let fields = Object.keys(destination);
	document.updateDestination.innerHTML = '';
	fields.forEach(function(val, index, array) {
		let input = document.createElement('input')
		input.name = val; 
		input.type = 'text';
		document.updateDestination.appendChild(input);
		input.insertAdjacentHTML("beforeBegin",val.toString().toUpperCase()+": ");
		input.insertAdjacentHTML("afterend","<br>");
		document.updateDestination.elements[index].value = destination[val];
	});
	
	let input = document.createElement('input')
	input.name = 'submit'; 
	input.type = 'submit';
	input.value = 'Update';
	document.updateDestination.appendChild(input);
	document.updateDestination.submit.addEventListener('click', updateDestination);
	
	document.getElementById('deleteButtonDiv').innerHTML = '';
	let deleteButton = document.createElement('button')
	deleteButton.name = 'deleteButton'; 
	deleteButton.id = 'deleteButton';
	deleteButton.textContent = 'Delete';
	document.getElementById('deleteButtonDiv').appendChild(deleteButton);
	document.getElementById('deleteButton').addEventListener('click', function (e) {
		confirm("Press OK to Delete");
		deleteDestination(destination.id);
	});

//	document.updateDestination.elements["oid"].value = destination.id;
//	document.updateDestination.elements["date"].value = destination.date;
//	document.updateDestination.elements["summary"].value = destination.summary;
//	document.updateDestination.elements["disease"].value = destination.disease;
//	document.updateDestination.elements["location"].value = destination.location;
//	document.updateDestination.elements["species"].value = destination.species;
//	document.updateDestination.elements["cases"].value = destination.cases;
//	document.updateDestination.elements["deaths"].value = destination.deaths;
//	document.updateDestination.elements["significance"].value = destination.significance;

	let formsDiv = document.getElementById('forms');
	formsDiv.style.display='none';
	let updateDiv = document.getElementById('update');
	updateDiv.style.display='block';
	document.getElementById('tablebody').textContent = '';
	let destinationTr2 = document.createElement('tr');
	let dateTd = document.createElement('td');
	dateTd.textContent = destination.date;
	destinationTr2.appendChild(dateTd);
	let summaryTd = document.createElement('td');
	summaryTd.textContent = destination.summary;
	destinationTr2.appendChild(summaryTd);
	let diseaseTd = document.createElement('td');
	diseaseTd.textContent = destination.disease;
	destinationTr2.appendChild(diseaseTd);
	let locationTd = document.createElement('td');
	locationTd.textContent = destination.location;
	destinationTr2.appendChild(locationTd);
	let speciesTd = document.createElement('td');
	speciesTd.textContent = destination.species;
	destinationTr2.appendChild(speciesTd);
	let casesTd = document.createElement('td');
	casesTd.textContent = destination.cases;
	destinationTr2.appendChild(casesTd);
	let deathsTd = document.createElement('td');
	deathsTd.textContent = destination.deaths;
	destinationTr2.appendChild(deathsTd);
	let significaneTd = document.createElement('td');
	significaneTd.textContent = destination.significance;
	destinationTr2.appendChild(significaneTd);
	destinationTr2.addEventListener('click', function(evt) {
		console.log(destination.id);
	});
	document.getElementById('tablebody').appendChild(destinationTr2);
	
};
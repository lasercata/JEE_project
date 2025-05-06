const headers = {
	id: "Identifiant",
	titre: "Titre",
	horaires: "Horaires",
	lieu: "Lieu",
	personnages: "Personnages",
  };

const data = [
	{
	  id: 1,
	  titre: "Le Roi Lion",
	  horaires: "10h00, 14h00, 18h00",
	  lieu: "Théâtre de la ville",
	  personnages: ["Simba", "Nala"],
	},
	{
	  id: 1,
	  titre: "Le Lion Roi",
	  horaires: "10h00, 14h00, 18h00",
	  lieu: "Théâtre de la ville",
	  personnages: ["Simba", "Nala"],
	},
	{
	  id: 2,
	  titre: "Roi Le Lion",
	  horaires: "10h00, 14h00, 18h00",
	  lieu: "Théâtre de la ville",
	  personnages: ["Simba", "Nala"],
	},
  ];
function loadShows(url) {
	fetch(url)
	// .then(response => response.json())
	.then(response => process())
	.catch(error => alert("Erreur : " + error));
}

function process() {
	var showlist = document.getElementById("showList");
	
	/*

	var table = document.createElement("table");
	
	console.log(shows);
	col_headers = document.createElement("tr")
	for (var col in shows[0]) {
		var th = document.createElement("th");
		th.append(col);
		col_headers.appendChild(th);
	}

	table.appendChild(col_headers);
	for (var show in shows) {
		console.log(shows[show]);
		var tr = document.createElement("tr");
		var td_id = document.createElement("td");
		td_id.append(shows[show].id);
		var td_titre = document.createElement("td");
		td_titre.append(shows[show].titre);
		var td_horaires = document.createElement("td");
		td_horaires.append(shows[show].horaires);
		var td_lieu = document.createElement("td");
		td_lieu.append(shows[show].lieu);
		var td_personnages = document.createElement("td");
		td_personnages.append(shows[show].personnages.join(", "));
		tr.appendChild(td_id);
		tr.appendChild(td_titre);
		tr.appendChild(td_horaires);
		tr.appendChild(td_lieu);
		tr.appendChild(td_personnages);

		table.appendChild(tr);
	}
	showlist.appendChild(table);
	*/
	  // Create a new table element
	  const table = document.createElement("table");

	  // Create the headers
	  const headers_ = document.createElement("tr");
	  console.log(headers);
	  for (var header in headers) {
		console.log(headers[header]);
		var header_ = document.createElement("th");
		header_.textContent = headers[header];
		headers_.appendChild(header_);
	  }
	  table.appendChild(headers_);
  
	  // Fill the table
	  console.log(data);
	  data.forEach((row, index) => {
		console.log(index);
		var tr = document.createElement("tr");
  
		// Iterate through the headers to ensure column order
		for (var key in headers) {
		  var td = document.createElement("td");
		  const value = row[key];
  
		  // Handle arrays (e.g., personnages) by joining them with commas
		  td.textContent = Array.isArray(value) ? value.join(", ") : value;
		  console.log(td.textContent);
		  tr.appendChild(td);
		}
  
		table.appendChild(tr);
	  });
  
	  // Append the table to the page
	  showlist.appendChild(table);
	}

function searchByTitle() {
	//TODO
}




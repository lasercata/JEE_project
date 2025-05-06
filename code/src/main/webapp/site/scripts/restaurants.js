function loadAttractions(url) {
  fetch(url)
    .then((response) => response.json())
    .then((response) => process(response))
    .catch((error) => alert("Erreur : " + error));
}

function process(attractions) {
  var attractionlist = document.getElementById("attractionList");

  if (attractionlist.hasChildNodes()) {
    attractionlist.removeChild(attractionlist.firstChild);
  }

  var table = document.createElement("table");
  attractionlist.appendChild(table);

  console.log(attractions);
  for (var attraction of attractions) {
    console.log(attraction);
    var tr = document.createElement("tr");
    var td_id = document.createElement("td");
    td_id.append(attraction.id);
    var td_name = document.createElement("td");
    td_name.append(attraction.name);
    var td_type = document.createElement("td");
    td_type.append(attraction.type);
    var td_sizeAlone = document.createElement("td");
    td_sizeAlone.append(attraction.sizeAlone);
    var td_sizeWithAdult = document.createElement("td");
    td_sizeWithAdult.append(attraction.sizeWithAdult);
    tr.appendChild(td_id);
    tr.appendChild(td_name);
    tr.appendChild(td_type);
    tr.appendChild(td_sizeAlone);
    tr.appendChild(td_sizeWithAdult);
    table.appendChild(tr);
  }
}

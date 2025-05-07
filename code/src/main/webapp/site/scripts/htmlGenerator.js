const headers = {
  nom: "Titre",
  jour: "Jour",
  heure: "Horaires",
  lieu: "Lieu",
  personnages: "Personnages",
};
const data = [
  {
    nom: "Le Roi Lion",
    jour: "Lundi",
    heure: "10:00",
    lieu: "Théâtre de la ville",
    personnages: ["Simba", "Nala"],
  },
];

function loadAttractions() {
  return new HtmlGenerator("Attractions", headers, data);
}

function loadRestaurants() {
  return new HtmlGenerator("Restaurants", headers, data);
}

function loadShows() {
  return new HtmlGenerator("Shows", headers, data);
}

function load(url) {
  fetch(url)
    // .then((response) => response.json())
    // .then((response) => process(response))
    .then((response) => new HtmlGenerator("Restaurants", headers, data))
    .catch((error) => alert("Erreur : " + error));
}

/**
 * javascript class to generate html pages.
 */
class HtmlGenerator {
  /**
   * HtmlGenerator class constructor.
   * @param {string} title - type of activity : will be put in the header and is used to choose the right input.
   * @param {JSON} headers - JSON containing the DB columns names (fetch it with a SQL query).
   * @param {Array} data - array containing every object we fetched from the DB (as JSONs).
   */
  constructor(title, headers, data) {
    this.title = title;
    this.headers = headers;
    this.data = data;
    this.editingIndex = null;

    this.update();
  }

  /**
   * Adds the title to the page.
   */
  addTitle() {
    // Create a new header element
    const header = document.createElement("h1");

    // Add content to the header
    header.textContent = "This is the " + this.title + " management page";

    // Append the header to the document
    document.body.appendChild(header);
  }

  /**
   * Adds the table containing the data.
   */
  addTable() {
    // Create a new table element
    this.table = document.createElement("table");

    // Create the headers
    const headers_ = document.createElement("tr");
    for (var header in this.headers) {
      const header_ = document.createElement("th");
      header_.textContent = this.headers[header];
      headers_.appendChild(header_);
    }

    // Add Edit/Delete column header
    const actionsHeader = document.createElement("th");
    actionsHeader.textContent = "Actions";
    headers_.appendChild(actionsHeader);

    this.table.appendChild(headers_);

    // Fill the table with existing data using addTableRow
    this.data.forEach((row) => {
      this.addTableRow(row);
    });

    // Append the table to the page
    document.body.appendChild(this.table);
  }

  /**
   * Adds a row to the table.
   * @param {JSON} rowData - Data to add to the row.
   */
  addTableRow(rowData) {
    if (!this.table) {
      console.warn("La table n'existe pas encore. Utilisez addTable d'abord.");
      return;
    }

    // Ajouter rowData à la structure interne de données
    this.data.push(rowData);

    // Créer la ligne de tableau
    const tr = document.createElement("tr");

    const cellRefs = [];

    for (const key in this.headers) {
      const td = document.createElement("td");
      const value = rowData[key];
      td.textContent = Array.isArray(value) ? value.join(", ") : value ?? "";
      tr.appendChild(td);
      cellRefs.push(td);
    }

    // Boutons d'action
    const actionsTd = document.createElement("td");

    const editBtn = document.createElement("button");
    editBtn.textContent = "✏️";

    const setEditHandler = () => {
      // Remplacer chaque cellule par un input
      cellRefs.forEach((td, i) => {
        const key = Object.keys(this.headers)[i];
        const input = document.createElement("input");
        input.value = Array.isArray(rowData[key])
          ? rowData[key].join(", ")
          : rowData[key];
        td.textContent = "";
        td.appendChild(input);
      });

      editBtn.textContent = "✅";
      editBtn.onclick = () => {
        cellRefs.forEach((td, i) => {
          const input = td.querySelector("input");
          const key = Object.keys(this.headers)[i];
          const newValue = input.value.includes(",")
            ? input.value.split(",").map((s) => s.trim())
            : input.value;
          rowData[key] = newValue;
          td.textContent = Array.isArray(newValue)
            ? newValue.join(", ")
            : newValue;
        });

        editBtn.textContent = "✏️";
        editBtn.onclick = setEditHandler;
      };
    };

    editBtn.onclick = setEditHandler;

    const deleteBtn = document.createElement("button");
    deleteBtn.textContent = "🗑️";
    deleteBtn.onclick = () => {
      const rowIndex = this.data.indexOf(rowData);
      if (rowIndex > -1) this.data.splice(rowIndex, 1);
      tr.remove();
    };

    actionsTd.appendChild(editBtn);
    actionsTd.appendChild(deleteBtn);
    tr.appendChild(actionsTd);

    this.table.appendChild(tr);
  }

  /**
   * Appends schedule input for each day of the week to a given HTML element.
   * @param {HTMLElement} parentElement - The element to which the content will be appended.
   */
  appendSchedule(parentElement) {
    const htmlContent = `
      <div>
          <label for="monday_open">Lundi - Ouverture :</label>
          <input type="time" id="monday_open" name="opening_hours" required>
  
          <label for="monday_close">Fermeture :</label>
          <input type="time" id="monday_close" name="closing_hours" required>
      </div>
  
      <div>
          <label for="tuesday_open">Mardi - Ouverture :</label>
          <input type="time" id="tuesday_open" name="opening_hours" required>
  
          <label for="tuesday_close">Fermeture :</label>
          <input type="time" id="tuesday_close" name="closing_hours" required>
      </div>
  
      <div>
          <label for="wednesday_open">Mercredi - Ouverture :</label>
          <input type="time" id="wednesday_open" name="opening_hours" required>
  
          <label for="wednesday_close">Fermeture :</label>
          <input type="time" id="wednesday_close" name="closing_hours" required>
      </div>
  
      <div>
          <label for="thursday_open">Jeudi - Ouverture :</label>
          <input type="time" id="thursday_open" name="opening_hours" required>
  
          <label for="thursday_close">Fermeture :</label>
          <input type="time" id="thursday_close" name="closing_hours" required>
      </div>
  
      <div>
          <label for="friday_open">Vendredi - Ouverture :</label>
          <input type="time" id="friday_open" name="opening_hours" required>
  
          <label for="friday_close">Fermeture :</label>
          <input type="time" id="friday_close" name="closing_hours" required>
      </div>
  
      <div>
          <label for="saturday_open">Samedi - Ouverture :</label>
          <input type="time" id="saturday_open" name="opening_hours" required>
  
          <label for="saturday_close">Fermeture :</label>
          <input type="time" id="saturday_close" name="closing_hours" required>
      </div>
  
      <div>
          <label for="sunday_open">Dimanche - Ouverture :</label>
          <input type="time" id="sunday_open" name="opening_hours" required>
  
          <label for="sunday_close">Fermeture :</label>
          <input type="time" id="sunday_close" name="closing_hours" required>
      </div>
    `;

    // Create a temporary container to hold the HTML string
    const tempContainer = document.createElement("div");
    tempContainer.innerHTML = htmlContent;

    // Append all children to the target parent element
    while (tempContainer.firstChild) {
      parentElement.appendChild(tempContainer.firstChild);
    }
  }

  /**
   * Appends select input to a given HTML element.
   * @param {HTMLElement} parentElement - The element to which the content will be appended.
   * @param {Array} options - An array containing all the options we can select.
   * @param {string} id - the id of the select element.
   */
  appendSelect(parentElement, options, id) {
    var input = document.createElement("select");
    input.id = id;
    var input_blank = document.createElement("option");
    input_blank.value = "";
    input_blank.textContent = "-- Please choose an option --";
    input.appendChild(input_blank);
    for (var option in options) {
      var cuisine_input_option = document.createElement("option");
      cuisine_input_option.value = options[option].toLowerCase();
      cuisine_input_option.textContent = options[option];
      input.appendChild(cuisine_input_option);
    }

    parentElement.appendChild(input);
  }

  /**
   * Adds the inputs for the data to add to the DB for attractions.
   * @param {HTMLElement} parentElement - The element to which the content will be appended.
   */
  addInputsAttractions(parentElement) {
    var name_li = document.createElement("li");
    var name_label = document.createElement("label");
    name_label.textContent = "Nom :";
    var name_input = document.createElement("input");
    name_input.id = "attraction_name";
    name_li.appendChild(name_label);
    name_li.appendChild(name_input);

    const options = ["Rollercoaster", "4D Cinema", "Family"];
    var type_li = document.createElement("li");
    var type_label = document.createElement("label");
    type_label.textContent = "Type :";
    type_li.appendChild(type_label);
    this.appendSelect(type_li, options, "attraction_type");

    var min_height_alone_li = document.createElement("li");
    var min_height_alone_label = document.createElement("label");
    min_height_alone_label.textContent = "Taille minimum (seul) :";
    var min_height_alone_input = document.createElement("input");
    min_height_alone_input.type = "number";
    min_height_alone_input.step = 0.1;
    min_height_alone_input.min = 0;
    min_height_alone_input.id = "size_alone";
    min_height_alone_li.appendChild(min_height_alone_label);
    min_height_alone_li.appendChild(min_height_alone_input);

    var min_height_li = document.createElement("li");
    var min_height_label = document.createElement("label");
    min_height_label.textContent = "Taille minimum (accompagné) :";
    var min_height_input = document.createElement("input");
    min_height_input.type = "number";
    min_height_input.step = 0.1;
    min_height_input.min = 0;
    min_height_input.id = "size_with_adult";
    min_height_li.appendChild(min_height_label);
    min_height_li.appendChild(min_height_input);

    var schedule_li = document.createElement("li");
    var schedule_label = document.createElement("label");
    schedule_label.textContent = "Horaires :";
    schedule_li.appendChild(schedule_label);
    this.appendSchedule(schedule_li);

    parentElement.appendChild(name_li);
    parentElement.appendChild(type_li);
    parentElement.appendChild(min_height_alone_li);
    parentElement.appendChild(min_height_li);
    parentElement.appendChild(schedule_li);
  }

  /**
   * Adds the inputs for the data to add to the DB for restaurants.
   * @param {HTMLElement} parentElement - The element to which the content will be appended.
   */
  addInputsRestaurants(parentElement) {
    var name_li = document.createElement("li");
    var name_label = document.createElement("label");
    name_label.textContent = "Nom :";
    var name_input = document.createElement("input");
    name_input.id = "restaurant_name";
    name_li.appendChild(name_label);
    name_li.appendChild(name_input);

    const options = [
      "Française",
      "Italienne",
      "Chinoise",
      "Américaine",
      "Mexicaine",
      "Grecque",
      "Indienne",
      "Japonaise",
      "Thaïlandaise",
      "Espagnole",
      "Marocaine",
    ];
    var cuisine_li = document.createElement("li");
    var cuisine_label = document.createElement("label");
    cuisine_label.textContent = "Cuisine :";
    cuisine_li.appendChild(cuisine_label);
    this.appendSelect(cuisine_li, options, "cuisine_type");

    var schedule_li = document.createElement("li");
    var schedule_label = document.createElement("label");
    schedule_label.textContent = "Horaires :";
    schedule_li.appendChild(schedule_label);
    this.appendSchedule(schedule_li);

    var nb_seats_li = document.createElement("li");
    var nb_seats_label = document.createElement("label");
    nb_seats_label.textContent = "Nombre de places :";
    var nb_seats_input = document.createElement("input");
    nb_seats_input.type = "number";
    nb_seats_input.min = 0;
    nb_seats_input.step = 1;
    nb_seats_input.id = "nb_seats";
    nb_seats_li.appendChild(nb_seats_label);
    nb_seats_li.appendChild(nb_seats_input);

    parentElement.appendChild(name_li);
    parentElement.appendChild(cuisine_li);
    parentElement.appendChild(schedule_li);
    parentElement.appendChild(nb_seats_li);
  }

  /**
   * Adds the inputs for the data to add to the DB for shows.
   * @param {HTMLElement} parentElement - The element to which the content will be appended.
   */
  addInputsShows(parentElement) {
    var name_li = document.createElement("li");
    var name_label = document.createElement("label");
    name_label.textContent = "Nom :";
    var name_input = document.createElement("input");
    name_input.id = "title";
    name_li.appendChild(name_label);
    name_li.appendChild(name_input);

    const week = [
      "Lundi",
      "Mardi",
      "Mercredi",
      "Jeudi",
      "Vendredi",
      "Samedi",
      "Dimanche",
    ];
    var time_li = document.createElement("li");
    var day_label = document.createElement("label");
    day_label.textContent = "Jour :";
    time_li.appendChild(day_label);
    this.appendSelect(time_li, week, "day");
    var hour_label1 = document.createElement("label");
    hour_label1.textContent = "Heure de début:";
    var hour_input1 = document.createElement("input");
    hour_input1.type = "time";
    hour_input1.id = "beg_time";
    var hour_label2 = document.createElement("label");
    hour_label2.textContent = "Heure de fin:";
    var hour_input2 = document.createElement("input");
    hour_input2.type = "time";
    hour_input2.id = "end_time";
    time_li.appendChild(hour_label1);
    time_li.appendChild(hour_input1);
    time_li.appendChild(hour_label2);
    time_li.appendChild(hour_input2);

    const showLocations = [
      "Jardin Enchanté",
      "Château Mystérieux",
      "Aquarium Géant",
      "Théâtre Magique",
      "Vallée des Dragons",
      "Arène Intergalactique",
    ];
    var location_li = document.createElement("li");
    var location_label = document.createElement("label");
    location_label.textContent = "Lieu :";
    location_li.appendChild(location_label);
    this.appendSelect(location_li, showLocations, "location");

    const characters = [
      "Sherlock Holmes",
      "Gandalf",
      "Darth Vader",
      "Harry Potter",
      "Arya Stark",
      "Spock",
      "Batman",
      "Homer Simpson",
      "Lara Croft",
      "The Joker",
      "Mario",
      "Legolas",
      "Yoda",
      "Kratos",
      "Spider-Man",
    ];

    const characters_li = document.createElement("li");

    const characters_label = document.createElement("label");
    characters_label.textContent = "Personnages :";
    characters_li.appendChild(characters_label);

    // Container for checkboxes
    const checkboxContainer = document.createElement("div");

    characters.forEach((character) => {
      const checkboxWrapper = document.createElement("div");

      const checkbox = document.createElement("input");
      checkbox.type = "checkbox";
      checkbox.name = "characters";
      checkbox.value = character;
      checkbox.id = "characters";

      const label = document.createElement("label");
      label.textContent = character;

      checkboxWrapper.appendChild(checkbox);
      checkboxWrapper.appendChild(label);
      checkboxContainer.appendChild(checkboxWrapper);
    });

    characters_li.appendChild(checkboxContainer);

    parentElement.appendChild(name_li);
    parentElement.appendChild(time_li);
    parentElement.appendChild(location_li);
    parentElement.appendChild(characters_li);
  }

  /**
   * Fetches the content of the form input.
   * @param {HTMLElement} container
   * @returns the fetched data in json format.
   */
  collectFormData(container) {
    const data = {};
    if (this.title === "Attractions") {
      data.nom = container.querySelector("#attraction_name").value;
      data.type = container.querySelector("#attraction_type").value;
      data.minHauteurSeul = parseFloat(
        container.querySelector("#size_alone").value
      );
      data.minHauteurAccompagne = parseFloat(
        container.querySelector("#size_with_adult").value
      );
      data.horaires = this.collectSchedule(
        container.querySelector("li:nth-child(5)")
      );
    } else if (this.title === "Restaurants") {
      data.nom = container.querySelector("#restaurant_name").value;
      data.cuisine = container.querySelector("#cuisine_type").value;
      data.horaires = this.collectSchedule(
        container.querySelector("li:nth-child(3)")
      );
      data.nombrePlaces = parseInt(container.querySelector("#nb_seats").value);
    } else {
      data.nom = container.querySelector("#title").value;
      data.jour = container.querySelector("#day").value;

      const timeInputs = container.querySelectorAll(
        "li:nth-child(2) input[type='time']"
      );
      data.heureDebut = timeInputs[0]?.value || "";
      data.heureFin = timeInputs[1]?.value || "";

      data.lieu = container.querySelector("#location").value;
      data.personnages = Array.from(
        container.querySelectorAll(
          "li:nth-child(4) input[type='checkbox']:checked"
        )
      ).map((cb) => cb.value);
    }

    return data;
  }

  /**
   * Fetches the content of the schedule input.
   * @param {HTMLElement} container - schedule inputs container.
   * @returns the schedule fetched data in json format.
   */
  collectSchedule(container) {
    const days = [
      "monday",
      "tuesday",
      "wednesday",
      "thursday",
      "friday",
      "saturday",
      "sunday",
    ];
    const schedule = {};
    days.forEach((day) => {
      const open = container.querySelector(`#${day}_open`).value;
      const close = container.querySelector(`#${day}_close`).value;
      schedule[day] = { open, close };
    });
    return schedule;
  }

  /**
   * Adds the inputs for the data to add to the DB.
   */
  addInputs() {
    const ul = document.createElement("ul");
    if (this.title == "Attractions") {
      this.addInputsAttractions(ul);
    } else if (this.title == "Restaurants") {
      this.addInputsRestaurants(ul);
    } else {
      this.addInputsShows(ul);
    }

    // Add button
    const button = document.createElement("button");
    button.textContent = "Ajouter";
    button.addEventListener("click", () => {
      const data = this.collectFormData(ul);
      const newData = JSON.stringify(data, null, 2);
      console.log(newData); // for now we print the data...
      this.addTableRow(data);

      // Reset the inputs
      const inputs = ul.querySelectorAll("input, select");
      inputs.forEach((input) => {
        if (input.type === "checkbox" || input.type === "radio") {
          input.checked = false;
        } else {
          input.value = "";
        }
      });
    });

    document.body.appendChild(ul);
    document.body.appendChild(button);
  }

  /**
   * Calls all the functions creating elements in the page.
   */
  update() {
    this.addTitle();
    this.addTable();
    this.addInputs();
  }
}

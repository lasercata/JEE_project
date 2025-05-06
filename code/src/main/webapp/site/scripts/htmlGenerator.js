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
    id: 2,
    titre: "Le Lion Roi",
    horaires: "10h00, 14h00, 18h00",
    lieu: "Théâtre de la ville",
    personnages: ["Simba", "Nala"],
  },
  {
    id: 3,
    titre: "Roi Le Lion",
    horaires: "10h00, 14h00, 18h00",
    lieu: "Théâtre de la ville",
    personnages: ["Simba", "Nala"],
  },
  {
    id: 4,
    titre: "Lion Le Lion",
    horaires: "10h00, 14h00, 18h00",
    lieu: "Théâtre de la ville",
    personnages: ["Simba", "Nala"],
  },
];

function load(url) {
  fetch(url)
    // .then((response) => response.json())
    // .then((response) => process(response))
    .then((response) => new HtmlGenerator("Testing", { headers, data }))
    .catch((error) => alert("Erreur : " + error));
}

/**
 * javascript class to generate html pages.
 */
class HtmlGenerator {
  constructor(title, { headers, data }) {
    this.title = title;
    this.headers = headers;
    this.data = data;

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
   * Adds the schedule input.
   */
  addTable() {
    // Create a new table element
    const table = document.createElement("table");

    // Create the headers
    const headers_ = document.createElement("tr");
    console.log(this.headers);
    for (var header in this.headers) {
      console.log(this.headers[header]);
      var header_ = document.createElement("th");
      header_.textContent = this.headers[header];
      headers_.appendChild(header_);
    }
    table.appendChild(headers_);

    // Fill the table
    console.log(this.data);
    this.data.forEach((row, index) => {
      console.log(index);
      var tr = document.createElement("tr");

      // Iterate through the headers to ensure column order
      for (var key in this.headers) {
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
    document.body.appendChild(table);
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
   */
  appendSelect(parentElement, options) {
    var input = document.createElement("select");
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
   */
  addInputsAttractions(ul) {
    var name_li = document.createElement("li");
    var name_label = document.createElement("label");
    name_label.textContent = "Nom :";
    var name_input = document.createElement("input");
    name_li.appendChild(name_label);
    name_li.appendChild(name_input);

    const options = ["Rollercoaster", "4D Cinema", "Family"];
    var type_li = document.createElement("li");
    var type_label = document.createElement("label");
    type_label.textContent = "Type :";
    type_li.appendChild(type_label);
    this.appendSelect(type_li, options);

    var min_height_alone_li = document.createElement("li");
    var min_height_alone_label = document.createElement("label");
    min_height_alone_label.textContent = "Taille minimum (seul) :";
    var min_height_alone_input = document.createElement("input");
    min_height_alone_input.type = "number";
    min_height_alone_input.step = 0.1;
    min_height_alone_input.min = 0;
    min_height_alone_li.appendChild(min_height_alone_label);
    min_height_alone_li.appendChild(min_height_alone_input);

    var min_height_li = document.createElement("li");
    var min_height_label = document.createElement("label");
    min_height_label.textContent = "Taille minimum (accompagné) :";
    var min_height_input = document.createElement("input");
    min_height_input.type = "number";
    min_height_input.step = 0.1;
    min_height_input.min = 0;
    min_height_li.appendChild(min_height_label);
    min_height_li.appendChild(min_height_input);

    var schedule_li = document.createElement("li");
    var schedule_label = document.createElement("label");
    schedule_label.textContent = "Horaires :";
    schedule_li.appendChild(schedule_label);
    this.appendSchedule(schedule_li);

    ul.appendChild(name_li);
    ul.appendChild(type_li);
    ul.appendChild(min_height_alone_li);
    ul.appendChild(min_height_li);
    ul.appendChild(schedule_li);
  }

  /**
   * Adds the inputs for the data to add to the DB for restaurants.
   */
  addInputsRestaurants(ul) {
    var name_li = document.createElement("li");
    var name_label = document.createElement("label");
    name_label.textContent = "Nom :";
    var name_input = document.createElement("input");
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
    this.appendSelect(cuisine_li, options);

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
    nb_seats_li.appendChild(nb_seats_label);
    nb_seats_li.appendChild(nb_seats_input);

    ul.appendChild(name_li);
    ul.appendChild(cuisine_li);
    ul.appendChild(schedule_li);
    ul.appendChild(nb_seats_li);
  }

  /**
   * Adds the inputs for the data to add to the DB for shows.
   */
  addInputsShows(ul) {
    var name_li = document.createElement("li");
    var name_label = document.createElement("label");
    name_label.textContent = "Nom :";
    var name_input = document.createElement("input");
    name_li.appendChild(name_label);
    name_li.appendChild(name_input);

    var time_li = document.createElement("li");

    ul.appendChild(name_li);
  }

  /**
   * Adds the inputs for the data to add to the DB.
   */
  addInputs() {
    const ul = document.createElement("ul");
    // this.addInputsAttractions(ul);
    this.addInputsRestaurants(ul);
    document.body.appendChild(ul);
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

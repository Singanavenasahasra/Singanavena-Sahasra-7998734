// EXERCISE 1: JavaScript Basics & Setup
console.log("Welcome to the Community Portal");

window.addEventListener('load', () => {
    alert("Community Portal page is fully loaded!");
    initPortal();
});

const MOCK_API_GET_URL = "https://jsonplaceholder.typicode.com/posts?_limit=4";
const MOCK_API_POST_URL = "https://jsonplaceholder.typicode.com/posts";

// EXERCISE 6: Arrays and Methods (Storage array initialization)
let allEvents = [];

// EXERCISE 5: Objects and Prototypes (Constructor Definition)
class Event {
    // EXERCISE 2: Syntax, Data Types, and Operators (let and const initialization)
    // EXERCISE 10: Modern JavaScript Features (Default parameters)
    constructor(id, name, date, seats = 10, category = "General") {
        this.id = id;
        this.name = name;
        this.date = new Date(date);
        this.seats = seats;
        this.category = category;
    }
}

// EXERCISE 5: Objects and Prototypes (Prototype Method Assignment)
// EXERCISE 3: Conditionals, Loops, and Error Handling (Logical evaluation)
Event.prototype.checkAvailability = function() {
    return this.seats > 0 && this.date >= new Date();
};

// EXERCISE 4: Functions, Scope, Closures, Higher-Order Functions (Closure implementation)
function createRegistrationTracker() {
    let totalCount = 0;
    return {
        increment() {
            totalCount++;
            return totalCount;
        },
        getCount() {
            return totalCount;
        }
    };
}
const globalRegistrationTracker = createRegistrationTracker();

// EXERCISE 9: Async JS, Promises, Async/Await (Async initialization with loading spinners)
async function initPortal() {
    showSpinner(); // EXERCISE 9: UI Spinner Activation
    try {
        // EXERCISE 12: AJAX & Fetch API (Fetching mock API endpoint data)
        const response = await fetch(MOCK_API_GET_URL);
        if (!response.ok) throw new Error("Network response was not ok");
        const rawData = await response.json();
        
        const mockCategories = ["Music", "Workshop", "Social", "Music"];
        const mockDates = ["2026-07-15", "2026-08-22", "2026-01-10", "2026-09-05"];
        const mockSeats = [15, 0, 50, 8];

        // EXERCISE 6: Arrays and Methods (.map function to format raw objects into structured entity cards)
        allEvents = rawData.map((item, index) => {
            return new Event(
                item.id,
                `Community ${mockCategories[index]}: ${item.title.substring(0, 15)}`,
                mockDates[index],
                mockSeats[index],
                mockCategories[index]
            );
        });

        renderEvents(allEvents);
        setupEventListeners();
    } catch (error) {
        // EXERCISE 3: Conditionals, Loops, and Error Handling (Catch operational failures)
        console.error("Failed to load events asynchronously:", error);
        document.querySelector("#eventContainer").innerHTML = "<p>Unable to load events. Please try again later.</p>";
    } finally {
        hideSpinner(); // EXERCISE 9: UI Spinner Deactivation
    }
}

// EXERCISE 7: DOM Manipulation (Dynamic UI Rendering function)
function renderEvents(eventsArray) {
    // EXERCISE 7: Accessing DOM elements via querySelector()
    const container = document.querySelector("#eventContainer");
    container.innerHTML = "";

    // EXERCISE 3: Conditionals, Loops, and Error Handling (forEach Loop execution)
    eventsArray.forEach(eventItem => {
        // EXERCISE 3: if-else conditional configuration to screen dynamic metrics
        if (!eventItem.checkAvailability()) {
            return;
        }

        // EXERCISE 10: Modern JavaScript Features (Destructuring assignment pattern)
        const { id, name, date, seats, category } = eventItem;
        const formattedDate = date.toLocaleDateString();
        
        // EXERCISE 2: Syntax, Data Types, and Operators (Concatenating data elements via Template Literals)
        const infoString = `Event: ${name} | Date: ${formattedDate}`;

        // EXERCISE 7: Generating elements natively using createElement()
        const card = document.createElement("div");
        card.className = "eventCard";
        card.id = `event-card-${id}`;
        card.style.display = "none"; 
        
        card.innerHTML = `
            <h3>${infoString}</h3>
            <p>Category: <strong>${category}</strong></p>
            <p>Available Slots: <span id="seats-count-${id}">${seats}</span></p>
            <button class="btn" id="register-btn-${id}">Register</button>
        `;

        container.appendChild(card);
        
        // EXERCISE 14: jQuery and JS Frameworks (.fadeIn Visual Effect mapping)
        $(`#event-card-${id}`).fadeIn(400);

        // EXERCISE 8: Event Handling (onclick direct reference hook assigning functionality)
        document.getElementById(`register-btn-${id}`).onclick = function() {
            handleQuickRegistration(id);
        };
    });
}

// EXERCISE 4: Functions, Scope, Closures, Higher-Order Functions (Action Execution Encapsulation)
function handleQuickRegistration(eventId) {
    // EXERCISE 13: Debugging and Testing (Console tracking diagnostic markers)
    console.log(`[Debug] Initiating registration workflow for Event ID: ${eventId}`);
    
    // EXERCISE 3: try-catch wrapper encapsulating functional business pathways
    try {
        const targetEvent = allEvents.find(e => e.id === eventId);
        if (!targetEvent) throw new Error("Target event data signature not found.");

        if (targetEvent.seats <= 0) {
            alert("Registration Error: No remaining seats available!");
            return;
        }

        // EXERCISE 2: Syntax, Data Types, and Operators (Decrement operator handling available seats allocation counter)
        targetEvent.seats--;
        
        // EXERCISE 7: Dynamic structural UI target value update execution 
        const countDisplay = document.getElementById(`seats-count-${eventId}`);
        if (countDisplay) countDisplay.innerText = targetEvent.seats;

        // EXERCISE 4: Utilizing the closure function tracking overall portal allocations
        const currentTotal = globalRegistrationTracker.increment();
        console.log(`[Registration Tracker] Active dynamic system wide registration count: ${currentTotal}`);
        
        alert(`Successfully registered! Total portal check-ins processed: ${currentTotal}`);
        
        if (targetEvent.seats === 0) {
            // EXERCISE 14: jQuery and JS Frameworks (.fadeOut implementation sequence)
            $(`#event-card-${eventId}`).fadeOut(500, function() {
                renderEvents(allEvents);
            });
        }
    } catch (err) {
        console.error("Registration engine exception caught:", err.message);
    }
}

function setupEventListeners() {
    const filterDropdown = document.querySelector("#categoryFilter");
    if (filterDropdown) {
        // EXERCISE 8: Event Handling (onchange input element processing behavior)
        filterDropdown.onchange = function(e) {
            filterEventsByCategory(e.target.value);
        };
    }

    const searchBox = document.querySelector("#searchBox");
    if (searchBox) {
        // EXERCISE 8: Event Handling (onkeydown tracking character sequence loops)
        searchBox.onkeydown = function(e) {
            // EXERCISE 12: AJAX & Fetch API (setTimeout to isolate keystroke evaluation loops)
            setTimeout(() => {
                const keyword = e.target.value.toLowerCase();
                // EXERCISE 6: Arrays and Methods (.filter method lookup pipeline)
                const matchedEvents = allEvents.filter(evt => evt.name.toLowerCase().includes(keyword));
                renderEvents(matchedEvents);
            }, 10);
        };
    }

    const registrationForm = document.querySelector("#registrationForm");
    if (registrationForm) {
        registrationForm.onsubmit = function(e) {
            // EXERCISE 11: Working with Forms (Prevent execution standard defaults)
            e.preventDefault();
            processFormSubmission(e.target);
        };
    }
}

// EXERCISE 4: Functions, Scope, Closures, Higher-Order Functions (Higher-Order search execution filter logic)
function filterEventsByCategory(selectedCategory) {
    // EXERCISE 10: Modern JavaScript Features (Spread operator cloning layout indices)
    const clonedList = [...allEvents];
    
    if (!selectedCategory) {
        renderEvents(clonedList);
        return;
    }

    // EXERCISE 4: Dynamic analytical callback parameter configuration logic
    const dynamicCallback = (evt) => evt.category === selectedCategory;
    // EXERCISE 6: Arrays and Methods (.filter interface lookup verification)
    const filtered = clonedList.filter(dynamicCallback);
    renderEvents(filtered);
}

// EXERCISE 4: Functions, Scope, Closures, Higher-Order Functions (Creation mechanism encapsulation execution rules)
function addEvent({ name, date, seats, category }) {
    const newId = allEvents.length > 0 ? Math.max(...allEvents.map(e => e.id)) + 1 : 1;
    const newEvent = new Event(newId, name, date, seats, category);
    
    // EXERCISE 6: Arrays and Methods (.push database creation array insertion modification tracking)
    allEvents.push(newEvent);
    renderEvents(allEvents);
    
    // EXERCISE 5: Objects and Prototypes (Object.entries visualization testing logging patterns)
    console.log(`[Debug] New event pushed successfully. Current database log structure:`, Object.entries(newEvent));
}

function processFormSubmission(formElement) {
    console.log("[Debug] Form submission caught. Intercepting network payloads.");

    // EXERCISE 11: Working with Forms (Target parsing form variables capturing via form.elements collections)
    const nameInput = formElement.elements["userName"];
    const emailInput = formElement.elements["userEmail"];
    const eventInput = formElement.elements["selectedEventId"];

    let hasErrors = false;
    clearInlineErrors();

    // EXERCISE 11: Working with Forms (Dynamic baseline properties inline error check evaluations)
    if (!nameInput.value.trim()) {
        showInlineError(nameInput, "Full Name parameter is mandatory.");
        hasErrors = true;
    }
    if (!emailInput.value.includes("@")) {
        showInlineError(emailInput, "Please present a verified structural email account layout.");
        hasErrors = true;
    }
    if (!eventInput.value) {
        showInlineError(eventInput, "You must select a primary target event tier.");
        hasErrors = true;
    }

    if (hasErrors) return;

    const payload = {
        registrantName: nameInput.value,
        registrantEmail: emailInput.value,
        eventId: parseInt(eventInput.value, 10)
    };

    // EXERCISE 13: Debugging and Testing (Log form steps and trace data payloads prior to structural transmission)
    console.log("[Debug] Package validation verified. Payload scheduled for transmission:", payload);
    showSpinner();

    // EXERCISE 12: AJAX & Fetch API (setTimeout applied delaying verification profiles)
    setTimeout(() => {
        // EXERCISE 9: Async JS (Network promise orchestration sequence handling utilizing .then and .catch blocks)
        // EXERCISE 12: AJAX & Fetch API (Transmitting payload variables safely via Fetch API POST protocol engines)
        fetch(MOCK_API_POST_URL, {
            method: "POST",
            body: JSON.stringify(payload),
            headers: { "Content-type": "application/json; charset=UTF-8" }
        })
        .then(response => {
            if (!response.ok) throw new Error("Server transmission error failed on verification nodes.");
            return response.json();
        })
        .then(data => {
            console.log("[Debug] Post deployment resolution completed. Response payload:", data);
            // EXERCISE 12: AJAX & Fetch API (Conditional server success feedback visualization output alerts)
            alert(`Success! Backend registration confirmed. tracking key ID: ${data.id}`);
            formElement.reset();
            handleQuickRegistration(payload.eventId);
        })
        .catch(err => {
            console.error("AJAX pipeline failed transaction loops:", err);
            alert("Error: Unable to transmit secure reservation parameters to central council servers.");
        })
        .finally(() => {
            hideSpinner();
        });
    }, 1500); 
}

function showInlineError(inputElement, errorMessage) {
    inputElement.style.border = "2px solid red";
    const errorSpan = document.createElement("span");
    errorSpan.className = "inline-error-message";
    errorSpan.style.color = "red";
    errorSpan.style.fontSize = "12px";
    errorSpan.innerText = errorMessage;
    inputElement.parentNode.appendChild(errorSpan);
}

function clearInlineErrors() {
    const messages = document.querySelectorAll(".inline-error-message");
    messages.forEach(msg => msg.remove());
    const inputs = document.querySelectorAll("#registrationForm input, #registrationForm select");
    inputs.forEach(input => input.style.border = "");
}

function showSpinner() {
    const spinner = document.querySelector("#loadingSpinner");
    if (spinner) spinner.style.display = "block";
}

function hideSpinner() {
    const spinner = document.querySelector("#loadingSpinner");
    if (spinner) spinner.style.display = "none";
}

// EXERCISE 14: jQuery and JS Frameworks ($('#id').click structural interaction handlers)
$("#registerBtn").click(function() {
    $('html, body').animate({
        scrollTop: $("#registrationForm").offset().top
    }, 800);
});
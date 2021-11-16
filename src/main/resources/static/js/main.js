function writeItem() {
    let container = document.getElementById("container");

    let div = document.createElement("div");

    let checkbox = document.createElement("input");
    let span = document.createElement("span");
    let button = document.createElement("button");

    let inputText = document.getElementById("input-item");
    span.innerText = inputText.value;

    checkbox.type = "checkbox";
    checkbox.setAttribute("onchange", "updateText(event)");

    button.className = "add-button";
    button.innerText = "×";
    button.setAttribute("onclick", "removeItem(event)");

    div.className = "item-container";

    div.append(checkbox);
    div.append(span);
    div.append(button);

    container.append(div);

    inputText.value = "";
}

function removeItem(event) {
    event.currentTarget.parentNode.remove();
}

function updateText(event) {
    let text = event.currentTarget.nextSibling;

    if (text.style.textDecoration === "line-through") {
        text.style.textDecoration = "none";
    } else {
        text.style.textDecoration = "line-through";
    }
}
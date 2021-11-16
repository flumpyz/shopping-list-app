function writeItem() {
    let container = document.getElementById("container");

    let div = document.createElement("div");
    let span = document.createElement("span");
    let button = document.createElement("button");

    let inputText = document.getElementById("input-item");
    span.innerText = inputText.value;

    button.className = "add-button";
    button.innerText = "×";
    button.setAttribute("onclick", "removeItem(event)");

    div.className = "item-container";

    div.append(span);
    div.append(button);

    container.append(div);

    inputText.value = "";
}

function removeItem(event) {
    event.currentTarget.parentNode.remove();
}
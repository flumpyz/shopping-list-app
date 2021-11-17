function writeItem() {
    let container = document.getElementById("container");

    let div = document.createElement("div");

    let checkbox = document.createElement("input");
    let span = document.createElement("span");
    let button = document.createElement("button");

    let inputText = document.getElementById("input-item");
    let text = inputText.value;
    span.innerText = text;

    checkbox.type = "checkbox";
    checkbox.setAttribute("onchange", "updateText(event)");

    button.className = "delete-button";
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
    let noteId = event.currentTarget.id;

    event.currentTarget.parentNode.remove();


    let originPath = window.location.origin;
    fetch(`${originPath}/delete`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
                purchaseId: noteId
            }
        )
    })
}

function updateText(event) {
    let text = event.currentTarget.nextSibling.innerText;

    if (text.style.textDecoration === "line-through") {
        text.style.textDecoration = "none";
    } else {
        text.style.textDecoration = "line-through";
    }
}
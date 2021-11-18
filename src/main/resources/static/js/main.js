function removeItem(event) {
    let noteId = event.currentTarget.parentNode.id;

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

    let noteId = event.currentTarget.parentNode.id;
    let parentNode = event.currentTarget.parentNode;
    let text = event.currentTarget.parentNode;


    for (let i = 0; i < parentNode.children.length; i++) {
        if (parentNode.children[i].tagName === 'SPAN') {
            text = parentNode.children[i];
        }
        console.log(parentNode.children[i].tagName);
    }

    text.classList.toggle('strikethrough-line');
    let purchased = text.classList.contains('strikethrough-line');

    let originPath = window.location.origin;
    fetch(`${originPath}/update`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
                purchaseId: noteId,
                isBought: purchased
            }
        )
    })
}
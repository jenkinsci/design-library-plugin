function showModal() {
    const template = document.querySelector("#demo-template");
    const title = template.getAttribute("data-title");
    const content = template.content.firstElementChild.cloneNode(true);
    dialog.modal(content, {
      maxWidth: "550px",
      title: title,
    });
}

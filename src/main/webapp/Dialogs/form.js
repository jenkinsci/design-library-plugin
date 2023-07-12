function showForm() {
    const formTemplate = document.getElementById("#demo-form");
    const form = formTemplate.firstElementChild.cloneNode(true);
    const toggle = document.getElementById("formsubmit");
    const title = formTemplate.dataset.title;
    dialog.form(form, {
      title: title, okText: "Order", submitButton: toggle.checked
     }).then(formData => {
        let title = "Order status"
        let message = "Thank you " + formData.get("name")
        + ".\n We received your order. Here are the details:"
        + "\n\nQuantity: " + formData.get("quantity")
        + "\nFlavor: " + formData.get("_.flavor")
        dialog.alert(title, {message: message});
    });
}
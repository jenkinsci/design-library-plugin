function showForm() {
    const formTemplate = document.querySelector("#demo-form");
    const form = formTemplate.firstElementChild.cloneNode(true);
    const toggle = document.getElementById("formsubmit");
    console.log("Toggle " + toggle.checked);
    dialog.form(form, {okText: "Order", submitButton: toggle.checked}).then(formData => {
        let title = "Order status"
        let message = "Thank you " + formData.get("_.name")
        + ".\n We received your order. Here are the details:"
        + "\n\nQuantity: " + formData.get("_.quantity")
        + "\nFlavor: " + formData.get("_.flavor")
        dialog.alert(message, {title: title});
    });
}
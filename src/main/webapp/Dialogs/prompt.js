function showPrompt() {
    dialog
      .prompt("How should I call you?", {
        title: "Welcome to the Dialog demo",
        minWidth: "450px",
        maxWidth: "600px" })
      .then(
        (name) => {
          dialog.alert("Hello " + name);
        },
        () => {
          dialog.alert("Hello Stranger");
        }
      );
}

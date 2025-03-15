function showPrompt() {
  dialog
    .prompt("Welcome to the Dialog demo", {
      message: "How should I call you?",
      minWidth: "450px",
      maxWidth: "600px",
    })
    .then(
      (name) => {
        dialog.alert("Hello " + name);
      },
      () => {
        dialog.alert("Hello Stranger");
      },
    );
}

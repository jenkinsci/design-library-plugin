function showConfirm() {
  dialog
    .confirm("Do it", {
      message: "Are you sure?",
      cancelText: dialog.translations.no,
    })
    .then(
      () => {
        dialog.alert("You've done it.");
      },
      () => {
        dialog.alert(null, {
          title: "Alright then",
          message: "I'm not gonna do it.",
        });
      },
    );
}

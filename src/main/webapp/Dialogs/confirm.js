function showConfirm() {
    dialog.confirm("Are you sure?", { title: "Do it", cancelText: dialog.translations.no }).then(
      () => {
        dialog.alert("You've done it.");
      },
      () => {
        dialog.alert("OK, I'm not gonna do it.");
      }
    );
}
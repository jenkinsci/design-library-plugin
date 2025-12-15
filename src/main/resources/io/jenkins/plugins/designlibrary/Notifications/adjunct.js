function showDefault() {
  notificationBar.show('Default');
}

function showSuccess() {
  notificationBar.show('Success', notificationBar.SUCCESS);
}

function showWarning() {
  notificationBar.show('Warning', notificationBar.WARNING);
}

function showError() {
  notificationBar.show('Error', notificationBar.ERROR);
}

function hideNotification() {
  notificationBar.hide();
}

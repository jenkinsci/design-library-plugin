function display(r) {
  for (var i = 0; r.newfactors.length > i; i++) {
    var li = document.createElement("li");
    li.appendChild(document.createTextNode(r.newfactors[i]));
    document.getElementById("factors").appendChild(li);
  }
}

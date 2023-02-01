a.increment(1, function(t) {
  document.getElementById('msg').innerHTML = t.responseObject();
})

document.addEventListener("DOMContentLoaded", () => {
  const rootUrl = document.querySelector('head').dataset.rooturl
  const searchBar = document.querySelector("#search-bar")
  const searchBarResults = document.querySelector("#search-results")

  searchBar.addEventListener("input", () => {
    const fullUrl = `${rootUrl}/design-library/search?query=${searchBar.value}`;

    function appendResults(container, results) {
      results.forEach(item => {
        container.append(createElementFromHtml(`<a href="${item.url}">${item.icon} ${item.title}</a>`))
        const children = createElementFromHtml(`<div></div>`)
        appendResults(children, item["children"])
        container.append(children)
      })
    }

    fetch(fullUrl)
      .then(response => response.json())
      .then(json => {
        searchBarResults.innerHTML = "";

        appendResults(searchBarResults, json["data"])

        console.log(json)
      });
    })
});

function createElementFromHtml(html) {
  const template = document.createElement("template");
  template.innerHTML = html.trim();
  return template.content.firstElementChild;
}

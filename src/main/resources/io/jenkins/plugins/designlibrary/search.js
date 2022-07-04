document.addEventListener("DOMContentLoaded", () => {
  const data = document.querySelector("#search-data").textContent
  const searchWrapper = document.querySelector("#search-wrapper")
  const searchBar = document.querySelector("#search-bar")
  const searchResultsContainer = document.querySelector("#search-results-container")
  const searchBarResults = document.querySelector("#search-results")

  searchBar.addEventListener("input", () => {
    const query = searchBar.value.toLowerCase()

    // Hide the suggestions if the search query is empty
    if (query.length === 0) {
      hideResultsContainer()
      return
    }

    showResultsContainer()

    function appendResults(container, results) {
      results.forEach(item => {
        container.append(createElementFromHtml(`<a href="${item.url}">${item.icon} ${item.title}</a>`))
        const children = createElementFromHtml(`<div></div>`)
        appendResults(children, item["children"])
        container.append(children)
      })

      if (results.length === 0 && container === searchBarResults) {
        container.append(createElementFromHtml(`<p class="jdl-search-results__no-results-label">No results</p>`))
      }
    }

    // Filter results
    const results = JSON.parse(data)
      .filter(item => item.title.toLowerCase().includes(query) ||
        item.children.some(child => child.title.toLowerCase().includes(query)))
      .map(item => {
        item.children = item.children.filter(child => child.title.toLowerCase().includes(query))
        return item;
      })
      .slice(0, 5)

    searchBarResults.innerHTML = ""
    appendResults(searchBarResults, results)
    searchResultsContainer.style.height = searchBarResults.offsetHeight + "px"
  })

  function createElementFromHtml(html) {
    const template = document.createElement("template")
    template.innerHTML = html.trim()
    return template.content.firstElementChild
  }

  function showResultsContainer() {
    searchResultsContainer.classList.add("jdl-search-results-container--visible")
    document.querySelectorAll(".task").forEach(task => {
      task.style.opacity = 0.25
    })
  }

  function hideResultsContainer() {
    searchResultsContainer.classList.remove("jdl-search-results-container--visible")
    searchResultsContainer.style.height = "0px"
    document.querySelectorAll(".task").forEach(task => {
      task.style.opacity = 1
    })
  }

  searchBar.addEventListener("focusin", () => {
    if (searchBar.value.length !== 0) {
      searchResultsContainer.style.height = searchBarResults.offsetHeight + "px"
      showResultsContainer()
    }
  })

  document.addEventListener("click", event => {
    if (searchWrapper.contains(event.target)) {
      return
    }

    hideResultsContainer()
  })
})

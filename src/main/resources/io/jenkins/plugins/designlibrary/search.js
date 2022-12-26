const searchBarInput = document.querySelector("#design-library-search-bar");

searchBarInput.suggestions = function () {
  return [...document.querySelectorAll(".task-link")]
    .map((item) => ({
      url: item.href,
      icon: item.querySelector(
        ".task-icon-link"
      ).innerHTML,
      label: item.querySelector(".task-link-text").textContent,
    }));
};

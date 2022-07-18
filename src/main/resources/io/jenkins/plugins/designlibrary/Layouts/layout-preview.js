window.addEventListener("load", () => {
  const appBar = document.querySelector(".jenkins-app-bar")
  const tabBar = document.querySelector(".tabBar")
  const twoPaneTab = tabBar.querySelector(".tab:first-of-type")
  const onePaneTab = tabBar.querySelector(".tab:nth-of-type(2)")
  const fullscreenTab = tabBar.querySelector(".tab:last-of-type")
  const layoutPreview = document.querySelector("#layout-preview")

  appBar.insertAdjacentElement('afterend', tabBar)
  tabBar.insertAdjacentElement('afterend', layoutPreview)

  function resetLayoutPreview() {
    layoutPreview.classList.remove("jdl-longhorn--one")
    layoutPreview.classList.remove("jdl-longhorn--two")
    layoutPreview.classList.remove("jdl-longhorn--cards")
  }

  onePaneTab.addEventListener("click", () => {
    resetLayoutPreview()
    layoutPreview.classList.add("jdl-longhorn--one")
  })

  twoPaneTab.addEventListener("click", () => {
    resetLayoutPreview()
    layoutPreview.classList.add("jdl-longhorn--two")
  })

  fullscreenTab.addEventListener("click", () => {
    resetLayoutPreview()
    layoutPreview.classList.add("jdl-longhorn--cards")
  })
})

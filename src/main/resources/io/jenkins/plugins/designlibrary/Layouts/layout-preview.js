window.addEventListener("load", () => {
  const tabBar = document.querySelector(".tabBar")
  const twoPaneTab = tabBar.querySelector(".tab:first-of-type")
  const onePaneTab = tabBar.querySelector(".tab:nth-of-type(2)")
  const fullscreenTab = tabBar.querySelector(".tab:last-of-type")
  const layoutPreview = document.querySelector("#layout-preview")

  function resetLayoutPreview() {
    layoutPreview.classList.remove("jdl-layout-preview--one-column")
    layoutPreview.classList.remove("jdl-layout-preview--two-column")
    layoutPreview.classList.remove("jdl-layout-preview--full-screen")
  }

  onePaneTab.addEventListener("click", () => {
    resetLayoutPreview()
    layoutPreview.classList.add("jdl-layout-preview--one-column")
  })

  twoPaneTab.addEventListener("click", () => {
    resetLayoutPreview()
    layoutPreview.classList.add("jdl-layout-preview--two-column")
  })

  fullscreenTab.addEventListener("click", () => {
    resetLayoutPreview()
    layoutPreview.classList.add("jdl-layout-preview--full-screen")
  })
})

// This file is a workaround as Jenkins' 'section-to-tabs.js' inserts the tab bar at the top of the page
// Converts a page's section headings into clickable tabs
const tabPanes = document.querySelectorAll(".jenkins-tab-pane")
const content = document.querySelector("#main-panel")

// Hide tab panes
tabPanes.forEach((tabPane) => {
  tabPane.style.display = "none"
})

// Show the first tab pane
tabPanes[0].style.display = "block"

const tabBar = document.createElement("div")
tabBar.className = "jdl-tabs jdl-tabs--js"

const activeTabBackdrop = document.createElement("div")
activeTabBackdrop.className = "jdl-tabs__tab-backdrop"
tabBar.append(activeTabBackdrop)

content.insertBefore(tabBar, tabPanes[0])

// Add tabs for each tab pane
tabPanes.forEach((tabPane, index) => {
  const tabPaneTitle = tabPane.querySelector(".jenkins-tab-pane__title")
  tabPaneTitle.style.display = "none"

  const tab = document.createElement("div")
  tab.className = "jdl-tabs__tab"
  tab.innerText = tabPaneTitle.textContent

  tabBar.append(tab)

  tab.addEventListener("click", function () {
    document.querySelectorAll(".jdl-tabs__tab").forEach((tab) => {
      tab.classList.remove("jdl-tabs__tab--active")
    })
    tab.classList.add("jdl-tabs__tab--active")

    tabPanes.forEach((tabPane) => {
      tabPane.style.display = "none"
    })
    tabPanes[index].style.display = "block"

    const leftOffset = tab.getBoundingClientRect().left - tabBar.getBoundingClientRect().left
    activeTabBackdrop.style.left = leftOffset + "px"
    activeTabBackdrop.style.width = tab.getBoundingClientRect().width + "px"
  })

  // Select the first tab
  if (index === 0) {
    tab.click()
  }
})

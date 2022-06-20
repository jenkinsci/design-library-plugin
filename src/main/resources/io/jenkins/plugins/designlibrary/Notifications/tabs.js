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

const mobileSelectContainer = document.createElement("div");
mobileSelectContainer.className = "jenkins-select jenkins-!-margin-bottom-5 jdl-mobile-only"
const mobileSelect = document.createElement("select");
mobileSelect.className = "jenkins-select__input"
mobileSelectContainer.append(mobileSelect)

if (tabPanes.length <= 4) {
  tabBar.classList.add("jdl-desktop-only")
} else {
  mobileSelectContainer.classList.add("jenkins-hidden")
}

content.insertBefore(mobileSelectContainer, tabPanes[0])

// Add tabs for each tab pane
tabPanes.forEach((tabPane, index) => {
  const tabPaneTitle = tabPane.querySelector(".jenkins-tab-pane__title")
  tabPaneTitle.style.display = "none"

  const tab = document.createElement("div")
  tab.className = "jdl-tabs__tab"
  tab.innerText = tabPaneTitle.textContent

  tabBar.append(tab)

  const option = document.createElement("option");
  option.value = tabPaneTitle.textContent
  option.text = tabPaneTitle.textContent
  option.tab = tab
  mobileSelect.appendChild(option);

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

    mobileSelect.value = tabPaneTitle.textContent
  })

  // Select the first tab
  if (index === 0) {
    tab.click()
  }
})

mobileSelect.addEventListener('change', function() {
  mobileSelect.selectedOptions[0].tab.click()
});

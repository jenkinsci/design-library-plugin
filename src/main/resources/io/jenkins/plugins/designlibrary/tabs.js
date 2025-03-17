document.addEventListener("DOMContentLoaded", () => {
  // Find all the tab groups (each group is a container for tab panes)
  const tabGroups = document.querySelectorAll(".jdl-tab-panes");

  tabGroups.forEach((tabGroup) => {
    const tabPanes = tabGroup.querySelectorAll(".jdl-tab-pane");

    // Hide all tab panes initially
    tabPanes.forEach((tabPane) => {
      tabPane.style.display = "none";
    });

    // Show the first tab pane by default
    tabPanes[0].style.display = "block";

    // Create the tab bar for this tab group
    const tabBar = document.createElement("div");
    tabBar.className = "jdl-component-code__tab-bar";

    // Add tabs for each tab pane in this group
    tabPanes.forEach((tabPane, index) => {
      const tabPaneTitle = tabPane.querySelector(".jdl-tab-pane__title");
      tabPaneTitle.style.display = "none";

      const tab = document.createElement("button");
      tab.className = "tab";

      // Mark the first tab as active
      if (index === 0) {
        tab.classList.add("active");
      }

      // Add click event to each tab
      tab.addEventListener("click", function (e) {
        e.preventDefault();

        // Remove active class from all tabs in this tab group
        tabBar.querySelectorAll(".tab").forEach((tab) => {
          tab.classList.remove("active");
        });
        tab.classList.add("active");

        // Hide all tab panes in this group
        tabPanes.forEach((tabPane) => {
          tabPane.style.display = "none";
        });
        // Show the clicked tab pane
        tabPanes[index].style.display = "block";
      });

      // Set the tab text to the title of the tab pane
      tab.innerText = tabPaneTitle.textContent;

      // Append the tab to the tab bar
      tabBar.append(tab);
    });

    // Insert the tab bar before the first tab pane in this group
    tabGroup.insertBefore(tabBar, tabPanes[0]);
  });
});

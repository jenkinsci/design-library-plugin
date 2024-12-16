document.addEventListener("DOMContentLoaded", () => {

  const url = document.querySelector('head').dataset.rooturl

  document.querySelectorAll('.sample-remote')
    .forEach(element => {
      const uiComponentName = element.dataset.componentName;
      const fileName = element.dataset.sample;
      const executable = element.dataset.executable;

      // On the inputs page the preview markup link adds a hash to the url which breaks the regex extraction
      const fullUrl = `${url}/plugin/design-library/${uiComponentName}/${fileName}`;
      fetch(fullUrl)
        .then(response => response.text())
        .then(text => {
        element.innerText = text

        Prism.highlightElement(element)

        function setPrismBackgroundVariable() {
          const computedStyle = window.getComputedStyle(element.parentElement)
          const background = computedStyle.getPropertyValue('background')

          document.documentElement.style
            .setProperty(prismVariable, background);
        }

        // This is for the copy clipboard section which doesn't use prism
        // We need to match the colour
        const prismVariable = '--prism-background'
        if (!getComputedStyle(document.documentElement).getPropertyValue(prismVariable)) {
          setPrismBackgroundVariable()

          if (window.isSystemRespectingTheme) {
            window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', () => {
              // If done immediately while appearance is changing from light to dark sometimes the wrong value is retrieved
              // A slight delay fixes this
              setTimeout(() => setPrismBackgroundVariable(), 50)
            });
          }
        }

        const codeWrapper = element.closest(".jdl-component-code");
        if (codeWrapper) {
              const copyButton = codeWrapper.querySelector(".copy-button, .jenkins-copy-button")
              copyButton.setAttribute("text", text)
            }
        });
      if (executable === "true") {
        const script = document.createElement("script");  // create a script DOM node
        script.src = fullUrl;  // set its src to the provided URL
        document.head.appendChild(script);
      }
    })

  document.querySelectorAll(".jdl-component-code__expander").forEach((expander) => {
    expander.addEventListener("click", () => {
        expander.closest('.jdl-component-code').classList.toggle("jdl-component-code--minimized");
    })
  });
});

document.addEventListener("DOMContentLoaded", () => {

  const url = document.querySelector('head').dataset.rooturl

  document.querySelectorAll('.sample-remote')
    .forEach(element => {
      const fileName = element.dataset.sample;
      const executable = element.dataset.executable;
      const componentName = window.location.href.match(/.+design-library\/(.+)$/)[1]

      const fullUrl = `${url}/plugin/design-library/${componentName}${fileName}`;
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

          const prismVariable = '--prism-background'
          if (!getComputedStyle(document.documentElement).getPropertyValue(prismVariable)) {
            setPrismBackgroundVariable()

            if (window.isSystemRespectingTheme) {
              window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', event => {
                setPrismBackgroundVariable()
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

  const shareButton = document.querySelector("#button-share");

  if (shareButton) {
    if (!navigator.canShare) {
      shareButton.style.display = "none"
    }

    shareButton
      .addEventListener("click", async (e) => {
        try {
          const shareData = {
            title: document.title,
            text: `Learn about ${document.querySelector("h1").textContent} on Jenkins Design Library`,
            url: document.location.href
          }
          await navigator.share(shareData)
        } catch (error) {
          console.log(error)
        }
      });
  }
});

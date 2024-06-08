document.addEventListener("DOMContentLoaded", () => {

  const url = document.querySelector('head').dataset.rooturl

  document.querySelectorAll('.sample-remote')
    .forEach(element => {
      const fileName = element.dataset.sample;
      const executable = element.dataset.executable;
      const render = element.dataset.render

      // On the inputs page the preview markup link adds a hash to the url which breaks the regex extraction
      const strippedHash = window.location.href.replace('#', '')
      const componentName = strippedHash.match(/.+design-library\/(.+)$/)[1]

      const fullUrl = `${url}/plugin/design-library/${componentName}${fileName}`;
      fetch(fullUrl)
        .then(response => response.text())
        .then(text => {
          if (render === "true") {
            element.innerHTML = text
          } else {
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
                window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', event => {
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

  // 'On this page' side-panel
  function createElementFromHtml(html) {
    const template = document.createElement("template");
    template.innerHTML = html.trim();
    return template.content.firstElementChild;
  }

  const headings = [...document.querySelectorAll("h2")].filter(e => e.checkVisibility());
  const onThisPageList = document.querySelector(".jdl-on-this-page__list");

  headings.forEach(heading => {
    heading.setAttribute("id", heading.textContent);
    const newNode = createElementFromHtml(`<li><a href="#${heading.id}">${heading.textContent}</a></li>`);
    onThisPageList.append(newNode);
  });

  if (headings.length > 1) {
    onThisPageList.parentNode.style.display = 'none';
  }
});

document.addEventListener("DOMContentLoaded", () => {
  function extractLanguageFromClassList(element) {
    return Array.from(element.classList)
      .filter(clazz => clazz.startsWith('language-'))
      .map(clazz => clazz.replace('language-', ''));
  }

  const url = document.querySelector('head').dataset.rooturl

  document.querySelectorAll('.sample-remote')
    .forEach(element => {
      const fileName = element.dataset.sample;
      const componentName = window.location.href.match(/.+design-library\/(.+)$/)[1]

      const fullUrl = `${url}/plugin/design-library/${componentName}${fileName}`;
      fetch(fullUrl)
        .then(response => response.text())
        .then(text => {
          const language = extractLanguageFromClassList(element);
          if (language.length > 0) {
            element.innerHTML = Prism.highlight(text, Prism.languages[language], language.pop())
          } else {
            element.innerHTML = text
          }
          const codeWrapper = element.closest(".jdl-component-code");
          if (codeWrapper) {
            const copyButton = codeWrapper.querySelector(".copy-button, .jenkins-copy-button")
            copyButton.setAttribute("text", text)
          }
        })
    })

  document.querySelectorAll('.language-java,.language-xml,.language-html,.language-css')
    .forEach(element => {
      const language = extractLanguageFromClassList(element);

      if (language.length > 0) {
        element.innerHTML = Prism.highlight(element.innerHTML, Prism.languages[language], language.pop())
      }
    });
});

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

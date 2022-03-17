document.addEventListener("DOMContentLoaded", () =>{
  const url = document.querySelector('h1').dataset.url
  document.querySelectorAll('.sample-remote')
    .forEach(element => {
      const fileName = element.dataset.sample;
      const componentName = window.location.href.match(/.+ui-samples\/(.+)$/)[1]

      const fullUrl = `${url}/plugin/ui-samples-plugin/${componentName}${fileName}`;
      fetch(fullUrl)
        .then(response => response.text())
        .then(text => {
          const language = Array.from(element.classList)
            .filter(clazz => clazz.startsWith('language-'))
            .map(clazz => clazz.replace('language-', ''))
            .pop()

          element.innerHTML = Prism.highlight(text, Prism.languages[language], language)
        })
    })
});

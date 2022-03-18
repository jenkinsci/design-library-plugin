document.addEventListener("DOMContentLoaded", () =>{
  const url = document.querySelector('h1').dataset.url
  document.querySelectorAll('.sample-remote')
    .forEach(element => {
      const fileName = element.dataset.sample;
      const componentName = window.location.href.match(/.+design-library\/(.+)$/)[1]

      const fullUrl = `${url}/plugin/design-library/${componentName}${fileName}`;
      fetch(fullUrl)
        .then(response => response.text())
        .then(text => {
          const language = Array.from(element.classList)
            .filter(clazz => clazz.startsWith('language-'))
            .map(clazz => clazz.replace('language-', ''))

          if (language.length > 0) {
            element.innerHTML = Prism.highlight(text, Prism.languages[language], language)
          } else {
            element.innerHTML = text
          }
        })
    })
});

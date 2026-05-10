document.addEventListener("DOMContentLoaded", () => {
  const url = document.querySelector("head").dataset.rooturl;

  document.querySelectorAll(".callback-button").forEach((element) => {
    let callback = element.dataset.callback;
    element.onclick = () => {
      if (
        callback &&
        window[callback] &&
        typeof window[callback] === "function"
      ) {
        window[callback]();
      } else {
        console.warn(`Callback function ${callback} is not defined`);
      }
    };
  });

  document
    .querySelectorAll(".jdl-component-code__expander")
    .forEach((expander) => {
      expander.addEventListener("click", () => {
        expander
          .closest(".jdl-component-code")
          .classList.toggle("jdl-component-code--minimized");
      });
    });
});

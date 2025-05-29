const i18n = document.getElementById("plugin-i18n");

const longText = i18n.dataset.longtext;
const hello = i18n.dataset.hello;

console.log(longText);
console.log(hello.replace("{0}", "World!"));

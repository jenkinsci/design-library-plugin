const response = await fetch(
  `${JENKINS_URL}/i18n/resourceBundle?baseName=io.jenkins.plugins.designlibrary.localize`,
);
const json = await response.json();
const i18n = json.data;

const localization = i18n["How to localize texts in Jelly"];
const longText = i18n.longtext;
const hello = i18n.hello;

console.log(localization);
console.log(longText);
console.log(hello.replace("{0}", "World!"));

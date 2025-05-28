const i18n = await (
  await fetch(
    `${JENKINS_URL}/i18n/resourceBundle?baseName=package.path.to.generated.class`,
  )
).json();

const localization = i18n["How to localize texts in Jelly"];
const longText = i18n.longtext;
const hello = i18n.hello;

console.log(localization);
console.log(longText);
console.log(hello.replace("{0}", "World!"));

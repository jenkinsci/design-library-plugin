let type, position, amount;
const valueElement = document.querySelector("[data-type='value'] span");
const marginElement = document.querySelector("[data-type='margin']");
const paddingElement = document.querySelector("[data-type='padding']");
const typeElement = document.querySelector("[data-type='type']");
const positionElement = document.querySelector("[data-type='position']");
const amountElement = document.querySelector("[data-type='amount']");

typeElement.addEventListener("change", e => { type = e.target.value; onChange() });
positionElement.addEventListener("change", e => { position = e.target.value; onChange() });
amountElement.addEventListener("change", e => { amount = e.target.value; onChange() });
typeElement.dispatchEvent(new Event("change"));
positionElement.dispatchEvent(new Event("change"));
amountElement.dispatchEvent(new Event("change"));

function onChange() {
    if (!type || !position || !amount) {
        return;
    }

    const positionPart = position === 'all' ? '' : `-${position}`;

    if (type === 'margin') {
        marginElement.className = 'jdl-margin-box ' + `jenkins-!-padding${positionPart}-${amount}`;
        paddingElement.className = '';
    } else {
        marginElement.className = '';
        paddingElement.className = 'jdl-padding-box ' + `jenkins-!-padding${positionPart}-${amount}`;
    }

    valueElement.textContent = `.jenkins-!-${type}${positionPart}-${amount}`;
}

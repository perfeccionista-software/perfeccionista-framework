// noinspection BadExpressionStatementJS
(async (element, options) => {

    debugger;

    return isSelected(element);

});

function isSelected(element) {
    if (!isSelectable(element)) {
        return false;
    }
    let propertyName = 'selected';
    let type = element.type && element.type.toLowerCase();
    if ('checkbox' === type || 'radio' === type) {
        propertyName = 'checked';
    }
    return !!element[propertyName];
}

function isSelectable(element) {
    if (isElement(element, 'OPTION')) {
        return true;
    }
    if (isElement(element, 'INPUT')) {
        let type = element.type.toLowerCase();
        return type === 'checkbox' || type === 'radio';
    }
    return false;
}

function isElement(node, opt_tagName) {
    if (opt_tagName && (typeof opt_tagName !== 'string')) {
        opt_tagName = opt_tagName.toString();
    }
    return !!node && node.nodeType === 1 && (!opt_tagName || node.tagName.toUpperCase() === opt_tagName);
}
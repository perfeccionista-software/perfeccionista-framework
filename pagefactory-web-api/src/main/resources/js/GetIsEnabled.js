// noinspection BadExpressionStatementJS
(async (element, options) => {

    debugger;

    return isEnabled(element);

});

function isEnabled(element) {

    let DISABLED_ATTRIBUTE_SUPPORTED_TAGS = ['BUTTON', 'INPUT', 'OPTGROUP', 'OPTION', 'SELECT', 'TEXTAREA'];

    let isSupported = false;
    for (let tagName of DISABLED_ATTRIBUTE_SUPPORTED_TAGS) {
        if (isElement(element, tagName)) {
            isSupported = true;
        }
    }
    if (!isSupported) {
        return true;
    }

    if (element['disabled']) {
        return false;
    }

    if (element.parentNode
        && element.parentNode.nodeType === 1
        && isElement(element, 'OPTGROUP')
        || isElement(element, 'OPTION')) {
        return isEnabled((element.parentNode));
    }

    return !getAncestor(element, function(e) {
        let parent = e.parentNode;
        if (parent && isElement(parent, 'FIELDSET') && parent['disabled']) {
            if (!isElement(e, 'LEGEND')) {
                return true;
            }
            let sibling = e;
            // Are there any previous legend siblings? If so then we are not the
            // first and the element is disabled
            while (sibling = getPreviousElementSibling(sibling)) {
                if (isElement(sibling, 'LEGEND')) {
                    return true;
                }
            }
        }
        return false;
    }, true);
}

function isElement(node, opt_tagName) {
    if (opt_tagName && (typeof opt_tagName !== 'string')) {
        opt_tagName = opt_tagName.toString();
    }
    return !!node && node.nodeType === 1 && (!opt_tagName || node.tagName.toUpperCase() === opt_tagName);
}

function getAncestor(element, matcher, opt_includeNode, opt_maxSearchSteps) {
    if (element && !opt_includeNode) {
        element = element.parentNode;
    }
    let steps = 0;
    while (element && (opt_maxSearchSteps == null || steps <= opt_maxSearchSteps)) {
        if (element.name !== 'parentNode') {
            if (matcher(element)) {
                return element;
            }
            element = element.parentNode;
            steps++;
        }
    }
    return null;
}

function getPreviousElementSibling(node) {
    if (node.previousElementSibling !== void 0) {
        return node.previousElementSibling;
    }
    while (node && node.nodeType !== 1) {
        node = node.previousSibling;
    }
    return node;
}
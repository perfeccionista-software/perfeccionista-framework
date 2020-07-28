// noinspection BadExpressionStatementJS
(async (element, options) => {

    debugger;

    let target = element.getClientRects()[0];
    let result = {
        pageX: target.left,
        pageY: target.top,
        absolutePageX: window.pageXOffset + target.left,
        absolutePageY: window.pageYOffset + target.top,
        elementCenterX: target.left + element.offsetLeft + element.offsetWidth / 2,
        elementCenterY: target.top + element.offsetTop + element.offsetHeight / 2
    }
    return JSON.stringify(result);

});
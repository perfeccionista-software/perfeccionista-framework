// noinspection BadExpressionStatementJS
(async (element, options) => {

    debugger;

    let target = element.getClientRects()[0];
    let result = {
        pageX: target.left,
        pageY: target.top,
        absolutePageX: window.pageXOffset + target.left,
        absolutePageY: window.pageYOffset + target.top
    }
    return JSON.stringify(result);

});
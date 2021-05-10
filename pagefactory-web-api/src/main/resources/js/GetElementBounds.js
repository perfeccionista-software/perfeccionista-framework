// noinspection BadExpressionStatementJS
(async (element, options) => {

    debugger;

    let target = element.getClientRects()[0];
    let result = {
        width: target.width,
        height: target.height,

        screenLeft: target.left,
        screenTop: target.top,

        absoluteLeft: window.pageXOffset + target.left,
        absoluteTop: window.pageYOffset + target.top,

        centerX: target.left + element.offsetLeft + element.offsetWidth / 2,
        centerY: target.top + element.offsetTop + element.offsetHeight / 2
    }
    return JSON.stringify(result);

});

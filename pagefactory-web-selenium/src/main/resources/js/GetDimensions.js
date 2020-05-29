// noinspection BadExpressionStatementJS
(async (element, options) => {

    debugger;

    let target = element.getClientRects()[0];
    let result = {
        width: target.width,
        height: target.height
    }
    return JSON.stringify(result);

});
// noinspection BadExpressionStatementJS
(async (element, options) => {

    debugger;
    IncorrectSearchQueryError.prototype = Object.create(Error.prototype);
    IncorrectSearchQueryError.prototype.constructor = IncorrectSearchQueryError;

    let cssProperty = options.cssProperty;
    if (cssProperty === undefined || cssProperty === null) {
        throw new IncorrectSearchQueryError('CssProperty for extracting color is not declared');
    }

    let color = window.getComputedStyle(element)[cssProperty];
    if (color === undefined || color === null) {
        throw new IncorrectSearchQueryError("Can't extract color value for cssProperty " + cssProperty);
    }
    let colorComponents = color.match(/[0-9.]+/gi);
    if (colorComponents.length === 3) {
        return JSON.stringify({
            r: colorComponents[0],
            g: colorComponents[1],
            b: colorComponents[2],
            alpha: 1
        });
    }
    if (colorComponents.length === 4) {
        return JSON.stringify({
            r: colorComponents[0],
            g: colorComponents[1],
            b: colorComponents[2],
            alpha: colorComponents[3]
        });
    }
    throw new IncorrectSearchQueryError("Can't extract color value for cssProperty " + cssProperty);
});

function IncorrectSearchQueryError(message) {
    this.name = 'IncorrectSearchQueryError';
    this.message = message;
    if (Error.captureStackTrace) {
        Error.captureStackTrace(this, this.constructor);
    } else {
        this.stack = (new Error()).stack;
    }
}
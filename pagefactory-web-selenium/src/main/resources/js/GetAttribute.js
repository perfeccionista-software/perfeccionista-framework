// noinspection BadExpressionStatementJS
(async (element, options) => {

    debugger;

    let attributeName = options.attribute;
    if (attributeName === undefined || attributeName === null) {
        IncorrectSearchQueryError.prototype = Object.create(Error.prototype);
        IncorrectSearchQueryError.prototype.constructor = IncorrectSearchQueryError;
        throw new IncorrectSearchQueryError('Attribute for extracting is not declared');
    }
    return element.getAttribute(attributeName);

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
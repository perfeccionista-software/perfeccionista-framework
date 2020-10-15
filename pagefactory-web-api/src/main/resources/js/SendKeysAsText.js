// noinspection BadExpressionStatementJS
(async (element, options) => {

    debugger;

    let valueToInput = options.value;
    let delay = options.delay;

    if (valueToInput === undefined || valueToInput === null) {
        IncorrectSearchQueryError.prototype = Object.create(Error.prototype);
        IncorrectSearchQueryError.prototype.constructor = IncorrectSearchQueryError;
        throw new IncorrectSearchQueryError('Value to input is not declared');
    }

    if (delay === undefined || delay === null) {
        IncorrectSearchQueryError.prototype = Object.create(Error.prototype);
        IncorrectSearchQueryError.prototype.constructor = IncorrectSearchQueryError;
        throw new IncorrectSearchQueryError('Delay for input sequences is not declared');
    }

    if (delay === 0) {
        element.value = element.value + valueToInput;
        return;
    }

    for (let charToInput of Array.from(valueToInput)) {
        element.value = element.value + charToInput;
        let promise = new Promise(resolve => setTimeout(resolve, delay));
        await promise;
    }

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
// noinspection BadExpressionStatementJS
(async (element, options) => {

    debugger;

    let completely = options.completely;
    if (completely === undefined || completely === null) {
        IncorrectSearchQueryError.prototype = Object.create(Error.prototype);
        IncorrectSearchQueryError.prototype.constructor = IncorrectSearchQueryError;
        throw new IncorrectSearchQueryError('Required attribute "completely" is not declared');
    }

    let screenWidth = window.innerWidth;
    let screenHeight = window.innerHeight;

    let target = element.getClientRects()[0];

    let checkTopBorder = target.top > 0 && target.top < screenHeight;
    let checkRightBorder = (target.left + target.width) > 0 && (target.left + target.width) < screenWidth;
    let checkBottomBorder = (target.top + target.height) > 0 && (target.top + target.height) < screenHeight;
    let checkLeftBorder = target.left > 0 && target.left < screenWidth;

    let result;
    if (completely) {
        result = checkTopBorder && checkRightBorder && checkBottomBorder && checkLeftBorder;
    } else {
        result = (checkTopBorder || checkBottomBorder) && (checkLeftBorder || checkRightBorder);
    }

    return result;
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
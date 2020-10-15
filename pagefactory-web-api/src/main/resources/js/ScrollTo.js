// TODO: Перенести сюда логику скролла из селениума,
//  которая ищет родительский элемент и скроллит относительно него
// noinspection BadExpressionStatementJS
(async (element, options) => {

    debugger;

    IncorrectSearchQueryError.prototype = Object.create(Error.prototype);
    IncorrectSearchQueryError.prototype.constructor = IncorrectSearchQueryError;

    if (options === undefined || options === null) {
        throw new IncorrectSearchQueryError('Options for scrolling is not declared');
    }

    let scrollRoot = options.scrollRoot;
    checkRequiredOption(scrollRoot, 'scrollRoot');
    let topIndent = options.topIndent;
    checkRequiredOption(topIndent, 'topIndent');
    let rightIndent = options.rightIndent;
    checkRequiredOption(rightIndent, 'rightIndent');
    let bottomIndent = options.bottomIndent;
    checkRequiredOption(bottomIndent, 'bottomIndent');
    let leftIndent = options.leftIndent;
    checkRequiredOption(leftIndent, 'leftIndent');
    let delay = options.delay;

    let target = element.getClientRects()[0];
    let y = target.top + target.height / 2;
    if (y < topIndent || y > window.innerHeight - bottomIndent) {
        eval(scrollRoot).scrollTop += y - window.innerHeight / 2;
    }
    let x = target.left + target.width / 2;
    if (x < leftIndent || x > window.innerWidth - rightIndent) {
        eval(scrollRoot).scrollLeft += x - window.innerWidth / 2;
    }

    if (delay !== undefined || delay) {
        let promise = new Promise(resolve => setTimeout(resolve, delay));
        await promise;
    }

});

function checkRequiredOption(option, optionName) {
    if (option === undefined || option === null) {
        throw new IncorrectSearchQueryError('Required option ' + optionName + ' is not declared');
    }
}

function IncorrectSearchQueryError(message) {
    this.name = 'IncorrectSearchQueryError';
    this.message = message;
    if (Error.captureStackTrace) {
        Error.captureStackTrace(this, this.constructor);
    } else {
        this.stack = (new Error()).stack;
    }
}

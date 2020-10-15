// noinspection BadExpressionStatementJS
(async (element, options) => {

    debugger;

    // TODO: Тут все сложно.
    //  Нужно как-то передавать отдельно:
    //   - события нажатия и удерживания клавиш
    //   - событие нажатия клавиши
    //   - событие отпускания клавиши
    //  делать проверку того, что в конце все клавиши отпущены
    //  клавиши нужно нажимать через указанную задержку (delay)
    //  valuesToInput - массив чаров или событий изменения состояний клавиш

    let events = options.events;

    if (events === undefined || events === null) {
        IncorrectSearchQueryError.prototype = Object.create(Error.prototype);
        IncorrectSearchQueryError.prototype.constructor = IncorrectSearchQueryError;
        throw new IncorrectSearchQueryError('Value to input is not declared');
    }

    let keyboardState = {
        alt: false,
        ctrl: false,
        meta: false,
        shift: false,
    }

    for (let event of events) {
        let keyCode = event.keyCode;
        if (isModifierKey(keyCode)) {



        } else {



        }
        if (event.delay !== 0) {
            let promise = new Promise(resolve => setTimeout(resolve, event.delay));
            await promise;
        }
    }





    element.value = element.value + valueToInput;
    return element.value;

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

function isModifierKey(keyCode) {
    // LeftShift/RightShift or LeftControl/RightControl or LeftAlt/RightAlt or Meta/Command
    return keyCode === '\uE008' || keyCode === '\uE009' || keyCode === '\uE00A' || keyCode === '\uE03D';
}

function processModifierKey()

function setInitialKeyboardState()
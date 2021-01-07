// noinspection BadExpressionStatementJS
(async (element, options) => {

    debugger;

    let logLevel = options.logLevel;
    let valueToInput = options.valueToInput;

    switch (logLevel) {
        case 'SEVERE':
            console.error(valueToInput)
            break;
        case 'WARNING':
            console.warn(valueToInput);
            break;
        case 'INFO':
            console.info(valueToInput);
            break;
        case 'CONFIG':
        case 'FINE':
            console.debug(valueToInput);
            break;
        case 'FINER':
        case 'FINEST':
            console.trace(valueToInput);
            break;
        case 'OFF':
        default:
            console.log(valueToInput);
            break;
    }

    // return window.console.log(valueToInput);

});

// noinspection JSAnnotator,JSReferencingArgumentsOutsideOfFunction,JSUnresolvedVariable
return evaluate(arguments);

function evaluate(arguments) {
    let executeOperation = eval(window.localStorage.getItem('perfeccionista.js.selenium.ExecuteOperation'));
    return executeOperation(JSON.parse(arguments[0]));
}
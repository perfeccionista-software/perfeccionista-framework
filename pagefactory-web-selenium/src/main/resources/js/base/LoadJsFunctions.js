// noinspection JSReferencingArgumentsOutsideOfFunction,JSUnresolvedVariable
evaluate(arguments);

function evaluate(arguments) {
    let scriptsToLoad = JSON.parse(arguments[0]).scriptsToLoad;
    for (let scriptToLoad of scriptsToLoad) {
        window.localStorage.setItem(scriptToLoad.scriptName, scriptToLoad.scriptContent.join('\n'));
    }
}
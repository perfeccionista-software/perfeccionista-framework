// noinspection BadExpressionStatementJS
(async (operation) => {

    debugger;

    // Errors
    IncorrectSearchQueryError.prototype = Object.create(Error.prototype);
    IncorrectSearchQueryError.prototype.constructor = IncorrectSearchQueryError;
    ElementSearchError.prototype = Object.create(Error.prototype);
    ElementSearchError.prototype.constructor = ElementSearchError;
    ElementStateError.prototype = Object.create(Error.prototype);
    ElementStateError.prototype.constructor = ElementStateError;
    FunctionCallError.prototype = Object.create(Error.prototype);
    FunctionCallError.prototype.constructor = FunctionCallError;

    // EnvironmentBlock
    let withLogs = operation.withLogs !== undefined;
    let withOuterHtml = operation.withOuterHtml !== undefined;
    let dateTimeFormatter = new Intl.DateTimeFormat('ru', {
        day: 'numeric',
        month: '2-digit',
        year: 'numeric',
        hour: 'numeric',
        minute: 'numeric',
        second: 'numeric'
    });
    let evaluatedFunctions = new Map();

    // ResultBlock
    let searchHistory = [];
    let values = [];
    let logs = [];
    let error;
    let errorAttachments = [];

    try {
        addLogEntry('INFO', 'Operation started');
        await executeOperation(operation);
        addLogEntry('INFO', 'Operation finished');
    } catch (e) {
        error = {
            name: e.name,
            message: e.message,
            stackTrace: e.stack,
            attachments: errorAttachments
        }
        addLogEntry('INFO', 'Operation finished with error');
    }

    return createResult(searchHistory, values, error, logs);

    /**
     * Непосредственное выполнение операции.
     * @param operation
     */
    async function executeOperation(operation) {
        let locators = operation.locatorChain.locators;
        let endpointFunction = operation.endpointFunction;
        let locatorProcessingResult = {
            elementEntries : [
                {element : document.documentElement}
            ],
            invokeOnCallFunctions : []
        };
        for (let locator of locators) {
            locatorProcessingResult = await processLocator(locatorProcessingResult, locator);
            if (locatorProcessingResult.elementEntries.length === 0) {
                addLogEntry('WARN', 'No results found for locator: ' + JSON.stringify(locator));
                return;
            }
        }
        await executeEndpointFunction(locatorProcessingResult, endpointFunction);
    }

    /**
     * Обрабатываем поочередно переданную цепочку локаторов.
     * @param previousLocatorProcessingResult
     * @param locator
     */
    async function processLocator(previousLocatorProcessingResult, locator) {
        addLogEntry('DEBUG', 'Process locator: ' + JSON.stringify(locator));
        checkLocator(locator);
        let singleParent = previousLocatorProcessingResult.elementEntries.length === 1;
        let singleChild = locator.single;
        let locatorProcessingResult = {
            invokeOnCallFunctions: locator.invokeOnCallFunctions
        }
        let foundElementEntries;

        if (singleParent && singleChild) {
            foundElementEntries = await findSingleFromSingle(previousLocatorProcessingResult, locator);
        } else if (singleParent && !singleChild) {
            foundElementEntries = await findMultipleFromSingle(previousLocatorProcessingResult, locator);
        } else if (!singleParent && singleChild) {
            foundElementEntries = await findSingleFromMultiple(previousLocatorProcessingResult, locator);
        } else {
            throw new IncorrectSearchQueryError('Locator chain can contain only one multiple locator');
        }

        locatorProcessingResult.elementEntries = foundElementEntries;
        return locatorProcessingResult;
    }

    /**
     * Ищем один дочерний элемент от одного родительского элемента
     * @param previousLocatorProcessingResult
     * @param locator
     * @return {{element: *}[]|*[]}
     */
    async function findSingleFromSingle(previousLocatorProcessingResult, locator) {
        let parentElementEntry = previousLocatorProcessingResult.elementEntries[0];
        let parentElement = parentElementEntry.element;
        let parentFunctionInvocations = previousLocatorProcessingResult.invokeOnCallFunctions;
        if (parentFunctionInvocations !== undefined && parentFunctionInvocations.length > 0) {
            await executeInvokeOnCallFunctions(parentElement, parentFunctionInvocations);
        }
        let foundElements = findElements(parentElement, locator);
        let foundElementEntries = [];
        let size = foundElements.length;
        if (size === 0) {
            if (locator.strictSearch) {
                addLocatorErrorAttachment(locator);
                addParentElementErrorAttachment(parentElement);
                throw new ElementSearchError('No elements found');
            } else {
                addSearchHistoryEntry(locator, foundElementEntries);
                return foundElementEntries;
            }
        }
        if (size > 1) {
            let elementIndex = locator.index;
            if (elementIndex === undefined || elementIndex === null) {
                addLocatorErrorAttachment(locator);
                addParentElementErrorAttachment(parentElement);
                addChildElementErrorAttachments(foundElements);
                throw new ElementSearchError('More than one element found');
            }
            if (elementIndex >= size) {
                addLocatorErrorAttachment(locator);
                addParentElementErrorAttachment(parentElement);
                addChildElementErrorAttachments(foundElements);
                throw new ElementSearchError('No element with index ' + elementIndex + ' found');
            }
            let elementEntry = createElementEntry(foundElements[elementIndex], locator);
            elementEntry.index = elementIndex;
            foundElementEntries.push(elementEntry);
            addSearchHistoryEntry(locator, foundElementEntries);
            return foundElementEntries;
        }
        foundElementEntries.push(createElementEntry(foundElements[0], locator));
        addSearchHistoryEntry(locator, foundElementEntries);
        return foundElementEntries;
    }

    /**
     * Ищем несколько дочерних элементов от одного родительского
     * @param previousLocatorProcessingResult
     * @param locator
     * @return {[]|*[]}
     */
    async function findMultipleFromSingle(previousLocatorProcessingResult, locator) {
        let parentElementEntry = previousLocatorProcessingResult.elementEntries[0];
        let parentElement = parentElementEntry.element;
        let parentFunctionInvocations = previousLocatorProcessingResult.invokeOnCallFunctions;
        if (undefined !== parentFunctionInvocations && parentFunctionInvocations.length > 0) {
            await executeInvokeOnCallFunctions(parentElement, parentFunctionInvocations);
        }
        let foundElements = findElements(parentElement, locator);
        let foundElementEntries = [];
        let size = foundElements.length;
        if (size === 0) {
            if (locator.strictSearch) {
                addLocatorErrorAttachment(locator);
                addParentElementErrorAttachment(parentElement);
                throw new ElementSearchError('No elements found');
            } else {
                addSearchHistoryEntry(locator, foundElementEntries);
                return foundElementEntries;
            }
        }
        if (locator.indexes.length === 0) {
            for (let i = 0; i < foundElements.length; i++) {
                let elementEntry = createElementEntry(foundElements[i], locator);
                elementEntry.index = i;
                foundElementEntries.push(elementEntry);
            }
        } else {
            for (let elementIndex of locator.indexes) {
                if (elementIndex >= size) {
                    addLocatorErrorAttachment(locator);
                    addParentElementErrorAttachment(parentElement);
                    addChildElementErrorAttachments(foundElements);
                    throw new ElementSearchError('No element with index ' + elementIndex + ' found');
                }
                let elementEntry = createElementEntry(foundElements[elementIndex], locator);
                elementEntry.index = elementIndex;
                foundElementEntries.push(elementEntry);
            }
        }
        addSearchHistoryEntry(locator, foundElementEntries);
        return foundElementEntries;
    }

    /**
     * Ищем по одному дочернему элементу от каждого родительского
     * @param previousLocatorProcessingResult
     * @param locator
     * @return {[]}
     */
    async function findSingleFromMultiple(previousLocatorProcessingResult, locator) {
        let parentElementEntries = previousLocatorProcessingResult.elementEntries;
        let foundElementEntries = [];
        for (let parentElementEntry of parentElementEntries) {
            let parentElement = parentElementEntry.element;
            let parentFunctionInvocations = previousLocatorProcessingResult.invokeOnCallFunctions;
            if (undefined !== parentFunctionInvocations && parentFunctionInvocations.length > 0) {
                await executeInvokeOnCallFunctions(parentElement, parentFunctionInvocations);
            }
            let elementIndex = parentElementEntry.index;
            let foundElements = findElements(parentElement, locator);
            let size = foundElements.length;
            if (size === 0) {
                if (locator.strictSearch) {
                    addLocatorErrorAttachment(locator);
                    addParentElementErrorAttachment(parentElement);
                    throw new ElementSearchError('No elements found');
                } else {
                    let elementEntry = createElementEntry(null, locator);
                    elementEntry.index = elementIndex;
                    foundElementEntries.push(elementEntry);
                    continue;
                }
            }
            if (size > 1) {
                if (elementIndex === undefined || elementIndex === null) {
                    addLocatorErrorAttachment(locator);
                    addParentElementErrorAttachment(parentElement);
                    addChildElementErrorAttachments(foundElements);
                    throw new ElementSearchError('More than one element found');
                }
                if (elementIndex >= size) {
                    addLocatorErrorAttachment(locator);
                    addParentElementErrorAttachment(parentElement);
                    addChildElementErrorAttachments(foundElements);
                    throw new ElementSearchError('No element with index ' + elementIndex + ' found');
                }
                let elementEntry = createElementEntry(foundElements[elementIndex], locator);
                elementEntry.index = elementIndex;
                foundElementEntries.push(elementEntry);
            }
            let elementEntry = createElementEntry(foundElements[0], locator);
            elementEntry.index = elementIndex;
            foundElementEntries.push(elementEntry);
        }
        addSearchHistoryEntry(locator, foundElementEntries);
        return foundElementEntries;
    }

    /**
     * Ищем все элементы по переданному локатору от родительского элемента.
     * Если в локаторе установлен признак поиска только дочерних элементов,
     * то фильтруем все элементы, которые не принадлежат родительскому элементу
     * @param parentElement
     * @param locator
     * @return {HTMLElement[]|[]}
     */
    function findElements(parentElement, locator) {
        let foundElements = [];
        if (parentElement === null) {
            return foundElements;
        }
        switch (locator.locatorStrategy) {
            case 'id':
                foundElements = [document.getElementById(locator.locatorValue)];
                break;
            case 'css':
                foundElements = collectionToArray(parentElement.querySelectorAll(locator.locatorValue));
                break;
            case 'xpath':
                foundElements = findElementsByXpath(parentElement, locator.locatorValue);
                break;
            case 'className':
                foundElements = collectionToArray(parentElement.getElementsByClassName(locator.locatorValue));
                break;
            case 'tagName':
                foundElements = collectionToArray(parentElement.getElementsByTagName(locator.locatorValue));
                break;
            case 'name' :
                foundElements = collectionToArray(parentElement.getElementsByName(locator.locatorValue));
                break;
            case 'text':
                let textXpathValue = './/*[text()="' + locator.locatorValue + '"]';
                foundElements = findElementsByXpath(parentElement, textXpathValue);
                break;
            case 'containsText':
                let partialTextXpathValue = './/*[contains(text(), "' + locator.locatorValue + '")]';
                foundElements = findElementsByXpath(parentElement, partialTextXpathValue);
                break;
            default:
                throw new IncorrectSearchQueryError('Locator strategy ' + locator.locatorStrategy + ' not found');
        }
        if (foundElements.length === 0) {
            return foundElements;
        }
        // Убираем те элементы, которые не принадлежат родительскому узлу
        let foundElementsWithinParent = [];
        if (locator.onlyWithinParent) {
            for (let foundElement of foundElements) {
                if (parentElement.contains(foundElement)) {
                    foundElementsWithinParent.push(foundElement);
                }
            }
            addLogEntry('DEBUG', 'Elements found: ' + foundElementsWithinParent.length + '. OnlyWithinParent = true');
        } else {
            addLogEntry('DEBUG', 'Elements found: ' + foundElementsWithinParent.length + '. OnlyWithinParent = false');
            foundElementsWithinParent = foundElements;
        }
        return foundElementsWithinParent;

        function collectionToArray(htmlCollection) {
            let elements = [];
            for (let i = 0; i < htmlCollection.length; i++) {
                elements.push(htmlCollection[i]);
            }
            return elements;
        }
    }

    /**
     * Ищем элементы по xpath
     * @param parentElement
     * @param xpath
     * @return {[]}
     */
    function findElementsByXpath(parentElement, xpath) {
        let itemIterator;
        try {
            itemIterator = document.evaluate(xpath, parentElement, null, XPathResult.ORDERED_NODE_ITERATOR_TYPE, null);
        } catch {
            addLogEntry('WARN', 'No elements found by XPath: ' + xpath);
            itemIterator = null;
        }
        let foundItems = [];
        if (itemIterator === undefined || itemIterator === null) {
            return foundItems;
        }
        let processedItem = itemIterator.iterateNext();
        while (processedItem != null) {
            foundItems.push(processedItem);
            processedItem = itemIterator.iterateNext();
        }
        return foundItems;
    }

    // Вызов функций

    /**
     * Получаем объект вызываемой функции из localStorage или из локального хранилища,
     * если данная функция вызывалась хотя бы один раз до этого в рамках этой операции.
     * @param name
     * @returns {any}
     */
    function getStoredFunction(name) {
        if (evaluatedFunctions.has(name)) {
            return evaluatedFunctions.get(name);
        }
        let functionText = window.localStorage.getItem(name);
        if (functionText === undefined || functionText === null) {
            throw new FunctionCallError('Function with name ' + name + ' not found');
        }
        addLogEntry('DEBUG', 'Evaluate function ' + name + ' from local storage');
        let evaluatedFunction = eval(functionText);
        evaluatedFunctions.set(name, evaluatedFunction);
        return evaluatedFunction;
    }

    /**
     * Вызываем функцию с аргументами для элемента
     * @param element
     * @param functionInvocation
     */
    async function executeStoredFunctionForElement(element, functionInvocation) {
        let functionObject = getStoredFunction(functionInvocation.name);
        let functionParameters = functionInvocation.options;
        return await functionObject(element, functionParameters);
    }

    /**
     * Выполняем конечную функцию извлечения значений из найденных элементов (или выполнения с ними каких-то действий)
     * @param previousResult
     * @param endpointFunction
     * @return Nothing. Result appends to values.
     */
    async function executeEndpointFunction(previousResult, endpointFunction) {
        let invokeOnCallFunctions = previousResult.invokeOnCallFunctions;
        for (let elementEntry of previousResult.elementEntries) {
            await executeEndpointFunctionForElement(elementEntry, invokeOnCallFunctions, endpointFunction);
        }
    }

    /**
     * Выполняем конечную функцию для элемента. Перед конечной функцией выполняются промежуточные функции,
     * заданные для локатора. Функции выполняются последовательно. В каждой функции можно использовать свой таймаут,
     * однако, это не желательно.
     * @param elementEntry
     * @param invokeOnCallFunctions
     * @param endpointFunction
     * @return {Promise<void>}
     */
    async function executeEndpointFunctionForElement(elementEntry, invokeOnCallFunctions, endpointFunction) {
        let element = elementEntry.element;
        // TODO: Если правильно работает, то можно написать изящнее
        if (element === undefined || element === null) {
            let resultEntry = {
                value: null
            }
            if (elementEntry.index !== undefined && elementEntry.index !== null) {
                resultEntry.index = elementEntry.index;
            }
            values.push(resultEntry);
        }
        if (element !== undefined && element !== null) {
            if (invokeOnCallFunctions !== undefined && invokeOnCallFunctions.length > 0) {
                await executeInvokeOnCallFunctions(element, invokeOnCallFunctions);
            }
            addLogEntry('DEBUG', 'Execute endpoint function: ' + endpointFunction.name + ' for element: ' + element.outerHTML);
            let result = await executeStoredFunctionForElement(elementEntry.element, endpointFunction);
            if (result !== undefined && result !== null) {
                let resultEntry = {
                    value: result
                }
                if (elementEntry.index !== undefined && elementEntry.index !== null) {
                    resultEntry.index = elementEntry.index;
                }
                values.push(resultEntry);
            }
        }
    }

    /**
     * Вызываем все функции для элемента. Вызов этой функции ничего не возвращает
     * @param element
     * @param functionInvocations
     * @return {Promise<void>}
     */
    async function executeInvokeOnCallFunctions(element, functionInvocations) {
        for (let functionInvocation of functionInvocations) {
            addLogEntry('DEBUG', 'Execute invokeOnCall function: ' + functionInvocation.name + ' for element: ' + element.outerHTML);
            await executeStoredFunctionForElement(element, functionInvocation);
        }
    }

    // Checks and utils

    /**
     * Создаем запись с данными найденного элемента.
     * Проверяем хэш, если нужно, считаем хэш, если нужно
     * @param element
     * @param locator
     * @return {{element: *}}
     */
    function createElementEntry(element, locator) {
        let elementEntry = {
            element: element
        }
        let expectedHash = locator.expectedHash;
        if (expectedHash !== undefined && expectedHash !== null) {
            checkExpectedHash(element, expectedHash);
            elementEntry.hashCorrect = true;
        }
        if (locator.calculateHash) {
            elementEntry.hash = calculateHash(element);
        }
        return elementEntry;
    }

    /**
     * Создаем объект ответа
     * @param searchHistory
     * @param values
     * @param error
     * @param logs
     */
    function createResult(searchHistory, values, error, logs) {
        validateValues(values);
        let result = {
            searchHistory: JSON.stringify(searchHistory),
            values: values
        }
        if (error !== undefined && error !== null) {
            result.error = JSON.stringify(error);
        }
        if (withLogs) {
            result.logs = JSON.stringify(logs);
        }
        if (withOuterHtml) {
            result.outerHtml = document.documentElement.outerHTML;
        }
        return result;
    }

    /**
     * Валидируем полученный результат на отсутствие дубликатов индексов
     * @param values
     */
    function validateValues(values) {
        if (values.length === 0 || values.length === 1) {
            return;
        }
        let indexes = new Set();
        for (let value of values) {
            let index = value.index;
            if (index === undefined || index === null) {
                throw new ElementSearchError('Value doesn\'t have an index');
            }
            if (indexes.has(index)) {
                throw new ElementSearchError('Value has a duplicate index');
            }
            indexes.add(index);
        }
    }

    /**
     * Проверяем локатор на корректность его заполнения
     * @param locator
     */
    function checkLocator(locator) {
        if (locator.locatorId === undefined || locator.locatorId === null) {
            throw new IncorrectSearchQueryError('LocatorId is not declared');
        }
        if (locator.locatorComponent === undefined || locator.locatorComponent === null) {
            throw new IncorrectSearchQueryError('LocatorComponent is not declared');
        }
        if (locator.locatorStrategy === undefined || locator.locatorStrategy === null) {
            throw new IncorrectSearchQueryError('LocatorStrategy is not declared');
        }
        if (locator.locatorValue === undefined || locator.locatorValue === null) {
            throw new IncorrectSearchQueryError('LocatorValue is not declared');
        }
        // TODO: тут будут дополнительные проверки
    }

    /**
     * Проверяем хэш узла
     * @param element
     * @param expectedHash
     */
    function checkExpectedHash(element, expectedHash) {
        addLogEntry('DEBUG', 'Check expected hash ' + expectedHash + ' for element ' + element.outerHTML);
        let actualHash = calculateHash(element);
        if (expectedHash !== actualHash) {
            throw new ElementStateError('Element hash mismatch. Expected hash is:' + expectedHash + '. Actual hash is: ' + actualHash);
        }
    }

    /**
     * Рассчитываем хэш для элемента на основе его содержимого и атрибутов.
     * @param element
     * @return {string}
     */
    function calculateHash(element) {
        addLogEntry('DEBUG', 'Calculate hash for element ' + element.outerHTML);
        let hashLength = 0x20;
        let hash = new Array(hashLength).fill(0x0);
        evaluateChildElementHash(element, hash, 0, hashLength);
        let calculatedHash = convertToChars(hash);
        addLogEntry('DEBUG', 'Calculated hash is ' + calculatedHash);
        return calculatedHash;

        function evaluateChildElementHash(node, hash, pointer, hashLength) {
            pointer = appendStringToHash(node.tagName, hash, pointer, hashLength);
            for (let attribute of node.attributes) {
                pointer = appendStringToHash(attribute.value, hash, pointer, hashLength);
            }
            pointer = appendStringToHash(node.textContent, hash, pointer, hashLength);
            for (let child of node.children) {
                pointer = evaluateChildElementHash(child, hash, pointer, hashLength);
            }
            return pointer;
        }

        function appendStringToHash(stringToConvert, hash, pointer, hashLength) {
            for (let c of stringToConvert.split('')) {
                hash[pointer] ^= (c.charCodeAt(0) % 0x10);
                pointer = ++pointer % hashLength;
            }
            return pointer;
        }

        function convertToChars(integers) {
            let result = '';
            integers.map(i => i.toString(0x10)).forEach(s => result += s);
            return result;
        }
    }

    // Logging

    /**
     * Добавляем запись в лог
     * @param level
     * @param message
     */
    function addLogEntry(level, message) {
        if (withLogs) {
            let now = new Date();
            logs.push({
                timestamp : dateTimeFormatter.format(now) + '.' + now.getMilliseconds(),
                level : level,
                message : message
            });
        }
    }

    /**
     * Добавляем запись в историю поиска
     * @param locator
     * @param foundElementEntries
     */
    function addSearchHistoryEntry(locator, foundElementEntries) {
        let searchHistoryEntry = {
            locator : locator,
            result: []
        };
        foundElementEntries.forEach(function(entry) {
            let searchHistoryElementEntry = {};
            if (entry.index !== undefined && entry.index !== null) {
                searchHistoryElementEntry.index = entry.index;
            }
            if (entry.element !== undefined && entry.element !== null) {
                searchHistoryElementEntry.found = true;
            } else {
                searchHistoryElementEntry.found = false;
            }
            if (entry.hash !== undefined && entry.hash !== null) {
                searchHistoryElementEntry.hash = entry.hash;
            }
            if (entry.hashCorrect !== undefined && entry.hashCorrect !== null) {
                searchHistoryElementEntry.hashCorrect = entry.hashCorrect;
            }
            searchHistoryEntry.result.push(searchHistoryElementEntry);
        })
        searchHistory.push(searchHistoryEntry);
    }

    /**
     * Добавляем в ошибку информацию о локаторе на котором произошла ошибка поиска
     * @param locator
     */
    function addLocatorErrorAttachment(locator) {
        errorAttachments.push({
            name: 'Processed locator',
            type: 'json',
            content: locator
        })
    }

    /**
     * Добавляем в ошибку информацию о родительском элементе
     * @param parentElement
     * @param locator
     */
    function addParentElementErrorAttachment(parentElement) {
        errorAttachments.push({
            name: 'Parent element',
            type: 'text/html',
            content: parentElement.outerHTML
        });
    }

    /**
     * Добавляем в ошибку информацию о найденных элементах
     * @param foundElements
     */
    function addChildElementErrorAttachments(foundElements) {
        let foundElementNumber = 0;
        for (let foundElement of foundElements) {
            errorAttachments.push({
                name: 'Found element ' + foundElementNumber++,
                type: 'text/html',
                content: foundElement.outerHTML
            });
        }
    }

    // Errors

    function IncorrectSearchQueryError(message) {
        this.name = 'IncorrectSearchQueryError';
        this.message = message;
        if (Error.captureStackTrace) {
            Error.captureStackTrace(this, this.constructor);
        } else {
            this.stack = (new Error()).stack;
        }
    }

    function ElementSearchError(message) {
        this.name = 'ElementSearchError';
        this.message = message;
        if (Error.captureStackTrace) {
            Error.captureStackTrace(this, this.constructor);
        } else {
            this.stack = (new Error()).stack;
        }
    }

    function ElementStateError(message) {
        this.name = 'ElementStateError';
        this.message = message;
        if (Error.captureStackTrace) {
            Error.captureStackTrace(this, this.constructor);
        } else {
            this.stack = (new Error()).stack;
        }
    }

    function FunctionCallError(message) {
        this.name = 'FunctionCallError';
        this.message = message;
        if (Error.captureStackTrace) {
            Error.captureStackTrace(this, this.constructor);
        } else {
            this.stack = (new Error()).stack;
        }
    }

})
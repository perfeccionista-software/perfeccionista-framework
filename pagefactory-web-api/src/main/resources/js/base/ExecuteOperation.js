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
    let withPageSource = operation.withPageSource !== undefined;
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
        let selectors = operation.selectorChain.selectors;
        let endpointFunction = operation.endpointFunction;
        let selectorProcessingResult = {
            elementEntries : [
                {element : document.documentElement}
            ],
            invokeOnCallFunctions : []
        };
        for (let selector of selectors) {
            selectorProcessingResult = await processSelector(selectorProcessingResult, selector);
            if (selectorProcessingResult.elementEntries.length === 0) {
                addLogEntry('WARN', 'No results found for selector: ' + JSON.stringify(selector));
                return;
            }
        }
        await executeEndpointFunction(selectorProcessingResult, endpointFunction);
    }

    /**
     * Обрабатываем поочередно переданную цепочку локаторов.
     * @param previousSelectorProcessingResult
     * @param selector
     */
    async function processSelector(previousSelectorProcessingResult, selector) {
        addLogEntry('DEBUG', 'Process selector: ' + JSON.stringify(selector));
        checkSelector(selector);
        let singleParent = previousSelectorProcessingResult.elementEntries.length === 1;
        let singleChild = selector.single;
        let selectorProcessingResult = {
            invokeOnCallFunctions: selector.invokeOnCallFunctions
        }
        let foundElementEntries;

        if (singleParent && singleChild) {
            foundElementEntries = await findSingleFromSingle(previousSelectorProcessingResult, selector);
        } else if (singleParent && !singleChild) {
            foundElementEntries = await findMultipleFromSingle(previousSelectorProcessingResult, selector);
        } else if (!singleParent && singleChild) {
            foundElementEntries = await findSingleFromMultiple(previousSelectorProcessingResult, selector);
        } else {
            throw new IncorrectSearchQueryError('Selector chain can contain only one multiple selector');
        }

        selectorProcessingResult.elementEntries = foundElementEntries;
        return selectorProcessingResult;
    }

    /**
     * Ищем один дочерний элемент от одного родительского элемента
     * @param previousSelectorProcessingResult
     * @param selector
     * @return {{element: *}[]|*[]}
     */
    async function findSingleFromSingle(previousSelectorProcessingResult, selector) {
        let parentElementEntry = previousSelectorProcessingResult.elementEntries[0];
        let parentElement = parentElementEntry.element;
        let enclosingElement = parentElementEntry.element;
        let parentFunctionInvocations = previousSelectorProcessingResult.invokeOnCallFunctions;
        if (parentFunctionInvocations !== undefined && parentFunctionInvocations.length > 0) {
            await executeInvokeOnCallFunctions(parentElement, parentFunctionInvocations);
        }
        if (selector.fromParent === false) {
            parentElement = document.documentElement;
        }
        let foundElements = findElements(parentElement, selector, enclosingElement);
        let foundElementEntries = [];
        let size = foundElements.length;
        if (size === 0) {
            if (selector.strictSearch) {
                addSelectorErrorAttachment(selector);
                addParentElementErrorAttachment(parentElement);
                throw new ElementSearchError('No elements found');
            } else {
                addSearchHistoryEntry(selector, foundElementEntries);
                return foundElementEntries;
            }
        } else if (size === 1) {
            foundElementEntries.push(createElementEntry(foundElements[0], selector));
            addSearchHistoryEntry(selector, foundElementEntries);
        } else {
            let elementIndex = selector.index;
            if (elementIndex === undefined || elementIndex === null) {
                addSelectorErrorAttachment(selector);
                addParentElementErrorAttachment(parentElement);
                addChildElementErrorAttachments(foundElements);
                throw new ElementSearchError('More than one element found');
            }
            if (elementIndex >= size) {
                addSelectorErrorAttachment(selector);
                addParentElementErrorAttachment(parentElement);
                addChildElementErrorAttachments(foundElements);
                throw new ElementSearchError('No element with index ' + elementIndex + ' found');
            }
            let elementEntry = createElementEntry(foundElements[elementIndex], selector);
            elementEntry.index = elementIndex;
            foundElementEntries.push(elementEntry);
            addSearchHistoryEntry(selector, foundElementEntries);
            return foundElementEntries;
        }
        return foundElementEntries;
    }

    /**
     * Ищем несколько дочерних элементов от одного родительского
     * @param previousSelectorProcessingResult
     * @param selector
     * @return {[]|*[]}
     */
    async function findMultipleFromSingle(previousSelectorProcessingResult, selector) {
        let parentElementEntry = previousSelectorProcessingResult.elementEntries[0];
        let parentElement = parentElementEntry.element;
        let enclosingElement = parentElementEntry.element;
        let parentFunctionInvocations = previousSelectorProcessingResult.invokeOnCallFunctions;
        if (undefined !== parentFunctionInvocations && parentFunctionInvocations.length > 0) {
            await executeInvokeOnCallFunctions(parentElement, parentFunctionInvocations);
        }
        if (selector.fromParent === false) {
            parentElement = document.documentElement;
        }
        let foundElements = findElements(parentElement, selector, enclosingElement);
        let foundElementEntries = [];
        let size = foundElements.length;
        if (size === 0) {
            if (selector.strictSearch) {
                addSelectorErrorAttachment(selector);
                addParentElementErrorAttachment(parentElement);
                throw new ElementSearchError('No elements found');
            } else {
                addSearchHistoryEntry(selector, foundElementEntries);
                return foundElementEntries;
            }
        }
        if (selector.indexes == undefined) {
            for (let i = 0; i < foundElements.length; i++) {
                let elementEntry = createElementEntry(foundElements[i], selector);
                elementEntry.index = i;
                foundElementEntries.push(elementEntry);
            }
        } else {
            for (let elementIndex of selector.indexes) {
                if (elementIndex >= size) {
                    addSelectorErrorAttachment(selector);
                    addParentElementErrorAttachment(parentElement);
                    addChildElementErrorAttachments(foundElements);
                    throw new ElementSearchError('No element with index ' + elementIndex + ' found');
                }
                let elementEntry = createElementEntry(foundElements[elementIndex], selector);
                elementEntry.index = elementIndex;
                foundElementEntries.push(elementEntry);
            }
        }
        addSearchHistoryEntry(selector, foundElementEntries);
        return foundElementEntries;
    }

    /**
     * Ищем по одному дочернему элементу от каждого родительского
     * @param previousSelectorProcessingResult
     * @param selector
     * @return {[]}
     */
    async function findSingleFromMultiple(previousSelectorProcessingResult, selector) {
        let parentElementEntries = previousSelectorProcessingResult.elementEntries;
        let foundElementEntries = [];
        // TODO: Возможно, стоит сделать принудительный признак поиска элементов внутри предка
        // selector.onlyWithinParent = true;
        for (let parentElementEntry of parentElementEntries) {
            let parentElement = parentElementEntry.element;
            let enclosingElement = parentElementEntry.element;
            let parentFunctionInvocations = previousSelectorProcessingResult.invokeOnCallFunctions;
            if (undefined !== parentFunctionInvocations && parentFunctionInvocations.length > 0) {
                await executeInvokeOnCallFunctions(parentElement, parentFunctionInvocations);
            }
            let parentIndex = parentElementEntry.index;
            let foundElements = findElements(parentElement, selector, enclosingElement);
            let size = foundElements.length;
            if (size === 0) {
                if (selector.strictSearch) {
                    addSelectorErrorAttachment(selector);
                    addParentElementErrorAttachment(parentElement);
                    throw new ElementSearchError('No elements found');
                } else {
                    // Индексы для null-элементов возвращать нужно.
                    // Например, нужно отфильттровать строки списка в которых не содержится значение Х в элементе Y
                    // не важно, другое значение в элементе Y или элемента нет - индекс должен быть возвращен.
                    let elementEntry = createElementEntry(null, selector);
                    elementEntry.index = parentIndex;
                    foundElementEntries.push(elementEntry);
                }
            } else if (size === 1) {
                let elementEntry = createElementEntry(foundElements[0], selector);
                elementEntry.index = parentIndex;
                foundElementEntries.push(elementEntry);
            } else {
                let elementIndex = selector.index;
                if (elementIndex === undefined || elementIndex === null) {
                    addSelectorErrorAttachment(selector);
                    addParentElementErrorAttachment(parentElement);
                    addChildElementErrorAttachments(foundElements);
                    throw new ElementSearchError('More than one element found');
                }
                if (elementIndex >= size) {
                    addSelectorErrorAttachment(selector);
                    addParentElementErrorAttachment(parentElement);
                    addChildElementErrorAttachments(foundElements);
                    throw new ElementSearchError('No element with index ' + elementIndex + ' found');
                }
                let elementEntry = createElementEntry(foundElements[elementIndex], selector);
                elementEntry.index = elementIndex;
                foundElementEntries.push(elementEntry);
                addSearchHistoryEntry(selector, foundElementEntries);
            }
        }
        addSearchHistoryEntry(selector, foundElementEntries);
        return foundElementEntries;
    }

    /**
     * Ищем все элементы по переданному локатору от родительского элемента.
     * Если в локаторе установлен признак поиска только дочерних элементов,
     * то фильтруем все элементы, которые не принадлежат родительскому элементу
     * @param parentElement
     * @param selector
     * @param enclosingElement
     * @return {HTMLElement[]|[]}
     */
    function findElements(parentElement, selector, enclosingElement) {
        let foundElements = [];
        if (parentElement === null) {
            return foundElements;
        }
        switch (selector.selectorStrategy) {
            case 'selfNode':
                foundElements = new Array(parentElement);
                break;
            case 'id':
                let foundElement = document.getElementById(selector.selectorValue);
                if (foundElement !== null) {
                    foundElements = [foundElement];
                }
                break;
            case 'css':
                foundElements = collectionToArray(parentElement.querySelectorAll(selector.selectorValue));
                break;
            case 'xpath':
                foundElements = findElementsByXpath(parentElement, selector.selectorValue);
                break;
            case 'className':
                foundElements = collectionToArray(parentElement.getElementsByClassName(selector.selectorValue));
                break;
            case 'tagName':
                foundElements = collectionToArray(parentElement.getElementsByTagName(selector.selectorValue));
                break;
            case 'dti' :
                let dtiXpathValue = './/*[@data-test-id="' + selector.selectorValue + '"]';
                foundElements = findElementsByXpath(parentElement, dtiXpathValue);
                break;
            case 'name' :
                foundElements = collectionToArray(parentElement.getElementsByName(selector.selectorValue));
                break;
            case 'text':
                let textXpathValue = './/*[text()="' + selector.selectorValue + '"]';
                foundElements = findElementsByXpath(parentElement, textXpathValue);
                break;
            case 'containsText':
                let partialTextXpathValue = './/*[text()[contains(.,"' + selector.selectorValue + '")]]';
                foundElements = findElementsByXpath(parentElement, partialTextXpathValue);
                break;
            default:
                throw new IncorrectSearchQueryError('Selector strategy ' + selector.selectorStrategy + ' not found');
        }
        if (foundElements.length === 0) {
            return foundElements;
        }
        // Убираем те элементы, которые не принадлежат родительскому узлу
        let foundElementsWithinParent = [];

        if (selector.onlyWithinParent) {
            for (let foundElement of foundElements) {
                if (enclosingElement.contains(foundElement)) {
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
     * @param selector
     * @return {{element: *}}
     */
    function createElementEntry(element, selector) {
        let elementEntry = {
            element: element
        }
        let expectedHash = selector.expectedHash;
        if (expectedHash !== undefined && expectedHash !== null) {
            checkExpectedHash(element, expectedHash);
            elementEntry.hashCorrect = true;
        }
        if (selector.calculateHash) {
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
        if (withPageSource) {
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
     * @param selector
     */
    function checkSelector(selector) {
        if (selector.selectorId === undefined || selector.selectorId === null) {
            throw new IncorrectSearchQueryError('SelectorId is not declared');
        }
        if (selector.selectorComponent === undefined || selector.selectorComponent === null) {
            throw new IncorrectSearchQueryError('SelectorComponent is not declared');
        }
        if (selector.selectorStrategy === undefined || selector.selectorStrategy === null) {
            throw new IncorrectSearchQueryError('SelectorStrategy is not declared');
        }
        if (selector.selectorValue === undefined || selector.selectorValue === null) {
            throw new IncorrectSearchQueryError('SelectorValue is not declared');
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
     * @param selector
     * @param foundElementEntries
     */
    function addSearchHistoryEntry(selector, foundElementEntries) {
        let searchHistoryEntry = {
            selector : selector,
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
     * @param selector
     */
    function addSelectorErrorAttachment(selector) {
        errorAttachments.push({
            name: 'Processed selector',
            type: 'json',
            content: selector
        })
    }

    /**
     * Добавляем в ошибку информацию о родительском элементе
     * @param parentElement
     * @param selector
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

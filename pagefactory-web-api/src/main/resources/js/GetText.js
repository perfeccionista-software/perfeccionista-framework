/**
 * This function is based on the Selenium getText() function
 */
// noinspection BadExpressionStatementJS
(async (element, options) => {

    debugger;

    const IS_SHADOW_DOM_ENABLED = (typeof ShadowRoot === 'function');
    const NodeType = {
        ELEMENT: 1,
        ATTRIBUTE: 2,
        TEXT: 3,
        CDATA_SECTION: 4,
        ENTITY_REFERENCE: 5,
        ENTITY: 6,
        PROCESSING_INSTRUCTION: 7,
        COMMENT: 8,
        DOCUMENT: 9,
        DOCUMENT_TYPE: 10,
        DOCUMENT_FRAGMENT: 11,
        NOTATION: 12
    };
    const INLINE_DISPLAY_BOXES = [
        'inline',
        'inline-block',
        'inline-table',
        'none',
        'table-cell',
        'table-column',
        'table-column-group'
    ];

    return concatenateProcessedLines(appendVisibleTextLinesFromElement(element, []));

    /**
     * Соединяем обработанные строки в одну и заменяем неразрывные пробелы обычными
     */
    function concatenateProcessedLines(lines) {
        lines = lines.map(function (line) {
            return trimExcludingNonBreakingSpaceCharacters(line);
        });
        let joinedLines = lines.join('\n');
        let trimmedLines = trimExcludingNonBreakingSpaceCharacters(joinedLines);
        return trimmedLines.replace(/\xa0/g, ' ');
    }

    /**
     * Обрезаем пробелы в начале и конце строки. Оставляем на месте неразрывные пробелы
     */
    function trimExcludingNonBreakingSpaceCharacters(line) {
        return line.replace(/^[^\S\xa0]+|[^\S\xa0]+$/g, '');
    }

    /**
     * Получаем текстовые строки из элемента, если ShadowDom выключен
     */
    function appendVisibleTextLinesFromElement(element, lines) {
        appendVisibleTextLinesFromElementCommon(element, lines, isShown, function (node, lines, shown, whitespace, textTransform) {
            // noinspection EqualityComparisonWithCoercionJS
            if (node.nodeType == NodeType.TEXT && shown) {
                /** @type {!Text} */
                let textNode = (node);
                appendVisibleTextLinesFromTextNode(textNode, lines, whitespace, textTransform);
            } else if (isElement(node)) {
                /** @type {!Element} */
                let castElem = (node);
                appendVisibleTextLinesFromElement(castElem, lines);
            }
        });
        return lines;
    }

    /**
     * Добавляем текстовые строки из элемента, которые видны пользователю
     */
    function appendVisibleTextLinesFromElementCommon(element, lines, isShownFn, childNodeFn) {
        if (isElement(element, 'BR')) {
            lines.push('');
        } else {
            let isTD = isElement(element, 'TD');
            let display = getEffectiveStyle(element, 'display');
            let isBlock = !isTD && !(Array.prototype.indexOf.call(INLINE_DISPLAY_BOXES, display) >= 0);
            let previousElementSibling = getPreviousElementSibling(element);
            let prevDisplay = (previousElementSibling) ? getEffectiveStyle(previousElementSibling, 'display') : '';
            let thisFloat = getEffectiveStyle(element, 'float') || getEffectiveStyle(element, 'cssFloat') || getEffectiveStyle(element, 'styleFloat');
            // noinspection EqualityComparisonWithCoercionJS
            let runIntoThis = prevDisplay == 'run-in' && thisFloat == 'none';
            if (isBlock && !runIntoThis && !isEmptyOrWhitespace(currLine())) {
                lines.push('');
            }
            let shown = isShownFn(element);
            let whitespace = null, textTransform = null;
            if (shown) {
                whitespace = getEffectiveStyle(element, 'white-space');
                textTransform = getEffectiveStyle(element, 'text-transform');
            }
            element.childNodes.forEach(childNode => childNodeFn(childNode, lines, shown, whitespace, textTransform));
            let line = currLine();
            if ((isTD || display == 'table-cell') && line && !endsWith(line, ' ')) {
                lines[lines.length - 1] += ' ';
            }
            if (isBlock && display != 'run-in' && !isEmptyOrWhitespace(line)) {
                lines.push('');
            }
        }
        function currLine() {
            /** @type {string|undefined} */
            return lines[lines.length - 1] || '';
        }
    }

    /**
     * Добавляем текст из текстового узла
     */
    function appendVisibleTextLinesFromTextNode(textNode, lines, whitespace, textTransform) {
        let text = textNode.nodeValue.replace(/[\u200b\u200e\u200f]/g, '');
        text = canonicalizeNewlines(text);
        // noinspection EqualityComparisonWithCoercionJS
        if (whitespace == 'normal' || whitespace == 'nowrap') {
            text = text.replace(/\n/g, ' ');
        }
        if (whitespace == 'pre' || whitespace == 'pre-wrap') {
            text = text.replace(/[ \f\t\v\u2028\u2029]/g, '\xa0');
        } else {
            text = text.replace(/[ \f\t\v\u2028\u2029]+/g, ' ');
        }
        if (textTransform == 'capitalize') {
            text = text.replace(/\b(\S)/g, function () {
                return arguments[1].toUpperCase();
            });
        }
        // noinspection EqualityComparisonWithCoercionJS
        if (textTransform == 'uppercase') {
            text = text.toUpperCase();
        }
        // noinspection EqualityComparisonWithCoercionJS
        if (textTransform == 'lowercase') {
            text = text.toLowerCase();
        }
        let currLine = lines.pop() || '';
        if (endsWith(currLine, ' ') && startsWith(text, ' ')) {
            text = text.substr(1);
        }
        lines.push(currLine + text);
    }

    /**
     * Проверяет, что в строке содержатся не только пробелы и что строка не пустая
     */
    function isEmptyOrWhitespace(string) {
        return /^[\s\xa0]*$/.test(string);
    }

    /**
     * Проверяем префикс
     */
    function startsWith(string, prefix) {
        // noinspection EqualityComparisonWithCoercionJS
        return string.lastIndexOf(prefix, 0) == 0;
    }

    /**
     * Проверяем суффикс
     */
    function endsWith(string, suffix) {
        let l = string.length - suffix.length;
        // noinspection EqualityComparisonWithCoercionJS
        return l >= 0 && string.indexOf(suffix, l) == l;
    }

    /**
     * Проверяет, является ли узел элементом, а также, опционально, проверяет его имя тега.
     * Если имя тега не указано, то возвращается true если это элемент, независимо от тега
     */
    function isElement(node, opt_tagName) {
        if (opt_tagName && (typeof opt_tagName !== 'string')) {
            opt_tagName = opt_tagName.toString();
        }
        // noinspection EqualityComparisonWithCoercionJS
        return !!node && node.nodeType == NodeType.ELEMENT && (!opt_tagName || node.tagName.toUpperCase() == opt_tagName);
    }

    /**
     * Возвращает true, если указанное значение - функция
     */
    function isFunction(value) {
        let s = typeof value;
        // noinspection EqualityComparisonWithCoercionJS
        if (s == 'function') {
            return true;
        }
        // noinspection EqualityComparisonWithCoercionJS
        if (s == 'object' && value) {
            // noinspection EqualityComparisonWithCoercionJS
            if (className == '[object Function]'
                || typeof value.call != 'undefined'
                && typeof value.propertyIsEnumerable != 'undefined'
                && !value.propertyIsEnumerable('call')) {
                return 'function';
            }
        }
        return false;
    }

    /**
     * Проверяет видимость элемента
     */
    function isShown(element, ignoreOpacity) {

        /**
         * Определяет имеет ли элемент или его предки признак `display: none`
         */
        function displayed(elem) {
            if (isElement(elem)) {
                let element = /** @type {!Element} */ (elem);
                // noinspection EqualityComparisonWithCoercionJS
                if (getEffectiveStyle(element, 'display') == 'none') {
                    return false;
                }
            }
            let parent = getParentNodeInComposedDom(elem);
            if (IS_SHADOW_DOM_ENABLED && (parent instanceof ShadowRoot)) {
                if (parent.host.shadowRoot !== parent) {
                    return false;
                } else {
                    parent = parent.host;
                }
            }
            // noinspection EqualityComparisonWithCoercionJS
            if (parent && (parent.nodeType == NodeType.DOCUMENT || parent.nodeType == NodeType.DOCUMENT_FRAGMENT)) {
                return true;
            }
            if (parent && isElement(parent, 'DETAILS') && !parent.open && !isElement(elem, 'SUMMARY')) {
                return false;
            }
            return parent && displayed(parent);
        }

        /**
         * Проверяет видимость элемента
         */
        function isShown_(element, ignoreOpacity, parentsDisplayedFn) {
            function positiveSize(element) {
                let width = element.offsetWidth;
                let height = element.offsetHeight;
                if (width > 0 && height > 0) {
                    return true;
                }
                if (isElement(element, 'PATH') && (height > 0 || width > 0)) {
                    let strokeWidth = getEffectiveStyle(element, 'stroke-width');
                    return !!strokeWidth && (parseInt(strokeWidth, 10) > 0);
                }
                return getEffectiveStyle(element, 'overflow') != 'hidden' &&
                    [...element.childNodes].find(function (node) {
                        // noinspection EqualityComparisonWithCoercionJS
                        return node.nodeType == 'TEXT' || (isElement(node) && positiveSize(node));
                    });
            }
            if (!isElement(element)) {
                throw new Error('Argument to isShown must be of type Element');
            }
            if (isElement(element, 'BODY')) {
                return true;
            }
            if (isElement(element, 'OPTION') || isElement(element, 'OPTGROUP')) {
                let select = /**@type {Element}*/ (getAncestor(element, function (e) {
                    return isElement(e, 'SELECT');
                }));
                return !!select && isShown_(select, true, parentsDisplayedFn);
            }
            if (isElement(element, 'INPUT') && element.type.toLowerCase() == 'hidden') {
                return false;
            }
            if (isElement(element, 'NOSCRIPT')) {
                return false;
            }
            let visibility = getEffectiveStyle(element, 'visibility');
            // noinspection EqualityComparisonWithCoercionJS
            if (visibility == 'collapse' || visibility == 'hidden') {
                return false;
            }
            if (!parentsDisplayedFn(element)) {
                return false;
            }
            if (!ignoreOpacity && getOpacity(element) == 0) {
                return false;
            }
            return positiveSize(element);
        }
        return isShown_(element, !!ignoreOpacity, displayed);
    }


    //////////////////////////////////////
    // Extractors
    //////////////////////////////////////

    /**
     * Возвращает неявно заданный стиль элемента или null, если он не задан
     */
    function getEffectiveStyle(element, propertyName) {
        let styleName = toCamelCase(propertyName);
        // noinspection EqualityComparisonWithCoercionJS
        if (styleName == 'float' || styleName == 'cssFloat' || styleName == 'styleFloat') {
            styleName = 'cssFloat';
        }
        return getComputedStyle(element, styleName) || getCascadedStyle(element, styleName);
    }

    /**
     * Возвращает значение вычисленного стиля узла
     */
    function getComputedStyle(element, property) {
        // noinspection EqualityComparisonWithCoercionJS,JSUnresolvedVariable
        /** @type {!Document} */
        let doc = (element.nodeType == NodeType.DOCUMENT ? element : element.ownerDocument || element.document);
        // noinspection JSUnresolvedVariable
        if (doc.defaultView && doc.defaultView.getComputedStyle) {
            let styles = doc.defaultView.getComputedStyle(element, null);
            if (styles) {
                // element.style[..] is undefined for browser specific styles as 'filter'.
                return styles[property] || styles.getPropertyValue(property) || '';
            }
        }
        return '';
    }

    /**
     * Просматривает вверх DOM-дерево до первого значения стиля не равного 'inherit',
     * используя текущий стиль каждого узла если он доступен или 'inline' стиль
     */
    function getCascadedStyle(element, styleName) {
        // noinspection JSUnresolvedVariable
        let style = element.currentStyle || element.style;
        let value = style[styleName];
        if (!(value !== void 0) && isFunction(style.getPropertyValue)) {
            value = style.getPropertyValue(styleName);
        }
        // noinspection EqualityComparisonWithCoercionJS
        if (value != 'inherit') {
            return (value !== void 0) ? value : null;
        }
        let parent = getParentElement(element);
        return parent ? getCascadedStyle(parent, styleName) : null;
    }

    /**
     * Возвращает прозрачность узла
     */
    function getOpacity(elem) {
        // noinspection EqualityComparisonWithCoercionJS
        if (getEffectiveStyle(elem, 'position') == 'relative') {
            // Filter does not apply to non positioned elements.
            return 1;
        }
        let opacityStyle = getEffectiveStyle(elem, 'filter');
        let groups = opacityStyle.match(/^alpha\(opacity=(\d*)\)/) || opacityStyle.match(/^progid:DXImageTransform.Microsoft.Alpha\(Opacity=(\d*)\)/);
        if (groups) {
            return Number(groups[1]) / 100;
        } else {
            return 1; // Opaque.
        }
    }

    /**
     * Возвращает первый предшествующий соседний элемент, который является элементом
     */
    function getPreviousElementSibling(node) {
        // noinspection JSUnresolvedVariable
        if (node.previousElementSibling !== void 0) {
            // noinspection JSUnresolvedVariable
            /** @type {!Element} */
            return (node).previousElementSibling;
        }
        return getNextElementNode(node.previousSibling, false);
    }

    /**
     * Возвращает первый узел, являющийся элементом в указанном направлении, начиная с указанного узла
     */
    function getNextElementNode(node, forward) {
        // noinspection EqualityComparisonWithCoercionJS
        while (node && node.nodeType != NodeType.ELEMENT) {
            node = forward ? node.nextSibling : node.previousSibling;
        }
        /** @type {Element} */
        return (node);
    }

    /**
     * Возвращает отображаемый родительский элемент для указанного узла или null
     */
    function getParentNodeInComposedDom(node) {
        /**@type {Node}*/
        let parent = node.parentNode;
        // Shadow DOM v1
        // noinspection JSUnresolvedVariable
        if (parent && parent.shadowRoot && node.assignedSlot !== undefined) {
            // Can be null on purpose, meaning it has no parent as it hasn't yet been slotted
            return node.assignedSlot ? node.assignedSlot.parentNode : null;
        }
        // Shadow DOM V0 (deprecated)
        // noinspection JSUnresolvedVariable
        if (node.getDestinationInsertionPoints) {
            let destinations = node.getDestinationInsertionPoints();
            if (destinations.length > 0) {
                return destinations[destinations.length - 1];
            }
        }
        return parent;
    }

    /**
     * Возвращает родительский элемент для указанного узла или null
     */
    function getParentElement(node) {
        let elem = node.parentNode;
        // noinspection EqualityComparisonWithCoercionJS
        while (elem && elem.nodeType != NodeType.ELEMENT && elem.nodeType != NodeType.DOCUMENT && elem.nodeType != NodeType.DOCUMENT_FRAGMENT) {
            elem = elem.parentNode;
        }
        /** @type {Element} */
        return (isElement(elem) ? elem : null);
    }

    /**
     * Возвращает первый родительский элемент, который подходит под переданную функцию
     */
    function getAncestor(element, matcher) {
        if (element) {
            element = element.parentNode;
        }
        let steps = 0;
        while (element) {
            if (matcher(element)) {
                return element;
            }
            element = element.parentNode;
            steps++;
        }
        return null;
    }

    /**
     * Заменяет Windows и Mac переносы строк на Unix стиль: \r или \r\n -> \n
     */
    function canonicalizeNewlines(string) {
        return string.replace(/(\r\n|\r|\n)/g, '\n');
    }

    /**
     * Конвертирует строку из selector-case в camelCase
     */
    function toCamelCase(string) {
        return String(string).replace(/-([a-z])/g, function (all, match) {
            return match.toUpperCase();
        });
    }

});
// noinspection BadExpressionStatementJS
(async (element, options) => {

    debugger;

    let targetX = options.targetX;
    let targetY = options.targetY;
    let offsetX = options.offsetX;
    let offsetY = options.offsetY;
    let delay = options.dropDelay;

    let source = element;

    if (!source.draggable) {
        ElementStateError.prototype = Object.create(Error.prototype);
        ElementStateError.prototype.constructor = ElementStateError;
        throw new ElementStateError('Source element is not draggable');
    }

    let doc = source.ownerDocument,
        win = doc.defaultView,
        sourceRect = source.getBoundingClientRect(),
        sourceX = sourceRect.left + (sourceRect.width >> 1),
        sourceY = sourceRect.top + (sourceRect.height >> 1),
        targetXWithOffset = targetX + offsetX,
        targetYWithOffset = targetY + offsetY,
        dataTransfer = Object.create(Object.prototype, {
            _items: { value: { } },
            effectAllowed: { value: 'all', writable: true },
            dropEffect: { value: 'move', writable: true },
            files: { get: function () { return undefined } },
            types: { get: function () { return Object.keys(this._items) } },
            setData: { value: function (format, data) { this._items[format] = data } },
            getData: { value: function (format) { return this._items[format] } },
            clearData: { value: function (format) { delete this._items[format] } },
            setDragImage: { value: function () { } }
        });

    let target = doc.elementFromPoint(targetXWithOffset, targetYWithOffset);
    if (!target) {
        FunctionCallError.prototype = Object.create(Error.prototype);
        FunctionCallError.prototype.constructor = FunctionCallError;
        throw new FunctionCallError('The target element is not intractable and need to be scrolled into the view');
    }
    let targetRect = target.getBoundingClientRect();

    emit(source, 'dragstart', delay, function () {
        let rect3 = target.getBoundingClientRect();
        sourceX = rect3.left + targetXWithOffset - targetRect.left;
        sourceY = rect3.top + targetYWithOffset - targetRect.top;
        emit(target, 'dragenter', 1, function () {
            emit(target, 'dragover', delay, function () {
                target = doc.elementFromPoint(sourceX, sourceY);
                emit(target, 'drop', 1, function () {
                    emit(source, 'dragend', 1, callback);
                });});});});

    function emit(element, type, delay, callback) {
        let event = doc.createEvent('DragEvent');
        event.initMouseEvent(type, true, true, win, 0, 0, 0, sourceX, sourceY, false, false, false, false, 0, null);
        Object.defineProperty(event, 'dataTransfer', { get: function () { return dataTransfer } });
        element.dispatchEvent(event);
        win.setTimeout(callback, delay);
    }

});

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
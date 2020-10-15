// TODO: Перенести сюда логику скролла из селениума,
//  которая ищет родительский элемент и скроллит относительно него
// noinspection BadExpressionStatementJS
(async (element, options) => {

    debugger;

    let sessionId = options.sessionId;

    let currentMousePosition = getMouse();
    let elementCenterPosition = getElementCenterPosition(element);



    let mouseMoveEvent = document.createEvent("MouseEvents");

    mouseMoveEvent.initMouseEvent(
        "mousemove",            //event type : click, mousedown, mouseup, mouseover, mousemove, mouseout.
        true,               //canBubble
        false,              //cancelable
        window,                          //event's AbstractView : should be window
        1,                      // detail : Event's mouse click count
        50,                   // screenX
        50,                   // screenY
        50,                    // clientX
        50,                    // clientY
        false,                 // ctrlKey
        false,                  // altKey
        false,                // shiftKey
        false,                // metaKey
        0,                     // button : 0 = click, 1 = middle button, 2 = right button
        null              // relatedTarget : Only used with some event types (e.g. mouseover and mouseout). In other cases, pass null.
    );

    document.dispatchEvent(mouseMoveEvent)

    this.fireMouseEvent_(bot.events.EventType.MOUSEMOVE, null, null,
        toElemWasInteractable);

});

function getElementCenterPosition(element) {
    let target = element.getClientRects()[0];
    return  {
        elementCenterX: target.left + element.offsetLeft + element.offsetWidth / 2,
        elementCenterY: target.top + element.offsetTop + element.offsetHeight / 2
    }
}

/**
 * Возвращает объект мыши с последним его использованным состоянием
 */
function getMouse(sessionId) {
    let mouse = window.localStorage.getItem('mouse_' + sessionId);
    if (mouse === undefined || mouse === null) {
        return {
            xPosition: 0,
            yPosition: 0,
            false,                 // ctrlKey
            false,                  // altKey
            false,                // shiftKey
            false,                // metaKey
        }
    }
    return JSON.parse(mouse);
}

/**
 * Обновляет состояние объекта мыши для текущей тестовой сессии
 * @param sessionId
 * @param mouse
 */
function updateMouse(sessionId, mouse) {
    window.localStorage.setItem('mouse_' + sessionId, JSON.stringify(mouse));
}


function createMouseEvent(eventType, mouse, args) {

    let mouseEvent = document.createEvent("MouseEvents");
    let eventArgs = !(args === undefined || args === null)
        ? args
        : {
            canBubble: true,
            cancelable: false,
            detail: 0,
            ctr

        };

    mouseEvent.initMouseEvent(
        eventType,                       //event type : click, mousedown, mouseup, mouseover, mousemove, mouseout.
        eventArgs.canBubble,             //canBubble
        eventArgs.cancelable,            //cancelable
        window,                          //event's AbstractView : should be window
        eventArgs.detail,                // detail : Event's mouse click count
        50,                   // screenX
        50,                   // screenY
        50,                    // clientX
        50,                    // clientY
        false,                 // ctrlKey
        false,                  // altKey
        false,                // shiftKey
        false,                // metaKey
        0,                     // button : 0 = click, 1 = middle button, 2 = right button
        null              // relatedTarget : Only used with some event types (e.g. mouseover and mouseout). In other cases, pass null.
    );

    return mouseEvent;


}
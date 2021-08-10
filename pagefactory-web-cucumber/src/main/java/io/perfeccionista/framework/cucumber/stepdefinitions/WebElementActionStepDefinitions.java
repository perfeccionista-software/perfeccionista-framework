package io.perfeccionista.framework.cucumber.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.ru.Дано;
import io.perfeccionista.framework.cucumber.parameters.DataStorageParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueStringParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementActionParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementInteractionParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementPropertyParameter;
import io.perfeccionista.framework.pagefactory.elements.WebFileInput;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebHoverToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebScrollToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebInputTextAvailable;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.perfeccionista.framework.utils.ToolkitUtils.copyToClipboard;
import static io.perfeccionista.framework.utils.ToolkitUtils.getFromClipboard;

// TODO: Add to exception context limiter information
// TODO: Add step categories
// TODO: Как сделать снятие скриншотов конфигурируемым из проекта (для каждого метода)
public class WebElementActionStepDefinitions implements WebStepDefinitions {

    /**
     *
     * @param webElementAction -
     * @param elementFinder -
     */
    @Given("user on the {webElement} executes {webElementAction}")
    @Дано("пользователь на {webElement} выполняет {webElementAction}")
    public void userExecutesWebElementActionOnTheElement(WebElementParameter<WebChildElement> elementFinder,
                                                         WebElementActionParameter webElementAction) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .executeAction(webElementAction.getRaw()));
    }

    /**
     *
     * @param elementSourceFinder -
     * @param elementTargetFinder -
     */
    @Given("user drag the {webElement} and drop to {webElement}")
    @Дано("пользователь перетаскивает {webElement} на {webElement}")
    public void userExecutesWebElementInteractionOnTheElements(WebElementParameter<WebChildElement> elementSourceFinder,
                                                               WebElementParameter<WebChildElement> elementTargetFinder) {
        getWebPageContext().execute(context -> {
            WebChildElement sourceElement = elementSourceFinder.getElement(context, WebChildElement.class);
            WebChildElement targetElement = elementTargetFinder.getElement(context, WebChildElement.class);
            sourceElement.executeAction("Drag and Drop", targetElement.getElementBounds().getCenter());
        });
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("user clicks on the {webElement}")
    @Дано("пользователь нажимает на {webElement}")
    public void userClicksOnTheElement(WebElementParameter<WebClickAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebClickAvailable.class)
                        .click());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("user clears the text in the {webElement}")
    @Дано("пользователь очищает текст в {webElement}")
    public void userClearsTheTextInTheElement(WebElementParameter<WebInputTextAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebInputTextAvailable.class)
                        .clear());
    }

    /**
     *
     * @param elementFinder -
     * @param textToEnter -
     */
    @Given("user enters in the {webElement} {stringValue}")
    @Дано("пользователь вводит в {webElement} {stringValue}")
    public void userEnterTheTextInTheElement(WebElementParameter<WebInputTextAvailable> elementFinder,
                                             ValueStringParameter textToEnter) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebInputTextAvailable.class)
                        .typeText(textToEnter.getNotNullProcessedValue()));
    }

    /**
     *
     * @param elementFinder -
     * @param fileName -
     */
    @Given("user sets in the {webElement} filename {stringValue}")
    @Дано("пользователь устанавливает в {webElement} имя файла {stringValue}")
    public void userSetsFilenameInTheElement(WebElementParameter<WebFileInput> elementFinder,
                                             ValueStringParameter fileName) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebFileInput.class)
                        .replaceText(fileName.getNotNullProcessedValue()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("user scrolls the page to the {webElement}")
    @Дано("пользователь прокручивает страницу до {webElement}")
    public void userScrollThePageToTheElement(WebElementParameter<WebScrollToAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebScrollToAvailable.class)
                        .scrollTo());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("user hovers the cursor over the {webElement}")
    @Дано("пользователь наводит курсор на {webElement}")
    public void userHoversTheCursorOverTheElement(WebElementParameter<WebHoverToAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebHoverToAvailable.class)
                        .hoverTo(true));
    }

    /**
     *
     * @param elementFinders -
     */
    @Given("user hovers the cursor sequentially to")
    @Дано("пользователь поочередно наводит курсор на")
    public void userHoversTheCursorOverTheElement(List<WebElementParameter<WebHoverToAvailable>> elementFinders) {
        AtomicBoolean firstElementOfChain = new AtomicBoolean(true);
        getWebPageContext().execute(context -> {
            elementFinders.forEach(elementFinder -> {
                WebHoverToAvailable element = elementFinder.getElement(context, WebHoverToAvailable.class);
                if (firstElementOfChain.get()) {
                    element.hoverTo(true);
                    firstElementOfChain.set(false);
                } else {
                    element.hoverTo(false);
                }
            });
            firstElementOfChain.set(true);
        });
    }

    /**
     * Warning: для использования с множественным контекстом необходимо генерировать уникальное имя ключа,
     * иначе значения из последующих элементов будут перетирать предыдущие
     * @param elementFinder -
     * @param dataStorage -
     * @param key -
     */
    @Given("user saves text from the {webElement} to {dataStorage} by key {stringValue}")
    @Дано("пользователь сохраняет текст из {webElement} в {dataStorage} по ключу {stringValue}")
    public void userSavesTextFromTheElementToDataStorage(WebElementParameter<WebGetTextAvailable> elementFinder,
                                                         DataStorageParameter<String, String> dataStorage,
                                                         ValueStringParameter key) {
        getWebPageContext().execute(context -> {
            WebGetTextAvailable element = elementFinder.getElement(context, WebGetTextAvailable.class);
            String textToSave = element.getText();
            dataStorage.get().put(key.getNotNullProcessedValue(), textToSave);
        });
    }

    /**
     * Warning: для использования с множественным контекстом необходимо генерировать уникальное имя ключа,
     * иначе значения из последующих элементов будут перетирать предыдущие
     * @param elementProperty -
     * @param elementFinder -
     * @param dataStorage -
     * @param key -
     */
    @Given("user saves text from property {webElementProperty} of the {webElement} to {dataStorage} by key {stringValue}")
    @Дано("пользователь сохраняет текст из свойства {webElementProperty} элемента {webElement} в {dataStorage} по ключу {stringValue}")
    public void userSavesTextFromPropertyOfTheElementToDataStorage(WebElementPropertyParameter elementProperty,
                                                                   WebElementParameter<WebChildElement> elementFinder,
                                                                   DataStorageParameter<String, String> dataStorage,
                                                                   ValueStringParameter key) {
        getWebPageContext().execute(context -> {
            WebChildElement element = elementFinder.getElement(context, WebChildElement.class);
            String textToSave = element.getPropertyValue(elementProperty.getRaw());
            dataStorage.get().put(key.getNotNullProcessedValue(), textToSave);
        });
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("user saves text from the {webElement} to the clipboard")
    @Дано("пользователь сохраняет текст из {webElement} в буфер обмена")
    public void userSavesTextFromTheElementToTheClipboard(WebElementParameter<WebGetTextAvailable> elementFinder) {
        getWebPageContext().execute(context -> {
            WebGetTextAvailable element = elementFinder.getElement(context, WebGetTextAvailable.class);
            String textToCopy = element.getText();
            copyToClipboard(textToCopy);
        });
    }

    /**
     *
     * @param elementProperty -
     * @param elementFinder -
     */
    @Given("user saves text from property {webElementProperty} of the {webElement} to the clipboard")
    @Дано("пользователь сохраняет текст из свойства {webElementProperty} элемента {webElement} в буфер обмена")
    public void userSavesTextFromTheElementToTheClipboard(WebElementPropertyParameter elementProperty,
                                                          WebElementParameter<WebChildElement> elementFinder) {
        getWebPageContext().execute(context -> {
            WebChildElement element = elementFinder.getElement(context, WebChildElement.class);
            String textToCopy = element.getPropertyValue(elementProperty.getRaw());
            copyToClipboard(textToCopy);
        });
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("user paste text from the clipboard to the {webElement}")
    @Дано("пользователь вставляет текст из буфера обмена в {webElement}")
    public void userPasteTextFromTheClipboardToTheElement(WebElementParameter<WebInputTextAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebInputTextAvailable.class)
                        .typeText(getFromClipboard()));
    }

}

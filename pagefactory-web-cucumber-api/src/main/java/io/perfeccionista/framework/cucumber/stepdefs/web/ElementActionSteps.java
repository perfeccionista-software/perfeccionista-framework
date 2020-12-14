package io.perfeccionista.framework.cucumber.stepdefs.web;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.cucumber.parameters.DataStorageParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueStringParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementActionParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementInteractionParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementParameter;
import io.perfeccionista.framework.cucumber.parameters.WebElementPropertyParameter;
import io.perfeccionista.framework.pagefactory.elements.WebFileInput;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.HoverToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.ScrollToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SendKeysAvailable;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.perfeccionista.framework.utils.ToolkitUtils.copyToClipboard;
import static io.perfeccionista.framework.utils.ToolkitUtils.getFromClipboard;

// TODO: Add to exception context limiter information
// TODO: Wrap runLogic()
// TODO: Add step categories
// TODO: Как сделать снятие скриншотов конфигурируемым из проекта (для каждого метода)
public class ElementActionSteps implements WebStepDefinitions {

    /**
     *
     * @param webElementAction -
     * @param elementFinder -
     */
    @Given("user on the {webElement} executes {webElementAction}")
    @Given("пользователь на {webElement} выполняет {webElementAction}")
    public void userExecutesWebElementActionOnTheElement(WebElementParameter<WebChildElement> elementFinder,
                                                         WebElementActionParameter webElementAction) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebChildElement.class)
                        .executeAction(webElementAction.getRaw()));
    }

    /**
     *
     * @param webElementInteraction -
     * @param elementSourceFinder -
     * @param elementTargetFinder -
     */
    @Given("user on the {webElement} executes {webElementInteraction} to {webElement}")
    @Given("пользователь на {webElement} выполняет {webElementInteraction} к {webElement}")
    public void userExecutesWebElementInteractionOnTheElements(WebElementParameter<WebChildElement> elementSourceFinder,
                                                               WebElementInteractionParameter webElementInteraction,
                                                               WebElementParameter<WebChildElement> elementTargetFinder) {
        getWebPageContext().execute(context -> {
            WebChildElement sourceElement = elementSourceFinder.getElement(context, WebChildElement.class);
            WebChildElement targetElement = elementTargetFinder.getElement(context, WebChildElement.class);
            sourceElement.executeInteraction(webElementInteraction.getRaw(), targetElement);
        });
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("user clicks on the {webElement}")
    @Given("пользователь нажимает на {webElement}")
    public void userClicksOnTheElement(WebElementParameter<ClickAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, ClickAvailable.class)
                        .click());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("user clears the text in the {webElement}")
    @Given("пользователь очищает текст в {webElement}")
    public void userClearsTheTextInTheElement(WebElementParameter<ClearAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, ClearAvailable.class)
                        .clear());
    }

    /**
     *
     * @param elementFinder -
     * @param textToEnter -
     */
    @Given("user enters in the {webElement} {stringValue}")
    @Given("пользователь вводит в {webElement} {stringValue}")
    public void userEnterTheTextInTheElement(WebElementParameter<SendKeysAvailable> elementFinder,
                                             ValueStringParameter textToEnter) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, SendKeysAvailable.class)
                        .sendKeys(textToEnter.getNotNullProcessedValue()));
    }

    /**
     *
     * @param elementFinder -
     * @param fileName -
     */
    @Given("user sets in the {webElement} filename {stringValue}")
    @Given("пользователь устанавливает в {webElement} имя файла {stringValue}")
    public void userSetsFilenameInTheElement(WebElementParameter<WebFileInput> elementFinder,
                                             ValueStringParameter fileName) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebFileInput.class)
                        .setFileName(fileName.getNotNullProcessedValue()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("user scrolls the page to the {webElement}")
    @Given("пользователь прокручивает страницу до {webElement}")
    public void userScrollThePageToTheElement(WebElementParameter<ScrollToAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, ScrollToAvailable.class)
                        .scrollTo());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("user hovers the cursor over the {webElement}")
    @Given("пользователь наводит курсор на {webElement}")
    public void userHoversTheCursorOverTheElement(WebElementParameter<HoverToAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, HoverToAvailable.class)
                        .hoverTo(true));
    }

    /**
     *
     * @param elementFinders -
     */
    @Given("user hovers the cursor sequentially to")
    @Given("пользователь поочередно наводит курсор на")
    public void userHoversTheCursorOverTheElement(List<WebElementParameter<HoverToAvailable>> elementFinders) {
        AtomicBoolean firstElementOfChain = new AtomicBoolean(true);
        getWebPageContext().execute(context -> {
            elementFinders.forEach(elementFinder -> {
                HoverToAvailable element = elementFinder.getElement(context, HoverToAvailable.class);
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
    @Given("пользователь сохраняет текст из {webElement} в {dataStorage} по ключу {stringValue}")
    public void userSavesTextFromTheElementToDataStorage(WebElementParameter<GetTextAvailable> elementFinder,
                                                         DataStorageParameter<String, String> dataStorage,
                                                         ValueStringParameter key) {
        getWebPageContext().execute(context -> {
            GetTextAvailable element = elementFinder.getElement(context, GetTextAvailable.class);
            String textToSave = element.getText();
            dataStorage.get().put(key.getProcessedValue(), textToSave);
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
    @Given("пользователь сохраняет текст из свойства {webElementProperty} элемента {webElement} в {dataStorage} по ключу {stringValue}")
    public void userSavesTextFromPropertyOfTheElementToDataStorage(WebElementPropertyParameter elementProperty,
                                                                   WebElementParameter<WebChildElement> elementFinder,
                                                                   DataStorageParameter<String, String> dataStorage,
                                                                   ValueStringParameter key) {
        getWebPageContext().execute(context -> {
            WebChildElement element = elementFinder.getElement(context, WebChildElement.class);
            String textToSave = element.getPropertyValue(elementProperty.getRaw());
            dataStorage.get().put(key.getProcessedValue(), textToSave);
        });
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("user saves text from the {webElement} to the clipboard")
    @Given("пользователь сохраняет текст из {webElement} в буфер обмена")
    public void userSavesTextFromTheElementToTheClipboard(WebElementParameter<GetTextAvailable> elementFinder) {
        getWebPageContext().execute(context -> {
            GetTextAvailable element = elementFinder.getElement(context, GetTextAvailable.class);
            String textToCopy = element.getText();
            copyToClipboard(textToCopy);
        });
    }

    /**
     *
     * @param elementProperty -
     * @param elementFinder -
     */
    // TODO: Multiple context not available
    @Given("user saves text from property {webElementProperty} of the {webElement} to the clipboard")
    @Given("пользователь сохраняет текст из свойства {webElementProperty} элемента {webElement} в буфер обмена")
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
    @Given("пользователь вставляет текст из буфера обмена в {webElement}")
    public void userPasteTextFromTheClipboardToTheElement(WebElementParameter<SendKeysAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, SendKeysAvailable.class)
                        .sendKeys(getFromClipboard()));
    }

}
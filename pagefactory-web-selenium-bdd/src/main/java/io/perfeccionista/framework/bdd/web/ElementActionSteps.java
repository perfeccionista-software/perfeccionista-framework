package io.perfeccionista.framework.bdd.web;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.EnvironmentAvailable;
import io.perfeccionista.framework.bdd.parameters.DataStorageParameter;
import io.perfeccionista.framework.bdd.parameters.ValueStringParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementActionParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.bdd.parameters.WebElementPropertyParameter;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.HoverToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.ScrollToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SubmitAvailable;

import java.util.List;

import static io.perfeccionista.framework.utils.ToolkitUtils.copyToClipboard;
import static io.perfeccionista.framework.utils.ToolkitUtils.getFromClipboard;
import static java.util.stream.Collectors.toList;

// TODO: Add to exception context limiter information
// TODO: Wrap runLogic()
// TODO: Add step categories
// TODO: Как сделать снятие скриншотов конфигурируемым из проекта (для каждого метода)
public class ElementActionSteps implements EnvironmentAvailable {

    /**
     *
     * @param webElementAction -
     * @param elementFinder -
     */
    @Given("user on the {webElement} executes {webElementAction}")
    @Given("пользователь на {webElement} выполняет {webElementAction}")
    public void userExecutesWebElementActionOnTheElement(WebElementActionParameter webElementAction,
                                                         WebElementParameter<WebChildElement> elementFinder) {
        elementFinder.find()
                .forEachOrdered(element -> element.executeAction(webElementAction.getRaw()));
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
                                                               WebElementActionParameter webElementInteraction,
                                                               WebElementParameter<WebChildElement> elementTargetFinder) {
        List<WebChildElement> sourceElements = elementSourceFinder.find().collect(toList());
        List<WebChildElement> targetElements = elementTargetFinder.find().collect(toList());
        for (int i = 0; i < sourceElements.size(); i++) {
            sourceElements.get(i).executeInteraction(webElementInteraction.getRaw(), targetElements.get(i));
        }
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("user clicks on the {webElement}")
    @Given("пользователь нажимает на {webElement}")
    public void userClicksOnTheElement(WebElementParameter<ClickAvailable> elementFinder) {
        elementFinder.find()
                .forEachOrdered(ClickAvailable::click);
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("user clears the text in the {webElement}")
    @Given("пользователь очищает текст в {webElement}")
    public void userClearsTheTextInTheElement(WebElementParameter<ClearAvailable> elementFinder) {
        elementFinder.find()
                .forEachOrdered(ClearAvailable::clear);
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
        elementFinder.find()
                .forEachOrdered(element -> element.sendKeys(textToEnter.getProcessedValue()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("user scroll the page to the {webElement}")
    @Given("пользователь прокручивает страницу до {webElement}")
    public void userScrollThePageToTheElement(WebElementParameter<ScrollToAvailable> elementFinder) {
        elementFinder.find()
                .forEachOrdered(ScrollToAvailable::scrollTo);
    }

    /**
     *
     * @param elementFinder -
     */
    // TODO: Multiple context not available. В принципе, это тоже возможно тут сделать
    @Given("user hovers the cursor over the {webElement}")
    @Given("пользователь наводит курсор на {webElement}")
    public void userHoversTheCursorOverTheElement(WebElementParameter<HoverToAvailable> elementFinder) {
        elementFinder.findSingle()
                .hoverTo(true);
    }

    /**
     *
     * @param elementFinders -
     */
    // TODO: Multiple context not available. В принципе, это тоже возможно тут сделать
    @Given("user hovers the cursor sequentially to")
    @Given("пользователь поочередно наводит курсор на")
    public void userHoversTheCursorOverTheElement(List<WebElementParameter<HoverToAvailable>> elementFinders) {
        elementFinders.get(0).findSingle().hoverTo(true);
        for (int i = 1; i < elementFinders.size(); i++) {
            elementFinders.get(i).findSingle().hoverTo(false);
        }
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
        elementFinder.find()
                .forEachOrdered(element -> {
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
        elementFinder.find()
                .forEachOrdered(element -> {
                    String textToSave = element.getPropertyValue(elementProperty.getRaw());
                    dataStorage.get().put(key.getProcessedValue(), textToSave);
                });
    }

    /**
     *
     * @param elementFinder -
     */
    // TODO: Multiple context not available
    @Given("user saves text from the {webElement} to the clipboard")
    @Given("пользователь сохраняет текст из {webElement} в буфер обмена")
    public void userSavesTextFromTheElementToTheClipboard(WebElementParameter<GetTextAvailable> elementFinder) {
        String textToCopy = elementFinder.findSingle()
                .getText();
        copyToClipboard(textToCopy);
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
        String textToCopy = elementFinder.findSingle()
                .getPropertyValue(elementProperty.getRaw());
        copyToClipboard(textToCopy);
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("user paste text from the clipboard to the {webElement}")
    @Given("пользователь вставляет текст из буфера обмена в {webElement}")
    public void userPasteTextFromTheClipboardToTheElement(WebElementParameter<SendKeysAvailable> elementFinder) {
        elementFinder.find()
                .forEachOrdered(element -> element.sendKeys(getFromClipboard()));
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("user submits the form {webElement}")
    @Given("пользователь отправляет форму {webElement}")
    public void userSubmitsTheForm(WebElementParameter<SubmitAvailable> elementFinder) {
        elementFinder.find()
                .forEachOrdered(SubmitAvailable::submit);
    }

}

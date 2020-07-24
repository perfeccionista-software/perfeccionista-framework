package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebTextAutocomplete;
import io.perfeccionista.framework.pagefactory.elements.WebTextDropDownList;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;

public interface TextListElementsPage extends AbstractWebPage {

////    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
//    WebTextDropDownList simpleDropDownList();
//
////    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
//    WebTextAutocomplete simpleAutocomplete();

    @Name("Text list of countries")
    @WebLocator(id = "text-list")
    @WebLocator(component = LI, xpath = "self::node()//div[@itemid = 'text-list-item']", single = false)
    WebTextList textList();

    /** Список стран для тестирования списков + ДОЛЖНЫ БЫТЬ 2 ПУСТЫХ БЛОКА ДЛЯ ПРОВЕРКИ DISPLAYED_CONDITION
     * По-английски
     * Abkhazia
     * Australia
     * Austria
     * Azerbaijan
     * Aland Islands
     * Albania
     * Algeria
     * Anguilla
     * Angola
     * Andorra
     * Argentina
     * Armenia
     * Aruba
     * Afghanistan
     * Bahamas
     * Bangladesh
     * Barbados
     * Bahrain
     * Belarus
     * Belize
     * Belgium
     * Benin
     * Bulgaria
     * Bolivia
     * Bosnia & Herzegovina
     * Botswana
     * Brazil
     * Brunei Darussalam
     * Burundi
     * Bhutan
     * Vatican City
     * United Kingdom
     * Hungary
     * Venezuela
     * Timor, East
     * Viet Nam
     * Gabon
     * Haiti
     * Gambia
     * Ghana
     * Guadeloupe
     * Guatemala
     * Guinea
     * Guinea-Bissau
     * Germany
     * Gibraltar
     * Hong Kong
     * Honduras
     * Grenada
     * Greenland
     * Greece
     * Georgia
     * Guam
     * Denmark
     * Dominica
     * Dominican Republic
     * Egypt
     * Zambia
     * Western Sahara
     * Zimbabwe
     * Israel
     * India
     * Indonesia
     * Jordan
     * Iraq
     * Iran
     * Ireland
     * Iceland
     * Spain
     * Italy
     * Yemen
     * Kazakhstan
     * Cambodia
     * Cameroon
     * Canada
     * Qatar
     * Kenya
     * Cyprus
     * Kyrgyzstan
     * Kiribati
     * China
     * Colombia
     * Korea, D.P.R.
     * Korea
     * Costa Rica
     * Cote d'Ivoire
     * Cuba
     * Kuwait
     * Lao P.D.R.
     * Latvia
     * Lesotho
     * Liberia
     * Lebanon
     * Libyan Arab Jamahiriya
     * Lithuania
     * Liechtenstein
     * Luxembourg
     * Mauritius
     * Mauritania
     * Madagascar
     * Macedonia
     * Malawi
     * Malaysia
     * Mali
     * Maldives
     * Malta
     * Morocco
     * Mexico
     * Mozambique
     * Moldova
     * Monaco
     * Mongolia
     * Namibia
     * Nepal
     * Niger
     * Nigeria
     * Netherlands
     * Nicaragua
     * New Zealand
     * Norway
     * United Arab Emirates
     * Oman
     * Pakistan
     * Panama
     * Paraguay
     * Peru
     * Poland
     * Portugal
     * Russia
     * Romania
     * San Marino
     * Saudi Arabia
     * Senegal
     * Serbia
     * Singapore
     * Syrian Arab Republic
     * Slovakia
     * Slovenia
     * Somalia
     * Sudan
     * USA
     * Tajikistan
     * Thailand
     * Tanzania
     * Togo
     * Tunisia
     * Turkmenistan
     * Turkey
     * Uganda
     * Uzbekistan
     * Ukraine
     * Uruguay
     * Micronesia
     * Fiji
     * Philippines
     * Finland
     * France
     * Croatia
     * Chad
     * Montenegro
     * Czech Republic
     * Chile
     * Switzerland
     * Sweden
     * Sri Lanka
     * Ecuador
     * Eritrea
     * Estonia
     * Ethiopia
     * South Africa
     * Jamaica
     * Japan
     */
}
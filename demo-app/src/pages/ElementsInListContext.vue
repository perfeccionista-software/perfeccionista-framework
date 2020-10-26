<template>
    <div id="elements-block-context-page">
        <b-container>
            <b-row>
                <b-col><h3 class="page-title">{{ pageTitle }}</h3></b-col>
            </b-row>

            <!-- List -->
            <b-row class="mb-3 text-center" align-v="center">
                <b-col cols="12">
                    <b-list-group id="continents-list">
                        <b-list-group-item itemid="continents-list-item" v-for="continent in continentsData" v-bind:key="continent.name">
                            <b-row>
                                <b-col cols="2">
                                    <span itemid="continent-name" class="mr-2" v-b-tooltip.hover :title="continent.name">{{ continent.name }}</span>
                                    <b-badge itemid="continent-total" variant="primary" v-b-tooltip.hover :title="continent.total" pill>{{ continent.total }}</b-badge>
                                </b-col>
                                <b-col itemid="countries-list" cols="10" class="border-left">
                                    <b-row class="mb-3 text-center" itemid="countries-list-item" v-for="country in continent.countries" v-bind:key="country.name">
                                        <b-col cols="1">
                                            <b-checkbox itemid="checkbox" :id="country.name" :checked="country.selected" :disabled="!country.enabled">{{ country.number}}</b-checkbox>
                                        </b-col>
                                        <b-col cols="1">
                                            <span itemid="number">{{ country.number }}</span>
                                        </b-col>
                                        <b-col cols="3">
                                            <b-link itemid="country-name" class="mr-2" v-b-tooltip.hover :title="country.name" :href="country.wikiLink" v-if="country.wikiLink!==null">{{ country.name }}</b-link>
                                        </b-col>
                                        <b-col cols="5">
                                            <span itemid="country-full-name" class="mr-2" v-b-tooltip.hover :title="country.fullName">{{ country.fullName }}
                                                <!-- Not present -->
                                                <b-badge class="ml-2" itemid="sng" v-if="country.sng">СНГ</b-badge>
                                            </span>
                                        </b-col>
                                        <b-col cols="2">
                                            <!-- Not present -->
                                            <span itemid="population-number" v-if="country.population">{{ formatPopulation(country.population) }}</span>
                                            <!-- Not displayed -->
                                            <span itemid="population-unit" v-show="country.population">&nbsp;тыс</span>
                                        </b-col>
                                    </b-row>
                                </b-col>
                            </b-row>
                        </b-list-group-item>
                    </b-list-group>
                </b-col>
            </b-row>

        </b-container>
    </div>
</template>

<script>
export default {
    data: function() {
        return {
            pageTitle: 'Список в списке',

            toastCount: 195,
            continentsData: [
                {
                    name: 'Eurasia',
                    total: 91,
                    countries: [
                        {
                            number: 1,
                            name: 'Словакия',
                            fullName: 'Словацкая Республика',
                            sng: false,
                            population: 5449176,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D0%BB%D0%BE%D0%B2%D0%B0%D0%BA%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 2,
                            name: 'Индонезия',
                            fullName: 'Республика Индонезия',
                            sng: false,
                            population: 266911900,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%98%D0%BD%D0%B4%D0%BE%D0%BD%D0%B5%D0%B7%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 3,
                            name: 'Сирия',
                            fullName: 'Сирийская Арабская Республика',
                            sng: false,
                            population: 17070000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D0%B8%D1%80%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {number: 4, name: '', fullName: '', sng: false, population: null, wikiLink: null, selected: false, enabled: true},
                        {
                            number: 5,
                            name: 'Босния и Герцеговина',
                            fullName: 'Босния и Герцеговина',
                            sng: false,
                            population: 3511372,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%91%D0%BE%D1%81%D0%BD%D0%B8%D1%8F_%D0%B8_%D0%93%D0%B5%D1%80%D1%86%D0%B5%D0%B3%D0%BE%D0%B2%D0%B8%D0%BD%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 6,
                            name: 'Венгрия',
                            fullName: 'Венгрия',
                            sng: false,
                            population: 9722556,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%92%D0%B5%D0%BD%D0%B3%D1%80%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 7,
                            name: 'Германия',
                            fullName: 'Федеративная Республика Германия',
                            sng: false,
                            population: 83149300,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%93%D0%B5%D1%80%D0%BC%D0%B0%D0%BD%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 8,
                            name: 'Шри-Ланка',
                            fullName: 'Демократическая Социалистическая Республика Шри-Ланка',
                            sng: false,
                            population: 21803000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A8%D1%80%D0%B8-%D0%9B%D0%B0%D0%BD%D0%BA%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 9,
                            name: 'Грузия',
                            fullName: 'Грузия',
                            sng: false,
                            population: 3723500,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%93%D1%80%D1%83%D0%B7%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 10,
                            name: 'Люксембург',
                            fullName: 'Великое Герцогство Люксембург',
                            sng: false,
                            population: 613894,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9B%D1%8E%D0%BA%D1%81%D0%B5%D0%BC%D0%B1%D1%83%D1%80%D0%B3',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 11,
                            name: 'Восточный Тимор',
                            fullName: 'Демократическая Республика Восточный Тимор',
                            sng: false,
                            population: 1212107,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%92%D0%BE%D1%81%D1%82%D0%BE%D1%87%D0%BD%D1%8B%D0%B9_%D0%A2%D0%B8%D0%BC%D0%BE%D1%80',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 12,
                            name: 'Норвегия',
                            fullName: 'Королевство Норвегия',
                            sng: false,
                            population: 5506800,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9D%D0%BE%D1%80%D0%B2%D0%B5%D0%B3%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 13,
                            name: 'Ирландия',
                            fullName: 'Ирландия',
                            sng: false,
                            population: 4921500,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%98%D1%80%D0%BB%D0%B0%D0%BD%D0%B4%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 14,
                            name: 'Филиппины',
                            fullName: 'Республика Филиппины',
                            sng: false,
                            population: 108572750,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A4%D0%B8%D0%BB%D0%B8%D0%BF%D0%BF%D0%B8%D0%BD%D1%8B',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 15,
                            name: 'Испания',
                            fullName: 'Королевство Испания',
                            sng: false,
                            population: 46934632,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%98%D1%81%D0%BF%D0%B0%D0%BD%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 16,
                            name: 'Россия',
                            fullName: 'Российская Федерация',
                            sng: true,
                            population: 146748590,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A0%D0%BE%D1%81%D1%81%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 17,
                            name: 'Швейцария',
                            fullName: 'Швейцарская Конфедерация',
                            sng: false,
                            population: 8570146,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A8%D0%B2%D0%B5%D0%B9%D1%86%D0%B0%D1%80%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 18,
                            name: 'Катар',
                            fullName: 'Государство Катар',
                            sng: false,
                            population: 2753045,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9A%D0%B0%D1%82%D0%B0%D1%80',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 19,
                            name: 'Пакистан',
                            fullName: 'Исламская Республика Пакистан',
                            sng: false,
                            population: 217569002,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9F%D0%B0%D0%BA%D0%B8%D1%81%D1%82%D0%B0%D0%BD',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 20,
                            name: 'Оман',
                            fullName: 'Султанат Оман',
                            sng: false,
                            population: 4088690,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9E%D0%BC%D0%B0%D0%BD',
                            selected: true,
                            enabled: true
                        },
                        {
                            number: 21,
                            name: 'Бангладеш',
                            fullName: 'Народная Республика Бангладеш',
                            sng: false,
                            population: 170509761,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%91%D0%B0%D0%BD%D0%B3%D0%BB%D0%B0%D0%B4%D0%B5%D1%88',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 22,
                            name: 'Болгария',
                            fullName: 'Республика Болгария',
                            sng: false,
                            population: 7000039,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%91%D0%BE%D0%BB%D0%B3%D0%B0%D1%80%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 23,
                            name: 'Бутан',
                            fullName: 'Королевство Бутан',
                            sng: false,
                            population: 763000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%91%D1%83%D1%82%D0%B0%D0%BD',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 24,
                            name: 'Франция',
                            fullName: 'Французская Республика',
                            sng: false,
                            population: 68859599,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A4%D1%80%D0%B0%D0%BD%D1%86%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 25,
                            name: 'Мальдивы',
                            fullName: 'Мальдивская Республика',
                            sng: false,
                            population: 402071,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9C%D0%B0%D0%BB%D1%8C%D0%B4%D0%B8%D0%B2%D1%8B',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 26,
                            name: 'Андорра',
                            fullName: 'Княжество Андорра',
                            sng: false,
                            population: 76177,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%90%D0%BD%D0%B4%D0%BE%D1%80%D1%80%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 27,
                            name: 'Украина',
                            fullName: 'Украина',
                            sng: false,
                            population: 41710267,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A3%D0%BA%D1%80%D0%B0%D0%B8%D0%BD%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 28,
                            name: 'Австрия',
                            fullName: 'Австрийская Республика',
                            sng: false,
                            population: 8902600,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%90%D0%B2%D1%81%D1%82%D1%80%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 29,
                            name: 'Черногория',
                            fullName: 'Черногория',
                            sng: false,
                            population: 622218,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A7%D0%B5%D1%80%D0%BD%D0%BE%D0%B3%D0%BE%D1%80%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 30,
                            name: 'Киргизия',
                            fullName: 'Киргизская Республика',
                            sng: true,
                            population: 6523500,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9A%D0%B8%D1%80%D0%B3%D0%B8%D0%B7%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 31,
                            name: 'Португалия',
                            fullName: 'Португальская Республика',
                            sng: false,
                            population: 10276617,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D1%80%D1%82%D1%83%D0%B3%D0%B0%D0%BB%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 32,
                            name: 'Швеция',
                            fullName: 'Королевство Швеция',
                            sng: false,
                            population: 10313447,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A8%D0%B2%D0%B5%D1%86%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 33,
                            name: 'Туркмения',
                            fullName: 'Туркменистан',
                            sng: false,
                            population: 5942000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A2%D1%83%D1%80%D0%BA%D0%BC%D0%B5%D0%BD%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 34,
                            name: 'Бахрейн',
                            fullName: 'Королевство Бахрейн',
                            sng: false,
                            population: 1451200,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%91%D0%B0%D1%85%D1%80%D0%B5%D0%B9%D0%BD',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 35,
                            name: 'Казахстан',
                            fullName: 'Республика Казахстан',
                            sng: true,
                            population: 18700300,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9A%D0%B0%D0%B7%D0%B0%D1%85%D1%81%D1%82%D0%B0%D0%BD',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 36,
                            name: 'Великобритания',
                            fullName: 'Соединённое Королевство Великобритании и Северной Ирландии',
                            sng: false,
                            population: 66647112,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%92%D0%B5%D0%BB%D0%B8%D0%BA%D0%BE%D0%B1%D1%80%D0%B8%D1%82%D0%B0%D0%BD%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 37,
                            name: 'Азербайджан',
                            fullName: 'Азербайджанская Республика',
                            sng: true,
                            population: 9981500,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%90%D0%B7%D0%B5%D1%80%D0%B1%D0%B0%D0%B9%D0%B4%D0%B6%D0%B0%D0%BD',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 38,
                            name: 'Афганистан',
                            fullName: 'Исламская Республика Афганистан',
                            sng: false,
                            population: 32225560,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%90%D1%84%D0%B3%D0%B0%D0%BD%D0%B8%D1%81%D1%82%D0%B0%D0%BD',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 39,
                            name: 'Бруней',
                            fullName: 'Государство Бруней-Даруссалам',
                            sng: false,
                            population: 433000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%91%D1%80%D1%83%D0%BD%D0%B5%D0%B9',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 40,
                            name: 'Хорватия',
                            fullName: 'Республика Хорватия',
                            sng: false,
                            population: 4076246,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A5%D0%BE%D1%80%D0%B2%D0%B0%D1%82%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 41,
                            name: 'Лихтенштейн',
                            fullName: 'Княжество Лихтенштейн',
                            sng: false,
                            population: 38378,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9B%D0%B8%D1%85%D1%82%D0%B5%D0%BD%D1%88%D1%82%D0%B5%D0%B9%D0%BD',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 42,
                            name: 'Саудовская Аравия',
                            fullName: 'Королевство Саудовская Аравия',
                            sng: false,
                            population: 34218169,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D0%B0%D1%83%D0%B4%D0%BE%D0%B2%D1%81%D0%BA%D0%B0%D1%8F_%D0%90%D1%80%D0%B0%D0%B2%D0%B8%D1%8F',
                            selected: false,
                            enabled: false
                        },
                        {
                            number: 43,
                            name: 'Ливан',
                            fullName: 'Ливанская Республика',
                            sng: false,
                            population: 6856000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9B%D0%B8%D0%B2%D0%B0%D0%BD',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 44,
                            name: 'Румыния',
                            fullName: 'Румыния',
                            sng: false,
                            population: 19401658,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A0%D1%83%D0%BC%D1%8B%D0%BD%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 45,
                            name: 'Ирак',
                            fullName: 'Республика Ирак',
                            sng: false,
                            population: 39310000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%98%D1%80%D0%B0%D0%BA',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 46,
                            name: 'КНДР',
                            fullName: 'Корейская Народно-Демократическая Республика',
                            sng: false,
                            population: 25666000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9A%D0%BE%D1%80%D0%B5%D0%B9%D1%81%D0%BA%D0%B0%D1%8F_%D0%9D%D0%B0%D1%80%D0%BE%D0%B4%D0%BD%D0%BE-%D0%94%D0%B5%D0%BC%D0%BE%D0%BA%D1%80%D0%B0%D1%82%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B0%D1%8F_%D0%A0%D0%B5%D1%81%D0%BF%D1%83%D0%B1%D0%BB%D0%B8%D0%BA%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 47,
                            name: 'Монголия',
                            fullName: 'Монголия',
                            sng: false,
                            population: 3318339,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9C%D0%BE%D0%BD%D0%B3%D0%BE%D0%BB%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 48,
                            name: 'Мальта',
                            fullName: 'Республика Мальта',
                            sng: false,
                            population: 493559,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9C%D0%B0%D0%BB%D1%8C%D1%82%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 49,
                            name: 'Корея',
                            fullName: 'Республика Корея',
                            sng: false,
                            population: null,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A0%D0%B5%D1%81%D0%BF%D1%83%D0%B1%D0%BB%D0%B8%D0%BA%D0%B0_%D0%9A%D0%BE%D1%80%D0%B5%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 50,
                            name: 'Словения',
                            fullName: 'Республика Словения',
                            sng: false,
                            population: 2110550,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D0%BB%D0%BE%D0%B2%D0%B5%D0%BD%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 51,
                            name: 'Иран',
                            fullName: 'Исламская Республика Иран',
                            sng: false,
                            population: 84062042,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%98%D1%80%D0%B0%D0%BD',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 52,
                            name: 'Молдавия',
                            fullName: 'Республика Молдова',
                            sng: true,
                            population: 3546500,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9C%D0%BE%D0%BB%D0%B4%D0%B0%D0%B2%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 53,
                            name: 'Мьянма',
                            fullName: 'Республика Союз Мьянма',
                            sng: false,
                            population: 54045000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9C%D1%8C%D1%8F%D0%BD%D0%BC%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 54,
                            name: 'Италия',
                            fullName: 'Итальянская Республика',
                            sng: false,
                            population: 60238522,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%98%D1%82%D0%B0%D0%BB%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 55,
                            name: 'Камбоджа',
                            fullName: 'Королевство Камбоджа',
                            sng: false,
                            population: 15288489,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9A%D0%B0%D0%BC%D0%B1%D0%BE%D0%B4%D0%B6%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 56,
                            name: 'ОАЭ',
                            fullName: 'Объединённые Арабские Эмираты',
                            sng: false,
                            population: 9771000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9E%D0%B1%D1%8A%D0%B5%D0%B4%D0%B8%D0%BD%D1%91%D0%BD%D0%BD%D1%8B%D0%B5_%D0%90%D1%80%D0%B0%D0%B1%D1%81%D0%BA%D0%B8%D0%B5_%D0%AD%D0%BC%D0%B8%D1%80%D0%B0%D1%82%D1%8B',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 57,
                            name: 'Узбекистан',
                            fullName: 'Республика Узбекистан',
                            sng: true,
                            population: 34143918,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A3%D0%B7%D0%B1%D0%B5%D0%BA%D0%B8%D1%81%D1%82%D0%B0%D0%BD',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 58,
                            name: 'Литва',
                            fullName: 'Литовская Республика',
                            sng: false,
                            population: 2790472,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9B%D0%B8%D1%82%D0%B2%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 59,
                            name: 'Таджикистан',
                            fullName: 'Республика Таджикистан',
                            sng: true,
                            population: 9127000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A2%D0%B0%D0%B4%D0%B6%D0%B8%D0%BA%D0%B8%D1%81%D1%82%D0%B0%D0%BD',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 60,
                            name: 'Индия',
                            fullName: 'Республика Индия',
                            sng: false,
                            population: 1370959000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%98%D0%BD%D0%B4%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 61,
                            name: 'Польша',
                            fullName: 'Республика Польша',
                            sng: false,
                            population: 38313035,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D0%BB%D1%8C%D1%88%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 62,
                            name: 'Эстония',
                            fullName: 'Эстонская Республика',
                            sng: false,
                            population: 1328360,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%AD%D1%81%D1%82%D0%BE%D0%BD%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 63,
                            name: 'Дания',
                            fullName: 'Королевство Дания',
                            sng: false,
                            population: 5762082,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%94%D0%B0%D0%BD%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 64,
                            name: 'Сингапур',
                            fullName: 'Республика Сингапур',
                            sng: false,
                            population: 5703600,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D0%B8%D0%BD%D0%B3%D0%B0%D0%BF%D1%83%D1%80',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 65,
                            name: 'Йемен',
                            fullName: 'Йеменская Республика',
                            sng: false,
                            population: 29162000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%99%D0%B5%D0%BC%D0%B5%D0%BD',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 66,
                            name: 'Вьетнам',
                            fullName: 'Социалистическая Республика Вьетнам',
                            sng: false,
                            population: 96208984,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%92%D1%8C%D0%B5%D1%82%D0%BD%D0%B0%D0%BC',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 67,
                            name: 'Северная Македония',
                            fullName: 'Республика Северная Македония',
                            sng: false,
                            population: 2077132,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D0%B5%D0%B2%D0%B5%D1%80%D0%BD%D0%B0%D1%8F_%D0%9C%D0%B0%D0%BA%D0%B5%D0%B4%D0%BE%D0%BD%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 68,
                            name: 'Китай',
                            fullName: 'Китайская Народная Республика',
                            sng: false,
                            population: 1402884000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9A%D0%B8%D1%82%D0%B0%D0%B9',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 69,
                            name: 'Япония',
                            fullName: 'Япония',
                            sng: false,
                            population: 125950000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%AF%D0%BF%D0%BE%D0%BD%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 70,
                            name: 'Таиланд',
                            fullName: 'Королевство Таиланд',
                            sng: false,
                            population: 66506085,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A2%D0%B0%D0%B8%D0%BB%D0%B0%D0%BD%D0%B4',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 71,
                            name: 'Исландия',
                            fullName: 'Исландия',
                            sng: false,
                            population: 356991,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%98%D1%81%D0%BB%D0%B0%D0%BD%D0%B4%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 72,
                            name: 'Малайзия',
                            fullName: 'Малайзия',
                            sng: false,
                            population: 32825025,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9C%D0%B0%D0%BB%D0%B0%D0%B9%D0%B7%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 73,
                            name: 'Израиль',
                            fullName: 'Государство Израиль',
                            sng: false,
                            population: 9136000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%98%D0%B7%D1%80%D0%B0%D0%B8%D0%BB%D1%8C',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 74,
                            name: 'Сербия',
                            fullName: 'Республика Сербия',
                            sng: false,
                            population: 6963764,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D0%B5%D1%80%D0%B1%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 75,
                            name: 'Турция',
                            fullName: 'Турецкая Республика',
                            sng: false,
                            population: 83154997,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A2%D1%83%D1%80%D1%86%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 76,
                            name: 'Чехия',
                            fullName: 'Чешская Республика',
                            sng: false,
                            population: 10693939,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A7%D0%B5%D1%85%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 77,
                            name: 'Финляндия',
                            fullName: 'Финляндская Республика',
                            sng: false,
                            population: 5526774,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A4%D0%B8%D0%BD%D0%BB%D1%8F%D0%BD%D0%B4%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 78,
                            name: 'Лаос',
                            fullName: 'Лаосская Народно-Демократическая Республика',
                            sng: false,
                            population: 7123205,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9B%D0%B0%D0%BE%D1%81',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 79,
                            name: 'Бельгия',
                            fullName: 'Королевство Бельгия',
                            sng: false,
                            population: 11476279,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%91%D0%B5%D0%BB%D1%8C%D0%B3%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 80,
                            name: 'Сан-Марино',
                            fullName: 'Республика Сан-Марино',
                            sng: false,
                            population: 34590,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D0%B0%D0%BD-%D0%9C%D0%B0%D1%80%D0%B8%D0%BD%D0%BE',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 81,
                            name: 'Непал',
                            fullName: 'Федеративная Демократическая Республика Непал',
                            sng: false,
                            population: 28609000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9D%D0%B5%D0%BF%D0%B0%D0%BB',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 82,
                            name: 'Албания',
                            fullName: 'Республика Албания',
                            sng: false,
                            population: 2845955,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%90%D0%BB%D0%B1%D0%B0%D0%BD%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 83,
                            name: 'Иордания',
                            fullName: 'Иорданское Хашимитское Королевство',
                            sng: false,
                            population: 10622300,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%98%D0%BE%D1%80%D0%B4%D0%B0%D0%BD%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 84,
                            name: 'Греция',
                            fullName: 'Греческая Республика',
                            sng: false,
                            population: 10741165,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%93%D1%80%D0%B5%D1%86%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 85,
                            name: 'Армения',
                            fullName: 'Республика Армения',
                            sng: true,
                            population: 2962100,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%90%D1%80%D0%BC%D0%B5%D0%BD%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 86,
                            name: 'Латвия',
                            fullName: 'Латвийская Республика',
                            sng: false,
                            population: 1908100,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9B%D0%B0%D1%82%D0%B2%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 87,
                            name: 'Кипр',
                            fullName: 'Республика Кипр',
                            sng: false,
                            population: 875898,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A0%D0%B5%D1%81%D0%BF%D1%83%D0%B1%D0%BB%D0%B8%D0%BA%D0%B0_%D0%9A%D0%B8%D0%BF%D1%80',
                            selected: true,
                            enabled: true
                        },
                        {
                            number: 88,
                            name: 'Кувейт',
                            fullName: 'Государство Кувейт',
                            sng: false,
                            population: 4207000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9A%D1%83%D0%B2%D0%B5%D0%B9%D1%82',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 89,
                            name: 'Монако',
                            fullName: 'Княжество Монако',
                            sng: false,
                            population: 38300,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9C%D0%BE%D0%BD%D0%B0%D0%BA%D0%BE',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 90,
                            name: 'Белоруссия',
                            fullName: 'Республика Беларусь',
                            sng: true,
                            population: 9408400,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%91%D0%B5%D0%BB%D0%BE%D1%80%D1%83%D1%81%D1%81%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 91,
                            name: 'Нидерланды',
                            fullName: 'Королевство Нидерландов',
                            sng: false,
                            population: 17440045,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9D%D0%B8%D0%B4%D0%B5%D1%80%D0%BB%D0%B0%D0%BD%D0%B4%D1%8B',
                            selected: false,
                            enabled: true
                        }
                    ]
                },
                {
                    name: 'Africa',
                    total: 55,
                    countries: [
                        {
                            number: 1,
                            name: 'Уганда',
                            fullName: 'Республика Уганда',
                            sng: false,
                            population: 40006700,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A3%D0%B3%D0%B0%D0%BD%D0%B4%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 2,
                            name: 'Ливия',
                            fullName: 'Ливия',
                            sng: false,
                            population: 6777000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9B%D0%B8%D0%B2%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 3,
                            name: 'Коморы',
                            fullName: 'Союз Коморских Островов',
                            sng: false,
                            population: 806153,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9A%D0%BE%D0%BC%D0%BE%D1%80%D1%8B',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 4,
                            name: 'Мозамбик',
                            fullName: 'Республика Мозамбик',
                            sng: false,
                            population: 30366000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9C%D0%BE%D0%B7%D0%B0%D0%BC%D0%B1%D0%B8%D0%BA',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 5,
                            name: 'Руанда',
                            fullName: 'Республика Руанда',
                            sng: false,
                            population: 11952693,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A0%D1%83%D0%B0%D0%BD%D0%B4%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 6,
                            name: 'Египет',
                            fullName: 'Арабская Республика Египет',
                            sng: false,
                            population: 100330800,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%95%D0%B3%D0%B8%D0%BF%D0%B5%D1%82',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 7,
                            name: 'Камерун',
                            fullName: 'Республика Камерун',
                            sng: false,
                            population: 24348251,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9A%D0%B0%D0%BC%D0%B5%D1%80%D1%83%D0%BD',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 8,
                            name: 'Намибия',
                            fullName: 'Республика Намибия',
                            sng: false,
                            population: 2495000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9D%D0%B0%D0%BC%D0%B8%D0%B1%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 9,
                            name: 'Малави',
                            fullName: 'Республика Малави',
                            sng: false,
                            population: 17563749,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9C%D0%B0%D0%BB%D0%B0%D0%B2%D0%B8',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 10,
                            name: 'Танзания',
                            fullName: 'Объединённая Республика Танзания',
                            sng: false,
                            population: 55890747,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A2%D0%B0%D0%BD%D0%B7%D0%B0%D0%BD%D0%B8%D1%8F',
                            selected: false,
                            enabled: false
                        },
                        {
                            number: 11,
                            name: 'Нигерия',
                            fullName: 'Федеративная Республика Нигерия',
                            sng: false,
                            population: 206762296,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9D%D0%B8%D0%B3%D0%B5%D1%80%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 12,
                            name: 'Гвинея',
                            fullName: 'Гвинейская Республика',
                            sng: false,
                            population: 12771000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%93%D0%B2%D0%B8%D0%BD%D0%B5%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 13,
                            name: 'Судан',
                            fullName: 'Республика Судан',
                            sng: false,
                            population: 42530150,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D1%83%D0%B4%D0%B0%D0%BD',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 14,
                            name: 'Гамбия',
                            fullName: 'Республика Гамбия',
                            sng: false,
                            population: 2348000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%93%D0%B0%D0%BC%D0%B1%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 15,
                            name: 'Нигер',
                            fullName: 'Республика Нигер',
                            sng: false,
                            population: 22314743,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9D%D0%B8%D0%B3%D0%B5%D1%80',
                            selected: false,
                            enabled: false
                        },
                        {
                            number: 16,
                            name: 'Габон',
                            fullName: 'Габонская Республика',
                            sng: false,
                            population: 2173000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%93%D0%B0%D0%B1%D0%BE%D0%BD',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 17,
                            name: 'Маврикий',
                            fullName: 'Республика Маврикий',
                            sng: false,
                            population: 1261208,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9C%D0%B0%D0%B2%D1%80%D0%B8%D0%BA%D0%B8%D0%B9',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 18,
                            name: 'Джибути',
                            fullName: 'Республика Джибути',
                            sng: false,
                            population: 974000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%94%D0%B6%D0%B8%D0%B1%D1%83%D1%82%D0%B8',
                            selected: true,
                            enabled: true
                        },
                        {
                            number: 19,
                            name: "Кот-д'Ивуар",
                            fullName: "Республика Кот-д'Ивуар",
                            sng: false,
                            population: null,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9A%D0%BE%D1%82-%D0%B4%E2%80%99%D0%98%D0%B2%D1%83%D0%B0%D1%80',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 20,
                            name: 'Зимбабве',
                            fullName: 'Республика Зимбабве',
                            sng: false,
                            population: 14645000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%97%D0%B8%D0%BC%D0%B1%D0%B0%D0%B1%D0%B2%D0%B5',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 21,
                            name: 'Эфиопия',
                            fullName: 'Федеративная Демократическая Республика Эфиопия',
                            sng: false,
                            population: 112079000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%AD%D1%84%D0%B8%D0%BE%D0%BF%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 22,
                            name: 'Гана',
                            fullName: 'Республика Гана',
                            sng: false,
                            population: 30418000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%93%D0%B0%D0%BD%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 23,
                            name: 'Южный Судан',
                            fullName: 'Республика Южный Судан',
                            sng: false,
                            population: 11062000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%AE%D0%B6%D0%BD%D1%8B%D0%B9_%D0%A1%D1%83%D0%B4%D0%B0%D0%BD',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 24,
                            name: 'Лесото',
                            fullName: 'Королевство Лесото',
                            sng: false,
                            population: 2007201,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9B%D0%B5%D1%81%D0%BE%D1%82%D0%BE',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 25,
                            name: 'Кения',
                            fullName: 'Республика Кения',
                            sng: false,
                            population: 47564296,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9A%D0%B5%D0%BD%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 26,
                            name: 'Мавритания',
                            fullName: 'Исламская Республика Мавритания',
                            sng: false,
                            population: 3631775,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9C%D0%B0%D0%B2%D1%80%D0%B8%D1%82%D0%B0%D0%BD%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 27,
                            name: 'Гвинея-Бисау',
                            fullName: 'Республика Гвинея-Бисау',
                            sng: false,
                            population: 1921000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%93%D0%B2%D0%B8%D0%BD%D0%B5%D1%8F-%D0%91%D0%B8%D1%81%D0%B0%D1%83',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 28,
                            name: 'Сенегал',
                            fullName: 'Республика Сенегал',
                            sng: false,
                            population: 19256346,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D0%B5%D0%BD%D0%B5%D0%B3%D0%B0%D0%BB',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 29,
                            name: 'Сомали',
                            fullName: 'Федеративная Республика Сомали',
                            sng: false,
                            population: 15443000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D0%BE%D0%BC%D0%B0%D0%BB%D0%B8',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 30,
                            name: 'Буркина-Фасо',
                            fullName: 'Буркина-Фасо',
                            sng: false,
                            population: 20870060,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%91%D1%83%D1%80%D0%BA%D0%B8%D0%BD%D0%B0-%D0%A4%D0%B0%D1%81%D0%BE',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 31,
                            name: 'Ботсвана',
                            fullName: 'Республика Ботсвана',
                            sng: false,
                            population: 2304000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%91%D0%BE%D1%82%D1%81%D0%B2%D0%B0%D0%BD%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 32,
                            name: 'Сейшелы',
                            fullName: 'Республика Сейшельские Острова',
                            sng: false,
                            population: null,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D0%B5%D0%B9%D1%88%D0%B5%D0%BB%D1%8C%D1%81%D0%BA%D0%B8%D0%B5_%D0%9E%D1%81%D1%82%D1%80%D0%BE%D0%B2%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 33,
                            name: 'Сан-Томе и Принсипи',
                            fullName: 'Демократическая Республика Сан-Томе и Принсипи',
                            sng: false,
                            population: 215000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D0%B0%D0%BD-%D0%A2%D0%BE%D0%BC%D0%B5_%D0%B8_%D0%9F%D1%80%D0%B8%D0%BD%D1%81%D0%B8%D0%BF%D0%B8',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 34,
                            name: 'Ангола',
                            fullName: 'Республика Ангола',
                            sng: false,
                            population: 31825000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%90%D0%BD%D0%B3%D0%BE%D0%BB%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 35,
                            name: 'Эритрея',
                            fullName: 'Государство Эритрея',
                            sng: false,
                            population: 3497000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%AD%D1%80%D0%B8%D1%82%D1%80%D0%B5%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 36,
                            name: 'ЮАР',
                            fullName: 'Южно-Африканская Республика',
                            sng: false,
                            population: 54956900,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%AE%D0%B6%D0%BD%D0%BE-%D0%90%D1%84%D1%80%D0%B8%D0%BA%D0%B0%D0%BD%D1%81%D0%BA%D0%B0%D1%8F_%D0%A0%D0%B5%D1%81%D0%BF%D1%83%D0%B1%D0%BB%D0%B8%D0%BA%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 37,
                            name: 'ЦАР',
                            fullName: 'Центральноафриканская Республика',
                            sng: false,
                            population: 4745000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A6%D0%B5%D0%BD%D1%82%D1%80%D0%B0%D0%BB%D1%8C%D0%BD%D0%BE%D0%B0%D1%84%D1%80%D0%B8%D0%BA%D0%B0%D0%BD%D1%81%D0%BA%D0%B0%D1%8F_%D0%A0%D0%B5%D1%81%D0%BF%D1%83%D0%B1%D0%BB%D0%B8%D0%BA%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 38,
                            name: 'ДР Конго',
                            fullName: 'Демократическая Республика Конго',
                            sng: false,
                            population: null,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%94%D0%B5%D0%BC%D0%BE%D0%BA%D1%80%D0%B0%D1%82%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B0%D1%8F_%D0%A0%D0%B5%D1%81%D0%BF%D1%83%D0%B1%D0%BB%D0%B8%D0%BA%D0%B0_%D0%9A%D0%BE%D0%BD%D0%B3%D0%BE',
                            selected: false,
                            enabled: true
                        },
                        {number: 39, name: '', fullName: '', sng: false, population: null, wikiLink: null, selected: false, enabled: true},
                        {
                            number: 40,
                            name: 'Мадагаскар',
                            fullName: 'Республика Мадагаскар',
                            sng: false,
                            population: 25680342,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9C%D0%B0%D0%B4%D0%B0%D0%B3%D0%B0%D1%81%D0%BA%D0%B0%D1%80',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 41,
                            name: 'Алжир',
                            fullName: 'Алжирская Народная Демократическая Республика',
                            sng: false,
                            population: 43053000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%90%D0%BB%D0%B6%D0%B8%D1%80',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 42,
                            name: 'Чад',
                            fullName: 'Республика Чад',
                            sng: false,
                            population: 15948000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A7%D0%B0%D0%B4',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 43,
                            name: 'Бенин',
                            fullName: 'Республика Бенин',
                            sng: false,
                            population: 10315244,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%91%D0%B5%D0%BD%D0%B8%D0%BD',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 44,
                            name: 'Либерия',
                            fullName: 'Республика Либерия',
                            sng: false,
                            population: 4937000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9B%D0%B8%D0%B1%D0%B5%D1%80%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 45,
                            name: 'Замбия',
                            fullName: 'Республика Замбия',
                            sng: false,
                            population: 17861000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%97%D0%B0%D0%BC%D0%B1%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 46,
                            name: 'Марокко',
                            fullName: 'Королевство Марокко',
                            sng: false,
                            population: 35898950,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9C%D0%B0%D1%80%D0%BE%D0%BA%D0%BA%D0%BE',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 47,
                            name: 'Бурунди',
                            fullName: 'Республика Бурунди',
                            sng: false,
                            population: 11215578,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%91%D1%83%D1%80%D1%83%D0%BD%D0%B4%D0%B8',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 48,
                            name: 'Сьерра-Леоне',
                            fullName: 'Республика Сьерра-Леоне',
                            sng: false,
                            population: 7813000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D1%8C%D0%B5%D1%80%D1%80%D0%B0-%D0%9B%D0%B5%D0%BE%D0%BD%D0%B5',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 49,
                            name: 'Мали',
                            fullName: 'Республика Мали',
                            sng: false,
                            population: 19658000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9C%D0%B0%D0%BB%D0%B8',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 50,
                            name: 'Эсватини',
                            fullName: 'Королевство Эсватини',
                            sng: false,
                            population: 1148000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%AD%D1%81%D0%B2%D0%B0%D1%82%D0%B8%D0%BD%D0%B8',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 51,
                            name: 'Экваториальная Гвинея',
                            fullName: 'Республика Экваториальная Гвинея',
                            sng: false,
                            population: 1356000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%AD%D0%BA%D0%B2%D0%B0%D1%82%D0%BE%D1%80%D0%B8%D0%B0%D0%BB%D1%8C%D0%BD%D0%B0%D1%8F_%D0%93%D0%B2%D0%B8%D0%BD%D0%B5%D1%8F',
                            selected: false,
                            enabled: false
                        },
                        {
                            number: 52,
                            name: 'Того',
                            fullName: 'Тоголезская Республика',
                            sng: false,
                            population: 8082000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A2%D0%BE%D0%B3%D0%BE',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 53,
                            name: 'Тунис',
                            fullName: 'Тунисская Республика',
                            sng: false,
                            population: 10982754,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A2%D1%83%D0%BD%D0%B8%D1%81',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 54,
                            name: 'Конго',
                            fullName: 'Республика Конго',
                            sng: false,
                            population: null,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A0%D0%B5%D1%81%D0%BF%D1%83%D0%B1%D0%BB%D0%B8%D0%BA%D0%B0_%D0%9A%D0%BE%D0%BD%D0%B3%D0%BE',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 55,
                            name: 'Кабо-Верде',
                            fullName: 'Республика Кабо-Верде',
                            sng: false,
                            population: 550000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9A%D0%B0%D0%B1%D0%BE-%D0%92%D0%B5%D1%80%D0%B4%D0%B5',
                            selected: false,
                            enabled: true
                        }
                    ]
                },
                {
                    name: 'North America',
                    total: 23,
                    countries: [
                        {
                            number: 1,
                            name: 'Доминикана',
                            fullName: 'Доминиканская Республика',
                            sng: false,
                            population: null,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%94%D0%BE%D0%BC%D0%B8%D0%BD%D0%B8%D0%BA%D0%B0%D0%BD%D1%81%D0%BA%D0%B0%D1%8F_%D0%A0%D0%B5%D1%81%D0%BF%D1%83%D0%B1%D0%BB%D0%B8%D0%BA%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 2,
                            name: 'Коста-Рика',
                            fullName: 'Республика Коста-Рика',
                            sng: false,
                            population: 4953199,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9A%D0%BE%D1%81%D1%82%D0%B0-%D0%A0%D0%B8%D0%BA%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 3,
                            name: 'Тринидад и Тобаго',
                            fullName: 'Республика Тринидад и Тобаго',
                            sng: false,
                            population: 1395000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A2%D1%80%D0%B8%D0%BD%D0%B8%D0%B4%D0%B0%D0%B4_%D0%B8_%D0%A2%D0%BE%D0%B1%D0%B0%D0%B3%D0%BE',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 4,
                            name: 'Сент-Люсия',
                            fullName: 'Сент-Люсия',
                            sng: false,
                            population: 183000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D0%B5%D0%BD%D1%82-%D0%9B%D1%8E%D1%81%D0%B8%D1%8F',
                            selected: true,
                            enabled: true
                        },
                        {
                            number: 5,
                            name: 'Мексика',
                            fullName: 'Мексиканские Соединённые Штаты',
                            sng: false,
                            population: 126577691,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9C%D0%B5%D0%BA%D1%81%D0%B8%D0%BA%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 6,
                            name: 'Гаити',
                            fullName: 'Республика Гаити',
                            sng: false,
                            population: 10911819,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A0%D0%B5%D1%81%D0%BF%D1%83%D0%B1%D0%BB%D0%B8%D0%BA%D0%B0_%D0%93%D0%B0%D0%B8%D1%82%D0%B8',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 7,
                            name: 'Гватемала',
                            fullName: 'Республика Гватемала',
                            sng: false,
                            population: 17679735,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%93%D0%B2%D0%B0%D1%82%D0%B5%D0%BC%D0%B0%D0%BB%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 8,
                            name: 'Никарагуа',
                            fullName: 'Республика Никарагуа',
                            sng: false,
                            population: 6198154,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9D%D0%B8%D0%BA%D0%B0%D1%80%D0%B0%D0%B3%D1%83%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 9,
                            name: 'Антигуа и Барбуда',
                            fullName: 'Антигуа и Барбуда',
                            sng: false,
                            population: 97000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%90%D0%BD%D1%82%D0%B8%D0%B3%D1%83%D0%B0_%D0%B8_%D0%91%D0%B0%D1%80%D0%B1%D1%83%D0%B4%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 10,
                            name: 'Сальвадор',
                            fullName: 'Республика Эль-Сальвадор',
                            sng: false,
                            population: 6454000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D0%B0%D0%BB%D1%8C%D0%B2%D0%B0%D0%B4%D0%BE%D1%80',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 11,
                            name: 'Гренада',
                            fullName: 'Гренада',
                            sng: false,
                            population: 112000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%93%D1%80%D0%B5%D0%BD%D0%B0%D0%B4%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 12,
                            name: 'Канада',
                            fullName: 'Канада',
                            sng: false,
                            population: 38054960,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9A%D0%B0%D0%BD%D0%B0%D0%B4%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 13,
                            name: 'Ямайка',
                            fullName: 'Ямайка',
                            sng: false,
                            population: 2726667,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%AF%D0%BC%D0%B0%D0%B9%D0%BA%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 14,
                            name: 'Барбадос',
                            fullName: 'Барбадос',
                            sng: false,
                            population: 287000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%91%D0%B0%D1%80%D0%B1%D0%B0%D0%B4%D0%BE%D1%81',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 15,
                            name: 'Белиз',
                            fullName: 'Белиз',
                            sng: false,
                            population: 387879,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%91%D0%B5%D0%BB%D0%B8%D0%B7',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 16,
                            name: 'Панама',
                            fullName: 'Республика Панама',
                            sng: false,
                            population: 3764166,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9F%D0%B0%D0%BD%D0%B0%D0%BC%D0%B0',
                            selected: true,
                            enabled: true
                        },
                        {
                            number: 17,
                            name: 'США',
                            fullName: 'Соединённые Штаты Америки',
                            sng: false,
                            population: 331427186,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D0%BE%D0%B5%D0%B4%D0%B8%D0%BD%D1%91%D0%BD%D0%BD%D1%8B%D0%B5_%D0%A8%D1%82%D0%B0%D1%82%D1%8B_%D0%90%D0%BC%D0%B5%D1%80%D0%B8%D0%BA%D0%B8',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 18,
                            name: 'Куба',
                            fullName: 'Республика Куба',
                            sng: false,
                            population: 11333000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9A%D1%83%D0%B1%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 19,
                            name: 'Доминика',
                            fullName: 'Содружество Доминики',
                            sng: false,
                            population: 72000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%94%D0%BE%D0%BC%D0%B8%D0%BD%D0%B8%D0%BA%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 20,
                            name: 'Сент-Винсент и Гренадины',
                            fullName: 'Сент-Винсент и Гренадины',
                            sng: false,
                            population: 111000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D0%B5%D0%BD%D1%82-%D0%92%D0%B8%D0%BD%D1%81%D0%B5%D0%BD%D1%82_%D0%B8_%D0%93%D1%80%D0%B5%D0%BD%D0%B0%D0%B4%D0%B8%D0%BD%D1%8B',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 21,
                            name: 'Гондурас',
                            fullName: 'Республика Гондурас',
                            sng: false,
                            population: 9158345,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%93%D0%BE%D0%BD%D0%B4%D1%83%D1%80%D0%B0%D1%81',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 22,
                            name: 'Багамы',
                            fullName: 'Содружество Багамских Островов',
                            sng: false,
                            population: null,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%91%D0%B0%D0%B3%D0%B0%D0%BC%D1%81%D0%BA%D0%B8%D0%B5_%D0%9E%D1%81%D1%82%D1%80%D0%BE%D0%B2%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 23,
                            name: 'Сент-Китс и Невис',
                            fullName: 'Федерация Сент-Китс и Невис',
                            sng: false,
                            population: 56183,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D0%B5%D0%BD%D1%82-%D0%9A%D0%B8%D1%82%D1%81_%D0%B8_%D0%9D%D0%B5%D0%B2%D0%B8%D1%81',
                            selected: false,
                            enabled: true
                        }
                    ]
                },
                {
                    name: 'South America',
                    total: 12,
                    countries: [
                        {
                            number: 1,
                            name: 'Чили',
                            fullName: 'Республика Чили',
                            sng: false,
                            population: 19494315,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A7%D0%B8%D0%BB%D0%B8',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 2,
                            name: 'Аргентина',
                            fullName: 'Аргентинская Республика',
                            sng: false,
                            population: 44938712,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%90%D1%80%D0%B3%D0%B5%D0%BD%D1%82%D0%B8%D0%BD%D0%B0',
                            selected: false,
                            enabled: false
                        },
                        {
                            number: 3,
                            name: 'Перу',
                            fullName: 'Республика Перу',
                            sng: false,
                            population: 32162184,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9F%D0%B5%D1%80%D1%83',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 4,
                            name: 'Парагвай',
                            fullName: 'Республика Парагвай',
                            sng: false,
                            population: 7152703,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9F%D0%B0%D1%80%D0%B0%D0%B3%D0%B2%D0%B0%D0%B9',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 5,
                            name: 'Колумбия',
                            fullName: 'Республика Колумбия',
                            sng: false,
                            population: 48258494,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9A%D0%BE%D0%BB%D1%83%D0%BC%D0%B1%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 6,
                            name: 'Бразилия',
                            fullName: 'Федеративная Республика Бразилия',
                            sng: false,
                            population: 211422495,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%91%D1%80%D0%B0%D0%B7%D0%B8%D0%BB%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 7,
                            name: 'Суринам',
                            fullName: 'Республика Суринам',
                            sng: false,
                            population: 581000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D1%83%D1%80%D0%B8%D0%BD%D0%B0%D0%BC',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 8,
                            name: 'Боливия',
                            fullName: 'Многонациональное Государство Боливия',
                            sng: false,
                            population: 11469896,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%91%D0%BE%D0%BB%D0%B8%D0%B2%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 9,
                            name: 'Венесуэла',
                            fullName: 'Боливарианская Республика Венесуэла',
                            sng: false,
                            population: 32593000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%92%D0%B5%D0%BD%D0%B5%D1%81%D1%83%D1%8D%D0%BB%D0%B0',
                            selected: true,
                            enabled: true
                        },
                        {
                            number: 10,
                            name: 'Эквадор',
                            fullName: 'Республика Эквадор',
                            sng: false,
                            population: 17462020,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%AD%D0%BA%D0%B2%D0%B0%D0%B4%D0%BE%D1%80',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 11,
                            name: 'Уругвай',
                            fullName: 'Восточная Республика Уругвай',
                            sng: false,
                            population: 3415866,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A3%D1%80%D1%83%D0%B3%D0%B2%D0%B0%D0%B9',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 12,
                            name: 'Гайана',
                            fullName: 'Кооперативная Республика Гайана',
                            sng: false,
                            population: 801623,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%93%D0%B0%D0%B9%D0%B0%D0%BD%D0%B0',
                            selected: false,
                            enabled: true
                        }
                    ]
                },
                {
                    name: 'Australia',
                    total: 14,
                    countries: [
                        {
                            number: 1,
                            name: 'Палау',
                            fullName: 'Республика Палау',
                            sng: false,
                            population: 21507,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9F%D0%B0%D0%BB%D0%B0%D1%83',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 2,
                            name: 'Маршалловы Острова',
                            fullName: 'Республика Маршалловы Острова',
                            sng: false,
                            population: 53069,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9C%D0%B0%D1%80%D1%88%D0%B0%D0%BB%D0%BB%D0%BE%D0%B2%D1%8B_%D0%9E%D1%81%D1%82%D1%80%D0%BE%D0%B2%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 3,
                            name: 'Кирибати',
                            fullName: 'Республика Кирибати',
                            sng: false,
                            population: 118000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9A%D0%B8%D1%80%D0%B8%D0%B1%D0%B0%D1%82%D0%B8',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 4,
                            name: 'Тувалу',
                            fullName: 'Тувалу',
                            sng: false,
                            population: 10927,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A2%D1%83%D0%B2%D0%B0%D0%BB%D1%83',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 5,
                            name: 'Австралия',
                            fullName: 'Австралийский Союз',
                            sng: false,
                            population: 25700000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%90%D0%B2%D1%81%D1%82%D1%80%D0%B0%D0%BB%D0%B8%D1%8F',
                            selected: false,
                            enabled: false
                        },
                        {
                            number: 6,
                            name: 'Вануату',
                            fullName: 'Республика Вануату',
                            sng: false,
                            population: 300000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%92%D0%B0%D0%BD%D1%83%D0%B0%D1%82%D1%83',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 7,
                            name: 'Новая Зеландия',
                            fullName: 'Новая Зеландия',
                            sng: false,
                            population: 4964880,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9D%D0%BE%D0%B2%D0%B0%D1%8F_%D0%97%D0%B5%D0%BB%D0%B0%D0%BD%D0%B4%D0%B8%D1%8F',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 8,
                            name: 'Соломоновы Острова',
                            fullName: 'Соломоновы Острова',
                            sng: false,
                            population: 670000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D0%BE%D0%BB%D0%BE%D0%BC%D0%BE%D0%BD%D0%BE%D0%B2%D1%8B_%D0%9E%D1%81%D1%82%D1%80%D0%BE%D0%B2%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 9,
                            name: 'Тонга',
                            fullName: 'Королевство Тонга',
                            sng: false,
                            population: 100651,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A2%D0%BE%D0%BD%D0%B3%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 10,
                            name: 'Науру',
                            fullName: 'Республика Науру',
                            sng: false,
                            population: 11086,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9D%D0%B0%D1%83%D1%80%D1%83',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 11,
                            name: 'Фиджи',
                            fullName: 'Республика Фиджи',
                            sng: false,
                            population: 890000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A4%D0%B8%D0%B4%D0%B6%D0%B8',
                            selected: false,
                            enabled: true
                        },
                        {number: 12, name: '', fullName: '', sng: false, population: null, wikiLink: null, selected: false, enabled: true},
                        {
                            number: 13,
                            name: 'Самоа',
                            fullName: 'Независимое Государство Самоа',
                            sng: false,
                            population: 197000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A1%D0%B0%D0%BC%D0%BE%D0%B0',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 14,
                            name: 'Микронезия',
                            fullName: 'Федеративные Штаты Микронезии',
                            sng: false,
                            population: 114000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%A4%D0%B5%D0%B4%D0%B5%D1%80%D0%B0%D1%82%D0%B8%D0%B2%D0%BD%D1%8B%D0%B5_%D0%A8%D1%82%D0%B0%D1%82%D1%8B_%D0%9C%D0%B8%D0%BA%D1%80%D0%BE%D0%BD%D0%B5%D0%B7%D0%B8%D0%B8',
                            selected: false,
                            enabled: true
                        },
                        {
                            number: 15,
                            name: 'Папуа — Новая Гвинея',
                            fullName: 'Независимое Государство Папуа — Новая Гвинея',
                            sng: false,
                            population: 8776000,
                            wikiLink: 'https://ru.wikipedia.org/wiki/%D0%9F%D0%B0%D0%BF%D1%83%D0%B0_%E2%80%94_%D0%9D%D0%BE%D0%B2%D0%B0%D1%8F_%D0%93%D0%B2%D0%B8%D0%BD%D0%B5%D1%8F',
                            selected: false,
                            enabled: true
                        }
                    ]
                },
                {
                    name: 'Antarctica',
                    total: 0,
                    countries: []
                }
            ]
        }
    },
    methods: {
        formatPopulation: function(population) {
            if (population === null) {
                return null;
            }
            const populationInMillions = population/1000;
            return populationInMillions.toFixed(0);
        },
        formatName: function(fullName, size) {
            if (fullName.length > size) {
                return fullName.substring(0, size - 3) + "...";
            }
            return fullName;
        }
    }
}
</script>

<style scoped>

</style>
<template>
    <div id="home-page">
        <b-container>
            <b-row>
                <b-col><h3 class="page-title">{{ pageTitle }}</h3></b-col>
            </b-row>
            <b-row>
                <b-col>
                    На данный момент в состав Организации Объединённых Наций входит 193 государства-члена и три
                    государства-наблюдателя.
                    Святой Престол (Ватикан), Государство Палестина и Мальтийский орден являются субъектами
                    международного права
                    и постоянными наблюдателями Генеральной Ассамблеи ООН, не являясь членами ООН.
                </b-col>
            </b-row>
            <b-row test-id="flags-first-group" class="p-4" align-h="center" align-v="center">
                <b-col cols="2">
                    <b-img alt="Ватикан" thumbnail
                           src="src/static/flags/07_Flag_of_the_Vatican_City.svg.png"></b-img>
                </b-col>
                <b-col cols="2">
                    <b-img alt="Палестина" thumbnail src="src/static/flags/08_Flag_of_Palestine.svg.png"></b-img>
                </b-col>
                <b-col cols="2">
                    <b-img alt="Мальтийский орден" thumbnail
                           src="src/static/flags/09_Flag_of_the_Order_of_St._John_(various).svg.png"></b-img>
                </b-col>
            </b-row>
            <b-row>
                <b-col>
                    Около десятка государственных образований фактически являются самостоятельными государствами,
                    контролируя и
                    осуществляя управление своими территориями, но либо не признаны другими государствами, либо признаны
                    недостаточным их количеством (например, для вхождения в ООН образованию требуется признание 2/3
                    государств,
                    входящих в организацию).
                </b-col>
            </b-row>
            <b-row test-id="flags-second-group" class="p-4" align-h="center" align-v="center">
                <b-col cols="2">
                    <b-img alt="Абхазия" thumbnail
                           src="src/static/flags/01_Flag_of_the_Republic_of_Abkhazia.svg.png"></b-img>
                </b-col>
                <b-col cols="2">
                    <b-img alt="Косово" thumbnail src="src/static/flags/02_Flag_of_Kosovo.svg.png"></b-img>
                </b-col>
                <b-col cols="2">
                    <b-img alt="ТРСК" thumbnail
                           src="src/static/flags/03_Flag_of_the_Turkish_Republic_of_Northern_Cyprus.svg.png"></b-img>
                </b-col>
                <b-col cols="2">
                    <b-img alt="Китайская Республика (Тайвань)" thumbnail
                           src="src/static/flags/04_Flag_of_the_Republic_of_China.svg.png"></b-img>
                </b-col>
                <b-col cols="2">
                    <b-img alt="САДР" thumbnail
                           src="src/static/flags/05_Flag_of_the_Sahrawi_Arab_Democratic_Republic.svg.png"></b-img>
                </b-col>
                <b-col cols="2">
                    <b-img alt="Южная Осетия" thumbnail src="src/static/flags/06_Flag_of_South_Ossetia.svg.png"></b-img>
                </b-col>
            </b-row>
            <b-row>
                <b-col>
                    Во многих частях света существуют регионы, население которых борется за признание их самостоятельной
                    государственности.
                    В то же время, не следует приравнивать государства мира к виртуальным государствам, которые не имеют
                    территории
                    и какой-либо этнической и исторической легитимности. Статус и категория некоторых территорий и
                    образований
                    не могут быть однозначно идентифицированы.
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
    export default {
        data: function() {
            return {
                pageTitle: "Государства мира"
            }
        }
    }
</script>
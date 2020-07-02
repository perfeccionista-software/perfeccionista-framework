<template>
    <div id="app">
        <b-container fluid="lg">
            <b-row align-v="center">
                <b-col cols="12">
                    <div class="header">
                        <h2>Perfeccionista</h2>
                    </div>
                </b-col>
            </b-row>
            <b-row>
                <b-col cols="3">
                    <b-list-group id="main-menu">
                        <b-list-group-item><router-link to="/">Home</router-link></b-list-group-item>
                        <b-list-group-item><router-link to="/elements">Elements</router-link></b-list-group-item>
                        <b-list-group-item><router-link to="/list-elements">List Elements</router-link></b-list-group-item>
                        <b-list-group-item><router-link to="/text-list-elements">Text List Elements</router-link></b-list-group-item>
                        <b-list-group-item><router-link to="/table-element">Table Element</router-link></b-list-group-item>
                        <b-list-group-item><router-link to="/text-table-element">Text Table Element</router-link></b-list-group-item>
                        <b-list-group-item><router-link to="/elements-in-list-context">Elements (List Context)</router-link></b-list-group-item>
                        <b-list-group-item><router-link to="/elements-in-table-context">Elements (Table Context)</router-link></b-list-group-item>
                        <b-list-group-item><router-link to="/embed-element">Embed Elements</router-link></b-list-group-item>
                        <b-list-group-item><router-link to="/form-element">Form Elements</router-link></b-list-group-item>
                        <b-list-group-item><router-link to="/locator-breaker">Locator Breaker</router-link></b-list-group-item>
                    </b-list-group>
                </b-col>
                <b-col cols="9">
                    <transition name="fade" mode="out-in">
                        <router-view class="view"></router-view>
                    </transition>
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
    export default {
        name: "App"
    }
</script>

<style scoped>

</style>
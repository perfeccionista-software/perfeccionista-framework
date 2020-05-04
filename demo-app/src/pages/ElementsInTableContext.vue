<template>
    <div id="elements-cell-context-page">
        <b-container>
            <b-row>
                <b-col><h3 class="page-title">{{ pageTitle }}</h3></b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
    export default {
        data: function() {
            return {
                pageTitle: 'Простые элементы (в контексте ячейки)'
            }
        },
    }
</script>

<style scoped>

</style>
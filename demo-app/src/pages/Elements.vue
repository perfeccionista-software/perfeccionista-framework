<template>
    <div id="elements-page">
        <b-container>
            <b-row>
                <b-col><h3 class="page-title">{{ pageTitle }}</h3></b-col>
            </b-row>
            <!-- Images test -->
            <b-row class="mb-3 text-center" align-h="center" align-v="center">
                <b-col cols="3">
                    <b-img id="simple-image" alt="World map picture" thumbnail src="src/static/world_map.jpeg"></b-img>
                </b-col>
                <b-col cols="9"></b-col>
            </b-row>
            <!-- Button test -->
            <b-row class="mb-3 text-center" align-v="center">
                <b-col cols="3">
                    <b-button id="simple-button" variant="primary" v-on:click="setSimpleButtonVisible">Simple Button</b-button>
                </b-col>
                <b-col cols="6">
                </b-col>
                <b-col cols="3">
                    <!-- Not present before link clicked -->
                    <span id="simple-button-text" v-if="simpleButtonVisible===true">Simple Button clicked</span>
                </b-col>
            </b-row>
            <!-- Button with hover test -->
            <b-row class="mb-3 text-center" align-v="center" v-on:mouseleave="visibleLinkState=false">
                <b-col cols="3">
                    <b-button id="button-for-hover" variant="primary" v-on:mouseover="visibleLinkState=true">Button for Hover</b-button>
                </b-col>
                <b-col cols="3">
                    <b-link id="visible-link" v-if="visibleLinkState" v-on:click="setTextForHoverClick">Visible Link</b-link>
                </b-col>
                <b-col cols="3">
                </b-col>
                <b-col cols="3">
                    <span id="visible-link-text">{{ textForVisibleLink }}</span>
                </b-col>
            </b-row>
            <!-- Button with waiting test -->
            <b-row class="mb-3 text-center" align-v="center">
                <b-col cols="3">
                    <b-button id="button-with-spinner" variant="primary" v-on:click="setTextForButtonWithSpinner">Button for Spinner</b-button>
                </b-col>
                <b-col cols="6">
                    <b-spinner id="spinner" variant="primary" v-show=spinnerVisible label="Spinning"></b-spinner>
                </b-col>
                <b-col cols="3">
                    <span id="button-with-spinner-text" v-show=textForButtonWithSpinnerVisible>Spinner Button clicked</span>
                </b-col>
            </b-row>
            <!-- Link test -->
            <b-row class="mb-3 text-center" align-v="center">
                <b-col cols="3">
                    <b-link id="simple-link" v-on:click="setSimpleLinkVisible">Simple Link</b-link>
                </b-col>
                <b-col cols="6">
                </b-col>
                <b-col cols="3">
                    <!-- Not visible but present before link clicked -->
                    <span id="simple-link-text" v-show=simpleLinkVisible>Simple Link clicked</span>
                </b-col>
            </b-row>
            <!-- TextInput test -->
            <b-row class="mb-3 text-center" align-v="center">
                <b-col cols="3">
                    <!--suppress XmlDuplicatedId -->
                    <b-button id="simple-input-button" variant="danger" v-if="simpleInputState==='Enabled'" v-on:click="simpleInputState='Disabled'">Disable Input</b-button>
                    <!--suppress XmlDuplicatedId -->
                    <b-button id="simple-input-button" variant="success" v-else v-on:click="simpleInputState='Enabled'">Enable Input</b-button>
                </b-col>
                <b-col cols="6">
                    <b-form-input id="simple-input" v-if="simpleInputState==='Enabled'" v-model="textFromSimpleInput" placeholder="Enter text"></b-form-input>
                    <b-form-input id="simple-input" v-else disabled v-model="textFromSimpleInput" placeholder="Enter text"></b-form-input>
                </b-col>
                <b-col cols="3">
                    <span id="simple-input-text">{{ textFromSimpleInput }}</span>
                </b-col>
            </b-row>
            <!-- TextArea test -->
            <b-row class="mb-3 text-center" align-v="center">
                <b-col cols="3">
                    <!--suppress XmlDuplicatedId -->
                    <b-button id="area-input-button" variant="danger" v-if="areaInputState==='Enabled'" v-on:click="areaInputState='Disabled'">Disable Area</b-button>
                    <!--suppress XmlDuplicatedId -->
                    <b-button id="area-input-button" variant="success" v-else v-on:click="areaInputState='Enabled'">Enable Area</b-button>
                </b-col>
                <b-col cols="6">
                    <b-form-textarea id="area-input" v-if="areaInputState==='Enabled'" v-model="textFromAreaInput" placeholder="Enter text" rows="3"></b-form-textarea>
                    <b-form-textarea id="area-input" v-else v-model="textFromAreaInput" placeholder="Enter text" rows="3" disabled></b-form-textarea>
                </b-col>
                <b-col cols="3">
                    <span id="area-input-text">{{ textFromAreaInput }}</span>
                </b-col>
            </b-row>
            <!-- Checkbox test -->
            <b-row class="mb-3 text-center" align-v="center">
                <b-col cols="3">
                    <b-checkbox id="checkbox-one" name="Checkbox 1" value="Label 1" v-model="checkboxSelected">Label 1</b-checkbox>
                </b-col>
                <b-col cols="3">
                    <b-checkbox id="checkbox-two" name="Checkbox 2" value="Label 2" v-model="checkboxSelected">Label 2</b-checkbox>
                </b-col>
                <b-col cols="3">
                    <b-checkbox id="checkbox-three" name="Checkbox 3" value="Label 3" v-model="checkboxSelected" disabled>Label 3</b-checkbox>
                </b-col>
                <b-col cols="3">
                    <span id="checkbox-text">{{ checkboxSelected.join("; ") }}</span>
                </b-col>
            </b-row>
            <!-- RadioGroup test -->
            <b-row id="radio-group" class="mb-3 text-center" align-v="center">
                <b-col cols="3"><b-radio id="radio-one" name="RadioButton 1"  value="Label 1" v-model="radioButtonSelected">Label 1</b-radio></b-col>
                <b-col cols="3"><b-radio id="radio-two" name="RadioButton 2" value="Label 2" v-model="radioButtonSelected">Label 2</b-radio></b-col>
                <b-col cols="3"><b-radio id="radio-three" name="RadioButton 3" value="Label 3" v-model="radioButtonSelected" disabled>Label 3</b-radio></b-col>
                <b-col cols="3">
                    <span id="radio-text">{{ radioButtonSelected }}</span>
                </b-col>
            </b-row>
            <!-- FileInput test -->
            <b-row class="mb-3 text-center" align-v="center">
                <b-col cols="6">
                    <b-form-file id="file-input" class="text-left" v-model="fileSelected" :state="Boolean(fileSelected)" placeholder="Choose a file" drop-placeholder="Drop file here..."></b-form-file>
                </b-col>
                <b-col cols="3">
                    <span id="file-input-file-name">{{ fileSelected ? fileSelected.name : '' }}</span>
                </b-col>
                <b-col cols="3">
                    <b-link id="file-download" href="src/static/LICENSE.txt" download>Download File</b-link>
                </b-col>
            </b-row>
            <!-- Drag&Drop test -->
            <b-row class="mb-3 text-center" align-v="center">
                <b-col cols="3">
                    <span id="drag-and-drop-tooltip" v-b-tooltip.hover title="Drag Source and Drop to Target">Drag&Drop</span>
                </b-col>
                <b-col cols="3">
                    <drag id="drag-and-drop-source" class="drag" :transfer-data="{content: 'Source'}" :effect-allowed="['move']" drop-effect="move">Source</drag>
                </b-col>
                <b-col cols="3">
                    <drop id="drag-and-drop-target" class="drop" :class="{ sourceOver }" @dragover="sourceOver = true" @dragleave="sourceOver = false" @drop="handleDrop">Target</drop>
                </b-col>
                <b-col cols="3">
                    <span id="drag-and-drop-text">{{ sourceText }}</span>
                </b-col>
            </b-row>
            <!-- Double click button test -->
            <b-row class="mb-3 text-center" align-v="center">
                <b-col cols="3">
                    <b-button id="double-click-button" variant="outline-primary" v-on:dblclick="doubleClickText='Double click done'">Double click Button</b-button>
                </b-col>
                <b-col cols="6">
                </b-col>
                <b-col cols="3">
                    <span id="double-click-text">{{ doubleClickText }}</span>
                </b-col>
            </b-row>
            <!-- Modal window test -->
            <b-row class="mb-3 text-center" align-v="center">
                <b-col cols="3">
                    <b-button id="modal-button" variant="warning" v-b-modal.modal-window>Button for Modal</b-button>
                </b-col>
                <b-col cols="3">
                </b-col>
                <b-col cols="3">
                </b-col>
                <b-col cols="3">
                    <b-modal id="modal-window" title="Modal Window">
                        <p class="my-4">Modal window opened!</p>
                    </b-modal>
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
    import { Drag, Drop } from 'vue-drag-drop';
    export default {
        components: { Drag, Drop },
        data: function () {
            return {
                pageTitle: 'Простые элементы',

                simpleButtonVisible: false,
                simpleLinkVisible: false,
                visibleLinkState: false,
                spinnerVisible: false,
                textForButtonWithSpinnerVisible: false,
                textForVisibleLink: "",
                simpleInputState: "Enabled",
                textFromSimpleInput: "",
                areaInputState: "Enabled",
                textFromAreaInput: "",
                checkboxSelected: [],
                radioButtonSelected: "Label 2",
                fileSelected: null,
                sourceOver: false,
                sourceData: '',
                sourceText: "",
                doubleClickText: ""
            }
        },
        methods: {
            setSimpleButtonVisible: function() {
                console.log("Simple Button clicked...");
                this.simpleButtonVisible = true;
            },
            setSimpleLinkVisible: function() {
                console.log("Simple Link clicked...");
                this.simpleLinkVisible = true;
            },
            setTextForHoverClick: function() {
                console.log("Visible Link clicked...");
                this.textForVisibleLink = "Visible Link clicked";
            },
            handleDrop: function(data) {
                this.sourceOver = false;
                this.sourceText = data.content + " moved";
            },
            setTextForButtonWithSpinner: function() {
                console.log("Spinner Button clicked");
                this.spinnerVisible = true;
                setTimeout(this.spinnerFinished, 3000)
            },
            spinnerFinished: function() {
                this.spinnerVisible = false;
                this.textForButtonWithSpinnerVisible = true;
            }
        }
    }
</script>

<style scoped>
    .drag {
        padding: 5px 0;
        border: 1px solid dimgrey;
        border-radius: 3px;
    }
    .drop {
        padding: 5px 0;
        border: 1px dashed dimgrey;
        border-radius: 3px;
    }
    .drop.sourceOver {
        padding: 3px 0;
        border: 3px dashed dimgrey;
        background: #ccc;
    }
</style>
import Vue from 'vue'
import VueRouter from 'vue-router'
import { BootstrapVue, BootstrapVueIcons } from "bootstrap-vue"
import { CoolSelectPlugin } from "vue-cool-select";
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap-vue/dist/bootstrap-vue.css"
import "vue-cool-select/dist/themes/bootstrap.css";
import "./assets/main.css"
import App from "./App.vue";
import Home from                     "./pages/Home.vue";
import Elements from                 "./pages/Elements.vue";
import ElementsInListContext from    "./pages/ElementsInListContext.vue";
import ElementsInTableContext from   "./pages/ElementsInTableContext.vue";
import ListElements from             "./pages/ListElements.vue";
import ListElementsAutocomplete from "./pages/ListElementsAutocomplete.vue";
import ListElementsDropdown from     "./pages/ListElementsDropdown.vue";
import TextListElements from         "./pages/TextListElements.vue";
import TableElement from             "./pages/TableElement.vue";
import TextTableElement from         "./pages/TextTableElement.vue";
import LocatorBreaker from           "./pages/LocatorBreaker.vue";
import EmbedElements from            "./pages/EmbedElements.vue";
import FormElements from             "./pages/FormElements.vue";

Vue.use(BootstrapVue, BootstrapVueIcons)
Vue.use(CoolSelectPlugin);
Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  routes: [
    { path: '/elements', component: Elements, meta: {title: 'Perfeccionista Portal: Elements'} },
    { path: '/elements-in-list-context', component: ElementsInListContext, meta: {title: 'Perfeccionista Portal: Elements (List Context)'} },
    { path: '/elements-in-table-context', component: ElementsInTableContext, meta: {title: 'Perfeccionista Portal: Elements (Table Context)'} },
    { path: '/list-elements', component: ListElements, meta: {title: 'Perfeccionista Portal: List Elements'} },
    { path: '/autocomplete-list-elements', component: ListElementsAutocomplete, meta: {title: 'Perfeccionista Portal: Autocomplete List Elements'} },
    { path: '/dropdown-list-elements', component: ListElementsDropdown, meta: {title: 'Perfeccionista Portal: Dropdown List Elements'} },
    { path: '/text-list-elements', component: TextListElements, meta: {title: 'Perfeccionista Portal: Text List Elements'} },
    { path: '/table-element', component: TableElement, meta: {title: 'Perfeccionista Portal: Table Elements'} },
    { path: '/text-table-element', component: TextTableElement, meta: {title: 'Perfeccionista Portal: Text Table Elements'} },
    { path: '/embed-element', component: EmbedElements, meta: {title: 'Perfeccionista Portal: Embed Elements'} },
    { path: '/form-element', component: FormElements, meta: {title: 'Perfeccionista Portal: From Elements'} },
    { path: '/locator-breaker', component: LocatorBreaker, meta: {title: 'Perfeccionista Portal: Locator Breaker'} },
    { path: '/', component: Home, meta: {title: 'Perfeccionista Portal: Home'} },
  ]
})
router.beforeEach(function(to, from, next) {
  Vue.nextTick(function() {
    document.title = to.meta.title;
    next();
  });
})

new Vue({
  el: '#app',
  router: router,
  render: function (n) {
    return n(App);
  }
}).$mount('#app')

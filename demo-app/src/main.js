import Vue from 'vue'
import VueRouter from 'vue-router'
import { BootstrapVue, BootstrapVueIcons } from "bootstrap-vue"
import { CoolSelectPlugin } from "vue-cool-select";
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap-vue/dist/bootstrap-vue.css"
import "vue-cool-select/dist/themes/bootstrap.css";
import "./assets/main.css"
import App from "./App.vue";
import Home from "./pages/Home.vue";
import Elements from "./pages/Elements.vue";
import ElementsInListContext from "./pages/ElementsInListContext.vue";
import ElementsInTableContext from "./pages/ElementsInTableContext.vue";
import ListElements from "./pages/ListElements.vue";
import TextListElements from "./pages/TextListElements.vue";
import TableElement from "./pages/TableElement.vue";
import TextTableElement from "./pages/TextTableElement.vue";
import LocatorBreaker from "./pages/LocatorBreaker.vue";
import EmbedElements from "./pages/EmbedElements.vue";
import FormElements from "./pages/FormElements.vue";

Vue.use(BootstrapVue, BootstrapVueIcons)
Vue.use(CoolSelectPlugin);
Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  routes: [
    { path: '/', component: Home },
    { path: '/elements', component: Elements },
    { path: '/elements-in-list-context', component: ElementsInListContext },
    { path: '/elements-in-table-context', component: ElementsInTableContext },
    { path: '/list-elements', component: ListElements },
    { path: '/text-list-elements', component: TextListElements },
    { path: '/table-element', component: TableElement },
    { path: '/text-table-element', component: TextTableElement },
    { path: '/embed-element', component: EmbedElements },
    { path: '/form-element', component: FormElements },
    { path: '/locator-breaker', component: LocatorBreaker },
  ]
})

new Vue({
  el: '#app',
  router: router,
  render: function (n) {
    return n(App);
  }
}).$mount('#app')

import Vue from "vue"
import App from "pages/App.vue"
import VueResource from "vue-resource"
import router from "./router";

Vue.use(VueResource);

new Vue({
    el: "#app",
    router,
    render: a => a(App)
});
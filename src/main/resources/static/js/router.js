import Vue from "vue"
import VueRouter from "vue-router"

import Semester from "./components/Semesters.vue";
import Faculty from "./components/Faculties.vue";

Vue.use(VueRouter);

const routes = [
    { path: "/my-semesters/:institutionId/:institutionName", component: Semester, props: true },
    { path: "/my-faculties/:semesterId/:semesterName", component: Faculty, props: true },
];

export default new VueRouter({
    mode: "history",
    routes
});
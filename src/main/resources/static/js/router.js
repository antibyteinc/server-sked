import Vue from "vue"
import VueRouter from "vue-router"

import Semester from "./components/Semesters.vue";
import Faculty from "./components/Faculties.vue";

import Schedule from "./pages/Schedule.vue";
import Institutions from "./components/Institutions.vue";

Vue.use(VueRouter);

const routes = [
    { path: "/my-semesters/:institutionId/:institutionName", component: Semester, props: true },
    { path: "/my-faculties/:semesterId/:semesterName", component: Faculty, props: true },
    { path: "/schedule/:groupId", component: Schedule, props: true },
    {path: "/my-institutions", component: Institutions}
];

export default new VueRouter({
    mode: "history",
    routes
});
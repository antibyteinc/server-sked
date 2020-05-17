import Vue from "vue"
import VueRouter from "vue-router"

import Institutions from "./components/Institutions.vue";
import Schedule from "./pages/Schedule.vue";
import Teachers from "./components/schedule/Teachers.vue";
import Lesson from "./components/schedule/Lesson.vue";
import Classroom from "./components/schedule/Classroom.vue";
// import InstitutionPage from "./pages/InstitutionPage.vue";

Vue.use(VueRouter);

const routes = [
    {path: "/my-institutions", component: Institutions},
    {path: "/schedule/:groupId", component: Schedule, props: true},
    {path: "/my-teachers/:institutionId", component: Teachers, props: true},
    {path: "/my-lessons/:institutionId", component: Lesson, props: true},
    {path: "/my-classrooms/:institutionId", component: Classroom, props: true},
    // {path: "/institution-page/:insId", component: InstitutionPage, props: true}
];

export default new VueRouter({
    mode: "history",
    routes
});
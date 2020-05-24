import Vue from "vue"
import VueRouter from "vue-router"

import Institutions from "./components/Institutions.vue";
import Profile from "./pages/Profile.vue";
import Schedule from "./pages/Schedule.vue";
import Teachers from "./components/schedule/Teachers.vue";
import Lesson from "./components/schedule/Lesson.vue";
import Classroom from "./components/schedule/Classroom.vue";
import InstitutionPage from "./pages/InstitutionPage.vue";
import Faculties from "./components/Faculties.vue";

Vue.use(VueRouter);

const routes = [
    {path: "/my-institutions", component: Institutions},
    {path: "/profile", component: Profile},
    {path: "/schedule/:groupId", component: Schedule, props: true},
    {path: "/my-teachers", component: Teachers},
    {path: "/my-lessons", component: Lesson},
    {path: "/my-classrooms", component: Classroom},
    {path: "/my-institution-page", component: InstitutionPage, props: true},
    {path: "/faculties", component: Faculties, props: true}
];

export default new VueRouter({
    mode: "history",
    routes
});
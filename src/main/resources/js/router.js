import Vue from "vue"
import VueRouter from "vue-router"

import Main from "./pages/Main.vue";
import Profile from "./pages/Profile.vue";

import Lesson from "./pages/items/Lesson.vue";
import Teacher from "./pages/items/Teacher.vue";
import Classroom from "./pages/items/Classroom.vue";

import Institute from "./pages/division/Institute.vue";
import Division from "./pages/division/Division.vue";
import Schedule from "./pages/schedule/Schedule.vue";

import View from "./pages/View.vue";
import ViewDivision from "./pages/view/ViewDivision.vue";
import ViewSchedule from "./pages/view/ViewSchedule.vue";
import ViewInstitute from "./pages/view/ViewInstitute.vue";
import ViewTeacher from "./pages/view/ViewTeacher.vue";

Vue.use(VueRouter);

const routes = [
    {path: "/main", component: Main},
    {path: "/profile", component: Profile},

    {path: "/lessons", component: Lesson},
    {path: "/teachers", component: Teacher},
    {path: "/classrooms", component: Classroom},

    {path: "/my-institute", component: Institute},
    {path: "/divisions", component: Division},
    {path: "/schedules/:groupId", component: Schedule, props: true},

    {path: "/view/:userId", component: View, props: true},
    {path: "/view-division/:userId", component: ViewDivision, props: true},
    {path: "/view-schedules/:groupId", component: ViewSchedule, props: true},
    {path: "/view-institute/:userId", component: ViewInstitute, props: true},
    {path: "/view-teacher/:userId", component: ViewTeacher, props: true},
    {path: "/", redirect: "/main"}
];

export default new VueRouter({
    mode: "history",
    routes
});
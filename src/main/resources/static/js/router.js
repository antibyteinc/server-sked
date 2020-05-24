import Vue from "vue"
import VueRouter from "vue-router"

import Main from "./pages/Main.vue";
import Profile from "./pages/Profile.vue";
import Lesson from "./pages/items/Lesson.vue";
import Teacher from "./pages/items/Teacher.vue";
import Classroom from "./pages/items/Classroom.vue";
import Cover from "./pages/items/Cover.vue";
import Division from "./pages/division/Division.vue";
import Schedule from "./pages/schedule/Schedule.vue";

Vue.use(VueRouter);

const routes = [
    {path: "/main", component: Main},
    {path: "/profile", component: Profile},

    {path: "/lessons", component: Lesson},
    {path: "/teachers", component: Teacher},
    {path: "/classrooms", component: Classroom},
    {path: "/cover", component: Cover},

    {path: "/divisions", component: Division},
    {path: "/schedules/:groupId", component: Schedule, props: true},
];

export default new VueRouter({
    mode: "history",
    routes
});
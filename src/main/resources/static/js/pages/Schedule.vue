<template>
    <div>
        <teachers :group-id="groupId"></teachers>
        <hr>
        <lesson :group-id="groupId"></lesson>
        <hr>
        <type-lesson :group-id="groupId"></type-lesson>
        <hr>
        <classroom :group-id="groupId"></classroom>
    </div>
</template>
<script>
    import Teachers from "../components/schedule/Teachers.vue";
    import Lesson from "../components/schedule/Lesson.vue";
    import TypeLesson from "../components/schedule/TypeLesson.vue";
    import Classroom from "../components/schedule/Classroom.vue";

    export default {
        props: ["groupId"],
        components: {
            Teachers, Lesson, TypeLesson, Classroom
        }
    }
</script>
<style>

</style>
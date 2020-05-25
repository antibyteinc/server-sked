<template>
    <div class="wrapper__course">
        <div class="courses" v-if="courses.length > 0">
            <div class="course" v-for="course in courses">
                <div class="course-header">
                    <div class="course-name">{{course.name}} <span>курс</span></div>
                </div>
                <div class="course-body"></div>
                <div class="course-footer">
                    <view-department :course="course"></view-department>
                </div>
            </div>
        </div>

        <div class="course" v-else>
            <p>Список курсів порожній</p>
        </div>
    </div>
</template>
<script>
    import ViewDepartment from "./ViewDepartment.vue";

    export default {
        props: ["faculty"],
        components: {
            ViewDepartment
        },
        data() {
            return {
                courseApi: this.$resource("/course{/id}"),
                courses: [],
            }
        },
        watch: {
            faculty() {
                if (this.faculty !== undefined) {
                    this.getCourseFromDb();
                }
            }
        },
        methods: {
            getCourseFromDb() {
                this.courses = [];
                this.courseApi.get({id: this.faculty.id}).then(res => {
                    res.json().then(data => {
                        data.forEach(course => {
                            this.courses.push(course);
                        })
                    })
                })
            }
        }
    }
</script>
<style>

</style>
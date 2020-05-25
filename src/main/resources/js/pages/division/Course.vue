<template>
    <div class="wrapper__course" v-if="faculties.length > 0">
        <div class="courses" v-if="courses.length > 0">
            <div class="course" v-for="course in courses">
                <div class="course-header">
                    <div class="course-name">{{course.name}} <span>курс</span></div>
                    <div class="course-set">
                        <span class="glyphicon glyphicon-remove" @click="removeCourse(course.id)"></span>
                    </div>
                </div>
                <div class="course-body"></div>
                <div class="course-footer">
                    <department :course="course"></department>
                </div>
            </div>
        </div>
        <div class="course" v-else>
            <p>Список курсів порожній</p>
        </div>
        <div class="course-form">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Додати курс" v-model="newCourse.name" @keyup.enter="addCourse">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button" @click="addCourse">Додати</button>
                </span>
            </div>
        </div>
    </div>
</template>
<script>
    import Department from "./Department.vue";

    function getIndexItemById(list, id) {
        for (let i = 0; list.length; i++) {
            if (list[i].id === id) {
                return i;
            }
        }
        return -1;
    }

    export default {
        props: ["currentFaculty", "faculties"],
        components: {
            Department
        },
        data() {
            return {
                courseApi: this.$resource("/course{/id}"),
                departmentApi: this.$resource("/department{/id}"),
                courses: [],
                newCourse: {
                    name: "",
                }
            }
        },
        watch: {
            currentFaculty() {
                if (this.faculties.length > 0) {
                    this.getCourseFromDb();
                }
            }
        },
        created() {
            if (this.faculties.length > 0) {
                this.getCourseFromDb();
            }
        },
        methods: {
            getCourseFromDb() {
                this.courses = [];
                this.courseApi.get({id: this.currentFaculty.id}).then(res => {
                    res.json().then(data => {
                        data.forEach(course => {
                            this.courses.push(course);
                        })
                    })
                })
            },
            addCourse() {
                this.courseApi.save({id: this.currentFaculty.id}, this.newCourse).then(res => {
                    res.json().then(data => {
                        this.courses.push(data)
                    })
                });
                this.newCourse.name = "";
            },
            removeCourse(id) {
                this.courseApi.delete({id: id}).then(res => {
                    if (res.ok) {
                        let index = getIndexItemById(this.courses, id);
                        this.courses.splice(index, 1);
                    }
                })
            }
        }
    }
</script>
<style>

</style>
<template>
    <div v-if="teachers.length > 0">
        <div v-for="teacher in teachers">
            <div class="teacher">
                {{teacher.surname}}
                {{teacher.name}}
                {{teacher.patronymic}}
            </div>
        </div>
    </div>
    <div v-else>
        <p>Список викладачів порожній</p>
    </div>
</template>
<script>
    export default {
        props: ["userId"],
        data() {
            return {
                teacherApi: this.$resource("/teacher/get-teachers-by-userId/{id}"),
                teachers: [],
            }
        },
        created() {
            this.getTeachersFromDb();
        },
        methods: {
            getTeachersFromDb() {
                this.teacherApi.get({id: this.userId}).then(res => {
                    res.json().then(data => {
                        data.forEach(teacher => {
                            this.teachers.push(teacher);
                        })
                    })
                })
            }
        }
    }
</script>
<style scoped>
    .teacher {
        width: 150px;
        height: 150px;
        border: 1px solid black;
    }
</style>
<template>
    <div class="wrapper__lesson">
        <div class="panel panel-default col-md-8 col-md-offset-2">
            <div>
                <h3>Предмети</h3>
            </div>
            <div class="lesson-form">
                <input type="text" placeholder="Назва предмету" v-model="newLesson.name">
                <button @click="addLesson">Додати</button>
            </div>
            <div class="lessons" v-if="lessons.length > 0">
                <div class="lesson">
                    <table>
                        <tr>
                            <th>id</th>
                            <th>Назва предмету</th>
                            <th></th>
                        </tr>
                        <tr v-for="lesson in lessons">
                            <td>{{lesson.id}}</td>
                            <td>{{lesson.name}}</td>
                            <td><span class="glyphicon glyphicon-remove" @click="removeLesson(lesson.id)"></span></td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="teachers" v-else>
                <p>Список предметів порожній</p>
            </div>
        </div>
        <type-lesson></type-lesson>
    </div>
</template>
<script>
    import TypeLesson from "./TypeLesson.vue";

    function getIndexItemById(list, id) {
        for (let i = 0; list.length; i++) {
            if (list[i].id === id) {
                return i;
            }
        }
        return -1;
    }

    export  default {
        components: {
            TypeLesson
        },
        data() {
            return {
                lessonApi: this.$resource("/lessons{/id}"),
                lessons: [],
                newLesson: {
                    name: "",
                },
            }
        },
        created() {
            this.getLessonFromDb();
        },
        methods: {
            getLessonFromDb() {
                this.lessons = [];
                this.lessonApi.get().then(res => {
                    res.json().then(data => {
                        data.forEach(lesson => {
                            this.lessons.push(lesson);
                        })
                    })
                })
            },
            addLesson() {
                this.lessonApi.save(this.newLesson).then(res => {
                    res.json().then(data => {
                        this.lessons.push(data)
                    })
                });
                this.newLesson.name = "";
            },
            removeLesson(id) {
                this.lessonApi.delete({id: id}).then(res => {
                    if (res.ok) {
                        let index = getIndexItemById(this.lessons, id);
                        this.lessons.splice(index, 1);
                    }
                })
            }
        }
    }
</script>
<style>

</style>
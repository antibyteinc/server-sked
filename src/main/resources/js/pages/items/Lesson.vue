<template>
    <div class="wrapper">
        <div class="wrapper__lesson">
            <div class="panel panel-default col-md-4 col-md-offset-4">
                <div>
                    <h3 class="text-center">Предмети</h3>
                </div>
                <div class="lessons" v-if="lessons.length > 0" style="margin-top: 30px">
                    <div class="lesson">
                        <table class="table table-condensed table-hover">
                            <thead>
                                <tr>
                                    <th>id</th>
                                    <th>Назва предмету</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="lesson in lessons">
                                    <td>{{lesson.id}}</td>
                                    <td>{{lesson.name}}</td>
                                    <td><span class="glyphicon glyphicon-remove" @click="removeLesson(lesson.id)"></span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="teachers text-center" v-else style="margin-top: 20px">
                    <p>Список викладачів порожній</p>
                </div>
            </div>
            <type-lesson></type-lesson>

            <div class="panel-lesson-form panel panel-default col-md-4 col-md-offset-4">
                <div class="panel-body">
                    <div class="col-lg-12">
                        <div class="input-group lesson-form">
                            <input type="text" class="form-control" placeholder="Назва предмету" v-model="newLesson.name"
                                   @keyup.enter="addLesson">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button" @click="addLesson">Додати</button>
                            </span>
                        </div><!-- /input-group -->
                    </div><!-- /.col-lg-6 -->
                </div>
            </div>
        </div>
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

    export default {
        components: {
            TypeLesson
        },
        data() {
            return {
                lessonApi: this.$resource("/lesson{/id}"),
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
<style scoped>
    .wrapper {
        display: flex;
        flex-direction: column;
        justify-content: center;
        margin-top: 1%;
        margin-bottom: 100vh;
    }

    .panel-lesson-form {
        position: fixed; /* Фиксированное положение */
        left: 0;
        bottom: 0; /* Левый нижний угол */
    }


</style>
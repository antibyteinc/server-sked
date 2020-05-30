<template>
    <div class="wrapper__type-lesson">
        <div class="panel panel-default col-md-4 col-md-offset-4">
            <div>
                <h3 class="text-center">Тип предмету</h3>
            </div>
            <div class="col-lg-12">
                <div class="input-group type-lesson-form" style="margin-top: 30px; margin-bottom: 30px">
                    <input type="text" class="form-control" placeholder="Назва типу предмету" v-model="newTypeLesson.name" @keyup.enter="addTypeLesson">
                    <span class="input-group-btn">
                                <button class="btn btn-default" type="button" @click="addTypeLesson">Додати</button>
                            </span>
                </div><!-- /input-group -->
            </div><!-- /.col-lg-6 -->
            <div class="type-lessons" v-if="typeLessons.length > 0" style="margin-top: 30px">
                <div class="type-lesson">
                    <table class="table table-condensed table-hover">
                        <thead>
                            <tr>
                                <th>id</th>
                                <th>Назва типу предмету</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="typeLesson in typeLessons">
                                <td>{{typeLesson.id}}</td>
                                <td>{{typeLesson.name}}</td>
                                <td><span class="glyphicon glyphicon-remove" @click="removeTypeLesson(typeLesson.id)"></span></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="type-lessons text-center" v-else style="margin-top: 20px">
                <p>Список викладачів порожній</p>
            </div>
        </div>
    </div>
</template>
<script>
    function getIndexItemById(list, id) {
        for (let i = 0; list.length; i++) {
            if (list[i].id === id) {
                return i;
            }
        }
        return -1;
    }

    export  default {
        data() {
            return {
                typeLessonApi: this.$resource("/type-lesson{/id}"),
                typeLessons: [],
                newTypeLesson: {
                    name: "",
                },
            }
        },
        created() {
            this.getTypeLessonFromDb();
        },
        methods: {
            getTypeLessonFromDb() {
                this.typeLessons = [];
                this.typeLessonApi.get().then(res => {
                    res.json().then(data => {
                        data.forEach(lesson => {
                            this.typeLessons.push(lesson);
                        })
                    })
                })
            },
            addTypeLesson() {
                this.typeLessonApi.save(this.newTypeLesson).then(res => {
                    res.json().then(data => {
                        this.typeLessons.push(data)
                    })
                });
                this.newTypeLesson.name = "";
            },
            removeTypeLesson(id) {
                this.typeLessonApi.delete({id: id}).then(res => {
                    if (res.ok) {
                        let index = getIndexItemById(this.typeLessons, id);
                        this.typeLessons.splice(index, 1);
                    }
                })
            }
        }
    }
</script>
<style>

</style>
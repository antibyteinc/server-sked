<template>
    <div class="wrapper__type-lesson">
        <div class="panel panel-default col-md-8 col-md-offset-2">
            <div>
                <h3>Тип предмету</h3>
            </div>
            <div class="type-lesson-form">
                <input type="text" placeholder="Назва типу предмету" v-model="newTypeLesson.name" @keyup.enter="addTypeLesson">
                <button @click="addTypeLesson">Додати</button>
            </div>
            <div class="type-lessons" v-if="typeLessons.length > 0">
                <div class="type-lesson">
                    <table>
                        <tr>
                            <th>id</th>
                            <th>Назва типу предмету</th>
                            <th></th>
                        </tr>
                        <tr v-for="typeLesson in typeLessons">
                            <td>{{typeLesson.id}}</td>
                            <td>{{typeLesson.name}}</td>
                            <td><span class="glyphicon glyphicon-remove" @click="removeTypeLesson(typeLesson.id)"></span></td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="teachers" v-else>
                <p>Список типу предметів порожній</p>
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
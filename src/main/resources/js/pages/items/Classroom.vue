<template>
    <div class="wrapper">
        <div class="wrapper__classroom">
            <div class="panel panel-default col-md-6 col-md-offset-3">
                <div>
                    <h3 class="text-center">Аудиторії</h3>
                </div>
                <div class="classrooms" v-if="classrooms.length > 0" style="margin-top: 30px">
                    <div class="classroom">
                        <table class="table table-condensed table-hover">
                            <thead>
                            <tr>
                                <th>id</th>
                                <th>Корпус</th>
                                <th>Кабінет</th>
                                <th>Назва</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="classroom in classrooms">
                                <td>{{classroom.id}}</td>
                                <td>{{classroom.building}}</td>
                                <td>{{classroom.cabinet}}</td>
                                <td>{{classroom.name}}</td>
                                <td><span class="glyphicon glyphicon-remove" @click="removeClassroom(classroom.id)"></span></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="classrooms text-center" v-else style="margin-top: 20px">
                    <p>Список викладачів порожній</p>
                </div>
            </div>

            <div class="panel-building-form panel panel-default col-md-6 col-md-offset-3">
                <div class="panel-body">
                    <div class="classroom-form">
                        <form action="" class="form-inline">
                            <input type="text" placeholder="Корпус" class="form-control" v-model="newClassroom.building" @keyup.enter="addClassroom">
                            <input type="text" placeholder="Кабінет" class="form-control" v-model="newClassroom.cabinet" @keyup.enter="addClassroom">
                            <input type="text" placeholder="Назва" class="form-control" v-model="newClassroom.name" @keyup.enter="addClassroom">
                            <button class="btn btn-default" @click="addClassroom">Додати</button>
                        </form>
                    </div>
                </div>
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
                classroomApi: this.$resource("/classroom{/id}"),
                classrooms: [],
                newClassroom: {
                    building: "",
                    cabinet: "",
                    name: "",
                },
            }
        },
        created() {
            this.getClassroomFromDb();
        },
        methods: {
            getClassroomFromDb() {
                this.classrooms = [];
                this.classroomApi.get().then(res => {
                    res.json().then(data => {
                        data.forEach(classroom => {
                            this.classrooms.push(classroom);
                        })
                    })
                })
            },
            addClassroom() {
                this.classroomApi.save(this.newClassroom).then(res => {
                    res.json().then(data => {
                        this.classrooms.push(data)
                    })
                });
                this.newClassroom.building = "";
                this.newClassroom.cabinet = "";
                this.newClassroom.name = "";
            },
            removeClassroom(id) {
                this.classroomApi.delete({id: id}).then(res => {
                    if (res.ok) {
                        let index = getIndexItemById(this.classrooms, id);
                        this.classrooms.splice(index, 1);
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

    .panel-building-form {
        position: fixed; /* Фиксированное положение */
        left: 0; bottom: 0; /* Левый нижний угол */
    }
</style>
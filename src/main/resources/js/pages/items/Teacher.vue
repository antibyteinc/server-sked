<template>
    <div class="wrapper">
        <div class="wrapper__teacher">
            <div class="panel panel-default col-md-10 col-md-offset-1">
                <div>
                    <h3 class="text-center">Викладачі</h3>
                </div>
                <div class="teachers" v-if="teachers.length > 0" style="margin-top: 30px">
                    <div class="teacher">
                        <table class="table table-condensed table-hover">
                            <thead>
                            <tr>
                                <th>id</th>
                                <th>Призвище</th>
                                <th>Ім'я</th>
                                <th>По-батькові</th>
                                <th>Звання</th>
<!--                                <th>Дата народження</th>-->
                                <th>Конт. телефон</th>
                                <th>Пошта</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr v-for="teacher in teachers">
                                    <td>{{teacher.id}}</td>
                                    <td>{{teacher.surname}}</td>
                                    <td>{{teacher.name}}</td>
                                    <td>{{teacher.patronymic}}</td>
                                    <td>{{teacher.rankTeacher}}</td>
<!--                                    <td>{{teacher.birthday}}</td>-->
                                    <td>{{teacher.phoneNumber}}</td>
                                    <td>{{teacher.mail}}</td>
                                    <td><span class="glyphicon glyphicon-remove" @click="removeTeacher(teacher.id)"></span></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="teachers text-center" v-else style="margin-top: 20px">
                    <p>Список викладачів порожній</p>
                </div>
            </div>

            <div class="panel-teacher-form panel panel-default col-md-10 col-md-offset-1">
                <div class="panel-body">
                    <div class="teacher-form">
                        <form action="" class="form-inline">
                            <input type="text" class="form-control" placeholder="Призвище" v-model="newTeacher.surname" @keyup.enter="addTeacher">
                            <input type="text" class="form-control" placeholder="Ім'я" v-model="newTeacher.name" @keyup.enter="addTeacher">
                            <input type="text" class="form-control" placeholder="По-батькові" v-model="newTeacher.patronymic" @keyup.enter="addTeacher">
                            <input type="text" class="form-control" placeholder="Звання" v-model="newTeacher.rankTeacher" @keyup.enter="addTeacher">
                            <!--                    <input type="date" placeholder="Дата народження" v-model="newTeacher.birthday" @keyup.enter="addTeacher">-->
                            <input type="text" class="form-control" placeholder="Конт. телефон" v-model="newTeacher.phoneNumber" @keyup.enter="addTeacher">
                            <input type="text" class="form-control" placeholder="Пошта" v-model="newTeacher.mail" @keyup.enter="addTeacher">
                            <button class="btn btn-default" @click="addTeacher">Додати</button>
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
        props: ["institutionId"],
        data() {
            return {
                teacherApi: this.$resource("/teacher{/id}"),

                teachers: [],
                newTeacher: {
                    surname: "",
                    name: "",
                    patronymic: "",
                    rankTeacher: "",
                    birthday: "",
                    phoneNumber: "",
                    mail: ""
                },
            }
        },
        created() {
            this.getTeacherFromDb();
        },
        methods: {
            getTeacherFromDb() {
                this.teachers = [];
                this.teacherApi.get().then(res => {
                    res.json().then(data => {
                        data.forEach(teacher => {
                            this.teachers.push(teacher);
                        })
                    })
                })
            },
            addTeacher() {
                this.teacherApi.save(this.newTeacher).then(res => {
                    res.json().then(data => {
                        this.teachers.push(data)
                    })
                });
                this.newTeacher.surname = "";
                this.newTeacher.name = "";
                this.newTeacher.patronymic = "";
                this.newTeacher.rankTeacher = "";
                this.newTeacher.birthday = "";
                this.newTeacher.phoneNumber = "";
                this.newTeacher.mail = "";
            },
            removeTeacher(id) {
                this.teacherApi.delete({id: id}).then(res => {
                    if (res.ok) {
                        let index = getIndexItemById(this.teachers, id);
                        this.teachers.splice(index, 1);
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

    .teacher {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
    }

    .panel-teacher-form {
        position: fixed; /* Фиксированное положение */
        left: 0; bottom: 0; /* Левый нижний угол */
    }
</style>
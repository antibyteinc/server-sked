<template>
    <div class="wrapper__teacher">
        <div class="panel panel-default col-md-8 col-md-offset-2">
            <div>
                <h3>{{institutionName}}</h3>
                <h3>Викладачі</h3>
            </div>
            <div class="teacher-form">
                <input type="text" placeholder="Призвище" v-model="newTeacher.surname">
                <input type="text" placeholder="Ім'я" v-model="newTeacher.name">
                <input type="text" placeholder="По-батькові" v-model="newTeacher.patronymic">
                <input type="text" placeholder="Звання" v-model="newTeacher.rankTeacher">
                <input type="date" placeholder="Дата народження" v-model="newTeacher.birthday">
                <input type="text" placeholder="Конт. телефон" v-model="newTeacher.phoneNumber">
                <input type="text" placeholder="Пошта" v-model="newTeacher.mail">
                <button @click="addTeacher">Додати</button>
            </div>
            <div class="teachers" v-if="teachers.length > 0">
                <div class="teacher">
                    <table>
                        <tr>
                            <th>id</th>
                            <th>Призвище</th>
                            <th>Ім'я</th>
                            <th>По-батькові</th>
                            <th>Звання</th>
                            <th>Дата народження</th>
                            <th>Конт. телефон</th>
                            <th>Пошта</th>
                            <th></th>
                        </tr>
                        <tr v-for="teacher in teachers">
                            <td>{{teacher.id}}</td>
                            <td>{{teacher.surname}}</td>
                            <td>{{teacher.name}}</td>
                            <td>{{teacher.patronymic}}</td>
                            <td>{{teacher.rankTeacher}}</td>
                            <td>{{teacher.birthday}}</td>
                            <td>{{teacher.phoneNumber}}</td>
                            <td>{{teacher.mail}}</td>
                            <td><span class="glyphicon glyphicon-remove" @click="removeTeacher(teacher.id)"></span></td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="teachers" v-else>
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
        props: ["institutionId"],
        data() {
            return {
                institutionName: "",
                teacherApi: this.$resource("/teachers{/id}"),

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
            this.getInstitutionName();
            this.getTeacherFromDb();
        },
        methods: {
            getInstitutionName() {
                this.$resource("/institutions/get-name{/id}").get({id: this.institutionId}).then(res => {
                    res.text().then(data => {
                        this.institutionName = data;
                    });
                })
            },
            getTeacherFromDb() {
                this.teachers = [];
                this.teacherApi.get({id: this.institutionId}).then(res => {
                    res.json().then(data => {
                        data.forEach(teacher => {
                            this.teachers.push(teacher);
                        })
                    })
                })
            },
            addTeacher() {
                this.teacherApi.save({id: this.institutionId}, this.newTeacher).then(res => {
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
<style>
    .teacher {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
    }
</style>
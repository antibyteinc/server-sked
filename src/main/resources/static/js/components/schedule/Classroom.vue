<template>
    <div class="wrapper__classroom">
        <div class="panel panel-default col-md-8 col-md-offset-2">
            <div class="classroom-form">
                <input type="text" placeholder="Корпус" v-model="newClassroom.building">
                <input type="text" placeholder="Кабінет" v-model="newClassroom.cabinet">
                <input type="text" placeholder="Назва" v-model="newClassroom.name">
                <button @click="addClassroom">Додати</button>
            </div>
            <div class="classrooms" v-if="classrooms.length > 0">
                <div class="classroom">
                    <table>
                        <tr>
                            <th>id</th>
                            <th>Корпус</th>
                            <th>Кабінет</th>
                            <th>Назва</th>
                            <th></th>
                        </tr>
                        <tr v-for="classroom in classrooms">
                            <td>{{classroom.id}}</td>
                            <td>{{classroom.building}}</td>
                            <td>{{classroom.cabinet}}</td>
                            <td>{{classroom.name}}</td>
                            <td><span class="glyphicon glyphicon-remove" @click="removeClassroom(classroom.id)"></span></td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="classrooms" v-else>
                <p>Список кабінетів порожній</p>
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
        props: ["groupId"],
        data() {
            return {
                groupApi: this.$resource("/groups/get-institution-id{/id}"),
                institutionId: 0,

                classroomApi: this.$resource("/classrooms{/id}"),

                classrooms: [],
                newClassroom: {
                    building: "",
                    cabinet: "",
                    name: "",
                },
                institution: null
            }
        },
        watch: {
            institutionId() {
                this.getClassroomFromDb();
            }
        },
        created() {
            this.getInstitutionId();
            this.getInstitutionId();
            this.getClassroomFromDb();
        },
        methods: {
            getInstitutionId() {
                this.groupApi.get({id: this.groupId}).then(res => {
                    res.json().then(data => {
                        this.institutionId = data;
                    });
                });
            },
            getClassroomFromDb() {
                this.classrooms = [];
                this.classroomApi.get({id: this.institutionId}).then(res => {
                    res.json().then(data => {
                        data.forEach(classroom => {
                            this.classrooms.push(classroom);
                        })
                    })
                })
            },
            addClassroom() {
                this.classroomApi.save({id: this.institutionId}, this.newClassroom).then(res => {
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
<style>

</style>
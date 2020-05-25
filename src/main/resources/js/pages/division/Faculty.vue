<template>
    <div class="panel panel-default col-md-8 col-md-offset-2">
        <div class="container">
            <div class="wrapper">
                <div class="wrapper__faculty">
                    <div class="faculties" v-if="faculties.length > 0">
                        <div class="faculty" v-for="faculty in faculties">
                            <button @click="switchFaculty(faculty)">{{faculty.name}}</button>
                            <span class="glyphicon glyphicon-remove" @click="removeFaculty(faculty.id)"></span>
                        </div>
                    </div>
                    <div class="faculties" v-else>
                        <p>Список факультетів порожній</p>
                    </div>

                    <div class="faculty-form">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Назва факультету"
                                   v-model="newFaculty.name" @keyup.enter="addFaculty">
                            <span class="input-group-btn">
                            <button class="btn btn-default" type="button" @click="addFaculty">Додати</button>
                        </span>
                        </div>
                    </div>
                </div>
                <course :currentFaculty="currentFaculty" :faculties="faculties"></course>
            </div>
            <span class="glyphicon glyphicon-remove" @click="close"></span>
        </div>
    </div>
</template>
<script>
    import Course from "./Course.vue";

    function getIndexItemById(list, id) {
        for (let i = 0; list.length; i++) {
            if (list[i].id === id) {
                return i;
            }
        }
        return -1;
    }

    export default {
        props: ["semester"],
        components: {
            Course
        },
        data() {
            return {
                currentFaculty: null,
                facultyApi: this.$resource("/faculty{/id}"),
                faculties: [],
                newFaculty: {
                    name: "",
                },
            }
        },
        watch: {
            semester() {
                this.getFacultyFromDb(this.semester.id);
            },
        },
        created() {
            this.getFacultyFromDb(this.semester.id);
        },
        methods: {
            switchFaculty(faculty) {
                this.currentFaculty = faculty;
            },
            getFacultyFromDb(id) {
                this.faculties = [];
                this.facultyApi.get({id: id}).then(res => {
                    res.json().then(data => {
                        data.forEach(faculty => {
                            this.faculties.push(faculty);
                        });
                        this.currentFaculty = data[0];
                    })
                })
            },
            addFaculty() {
                this.facultyApi.save({id: this.semester.id}, this.newFaculty).then(res => {
                    res.json().then(data => {
                        this.faculties.push(data);
                        this.currentFaculty = data;
                    })
                });

                this.newFaculty.name = "";
            },
            removeFaculty(id) {
                this.facultyApi.delete({id: id}).then(res => {
                    if (res.ok) {
                        let index = getIndexItemById(this.faculties, id);
                        this.faculties.splice(index, 1);
                    }
                })
            },
            close() {
                this.$emit("closeFaculty", true);
            }
        }
    }
</script>
<style>
    .container {
        margin: 0 auto;
        width: 100%;
        max-width: 1600px;
        /*border: 1px solid black;*/
    }

    .wrapper {
        width: 100%;
        position: relative;
        /*border: 1px solid black;*/
    }

    /*faculty*/
    .wrapper__faculty {
        display: flex;
        flex-direction: row;
        justify-content: space-around;
    }

    .faculties {
        display: flex;
        flex-direction: row;
        flex-wrap: nowrap;
        width: 100%;
        max-width: 1370px;
        overflow-y: auto;
        margin: 20px;
        border: 1px solid black;
        border-radius: 8px;
    }

    .faculty {
        display: flex;
        flex-direction: row;
        flex-wrap: nowrap;
        margin: 0 5px;
    }

    .faculty-form {
        margin-top: 6px;
        padding: 15px;
    }

    /*course*/
    .courses {
        display: flex;
        flex-direction: row;
        flex-wrap: nowrap;
        width: 100%;
        max-width: 1370px;
        overflow-y: auto;
        /*border: 1px solid blue;*/
    }

    /*scrollbar*/
    .faculties::-webkit-scrollbar,
    .courses::-webkit-scrollbar {
        width: 10px;
        height: 7px;
    }

    .faculties::-webkit-scrollbar-track,
    .courses::-webkit-scrollbar-track {
        background: white;
        border-radius: 5px;
    }

    .faculties::-webkit-scrollbar-thumb,
    .courses::-webkit-scrollbar-thumb {
        background: #888888;
        border-radius: 5px;
    }

    .faculties::-webkit-scrollbar-thumb,
    .courses::-webkit-scrollbar-thumb {
        background: #555555;
    }

    /*course*/
    .wrapper__course {
        padding-top: 5px;
        display: flex;
        flex-direction: row;
        justify-content: space-around;
        /*border: 1px solid red;*/
    }

    .courses {
        margin: 20px;
        padding: 10px;
        border: 1px solid black;
        border-radius: 7px;
    }

    .course {
        min-width: 230px;
        max-width: 230px;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        border-radius: 8px;
        padding: 10px;
        margin-right: 20px;
        /*border: 1px solid black;*/
    }

    .course:last-child {
        margin-right: 15px;
    }

    .course-form {
        margin-top: 10px;
        padding: 15px;
    }

    .course-header {
        position: relative;
        display: flex;
        flex-direction: row;
        justify-content: center;
        padding-bottom: 5px;
    }

    .course-name {
        font-size: 22px;
        font-weight: 700;
    }

    .course-name span {
        font-size: 14px;
        color: #888888;
        font-weight: 200;
    }

    .course-set {
        position: absolute;
        top: 0;
        right: 0;
        margin-top: 8px;
        margin-right: 2px;
    }

    .course-footer {
        display: flex;
        flex-direction: column;
        justify-content: start;
        flex-grow: 1;
    }
</style>
<template>
    <div class="wrapper">
        <div class="wrapper__semester">
            <div class="panel panel-default col-md-8 col-md-offset-2">
                <div class="semester-header">
                    <h3>Мої семестри у закладі <b>{{institution.name}}</b></h3>
                </div>
                <div class="semester-content">
                    <div v-if="semesters.length > 0">
                        <table>
                            <tr>
                                <th>id</th>
                                <th>Назва</th>
                                <th>Початок</th>
                                <th>Кінец</th>
                            </tr>
                            <tr v-for="semester in semesters">
                                <td>{{semester.id}}</td>
<!--                                <td><router-link :to="'/my-faculties/' + semester.id + '/' + semester.name"><button>{{semester.name}}</button></router-link></td>-->
                                <td><button @click="switchSemester(semester)">{{semester.name}}</button></td>
                                <td>{{dateToString(semester.start)}}</td>
                                <td>{{dateToString(semester.finish)}}</td>
                                <td><span class="glyphicon glyphicon-remove" @click="removeSemester(semester.id)"></span></td>
                            </tr>
                        </table>
                    </div>
                    <div v-else>
                        <p>Список семестрів порожній</p>
                    </div>
                </div>
                <div class="semester-footer">
                    <input type="text" placeholder="Назва семестру" v-model="newSemester.name" @keyup.enter="addSemester">
                    Початок: <input type="date" v-model="newSemester.start">
                    Кінец: <input type="date" v-model="newSemester.finish">
                    <button @click="addSemester">Додати</button>
                </div>
            </div>
            <span class="glyphicon glyphicon-remove" @click="close"></span>
        </div>
        <component v-if="currentSemester" is="faculty" :semester="currentSemester" @closeFaculty="closeFaculty"></component>
    </div>
</template>
<script>
    import Faculty from "./Faculty.vue";

    function getIndexItemById(list, id) {
        for (let i = 0; list.length; i++) {
            if (list[i].id === id) {
                return i;
            }
        }
        return -1;
    }

    function dateLongToStringForInput(date) {
        date = new Date(date);
        let d = date.getDate();
        let m = date.getMonth() + 1;
        let y = date.getFullYear();

        return "" + y + "-" + (m <= 9 ? '0' + m : m) + "-" + (d <= 9 ? '0' + d : d);
    }

    function dateLongToString(date) {
        date = new Date(date);
        // let strMonth = ["січ", "лют", "бер", "квіт", "трав", "черв", "лип", "серп", "вер", "жовт", "лист", "груд"];

        let d = date.getDate();
        // let m = strMonth[date.getMonth()];
        let m = date.getMonth() + 1;
        let y = date.getFullYear();
        // let h = date.getHours();
        // let min = date.getMinutes();
        // let s = date.getSeconds();

        return "" + (d <= 9 ? '0' + d : d) + "." + (m <= 9 ? '0' + m : m) + "." + y;
    }

    export default {
        props: ["institution"],
        data() {
            return {
                currentSemester: null,

                semesterApi: this.$resource("/semester{/id}"),
                semesters: [],
                newSemester: {
                    name: "",
                    start: null,
                    finish: null
                }
            }
        },
        components: {
            Faculty
        },
        watch: {
            institution() {
                this.getSemesterFromDb(this.institution.id);
                this.currentSemester = null
            }
        },
        created() {
            this.getSemesterFromDb(this.institution.id);
            this.setSemesterDateForInput()


        },
        methods: {
            dateToString(date) {
                return dateLongToString(date)
            },
            close() {
                this.$emit("closeSemester", true);
            },
            closeFaculty(data) {
                if (data) {
                    this.currentSemester = null;
                }
            },
            switchSemester(semester) {
                this.currentSemester = semester;
            },
            getSemesterFromDb(id) {
                this.semesters = [];
                this.semesterApi.get({id: id}).then(res => {
                    res.json().then(data => {
                        data.forEach(semester => {
                            this.semesters.push(semester);
                        })
                    })
                })
            },
            addSemester() {
                this.newSemester.start = new Date(this.newSemester.start).getTime();
                this.newSemester.finish = new Date(this.newSemester.finish).getTime();

                this.semesterApi.save({id: this.institution.id}, this.newSemester).then(res => {
                    res.json().then(data => {
                        this.semesters.push(data)
                    })
                });

                this.newSemester.name = "";
                this.setSemesterDateForInput();
            },
            removeSemester(id) {
                this.semesterApi.delete({id: id}).then(res => {
                    if (res.ok) {
                        let index = getIndexItemById(this.semesters, id);
                        this.semesters.splice(index, 1);
                    }
                })
            },
            setSemesterDateForInput() {
                this.newSemester.start = "2020-09-01";
                this.newSemester.finish = "2020-12-24";
            }
        }
    }
</script>
<style scoped>

</style>
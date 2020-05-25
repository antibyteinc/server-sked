<template>
    <div class="wrapper">
        <div class="wrapper__semester">
            <div class="panel panel-default col-md-8 col-md-offset-2">
                <div class="semester-header">
                    <h3>Мої семестри у закладі <b>{{division.name}}</b></h3>
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
                                <td><button @click="switchSemester(semester)">{{semester.name}}</button></td>
                                <td>{{dateToString(semester.start)}}</td>
                                <td>{{dateToString(semester.finish)}}</td>
                            </tr>
                        </table>
                    </div>
                    <div v-else>
                        <p>Список семестрів порожній</p>
                    </div>
                </div>
            </div>
            <view-faculty :semester="currentSemester"></view-faculty>
        </div>
    </div>
</template>
<script>
    import ViewFaculty from "./ViewFaculty.vue";

    export default {
        props: ["division"],
        components: {
            ViewFaculty
        },
        created() {
            this.getSemesterFromDb();
        },
        data() {
            return {
                currentSemester: null,
                semesterApi: this.$resource("/semester{/id}"),
                semesters: [],
            }
        },
        watch: {
            division() {
                this.getSemesterFromDb();
            }
        },
        methods: {
            getSemesterFromDb() {
                this.semesters = [];
                this.semesterApi.get({id: this.division.id}).then(res => {
                    res.json().then(data => {
                        data.forEach(semester => {
                            this.semesters.push(semester);
                        })
                    })
                })
            },
            switchSemester(semester) {
                this.currentSemester = semester;
            },
            dateToString(date) {
                date = new Date(date);

                let d = date.getDate();
                let m = date.getMonth() + 1;
                let y = date.getFullYear();

                return "" + (d <= 9 ? '0' + d : d) + "." + (m <= 9 ? '0' + m : m) + "." + y;
            }
        }
    }
</script>
<style scoped>

</style>
<template>
    <div class="wrapper">
        <div class="wrapper__semester">
            <div class="panel panel-default col-md-8 col-md-offset-2">
                <div class="semester-header text-center">
                    <!--                    <h3>Мої семестри у закладі <b>{{institution.name}}</b></h3>-->
                    <h3>Семестри</h3>
                </div>
                <div class="semester-content" style="margin-top: 30px">
                    <div v-if="semesters.length > 0">
                        <table class="table table-condensed">
                            <tr>
                                <th>id</th>
                                <th>Назва</th>
                                <th>Початок</th>
                                <th>Кінец</th>
                                <th></th>
                            </tr>
                            <tbody>
                                <tr v-for="semester in semesters">
                                    <td>{{semester.id}}</td>
                                    <td><button class="btn-division btn btn-default" @click="switchSemester(semester)" style="width: 250px">{{semester.name}}</button></td>
                                    <td>{{dateToString(semester.start)}}</td>
                                    <td>{{dateToString(semester.finish)}}</td>
                                </tr>
                            </tbody>
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
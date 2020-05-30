<template>
    <div class="wrapper">
        <div class="wrapper__division panel panel-default col-md-8 col-md-offset-2">
            <h3 class="text-center">Розклади</h3>
            <div class="divisions" style="margin-top: 30px">
                <div v-if="divisions.length > 0">
                    <table class="institution table table-condensed">
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>Назва розкладу</th>
                            <th>Дата створення</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr v-for="division in divisions" :key="division.id">
                                <td>{{division.id}}</td>
                                <td>
                                    <button class="btn-division btn btn-default" @click="switchDivision(division)">{{division.name}}</button>
                                </td>
                                <td>{{dateToString(division.lastModified)}}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div v-else>
                    <p>Список розкладів порожній</p>
                </div>
            </div>
        </div>
<!--        <component v-if="currentInstitution" is="semester" :institution="currentInstitution" @closeSemester="closeSemester"></component>-->
        <component v-if="currentDivision" is="view-semester" :division="currentDivision"></component>
    </div>
</template>
<script>
    import ViewSemester from "./ViewSemester.vue";

    export default {
        props: ["userId"],
        data() {
            return {
                currentDivision: null,
                divisionApi: this.$resource("/division/get-division-by-userId{/id}"),
                divisions: [],
            }
        },
        components: {
            ViewSemester
        },
        created() {
            this.getDivisionsFromDb();
        },
        methods: {
            getDivisionsFromDb() {
                this.divisions = [];
                this.divisionApi.get({id: this.userId}).then(res => {
                    res.json().then(data => {
                        data.forEach(division => {
                            this.divisions.push(division);
                        })
                    })
                });
            },
            switchDivision(division) {
                this.currentDivision = division;
            },
            dateToString(date) {
                date = new Date(date);
                let strMonth = ["січ", "лют", "бер", "квіт", "трав", "черв", "лип", "серп", "вер", "жовт", "лист", "груд"];

                let d = date.getDate();
                let m = strMonth[date.getMonth()];
                let y = date.getFullYear();
                let h = date.getHours();
                let min = date.getMinutes();
                let s = date.getSeconds();

                return "" + (d <= 9 ? '0' + d : d) + "." + (m <= 9 ? '0' + m : m) + "." + y + " " + (h <= 9 ? '0' + h : h) + ":" + (min <= 9 ? '0' + min : min) + ":" + (s <= 9 ? '0' + s : s);
            }
        }
    }
</script>
<style scoped>
    .wrapper {
        display: flex;
        flex-direction: column;
        justify-content: center;
        margin-bottom: 60vh;
    }

    .wrapper__division {
        margin-top: 50px;
        padding: 10px;
    }

    .division {
        /*width: 50%;*/
    }

    .btn-division {
        max-width: 300px;
    }
</style>
<template>
    <div class="wrapper">
        <div class="wrapper__institution panel panel-default col-md-8 col-md-offset-2">
            <h3 class="text-center">Створенні розклади</h3>
            <div class="institutions">
                <div v-if="institutions.length > 0">
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
                        <tr v-for="institution in institutions" :key="institution.id">
                            <td>{{institution.id}}</td>
                            <td>
                                <button class="btn-division btn btn-default" @click="switchInstitution(institution)" style="width: 250px">{{institution.name}}</button>
                            </td>
                            <td>{{dateToString(institution.lastModified)}}</td>
                            <td><span class="glyphicon glyphicon-remove"
                                      @click="removeInstitution(institution.id)"></span></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div v-else>
                    <p>Список розкладів порожній</p>
                </div>
            </div>

            <div class="institution-panel col-lg-12">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Назва розкладу" v-model="newInstitution.name"
                           @keyup.enter="addDivision">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button" @click="addDivision">Додати</button>
                    </span>
                </div><!-- /input-group -->
            </div><!-- /.col-lg-6 -->
        </div>
        <component v-if="currentInstitution" is="semester" :institution="currentInstitution"
                   @closeSemester="closeSemester"></component>
    </div>
</template>
<script>
    import Semester from "./Semester.vue";

    function getIndexItemById(list, id) {
        for (let i = 0; list.length; i++) {
            if (list[i].id === id) {
                return i;
            }
        }
        return -1;
    }

    export default {
        data() {
            return {
                currentInstitution: null,

                institutionApi: this.$resource("/division/user{/id}"),
                institutions: [],
                newInstitution: {
                    name: ""
                }
            }
        },
        components: {
            Semester
        },
        created() {
            this.getInstitutionsFromDb()
        },
        methods: {
            closeSemester(data) {
                if (data) {
                    this.currentInstitution = null;
                }
            },
            switchInstitution(institution) {
                this.currentInstitution = institution;
            },
            getInstitutionsFromDb() {
                this.institutions = [];
                this.institutionApi.get().then(res => {
                    res.json().then(data => {
                        data.forEach(institution => {
                            this.institutions.push(institution);
                        })
                    })
                })
            },
            addDivision() {
                this.institutionApi.save(this.newInstitution).then(res => {
                    res.json().then(data => {
                        this.institutions.push(data);

                        this.newInstitution = {
                            name: ""
                        }
                    })
                })
            },
            removeInstitution(id) {
                this.institutionApi.delete({id: id}).then(res => {
                    if (res.ok) {
                        let index = getIndexItemById(this.institutions, id);
                        this.institutions.splice(index, 1);
                    }
                })
            },
            dateToString(date) {
                date = new Date(date);
                let strMonth = ["січ", "лют", "бер", "квіт", "трав", "черв", "лип", "серп", "вер", "жовт", "лист", "груд"];

                let d = date.getDate();
                let m = strMonth[date.getMonth()];
                // let m = date.getMonth() + 1;
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
        margin-top: 1%;
        margin-bottom: 100vh;
    }

    .wrapper__institution {
        margin-top: 60px;
        padding: 50px;
    }

    .institution {
        margin-top: 50px;
    }

    .btn-division {
        max-width: 300px;
    }
</style>